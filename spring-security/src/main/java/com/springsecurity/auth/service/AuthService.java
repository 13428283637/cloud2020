package com.springsecurity.auth.service;

import com.springsecurity.auth.entity.SysUser;
import org.springframework.security.core.userdetails.User;

public interface AuthService {
    SysUser register(SysUser userToAdd);
    String login(String username, String password);
    String refresh(String oldToken);
}