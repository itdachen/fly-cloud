package com.github.itdachen.admin.sdk.query;

import com.github.itdachen.framework.core.biz.BizQuery;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


import java.io.Serializable;


/**
 * 岗位信息 查询参数
 *
 * @author 王大宸
 * @date 2024-12-28 18:40:48
 */
public class ClazzInfoQuery extends BizQuery implements Serializable {
    private static final long serialVersionUID = 1L;

            /** 租户ID */
            private String tenantId;

            /** 岗位代码 */
            private String clazzCode;

            /** 岗位名称, 例如: 信息中心-总部-主管领导岗 */
            private String clazzTitle;

            /** 部门专属岗: Y-是;N-否(暂时不用,备用) */
            private String deptFlag;

            /** 部门代码(暂时不用,备用) */
            private String deptCode;

            /** 岗位等级代码 */
            private String levelCode;

            /** 岗位职能: 主管领导岗, 业务岗等 */
            private String funcCode;

            /** 有效标志: Y-是;N-否 */
            private String validFlag;

            /** 删除标志: N-未删除;Y-已删除 */
            private String deleteFlag;



    public ClazzInfoQuery() {
    }

    public ClazzInfoQuery(int page, int limit, String tenantId, String clazzCode, String clazzTitle, String deptFlag, String deptCode, String levelCode, String funcCode, String validFlag, String deleteFlag) {
        super(page, limit);
            this.tenantId = tenantId;
            this.clazzCode = clazzCode;
            this.clazzTitle = clazzTitle;
            this.deptFlag = deptFlag;
            this.deptCode = deptCode;
            this.levelCode = levelCode;
            this.funcCode = funcCode;
            this.validFlag = validFlag;
            this.deleteFlag = deleteFlag;
    }

    public static ClazzInfoQueryBuilder builder() {
        return new ClazzInfoQueryBuilder();
    }

    public static class ClazzInfoQueryBuilder {
        private Integer page = 1;
        private Integer limit = 10;
            private String tenantId;
            private String clazzCode;
            private String clazzTitle;
            private String deptFlag;
            private String deptCode;
            private String levelCode;
            private String funcCode;
            private String validFlag;
            private String deleteFlag;

        public ClazzInfoQueryBuilder() {
        }

        public ClazzInfoQueryBuilder page(Integer page) {
            this.page = page;
            return this;
        }

        public ClazzInfoQueryBuilder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

            /* 租户ID */
            public ClazzInfoQueryBuilder tenantId(String tenantId) {
                this.tenantId = tenantId;
                return this;
            }
            /* 岗位代码 */
            public ClazzInfoQueryBuilder clazzCode(String clazzCode) {
                this.clazzCode = clazzCode;
                return this;
            }
            /* 岗位名称, 例如: 信息中心-总部-主管领导岗 */
            public ClazzInfoQueryBuilder clazzTitle(String clazzTitle) {
                this.clazzTitle = clazzTitle;
                return this;
            }
            /* 部门专属岗: Y-是;N-否(暂时不用,备用) */
            public ClazzInfoQueryBuilder deptFlag(String deptFlag) {
                this.deptFlag = deptFlag;
                return this;
            }
            /* 部门代码(暂时不用,备用) */
            public ClazzInfoQueryBuilder deptCode(String deptCode) {
                this.deptCode = deptCode;
                return this;
            }
            /* 岗位等级代码 */
            public ClazzInfoQueryBuilder levelCode(String levelCode) {
                this.levelCode = levelCode;
                return this;
            }
            /* 岗位职能: 主管领导岗, 业务岗等 */
            public ClazzInfoQueryBuilder funcCode(String funcCode) {
                this.funcCode = funcCode;
                return this;
            }
            /* 有效标志: Y-是;N-否 */
            public ClazzInfoQueryBuilder validFlag(String validFlag) {
                this.validFlag = validFlag;
                return this;
            }
            /* 删除标志: N-未删除;Y-已删除 */
            public ClazzInfoQueryBuilder deleteFlag(String deleteFlag) {
                this.deleteFlag = deleteFlag;
                return this;
            }

        public ClazzInfoQuery build() {
            return new ClazzInfoQuery(page, limit, tenantId, clazzCode, clazzTitle, deptFlag, deptCode, levelCode, funcCode, validFlag, deleteFlag);
        }

    }



        public void setTenantId(String tenantId) {
            this.tenantId = tenantId;
        }

        public String getTenantId() {
            return tenantId;
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

        public void setDeptFlag(String deptFlag) {
            this.deptFlag = deptFlag;
        }

        public String getDeptFlag() {
            return deptFlag;
        }

        public void setDeptCode(String deptCode) {
            this.deptCode = deptCode;
        }

        public String getDeptCode() {
            return deptCode;
        }

        public void setLevelCode(String levelCode) {
            this.levelCode = levelCode;
        }

        public String getLevelCode() {
            return levelCode;
        }

        public void setFuncCode(String funcCode) {
            this.funcCode = funcCode;
        }

        public String getFuncCode() {
            return funcCode;
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
                    .append("clazzCode", getClazzCode())
                    .append("clazzTitle", getClazzTitle())
                    .append("deptFlag", getDeptFlag())
                    .append("deptCode", getDeptCode())
                    .append("levelCode", getLevelCode())
                    .append("funcCode", getFuncCode())
                    .append("validFlag", getValidFlag())
                    .append("deleteFlag", getDeleteFlag())
                .toString();
    }

}
