package com.github.itdachen.auth.sdk.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


import java.io.Serializable;


/**
 * 人员身份菜单 VO
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:49
 */
public class AuthRoleMenuVO implements Serializable {
    private static final long serialVersionUID = 1L;

        /** ID */
        private String id;

        /** 租户ID */
        private String tenantId;

        /** 应用ID */
        private String appId;

        /** 人员身份ID */
        private String roleId;

        /** 菜单ID */
        private String menuId;



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

            public void setRoleId(String roleId) {
                this.roleId = roleId;
            }

            public String getRoleId() {
                return roleId;
            }

            public void setMenuId(String menuId) {
                this.menuId = menuId;
            }

            public String getMenuId() {
                return menuId;
            }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                    .append("id", getId())
                    .append("tenantId", getTenantId())
                    .append("appId", getAppId())
                    .append("roleId", getRoleId())
                    .append("menuId", getMenuId())
                .toString();
    }

}
