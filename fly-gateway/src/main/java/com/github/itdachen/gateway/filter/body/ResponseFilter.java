package com.github.itdachen.gateway.filter.body;

import com.github.itdachen.framework.core.utils.StringUtils;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.NettyWriteResponseFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.netty.handler.codec.http.HttpHeaderValues.APPLICATION_JSON;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.ORIGINAL_RESPONSE_CONTENT_TYPE_ATTR;

/**
 * Description:
 * Created by 王大宸 on 2023-05-26 16:44
 * Created with IntelliJ IDEA.
 */
//public class ResponseFilter  implements GlobalFilter, Ordered {
//    private static final Logger logger = LoggerFactory.getLogger(ResponseFilter.class);
//
//    /**
//     * 1.封装HttpResponse，读取Respond 内容；
//     * 2.记录操作日志：请求信息、响应状态、响应信息等；
//     */
//
//    @Override
//    public int getOrder() {
//        // before write response.
//        return NettyWriteResponseFilter.WRITE_RESPONSE_FILTER_ORDER - 1;
//    }
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        // Response
//        ServerHttpResponse mutatedResponse = responseDecorator(exchange);
//
//        return chain.filter(exchange.mutate().response(mutatedResponse).build());
//    }
//
//    public ServerHttpResponse responseDecorator(ServerWebExchange exchange) {
//        ServerHttpResponse originalResponse = exchange.getResponse();
//        DataBufferFactory bufferFactory = originalResponse.bufferFactory();
//        return new ServerHttpResponseDecorator(originalResponse) {
//            @Override
//            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
//
//                // 读取 Response 的响应流中的信息后，再写回去
//                if (body instanceof Flux) {
//                    // 获取ContentType，判断是否返回JSON格式数据
//                    String originalResponseContentType = exchange.getAttribute(ORIGINAL_RESPONSE_CONTENT_TYPE_ATTR);
//                    if (StringUtils.isNotBlank(originalResponseContentType) && originalResponseContentType.contains(APPLICATION_JSON)) {
//                        Flux<? extends DataBuffer> fluxBody = Flux.from(body);
//
//                        return super.writeWith(fluxBody.buffer().map(dataBuffers -> {
//                            // Response Content.
//                            // String responseContent = resolveContentFromResponse(dataBuffers, bufferFactory);
//                            DataBuffer join = bufferFactory.join(dataBuffers);
//                            byte[] content = new byte[join.readableByteCount()];
//                            join.read(content);
//                            DataBufferUtils.release(join);
//
//                            String responseContent = formatStr(new String(content, StandardCharsets.UTF_8));
//
//
//                            // 添加操作日志.
//                            ServerHttpRequest request = exchange.getRequest();
//                            String apiPath = request.getPath().toString();
//                            HttpHeaders headers = request.getHeaders();
//                            String requestBody = resolveBodyFromRequest(request.getBody());
//
//                            appendSystemLog(apiPath, headers, requestBody, responseContent, getStatusCode());
//
//                            // 写回Response Content.
//                            // String 转为 byte[]后再写入DataBuffer,可能出现中文全部为?的情况
//                            // byte[] uppedContent = new String(responseContent.getBytes(), StandardCharsets.UTF_8).getBytes();
//                            // originalResponse.getHeaders().setContentLength(uppedContent.length);
//                            return bufferFactory.wrap(content);
//                        }));
//                    }
//                }
//                return super.writeWith(body);
//            }
//
//            @Override
//            public Mono<Void> writeAndFlushWith(Publisher<? extends Publisher<? extends DataBuffer>> body) {
//                return writeWith(Flux.from(body).flatMapSequential(p -> p));
//            }
//        };
//    }
//
//    /**
//     * 解析 RequestBody
//     */
//    public String resolveBodyFromRequest(Flux<DataBuffer> body) {
//        AtomicReference<String> bodyRef = new AtomicReference<>();
//        // 缓存读取的request body信息
//        body.subscribe(dataBuffer -> {
//            CharBuffer charBuffer = StandardCharsets.UTF_8.decode(dataBuffer.asByteBuffer());
//            DataBufferUtils.release(dataBuffer);
//            bodyRef.set(charBuffer.toString());
//        });
//        return formatStr(bodyRef.get());
//    }
//
//    /**
//     * 解析 ResponseBody
//     */
//    public String resolveContentFromResponse(List<? extends DataBuffer> dataBuffers, DataBufferFactory bufferFactory) {
//        //（返回数据内如果字符串过大，默认会切割）解决返回体分段传输
//        // 遍历 List时，拼接字符串会遇到汉字乱码的现象。应全部读取后再转成字符串。
//        DataBuffer join = bufferFactory.join(dataBuffers);
//        byte[] content = new byte[join.readableByteCount()];
//        join.read(content);
//        DataBufferUtils.release(join);
//
//        return formatStr(new String(content, StandardCharsets.UTF_8));
//    }
//
//
//    /**
//     * 去掉空格,换行和制表符
//     */
//    private String formatStr(String str){
//        if (str != null && str.length() > 0) {
//            Pattern p = Pattern.compile("\t|\r|\n");
//            Matcher m = p.matcher(str);
//            return m.replaceAll("");
//        }
//        return str;
//    }
//
//    /**
//     * 记录操作日志
//     */
//    public void appendSystemLog(String apiPath,
//                                HttpHeaders headers,
//                                String requestBody,
//                                String responseBody,
//                                HttpStatusCode status) {
//        int value = status.value();
//        try {
//            // todo 记录日志
//            // service
//        } catch (Exception e) {
//            logger.error("==== Append SystemLog error.", e);
//        }
//    }
//
//}
