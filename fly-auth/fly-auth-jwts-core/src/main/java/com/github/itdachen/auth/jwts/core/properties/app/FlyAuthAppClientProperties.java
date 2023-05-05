package com.github.itdachen.auth.jwts.core.properties.app;

/**
 * Description:
 * Created by 王大宸 on 2023/05/01 15:41
 * Created with IntelliJ IDEA.
 */
public class FlyAuthAppClientProperties {

    private String appId = "fly";

    private String appSecret = "com.github.itdachen";

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
