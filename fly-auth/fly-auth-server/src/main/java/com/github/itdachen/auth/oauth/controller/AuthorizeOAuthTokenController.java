package com.github.itdachen.auth.oauth.controller;

import com.github.itdachen.auth.oauth.entity.AuthorizeOAuthToken;
import com.github.itdachen.auth.oauth.service.IAuthorizeOAuthTokenService;
import com.github.itdachen.framework.cloud.jwt.core.AccessTokenInfo;
import com.github.itdachen.framework.core.response.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * 登录认证
 *
 * @author 王大宸
 * @date 2023/5/5 14:28
 */
@RestController
@RequestMapping("/oauth/jwt")
public class AuthorizeOAuthTokenController {
    private static final Logger logger = LoggerFactory.getLogger(AuthorizeOAuthTokenController.class);
    private final IAuthorizeOAuthTokenService oAuthTokenService;

    public AuthorizeOAuthTokenController(IAuthorizeOAuthTokenService oAuthTokenService) {
        this.oAuthTokenService = oAuthTokenService;
    }
    
    /***
     * 账号密码登录
     *
     * @author 王大宸
     * @date 2023/5/5 14:52
     * @param authToken authToken
     * @return com.github.itdachen.framework.core.response.ServerResponse<com.github.itdachen.auth.jwts.core.AccessTokenInfo>
     */
    @PostMapping(value = "/token")
    public ServerResponse<AccessTokenInfo> oauthJwtToken(@RequestBody AuthorizeOAuthToken authToken) throws Exception {
        return ServerResponse.okMsgData("登录成功!", oAuthTokenService.oauthJwtToken(authToken));
    }

}
