package com.github.itdachen.auth.jwts.parse.factory;

import com.github.itdachen.auth.jwts.core.enums.JwtTokenEnumType;
import com.github.itdachen.auth.jwts.core.properties.JwtsProperties;
import com.github.itdachen.auth.jwts.parse.factory.handler.AbstractParseJwtsTokenHandler;
import com.github.itdachen.auth.jwts.parse.factory.handler.EcdsaParseJwtsTokenHandler;
import com.github.itdachen.auth.jwts.parse.factory.handler.HmacParseJwtsTokenHandler;
import com.github.itdachen.auth.jwts.parse.factory.handler.RsaParseJwtsTokenHandler;
import org.springframework.stereotype.Component;

/**
 * Description:
 * Created by 王大宸 on 2023/04/30 15:35
 * Created with IntelliJ IDEA.
 */
@Component
public class ParseTokenFactory {

    private final JwtsProperties jwtProperties;

    public ParseTokenFactory(JwtsProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public AbstractParseJwtsTokenHandler build() {
        if (JwtTokenEnumType.ECDSA.equals(jwtProperties.getToken().getType())) {
            return new EcdsaParseJwtsTokenHandler();
        } else if (JwtTokenEnumType.HMAC.equals(jwtProperties.getToken().getType())) {
            return new HmacParseJwtsTokenHandler();
        } else if (JwtTokenEnumType.RSA.equals(jwtProperties.getToken().getType())) {
            return new RsaParseJwtsTokenHandler();
        }
        return new HmacParseJwtsTokenHandler();
    }

}
