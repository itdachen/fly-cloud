package com.github.itdachen.auth.rpc;

import com.github.itdachen.auth.interfaces.TokenUserPubKey;
import com.github.itdachen.auth.interfaces.client.IAuthClientTokenSecretRpc;
import com.github.itdachen.cloud.jwt.contents.JwtRedisKeyConstants;
import com.github.itdachen.cloud.jwt.crypto.AuthTokenSecretKey;
import com.github.itdachen.framework.core.response.ServerResponse;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 获取 TOKEN 解析公钥
 *
 * @author 王大宸
 * @date 2023-12-10 20:01
 */
@DubboService
public class AuthClientTokenSecretRpc implements IAuthClientTokenSecretRpc {
    private static final Logger logger = LoggerFactory.getLogger(AuthClientTokenSecretRpc.class);

    private final AuthTokenSecretKey secretKey;

    private final RedisTemplate<String, String> redisTemplate;

    public AuthClientTokenSecretRpc(RedisTemplate<String, String> redisTemplate,
                                    AuthTokenSecretKey secretKey) {
        this.redisTemplate = redisTemplate;
        this.secretKey = secretKey;
    }


    @Override
    public ServerResponse<TokenUserPubKey> getSecretPublicKey(String appId, String appSecret) throws Exception {
        logger.info("获取客户端 token 公钥, appId: {}, appSecret: {}", appId, appSecret);

        // TODO 自行根据 appId 和 appSecret 校验是否能获取 token 公钥


       // return ServerResponse.ok(new TokenUserPubKey(secretKey.getUserPubKey(), secretKey.getAlgorithm()));

        // final String userPriKey = redisTemplate.opsForValue().get(JwtRedisKeyConstants.USER_PRI_KEY);
        final String userPubKey = redisTemplate.opsForValue().get(JwtRedisKeyConstants.USER_PUB_KEY);
        final String algorithm = redisTemplate.opsForValue().get(JwtRedisKeyConstants.USER_PRI_KEY_ALG);

        return ServerResponse.ok(new TokenUserPubKey(userPubKey, algorithm));

    }


}
