package com.atguigu.cloud.wxpay.service;


//import com.dlc.common.utils.R;
import com.atguigu.cloud.wxpay.entity.WxPayBo;
import com.atguigu.cloud.wxpay.entity.WxPayVo;

import java.util.Map;

/**
 * @author zwq
 * @version 2.0
 * @date 2020-4-13 11:21:11
 */
public interface WxPayService {
    /**
     * 微信 小程序 支付实现
     */
    WxPayVo wxAppletPay(WxPayBo wxPayBo);

    /**
     *  微信 APP支付实现
     * @param wxPayBo
     * @return
     */
    Object wxAppPay(WxPayBo wxPayBo);

    String notifyApp(String resXml);

    boolean refund(String outTradeNo, String outRefundNo,
                   double totalFee, double refundFee, String refundDesc);

    public Map<String, String> createNative(WxPayBo wxPayBo);
}
