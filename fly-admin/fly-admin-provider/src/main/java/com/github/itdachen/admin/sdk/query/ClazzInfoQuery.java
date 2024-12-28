package com.github.itdachen.admin.sdk.query;

import com.github.itdachen.framework.core.biz.BizQuery;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


import java.io.Serializable;


/**
 * 岗位信息 查询参数
 *
 * @author 王大宸
 * @date 2024-12-28 20:42:52
 */
public class ClazzInfoQuery extends BizQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 租户ID
     */
   // private String tenantId;

    /**
     * 岗位代码
     */
    private String clazzCode;

    /**
     * 岗位名称, 例如: 信息中心-总部-主管领导岗
     */
    private String clazzTitle;

    /**
     * 部门等级代码
     */
    private String deptLevelCode;

    /**
     * 部门职能代码
     */
    private String deptFuncCode;

    /**
     * 岗位职能: 主管领导岗, 业务岗等
     */
    private String clazzFuncCode;

    /**
     * 部门专属岗: Y-是;N-否(暂时不用,备用)
     */
    private String deptFlag;

    /**
     * 部门代码(暂时不用,备用)
     */
    private String deptCode;

    /**
     * 部门名称(暂时不用,备用)
     */
    private String deptTitle;

    /**
     * 有效标志: Y-是;N-否
     */
    private String validFlag;

    /**
     * 删除标志: N-未删除;Y-已删除
     */
    private String deleteFlag;


    public ClazzInfoQuery() {
    }

    public ClazzInfoQuery(int page, int limit, String tenantId, String clazzCode, String clazzTitle, String deptLevelCode, String deptFuncCode, String clazzFuncCode, String deptFlag, String deptCode, String deptTitle, String validFlag, String deleteFlag) {
        super(page, limit);
        this.tenantId = tenantId;
        this.clazzCode = clazzCode;
        this.clazzTitle = clazzTitle;
        this.deptLevelCode = deptLevelCode;
        this.deptFuncCode = deptFuncCode;
        this.clazzFuncCode = clazzFuncCode;
        this.deptFlag = deptFlag;
        this.deptCode = deptCode;
        this.deptTitle = deptTitle;
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
        private String deptLevelCode;
        private String deptFuncCode;
        private String clazzFuncCode;
        private String deptFlag;
        private String deptCode;
        private String deptTitle;
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

        /* 部门等级代码 */
        public ClazzInfoQueryBuilder deptLevelCode(String deptLevelCode) {
            this.deptLevelCode = deptLevelCode;
            return this;
        }

        /* 部门职能代码 */
        public ClazzInfoQueryBuilder deptFuncCode(String deptFuncCode) {
            this.deptFuncCode = deptFuncCode;
            return this;
        }

        /* 岗位职能: 主管领导岗, 业务岗等 */
        public ClazzInfoQueryBuilder clazzFuncCode(String clazzFuncCode) {
            this.clazzFuncCode = clazzFuncCode;
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

        /* 部门名称(暂时不用,备用) */
        public ClazzInfoQueryBuilder deptTitle(String deptTitle) {
            this.deptTitle = deptTitle;
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
            return new ClazzInfoQuery(page, limit, tenantId, clazzCode, clazzTitle, deptLevelCode, deptFuncCode, clazzFuncCode, deptFlag, deptCode, deptTitle, validFlag, deleteFlag);
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

    public void setDeptLevelCode(String deptLevelCode) {
        this.deptLevelCode = deptLevelCode;
    }

    public String getDeptLevelCode() {
        return deptLevelCode;
    }

    public void setDeptFuncCode(String deptFuncCode) {
        this.deptFuncCode = deptFuncCode;
    }

    public String getDeptFuncCode() {
        return deptFuncCode;
    }

    public void setClazzFuncCode(String clazzFuncCode) {
        this.clazzFuncCode = clazzFuncCode;
    }

    public String getClazzFuncCode() {
        return clazzFuncCode;
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

    public void setDeptTitle(String deptTitle) {
        this.deptTitle = deptTitle;
    }

    public String getDeptTitle() {
        return deptTitle;
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
                .append("deptLevelCode", getDeptLevelCode())
                .append("deptFuncCode", getDeptFuncCode())
                .append("clazzFuncCode", getClazzFuncCode())
                .append("deptFlag", getDeptFlag())
                .append("deptCode", getDeptCode())
                .append("deptTitle", getDeptTitle())
                .append("validFlag", getValidFlag())
                .append("deleteFlag", getDeleteFlag())
                .toString();
    }

}
