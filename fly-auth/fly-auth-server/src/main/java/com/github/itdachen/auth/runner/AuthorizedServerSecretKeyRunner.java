package com.github.itdachen.auth.runner;

import com.github.itdachen.framework.cloud.jwt.crypto.AuthTokenSecretKey;
import com.github.itdachen.framework.cloud.jwt.crypto.SecretKeyHelper;
import com.github.itdachen.framework.cloud.jwt.crypto.constants.JwtRedisKeyConstants;
import com.github.itdachen.framework.cloud.jwt.crypto.key.JwtSecretKey;
import com.github.itdachen.framework.cloud.jwt.parse.AuthClientTokenSecretKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Description: 鉴权中心 私钥/公钥 初始化
 * Created by 王大宸 on 2023/04/30 15:50
 * Created with IntelliJ IDEA.
 */
@Component
public class AuthorizedServerSecretKeyRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(AuthorizedServerSecretKeyRunner.class);

    @Autowired
    private AuthTokenSecretKey authTokenSecretKey;
    @Autowired
    private AuthClientTokenSecretKey authClientTokenSecretKey;

    private final RedisTemplate<String, String> redisTemplate;
    private final SecretKeyHelper secretKeyHelper;

    public AuthorizedServerSecretKeyRunner(RedisTemplate<String, String> redisTemplate,
                                           SecretKeyHelper secretKeyHelper) {
        this.redisTemplate = redisTemplate;
        this.secretKeyHelper = secretKeyHelper;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("初始化加载用户 secret key ...");
        String publicKey = "";
        if (Boolean.TRUE.equals(redisTemplate.hasKey(JwtRedisKeyConstants.USER_PRI_KEY))
                && Boolean.TRUE.equals(redisTemplate.hasKey(JwtRedisKeyConstants.USER_PUB_KEY))) {

            String privateKey = redisTemplate.opsForValue().get(JwtRedisKeyConstants.USER_PRI_KEY);
            publicKey = redisTemplate.opsForValue().get(JwtRedisKeyConstants.USER_PUB_KEY);

            authTokenSecretKey.setUserPriKey(privateKey);
            authTokenSecretKey.setUserPubKey(publicKey);
        } else {
            JwtSecretKey jwtSecretKey = secretKeyHelper.secretKey();
            publicKey = jwtSecretKey.getPublicKey();

            redisTemplate.opsForValue().set(JwtRedisKeyConstants.USER_PRI_KEY, jwtSecretKey.getPrivateKey());
            redisTemplate.opsForValue().set(JwtRedisKeyConstants.USER_PUB_KEY, jwtSecretKey.getPublicKey());

            authTokenSecretKey.setUserPriKey(jwtSecretKey.getPrivateKey());
            authTokenSecretKey.setUserPubKey(jwtSecretKey.getPublicKey());
        }

        /* 客户 token 解析秘钥 */
        authClientTokenSecretKey.setTokenPublicKey(publicKey);
        logger.info("用户 secret key 初始化完成 ...");
    }

}
