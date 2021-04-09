package com.atguigu.cloud.wxpay.controller;

//import com.dlc.common.utils.R;
import com.atguigu.cloud.wxpay.entity.WxPayBo;
import com.atguigu.cloud.wxpay.entity.WxPayVo;
import com.atguigu.cloud.wxpay.service.WxPayService;
import com.atguigu.cloud.wxpay.utils.ResponseDataUtil;
import com.atguigu.cloud.wxpay.utils.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author zwq
 * @version 2.0
 * @date 2020-4-13 14:11:19
 */
@RestController
@RequestMapping("/wx/")
public class WxPayController {
    @Autowired
    private WxPayService wxPayService;
    @RequestMapping("/appletPay1")
    public Object wxPay1(){
        return "wxPay1支付发起失败";
//        return ResponseDataUtil.failure(500,"支付发起失败！");
    }

    @RequestMapping("/appletPay")
    @ResponseBody
    public Object wxPay(WxPayBo wxPayBo){
        WxPayVo wxPayVo = wxPayService.wxAppletPay(wxPayBo);
        if(wxPayVo!=null){
            return "支付发起成功";
//            return ResponseDataUtil.success("支付发起成功！",wxPayVo);
        }
        return "支付发起失败";
//        return ResponseDataUtil.failure(500,"支付发起失败！");
    }

    @RequestMapping("/appWxPay")
    @ResponseBody
    public Object appWxPay( WxPayBo wxPayBo) {
        //回调地址，需要改成自己的
       return wxPayService.wxAppPay(wxPayBo);
    }
    @RequestMapping("/appWxPay1")
    @ResponseBody
    public Object appWxPay1( WxPayBo wxPayBo) {
        //回调地址，需要改成自己的
        return wxPayService.createNative(wxPayBo);
    }
    /**
     *  APP和APPLET的回调接口地址 需要线上或者内网穿透的网络环境下
     */
    @PostMapping("/notifyApp")
    @ResponseBody
    public Object notifyApp(HttpServletRequest request) {

        String resXml = "";
        try {
            InputStream inputStream = request.getInputStream();
            //将InputStream转换成xmlString
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            resXml = sb.toString();
            String result = wxPayService.notifyApp(resXml);
            return WXPayUtil.xmlToMap(result);
//            return ResponseDataUtil.success("SUCCESS", WXPayUtil.xmlToMap(result));
        } catch (Exception e) {
            System.out.println("微信手机支付失败:" + e.getMessage());
            String result = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
            return "fail";
            //            return ResponseDataUtil.failure(500,"FAIL");
        }
    }

    /** 退款*/
    @PostMapping("/refund")
    @ResponseBody
    public Object refund(@RequestParam String no){
        return wxPayService.refund(no,no,0.01,0.01,null);
    }
}
