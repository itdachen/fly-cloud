package com.github.itdachen.gateway.filter;

import com.alibaba.fastjson.JSON;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

/**
 * Description:
 * Created by 王大宸 on 2023-05-26 15:57
 * Created with IntelliJ IDEA.
 */
//@Component
//public class CommonLogFilter implements GlobalFilter, Ordered {
//    private static final Logger logger = LoggerFactory.getLogger(CommonLogFilter.class);
//
//
//    //可以输出的 body 格式
//    public static final Set<MediaType> legalLogMediaTypes = Set.of(
//            MediaType.TEXT_XML,
//            MediaType.TEXT_PLAIN,
//            MediaType.APPLICATION_XML,
//            MediaType.APPLICATION_JSON
//    );
//
//    @Autowired
//    private TracedPublisherFactory tracedPublisherFactory;
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        long startTime = System.currentTimeMillis();
//        ServerHttpRequest request = exchange.getRequest();
//        ServerHttpResponse response = exchange.getResponse();
//        //获取用于拆包处理聚合读取请求和响应 body 的 buffer 的 factory
//        DataBufferFactory dataBufferFactory = response.bufferFactory();
//        //请求 http 头
//        HttpHeaders requestHeaders = request.getHeaders();
//        //请求 body 类型
//        MediaType requestContentType = requestHeaders.getContentType();
//        //请求 uri
//        String uri = request.getURI().toString();
//        //请求 http 方法
//        HttpMethod method = request.getMethod();
//        logger.info("{} -> {}: header: {}", method, uri, JSON.toJSONString(requestHeaders));
//        Flux<DataBuffer> dataBufferFlux = tracedPublisherFactory.getTracedFlux(request.getBody(), exchange)
//                //使用 buffer 在这里将所有 body 读取完避免拆包影响
//                .buffer()
//                .map(dataBuffers -> {
//                    //将所有 buffer 粘合在一起
//                    DataBuffer dataBuffer = dataBufferFactory.join(dataBuffers);
//                    //只有在 debug 开启的时候，才会输出 body
//                    if (logger.isDebugEnabled()) {
//                        //只有特定的 body 类型才会输出具体的
//                        if (legalLogMediaTypes.contains(requestContentType)) {
//                            try {
//                                //将 body 转化为 String 进行输出，同时注意，原始的 buffer 需要被释放，因为 body 流已经被读取出来，但是没有地方回收
//                                //参考
//                                String s = BufferUtils.dataBufferToString(dataBuffer);
//                                logger.debug("body: {}", s);
//                                dataBuffer = dataBufferFactory.wrap(s.getBytes());
//                            } catch (Exception e) {
//                                logger.error("error read request body: {}", e.getMessage(), e);
//                            }
//                        } else {
//                            logger.debug("body: {}", request);
//                        }
//                    }
//                    return dataBuffer;
//                });
//        return chain.filter(exchange.mutate().request(new ServerHttpRequestDecorator(request) {
//            @Override
//            public Flux<DataBuffer> getBody() {
//                return dataBufferFlux;
//            }
//        }).response(new ServerHttpResponseDecorator(response) {
//            @Override
//            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
//                HttpHeaders responseHeaders = super.getHeaders();
//                //调用这里的是写响应回客户端的 HttpClientConnect 的回写，已经跳出了 Spring Cloud Sleuth 的链路 Span，所以没有链路追踪信息
//                //但是我们在 CommonTraceFilter 我们将链路信息放入了响应 Header 中，所以这里我们就不用手动增加链路信息了
//                logger.info("response: {} -> {} {} header: {}, time: {}ms", method, uri, getStatusCode(), JSON.toJSONString(responseHeaders), System.currentTimeMillis() - startTime);
//                final MediaType contentType = responseHeaders.getContentType();
//                if (contentType != null && body instanceof Flux && legalLogMediaTypes.contains(contentType) && logger.isDebugEnabled()) {
//                    //有TCP粘包拆包问题，这个body是多次写入的，一次调用拿不到完整的body，所以这里转换成fluxBody利用其中的buffer来接受完整的body
//                    Flux<? extends DataBuffer> fluxBody = tracedPublisherFactory.getTracedFlux(Flux.from(body), exchange);
//                    return super.writeWith(fluxBody.buffer().map(buffers -> {
//                        DataBuffer buffer = dataBufferFactory.join(buffers);
//                        try {
//                            String s = BufferUtils.dataBufferToString(buffer);
//                            logger.debug("response: body: {}", s);
//                            return dataBufferFactory.wrap(s.getBytes());
//                        } catch (Exception e) {
//                            logger.error("error read response body: {}", e.getMessage(), e);
//                        }
//                        return buffer;
//                    }));
//                }
//                // if body is not a flux. never got there.
//                return super.writeWith(body);
//            }
//        }).build());
//    }
//
//    @Override
//    public int getOrder() {
//        //指定顺序，在 CommonTraceFilter（这个Filter是读取链路信息，最好在所有 Filter 之前） 之后
//        return 1;
//    }
//
//}
