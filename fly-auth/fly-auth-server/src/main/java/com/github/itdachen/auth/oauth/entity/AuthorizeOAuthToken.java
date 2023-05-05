package com.github.itdachen.auth.oauth.entity;

/***
 * 密码登录请求参数
 *
 * @author 王大宸
 * @date 2023/5/5 14:52
 */
public class AuthorizeOAuthToken {

    /**
     * 登录账号
     */
    private String username;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 验证码
     */
    private String code;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
