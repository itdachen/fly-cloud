/*
 ++++++++++++++++++++++++++++++++++
 + 权限下发
 + @author 王大宸
 + @date 2025-01-01 17:12:49
 ++++++++++++++++++++++++++++++++++
 */

import {BizQuery} from "@/fly/biz/BizModel";
import {reactive, ref} from "vue";
import {TableData} from "axios";

/**
 * 权限下发 查询参数
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:49
 */
export interface AuthGrantMenuQuery extends BizQuery {
            /** 租户ID */
           tenantId?: string,
            /** appID */
           appId?: string,
            /** 用户ID/人员身份ID */
           roleId?: string,
            /** 菜单ID */
           menuId?: string,
}


/**
 * 权限下发 向后端传值对象
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:49
 */
export interface AuthGrantMenu {
    /** ID */
    id?: string , 
    /** 租户ID */
    tenantId?: string , 
    /** appID */
    appId?: string , 
    /** 用户ID/人员身份ID */
    roleId?: string , 
    /** 菜单ID */
    menuId?: string 
}


/**
 * 初始化方法
 */
export default function useAuthGrantMenuBuilder() {

    /**
     * 实例化查询数据对象
     */
    const queryAuthGrantMenuParams = reactive<AuthGrantMenuQuery>({
        page: 1,
        limit: 10,
        /** 租户ID */
        tenantId: '' , 
        /** appID */
        appId: '' , 
        /** 用户ID/人员身份ID */
        roleId: '' , 
        /** 菜单ID */
        menuId: '' 
    });

    /**
     * 分页数据
     */
    const tableAuthGrantMenuData = reactive< TableData < AuthGrantMenu > > ({
        loading: false,
        total: 0,
        rows: [],
    });

    /**
     * 实例化对象
     */
    const authGrantMenu = ref< AuthGrantMenu >({
         /** ID */
         id: '' , 
         /** 租户ID */
         tenantId: '' , 
         /** appID */
         appId: '' , 
         /** 用户ID/人员身份ID */
         roleId: '' , 
         /** 菜单ID */
         menuId: '' 
    });

    /**
     * 分页列表展示项
     */
    const authGrantMenuColumns = [
        {title: 'appID', key: 'appId',  ellipsisTooltip: true, align: 'center'},
        {title: '用户ID/人员身份ID', key: 'roleId',  ellipsisTooltip: true, align: 'center'},
        {title: '菜单ID', key: 'menuId',  ellipsisTooltip: true, align: 'center'},
        {title: '操作', width: '280px', customSlot: 'operator', key: 'operator',  align: 'center', fixed: 'right'  }
    ];

    /**
     *  新增/修改/查看 弹窗
     */
    const refAuthGrantMenuComponent = ref();

    return {
        queryAuthGrantMenuParams,
        tableAuthGrantMenuData,
        authGrantMenu,
        authGrantMenuColumns,
        refAuthGrantMenuComponent
    }

}