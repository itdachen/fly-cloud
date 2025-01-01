package com.github.itdachen.auth.sdk.query;

import com.github.itdachen.framework.core.biz.BizQuery;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


import java.io.Serializable;


/**
 * 部门岗位关联表 查询参数
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:50
 */
public class AuthClazzDeptQuery extends BizQuery implements Serializable {
    private static final long serialVersionUID = 1L;

            /** 租户ID */
            private String tenantId;

            /** 部门代码 */
            private String deptCode;

            /** 部门名称 */
            private String deptTitle;

            /** 部门层级 */
            private String deptLevel;

            /** 岗位代码 */
            private String clazzCode;

            /** 岗位名称 */
            private String clazzTitle;

            /** 有效标志: Y-是;N-否 */
            private String validFlag;

            /** 删除标志: Y-已删除;N-未删除 */
            private String deleteFlag;



    public AuthClazzDeptQuery() {
    }

    public AuthClazzDeptQuery(int page, int limit, String tenantId, String deptCode, String deptTitle, String deptLevel, String clazzCode, String clazzTitle, String validFlag, String deleteFlag) {
        super(page, limit);
            this.tenantId = tenantId;
            this.deptCode = deptCode;
            this.deptTitle = deptTitle;
            this.deptLevel = deptLevel;
            this.clazzCode = clazzCode;
            this.clazzTitle = clazzTitle;
            this.validFlag = validFlag;
            this.deleteFlag = deleteFlag;
    }

    public static AuthClazzDeptQueryBuilder builder() {
        return new AuthClazzDeptQueryBuilder();
    }

    public static class AuthClazzDeptQueryBuilder {
        private Integer page = 1;
        private Integer limit = 10;
            private String tenantId;
            private String deptCode;
            private String deptTitle;
            private String deptLevel;
            private String clazzCode;
            private String clazzTitle;
            private String validFlag;
            private String deleteFlag;

        public AuthClazzDeptQueryBuilder() {
        }

        public AuthClazzDeptQueryBuilder page(Integer page) {
            this.page = page;
            return this;
        }

        public AuthClazzDeptQueryBuilder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

            /* 租户ID */
            public AuthClazzDeptQueryBuilder tenantId(String tenantId) {
                this.tenantId = tenantId;
                return this;
            }
            /* 部门代码 */
            public AuthClazzDeptQueryBuilder deptCode(String deptCode) {
                this.deptCode = deptCode;
                return this;
            }
            /* 部门名称 */
            public AuthClazzDeptQueryBuilder deptTitle(String deptTitle) {
                this.deptTitle = deptTitle;
                return this;
            }
            /* 部门层级 */
            public AuthClazzDeptQueryBuilder deptLevel(String deptLevel) {
                this.deptLevel = deptLevel;
                return this;
            }
            /* 岗位代码 */
            public AuthClazzDeptQueryBuilder clazzCode(String clazzCode) {
                this.clazzCode = clazzCode;
                return this;
            }
            /* 岗位名称 */
            public AuthClazzDeptQueryBuilder clazzTitle(String clazzTitle) {
                this.clazzTitle = clazzTitle;
                return this;
            }
            /* 有效标志: Y-是;N-否 */
            public AuthClazzDeptQueryBuilder validFlag(String validFlag) {
                this.validFlag = validFlag;
                return this;
            }
            /* 删除标志: Y-已删除;N-未删除 */
            public AuthClazzDeptQueryBuilder deleteFlag(String deleteFlag) {
                this.deleteFlag = deleteFlag;
                return this;
            }

        public AuthClazzDeptQuery build() {
            return new AuthClazzDeptQuery(page, limit, tenantId, deptCode, deptTitle, deptLevel, clazzCode, clazzTitle, validFlag, deleteFlag);
        }

    }



        public void setTenantId(String tenantId) {
            this.tenantId = tenantId;
        }

        public String getTenantId() {
            return tenantId;
        }

        public void setDeptCode(String deptCode) {
            this.deptCode = deptCode;
        }

        public String getDeptCode() {
            return deptCode;
        }

        public void setDeptTitle(String deptTitle) {
            this.deptTitle = deptTitle;
        }

        public String getDeptTitle() {
            return deptTitle;
        }

        public void setDeptLevel(String deptLevel) {
            this.deptLevel = deptLevel;
        }

        public String getDeptLevel() {
            return deptLevel;
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
                    .append("deptCode", getDeptCode())
                    .append("deptTitle", getDeptTitle())
                    .append("deptLevel", getDeptLevel())
                    .append("clazzCode", getClazzCode())
                    .append("clazzTitle", getClazzTitle())
                    .append("validFlag", getValidFlag())
                    .append("deleteFlag", getDeleteFlag())
                .toString();
    }

}
