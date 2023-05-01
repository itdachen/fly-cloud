package com.github.itdachen.auth.jwts.parse.factory.handler;

import com.github.itdachen.auth.jwts.core.IJwtsInfo;

import java.security.PublicKey;

/**
 * Description:
 * Created by 王大宸 on 2023/04/30 15:42
 * Created with IntelliJ IDEA.
 */
public class EcdsaParseJwtsTokenHandler extends AbstractParseJwtsTokenHandler{
    public static final String ALGORITHM = "EC";

    @Override
    public IJwtsInfo parseToken(String token, String signingKey) throws Exception {
        PublicKey publicKey = generalPublicKey(signingKey);
        return parseIJWTInfoToken(token, publicKey);
    }

    @Override
    protected PublicKey generalPublicKey(String secretKey) throws Exception {
        return generalPublicKey(secretKey, ALGORITHM);
    }
}
