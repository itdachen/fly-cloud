package com.github.itdachen.gateway.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 全局异常
 * Created by 王大宸 on 2023-09-03 22:12
 * Created with IntelliJ IDEA.
 */
@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {
    private static final Logger logger = LoggerFactory.getLogger(GlobalErrorAttributes.class);

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Throwable error = super.getError(request);

        logger.error("网关处理异常", error);

        Map<String, Object> map = new HashMap<>();
        map.put("success", false);
        map.put("status", HttpStatus.BAD_REQUEST.value());
        map.put("msg", error.getMessage());
        map.put("data", null);

        return map;

    }

}
