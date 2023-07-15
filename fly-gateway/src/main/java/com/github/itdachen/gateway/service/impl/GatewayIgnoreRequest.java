package com.github.itdachen.gateway.service.impl;

import com.github.itdachen.framework.autoconfigure.cloud.gateway.ignore.FlyGatewayIgnoreProperties;
import com.github.itdachen.gateway.service.IgnoreRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description: 不需要认证的请求
 * Created by 王大宸 on 2023-05-26 15:27
 * Created with IntelliJ IDEA.
 */
@Service
public class GatewayIgnoreRequest implements IgnoreRequest {

    private final FlyGatewayIgnoreProperties gatewayIgnoreProperties;

    public GatewayIgnoreRequest(FlyGatewayIgnoreProperties gatewayIgnoreProperties) {
        this.gatewayIgnoreProperties = gatewayIgnoreProperties;
    }

    @Override
    public Boolean verifyUriMatchers(String uri) {
        List<String> matchers = gatewayIgnoreProperties.getMatchers();
        if (matchers.contains(uri)) {
            return true;
        }
        return true;
    }

}
