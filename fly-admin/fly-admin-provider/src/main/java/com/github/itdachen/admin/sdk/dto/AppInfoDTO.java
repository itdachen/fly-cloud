package com.github.itdachen.admin.sdk.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


import java.io.Serializable;

/**
 * 应用信息 DTO
 *
 * @author 王大宸
 * @date 2024-09-06 10:16:14
 */
public class AppInfoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * appID
     */
    private String id;

    /**
     * 平台ID
     */
    // @NotBlank(message = "平台ID不能为空")
    private String platId;

    /**
     * 平台名称
     */
    //  @NotBlank(message = "平台名称不能为空")
    private String platTitle;

    /**
     * app秘钥
     */
    // @NotBlank(message = "app秘钥不能为空")
    private String appSecret;

    /**
     * 应用标识
     */
    // @NotBlank(message = "应用标识不能为空")
    private String appCode;

    /**
     * 应用名称
     */
    //  @NotBlank(message = "应用名称不能为空")
    private String appTitle;

    /**
     * 应用名称简称
     */
    // @NotBlank(message = "应用名称简称不能为空")
    private String appAsTitle;

    /**
     * 应用类型: BACK-后端;VIEW-前端
     */
    // @NotBlank(message = "应用类型: BACK-后端;VIEW-前端不能为空")
    private String appType;

    /**
     * 应用类型
     */
    //  @NotBlank(message = "应用类型不能为空")
    private String typeCode;

    /**
     * 应用类型标题
     */
    // @NotBlank(message = "应用类型标题不能为空")
    private String typeTitle;

    /**
     * 访问地址
     */
    // @NotBlank(message = "访问地址不能为空")
    private String askUri;

    /**
     * 图标
     */
    // @NotBlank(message = "图标不能为空")
    private String iconIco;

    /**
     * 职能代码
     */
    // @NotBlank(message = "职能代码不能为空")
    private String funcCode;

    /**
     * 职能名称
     */
    // @NotBlank(message = "职能名称不能为空")
    private String funcTitle;

    /**
     * 是否可删除: Y-是;N-否
     */
    // @NotBlank(message = "是否可删除: Y-是;N-否不能为空")
    private String validDel;

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

    public void setPlatId(String platId) {
        this.platId = platId;
    }

    public String getPlatId() {
        return platId;
    }

    public void setPlatTitle(String platTitle) {
        this.platTitle = platTitle;
    }

    public String getPlatTitle() {
        return platTitle;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppTitle(String appTitle) {
        this.appTitle = appTitle;
    }

    public String getAppTitle() {
        return appTitle;
    }

    public void setAppAsTitle(String appAsTitle) {
        this.appAsTitle = appAsTitle;
    }

    public String getAppAsTitle() {
        return appAsTitle;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getAppType() {
        return appType;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeTitle(String typeTitle) {
        this.typeTitle = typeTitle;
    }

    public String getTypeTitle() {
        return typeTitle;
    }

    public void setAskUri(String askUri) {
        this.askUri = askUri;
    }

    public String getAskUri() {
        return askUri;
    }

    public void setIconIco(String iconIco) {
        this.iconIco = iconIco;
    }

    public String getIconIco() {
        return iconIco;
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

    public void setValidDel(String validDel) {
        this.validDel = validDel;
    }

    public String getValidDel() {
        return validDel;
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
                .append("platId", getPlatId())
                .append("platTitle", getPlatTitle())
                .append("appSecret", getAppSecret())
                .append("appCode", getAppCode())
                .append("appTitle", getAppTitle())
                .append("appAsTitle", getAppAsTitle())
                .append("appType", getAppType())
                .append("typeCode", getTypeCode())
                .append("typeTitle", getTypeTitle())
                .append("askUri", getAskUri())
                .append("iconIco", getIconIco())
                .append("funcCode", getFuncCode())
                .append("funcTitle", getFuncTitle())
                .append("validDel", getValidDel())
                .append("validFlag", getValidFlag())
                .append("remarks", getRemarks())
                .toString();
    }

}
