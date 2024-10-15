package com.github.itdachen.admin.sdk.query;

import com.github.itdachen.framework.core.biz.BizQuery;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


import java.io.Serializable;


/**
 * 租户/公司信息 查询参数
 *
 * @author 王大宸
 * @date 2024-10-15 16:27:41
 */
public class TenantInfoQuery extends BizQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    private String title;

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
     * 拥有者ID
     */
    private String ownerId;

    /**
     * 拥有者昵称
     */
    private String ownerNickName;

    /**
     * 是否可删除: Y-是;N-否
     */
    private String validDel;

    /**
     * 有效标志: Y-是;N-否
     */
    private String validFlag;


    public TenantInfoQuery() {
    }

    public TenantInfoQuery(int page, int limit, String title, String provId, String cityId, String countyId, String ownerId, String ownerNickName, String validDel, String validFlag) {
        super(page, limit);
        this.title = title;
        this.provId = provId;
        this.cityId = cityId;
        this.countyId = countyId;
        this.ownerId = ownerId;
        this.ownerNickName = ownerNickName;
        this.validDel = validDel;
        this.validFlag = validFlag;
    }

    public static TenantInfoQueryBuilder builder() {
        return new TenantInfoQueryBuilder();
    }

    public static class TenantInfoQueryBuilder {
        private Integer page = 1;
        private Integer limit = 10;
        private String title;
        private String provId;
        private String cityId;
        private String countyId;
        private String ownerId;
        private String ownerNickName;
        private String validDel;
        private String validFlag;

        public TenantInfoQueryBuilder() {
        }

        public TenantInfoQueryBuilder page(Integer page) {
            this.page = page;
            return this;
        }

        public TenantInfoQueryBuilder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

        /* 名称 */
        public TenantInfoQueryBuilder title(String title) {
            this.title = title;
            return this;
        }

        /* 所属省级ID */
        public TenantInfoQueryBuilder provId(String provId) {
            this.provId = provId;
            return this;
        }

        /* 所属市州 */
        public TenantInfoQueryBuilder cityId(String cityId) {
            this.cityId = cityId;
            return this;
        }

        /* 所属区县 */
        public TenantInfoQueryBuilder countyId(String countyId) {
            this.countyId = countyId;
            return this;
        }

        /* 拥有者ID */
        public TenantInfoQueryBuilder ownerId(String ownerId) {
            this.ownerId = ownerId;
            return this;
        }

        /* 拥有者昵称 */
        public TenantInfoQueryBuilder ownerNickName(String ownerNickName) {
            this.ownerNickName = ownerNickName;
            return this;
        }

        /* 是否可删除: Y-是;N-否 */
        public TenantInfoQueryBuilder validDel(String validDel) {
            this.validDel = validDel;
            return this;
        }

        /* 有效标志: Y-是;N-否 */
        public TenantInfoQueryBuilder validFlag(String validFlag) {
            this.validFlag = validFlag;
            return this;
        }

        public TenantInfoQuery build() {
            return new TenantInfoQuery(page, limit, title, provId, cityId, countyId, ownerId, ownerNickName, validDel, validFlag);
        }

    }


    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
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

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerNickName(String ownerNickName) {
        this.ownerNickName = ownerNickName;
    }

    public String getOwnerNickName() {
        return ownerNickName;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("page", getPage())
                .append("limit", getLimit())
                .append("title", getTitle())
                .append("provId", getProvId())
                .append("cityId", getCityId())
                .append("countyId", getCountyId())
                .append("ownerId", getOwnerId())
                .append("ownerNickName", getOwnerNickName())
                .append("validDel", getValidDel())
                .append("validFlag", getValidFlag())
                .toString();
    }

}
