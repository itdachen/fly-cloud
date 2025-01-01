package com.github.itdachen.auth.sdk.query;

import com.github.itdachen.framework.core.biz.BizQuery;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


import java.io.Serializable;


/**
 * 身份岗位管理表 查询参数
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:50
 */
public class AuthClazzRoleQuery extends BizQuery implements Serializable {
    private static final long serialVersionUID = 1L;

            /** 租户ID */
            private String tenantId;

            /** 应用ID */
            private String appId;

            /** 岗位代码 */
            private String clazzCode;

            /** 岗位名称 */
            private String clazzTitle;

            /** 身份ID */
            private String roleId;

            /** 有效标志: Y-是;N-否 */
            private String validFlag;

            /** 删除标志: Y-已删除;N-未删除 */
            private String deleteFlag;



    public AuthClazzRoleQuery() {
    }

    public AuthClazzRoleQuery(int page, int limit, String tenantId, String appId, String clazzCode, String clazzTitle, String roleId, String validFlag, String deleteFlag) {
        super(page, limit);
            this.tenantId = tenantId;
            this.appId = appId;
            this.clazzCode = clazzCode;
            this.clazzTitle = clazzTitle;
            this.roleId = roleId;
            this.validFlag = validFlag;
            this.deleteFlag = deleteFlag;
    }

    public static AuthClazzRoleQueryBuilder builder() {
        return new AuthClazzRoleQueryBuilder();
    }

    public static class AuthClazzRoleQueryBuilder {
        private Integer page = 1;
        private Integer limit = 10;
            private String tenantId;
            private String appId;
            private String clazzCode;
            private String clazzTitle;
            private String roleId;
            private String validFlag;
            private String deleteFlag;

        public AuthClazzRoleQueryBuilder() {
        }

        public AuthClazzRoleQueryBuilder page(Integer page) {
            this.page = page;
            return this;
        }

        public AuthClazzRoleQueryBuilder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

            /* 租户ID */
            public AuthClazzRoleQueryBuilder tenantId(String tenantId) {
                this.tenantId = tenantId;
                return this;
            }
            /* 应用ID */
            public AuthClazzRoleQueryBuilder appId(String appId) {
                this.appId = appId;
                return this;
            }
            /* 岗位代码 */
            public AuthClazzRoleQueryBuilder clazzCode(String clazzCode) {
                this.clazzCode = clazzCode;
                return this;
            }
            /* 岗位名称 */
            public AuthClazzRoleQueryBuilder clazzTitle(String clazzTitle) {
                this.clazzTitle = clazzTitle;
                return this;
            }
            /* 身份ID */
            public AuthClazzRoleQueryBuilder roleId(String roleId) {
                this.roleId = roleId;
                return this;
            }
            /* 有效标志: Y-是;N-否 */
            public AuthClazzRoleQueryBuilder validFlag(String validFlag) {
                this.validFlag = validFlag;
                return this;
            }
            /* 删除标志: Y-已删除;N-未删除 */
            public AuthClazzRoleQueryBuilder deleteFlag(String deleteFlag) {
                this.deleteFlag = deleteFlag;
                return this;
            }

        public AuthClazzRoleQuery build() {
            return new AuthClazzRoleQuery(page, limit, tenantId, appId, clazzCode, clazzTitle, roleId, validFlag, deleteFlag);
        }

    }



        public void setTenantId(String tenantId) {
            this.tenantId = tenantId;
        }

        public String getTenantId() {
            return tenantId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getAppId() {
            return appId;
        }

        public void setClazzCode(String clazzCode) {
            this.clazzCode = clazzCode;
        }

        public String getClazzCode() {
            return clazzCode;
        }

        public void setClazzTitle(String clazzTitle) {
            this.clazzTitle = clazzTitle;
        }

        public String getClazzTitle() {
            return clazzTitle;
        }

        public void setRoleId(String roleId) {
            this.roleId = roleId;
        }

        public String getRoleId() {
            return roleId;
        }

        public void setValidFlag(String validFlag) {
            this.validFlag = validFlag;
        }

        public String getValidFlag() {
            return validFlag;
        }

        public void setDeleteFlag(String deleteFlag) {
            this.deleteFlag = deleteFlag;
        }

        public String getDeleteFlag() {
            return deleteFlag;
        }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("page", getPage())
        .append("limit", getLimit())
                    .append("tenantId", getTenantId())
                    .append("appId", getAppId())
                    .append("clazzCode", getClazzCode())
                    .append("clazzTitle", getClazzTitle())
                    .append("roleId", getRoleId())
                    .append("validFlag", getValidFlag())
                    .append("deleteFlag", getDeleteFlag())
                .toString();
    }

}
