package com.github.itdachen.gateway.filter.body;

import com.github.itdachen.gateway.constants.GatewayConstant;
import com.github.itdachen.gateway.service.IgnoreRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Description: 请求体缓存
 * 顶级过滤器用来缓存请求正文
 * Created by 王大宸 on 2023-05-26 15:22
 * Created with IntelliJ IDEA.
 */
@Component
@SuppressWarnings("all")
public class RequestBodyCacheFilter implements GlobalFilter, Ordered {
    private static final Logger logger = LoggerFactory.getLogger(RequestBodyCacheFilter.class);


    @Autowired
    private IgnoreRequest ignoreRequest;

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        /* 登录/注册/GET请求/DELETE请求, 不使用用缓存过滤器 */
        if (HttpMethod.GET.equals(exchange.getRequest().getMethod())
                || HttpMethod.DELETE.equals(exchange.getRequest().getMethod())
                || ignoreRequest.verifyUriMatchers(exchange.getRequest().getURI().getPath())) {
            exchange.getAttributes().remove(GatewayConstant.LOG_ID);
            return chain.filter(exchange);
        }

        return ServerWebExchangeUtils.cacheRequestBody(
                exchange, (request) -> chain.filter(exchange.mutate().request(request).build())
        );


        // DataBufferUtils.join 拿到请求中的数据 --> DataBuffer
//        return DataBufferUtils.join(exchange.getRequest().getBody()).flatMap(dataBuffer -> {
//            // 确保数据缓冲区不被释放, 必须要 DataBufferUtils.retain
//            DataBufferUtils.retain(dataBuffer);
//
//            // defer、just 都是去创建数据源, 得到当前数据的副本
//            Flux<DataBuffer> cachedFlux = Flux.defer(() ->
//                    Flux.just(dataBuffer.slice(0, dataBuffer.readableByteCount())));
//
//            // 重新包装 ServerHttpRequest, 重写 getBody 方法, 能够返回请求数据
//            ServerHttpRequest mutatedRequest = new ServerHttpRequestDecorator(exchange.getRequest()) {
//                @Override
//                public Flux<DataBuffer> getBody() {
//                    return cachedFlux;
//                }
//            };
//            // 将包装之后的 ServerHttpRequest 向下继续传递
//            return chain.filter(exchange.mutate().request(mutatedRequest).build());
//        });


    }


}
