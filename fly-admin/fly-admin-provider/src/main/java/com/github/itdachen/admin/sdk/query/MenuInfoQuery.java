package com.github.itdachen.admin.sdk.query;

import com.github.itdachen.framework.core.biz.BizQuery;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


import java.io.Serializable;


/**
 * 菜单信息 查询参数
 *
 * @author 王大宸
 * @date 2024-12-30 10:26:52
 */
public class MenuInfoQuery extends BizQuery implements Serializable {
    private static final long serialVersionUID = 1L;

            /** 应用ID/appId */
            private String appId;

            /** 上级ID */
            private String parentId;

            /** 认证编码 */
            private String authCode;

            /** 类型:dirt-目录;menu-菜单;uri-外链;third-第三方平台 */
            private String type;

            /** 菜单标题 */
            private String title;

            /** 是否展示/有效标志: Y-有效;N-无效 */
            private String validFlag;

            /** 菜单打开方式 */
            private String openType;

            /** 是否需要授权: Y-需要;N-不需要 */
            private String hasAuth;

            /** 是否需要白名单IP: Y-需要;N-不需要 */
            private String hasIp;



    public MenuInfoQuery() {
    }

    public MenuInfoQuery(int page, int limit, String appId, String parentId, String authCode, String type, String title, String validFlag, String openType, String hasAuth, String hasIp) {
        super(page, limit);
            this.appId = appId;
            this.parentId = parentId;
            this.authCode = authCode;
            this.type = type;
            this.title = title;
            this.validFlag = validFlag;
            this.openType = openType;
            this.hasAuth = hasAuth;
            this.hasIp = hasIp;
    }

    public static MenuInfoQueryBuilder builder() {
        return new MenuInfoQueryBuilder();
    }

    public static class MenuInfoQueryBuilder {
        private Integer page = 1;
        private Integer limit = 10;
            private String appId;
            private String parentId;
            private String authCode;
            private String type;
            private String title;
            private String validFlag;
            private String openType;
            private String hasAuth;
            private String hasIp;

        public MenuInfoQueryBuilder() {
        }

        public MenuInfoQueryBuilder page(Integer page) {
            this.page = page;
            return this;
        }

        public MenuInfoQueryBuilder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

            /* 应用ID/appId */
            public MenuInfoQueryBuilder appId(String appId) {
                this.appId = appId;
                return this;
            }
            /* 上级ID */
            public MenuInfoQueryBuilder parentId(String parentId) {
                this.parentId = parentId;
                return this;
            }
            /* 认证编码 */
            public MenuInfoQueryBuilder authCode(String authCode) {
                this.authCode = authCode;
                return this;
            }
            /* 类型:dirt-目录;menu-菜单;uri-外链;third-第三方平台 */
            public MenuInfoQueryBuilder type(String type) {
                this.type = type;
                return this;
            }
            /* 菜单标题 */
            public MenuInfoQueryBuilder title(String title) {
                this.title = title;
                return this;
            }
            /* 是否展示/有效标志: Y-有效;N-无效 */
            public MenuInfoQueryBuilder validFlag(String validFlag) {
                this.validFlag = validFlag;
                return this;
            }
            /* 菜单打开方式 */
            public MenuInfoQueryBuilder openType(String openType) {
                this.openType = openType;
                return this;
            }
            /* 是否需要授权: Y-需要;N-不需要 */
            public MenuInfoQueryBuilder hasAuth(String hasAuth) {
                this.hasAuth = hasAuth;
                return this;
            }
            /* 是否需要白名单IP: Y-需要;N-不需要 */
            public MenuInfoQueryBuilder hasIp(String hasIp) {
                this.hasIp = hasIp;
                return this;
            }

        public MenuInfoQuery build() {
            return new MenuInfoQuery(page, limit, appId, parentId, authCode, type, title, validFlag, openType, hasAuth, hasIp);
        }

    }



        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getAppId() {
            return appId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public String getParentId() {
            return parentId;
        }

        public void setAuthCode(String authCode) {
            this.authCode = authCode;
        }

        public String getAuthCode() {
            return authCode;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void setValidFlag(String validFlag) {
            this.validFlag = validFlag;
        }

        public String getValidFlag() {
            return validFlag;
        }

        public void setOpenType(String openType) {
            this.openType = openType;
        }

        public String getOpenType() {
            return openType;
        }

        public void setHasAuth(String hasAuth) {
            this.hasAuth = hasAuth;
        }

        public String getHasAuth() {
            return hasAuth;
        }

        public void setHasIp(String hasIp) {
            this.hasIp = hasIp;
        }

        public String getHasIp() {
            return hasIp;
        }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("page", getPage())
        .append("limit", getLimit())
                    .append("appId", getAppId())
                    .append("parentId", getParentId())
                    .append("authCode", getAuthCode())
                    .append("type", getType())
                    .append("title", getTitle())
                    .append("validFlag", getValidFlag())
                    .append("openType", getOpenType())
                    .append("hasAuth", getHasAuth())
                    .append("hasIp", getHasIp())
                .toString();
    }

}
