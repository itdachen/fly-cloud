package com.github.itdachen.auth.rpc;

import com.github.itdachen.auth.interfaces.TokenUserPubKey;
import com.github.itdachen.auth.interfaces.client.IAuthClientTokenSecretRpc;
import com.github.itdachen.cloud.jwt.crypto.AuthTokenSecretKey;
import com.github.itdachen.framework.core.response.ServerResponse;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AuthClientTokenSecretRpc
 *
 * @author 王大宸
 * @date 2023-12-10 20:01
 */
@DubboService
public class AuthClientTokenSecretRpc implements IAuthClientTokenSecretRpc {
    private static final Logger logger = LoggerFactory.getLogger(AuthClientTokenSecretRpc.class);

    private final AuthTokenSecretKey authClientTokenSecretKey;

    public AuthClientTokenSecretRpc(AuthTokenSecretKey authClientTokenSecretKey) {
        this.authClientTokenSecretKey = authClientTokenSecretKey;
    }

    @Override
    public ServerResponse<TokenUserPubKey> getSecretPublicKey(String appId, String appSecret) throws Exception {
        logger.info("获取客户端 token 公钥, appId: {}, appSecret: {}", appId, appSecret);

        // TODO 自行根据 appId 和 appSecret 校验是否能获取 token 公钥

        return ServerResponse.ok(
                new TokenUserPubKey(
                        authClientTokenSecretKey.getUserPubKey(),
                        authClientTokenSecretKey.getAlgorithm()
                )
        );
    }


}