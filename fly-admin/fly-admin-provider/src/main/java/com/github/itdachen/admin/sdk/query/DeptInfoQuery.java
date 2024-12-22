package com.github.itdachen.admin.sdk.query;

import com.github.itdachen.framework.core.biz.BizQuery;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


import java.io.Serializable;


/**
 * 部门信息 查询参数
 *
 * @author 王大宸
 * @date 2024-12-22 22:30:09
 */
public class DeptInfoQuery extends BizQuery implements Serializable {
    private static final long serialVersionUID = 1L;

            /** 租户标识/公司标识 */
            private String tenantId;

            /** 上级ID */
            private String parentId;

            /** 部门名称 */
            private String title;

            /** 删除标志:  Y-已删除;N-未删除 */
            private String deleteFlag;



    public DeptInfoQuery() {
    }

    public DeptInfoQuery(int page, int limit, String tenantId, String parentId, String title, String deleteFlag) {
        super(page, limit);
            this.tenantId = tenantId;
            this.parentId = parentId;
            this.title = title;
            this.deleteFlag = deleteFlag;
    }

    public static DeptInfoQueryBuilder builder() {
        return new DeptInfoQueryBuilder();
    }

    public static class DeptInfoQueryBuilder {
        private Integer page = 1;
        private Integer limit = 10;
            private String tenantId;
            private String parentId;
            private String title;
            private String deleteFlag;

        public DeptInfoQueryBuilder() {
        }

        public DeptInfoQueryBuilder page(Integer page) {
            this.page = page;
            return this;
        }

        public DeptInfoQueryBuilder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

            /* 租户标识/公司标识 */
            public DeptInfoQueryBuilder tenantId(String tenantId) {
                this.tenantId = tenantId;
                return this;
            }
            /* 上级ID */
            public DeptInfoQueryBuilder parentId(String parentId) {
                this.parentId = parentId;
                return this;
            }
            /* 部门名称 */
            public DeptInfoQueryBuilder title(String title) {
                this.title = title;
                return this;
            }
            /* 删除标志:  Y-已删除;N-未删除 */
            public DeptInfoQueryBuilder deleteFlag(String deleteFlag) {
                this.deleteFlag = deleteFlag;
                return this;
            }

        public DeptInfoQuery build() {
            return new DeptInfoQuery(page, limit, tenantId, parentId, title, deleteFlag);
        }

    }



        public void setTenantId(String tenantId) {
            this.tenantId = tenantId;
        }

        public String getTenantId() {
            return tenantId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public String getParentId() {
            return parentId;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
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
                    .append("parentId", getParentId())
                    .append("title", getTitle())
                    .append("deleteFlag", getDeleteFlag())
                .toString();
    }

}
