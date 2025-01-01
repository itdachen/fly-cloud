package com.github.itdachen.auth.sdk.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


import java.io.Serializable;


/**
 * 身份岗位管理表 VO
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:50
 */
public class AuthClazzRoleVO implements Serializable {
    private static final long serialVersionUID = 1L;

        /** 主键唯一标识 */
        private String id;

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

        /** 身份名称 */
        private String roleTitle;

        /** 有效标志: Y-是;N-否 */
        private String validFlag;

        /** 备注 */
        private String remarks;



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

            public void setRoleTitle(String roleTitle) {
                this.roleTitle = roleTitle;
            }

            public String getRoleTitle() {
                return roleTitle;
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


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                    .append("id", getId())
                    .append("tenantId", getTenantId())
                    .append("appId", getAppId())
                    .append("clazzCode", getClazzCode())
                    .append("clazzTitle", getClazzTitle())
                    .append("roleId", getRoleId())
                    .append("roleTitle", getRoleTitle())
                    .append("validFlag", getValidFlag())
                    .append("remarks", getRemarks())
                .toString();
    }

}
