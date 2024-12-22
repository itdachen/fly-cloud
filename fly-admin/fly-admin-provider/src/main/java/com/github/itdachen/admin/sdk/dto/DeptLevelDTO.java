package com.github.itdachen.admin.sdk.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


import java.io.Serializable;

/**
 * 部门等级管理 DTO
 *
 * @author 王大宸
 * @date 2024-12-22 20:30:44
 */
public class DeptLevelDTO implements Serializable {
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
     * 层级代码, 例如:00,11,21,31,41,51
     */
    @NotBlank(message = "层级代码不能为空")
    private String levelCode;

    /**
     * 层级名称,例如: 00-总部; 11-省级部门; 21-市州级部门; 31-区县级;41-乡镇级;51-社区/街道
     */
    @NotBlank(message = "层级名称不能为空")
    private String levelTitle;

    /**
     * 有效标志: Y-是;N-否
     */
    @NotBlank(message = "有效标志不能为空")
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
                .append("levelCode", getLevelCode())
                .append("levelTitle", getLevelTitle())
                .append("validFlag", getValidFlag())
                .append("remarks", getRemarks())
                .toString();
    }

}
