package com.github.itdachen.auth.jwts.crypto;

import com.github.itdachen.auth.jwts.core.IJwtsInfo;
import com.github.itdachen.auth.jwts.core.SecretKeyConfiguration;
import com.github.itdachen.auth.jwts.crypto.factory.JwtsTokenFactory;
import com.github.itdachen.auth.jwts.core.properties.JwtsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Description:
 * Created by 王大宸 on 2023/04/30 15:14
 * Created with IntelliJ IDEA.
 */
@Component
public class JwtsTokenHelper {

    private final JwtsTokenFactory tokenFactory;
    private final JwtsProperties jwtsProperties;

    public JwtsTokenHelper(JwtsTokenFactory tokenFactory,
                           JwtsProperties jwtsProperties) {
        this.tokenFactory = tokenFactory;
        this.jwtsProperties = jwtsProperties;
    }

    @Autowired
    private SecretKeyConfiguration secretKeyConfiguration;

    /***
     * 加密返回 token
     *
     * @author 王大宸
     * @date 2023/4/12 22:53
     * @param jwtInfo  jwtInfo
     * @return java.lang.String
     */
    public String createToken(IJwtsInfo jwtInfo) throws Exception {
        return tokenFactory.build().createToken(jwtInfo,
                secretKeyConfiguration.getUserPriKey(),
                jwtsProperties.getToken().getExpires());
    }

}
