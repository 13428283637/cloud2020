package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @auther zzyy
 * @create 2020-02-17 21:13
 */
@SpringBootApplication
//@EnableEurekaClient
//@EnableDiscoveryClient
//@ComponentScan(basePackages={"com.atguigu.springcloud.api"})//扫描接口
public class SwaggerTest8011
{
    public static void main(String[] args) {
        SpringApplication.run(SwaggerTest8011.class, args);
    }
}
