package com.github.itdachen.admin.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 用户管理
 *
 * @author 王大宸
 * @date 2024-09-02 14:38:09
 */
@Table(name = "fly_next_user_info")
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户id唯一标识
     */
    @Id
    @Column(name = "id")
    private String id;

    /**
     * 租户ID
     */
    @Column(name = "tenant_id")
    private String tenantId;

    /**
     * 租户名称
     */
    @Column(name = "tenant_title")
    private String tenantTitle;

    /**
     * 昵称
     */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 身份证号码
     */
    @Column(name = "id_card")
    private String idCard;

    /**
     * 性别
     */
    @Column(name = "sex")
    private String sex;

    /**
     * 电话号码
     */
    @Column(name = "telephone")
    private String telephone;

    /**
     * 头像
     */
    @Column(name = "avatar")
    private String avatar;

    /**
     * 电子邮箱
     */
    @Column(name = "e_mail_box")
    private String eMailBox;

    /**
     * 用户类型
     */
    @Column(name = "user_type")
    private String userType;

    /**
     * 有效标志: Y-是;N-否; 与 ta_fly_login_info 表保持一致
     */
    @Column(name = "valid_flag")
    private String validFlag;

    /**
     * 备注
     */
    @Column(name = "remarks")
    private String remarks;

    /**
     * 删除标志
     */
    @Column(name = "del_flag")
    private String delFlag;

    /**
     * 删除时间
     */
    @Column(name = "del_time")
    private LocalDateTime delTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @Column(name = "create_user")
    private String createUser;

    /**
     * 创建人id
     */
    @Column(name = "create_user_id")
    private String createUserId;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    /**
     * 更新人
     */
    @Column(name = "update_user")
    private String updateUser;

    /**
     * 更新人id
     */
    @Column(name = "update_user_id")
    private String updateUserId;


    public UserInfo() {
    }


    public UserInfo(String id, String tenantId, String tenantTitle, String nickName, String idCard, String sex, String telephone, String avatar, String eMailBox, String userType, String validFlag, String remarks, String delFlag, LocalDateTime delTime, LocalDateTime createTime, String createUser, String createUserId, LocalDateTime updateTime, String updateUser, String updateUserId) {
        this.id = id;
        this.tenantId = tenantId;
        this.tenantTitle = tenantTitle;
        this.nickName = nickName;
        this.idCard = idCard;
        this.sex = sex;
        this.telephone = telephone;
        this.avatar = avatar;
        this.eMailBox = eMailBox;
        this.userType = userType;
        this.validFlag = validFlag;
        this.remarks = remarks;
        this.delFlag = delFlag;
        this.delTime = delTime;
        this.createTime = createTime;
        this.createUser = createUser;
        this.createUserId = createUserId;
        this.updateTime = updateTime;
        this.updateUser = updateUser;
        this.updateUserId = updateUserId;
    }

    public static UserInfoBuilder builder() {
        return new UserInfoBuilder();
    }

    public static class UserInfoBuilder {
        private String id;
        private String tenantId;
        private String tenantTitle;
        private String nickName;
        private String idCard;
        private String sex;
        private String telephone;
        private String avatar;
        private String eMailBox;
        private String userType;
        private String validFlag;
        private String remarks;
        private String delFlag;
        private LocalDateTime delTime;
        private LocalDateTime createTime;
        private String createUser;
        private String createUserId;
        private LocalDateTime updateTime;
        private String updateUser;
        private String updateUserId;

        public UserInfoBuilder() {
        }

        /* 用户id唯一标识 */
        public UserInfoBuilder id(String id) {
            this.id = id;
            return this;
        }

        /* 租户ID */
        public UserInfoBuilder tenantId(String tenantId) {
            this.tenantId = tenantId;
            return this;
        }

        /* 租户名称 */
        public UserInfoBuilder tenantTitle(String tenantTitle) {
            this.tenantTitle = tenantTitle;
            return this;
        }

        /* 昵称 */
        public UserInfoBuilder nickName(String nickName) {
            this.nickName = nickName;
            return this;
        }

        /* 身份证号码 */
        public UserInfoBuilder idCard(String idCard) {
            this.idCard = idCard;
            return this;
        }

        /* 性别 */
        public UserInfoBuilder sex(String sex) {
            this.sex = sex;
            return this;
        }

        /* 电话号码 */
        public UserInfoBuilder telephone(String telephone) {
            this.telephone = telephone;
            return this;
        }

        /* 头像 */
        public UserInfoBuilder avatar(String avatar) {
            this.avatar = avatar;
            return this;
        }

        /* 电子邮箱 */
        public UserInfoBuilder eMailBox(String eMailBox) {
            this.eMailBox = eMailBox;
            return this;
        }

        /* 用户类型 */
        public UserInfoBuilder userType(String userType) {
            this.userType = userType;
            return this;
        }

        /* 有效标志: Y-是;N-否; 与 ta_fly_login_info 表保持一致 */
        public UserInfoBuilder validFlag(String validFlag) {
            this.validFlag = validFlag;
            return this;
        }

        /* 备注 */
        public UserInfoBuilder remarks(String remarks) {
            this.remarks = remarks;
            return this;
        }

        /* 删除标志 */
        public UserInfoBuilder delFlag(String delFlag) {
            this.delFlag = delFlag;
            return this;
        }

        /* 删除时间 */
        public UserInfoBuilder delTime(LocalDateTime delTime) {
            this.delTime = delTime;
            return this;
        }

        /* 创建时间 */
        public UserInfoBuilder createTime(LocalDateTime createTime) {
            this.createTime = createTime;
            return this;
        }

        /* 创建人 */
        public UserInfoBuilder createUser(String createUser) {
            this.createUser = createUser;
            return this;
        }

        /* 创建人id */
        public UserInfoBuilder createUserId(String createUserId) {
            this.createUserId = createUserId;
            return this;
        }

        /* 更新时间 */
        public UserInfoBuilder updateTime(LocalDateTime updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        /* 更新人 */
        public UserInfoBuilder updateUser(String updateUser) {
            this.updateUser = updateUser;
            return this;
        }

        /* 更新人id */
        public UserInfoBuilder updateUserId(String updateUserId) {
            this.updateUserId = updateUserId;
            return this;
        }

        public UserInfo build() {
            return new UserInfo(id,
                    tenantId,
                    tenantTitle,
                    nickName,
                    idCard,
                    sex,
                    telephone,
                    avatar,
                    eMailBox,
                    userType,
                    validFlag,
                    remarks,
                    delFlag,
                    delTime,
                    createTime,
                    createUser,
                    createUserId,
                    updateTime,
                    updateUser,
                    updateUserId
            );
        }

    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantTitle(String tenantTitle) {
        this.tenantTitle = tenantTitle;
    }

    public String getTenantTitle() {
        return tenantTitle;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public void seteMailBox(String eMailBox) {
        this.eMailBox = eMailBox;
    }

    public String geteMailBox() {
        return eMailBox;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserType() {
        return userType;
    }

    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
    }

    public String getValidFlag() {
        return validFlag;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelTime(LocalDateTime delTime) {
        this.delTime = delTime;
    }

    public LocalDateTime getDelTime() {
        return delTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("tenantId", getTenantId())
                .append("tenantTitle", getTenantTitle())
                .append("nickName", getNickName())
                .append("idCard", getIdCard())
                .append("sex", getSex())
                .append("telephone", getTelephone())
                .append("avatar", getAvatar())
                .append("eMailBox", geteMailBox())
                .append("userType", getUserType())
                .append("validFlag", getValidFlag())
                .append("remarks", getRemarks())
                .append("delFlag", getDelFlag())
                .append("delTime", getDelTime())
                .append("createTime", getCreateTime())
                .append("createUser", getCreateUser())
                .append("createUserId", getCreateUserId())
                .append("updateTime", getUpdateTime())
                .append("updateUser", getUpdateUser())
                .append("updateUserId", getUpdateUserId())
                .toString();
    }

}
