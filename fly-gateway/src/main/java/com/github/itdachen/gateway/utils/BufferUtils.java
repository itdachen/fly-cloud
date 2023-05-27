package com.github.itdachen.gateway.utils;

import org.apache.commons.codec.Charsets;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;

/**
 * Description:
 * Created by 王大宸 on 2023-05-26 15:59
 * Created with IntelliJ IDEA.
 */
public class BufferUtils {

    public static String dataBufferToString(DataBuffer dataBuffer) {
        byte[] content = new byte[dataBuffer.readableByteCount()];
        dataBuffer.read(content);
        DataBufferUtils.release(dataBuffer);
        return new String(content, Charsets.UTF_8);
    }

}
