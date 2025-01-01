package com.github.itdachen.auth.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;



/**
 * 岗位菜单
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:50
 */
@Table(name = "fly_next_auth_clazz_menu")
public class AuthClazzMenu implements Serializable{
private static final long serialVersionUID=1L;

    /** ID */
    @Id
    @Column(name = "id")
    private String id;

    /** 租户ID */
    @Column(name = "tenant_id")
    private String tenantId;

    /** 应用ID */
    @Column(name = "app_id")
    private String appId;

    /** 岗位代码 */
    @Column(name = "clazz_code")
    private String clazzCode;

    /** 岗位名称 */
    @Column(name = "clazz_title")
    private String clazzTitle;

    /** 菜单ID/资源ID */
    @Column(name = "menu_id")
    private String menuId;

    /** 菜单名称/资源名称 */
    @Column(name = "menu_title")
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
