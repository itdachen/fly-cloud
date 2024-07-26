package com.github.itdachen.gateway.routes;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.github.itdachen.boot.autoconfigure.cloud.gateway.routes.GatewayRoutesProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * 将 ConfigService 交由 spring 管理
 *
 * @author 王大宸
 * @date 2024-07-25 17:06
 */
@Configuration
public class GatewayConfigServiceConfig {

    private final GatewayRoutesProperties gatewayRoutesProperties;

    public GatewayConfigServiceConfig(GatewayRoutesProperties gatewayRoutesProperties) {
        this.gatewayRoutesProperties = gatewayRoutesProperties;
    }


    /***
     * 配置信息
     *
     * @author 王大宸
     * @date 2024/7/25 17:07
     * @return com.alibaba.nacos.api.config.ConfigService
     */
    @Bean
    public ConfigService configService() throws NacosException {
        Properties properties = new Properties();
        /*  配置 Nacos 地址和命名空间 */
        properties.setProperty(PropertyKeyConst.SERVER_ADDR, gatewayRoutesProperties.getServerAddr());
        properties.setProperty(PropertyKeyConst.NAMESPACE, gatewayRoutesProperties.getNamespace());

        /* 新版本, 需要添加 Nacos 账号密码 */
        properties.setProperty(PropertyKeyConst.USERNAME, gatewayRoutesProperties.getUsername());
        properties.setProperty(PropertyKeyConst.PASSWORD, gatewayRoutesProperties.getPassword());


      //  properties.setProperty(PropertyKeyConst.CLUSTER_NAME, nacosConfigProperties.getClusterName());

        return NacosFactory.createConfigService(properties);

    }

}
