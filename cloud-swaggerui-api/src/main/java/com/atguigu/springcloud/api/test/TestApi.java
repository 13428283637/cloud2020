package com.atguigu.springcloud.api.test;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;

@Api(value = "TestApi",description = "swagger-ui测试")
public interface TestApi {

    @ApiOperation("testApi")
    @GetMapping("/test")
    public String test();

    @ApiOperation("testApi1")
    @GetMapping("/test1/{id}")
    public String test1(@ApiParam(name = "参数Id") String id);

}
