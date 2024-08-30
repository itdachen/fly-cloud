package com.github.itdachen.auth.oauth.controller;

import com.github.itdachen.auth.oauth.entity.AuthorizeOAuthToken;
import com.github.itdachen.auth.oauth.service.IAuthorizeOAuthTokenService;
import com.github.itdachen.auth.utils.CaptchaUtils;
import com.github.itdachen.framework.context.exception.BizException;
import com.github.itdachen.framework.context.jwt.AccessTokenInfo;
import com.github.itdachen.framework.core.response.ServerResponse;
import com.github.itdachen.framework.core.utils.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
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

    @Autowired
    private StringRedisTemplate redisTemplate;

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
    public ServerResponse<AccessTokenInfo> oauthJwtToken(HttpServletRequest request, @RequestBody AuthorizeOAuthToken authToken) throws Exception {
        verifyCaptcha(request, authToken);  // 校验验证码
        AccessTokenInfo accessTokenInfo = oAuthTokenService.oauthJwtToken(authToken);
        return ServerResponse.ok("登录成功!", accessTokenInfo);
    }

    /***
     * 账号密码登录
     *
     * @author 王大宸
     * @date 2024/7/30 9:40
     * @param authToken authToken
     * @return com.github.itdachen.framework.core.response.ServerResponse<com.github.itdachen.framework.context.jwt.AccessTokenInfo>
     */
    @PostMapping(value = "/token2")
    public ServerResponse<AccessTokenInfo> oauthJwtToken2(@RequestBody AuthorizeOAuthToken authToken) throws Exception {
        return ServerResponse.ok("登录成功!", null);
    }


    /***
     * 校验验证码
     *
     * @author 王大宸
     * @date 2024/8/30 10:43
     * @param request request
     * @param authToken authToken
     * @return void
     */
    private void verifyCaptcha(HttpServletRequest request, AuthorizeOAuthToken authToken) throws BizException {
        if (StringUtils.isEmpty(authToken.getCode())) {
            throw new BizException("验证码不能为空！");
        }
        final String key = CaptchaUtils.getCaptchaKey(request);
        String cacheCaptcha = redisTemplate.opsForValue().get(key);
        if (null == cacheCaptcha) {
            throw new BizException("验证码已失效！");
        }
        if (!authToken.getCode().equalsIgnoreCase(cacheCaptcha)) {
            throw new BizException("验证码错误！");
        }
        redisTemplate.delete(key);
    }


}
