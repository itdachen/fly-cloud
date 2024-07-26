package com.github.itdachen.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Description: 网关启动入口类
 * Created by 王大宸 on 2024/02/11 14:20
 * Created with IntelliJ IDEA.
 */
@EnableAsync
@RefreshScope
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.github.itdachen"})
public class GatewayBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(GatewayBootstrap.class, args);
    }

}
