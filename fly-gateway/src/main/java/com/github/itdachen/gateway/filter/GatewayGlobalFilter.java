package com.github.itdachen.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Description: 全局过滤器
 * Created by 王大宸 on 2023/05/05 21:41
 * Created with IntelliJ IDEA.
 */
@Configuration
@SuppressWarnings("all")
public class GatewayGlobalFilter implements GlobalFilter {
    private static final Logger logger = LoggerFactory.getLogger(GatewayGlobalFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange webExchange, GatewayFilterChain filterChain) {
        ServerHttpRequest request = webExchange.getRequest();
        String path = request.getURI().getPath();
        logger.info("path ==> " + path);
        return filterChain.filter(webExchange);
    }

}
