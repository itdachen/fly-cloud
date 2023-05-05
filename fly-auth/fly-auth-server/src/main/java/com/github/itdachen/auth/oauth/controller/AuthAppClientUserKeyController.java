package com.github.itdachen.auth.oauth.controller;

import com.github.itdachen.auth.jwts.core.SecretKeyConfiguration;
import com.github.itdachen.auth.oauth.service.IVerifyClientSecretKeyService;
import com.github.itdachen.framework.context.annotation.IgnoreUserToken;
import com.github.itdachen.framework.core.response.ServerResponse;
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
@IgnoreUserToken
@RequestMapping(value = "/client")
public class AuthAppClientUserKeyController {

    @Autowired
    private SecretKeyConfiguration secretKeyConfiguration;
    @Autowired
    private IVerifyClientSecretKeyService verifyClientSecretKeyService;

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
    public ServerResponse<String> userSecretKey(@RequestParam("appId") String appId,
                                                @RequestParam("appSecret") String appSecret) throws Exception {
        verifyClientSecretKeyService.verifyClient(appId, appSecret);
        return ServerResponse.okData(secretKeyConfiguration.getUserPubKey());
    }

}
