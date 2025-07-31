package com.rong.heartserver.Config;

import com.rong.heartserver.Interceptors.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class WebInterceptorConfig implements WebMvcConfigurer {
    // 不需要拦截的请求URL
    private final static List<String> EXCLUDE_PATH = Arrays.asList(
            "/user/login/signin", "/user/login/signup"
    );

    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册用户Token令牌验证拦截器
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(EXCLUDE_PATH);

    }
}
