package com.github.itdachen.admin.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 部门信息
 *
 * @author 王大宸
 * @date 2024-12-22 22:30:09
 */
@Table(name = "fly_next_dept_info")
public class DeptInfo implements Serializable {
    private static final long serialVersionUID = 1L;


    /** 主键唯一标识 */
    @Id
    @Column(name = "id")
    private String id;

    /** 租户标识/公司标识 */
    @Column(name = "tenant_id")
    private String tenantId;

    /** 部门编码 */
    @Column(name = "dept_code")
    private String deptCode;

    /** 上级部门编码 */
    @Column(name = "parent_code")
    private String parentCode;

    /** 部门名称 */
    @Column(name = "title")
    private String title;

    /** 部门简称 */
    @Column(name = "title_as")
    private String titleAs;

    /** 部门标志: Y-是;N-否(否的时候为行政区域, 例如: 贵州省贵阳市) */
    @Column(name = "dept_flag")
    private String deptFlag;

    /** 是否管理本级: Y-是;N-否 */
    @Column(name = "that_level")
    private String thatLevel;

    /** 部门级次代码: 00-总部/10-省级/20-市州级/30-区县级/40-乡村级 */
    @Column(name = "level_code")
    private String levelCode;

    /** 部门级次名称 */
    @Column(name = "level_title")
    private String levelTitle;

    /** 职能代码(dept_func表中 code) */
    @Column(name = "func_code")
    private String funcCode;

    /** 职能名称(dept_func表中title) */
    @Column(name = "func_title")
    private String funcTitle;

    /** 联系电话 */
    @Column(name = "telephone")
    private String telephone;

    /** 电子邮箱 */
    @Column(name = "mail_box")
    private String mailBox;

    /** 传真 */
    @Column(name = "facsimile")
    private String facsimile;

    /** 所属省级ID */
    @Column(name = "prov_id")
    private String provId;

    /** 所属省级名称 */
    @Column(name = "prov_title")
    private String provTitle;

    /** 所属市州 */
    @Column(name = "city_id")
    private String cityId;

    /** 所属市州名称 */
    @Column(name = "city_title")
    private String cityTitle;

    /** 所属区县 */
    @Column(name = "county_id")
    private String countyId;

    /** 所属区县名称 */
    @Column(name = "county_title")
    private String countyTitle;

    /** 详细地址 */
    @Column(name = "address")
    private String address;

    /** 有效标志: Y-是;N-否 */
    @Column(name = "valid_flag")
    private String validFlag;

    /** 删除标志:  Y-已删除;N-未删除 */
    @Column(name = "delete_flag")
    private String deleteFlag;

    /** 备注 */
    @Column(name = "remarks")
    private String remarks;

    /** 创建时间 */
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /** 创建人 */
    @Column(name = "create_user")
    private String createUser;

    /** 创建人id */
    @Column(name = "create_user_id")
    private String createUserId;

    /** 更新时间 */
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    /** 更新人 */
    @Column(name = "update_user")
    private String updateUser;

    /** 更新人id */
    @Column(name = "update_user_id")
    private String updateUserId;





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

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("tenantId", getTenantId())
                .append("deptCode", getDeptCode())
                .append("parentCode", getParentCode())
                .append("title", getTitle())
                .append("titleAs", getTitleAs())
                .append("deptFlag", getDeptFlag())
                .append("thatLevel", getThatLevel())
                .append("levelCode", getLevelCode())
                .append("levelTitle", getLevelTitle())
                .append("funcCode", getFuncCode())
                .append("funcTitle", getFuncTitle())
                .append("telephone", getTelephone())
                .append("mailBox", getMailBox())
                .append("facsimile", getFacsimile())
                .append("provId", getProvId())
                .append("provTitle", getProvTitle())
                .append("cityId", getCityId())
                .append("cityTitle", getCityTitle())
                .append("countyId", getCountyId())
                .append("countyTitle", getCountyTitle())
                .append("address", getAddress())
                .append("validFlag", getValidFlag())
                .append("deleteFlag", getDeleteFlag())
                .append("remarks", getRemarks())
                .append("createTime", getCreateTime())
                .append("createUser", getCreateUser())
                .append("createUserId", getCreateUserId())
                .append("updateTime", getUpdateTime())
                .append("updateUser", getUpdateUser())
                .append("updateUserId", getUpdateUserId())
                .toString();
    }

}
