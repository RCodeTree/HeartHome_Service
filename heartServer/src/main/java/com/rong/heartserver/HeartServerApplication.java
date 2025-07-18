package com.rong.heartserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "com.rong.*") // 主要负责 Spring 核心组件（Beans）的扫描和自动配置
@ServletComponentScan(basePackages = "com.rong.*") // 主要负责 Servlet、Filter、Listener 等 Web 组件的扫描和自动配置
@EnableTransactionManagement // 开启事务管理
public class HeartServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeartServerApplication.class, args);
    }

}
