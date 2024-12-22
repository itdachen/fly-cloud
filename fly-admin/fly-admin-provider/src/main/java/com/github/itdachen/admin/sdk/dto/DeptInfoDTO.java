package com.github.itdachen.admin.sdk.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


import java.io.Serializable;

/**
 * 部门信息 DTO
 *
 * @author 王大宸
 * @date 2024-12-22 22:30:09
 */
public class DeptInfoDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键唯一标识
     */
    private String id;

    /**
     * 租户标识/公司标识
     */
    private String tenantId;

    /**
     * 上级ID
     */
    @NotBlank(message = "上级ID不能为空")
    private String parentId;

    /**
     * 部门名称
     */
    @NotBlank(message = "部门名称不能为空")
    private String title;

    /**
     * 部门简称
     */
    //  @NotBlank(message = "部门简称不能为空")
    private String titleAs;

    /**
     * 部门标志: Y-是;N-否
     */
    //  @NotBlank(message = "部门标志: Y-是;N-否不能为空")
    private String deptFlag;

    /**
     * 是否管理本级: Y-是;N-否
     */
    //  @NotBlank(message = "是否管理本级: Y-是;N-否不能为空")
    private String thatLevel;

    /**
     * 部门级次代码: 00-总部/10-省级/20-市州级/30-区县级/40-乡村级
     */
    @NotBlank(message = "部门级次不能为空")
    private String levelCode;

    /**
     * 部门级次名称
     */
    //  @NotBlank(message = "部门级次名称不能为空")
    private String levelTitle;

    /**
     * 联系电话
     */
    // @NotBlank(message = "联系电话不能为空")
    private String telephone;

    /**
     * 电子邮箱
     */
    //  @NotBlank(message = "电子邮箱不能为空")
    private String mailBox;

    /**
     * 传真
     */
    // @NotBlank(message = "传真不能为空")
    private String facsimile;

    /**
     * 职能代码(dept_func表中 code)
     */
    // @NotBlank(message = "职能代码(dept_func表中 code)不能为空")
    private String funcCode;

    /**
     * 职能名称(dept_func表中title)
     */
    // @NotBlank(message = "职能名称(dept_func表中title)不能为空")
    private String funcTitle;

    /**
     * 所属省级ID
     */
    //  @NotBlank(message = "所属省级ID不能为空")
    private String provId;

    /**
     * 所属省级名称
     */
    // @NotBlank(message = "所属省级名称不能为空")
    private String provTitle;

    /**
     * 所属市州
     */
    // @NotBlank(message = "所属市州不能为空")
    private String cityId;

    /**
     * 所属市州名称
     */
    // @NotBlank(message = "所属市州名称不能为空")
    private String cityTitle;

    /**
     * 所属区县
     */
    //  @NotBlank(message = "所属区县不能为空")
    private String countyId;

    /**
     * 所属区县名称
     */
    // @NotBlank(message = "所属区县名称不能为空")
    private String countyTitle;

    /**
     * 详细地址
     */
    // @NotBlank(message = "详细地址不能为空")
    private String address;

    /**
     * 有效标志: Y-是;N-否
     */
    // @NotBlank(message = "有效标志: Y-是;N-否不能为空")
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

    public void setTitleAs(String titleAs) {
        this.titleAs = titleAs;
    }

    public String getTitleAs() {
        return titleAs;
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

    public void setLevelTitle(String levelTitle) {
        this.levelTitle = levelTitle;
    }

    public String getLevelTitle() {
        return levelTitle;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setMailBox(String mailBox) {
        this.mailBox = mailBox;
    }

    public String getMailBox() {
        return mailBox;
    }

    public void setFacsimile(String facsimile) {
        this.facsimile = facsimile;
    }

    public String getFacsimile() {
        return facsimile;
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

    public void setProvId(String provId) {
        this.provId = provId;
    }

    public String getProvId() {
        return provId;
    }

    public void setProvTitle(String provTitle) {
        this.provTitle = provTitle;
    }

    public String getProvTitle() {
        return provTitle;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityTitle(String cityTitle) {
        this.cityTitle = cityTitle;
    }

    public String getCityTitle() {
        return cityTitle;
    }

    public void setCountyId(String countyId) {
        this.countyId = countyId;
    }

    public String getCountyId() {
        return countyId;
    }

    public void setCountyTitle(String countyTitle) {
        this.countyTitle = countyTitle;
    }

    public String getCountyTitle() {
        return countyTitle;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
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
                .append("parentId", getParentId())
                .append("title", getTitle())
                .append("titleAs", getTitleAs())
                .append("deptFlag", getDeptFlag())
                .append("thatLevel", getThatLevel())
                .append("levelCode", getLevelCode())
                .append("levelTitle", getLevelTitle())
                .append("telephone", getTelephone())
                .append("mailBox", getMailBox())
                .append("facsimile", getFacsimile())
                .append("funcCode", getFuncCode())
                .append("funcTitle", getFuncTitle())
                .append("provId", getProvId())
                .append("provTitle", getProvTitle())
                .append("cityId", getCityId())
                .append("cityTitle", getCityTitle())
                .append("countyId", getCountyId())
                .append("countyTitle", getCountyTitle())
                .append("address", getAddress())
                .append("validFlag", getValidFlag())
                .append("remarks", getRemarks())
                .toString();
    }


}
