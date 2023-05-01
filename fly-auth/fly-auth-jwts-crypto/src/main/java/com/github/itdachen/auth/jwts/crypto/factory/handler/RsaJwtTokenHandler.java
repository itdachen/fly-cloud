package com.github.itdachen.auth.jwts.crypto.factory.handler;

import com.github.itdachen.auth.jwts.core.IJwtsInfo;
import com.github.itdachen.auth.jwts.crypto.key.JwtSecretKey;
import com.github.itdachen.auth.jwts.core.properties.JwtsProperties;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.PrivateKey;

/**
 * Description:
 * Created by 王大宸 on 2023/04/30 15:29
 * Created with IntelliJ IDEA.
 */
public class RsaJwtTokenHandler extends AbstractJwtTokenHandler {
    public static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.PS512;
    public static final String ALGORITHM = "RSA";

    public RsaJwtTokenHandler(JwtsProperties properties) {
        this.properties = properties;
    }

    @Override
    public String createToken(IJwtsInfo jwtInfo, String signingKey, Long expires) throws Exception {
        final PrivateKey privateKey = generalPrivateKey(signingKey);
        return createJwtToken(jwtInfo, privateKey, expires, signatureAlgorithm);
    }


    @Override
    public JwtSecretKey generationKey() {
        return generationSecretKey(signatureAlgorithm);
    }

    @Override
    protected PrivateKey generalPrivateKey(String secretKey) throws Exception {
        return generalPrivateKey(secretKey, ALGORITHM);
    }


}
