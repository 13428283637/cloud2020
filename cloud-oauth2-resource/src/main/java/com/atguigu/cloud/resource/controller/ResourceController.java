package com.atguigu.cloud.resource.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class ResourceController {


    @PreAuthorize("hasAnyAuthority('resource')")
    @RequestMapping("resource")
    public String resource(){
        return "resource";
    }


    @RequestMapping("/admin")
//    @PreAuthorize("hasAnyAuthority('admin')")
    public Object admin() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


    @GetMapping("/info")
    public Object info(Authentication authentication) {
        Object oAuth2User = authentication.getPrincipal();
//        UserInfoDTO userInfoDTO = new UserInfoDTO();
//        userInfoDTO.setId((Integer) oAuth2User.getAttributes().get("id"));
//        userInfoDTO.setLogin((String) oAuth2User.getAttributes().get("login"));
//        userInfoDTO.setAvatar_url((String) oAuth2User.getAttributes().get("avatar_url"));

        return authentication;
    }

    @GetMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }

}
