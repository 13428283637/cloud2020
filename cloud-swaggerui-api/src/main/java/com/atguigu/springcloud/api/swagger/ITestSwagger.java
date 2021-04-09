package com.atguigu.springcloud.api.swagger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

@Api(value = "ITestSwagger",description = "ITestSwagger测试")
public interface ITestSwagger {
    @ApiOperation("ITestSwagger")
    @GetMapping("/test")
    public String test();
}
