package com.atguigu.cloud.wxpay.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author zwq
 * @version 2.0
 * @date 2020-4-11 10:29:06
 */
//@Component
//@PropertySource({"classpath:weixin.properties"})
//@ConfigurationProperties(prefix="wx")
//@Data


@Component
@Getter
@Setter
@PropertySource("classpath:wxpay.properties")
@ConfigurationProperties(prefix = "wxpay",ignoreUnknownFields = false)
public class WxGroupConfig {
    /** 微信支付 小程序APP_ID*/

//    @Value("${wxpay.applet_appId}")
    private String appletAppId;
    /** 微信支付 APP的APP_ID*/
//    @Value("${wxpay.app_id}")
    private String appId;
    /** 微信支付 key*/
//    @Value("${wxpay.wx_pay_key}")
    private String wxPayKey;
    /** 微信支付 商户id*/
//    @Value("${wxpay.wx_pay_mch_id}")
    private String wxPayMchId;
    /** 微信支付 支付回调地址*/
//    @Value("${wxpay.callback}")
    private String callBack;
    /** 微信支付 密钥*/
//    @Value("${wxpay.app_secret}")
    private String appSecret;
}
