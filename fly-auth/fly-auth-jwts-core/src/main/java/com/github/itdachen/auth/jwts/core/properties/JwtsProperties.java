package com.github.itdachen.auth.jwts.core.properties;

import com.github.itdachen.auth.jwts.core.properties.app.FlyAuthAppClientProperties;
import com.github.itdachen.auth.jwts.core.properties.token.JwtsTokenProperties;
import com.github.itdachen.framework.context.constants.UserInfoConstant;
import com.github.itdachen.framework.context.constants.UserTypeConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 认证配置
 * Created by 王大宸 on 2023/04/30 15:16
 * Created with IntelliJ IDEA.
 */
@ConfigurationProperties(prefix = "auth")
public class JwtsProperties {

    /**
     * token 配置
     */
    private JwtsTokenProperties token = new JwtsTokenProperties();

    /**
     * 客户端配置
     */
    private FlyAuthAppClientProperties app = new FlyAuthAppClientProperties();


    /**
     * 不需要认证的复制
     */
    private List<String> matchers = new ArrayList<>();

    /**
     * 加解密秘钥
     */
    private String secretKey = "tNTaMC1B0Kzdl1q0LKJy7MooRbuwaebzfcS3nGOsPAoSvt3w==";

    /**
     * 认证中心名称
     */
    private String serviceId = "auth";

    /**
     * 权限校验标识
     */
    private String verifiedTicket = UserInfoConstant.VERIFIED_TICKET_VALUE;

    /**
     * 内网请求标识
     */
    private String nalFeign = UserInfoConstant.NAL_FEIGN_VALUE;


    public JwtsTokenProperties getToken() {
        return token;
    }

    public void setToken(JwtsTokenProperties token) {
        this.token = token;
    }

    public FlyAuthAppClientProperties getApp() {
        return app;
    }

    public void setApp(FlyAuthAppClientProperties app) {
        this.app = app;
    }

    public List<String> getMatchers() {
        return matchers;
    }

    public void setMatchers(List<String> matchers) {
        this.matchers = matchers;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getVerifiedTicket() {
        return verifiedTicket;
    }

    public void setVerifiedTicket(String verifiedTicket) {
        this.verifiedTicket = verifiedTicket;
    }

    public String getNalFeign() {
        return nalFeign;
    }

    public void setNalFeign(String nalFeign) {
        this.nalFeign = nalFeign;
    }
}
