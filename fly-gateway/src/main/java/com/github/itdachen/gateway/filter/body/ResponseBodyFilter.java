package com.github.itdachen.gateway.filter.body;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.shaded.com.google.common.base.Joiner;
import com.alibaba.nacos.shaded.com.google.common.base.Throwables;
import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import com.github.itdachen.framework.context.constants.YesOrNotConstant;
import com.github.itdachen.framework.context.entity.ApiLogClient;
import com.github.itdachen.gateway.constants.GatewayConstant;
import com.github.itdachen.gateway.service.ILogService;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * Description: 响应数据
 * Created by 王大宸 on 2023-05-26 14:59
 * Created with IntelliJ IDEA.
 */
@Component
@SuppressWarnings("all")
public class ResponseBodyFilter implements GlobalFilter, Ordered {
    private static final Logger logger = LoggerFactory.getLogger(ResponseBodyFilter.class);


    @Autowired
    private ILogService logService;

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpResponse originalResponse = exchange.getResponse();
        DataBufferFactory bufferFactory = originalResponse.bufferFactory();
        ServerHttpResponseDecorator response = new ServerHttpResponseDecorator(originalResponse) {
            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                // 获取ContentType，判断是否返回JSON格式数据
                String originalResponseContentType = exchange.getAttribute(ServerWebExchangeUtils.ORIGINAL_RESPONSE_CONTENT_TYPE_ATTR);
                if (StringUtils.isNotBlank(originalResponseContentType) && originalResponseContentType.contains("application/json")) {
                    Flux<? extends DataBuffer> fluxBody = Flux.from(body);
                    //（返回数据内如果字符串过大，默认会切割）解决返回体分段传输
                    return super.writeWith(fluxBody.buffer().map(dataBuffers -> {
//                        List<String> list = Lists.newArrayList();
//                        dataBuffers.forEach(dataBuffer -> {
//                            try {
//                                byte[] content = new byte[dataBuffer.readableByteCount()];
//                                dataBuffer.read(content);
//                                // 这是释放掉, 不然会导致内存溢出
//                                DataBufferUtils.release(dataBuffer);
//                                list.add(new String(content, StandardCharsets.UTF_8));
//                            } catch (Exception e) {
//                                logger.info("加载Response字节流异常，失败原因：{}", Throwables.getStackTraceAsString(e));
//                            }
//                        });
//                        String resBody = joiner.join(list);


                        DataBufferFactory dataBufferFactory = new DefaultDataBufferFactory();
                        DataBuffer join = dataBufferFactory.join(dataBuffers);

                        byte[] content = new byte[join.readableByteCount()];
                        join.read(content);

                        /* 释放内存 */
                        DataBufferUtils.release(join);

                        /* 响应数据体 */
                        String resBody = new String(content, StandardCharsets.UTF_8);

                        if (null != exchange.getAttribute(GatewayConstant.LOG_ID)) {
                            resBodyLog(exchange, resBody);
                        }
                        return bufferFactory.wrap(content);
                    }));
                }
                return super.writeWith(body);
            }

            @Override
            public Mono<Void> writeAndFlushWith(Publisher<? extends Publisher<? extends DataBuffer>> body) {
                return writeWith(Flux.from(body).flatMapSequential(p -> p));
            }
        };

        return chain.filter(exchange.mutate().response(response).build());
    }


    private void resBodyLog(ServerWebExchange exchange, String body) {
        JSONObject jsonObject = JSON.parseObject(body);
        Object statusCode = jsonObject.get("status");
        String status = YesOrNotConstant.YES;
        if (200 != Integer.parseInt(String.valueOf(statusCode))) {
            status = YesOrNotConstant.NOT;
        }

        /* 操作日志, 更新返回消息 */
        logService.updateApiLog(
                ApiLogClient.builder()
                        .id(exchange.getAttribute(GatewayConstant.LOG_ID))
                        .jsonResult(body)
                        .msg(String.valueOf(jsonObject.get("msg")))
                        .makeUseStatus(status)
                        .build(),
                ""
        );
    }

}
