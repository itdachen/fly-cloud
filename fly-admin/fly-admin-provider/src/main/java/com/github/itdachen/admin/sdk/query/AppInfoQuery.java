package com.github.itdachen.admin.sdk.query;

import com.github.itdachen.framework.core.biz.BizQuery;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


import java.io.Serializable;


/**
 * 应用信息 查询参数
 *
 * @author 王大宸
 * @date 2024-09-06 10:16:14
 */
public class AppInfoQuery extends BizQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 平台ID
     */
    private String platId;

    /**
     * 应用标识
     */
    private String appCode;

    /**
     * 应用名称
     */
    private String appTitle;

    /**
     * 应用类型: BACK-后端;VIEW-前端
     */
    private String appType;

    /**
     * 应用类型
     */
    private String typeCode;

    /**
     * 职能代码
     */
    private String funcCode;

    /**
     * 有效标志: Y-是;N-否
     */
    private String validFlag;


    public AppInfoQuery() {
    }

    public AppInfoQuery(int page, int limit, String platId, String appCode, String appTitle, String appType, String typeCode, String funcCode, String validFlag) {
        super(page, limit);
        this.platId = platId;
        this.appCode = appCode;
        this.appTitle = appTitle;
        this.appType = appType;
        this.typeCode = typeCode;
        this.funcCode = funcCode;
        this.validFlag = validFlag;
    }

    public static AppInfoQueryBuilder builder() {
        return new AppInfoQueryBuilder();
    }

    public static class AppInfoQueryBuilder {
        private Integer page = 1;
        private Integer limit = 10;
        private String platId;
        private String appCode;
        private String appTitle;
        private String appType;
        private String typeCode;
        private String funcCode;
        private String validFlag;

        public AppInfoQueryBuilder() {
        }

        public AppInfoQueryBuilder page(Integer page) {
            this.page = page;
            return this;
        }

        public AppInfoQueryBuilder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

        /* 平台ID */
        public AppInfoQueryBuilder platId(String platId) {
            this.platId = platId;
            return this;
        }

        /* 应用标识 */
        public AppInfoQueryBuilder appCode(String appCode) {
            this.appCode = appCode;
            return this;
        }

        /* 应用名称 */
        public AppInfoQueryBuilder appTitle(String appTitle) {
            this.appTitle = appTitle;
            return this;
        }

        /* 应用类型: BACK-后端;VIEW-前端 */
        public AppInfoQueryBuilder appType(String appType) {
            this.appType = appType;
            return this;
        }

        /* 应用类型 */
        public AppInfoQueryBuilder typeCode(String typeCode) {
            this.typeCode = typeCode;
            return this;
        }

        /* 职能代码 */
        public AppInfoQueryBuilder funcCode(String funcCode) {
            this.funcCode = funcCode;
            return this;
        }

        /* 有效标志: Y-是;N-否 */
        public AppInfoQueryBuilder validFlag(String validFlag) {
            this.validFlag = validFlag;
            return this;
        }

        public AppInfoQuery build() {
            return new AppInfoQuery(page, limit, platId, appCode, appTitle, appType, typeCode, funcCode, validFlag);
        }

    }


    public void setPlatId(String platId) {
        this.platId = platId;
    }

    public String getPlatId() {
        return platId;
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

    public void setFuncCode(String funcCode) {
        this.funcCode = funcCode;
    }

    public String getFuncCode() {
        return funcCode;
    }

    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
    }

    public String getValidFlag() {
        return validFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("page", getPage())
                .append("limit", getLimit())
                .append("platId", getPlatId())
                .append("appCode", getAppCode())
                .append("appTitle", getAppTitle())
                .append("appType", getAppType())
                .append("typeCode", getTypeCode())
                .append("funcCode", getFuncCode())
                .append("validFlag", getValidFlag())
                .toString();
    }

}
