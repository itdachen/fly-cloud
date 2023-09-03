package com.github.itdachen.auth.oauth.controller;

import com.github.itdachen.auth.oauth.service.IVerifyClientSecretKeyService;
import com.github.itdachen.framework.cloud.jwt.crypto.AuthTokenSecretKey;
import com.github.itdachen.framework.cloud.jwt.parse.key.AuthClientTokenSecretKey;
import com.github.itdachen.framework.context.annotation.IgnoreUserToken;
import com.github.itdachen.framework.core.response.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/***
 * 客户端同步公钥
 *
 * @author 王大宸
 * @date 2023/5/5 14:45
 */
@RestController
@IgnoreUserToken // 不需要 token 校验
@RequestMapping(value = "/client")
public class AuthAppClientUserKeyController {
    private static final Logger logger = LoggerFactory.getLogger(AuthAppClientUserKeyController.class);

    private final AuthClientTokenSecretKey authClientTokenSecretKey;

    public AuthAppClientUserKeyController(AuthClientTokenSecretKey authClientTokenSecretKey) {
        this.authClientTokenSecretKey = authClientTokenSecretKey;
    }

    /***
     * 同步用户公钥
     *
     * @author 王大宸
     * @date 2023/5/5 15:01
     * @param appId       客户端ID
     * @param appSecret   客户端秘钥
     * @return com.github.itdachen.framework.core.response.ServerResponse<java.lang.String>
     */
    @GetMapping("/user/secret/key")
    public ServerResponse<String> userSecretKey(@RequestParam(value = "appId") String appId,
                                                @RequestParam(value = "appSecret") String appSecret) throws Exception {

        logger.info("获取客户端 token 公钥, appId: {}, appSecret: {}", appId, appSecret);

        // TODO 自行根据 appId 和 appSecret 校验是否能获取 token 公钥

        return ServerResponse.okData(authClientTokenSecretKey.getTokenPublicKey());
    }

}
