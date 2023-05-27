package com.github.itdachen.gateway.service.impl;

import com.github.itdachen.gateway.service.IgnoreRequest;
import org.springframework.stereotype.Service;

/**
 * Description: 不需要认证的请求
 * Created by 王大宸 on 2023-05-26 15:27
 * Created with IntelliJ IDEA.
 */
@Service
public class GatewayIgnoreRequest implements IgnoreRequest {

    @Override
    public Boolean verifyUriMatchers(String uri) {
        return null;

    }
}
