package com.github.itdachen.auth.jwts.core;

import com.github.itdachen.auth.jwts.core.properties.JwtsProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Description:  配置 密钥/公钥
 * Created by 王大宸 on 2023/04/30 15:48
 * Created with IntelliJ IDEA.
 */
@Configuration
@EnableConfigurationProperties(JwtsProperties.class)
public class SecretKeyConfiguration {

    private String userPubKey;

    private String userPriKey;

    private String servicePriKey;

    private String servicePubKey;


    public String getUserPubKey() {
        return userPubKey;
    }

    public void setUserPubKey(String userPubKey) {
        this.userPubKey = userPubKey;
    }

    public String getUserPriKey() {
        return userPriKey;
    }

    public void setUserPriKey(String userPriKey) {
        this.userPriKey = userPriKey;
    }

    public String getServicePriKey() {
        return servicePriKey;
    }

    public void setServicePriKey(String servicePriKey) {
        this.servicePriKey = servicePriKey;
    }

    public String getServicePubKey() {
        return servicePubKey;
    }

    public void setServicePubKey(String servicePubKey) {
        this.servicePubKey = servicePubKey;
    }


}