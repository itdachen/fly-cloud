package com.github.itdachen.auth.sdk.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


import java.io.Serializable;

/**
 * 岗位菜单 DTO
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:50
 */
public class AuthClazzMenuDTO implements Serializable {
    private static final long serialVersionUID = 1L;

        /** ID */
        private String id;

        /** 租户ID */
        private String tenantId;

        /** 应用ID */
 @NotBlank(message = "应用ID不能为空")
        private String appId;

        /** 岗位代码 */
 @NotBlank(message = "岗位代码不能为空")
        private String clazzCode;

        /** 岗位名称 */
 @NotBlank(message = "岗位名称不能为空")
        private String clazzTitle;

        /** 菜单ID/资源ID */
 @NotBlank(message = "菜单ID/资源ID不能为空")
        private String menuId;

        /** 菜单名称/资源名称 */
 @NotBlank(message = "菜单名称/资源名称不能为空")
        private String menuTitle;





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
            public void setMenuId(String menuId) {
                this.menuId = menuId;
            }

            public String getMenuId() {
                return menuId;
            }
            public void setMenuTitle(String menuTitle) {
                this.menuTitle = menuTitle;
            }

            public String getMenuTitle() {
                return menuTitle;
            }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                    .append("id", getId())
                    .append("tenantId", getTenantId())
                    .append("appId", getAppId())
                    .append("clazzCode", getClazzCode())
                    .append("clazzTitle", getClazzTitle())
                    .append("menuId", getMenuId())
                    .append("menuTitle", getMenuTitle())
                .toString();
    }

}
