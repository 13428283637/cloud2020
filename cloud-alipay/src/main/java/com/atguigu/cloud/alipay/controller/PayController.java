package com.atguigu.cloud.alipay.controller;

import com.atguigu.cloud.alipay.service.AlipayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/alipay")
public class PayController {

    @Autowired
    private AlipayService alipayService;

    @GetMapping("/hello")
    public String hello() {
        return "index";
    }
    @GetMapping("/returnUrl")
    public String returnUrl() {
        return "支付成功了";
    }

    @RequestMapping("/notifyApp")
    public String notifyApp(HttpServletRequest request) throws IOException {
        ServletInputStream is = request.getInputStream();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int len = 0;
        while((len=is.read(bytes))!=-1){
            os.write(bytes,0,len);
        }
        os.close();
        is.close();
        System.out.println(os.toString());
        return os.toString();
    }
    /**
     * 跳转到支付界面
     * @return
     * @throws Exception
     */
    @GetMapping("/topay")
    @ResponseBody
    public String pay() throws Exception {
        String form = alipayService.toPay(String.valueOf(new Date().getTime()),
        	0.01, "易购商城"+ UUID.randomUUID(), "订单描述"+ UUID.randomUUID());
        return form;
    }
}
