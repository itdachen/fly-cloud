package com.github.itdachen.admin.sdk.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


import java.io.Serializable;


/**
 * 岗位信息 VO
 *
 * @author 王大宸
 * @date 2024-12-28 18:40:48
 */
public class ClazzInfoVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键唯一标识
     */
    private String id;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 岗位代码
     */
    private String clazzCode;

    /**
     * 岗位名称, 例如: 信息中心-总部-主管领导岗
     */
    private String clazzTitle;

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
     * 岗位等级代码
     */
    private String levelCode;

    /**
     * 岗位等级名称
     */
    private String levelTitle;

    /**
     * 岗位职能: 主管领导岗, 业务岗等
     */
    private String funcCode;

    /**
     * 岗位职能名称: 主管领导岗, 业务岗等
     */
    private String funcTitle;

    /**
     * 有效标志: Y-是;N-否
     */
    private String validFlag;

    /**
     * 备注
     */
    private String remarks;


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
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

    public void setDeptTitle(String deptTitle) {
        this.deptTitle = deptTitle;
    }

    public String getDeptTitle() {
        return deptTitle;
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

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRemarks() {
        return remarks;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("tenantId", getTenantId())
                .append("clazzCode", getClazzCode())
                .append("clazzTitle", getClazzTitle())
                .append("deptFlag", getDeptFlag())
                .append("deptCode", getDeptCode())
                .append("deptTitle", getDeptTitle())
                .append("levelCode", getLevelCode())
                .append("levelTitle", getLevelTitle())
                .append("funcCode", getFuncCode())
                .append("funcTitle", getFuncTitle())
                .append("validFlag", getValidFlag())
                .append("remarks", getRemarks())
                .toString();
    }

}
