package com.github.itdachen.admin.sdk.query;

import com.github.itdachen.framework.core.biz.BizQuery;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


import java.io.Serializable;


/**
 * 用户管理 查询参数
 *
 * @author 王大宸
 * @date 2024-09-02 14:38:09
 */
public class UserInfoQuery extends BizQuery implements Serializable {
    private static final long serialVersionUID = 1L;

            /** 租户ID */
            private String tenantId;

            /** 昵称 */
            private String nickName;

            /** 身份证号码 */
            private String idCard;

            /** 性别 */
            private String sex;

            /** 电话号码 */
            private String telephone;

            /** 电子邮箱 */
            private String eMailBox;

            /** 有效标志: Y-是;N-否; 与 ta_fly_login_info 表保持一致 */
            private String validFlag;



    public UserInfoQuery() {
    }

    public UserInfoQuery(int page, int limit, String tenantId, String nickName, String idCard, String sex, String telephone, String eMailBox, String validFlag) {
        super(page, limit);
            this.tenantId = tenantId;
            this.nickName = nickName;
            this.idCard = idCard;
            this.sex = sex;
            this.telephone = telephone;
            this.eMailBox = eMailBox;
            this.validFlag = validFlag;
    }

    public static UserInfoQueryBuilder builder() {
        return new UserInfoQueryBuilder();
    }

    public static class UserInfoQueryBuilder {
        private Integer page = 1;
        private Integer limit = 10;
            private String tenantId;
            private String nickName;
            private String idCard;
            private String sex;
            private String telephone;
            private String eMailBox;
            private String validFlag;

        public UserInfoQueryBuilder() {
        }

        public UserInfoQueryBuilder page(Integer page) {
            this.page = page;
            return this;
        }

        public UserInfoQueryBuilder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

            /* 租户ID */
            public UserInfoQueryBuilder tenantId(String tenantId) {
                this.tenantId = tenantId;
                return this;
            }
            /* 昵称 */
            public UserInfoQueryBuilder nickName(String nickName) {
                this.nickName = nickName;
                return this;
            }
            /* 身份证号码 */
            public UserInfoQueryBuilder idCard(String idCard) {
                this.idCard = idCard;
                return this;
            }
            /* 性别 */
            public UserInfoQueryBuilder sex(String sex) {
                this.sex = sex;
                return this;
            }
            /* 电话号码 */
            public UserInfoQueryBuilder telephone(String telephone) {
                this.telephone = telephone;
                return this;
            }
            /* 电子邮箱 */
            public UserInfoQueryBuilder eMailBox(String eMailBox) {
                this.eMailBox = eMailBox;
                return this;
            }
            /* 有效标志: Y-是;N-否; 与 ta_fly_login_info 表保持一致 */
            public UserInfoQueryBuilder validFlag(String validFlag) {
                this.validFlag = validFlag;
                return this;
            }

        public UserInfoQuery build() {
            return new UserInfoQuery(page, limit, tenantId, nickName, idCard, sex, telephone, eMailBox, validFlag);
        }

    }



        public void setTenantId(String tenantId) {
            this.tenantId = tenantId;
        }

        public String getTenantId() {
            return tenantId;
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

        public void seteMailBox(String eMailBox) {
            this.eMailBox = eMailBox;
        }

        public String geteMailBox() {
            return eMailBox;
        }

        public void setValidFlag(String validFlag) {
            this.validFlag = validFlag;
        }

        public String getValidFlag() {
            return validFlag;
        }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("page", getPage())
        .append("limit", getLimit())
                    .append("tenantId", getTenantId())
                    .append("nickName", getNickName())
                    .append("idCard", getIdCard())
                    .append("sex", getSex())
                    .append("telephone", getTelephone())
                    .append("eMailBox", geteMailBox())
                    .append("validFlag", getValidFlag())
                .toString();
    }

}
