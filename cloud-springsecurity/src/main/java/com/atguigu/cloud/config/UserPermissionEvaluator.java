package com.min.security.security;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.atguigu.cloud.config.CustomUserDetailsService;
import com.min.security.model.SysAuth;
import com.min.security.security.model.SysUserDetails;
import com.min.security.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


/**
 * 用户权限注解处理类
 */
@Component
public class UserPermissionEvaluator implements PermissionEvaluator {

    @Autowired
    private CustomUserDetailsService sysUserService;

    /**
     * 判断是否拥有权限
     *
     * @param authentication 用户身份
     * @param targetUrl      目标路径
     * @param permission     路径权限
     *
     * @return 是否拥有权限
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object targetUrl, Object permission) {
        UserDetails sysUserDetails =  authentication.getPrincipal();

        Set<String> permissions = new HashSet<String>(); // 用户权限

        List<SysAuth> authList = sysUserService.findAuthByUserId(sysUserDetails.getId());
        authList.forEach(auth -> {
            permissions.add(auth.getPermission());
        });

        // 判断是否拥有权限
        if (permissions.contains(permission.toString())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
                                 Object permission) {
        return false;
    }

}
