/*
 ++++++++++++++++++++++++++++++++++
 + 资源(按钮)信息
 + @author 王大宸
 + @date 2024-12-30 10:26:53
 ++++++++++++++++++++++++++++++++++
 */

import {BizQuery} from "@/fly/biz/BizModel";
import {reactive, ref} from "vue";
import {TableData} from "axios";

/**
 * 资源(按钮)信息 查询参数
 *
 * @author 王大宸
 * @date 2024-12-30 10:26:53
 */
export interface ElementInfoQuery extends BizQuery {
    /** 应用ID/appID */
    appId?: string,
    /** 菜单ID */
    menuId?: string,
    /** 标题 */
    title?: string,
    /** 类型: button,uri */
    type?: string,
    /** 是否展示/有效标志: Y-有效;N-无效 */
    validFlag?: string,
}


/**
 * 资源(按钮)信息 向后端传值对象
 *
 * @author 王大宸
 * @date 2024-12-30 10:26:53
 */
export interface ElementInfo {
    /** 资源ID */
    id?: string,
    /** 应用ID/appID */
    appId?: string,
    /** 菜单ID */
    menuId?: string,
    /** 菜单名称 */
    menuTitle?: string,
    /** 认证编码 */
    authCode?: string,
    /** 标题 */
    title?: string,
    /** 类型: button,uri */
    type?: string,
    /** 页面访问地址 */
    pageUri?: string,
    /** 接口请求地址 */
    apiUri?: string,
    /** API请求类型: POST/GET/PUT/DELETE */
    httpMethod?: string,
    /** 排序 */
    orderNum?: string,
    /** 第三方平台标识 */
    thirdParty?: string,
    /** 第三方平台名称 */
    thirdTitle?: string,
    /** 第三方基础访问地址, 例如: http://www.baidu.com */
    thirdUri?: string,
    /** 最终访问地址, 根据第三方平台信息拼接 */
    askUri?: string,
    /** 是否展示/有效标志: Y-有效;N-无效 */
    validFlag?: string,
    /** 是否需要授权: Y-需要;N-不需要 */
    hasAuth?: string,
    /** 是否需要IP授权: Y-需要;N-不需要 */
    hasIp?: string,
    /** 备注 */
    remarks?: string
}


/**
 * 初始化方法
 */
export default function useElementInfoBuilder() {

    /**
     * 实例化查询数据对象
     */
    const queryElementInfoParams = reactive<ElementInfoQuery>({
        page: 1,
        limit: 10,
        /** 应用ID/appID */
        appId: '',
        /** 菜单ID */
        menuId: '',
        /** 标题 */
        title: '',
        /** 类型: button,uri */
        type: '',
        /** 是否展示/有效标志: Y-有效;N-无效 */
        validFlag: ''
    });

    /**
     * 分页数据
     */
    const tableElementInfoData = reactive<TableData<ElementInfo>>({
        loading: false,
        total: 0,
        rows: [],
    });

    /**
     * 实例化对象
     */
    const elementInfo = ref<ElementInfo>({
        /** 资源ID */
        id: '',
        /** 应用ID/appID */
        appId: '',
        /** 菜单ID */
        menuId: '',
        /** 菜单名称 */
        menuTitle: '',
        /** 认证编码 */
        authCode: '',
        /** 标题 */
        title: '',
        /** 类型: button,uri */
        type: '',
        /** 页面访问地址 */
        pageUri: '',
        /** 接口请求地址 */
        apiUri: '',
        /** API请求类型: POST/GET/PUT/DELETE */
        httpMethod: '',
        /** 排序 */
        orderNum: '',
        /** 第三方平台标识 */
        thirdParty: '',
        /** 第三方平台名称 */
        thirdTitle: '',
        /** 第三方基础访问地址, 例如: http://www.baidu.com */
        thirdUri: '',
        /** 最终访问地址, 根据第三方平台信息拼接 */
        askUri: '',
        /** 是否展示/有效标志: Y-有效;N-无效 */
        validFlag: '',
        /** 是否需要授权: Y-需要;N-不需要 */
        hasAuth: '',
        /** 是否需要IP授权: Y-需要;N-不需要 */
        hasIp: '',
        /** 备注 */
        remarks: ''
    });

    /**
     * 分页列表展示项
     */
    const elementInfoColumns = [
        {title: '标题', key: 'title', ellipsisTooltip: true, align: 'left'},
        {title: '接口地址', key: 'apiUri', ellipsisTooltip: true, align: 'left'},
        {title: '认证编码', key: 'authCode', ellipsisTooltip: true, align: 'left'},
        {
            title: '有效标志',
            key: 'validFlag',
            ellipsisTooltip: true,
            align: 'center',
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
    const refElementInfoComponent = ref();

    return {
        queryElementInfoParams,
        tableElementInfoData,
        elementInfo,
        elementInfoColumns,
        refElementInfoComponent
    }

}