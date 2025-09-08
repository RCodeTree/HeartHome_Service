package com.rong.heartserver.Interceptors;

import com.rong.heartcommon.Result.Result;
import com.rong.heartcommon.Utils.JsonUtils;
import com.rong.heartcommon.Utils.JwtUtils;
import com.rong.heartserver.Config.JwtUtilsYml;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.crypto.SecretKey;

/**
 * 定义用户Token令牌验证拦截器
 */
@Component
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtilsYml jwtUtilsYml;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Interceptor---拦截到请求：{}", request.getRequestURL().toString());

        // 安全获取请求头
        String authHeader = request.getHeader(jwtUtilsYml.getHeader());

        // 验证请求头格式
        if (authHeader == null || !authHeader.startsWith("Bearer ") || authHeader.length() <= 7) {
            log.warn("Interceptor---无效的Authorization头：{}", authHeader);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JsonUtils.toJson(Result.error(401, "Token令牌格式错误")));
            return false;
        }

        // 提取token
        String jwtToken = authHeader.substring(7);
        log.info("Interceptor---请求中的jwtToken：{}", jwtToken);

        // 验证token是否为空
        if (jwtToken.trim().isEmpty()) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JsonUtils.toJson(Result.error(401, "Token令牌不存在")));
            return false;
        }

        // 解析token令牌
        try {
            log.info("Interceptor---解析token令牌：{}", jwtToken);
            // 生成密钥
            SecretKey SECRET_KEY = JwtUtils.generateKey(jwtUtilsYml.getSecret(), jwtUtilsYml.getAlgorithm());
            JwtUtils.parseToken(jwtToken, SECRET_KEY);
        } catch (Exception e) { // 在解析失败的时候会抛出异常
            // 抛出异常，返回错误结果
            log.error("Interceptor---Token校验失败：{}", e.getMessage());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JsonUtils.toJson(Result.<Void>error(401, "Token校验失败或过期")));
            return false;
        }

        // 令牌合法，放行
        log.info("Interceptor---token令牌解析成功");
        return true;
    }
}
