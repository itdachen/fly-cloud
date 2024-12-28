package com.github.itdachen.admin.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 岗位信息
 *
 * @author 王大宸
 * @date 2024-12-28 20:42:52
 */
@Table(name = "fly_next_clazz_info")
public class ClazzInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键唯一标识
     */
    @Id
    @Column(name = "id")
    private String id;

    /**
     * 租户ID
     */
    @Column(name = "tenant_id")
    private String tenantId;

    /**
     * 岗位代码
     */
    @Column(name = "clazz_code")
    private String clazzCode;

    /**
     * 岗位名称, 例如: 信息中心-总部-主管领导岗
     */
    @Column(name = "clazz_title")
    private String clazzTitle;

    /**
     * 部门等级代码
     */
    @Column(name = "dept_level_code")
    private String deptLevelCode;

    /**
     * 部门等级名称
     */
    @Column(name = "dept_level_title")
    private String deptLevelTitle;

    /**
     * 部门职能代码
     */
    @Column(name = "dept_func_code")
    private String deptFuncCode;

    /**
     * 部门职能名称
     */
    @Column(name = "dept_func_title")
    private String deptFuncTitle;

    /**
     * 岗位职能: 主管领导岗, 业务岗等
     */
    @Column(name = "clazz_func_code")
    private String clazzFuncCode;

    /**
     * 岗位职能名称: 主管领导岗, 业务岗等
     */
    @Column(name = "clazz_func_title")
    private String clazzFuncTitle;

    /**
     * 部门专属岗: Y-是;N-否(暂时不用,备用)
     */
    @Column(name = "dept_flag")
    private String deptFlag;

    /**
     * 部门代码(暂时不用,备用)
     */
    @Column(name = "dept_code")
    private String deptCode;

    /**
     * 部门名称(暂时不用,备用)
     */
    @Column(name = "dept_title")
    private String deptTitle;

    /**
     * 有效标志: Y-是;N-否
     */
    @Column(name = "valid_flag")
    private String validFlag;

    /**
     * 备注
     */
    @Column(name = "remarks")
    private String remarks;

    /**
     * 删除标志: N-未删除;Y-已删除
     */
    @Column(name = "delete_flag")
    private String deleteFlag;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @Column(name = "create_user")
    private String createUser;

    /**
     * 创建人id
     */
    @Column(name = "create_user_id")
    private String createUserId;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    /**
     * 更新人
     */
    @Column(name = "update_user")
    private String updateUser;

    /**
     * 更新人id
     */
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

    public void setDeptLevelTitle(String deptLevelTitle) {
        this.deptLevelTitle = deptLevelTitle;
    }

    public String getDeptLevelTitle() {
        return deptLevelTitle;
    }

    public void setDeptFuncCode(String deptFuncCode) {
        this.deptFuncCode = deptFuncCode;
    }

    public String getDeptFuncCode() {
        return deptFuncCode;
    }

    public void setDeptFuncTitle(String deptFuncTitle) {
        this.deptFuncTitle = deptFuncTitle;
    }

    public String getDeptFuncTitle() {
        return deptFuncTitle;
    }

    public void setClazzFuncCode(String clazzFuncCode) {
        this.clazzFuncCode = clazzFuncCode;
    }

    public String getClazzFuncCode() {
        return clazzFuncCode;
    }

    public void setClazzFuncTitle(String clazzFuncTitle) {
        this.clazzFuncTitle = clazzFuncTitle;
    }

    public String getClazzFuncTitle() {
        return clazzFuncTitle;
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

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getDeleteFlag() {
        return deleteFlag;
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
                .append("clazzCode", getClazzCode())
                .append("clazzTitle", getClazzTitle())
                .append("deptLevelCode", getDeptLevelCode())
                .append("deptLevelTitle", getDeptLevelTitle())
                .append("deptFuncCode", getDeptFuncCode())
                .append("deptFuncTitle", getDeptFuncTitle())
                .append("clazzFuncCode", getClazzFuncCode())
                .append("clazzFuncTitle", getClazzFuncTitle())
                .append("deptFlag", getDeptFlag())
                .append("deptCode", getDeptCode())
                .append("deptTitle", getDeptTitle())
                .append("validFlag", getValidFlag())
                .append("remarks", getRemarks())
                .append("deleteFlag", getDeleteFlag())
                .append("createTime", getCreateTime())
                .append("createUser", getCreateUser())
                .append("createUserId", getCreateUserId())
                .append("updateTime", getUpdateTime())
                .append("updateUser", getUpdateUser())
                .append("updateUserId", getUpdateUserId())
                .toString();
    }


}
