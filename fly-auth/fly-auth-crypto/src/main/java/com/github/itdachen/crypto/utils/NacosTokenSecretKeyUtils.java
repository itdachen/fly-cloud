package com.github.itdachen.crypto.utils;

import java.util.Base64;

/**
 * Description: nacos.core.auth.plugin.nacos.token.secret.key 加密
 * 自定义密钥时，推荐将配置项设置为Base64编码的字符串，且原始密钥长度不得低于32字符
 * Created by 王大宸 on 2023-07-06 22:55
 * Created with IntelliJ IDEA.
 */
public class NacosTokenSecretKeyUtils {

    public static void main(String[] args) {
        String str = "FlyCloudNacosSecretKey_05678Auth!@#";
        System.err.println("原始长度: " + str.length());
        String encodedString = Base64.getEncoder().encodeToString(str.getBytes());
        System.err.println("encodedString: " + encodedString);
        System.err.println("encodedString: " + encodedString.length());

        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        String decodedString = new String(decodedBytes);
        System.err.println("decodedString: " + decodedString);

    }

}
