package com.github.itdachen.admin;

import com.github.itdachen.auth.client.EnableAuthClient;
import com.github.itdachen.framework.boot.runner.SpringBootBootstrap;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Description: 启动入口
 * Created by 王大宸 on 2023/04/30 13:35
 * Created with IntelliJ IDEA.
 */
@RefreshScope // 动态刷新配置
@EnableScheduling // 定时任务
@EnableAuthClient
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients({"com.github.itdachen"})
@ComponentScan(basePackages = {"com.github.itdachen"})
@MapperScan(basePackages = "com.github.itdachen.admin.**.mapper")
public class AdminBootstrap {

    public static void main(String[] args) {
        SpringBootBootstrap.run(AdminBootstrap.class);
    }

}
