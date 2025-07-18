package com.rong.heartserver.Config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * JWT配置类 --- 使用yml配置文件
 */

@Data
@Component // 将该类注入到Spring容器中
@ConfigurationProperties(prefix = "jwt") // 读取配置文件中的jwt配置
public class JwtUtilsYml {
    private String secret; // 密钥
    private String algorithm; // 算法
    private Long expire; // 过期时间
    private String header; // 头部
}
