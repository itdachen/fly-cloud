package com.github.itdachen.auth.jwts.core.enums;

/**
 * Description: 生成 token 类型
 * Created by 王大宸 on 2023/04/30 14:47
 * Created with IntelliJ IDEA.
 */
public enum JwtTokenEnumType {

    ECDSA,

    /**
     * 适用于单体, 只需要一个 key
     */
    HMAC,

    RSA,

}
