package com.github.itdachen.gateway.filter;

import com.github.itdachen.framework.context.constants.UserInfoConstant;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Description: 权限过滤器
 * Created by 王大宸 on 2023-09-03 22:05
 * Created with IntelliJ IDEA.
 */
@Component
public class AuthorizationPermissionFilter implements GlobalFilter, Ordered {
    private static final Logger logger = LoggerFactory.getLogger(AuthorizationPermissionFilter.class);


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {


//        String token = exchange.getRequest().getHeaders().getFirst(UserInfoConstant.HEADER_AUTHORIZATION);
//        if (StringUtils.isBlank(token)) {
//            logger.error("网关请求认证失败");
//            throw new RuntimeException("非法请求");
//        }

        // TODO 权限校验


        /* 添加认证成功的请求头 */
        exchange.mutate().request((r ->
                r.header(UserInfoConstant.VERIFIED_TICKET, UserInfoConstant.VERIFIED_TICKET_VALUE))
        ).build();

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

}
