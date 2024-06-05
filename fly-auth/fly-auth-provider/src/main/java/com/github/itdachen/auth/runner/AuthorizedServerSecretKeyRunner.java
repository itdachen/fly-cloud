package com.github.itdachen.auth.runner;

import com.github.itdachen.cloud.jwt.ITokenSecretKeyHandler;
import com.github.itdachen.cloud.jwt.IVerifyTicketPublicKeyHelper;
import com.github.itdachen.cloud.jwt.contents.JwtRedisKeyConstants;
import com.github.itdachen.cloud.jwt.crypto.AuthTokenSecretKey;
import com.github.itdachen.cloud.jwt.parse.TokenPublicKey;
import com.github.itdachen.framework.context.jwt.key.JwtSecretKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.security.PublicKey;

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
    private TokenPublicKey tokenPublicKey;
    @Autowired
    private IVerifyTicketPublicKeyHelper verifyTicketPublicKeyHelper;

    private final RedisTemplate<String, String> redisTemplate;
    private final ITokenSecretKeyHandler secretKeyHelper;

    public AuthorizedServerSecretKeyRunner(RedisTemplate<String, String> redisTemplate,
                                           ITokenSecretKeyHandler secretKeyHelper) {
        this.redisTemplate = redisTemplate;
        this.secretKeyHelper = secretKeyHelper;
    }

    @Override
    public void run(String... args) throws Exception {
        refreshUserSecretKey();
    }

    /***
     * 每一分钟同步一次, 防止其他认证中心初始化加解密信息
     *
     * @author 王大宸
     * @date 2023/12/10 21:00
     * @return void
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void refreshUserSecretKey() throws Exception {
        logger.info("初始化加载用户 secret key ...");
        if (Boolean.TRUE.equals(redisTemplate.hasKey(JwtRedisKeyConstants.USER_PRI_KEY))
                && Boolean.TRUE.equals(redisTemplate.hasKey(JwtRedisKeyConstants.USER_PUB_KEY))
                && Boolean.TRUE.equals(redisTemplate.hasKey(JwtRedisKeyConstants.USER_PRI_KEY_ALG))) {

            String userPriKey = redisTemplate.opsForValue().get(JwtRedisKeyConstants.USER_PRI_KEY);
            String userPubKey = redisTemplate.opsForValue().get(JwtRedisKeyConstants.USER_PUB_KEY);
            String algorithm = redisTemplate.opsForValue().get(JwtRedisKeyConstants.USER_PRI_KEY_ALG);

            authTokenSecretKey.setUserPriKey(userPriKey);
            authTokenSecretKey.setUserPubKey(userPubKey);
            authTokenSecretKey.setAlgorithm(algorithm);

            /* 加密 token 私钥 */
            PrivateKey privateKey = secretKeyHelper.privateKey(authTokenSecretKey.getUserPriKey(), algorithm);
            authTokenSecretKey.setPrivateKey(privateKey);

            /* 解析 token 公钥 */
            PublicKey publicKey = verifyTicketPublicKeyHelper.publicKey(userPubKey, algorithm);
            this.tokenPublicKey.setPublicKey(publicKey);
        } else {
            JwtSecretKey jwtSecretKey = secretKeyHelper.getJwtSecretKey();

            redisTemplate.opsForValue().set(JwtRedisKeyConstants.USER_PRI_KEY, jwtSecretKey.getPrivateKey());
            redisTemplate.opsForValue().set(JwtRedisKeyConstants.USER_PUB_KEY, jwtSecretKey.getPublicKey());
            redisTemplate.opsForValue().set(JwtRedisKeyConstants.USER_PRI_KEY_ALG, jwtSecretKey.getAlgorithm());

            authTokenSecretKey.setUserPriKey(jwtSecretKey.getPrivateKey());
            authTokenSecretKey.setUserPubKey(jwtSecretKey.getPublicKey());
            authTokenSecretKey.setAlgorithm(jwtSecretKey.getAlgorithm());

            /* 加密 token 私钥 */
            PrivateKey privateKey = secretKeyHelper.privateKey(authTokenSecretKey.getUserPriKey(), jwtSecretKey.getAlgorithm());
            authTokenSecretKey.setPrivateKey(privateKey);

            /* 解析 token 公钥 */
            PublicKey publicKey = verifyTicketPublicKeyHelper.publicKey(jwtSecretKey.getPublicKey(), jwtSecretKey.getAlgorithm());
            this.tokenPublicKey.setPublicKey(publicKey);

        }
        logger.info("用户 secret key 初始化完成 ...");
    }

}
