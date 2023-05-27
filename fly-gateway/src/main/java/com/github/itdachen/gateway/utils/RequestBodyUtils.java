package com.github.itdachen.gateway.utils;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import reactor.core.publisher.Flux;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description: 滤器请求/响应工具类
 * Created by 王大宸 on 2023-05-26 9:44
 * Created with IntelliJ IDEA.
 */
public class RequestBodyUtils {

    /***
     * 获取post请求的body体, 没有1024字节的限制
     *
     * @author 王大宸
     * @date 2023/5/26 10:06
     * @param body body
     * @return java.lang.String
     */
    public static String resolveBodyFromRequest(Flux<DataBuffer> body) {
        AtomicReference<String> bodyRef = new AtomicReference<>();
        /* 缓存读取的request body信息 */
        body.subscribe(dataBuffer -> {
            CharBuffer charBuffer = StandardCharsets.UTF_8.decode(dataBuffer.asByteBuffer());
            /* 释放内存 */
            DataBufferUtils.release(dataBuffer);
            bodyRef.set(charBuffer.toString());
        });
        /* 获取request body */
        return bodyRef.get();

    }

    /***
     * 读取body内容, 存在 1024字节的限制
     *
     * @author 王大宸
     * @date 2023/5/26 10:06
     * @param body body
     * @return java.lang.String
     */
    public static String resolveBodyFromRequest2(Flux<DataBuffer> body) {
        StringBuilder sb = new StringBuilder();

        body.subscribe(buffer -> {
            byte[] bytes = new byte[buffer.readableByteCount()];
            buffer.read(bytes);
            DataBufferUtils.release(buffer);
            String bodyString = new String(bytes, StandardCharsets.UTF_8);
            sb.append(bodyString);
        });
        return formatStr(sb.toString());
    }

    /**
     * 去掉空格,换行和制表符
     *
     * @param str
     * @return
     */
    private static String formatStr(String str) {
        if (str != null && str.length() > 0) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            return m.replaceAll("");
        }
        return str;
    }


}
