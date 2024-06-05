package com.github.itdachen.auth.oauth.service.impl;

import com.github.itdachen.auth.oauth.entity.AuthorizeOAuthToken;
import com.github.itdachen.auth.oauth.mapper.IAuthorizeOAuthTokenMapper;
import com.github.itdachen.auth.oauth.service.IAuthorizeOAuthTokenService;
import com.github.itdachen.cloud.jwt.ICryptoTokenHandler;
import com.github.itdachen.framework.context.constants.UserInfoConstant;
import com.github.itdachen.framework.context.constants.UserTypeConstant;
import com.github.itdachen.framework.context.exception.BizException;
import com.github.itdachen.framework.context.jwt.AccessTokenInfo;
import com.github.itdachen.framework.context.jwt.JwtTokenInfo;
import com.github.itdachen.framework.context.userdetails.UserInfoDetails;
import com.github.itdachen.framework.core.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public AuthorizeOAuthTokenServiceImpl(IAuthorizeOAuthTokenMapper authorizeOAuthTokenMapper,
                                          ICryptoTokenHandler jwtsTokenHelper,
                                          PasswordEncoder passwordEncoder) {
        this.authorizeOAuthTokenMapper = authorizeOAuthTokenMapper;
        this.jwtsTokenHelper = jwtsTokenHelper;
        this.passwordEncoder = passwordEncoder;
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
        if (null == authToken
                || StringUtils.isEmpty(authToken.getUsername())
                || StringUtils.isEmpty(authToken.getPassword())) {
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
        currentUserDetails.setId("1");
        currentUserDetails.setUserType("1");


        /* 存放在 token 中的信息 */
        Map<String, String> otherInfo = new HashMap<>(16);
        otherInfo.put(UserInfoConstant.AVATAR, currentUserDetails.getAvatar());
        otherInfo.put(UserInfoConstant.TELEPHONE, currentUserDetails.getTelephone());
        otherInfo.put(UserInfoConstant.USER_TYPE, UserTypeConstant.MEMBER);
        otherInfo.put(UserInfoConstant.TENANT_ID, currentUserDetails.getTenantId());
        otherInfo.put(UserInfoConstant.DEPT_LEVEL, currentUserDetails.getDeptLevel());

        String access_token = jwtsTokenHelper.token(new JwtTokenInfo.Builder()
                .username(currentUserDetails.getUsername())
                .nickName(currentUserDetails.getNickName())
                .userId(currentUserDetails.getId())
                .otherInfo(otherInfo)
                .build()
        );


        Map<String, Object> infoMap = new HashMap<>(8);
        infoMap.put(UserInfoConstant.USER_TYPE, UserTypeConstant.MEMBER);
        infoMap.put(UserInfoConstant.TENANT_ID, currentUserDetails.getTenantId());
        infoMap.put(UserInfoConstant.AVATAR, currentUserDetails.getAvatar());
        infoMap.put(UserInfoConstant.NICK_NAME, currentUserDetails.getNickName());
        infoMap.put(UserInfoConstant.TELEPHONE, currentUserDetails.getTelephone());
        infoMap.put(UserInfoConstant.DEPT_LEVEL, currentUserDetails.getDeptLevel());

        return new AccessTokenInfo.Builder()
                .access_token(access_token)
             //   .expires_in(Integer.parseInt(String.valueOf(jwtProperties.getExpires())))
                .info(infoMap)
                .build();
    }

}
