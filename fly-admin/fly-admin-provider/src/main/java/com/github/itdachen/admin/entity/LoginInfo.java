package com.github.itdachen.admin.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 登录信息
 *
 * @author 王大宸
 * @date 2024-09-02 16:16:52
 */
@Table(name = "fly_next_login_info")
public class LoginInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID(与 ta_fly_user_info 表保持一致)
     */
    @Id
    @Column(name = "id")
    private String id;

    /**
     * 登录账号
     */
    @Column(name = "username")
    private String username;

    /**
     * 当前登录密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 上次更新密码(加密后)
     */
    @Column(name = "last_password")
    private String lastPassword;

    /**
     * 有效标志: Y-有效;N-无效(与 ta_fly_user_info 表保持一致)
     */
    @Column(name = "valid_flag")
    private String validFlag;

    /**
     * 删除标志(逻辑删除):  Y-删除;N-未删除(与 ta_fly_user_info 表保持一致)
     */
    @Column(name = "del_flag")
    private String delFlag;

    /**
     * 过期时间(密码过期时间, 有些系统需要定期更新账号的密码)
     */
    @Column(name = "exp_time")
    private LocalDateTime expTime;

    /**
     * 上次更新密码时间
     */
    @Column(name = "last_time")
    private LocalDateTime lastTime;

    /**
     * 创建日期
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    /**
     * 删除时间
     */
    @Column(name = "del_time")
    private LocalDateTime delTime;


    public LoginInfo() {
    }


    public LoginInfo(String id, String username, String password, String lastPassword, String validFlag, String delFlag, LocalDateTime expTime, LocalDateTime lastTime, LocalDateTime createTime, LocalDateTime updateTime, LocalDateTime delTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.lastPassword = lastPassword;
        this.validFlag = validFlag;
        this.delFlag = delFlag;
        this.expTime = expTime;
        this.lastTime = lastTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.delTime = delTime;
    }

    public static LoginInfoBuilder builder() {
        return new LoginInfoBuilder();
    }

    public static class LoginInfoBuilder {
        private String id;
        private String username;
        private String password;
        private String lastPassword;
        private String validFlag;
        private String delFlag;
        private LocalDateTime expTime;
        private LocalDateTime lastTime;
        private LocalDateTime createTime;
        private LocalDateTime updateTime;
        private LocalDateTime delTime;

        public LoginInfoBuilder() {
        }

        /* 用户ID(与 ta_fly_user_info 表保持一致) */
        public LoginInfoBuilder id(String id) {
            this.id = id;
            return this;
        }

        /* 登录账号 */
        public LoginInfoBuilder username(String username) {
            this.username = username;
            return this;
        }

        /* 当前登录密码 */
        public LoginInfoBuilder password(String password) {
            this.password = password;
            return this;
        }

        /* 上次更新密码(加密后) */
        public LoginInfoBuilder lastPassword(String lastPassword) {
            this.lastPassword = lastPassword;
            return this;
        }

        /* 有效标志: Y-有效;N-无效(与 ta_fly_user_info 表保持一致) */
        public LoginInfoBuilder validFlag(String validFlag) {
            this.validFlag = validFlag;
            return this;
        }

        /* 删除标志(逻辑删除):  Y-删除;N-未删除(与 ta_fly_user_info 表保持一致) */
        public LoginInfoBuilder delFlag(String delFlag) {
            this.delFlag = delFlag;
            return this;
        }

        /* 过期时间(密码过期时间, 有些系统需要定期更新账号的密码) */
        public LoginInfoBuilder expTime(LocalDateTime expTime) {
            this.expTime = expTime;
            return this;
        }

        /* 上次更新密码时间 */
        public LoginInfoBuilder lastTime(LocalDateTime lastTime) {
            this.lastTime = lastTime;
            return this;
        }

        /* 创建日期 */
        public LoginInfoBuilder createTime(LocalDateTime createTime) {
            this.createTime = createTime;
            return this;
        }

        /* 更新时间 */
        public LoginInfoBuilder updateTime(LocalDateTime updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        /* 删除时间 */
        public LoginInfoBuilder delTime(LocalDateTime delTime) {
            this.delTime = delTime;
            return this;
        }

        public LoginInfo build() {
            return new LoginInfo(id,
                    username,
                    password,
                    lastPassword,
                    validFlag,
                    delFlag,
                    expTime,
                    lastTime,
                    createTime,
                    updateTime,
                    delTime
            );
        }

    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setLastPassword(String lastPassword) {
        this.lastPassword = lastPassword;
    }

    public String getLastPassword() {
        return lastPassword;
    }

    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
    }

    public String getValidFlag() {
        return validFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setExpTime(LocalDateTime expTime) {
        this.expTime = expTime;
    }

    public LocalDateTime getExpTime() {
        return expTime;
    }

    public void setLastTime(LocalDateTime lastTime) {
        this.lastTime = lastTime;
    }

    public LocalDateTime getLastTime() {
        return lastTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setDelTime(LocalDateTime delTime) {
        this.delTime = delTime;
    }

    public LocalDateTime getDelTime() {
        return delTime;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("username", getUsername())
                .append("password", getPassword())
                .append("lastPassword", getLastPassword())
                .append("validFlag", getValidFlag())
                .append("delFlag", getDelFlag())
                .append("expTime", getExpTime())
                .append("lastTime", getLastTime())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("delTime", getDelTime())
                .toString();
    }

}
