package com.atguigu.cloud.handler;

import com.baomidou.mybatisplus.extension.api.R;
import com.min.security.security.config.JWTConfig;
import com.min.security.security.utils.JWTTokenUtils;
import com.min.security.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 注销成功处理类
 */

@Slf4j
@Component
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) {
        // 添加到黑名单
        String token = request.getHeader(JWTConfig.tokenHeader);
        JWTTokenUtils.addBlackList(token);

        log.info("用户{}登出成功，Token信息已保存到Redis的黑名单中", JWTTokenUtils.getUserNameByToken(token));

        SecurityContextHolder.clearContext();
        ResponseUtils.responseJson(response, ResponseUtils.response(200, "退出成功", null));
    }
}
