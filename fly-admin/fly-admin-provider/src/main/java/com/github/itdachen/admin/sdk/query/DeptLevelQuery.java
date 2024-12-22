package com.github.itdachen.admin.sdk.query;

import com.github.itdachen.framework.core.biz.BizQuery;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


import java.io.Serializable;


/**
 * 部门等级管理 查询参数
 *
 * @author 王大宸
 * @date 2024-12-22 20:30:44
 */
public class DeptLevelQuery extends BizQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 层级代码
     */
    private String levelCode;

    /**
     * 层级名称,例如: 00-总部; 11-省级部门; 21-市州级部门; 31-区县级;41-乡镇级;51-社区/街道
     */
    private String levelTitle;

    /**
     * 有效标志: Y-是;N-否
     */
    private String validFlag;

    /**
     * 删除标志:  Y-已删除;N-未删除
     */
    private String deleteFlag;


    public DeptLevelQuery() {
    }

    public DeptLevelQuery(int page, int limit, String tenantId, String levelCode, String levelTitle, String validFlag, String deleteFlag) {
        super(page, limit);
        this.tenantId = tenantId;
        this.levelCode = levelCode;
        this.levelTitle = levelTitle;
        this.validFlag = validFlag;
        this.deleteFlag = deleteFlag;
    }

    public static DeptLevelQueryBuilder builder() {
        return new DeptLevelQueryBuilder();
    }

    public static class DeptLevelQueryBuilder {
        private Integer page = 1;
        private Integer limit = 10;
        private String tenantId;
        private String levelCode;
        private String levelTitle;
        private String validFlag;
        private String deleteFlag;

        public DeptLevelQueryBuilder() {
        }

        public DeptLevelQueryBuilder page(Integer page) {
            this.page = page;
            return this;
        }

        public DeptLevelQueryBuilder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

        /* 租户ID */
        public DeptLevelQueryBuilder tenantId(String tenantId) {
            this.tenantId = tenantId;
            return this;
        }

        /* 层级代码 */
        public DeptLevelQueryBuilder levelCode(String levelCode) {
            this.levelCode = levelCode;
            return this;
        }

        /* 层级名称,例如: 00-总部; 11-省级部门; 21-市州级部门; 31-区县级;41-乡镇级;51-社区/街道 */
        public DeptLevelQueryBuilder levelTitle(String levelTitle) {
            this.levelTitle = levelTitle;
            return this;
        }

        /* 有效标志: Y-是;N-否 */
        public DeptLevelQueryBuilder validFlag(String validFlag) {
            this.validFlag = validFlag;
            return this;
        }

        /* 删除标志:  Y-已删除;N-未删除 */
        public DeptLevelQueryBuilder deleteFlag(String deleteFlag) {
            this.deleteFlag = deleteFlag;
            return this;
        }

        public DeptLevelQuery build() {
            return new DeptLevelQuery(page, limit, tenantId, levelCode, levelTitle, validFlag, deleteFlag);
        }

    }


    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode;
    }

    public String getLevelCode() {
        return levelCode;
    }

    public void setLevelTitle(String levelTitle) {
        this.levelTitle = levelTitle;
    }

    public String getLevelTitle() {
        return levelTitle;
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
                .append("levelCode", getLevelCode())
                .append("levelTitle", getLevelTitle())
                .append("validFlag", getValidFlag())
                .append("deleteFlag", getDeleteFlag())
                .toString();
    }

}
