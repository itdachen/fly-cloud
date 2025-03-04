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

    /**
     * 租户标识/公司标识
     */
    private String tenantId;

    /**
     * 部门编码
     */
    private String deptCode;

    /**
     * 上级部门编码
     */
    private String parentCode;

    /**
     * 部门名称
     */
    private String title;

    /**
     * 部门标志: Y-是;N-否(否的时候为行政区域, 例如: 贵州省贵阳市)
     */
    private String deptFlag;

    /**
     * 是否管理本级: Y-是;N-否
     */
    private String thatLevel;

    /**
     * 部门级次代码: 00-总部/10-省级/20-市州级/30-区县级/40-乡村级
     */
    private String levelCode;

    /**
     * 职能代码(dept_func表中 code)
     */
    private String funcCode;

    /**
     * 所属省级ID
     */
    private String provId;

    /**
     * 所属市州
     */
    private String cityId;

    /**
     * 所属区县
     */
    private String countyId;

    /**
     * 有效标志: Y-是;N-否
     */
    private String validFlag;

    /**
     * 删除标志:  Y-已删除;N-未删除
     */
    private String deleteFlag;


    public DeptInfoQuery() {
    }

    public DeptInfoQuery(int page, int limit, String tenantId, String deptCode, String parentCode, String title, String deptFlag, String thatLevel, String levelCode, String funcCode, String provId, String cityId, String countyId, String validFlag, String deleteFlag) {
        super(page, limit);
        this.tenantId = tenantId;
        this.deptCode = deptCode;
        this.parentCode = parentCode;
        this.title = title;
        this.deptFlag = deptFlag;
        this.thatLevel = thatLevel;
        this.levelCode = levelCode;
        this.funcCode = funcCode;
        this.provId = provId;
        this.cityId = cityId;
        this.countyId = countyId;
        this.validFlag = validFlag;
        this.deleteFlag = deleteFlag;
    }

    public static DeptInfoQueryBuilder builder() {
        return new DeptInfoQueryBuilder();
    }

    public static class DeptInfoQueryBuilder {
        private Integer page = 1;
        private Integer limit = 10;
        private String tenantId;
        private String deptCode;
        private String parentCode;
        private String title;
        private String deptFlag;
        private String thatLevel;
        private String levelCode;
        private String funcCode;
        private String provId;
        private String cityId;
        private String countyId;
        private String validFlag;
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

        /* 部门编码 */
        public DeptInfoQueryBuilder deptCode(String deptCode) {
            this.deptCode = deptCode;
            return this;
        }

        /* 上级部门编码 */
        public DeptInfoQueryBuilder parentCode(String parentCode) {
            this.parentCode = parentCode;
            return this;
        }

        /* 部门名称 */
        public DeptInfoQueryBuilder title(String title) {
            this.title = title;
            return this;
        }

        /* 部门标志: Y-是;N-否(否的时候为行政区域, 例如: 贵州省贵阳市) */
        public DeptInfoQueryBuilder deptFlag(String deptFlag) {
            this.deptFlag = deptFlag;
            return this;
        }

        /* 是否管理本级: Y-是;N-否 */
        public DeptInfoQueryBuilder thatLevel(String thatLevel) {
            this.thatLevel = thatLevel;
            return this;
        }

        /* 部门级次代码: 00-总部/10-省级/20-市州级/30-区县级/40-乡村级 */
        public DeptInfoQueryBuilder levelCode(String levelCode) {
            this.levelCode = levelCode;
            return this;
        }

        /* 职能代码(dept_func表中 code) */
        public DeptInfoQueryBuilder funcCode(String funcCode) {
            this.funcCode = funcCode;
            return this;
        }

        /* 所属省级ID */
        public DeptInfoQueryBuilder provId(String provId) {
            this.provId = provId;
            return this;
        }

        /* 所属市州 */
        public DeptInfoQueryBuilder cityId(String cityId) {
            this.cityId = cityId;
            return this;
        }

        /* 所属区县 */
        public DeptInfoQueryBuilder countyId(String countyId) {
            this.countyId = countyId;
            return this;
        }

        /* 有效标志: Y-是;N-否 */
        public DeptInfoQueryBuilder validFlag(String validFlag) {
            this.validFlag = validFlag;
            return this;
        }

        /* 删除标志:  Y-已删除;N-未删除 */
        public DeptInfoQueryBuilder deleteFlag(String deleteFlag) {
            this.deleteFlag = deleteFlag;
            return this;
        }

        public DeptInfoQuery build() {
            return new DeptInfoQuery(page, limit, tenantId, deptCode, parentCode, title, deptFlag, thatLevel, levelCode, funcCode, provId, cityId, countyId, validFlag, deleteFlag);
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

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDeptFlag(String deptFlag) {
        this.deptFlag = deptFlag;
    }

    public String getDeptFlag() {
        return deptFlag;
    }

    public void setThatLevel(String thatLevel) {
        this.thatLevel = thatLevel;
    }

    public String getThatLevel() {
        return thatLevel;
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

    public void setProvId(String provId) {
        this.provId = provId;
    }

    public String getProvId() {
        return provId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCountyId(String countyId) {
        this.countyId = countyId;
    }

    public String getCountyId() {
        return countyId;
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
                .append("parentCode", getParentCode())
                .append("title", getTitle())
                .append("deptFlag", getDeptFlag())
                .append("thatLevel", getThatLevel())
                .append("levelCode", getLevelCode())
                .append("funcCode", getFuncCode())
                .append("provId", getProvId())
                .append("cityId", getCityId())
                .append("countyId", getCountyId())
                .append("validFlag", getValidFlag())
                .append("deleteFlag", getDeleteFlag())
                .toString();
    }

}
