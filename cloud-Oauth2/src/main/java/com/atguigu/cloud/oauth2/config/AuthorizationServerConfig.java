package com.atguigu.cloud.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.bootstrap.encrypt.KeyProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.security.KeyPair;

@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    //jwt令牌转换器
    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    TokenStore tokenStore;
    @Autowired
    private CustomUserAuthenticationConverter customUserAuthenticationConverter;

    //读取密钥的配置
    @Bean("keyProp")
    public KeyProperties keyProperties(){
        return new KeyProperties();
    }

    @Resource(name = "keyProp")
    private KeyProperties keyProperties;


    //客户端配置
    @Bean
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(this.dataSource).clients(this.clientDetails());
       /* clients.inMemory()
                .withClient("XcWebApp")//客户端id
                .secret("XcWebApp")//密码，要保密
                .accessTokenValiditySeconds(60)//访问令牌有效期
                .refreshTokenValiditySeconds(60)//刷新令牌有效期
                //授权客户端请求认证服务的类型authorization_code：根据授权码生成令牌，
                // client_credentials:客户端认证，refresh_token：刷新令牌，password：密码方式认证
                .authorizedGrantTypes("authorization_code", "client_credentials", "refresh_token", "password")
                .scopes("app");//客户端范围，名称自定义，必填*/
    }

    //token的存储方法
//    @Bean
//    public InMemoryTokenStore tokenStore() {
//        //将令牌存储到内存
//        return new InMemoryTokenStore();
//    }
//    @Bean
//    public TokenStore tokenStore(RedisConnectionFactory redisConnectionFactory){
//        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
//        return redisTokenStore;
//    }
    @Bean
    @Autowired
    public TokenStore tokenStore(JwtAccessTokenConverter jwtAccessTokenConverter) {
        return new JwtTokenStore(jwtAccessTokenConverter);
    }
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(CustomUserAuthenticationConverter customUserAuthenticationConverter) {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        KeyPair keyPair = new KeyStoreKeyFactory
                (keyProperties.getKeyStore().getLocation(), keyProperties.getKeyStore().getSecret().toCharArray())
                .getKeyPair(keyProperties.getKeyStore().getAlias(),keyProperties.getKeyStore().getPassword().toCharArray());
//        converter.setKeyPair(keyPair);
        converter.setSigningKey("fangyi");
        //配置自定义的CustomUserAuthenticationConverter
        DefaultAccessTokenConverter accessTokenConverter = (DefaultAccessTokenConverter) converter.getAccessTokenConverter();
        accessTokenConverter.setUserTokenConverter(customUserAuthenticationConverter);
        return converter;
    }
    //授权服务器端点配置
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        /*Collection<TokenEnhancer> tokenEnhancers = applicationContext.getBeansOfType(TokenEnhancer.class).values();
        TokenEnhancerChain tokenEnhancerChain=new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(new ArrayList<>(tokenEnhancers));

        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setReuseRefreshToken(true);
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setTokenStore(tokenStore);
        defaultTokenServices.setAccessTokenValiditySeconds(1111111);
        defaultTokenServices.setRefreshTokenValiditySeconds(1111111);
        defaultTokenServices.setTokenEnhancer(tokenEnhancerChain);

        endpoints
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                        //.tokenStore(tokenStore);
                .tokenServices(defaultTokenServices);*/
        endpoints.accessTokenConverter(jwtAccessTokenConverter)
                .authenticationManager(authenticationManager)//认证管理器
                .tokenStore(tokenStore)//令牌存储
                .userDetailsService(userDetailsService);//用户信息service
    }

    //授权服务器的安全配置
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
//        oauthServer.checkTokenAccess("isAuthenticated()");//校验token需要认证通过，可采用http basic认证
        oauthServer.allowFormAuthenticationForClients()
                .passwordEncoder(new BCryptPasswordEncoder())
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

//@Configuration
//@EnableAuthorizationServer
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)//激活方法上的PreAuthorize注解
//class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
//    //数据源，用于从数据库获取数据进行认证操作，测试可以从内存中获取
//    @Autowired
//    private DataSource dataSource;
//    //jwt令牌转换器
//    @Autowired
//    private JwtAccessTokenConverter jwtAccessTokenConverter;
//    //SpringSecurity 用户自定义授权认证类
//    @Autowired
//    UserDetailsService userDetailsService;
//    //授权认证管理器
//    @Autowired
//    AuthenticationManager authenticationManager;
//    //令牌持久化存储接口
//    @Autowired
//    TokenStore tokenStore;
//    @Autowired
//    private CustomUserAuthenticationConverter customUserAuthenticationConverter;
//
//    /***
//     * 客户端信息配置
//     * @param clients
//     * @throws Exception
//     */
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.jdbc(dataSource).clients(clientDetails());
//      /*  clients.inMemory()
//                .withClient("changgou")          //客户端id
//                .secret("changgou")                      //秘钥
//                .redirectUris("http://localhost")       //重定向地址
//                .accessTokenValiditySeconds(3600)          //访问令牌有效期
//                .refreshTokenValiditySeconds(3600)         //刷新令牌有效期
//                .authorizedGrantTypes(
//                        "authorization_code",          //根据授权码生成令牌
//                        "client_credentials",          //客户端认证
//                        "refresh_token",                //刷新令牌
//                        "password")                     //密码方式认证
//                .scopes("app");                         //客户端范围，名称自定义，必填*/
//    }
//
//    /***
//     * 授权服务器端点配置
//     * @param endpoints
//     * @throws Exception
//     */
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.accessTokenConverter(jwtAccessTokenConverter)
//                .authenticationManager(authenticationManager)//认证管理器
//                .tokenStore(tokenStore)                       //令牌存储
//                .userDetailsService(userDetailsService);     //用户信息service
//        //endpoints.tokenServices(defaultTokenServices());
//    }
//
//    /***
//     * 授权服务器的安全配置
//     * @param oauthServer
//     * @throws Exception
//     */
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
//        oauthServer.allowFormAuthenticationForClients()
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .tokenKeyAccess("permitAll()")
//                .checkTokenAccess("isAuthenticated()");
//    }
//
//
//    //读取密钥的配置
//    @Bean("keyProp")
//    public KeyProperties keyProperties(){
//        return new KeyProperties();
//    }
//
//    @Resource(name = "keyProp")
//    private KeyProperties keyProperties;
//
//    //客户端配置
//    @Bean
//    public ClientDetailsService clientDetails() {
//        return new JdbcClientDetailsService(dataSource);
//    }
//
//    @Bean
//    @Autowired
//    public TokenStore tokenStore(JwtAccessTokenConverter jwtAccessTokenConverter) {
//        return new JwtTokenStore(jwtAccessTokenConverter);
//    }
//
//    /****
//     * JWT令牌转换器
//     * @param customUserAuthenticationConverter
//     * @return
//     */
//    @Bean
//    public JwtAccessTokenConverter jwtAccessTokenConverter(CustomUserAuthenticationConverter customUserAuthenticationConverter) {
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        KeyPair keyPair = new KeyStoreKeyFactory(
//                keyProperties.getKeyStore().getLocation(),                          //证书路径 changgou.jks
//                keyProperties.getKeyStore().getSecret().toCharArray())              //证书秘钥 changgouapp
//                .getKeyPair(
//                        keyProperties.getKeyStore().getAlias(),                     //证书别名 changgou
//                        keyProperties.getKeyStore().getPassword().toCharArray());   //证书密码 changgou
//        converter.setKeyPair(keyPair);
//        //配置自定义的CustomUserAuthenticationConverter
//        DefaultAccessTokenConverter accessTokenConverter = (DefaultAccessTokenConverter) converter.getAccessTokenConverter();
//        accessTokenConverter.setUserTokenConverter(customUserAuthenticationConverter);
//        return converter;
//    }
//
//
//
//
//
//
//
//    /**
//     * <p>注意，自定义TokenServices的时候，需要设置@Primary，否则报错，</p>
//     *
//     * @return
//     */
//    @Primary
//    @Bean
//    public DefaultTokenServices defaultTokenServices() {
//        DefaultTokenServices tokenServices = new DefaultTokenServices();
//        tokenServices.setTokenStore(tokenStore);
//        tokenServices.setSupportRefreshToken(true);
////        tokenServices.setClientDetailsService(customClientDetailsService);
//        // token有效期自定义设置，90天
//        tokenServices.setAccessTokenValiditySeconds(60 * 60 );
//        // refresh_token 90天
//        tokenServices.setRefreshTokenValiditySeconds(60 * 60);
//        return tokenServices;
//    }
}

