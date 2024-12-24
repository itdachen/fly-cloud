package com.github.itdachen.auth.oauth.service.impl;

import com.github.itdachen.auth.oauth.entity.AuthorizeOAuthToken;
import com.github.itdachen.auth.oauth.mapper.IAuthorizeOAuthTokenMapper;
import com.github.itdachen.auth.oauth.service.IAuthorizeOAuthTokenService;
import com.github.itdachen.boot.autoconfigure.app.AppInfoProperties;
import com.github.itdachen.boot.autoconfigure.app.PlatformInfoProperties;
import com.github.itdachen.boot.autoconfigure.cloud.auth.properties.CloudTokenProperties;
import com.github.itdachen.cloud.jwt.ICryptoTokenHandler;
import com.github.itdachen.cloud.jwt.handler.TokenUserDetailsHandler;
import com.github.itdachen.framework.context.constants.LoginMethodConstant;
import com.github.itdachen.framework.context.constants.TokenTicketConstant;
import com.github.itdachen.framework.context.constants.UserInfoConstant;
import com.github.itdachen.framework.context.constants.UserTypeConstant;
import com.github.itdachen.framework.context.exception.BizException;
import com.github.itdachen.framework.context.jwt.AccessTokenInfo;
import com.github.itdachen.framework.context.jwt.JwtTokenInfo;
import com.github.itdachen.framework.context.userdetails.UserInfoDetails;
import com.github.itdachen.framework.core.utils.LocalDateUtils;
import com.github.itdachen.framework.core.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Created by 王大宸 on 2023/05/05 14:33
 * Created with IntelliJ IDEA.
 */
@Service
public class AuthorizeOAuthTokenServiceImpl implements IAuthorizeOAuthTokenService {
    private static final Logger logger = LoggerFactory.getLogger(AuthorizeOAuthTokenServiceImpl.class);
    private final IAuthorizeOAuthTokenMapper authorizeOAuthTokenMapper;
    private final ICryptoTokenHandler jwtsTokenHelper;
    private final PasswordEncoder passwordEncoder;
    private final CloudTokenProperties cloudTokenProperties;

    @Autowired
    private PlatformInfoProperties platformInfoProperties;
    @Autowired
    private AppInfoProperties appInfoProperties;


    public AuthorizeOAuthTokenServiceImpl(IAuthorizeOAuthTokenMapper authorizeOAuthTokenMapper,
                                          ICryptoTokenHandler jwtsTokenHelper,
                                          PasswordEncoder passwordEncoder,
                                          CloudTokenProperties cloudTokenProperties) {
        this.authorizeOAuthTokenMapper = authorizeOAuthTokenMapper;
        this.jwtsTokenHelper = jwtsTokenHelper;
        this.passwordEncoder = passwordEncoder;
        this.cloudTokenProperties = cloudTokenProperties;
    }

    /***
     * 账号密码登录
     *
     * @author 王大宸
     * @date 2023/5/5 14:54
     * @param authToken authToken
     * @return com.github.itdachen.auth.jwts.core.AccessTokenInfo
     */
    @Override
    public AccessTokenInfo oauthJwtToken(AuthorizeOAuthToken authToken) throws Exception {
        // 腾讯服务器根据 CODE 获取用户信息(OPENID)


        if (null == authToken || StringUtils.isEmpty(authToken.getUsername()) || StringUtils.isEmpty(authToken.getPassword())) {
            throw new BizException("登录认证信息不全!!!");
        }
//        CurrentUserDetails currentUserDetails = authorizedMapper.loadUserByUsername(authToken.getUsername());
//
//        if (null == currentUserDetails) {
//            throw new BizException("用户信息不存在");
//        }
//
//        if (!passwordEncoder.matches(authToken.getPassword(), currentUserDetails.getAccountSecret())) {
//            throw new BizException("密码错误");
//        }

        UserInfoDetails currentUserDetails = new UserInfoDetails();
        currentUserDetails.setUsername("admin");
        currentUserDetails.setNickName("王大宸");
        currentUserDetails.setId("1541230113952239617");
        currentUserDetails.setUserType(UserTypeConstant.SUPER_ADMINISTRATOR);
        currentUserDetails.setTenantId("520115100000001");
        currentUserDetails.setTenantTitle("太虚十境");
        currentUserDetails.setExpTime(LocalDateTime.now());
        currentUserDetails.setLastTime(LocalDateTime.now());

        /* 密码设置为空 */
        currentUserDetails.setLoginMethod(LoginMethodConstant.USERNAME_PASSWORD);
        currentUserDetails.setPassword("");
        /* 平台/应用信息 */
        currentUserDetails.setPlatId(platformInfoProperties.getId());
        currentUserDetails.setPlatName(platformInfoProperties.getTitle());
        currentUserDetails.setAppId(appInfoProperties.getAppId());
        currentUserDetails.setAppName(appInfoProperties.getAppName());
        currentUserDetails.setAppVersion(appInfoProperties.getVersion());
        currentUserDetails.setAppContextPath(appInfoProperties.getContextPath());

        currentUserDetails.setDeptId("1871467053303599104");
        currentUserDetails.setDeptLevel("00");
        currentUserDetails.setDeptParentId("520115100000001");
        currentUserDetails.setDeptTitle("领导办公室");

        Map<String, String> userDetailMap = TokenUserDetailsHandler.setUserDetailMap(currentUserDetails, TokenTicketConstant.ACCESS_TOKEN);
        String access_token = jwtsTokenHelper.accessToken(new JwtTokenInfo.Builder()
                .username(currentUserDetails.getUsername())
                .nickName(currentUserDetails.getNickName())
                .userId(currentUserDetails.getId())
                .otherInfo(userDetailMap)
                .build()
        );

        Map<String, String> refreshTokenMap = TokenUserDetailsHandler.setUserDetailMap(currentUserDetails, TokenTicketConstant.REFRESH_TOKEN);
        String refreshToken = jwtsTokenHelper.accessToken(new JwtTokenInfo.Builder()
                .username(currentUserDetails.getUsername())
                .nickName(currentUserDetails.getNickName())
                .userId(currentUserDetails.getId())
                .otherInfo(refreshTokenMap)
                .build()
        );


        Map<String, Object> infoMap = TokenUserDetailsHandler.toFrontEndInfo(currentUserDetails);

        return new AccessTokenInfo.Builder()
                .access_token(access_token)
                .refresh_token(refreshToken)
                .token_type(UserInfoConstant.TOKEN_TYPE)
                .expires_in(Integer.parseInt(String.valueOf(cloudTokenProperties.getExpires())))
                .info(infoMap)
                .build();
    }

}
