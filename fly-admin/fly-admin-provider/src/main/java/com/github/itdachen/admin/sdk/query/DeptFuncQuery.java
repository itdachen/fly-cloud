package com.github.itdachen.admin.sdk.query;

import com.github.itdachen.framework.core.biz.BizQuery;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


import java.io.Serializable;


/**
 * 部门职能 查询参数
 *
 * @author 王大宸
 * @date 2024-12-20 14:40:35
 */
public class DeptFuncQuery extends BizQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 编码(全局唯一)
     */
    private String funcCode;

    /**
     * 标题, 例如: 100-领导办公室;200-人事部;300-研发部
     */
    private String funcTitle;

    /**
     * 有效标志: Y-有效;N-无效
     */
    private String validFlag;

    /**
     * 删除标志:  Y-已删除;N-未删除
     */
    private String deleteFlag;


    public DeptFuncQuery() {
    }

    public DeptFuncQuery(int page, int limit, String tenantId, String funcCode, String funcTitle, String validFlag, String deleteFlag) {
        super(page, limit);
        this.tenantId = tenantId;
        this.funcCode = funcCode;
        this.funcTitle = funcTitle;
        this.validFlag = validFlag;
        this.deleteFlag = deleteFlag;
    }

    public static DeptFuncQueryBuilder builder() {
        return new DeptFuncQueryBuilder();
    }

    public static class DeptFuncQueryBuilder {
        private Integer page = 1;
        private Integer limit = 10;
        private String tenantId;
        private String funcCode;
        private String funcTitle;
        private String validFlag;
        private String deleteFlag;

        public DeptFuncQueryBuilder() {
        }

        public DeptFuncQueryBuilder page(Integer page) {
            this.page = page;
            return this;
        }

        public DeptFuncQueryBuilder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

        /* 租户ID */
        public DeptFuncQueryBuilder tenantId(String tenantId) {
            this.tenantId = tenantId;
            return this;
        }

        /* 编码(全局唯一) */
        public DeptFuncQueryBuilder funcCode(String funcCode) {
            this.funcCode = funcCode;
            return this;
        }

        /* 标题, 例如: 100-领导办公室;200-人事部;300-研发部 */
        public DeptFuncQueryBuilder funcTitle(String funcTitle) {
            this.funcTitle = funcTitle;
            return this;
        }

        /* 有效标志: Y-有效;N-无效 */
        public DeptFuncQueryBuilder validFlag(String validFlag) {
            this.validFlag = validFlag;
            return this;
        }

        /* 删除标志:  Y-已删除;N-未删除 */
        public DeptFuncQueryBuilder deleteFlag(String deleteFlag) {
            this.deleteFlag = deleteFlag;
            return this;
        }

        public DeptFuncQuery build() {
            return new DeptFuncQuery(page, limit, tenantId, funcCode, funcTitle, validFlag, deleteFlag);
        }

    }


    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setFuncCode(String funcCode) {
        this.funcCode = funcCode;
    }

    public String getFuncCode() {
        return funcCode;
    }

    public void setFuncTitle(String funcTitle) {
        this.funcTitle = funcTitle;
    }

    public String getFuncTitle() {
        return funcTitle;
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
                .append("funcCode", getFuncCode())
                .append("funcTitle", getFuncTitle())
                .append("validFlag", getValidFlag())
                .append("deleteFlag", getDeleteFlag())
                .toString();
    }


}
