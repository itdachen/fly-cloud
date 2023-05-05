package com.github.itdachen.auth.jwts.core.properties.token;

import com.github.itdachen.auth.jwts.core.enums.JwtTokenEnumType;

/**
 * Description:
 * Created by 王大宸 on 2023/05/05 16:03
 * Created with IntelliJ IDEA.
 */
public class JwtsTokenProperties {

    /**
     * 生成 token 方式, 默认使用 RSA, 私钥加密, 公钥解密
     */
    private JwtTokenEnumType type = JwtTokenEnumType.HMAC;

    /**
     * 签发者
     */
    private String issuer = "com.github.itdachen";

    /**
     * 有效时间, 定义默认有效期为 300 分钟 单位：分钟
     */
    private long expires = 18000;

    public JwtTokenEnumType getType() {
        return type;
    }

    public void setType(JwtTokenEnumType type) {
        this.type = type;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public long getExpires() {
        return expires * 1000;
    }

    public void setExpires(long expires) {
        this.expires = expires;
    }
}
