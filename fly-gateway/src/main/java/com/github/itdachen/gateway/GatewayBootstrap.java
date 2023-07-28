package com.github.itdachen.gateway;

import com.github.itdachen.auth.client.EnableAuthClient;
import com.github.itdachen.framework.boot.runner.SpringBootBootstrap;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Description: 网关启动入口类
 * Created by 王大宸 on 2023/02/11 14:20
 * Created with IntelliJ IDEA.
 */
@EnableAsync
@RefreshScope
@EnableScheduling
@EnableAuthClient
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients({"com.github.itdachen"})
@ComponentScan(basePackages = {"com.github.itdachen"})
public class GatewayBootstrap {

    public static void main(String[] args) {
        SpringBootBootstrap.run(GatewayBootstrap.class);
    }

}
