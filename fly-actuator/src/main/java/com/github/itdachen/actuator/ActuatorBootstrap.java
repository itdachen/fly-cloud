package com.github.itdachen.actuator;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Description: 微服务应用监控 启动入口类
 * 访问入口: http://127.0.0.1:8005/actuator
 * Created by 王大宸 on 2023/04/30 13:58
 * Created with IntelliJ IDEA.
 */
@EnableAsync
@RefreshScope
@EnableAdminServer
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.github.itdachen"})
public class ActuatorBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(ActuatorBootstrap.class, args);
    }

}
