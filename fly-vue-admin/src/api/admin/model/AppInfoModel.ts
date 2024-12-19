/*
 ++++++++++++++++++++++++++++++++++
 + 应用信息
 + @author 王大宸
 + @date 2024-09-05 10:00:59
 ++++++++++++++++++++++++++++++++++
 */

import {BizQuery} from "@/fly/biz/BizModel";
import {reactive, ref} from "vue";
import {TableData} from "axios";

/**
 * 应用信息 查询参数
 *
 * @author 王大宸
 * @date 2024-09-05 10:00:59
 */
export interface AppInfoQuery extends BizQuery {
    /** 平台ID */
    platId?: string,
    /** 应用标识 */
    appCode?: string,
    /** 应用名称 */
    appTitle?: string,
    /** 应用类型: BACK-后端;VIEW-前端 */
    appType?: string,
    /** 应用类型 */
    typeCode?: string,
    /** 职能代码 */
    funcCode?: string,
    /** 有效标志: Y-是;N-否 */
    validFlag?: string,
}


/**
 * 应用信息 向后端传值对象
 *
 * @author 王大宸
 * @date 2024-09-05 10:00:59
 */
export interface AppInfo {
    /** appID */
    id?: string,
    /** 平台ID */
    platId?: string,
    /** 平台名称 */
    platTitle?: string,
    /** app秘钥 */
    appSecret?: string,
    /** 应用标识 */
    appCode?: string,
    /** 应用名称 */
    appTitle?: string,
    /** 应用名称简称 */
    appAsTitle?: string,
    /** 应用类型: BACK-后端;VIEW-前端 */
    appType?: string,
    /** 应用类型 */
    typeCode?: string,
    /** 应用类型标题 */
    typeTitle?: string,
    /** 访问地址 */
    askUri?: string,
    /** 图标 */
    iconIco?: string,
    /** 职能代码 */
    funcCode?: string,
    /** 职能名称 */
    funcTitle?: string,
    /** 有效标志: Y-是;N-否 */
    validFlag?: string,
    /** 备注 */
    remarks?: string
}


/**
 * 初始化方法
 */
export default function useAppInfoBuilder() {

    /**
     * 实例化查询数据对象
     */
    const queryAppInfoParams = reactive({
        page: 1,
        limit: 10,
        /** 平台ID */
        platId: '',
        /** 应用标识 */
        appCode: '',
        /** 应用名称 */
        appTitle: '',
        /** 应用类型: BACK-后端;VIEW-前端 */
        appType: '',
        /** 应用类型 */
        typeCode: '',
        /** 职能代码 */
        funcCode: '',
        /** 有效标志: Y-是;N-否 */
        validFlag: ''
    });

    /**
     * 分页数据
     */
    const tableAppInfoData = reactive<TableData<AppInfo>>({
        loading: false, /* 是否显示加载框 */
        total: 0, /* 数据总条数 */
        rows: [], /* 展示的数据 */
    });

    /**
     * 实例化对象
     */
    const appInfo = ref<AppInfo>({
        /** appID */
        id: '',
        /** 平台ID */
        platId: '',
        /** 平台名称 */
        platTitle: '',
        /** app秘钥 */
        appSecret: '',
        /** 应用标识 */
        appCode: '',
        /** 应用名称 */
        appTitle: '',
        /** 应用名称简称 */
        appAsTitle: '',
        /** 应用类型: BACK-后端;VIEW-前端 */
        appType: 'BACK',
        /** 应用类型 */
        typeCode: 'BUS',
        /** 应用类型标题 */
        typeTitle: '',
        /** 访问地址 */
        askUri: '',
        /** 图标 */
        iconIco: '',
        /** 职能代码 */
        funcCode: '',
        /** 职能名称 */
        funcTitle: '',
        /** 有效标志: Y-是;N-否 */
        validFlag: 'Y',
        /** 备注 */
        remarks: ''
    });

    /**
     * 分页列表展示项
     */
    const appInfoColumns = [
        // {title: '平台名称', key: 'platTitle', ellipsisTooltip: true, align: 'center'},
        {title: '应用名称', key: 'appTitle', ellipsisTooltip: true, align: 'center'},
        {title: '应用标识', key: 'appCode', ellipsisTooltip: true, align: 'center'},
        {title: '应用类型', key: 'typeTitle', ellipsisTooltip: true, align: 'center'},
        //  {title: '访问地址', key: 'askUri', ellipsisTooltip: true, align: 'center'},
        //  {title: '图标', key: 'iconIco', ellipsisTooltip: true, align: 'center'},
        {
            title: '有效标志',
            key: 'validFlag',
            ellipsisTooltip: true,
            align: 'center',
            width: 150,
            customSlot: function (obj: any) {
                // if ('Y' === obj.data.validFlag) {
                //     return '有效';
                // }
                // return '无效';
                return 'Y' === obj.data.validFlag ? '有效' : '无效';

            }
        },
        {
            title: '操作',
            width: '280px',
            customSlot: 'operator',
            key: 'operator',
            align: 'center',
            fixed: 'right',
            ignoreExport: true
        }
    ];

    /**
     *  新增/修改/查看 弹窗
     */
    const refAppInfoForm = ref();

    return {
        queryAppInfoParams,
        tableAppInfoData,
        appInfo,
        appInfoColumns,
        refAppInfoForm
    }

}