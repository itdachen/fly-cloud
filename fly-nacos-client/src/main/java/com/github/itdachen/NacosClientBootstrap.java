package com.github.itdachen;

import com.github.itdachen.framework.boot.runner.SpringBootBootstrap;
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
        SpringBootBootstrap.run(NacosClientBootstrap.class);
    }

}