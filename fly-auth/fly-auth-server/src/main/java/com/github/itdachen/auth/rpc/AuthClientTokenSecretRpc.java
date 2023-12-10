package com.github.itdachen.auth.rpc;

import com.github.itdachen.framework.cloud.jwt.parse.AuthClientTokenSecretKey;
import com.github.itdachen.auth.interfaces.client.IAuthClientTokenSecretRpc;
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

    private final AuthClientTokenSecretKey authClientTokenSecretKey;

    public AuthClientTokenSecretRpc(AuthClientTokenSecretKey authClientTokenSecretKey) {
        this.authClientTokenSecretKey = authClientTokenSecretKey;
    }

    @Override
    public ServerResponse<String> getSecretPublicKey(String appId, String appSecret) throws Exception {
        logger.info("获取客户端 token 公钥, appId: {}, appSecret: {}", appId, appSecret);

        // TODO 自行根据 appId 和 appSecret 校验是否能获取 token 公钥

        return ServerResponse.okData(authClientTokenSecretKey.getTokenPublicKey());
    }


}
