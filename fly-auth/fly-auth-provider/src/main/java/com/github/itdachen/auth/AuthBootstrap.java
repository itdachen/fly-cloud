package com.github.itdachen.auth;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Description: 认证中心启动接口
 * Created by 王大宸 on 2023/04/30 13:06
 * Created with IntelliJ IDEA.
 */
@EnableDubbo
@RefreshScope // 动态刷新配置
@EnableScheduling // 定时任务
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.github.itdachen"})
@MapperScan(basePackages = "com.github.itdachen.auth.**.mapper")
public class AuthBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(AuthBootstrap.class, args);
    }

}
