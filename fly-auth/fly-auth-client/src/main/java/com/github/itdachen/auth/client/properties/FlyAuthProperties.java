package com.github.itdachen.auth.client.properties;

import com.github.itdachen.auth.client.properties.client.FlyAuthClientProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * Description: 客户端认证配置
 * Created by 王大宸 on 2023/05/01 15:39
 * Created with IntelliJ IDEA.
 */
@ConfigurationProperties(prefix = "auth")
public class FlyAuthProperties {

    /**
     * 认证客户端ID
     */
    private String serviceId = "auth";

    /**
     * 不需要认证的复制
     */
    private List<String> matchers;

    /**
     * 客户端配置
     */
    private FlyAuthClientProperties client = new FlyAuthClientProperties();


    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public List<String> getMatchers() {
        return matchers;
    }

    public void setMatchers(List<String> matchers) {
        this.matchers = matchers;
    }

    public FlyAuthClientProperties getClient() {
        return client;
    }

    public void setClient(FlyAuthClientProperties client) {
        this.client = client;
    }

}
