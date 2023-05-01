package com.github.itdachen.auth.jwts.crypto;

import com.github.itdachen.auth.jwts.core.IJwtsInfo;
import com.github.itdachen.auth.jwts.crypto.factory.JwtsTokenFactory;
import com.github.itdachen.auth.jwts.core.properties.JwtsProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Description:
 * Created by 王大宸 on 2023/04/30 15:14
 * Created with IntelliJ IDEA.
 */
@Component
@EnableConfigurationProperties(JwtsProperties.class)
public class JwtsTokenHelper {

    private final JwtsTokenFactory tokenFactory;

    public JwtsTokenHelper(JwtsTokenFactory tokenFactory) {
        this.tokenFactory = tokenFactory;
    }

    /***
     * 加密返回 token
     *
     * @author 王大宸
     * @date 2023/4/12 22:53
     * @param jwtInfo    jwtInfo
     * @param signingKey 加密 key
     * @param expires    有效时间, 单位: 毫秒
     * @return java.lang.String
     */
    public String createToken(IJwtsInfo jwtInfo,
                              String signingKey,
                              Long expires) throws Exception {
        return tokenFactory.build().createToken(jwtInfo, signingKey, expires);
    }

}
