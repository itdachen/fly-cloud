package com.github.itdachen.auth.sdk.query;

import com.github.itdachen.framework.core.biz.BizQuery;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


import java.io.Serializable;


/**
 * 岗位菜单 查询参数
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:50
 */
public class AuthClazzMenuQuery extends BizQuery implements Serializable {
    private static final long serialVersionUID = 1L;

            /** 租户ID */
            private String tenantId;

            /** 应用ID */
            private String appId;

            /** 岗位代码 */
            private String clazzCode;

            /** 岗位名称 */
            private String clazzTitle;

            /** 菜单ID/资源ID */
            private String menuId;

            /** 菜单名称/资源名称 */
            private String menuTitle;



    public AuthClazzMenuQuery() {
    }

    public AuthClazzMenuQuery(int page, int limit, String tenantId, String appId, String clazzCode, String clazzTitle, String menuId, String menuTitle) {
        super(page, limit);
            this.tenantId = tenantId;
            this.appId = appId;
            this.clazzCode = clazzCode;
            this.clazzTitle = clazzTitle;
            this.menuId = menuId;
            this.menuTitle = menuTitle;
    }

    public static AuthClazzMenuQueryBuilder builder() {
        return new AuthClazzMenuQueryBuilder();
    }

    public static class AuthClazzMenuQueryBuilder {
        private Integer page = 1;
        private Integer limit = 10;
            private String tenantId;
            private String appId;
            private String clazzCode;
            private String clazzTitle;
            private String menuId;
            private String menuTitle;

        public AuthClazzMenuQueryBuilder() {
        }

        public AuthClazzMenuQueryBuilder page(Integer page) {
            this.page = page;
            return this;
        }

        public AuthClazzMenuQueryBuilder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

            /* 租户ID */
            public AuthClazzMenuQueryBuilder tenantId(String tenantId) {
                this.tenantId = tenantId;
                return this;
            }
            /* 应用ID */
            public AuthClazzMenuQueryBuilder appId(String appId) {
                this.appId = appId;
                return this;
            }
            /* 岗位代码 */
            public AuthClazzMenuQueryBuilder clazzCode(String clazzCode) {
                this.clazzCode = clazzCode;
                return this;
            }
            /* 岗位名称 */
            public AuthClazzMenuQueryBuilder clazzTitle(String clazzTitle) {
                this.clazzTitle = clazzTitle;
                return this;
            }
            /* 菜单ID/资源ID */
            public AuthClazzMenuQueryBuilder menuId(String menuId) {
                this.menuId = menuId;
                return this;
            }
            /* 菜单名称/资源名称 */
            public AuthClazzMenuQueryBuilder menuTitle(String menuTitle) {
                this.menuTitle = menuTitle;
                return this;
            }

        public AuthClazzMenuQuery build() {
            return new AuthClazzMenuQuery(page, limit, tenantId, appId, clazzCode, clazzTitle, menuId, menuTitle);
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
        .append("page", getPage())
        .append("limit", getLimit())
                    .append("tenantId", getTenantId())
                    .append("appId", getAppId())
                    .append("clazzCode", getClazzCode())
                    .append("clazzTitle", getClazzTitle())
                    .append("menuId", getMenuId())
                    .append("menuTitle", getMenuTitle())
                .toString();
    }

}
