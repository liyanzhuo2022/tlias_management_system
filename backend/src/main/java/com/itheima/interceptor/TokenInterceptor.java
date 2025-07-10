package com.itheima.interceptor;

import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();

        if(requestURI.contains("/login")) {
            log.info("登录请求，放行");
            return true;
        }

        String token = request.getHeader("token");
        if (token == null || token. isEmpty()){
            log.info("令牌为空，响应401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        try {
            Claims claims = JwtUtils.parseJWT(token);
            Integer userId = (Integer) claims.get("id"); // ⚠ 确保字段名和生成 token 时一致
            log.info("已解析用户ID：{}", userId);
            com.itheima.utils.CurrentHolder.setCurrentId(userId); // ✅ 很关键
        } catch (Exception e) {
            log.info("令牌非法，响应401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }


        log.info("令牌合法，放行");
        return true;
    }
}
