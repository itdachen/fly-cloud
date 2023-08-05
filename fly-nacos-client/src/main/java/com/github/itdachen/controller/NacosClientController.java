package com.github.itdachen.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description:
 * Created by 王大宸 on 2023-08-05 20:58
 * Created with IntelliJ IDEA.
 */
@RestController
@RequestMapping("/nacos-client")
public class NacosClientController {
    private static final Logger logger = LoggerFactory.getLogger(NacosClientController.class);

    private final DiscoveryClient discoveryClient;

    public NacosClientController(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    /***
     * 根据 service id 获取服务所有的实例信息
     *
     * @author 王大宸
     * @date 2023/8/5 21:08
     * @param serviceId serviceId
     * @return java.util.List<org.springframework.cloud.client.ServiceInstance>
     */
    @GetMapping("/service-instance")
    public List<ServiceInstance> nacosClientInfo(@RequestParam(defaultValue = "nacos-client") String serviceId) {
        logger.info("request nacos client to get service instance info: [{}]", serviceId);
        return discoveryClient.getInstances(serviceId);
    }

}
