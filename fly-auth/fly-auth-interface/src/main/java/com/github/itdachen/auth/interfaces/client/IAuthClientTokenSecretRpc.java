package com.github.itdachen.auth.interfaces.client;

import com.github.itdachen.auth.interfaces.TokenUserPubKey;
import com.github.itdachen.framework.core.response.ServerResponse;

/**
 * 获取 token 解密秘钥
 *
 * @author 王大宸
 * @date 2023/12/10 19:59
 */
public interface IAuthClientTokenSecretRpc {

    ServerResponse<TokenUserPubKey> getSecretPublicKey(String appId, String appSecret) throws Exception;

}
