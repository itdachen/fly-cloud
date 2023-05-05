package com.github.itdachen.auth.config;

import com.github.itdachen.auth.jwts.core.SecretKeyConfiguration;
import com.github.itdachen.auth.jwts.crypto.SecretKeyHelper;
import com.github.itdachen.auth.jwts.crypto.constants.JwtRedisKeyConstants;
import com.github.itdachen.auth.jwts.crypto.key.JwtSecretKey;
import com.github.itdachen.auth.jwts.parse.key.AuthClientTokenSecretKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Description: 鉴权中心私钥/公钥初始化
 * Created by 王大宸 on 2023/04/30 15:50
 * Created with IntelliJ IDEA.
 */
@Component
public class AuthorizedServerSecretKeyRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(AuthorizedServerSecretKeyRunner.class);

    @Autowired
    private SecretKeyConfiguration secretKeyConfiguration;
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

            final String privateKey = redisTemplate.opsForValue().get(JwtRedisKeyConstants.USER_PRI_KEY);
            publicKey = redisTemplate.opsForValue().get(JwtRedisKeyConstants.USER_PUB_KEY);

            secretKeyConfiguration.setUserPriKey(privateKey);
            secretKeyConfiguration.setUserPubKey(publicKey);
        } else {
            final JwtSecretKey jwtSecretKey = secretKeyHelper.secretKey();

            redisTemplate.opsForValue().set(JwtRedisKeyConstants.USER_PRI_KEY, jwtSecretKey.getPrivateKey());
            redisTemplate.opsForValue().set(JwtRedisKeyConstants.USER_PUB_KEY, jwtSecretKey.getPublicKey());

            secretKeyConfiguration.setUserPriKey(jwtSecretKey.getPrivateKey());
            secretKeyConfiguration.setUserPubKey(jwtSecretKey.getPublicKey());

            publicKey = jwtSecretKey.getPublicKey();
        }
        authClientTokenSecretKey.setTokenPublicKey(publicKey);
        logger.info("用户 secret key 初始化完成 ...");
    }

}
