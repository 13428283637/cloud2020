package com.atguigu.cloud.wxpay.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author zwq
 * @version 2.0
 * @date 2020-4-13 11:21:59
 */
@Getter
@Setter
public class WxPayBo {
    private BigDecimal payAmount; // 支付金额
    private String openId;//顾客唯一标识
    private String deviceCode; //设备号
    private String leaseTerm;  //租期
    private String userId;    //用户ID
    private String tradeType; //支付平台
    private String priceGrand; //档次
    private String payChannel; //支付方式 0.微信，1.支付宝

}
