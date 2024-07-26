package com.github.itdachen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Description:
 * Created by 王大宸 on 2023-08-03 10:08
 * Created with IntelliJ IDEA.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosClientBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(NacosClientBootstrap.class, args);
    }

}