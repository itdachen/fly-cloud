/*
 ++++++++++++++++++++++++++++++++++
 + 人员身份菜单
 + @author 王大宸
 + @date 2025-01-01 17:12:49
 ++++++++++++++++++++++++++++++++++
 */

import {BizQuery} from "@/fly/biz/BizModel";
import {reactive, ref} from "vue";
import {TableData} from "axios";

/**
 * 人员身份菜单 查询参数
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:49
 */
export interface AuthRoleMenuQuery extends BizQuery {
            /** 租户ID */
           tenantId?: string,
            /** 应用ID */
           appId?: string,
            /** 人员身份ID */
           roleId?: string,
            /** 菜单ID */
           menuId?: string,
}


/**
 * 人员身份菜单 向后端传值对象
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:49
 */
export interface AuthRoleMenu {
    /** ID */
    id?: string , 
    /** 租户ID */
    tenantId?: string , 
    /** 应用ID */
    appId?: string , 
    /** 人员身份ID */
    roleId?: string , 
    /** 菜单ID */
    menuId?: string 
}


/**
 * 初始化方法
 */
export default function useAuthRoleMenuBuilder() {

    /**
     * 实例化查询数据对象
     */
    const queryAuthRoleMenuParams = reactive<AuthRoleMenuQuery>({
        page: 1,
        limit: 10,
        /** 租户ID */
        tenantId: '' , 
        /** 应用ID */
        appId: '' , 
        /** 人员身份ID */
        roleId: '' , 
        /** 菜单ID */
        menuId: '' 
    });

    /**
     * 分页数据
     */
    const tableAuthRoleMenuData = reactive< TableData < AuthRoleMenu > > ({
        loading: false,
        total: 0,
        rows: [],
    });

    /**
     * 实例化对象
     */
    const authRoleMenu = ref< AuthRoleMenu >({
         /** ID */
         id: '' , 
         /** 租户ID */
         tenantId: '' , 
         /** 应用ID */
         appId: '' , 
         /** 人员身份ID */
         roleId: '' , 
         /** 菜单ID */
         menuId: '' 
    });

    /**
     * 分页列表展示项
     */
    const authRoleMenuColumns = [
        {title: '应用ID', key: 'appId',  ellipsisTooltip: true, align: 'center'},
        {title: '人员身份ID', key: 'roleId',  ellipsisTooltip: true, align: 'center'},
        {title: '菜单ID', key: 'menuId',  ellipsisTooltip: true, align: 'center'},
        {title: '操作', width: '280px', customSlot: 'operator', key: 'operator',  align: 'center', fixed: 'right'  }
    ];

    /**
     *  新增/修改/查看 弹窗
     */
    const refAuthRoleMenuComponent = ref();

    return {
        queryAuthRoleMenuParams,
        tableAuthRoleMenuData,
        authRoleMenu,
        authRoleMenuColumns,
        refAuthRoleMenuComponent
    }

}