package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.api.swagger.ITestSwagger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestSwagger implements ITestSwagger {

    @Override
    @RequestMapping("test")
    public String test() {
        return "TestSwagger";
    }
}
