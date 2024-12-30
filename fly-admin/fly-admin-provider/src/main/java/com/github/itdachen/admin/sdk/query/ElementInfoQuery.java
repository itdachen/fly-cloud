package com.github.itdachen.admin.sdk.query;

import com.github.itdachen.framework.core.biz.BizQuery;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


import java.io.Serializable;


/**
 * 资源(按钮)信息 查询参数
 *
 * @author 王大宸
 * @date 2024-12-30 10:26:53
 */
public class ElementInfoQuery extends BizQuery implements Serializable {
    private static final long serialVersionUID = 1L;

            /** 应用ID/appID */
            private String appId;

            /** 菜单ID */
            private String menuId;

            /** 标题 */
            private String title;

            /** 类型: button,uri */
            private String type;

            /** 是否展示/有效标志: Y-有效;N-无效 */
            private String validFlag;



    public ElementInfoQuery() {
    }

    public ElementInfoQuery(int page, int limit, String appId, String menuId, String title, String type, String validFlag) {
        super(page, limit);
            this.appId = appId;
            this.menuId = menuId;
            this.title = title;
            this.type = type;
            this.validFlag = validFlag;
    }

    public static ElementInfoQueryBuilder builder() {
        return new ElementInfoQueryBuilder();
    }

    public static class ElementInfoQueryBuilder {
        private Integer page = 1;
        private Integer limit = 10;
            private String appId;
            private String menuId;
            private String title;
            private String type;
            private String validFlag;

        public ElementInfoQueryBuilder() {
        }

        public ElementInfoQueryBuilder page(Integer page) {
            this.page = page;
            return this;
        }

        public ElementInfoQueryBuilder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

            /* 应用ID/appID */
            public ElementInfoQueryBuilder appId(String appId) {
                this.appId = appId;
                return this;
            }
            /* 菜单ID */
            public ElementInfoQueryBuilder menuId(String menuId) {
                this.menuId = menuId;
                return this;
            }
            /* 标题 */
            public ElementInfoQueryBuilder title(String title) {
                this.title = title;
                return this;
            }
            /* 类型: button,uri */
            public ElementInfoQueryBuilder type(String type) {
                this.type = type;
                return this;
            }
            /* 是否展示/有效标志: Y-有效;N-无效 */
            public ElementInfoQueryBuilder validFlag(String validFlag) {
                this.validFlag = validFlag;
                return this;
            }

        public ElementInfoQuery build() {
            return new ElementInfoQuery(page, limit, appId, menuId, title, type, validFlag);
        }

    }



        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getAppId() {
            return appId;
        }

        public void setMenuId(String menuId) {
            this.menuId = menuId;
        }

        public String getMenuId() {
            return menuId;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
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
                    .append("appId", getAppId())
                    .append("menuId", getMenuId())
                    .append("title", getTitle())
                    .append("type", getType())
                    .append("validFlag", getValidFlag())
                .toString();
    }

}
