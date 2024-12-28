package com.github.itdachen.admin.sdk.query;

import com.github.itdachen.framework.core.biz.BizQuery;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


import java.io.Serializable;


/**
 * 岗位职能管理表(确定之后不能修改) 查询参数
 *
 * @author 王大宸
 * @date 2024-12-28 18:40:48
 */
public class ClazzFuncQuery extends BizQuery implements Serializable {
    private static final long serialVersionUID = 1L;

            /** 租户ID */
            private String tenantId;

            /** 岗位职能编码, 例如: 01-主管领导岗;02-业务岗 */
            private String funcCode;

            /** 岗位职能标题, 例如: 01-主管领导岗;02-业务岗 */
            private String funcTitle;

            /** 有效标志: Y-有效;N-无效 */
            private String validFlag;

            /** 删除标志:  Y-已删除;N-未删除 */
            private String deleteFlag;



    public ClazzFuncQuery() {
    }

    public ClazzFuncQuery(int page, int limit, String tenantId, String funcCode, String funcTitle, String validFlag, String deleteFlag) {
        super(page, limit);
            this.tenantId = tenantId;
            this.funcCode = funcCode;
            this.funcTitle = funcTitle;
            this.validFlag = validFlag;
            this.deleteFlag = deleteFlag;
    }

    public static ClazzFuncQueryBuilder builder() {
        return new ClazzFuncQueryBuilder();
    }

    public static class ClazzFuncQueryBuilder {
        private Integer page = 1;
        private Integer limit = 10;
            private String tenantId;
            private String funcCode;
            private String funcTitle;
            private String validFlag;
            private String deleteFlag;

        public ClazzFuncQueryBuilder() {
        }

        public ClazzFuncQueryBuilder page(Integer page) {
            this.page = page;
            return this;
        }

        public ClazzFuncQueryBuilder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

            /* 租户ID */
            public ClazzFuncQueryBuilder tenantId(String tenantId) {
                this.tenantId = tenantId;
                return this;
            }
            /* 岗位职能编码, 例如: 01-主管领导岗;02-业务岗 */
            public ClazzFuncQueryBuilder funcCode(String funcCode) {
                this.funcCode = funcCode;
                return this;
            }
            /* 岗位职能标题, 例如: 01-主管领导岗;02-业务岗 */
            public ClazzFuncQueryBuilder funcTitle(String funcTitle) {
                this.funcTitle = funcTitle;
                return this;
            }
            /* 有效标志: Y-有效;N-无效 */
            public ClazzFuncQueryBuilder validFlag(String validFlag) {
                this.validFlag = validFlag;
                return this;
            }
            /* 删除标志:  Y-已删除;N-未删除 */
            public ClazzFuncQueryBuilder deleteFlag(String deleteFlag) {
                this.deleteFlag = deleteFlag;
                return this;
            }

        public ClazzFuncQuery build() {
            return new ClazzFuncQuery(page, limit, tenantId, funcCode, funcTitle, validFlag, deleteFlag);
        }

    }



        public void setTenantId(String tenantId) {
            this.tenantId = tenantId;
        }

        public String getTenantId() {
            return tenantId;
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
                    .append("funcCode", getFuncCode())
                    .append("funcTitle", getFuncTitle())
                    .append("validFlag", getValidFlag())
                    .append("deleteFlag", getDeleteFlag())
                .toString();
    }

}
