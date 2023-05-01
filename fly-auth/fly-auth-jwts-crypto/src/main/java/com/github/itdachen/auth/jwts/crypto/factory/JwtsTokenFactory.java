package com.github.itdachen.auth.jwts.crypto.factory;

import com.github.itdachen.auth.jwts.core.enums.JwtTokenEnumType;
import com.github.itdachen.auth.jwts.crypto.factory.handler.AbstractJwtTokenHandler;
import com.github.itdachen.auth.jwts.crypto.factory.handler.EcdsaJwtTokenHandler;
import com.github.itdachen.auth.jwts.crypto.factory.handler.HmacJwtTokenHandler;
import com.github.itdachen.auth.jwts.crypto.factory.handler.RsaJwtTokenHandler;
import com.github.itdachen.auth.jwts.core.properties.JwtsProperties;
import org.springframework.stereotype.Component;

/**
 * Description: token 生成方式
 * Created by 王大宸 on 2023/04/30 15:07
 * Created with IntelliJ IDEA.
 */
@Component
public class JwtsTokenFactory {


    private final JwtsProperties jwtProperties;

    public JwtsTokenFactory(JwtsProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }


    public AbstractJwtTokenHandler build() {
        if (JwtTokenEnumType.ECDSA.equals(jwtProperties.getType())) {
            return new EcdsaJwtTokenHandler(jwtProperties);
        } else if (JwtTokenEnumType.HMAC.equals(jwtProperties.getType())) {
            return new HmacJwtTokenHandler(jwtProperties);
        } else if (JwtTokenEnumType.RSA.equals(jwtProperties.getType())) {
            return new RsaJwtTokenHandler(jwtProperties);
        }
        return new HmacJwtTokenHandler(jwtProperties);
    }

}
