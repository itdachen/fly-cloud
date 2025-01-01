/*
 ++++++++++++++++++++++++++++++++++
 + 岗位菜单
 + @author 王大宸
 + @date 2025-01-01 17:12:50
 ++++++++++++++++++++++++++++++++++
 */

import {BizQuery} from "@/fly/biz/BizModel";
import {reactive, ref} from "vue";
import {TableData} from "axios";

/**
 * 岗位菜单 查询参数
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:50
 */
export interface AuthClazzMenuQuery extends BizQuery {
            /** 租户ID */
           tenantId?: string,
            /** 应用ID */
           appId?: string,
            /** 岗位代码 */
           clazzCode?: string,
            /** 岗位名称 */
           clazzTitle?: string,
            /** 菜单ID/资源ID */
           menuId?: string,
            /** 菜单名称/资源名称 */
           menuTitle?: string,
}


/**
 * 岗位菜单 向后端传值对象
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:50
 */
export interface AuthClazzMenu {
    /** ID */
    id?: string , 
    /** 租户ID */
    tenantId?: string , 
    /** 应用ID */
    appId?: string , 
    /** 岗位代码 */
    clazzCode?: string , 
    /** 岗位名称 */
    clazzTitle?: string , 
    /** 菜单ID/资源ID */
    menuId?: string , 
    /** 菜单名称/资源名称 */
    menuTitle?: string 
}


/**
 * 初始化方法
 */
export default function useAuthClazzMenuBuilder() {

    /**
     * 实例化查询数据对象
     */
    const queryAuthClazzMenuParams = reactive<AuthClazzMenuQuery>({
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
        /** 菜单ID/资源ID */
        menuId: '' , 
        /** 菜单名称/资源名称 */
        menuTitle: '' 
    });

    /**
     * 分页数据
     */
    const tableAuthClazzMenuData = reactive< TableData < AuthClazzMenu > > ({
        loading: false,
        total: 0,
        rows: [],
    });

    /**
     * 实例化对象
     */
    const authClazzMenu = ref< AuthClazzMenu >({
         /** ID */
         id: '' , 
         /** 租户ID */
         tenantId: '' , 
         /** 应用ID */
         appId: '' , 
         /** 岗位代码 */
         clazzCode: '' , 
         /** 岗位名称 */
         clazzTitle: '' , 
         /** 菜单ID/资源ID */
         menuId: '' , 
         /** 菜单名称/资源名称 */
         menuTitle: '' 
    });

    /**
     * 分页列表展示项
     */
    const authClazzMenuColumns = [
        {title: '应用ID', key: 'appId',  ellipsisTooltip: true, align: 'center'},
        {title: '岗位代码', key: 'clazzCode',  ellipsisTooltip: true, align: 'center'},
        {title: '岗位名称', key: 'clazzTitle',  ellipsisTooltip: true, align: 'center'},
        {title: '菜单ID/资源ID', key: 'menuId',  ellipsisTooltip: true, align: 'center'},
        {title: '菜单名称/资源名称', key: 'menuTitle',  ellipsisTooltip: true, align: 'center'},
        {title: '操作', width: '280px', customSlot: 'operator', key: 'operator',  align: 'center', fixed: 'right'  }
    ];

    /**
     *  新增/修改/查看 弹窗
     */
    const refAuthClazzMenuComponent = ref();

    return {
        queryAuthClazzMenuParams,
        tableAuthClazzMenuData,
        authClazzMenu,
        authClazzMenuColumns,
        refAuthClazzMenuComponent
    }

}