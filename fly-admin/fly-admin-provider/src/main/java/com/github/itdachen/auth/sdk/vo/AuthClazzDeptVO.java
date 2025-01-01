package com.github.itdachen.auth.sdk.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


import java.io.Serializable;


/**
 * 部门岗位关联表 VO
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:50
 */
public class AuthClazzDeptVO implements Serializable {
    private static final long serialVersionUID = 1L;

        /** ID */
        private String id;

        /** 租户ID */
        private String tenantId;

        /** 部门代码 */
        private String deptCode;

        /** 部门名称 */
        private String deptTitle;

        /** 部门层级 */
        private String deptLevel;

        /** 岗位代码 */
        private String clazzCode;

        /** 岗位名称 */
        private String clazzTitle;

        /** 有效标志: Y-是;N-否 */
        private String validFlag;

        /** 备注 */
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

            public void setDeptLevel(String deptLevel) {
                this.deptLevel = deptLevel;
            }

            public String getDeptLevel() {
                return deptLevel;
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
                    .append("deptCode", getDeptCode())
                    .append("deptTitle", getDeptTitle())
                    .append("deptLevel", getDeptLevel())
                    .append("clazzCode", getClazzCode())
                    .append("clazzTitle", getClazzTitle())
                    .append("validFlag", getValidFlag())
                    .append("remarks", getRemarks())
                .toString();
    }

}
