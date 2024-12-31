/*
 ++++++++++++++++++++++++++++++++++
 + 菜单信息
 + @author 王大宸
 + @date 2024-12-30 10:26:52
 ++++++++++++++++++++++++++++++++++
 */

import {BizQuery} from "@/fly/biz/BizModel";
import {reactive, ref} from "vue";
import {TableData} from "axios";

/**
 * 菜单信息 查询参数
 *
 * @author 王大宸
 * @date 2024-12-30 10:26:52
 */
export interface MenuInfoQuery extends BizQuery {
    /** 应用ID/appId */
    appId?: string,
    /** 上级ID */
    parentId?: string,
    /** 认证编码 */
    authCode?: string,
    /** 类型:dirt-目录;menu-菜单;uri-外链;third-第三方平台 */
    type?: string,
    /** 菜单标题 */
    title?: string,
    /** 是否展示/有效标志: Y-有效;N-无效 */
    validFlag?: string,
    /** 菜单打开方式 */
    openType?: string,
    /** 是否需要授权: Y-需要;N-不需要 */
    hasAuth?: string,
    /** 是否需要白名单IP: Y-需要;N-不需要 */
    hasIp?: string,
}


/**
 * 菜单信息 向后端传值对象
 *
 * @author 王大宸
 * @date 2024-12-30 10:26:52
 */
export interface MenuInfo {
    /** 菜单ID */
    id?: string,
    /** 应用ID/appId */
    appId?: string,
    /** 上级ID */
    parentId?: string,
    /** 上级菜单 */
    parentTitle?: string,
    /** 认证编码 */
    authCode?: string,
    /** 类型:dirt-目录;menu-菜单;uri-外链;third-第三方平台 */
    type?: string,
    /** 菜单标题 */
    title?: string,
    /** 访问地址 */
    path?: string,
    /** 图标 */
    layIcon?: string,
    /** 排序 */
    orderNum?: string,
    /** 是否展示/有效标志: Y-有效;N-无效 */
    validFlag?: string,
    /** 菜单打开方式 */
    openType?: string,
    /** 是否需要授权: Y-需要;N-不需要 */
    hasAuth?: string,
    /** 是否需要白名单IP: Y-需要;N-不需要 */
    hasIp?: string,
    /** 是否固定 */
    affix?: string,
    /** 是否可关闭 */
    closable?: string,
    /** 第三方平台标识 */
    thirdParty?: string,
    /** 第三方平台名称 */
    thirdTitle?: string,
    /** 第三方基础访问地址, 例如: http://www.baidu.com */
    thirdUri?: string,
    /** 最终访问地址 */
    askUri?: string,
    /** 组件名称 */
    componentName?: string,
    /** 备注 */
    remarks?: string
}


/**
 * 初始化方法
 */
export default function useMenuInfoBuilder() {

    /**
     * 实例化查询数据对象
     */
    const queryMenuInfoParams = reactive<MenuInfoQuery>({
        page: 1,
        limit: 10,
        /** 应用ID/appId */
        appId: '',
        /** 上级ID */
        parentId: '',
        /** 认证编码 */
        authCode: '',
        /** 类型:dirt-目录;menu-菜单;uri-外链;third-第三方平台 */
        type: '',
        /** 菜单标题 */
        title: '',
        /** 是否展示/有效标志: Y-有效;N-无效 */
        validFlag: '',
        /** 菜单打开方式 */
        openType: '',
        /** 是否需要授权: Y-需要;N-不需要 */
        hasAuth: '',
        /** 是否需要白名单IP: Y-需要;N-不需要 */
        hasIp: ''
    });

    /**
     * 分页数据
     */
    const tableMenuInfoData = reactive<TableData<MenuInfo>>({
        loading: false,
        total: 0,
        rows: [],
    });

    /**
     * 实例化对象
     */
    const menuInfo = ref<MenuInfo>({
        /** 菜单ID */
        id: '',
        /** 应用ID/appId */
        appId: '',
        /** 上级ID */
        parentId: '',
        /** 上级菜单 */
        parentTitle: '',
        /** 认证编码 */
        authCode: '',
        /** 类型:dirt-目录;menu-菜单;uri-外链;third-第三方平台 */
        type: '',
        /** 菜单标题 */
        title: '',
        /** 访问地址 */
        path: '',
        /** 图标 */
        layIcon: '',
        /** 排序 */
        orderNum: '',
        /** 是否展示/有效标志: Y-有效;N-无效 */
        validFlag: '',
        /** 菜单打开方式 */
        openType: '',
        /** 是否需要授权: Y-需要;N-不需要 */
        hasAuth: '',
        /** 是否需要白名单IP: Y-需要;N-不需要 */
        hasIp: '',
        /** 是否固定 */
        affix: '',
        /** 是否可关闭 */
        closable: '',
        /** 第三方平台标识 */
        thirdParty: '',
        /** 第三方平台名称 */
        thirdTitle: '',
        /** 第三方基础访问地址, 例如: http://www.baidu.com */
        thirdUri: '',
        /** 最终访问地址, 根据第三方平台信息拼接 */
        askUri: '',
        /** 组件名称 */
        componentName: '',
        /** 备注 */
        remarks: ''
    });

    /**
     * 分页列表展示项
     */
    const menuInfoColumns = [
        {title: '菜单标题', key: 'title', ellipsisTooltip: true, align: 'left', customSlot: 'title'},
        {title: '认证编码', key: 'authCode', ellipsisTooltip: true, align: 'center'},
        {title: '路由地址', key: 'path', ellipsisTooltip: true, align: 'center'},
        {
            title: '类型',
            key: 'type',
            ellipsisTooltip: true,
            align: 'center',
            width: 100,
            customSlot: 'type'
        },
        {title: '排序', key: 'orderNum', ellipsisTooltip: true, width: 100, align: 'center'},
        {
            title: '有效标志', key: 'validFlag', ellipsisTooltip: true, align: 'center',
            width: 120,
            customSlot: function (obj: any) {
                return 'Y' === obj.data.validFlag ? '有效' : '无效';

            }
        },
        {title: '操作', width: '280px', customSlot: 'operator', key: 'operator', align: 'center', fixed: 'right'}
    ];

    /**
     *  新增/修改/查看 弹窗
     */
    const refMenuInfoComponent = ref();

    return {
        queryMenuInfoParams,
        tableMenuInfoData,
        menuInfo,
        menuInfoColumns,
        refMenuInfoComponent
    }

}