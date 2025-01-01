/*
 ++++++++++++++++++++++++++++++++++
 + 身份岗位管理表
 + @author 王大宸
 + @date 2025-01-01 17:12:50
 ++++++++++++++++++++++++++++++++++
 */

import {BizQuery} from "@/fly/biz/BizModel";
import {reactive, ref} from "vue";
import {TableData} from "axios";

/**
 * 身份岗位管理表 查询参数
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:50
 */
export interface AuthClazzRoleQuery extends BizQuery {
            /** 租户ID */
           tenantId?: string,
            /** 应用ID */
           appId?: string,
            /** 岗位代码 */
           clazzCode?: string,
            /** 岗位名称 */
           clazzTitle?: string,
            /** 身份ID */
           roleId?: string,
            /** 有效标志: Y-是;N-否 */
           validFlag?: string,
            /** 删除标志: Y-已删除;N-未删除 */
           deleteFlag?: string,
}


/**
 * 身份岗位管理表 向后端传值对象
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:50
 */
export interface AuthClazzRole {
    /** 主键唯一标识 */
    id?: string , 
    /** 租户ID */
    tenantId?: string , 
    /** 应用ID */
    appId?: string , 
    /** 岗位代码 */
    clazzCode?: string , 
    /** 岗位名称 */
    clazzTitle?: string , 
    /** 身份ID */
    roleId?: string , 
    /** 身份名称 */
    roleTitle?: string , 
    /** 有效标志: Y-是;N-否 */
    validFlag?: string , 
    /** 备注 */
    remarks?: string 
}


/**
 * 初始化方法
 */
export default function useAuthClazzRoleBuilder() {

    /**
     * 实例化查询数据对象
     */
    const queryAuthClazzRoleParams = reactive<AuthClazzRoleQuery>({
        page: 1,
        limit: 10,
        /** 租户ID */
        tenantId: '' , 
        /** 应用ID */
        appId: '' , 
        /** 岗位代码 */
        clazzCode: '' , 
        /** 岗位名称 */
        clazzTitle: '' , 
        /** 身份ID */
        roleId: '' , 
        /** 有效标志: Y-是;N-否 */
        validFlag: '' , 
        /** 删除标志: Y-已删除;N-未删除 */
        deleteFlag: '' 
    });

    /**
     * 分页数据
     */
    const tableAuthClazzRoleData = reactive< TableData < AuthClazzRole > > ({
        loading: false,
        total: 0,
        rows: [],
    });

    /**
     * 实例化对象
     */
    const authClazzRole = ref< AuthClazzRole >({
         /** 主键唯一标识 */
         id: '' , 
         /** 租户ID */
         tenantId: '' , 
         /** 应用ID */
         appId: '' , 
         /** 岗位代码 */
         clazzCode: '' , 
         /** 岗位名称 */
         clazzTitle: '' , 
         /** 身份ID */
         roleId: '' , 
         /** 身份名称 */
         roleTitle: '' , 
         /** 有效标志: Y-是;N-否 */
         validFlag: '' , 
         /** 备注 */
         remarks: '' 
    });

    /**
     * 分页列表展示项
     */
    const authClazzRoleColumns = [
        {title: '应用ID', key: 'appId',  ellipsisTooltip: true, align: 'center'},
        {title: '岗位代码', key: 'clazzCode',  ellipsisTooltip: true, align: 'center'},
        {title: '岗位名称', key: 'clazzTitle',  ellipsisTooltip: true, align: 'center'},
        {title: '身份ID', key: 'roleId',  ellipsisTooltip: true, align: 'center'},
        {title: '身份名称', key: 'roleTitle',  ellipsisTooltip: true, align: 'center'},
        {title: '有效标志: Y-是;N-否', key: 'validFlag',  ellipsisTooltip: true, align: 'center'},
        {title: '操作', width: '280px', customSlot: 'operator', key: 'operator',  align: 'center', fixed: 'right'  }
    ];

    /**
     *  新增/修改/查看 弹窗
     */
    const refAuthClazzRoleComponent = ref();

    return {
        queryAuthClazzRoleParams,
        tableAuthClazzRoleData,
        authClazzRole,
        authClazzRoleColumns,
        refAuthClazzRoleComponent
    }

}