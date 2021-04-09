package com.atguigu.cloud.oauth2.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * 描述
 *
 * @author www.itheima.com
 * @version 1.0
 * @package com.changgou.oauth.controller *
 * @since 1.0
 */
@RestController
@RequestMapping("/oauth")
@Api(value = "ITestSwagger",description = "ITestSwagger测试")
public class LoginRedirectController {

    @RequestMapping("/login")
    public String login(String From, Model model) {
        model.addAttribute("from",From);
        return "login";
    }

    @ApiOperation("test")
    @RequestMapping("/test")
    public String test() {
        return "test";
    }




    @ApiOperation("ITestSwagger")
    @RequestMapping("/admin")
    @PreAuthorize("hasAnyAuthority('admin1')")
    public Object admin() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }




}
