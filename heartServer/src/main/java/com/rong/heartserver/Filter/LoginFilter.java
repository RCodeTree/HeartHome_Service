package com.rong.heartserver.Filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import javax.crypto.SecretKey;

import com.rong.heartcommon.Utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.rong.heartcommon.Result.Result;
import com.rong.heartcommon.Utils.JwtUtils;
import com.rong.heartserver.Config.JwtUtilsYml;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 登录过滤器
 */
@WebFilter(urlPatterns = "/*")
@Slf4j
public class LoginFilter implements Filter {
    // URL白名单
    private static final List<String> EXCLUDE_URLS = Arrays.asList(
            "/user/login/signin", // 登录URL
            "/user/login/signup" // 注册URL
    );

    @Autowired
    private JwtUtilsYml jwtUtilsYml;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest; // 强转成HttpServletRequest
        HttpServletResponse response = (HttpServletResponse) servletResponse; // 强转成HttpServletResponse

        // 获取请求url
        log.info("Filter---请求url是：" + request.getRequestURI());
        String url = request.getRequestURI();

        // 使用Stream判断请求URL是否在白名单中 包含白名单中的url，直接放行
        if (EXCLUDE_URLS.stream().anyMatch(url::contains)) {
            log.info("Filter---请求在白名单中，直接放行：{}", url);
            filterChain.doFilter(request, response);
            return;
        }

        // 获取请求头中的token
        String token = request.getHeader(jwtUtilsYml.getHeader());
        log.info("token是：" + token);

        // 判断token令牌是否存在，如果不存在，返回错误结果(未登录)
        if (token == null || token.isEmpty()) { // 修改这里，先判断是否为null
            response.getWriter().write(JsonUtils.toJson(Result.error(401, "未登录")));
            return;
        }

        // 解析token令牌
        try {
            SecretKey SECRET_KEY = JwtUtils.generateKey(jwtUtilsYml.getSecret(), jwtUtilsYml.getAlgorithm());
            JwtUtils.parseToken(token, SECRET_KEY);
        } catch (Exception e) { // 在解析失败的时候会抛出异常
            // 抛出异常，返回错误结果(未登录)
            response.getWriter().write(JsonUtils.toJson(Result.error(401, "未登录")));
            return;
        }

        // 令牌合法，放行
        filterChain.doFilter(request, response);
    }

}
