package com.github.itdachen.auth.oauth.service;

import com.github.itdachen.auth.oauth.entity.AuthorizeOAuthToken;
import com.github.itdachen.framework.cloud.jwt.crypto.AccessTokenInfo;

/**
 * Description:
 * Created by 王大宸 on 2023/05/05 14:33
 * Created with IntelliJ IDEA.
 */
public interface IAuthorizeOAuthTokenService {

    /***
     * 账号密码登录
     *
     * @author 王大宸
     * @date 2023/5/5 15:05
     * @param authToken authToken
     * @return com.github.itdachen.auth.jwts.core.AccessTokenInfo
     */
    AccessTokenInfo oauthJwtToken(AuthorizeOAuthToken authToken) throws Exception;


}
