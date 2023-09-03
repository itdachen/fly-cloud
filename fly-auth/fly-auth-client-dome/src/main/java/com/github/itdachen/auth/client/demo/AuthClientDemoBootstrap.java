package com.github.itdachen.auth.client.demo;

import com.github.itdachen.auth.client.EnableAuthClient;
import com.github.itdachen.framework.boot.runner.SpringBootBootstrap;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * Description:
 * Created by 王大宸 on 2023-09-03 18:09
 * Created with IntelliJ IDEA.
 */
@EnableAuthClient  // 启动认证客户端注解
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients({"com.github.itdachen"})
@ComponentScan(basePackages = {"com.github.itdachen"})
public class AuthClientDemoBootstrap {

    public static void main(String[] args) {
        SpringBootBootstrap.run(AuthClientDemoBootstrap.class);
    }

}
