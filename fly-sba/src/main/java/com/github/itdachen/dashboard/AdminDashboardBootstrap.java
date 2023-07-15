package com.github.itdachen.dashboard;

import com.github.itdachen.framework.boot.runner.SpringBootBootstrap;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Description:
 * Created by 王大宸 on 2023/04/30 13:58
 * Created with IntelliJ IDEA.
 */
@EnableAsync
@RefreshScope
@EnableAdminServer
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.github.itdachen"})
public class AdminDashboardBootstrap {

    public static void main(String[] args) {
        SpringBootBootstrap.run(AdminDashboardBootstrap.class);
    }

}
