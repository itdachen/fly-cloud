import {BizQuery} from "/@/fly/biz/BizModel";
import {reactive, ref} from "vue";
import {ColumnProps} from '/@/components/ProTable/interface';
import {DialogTypeEnum} from "/@/components/dialog/DialogModel";
import {TableData} from "axios";

/**
 * 应用信息 查询参数
 *
 * @author 王大宸
 * @date 2024-09-13 11:03:11
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
 * @date 2024-09-13 11:03:11
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
    /** 是否可删除: Y-是;N-否 */
    validDel?: string,
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
    const queryAppInfoParams = reactive<AppInfoQuery>({
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
    const tableData = reactive<TableData<AppInfo>>({
        total: 0,
        rows: [],
    });

    /**
     * 实例化对象
     */
    const appInfo = reactive<AppInfo>({
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
        appType: '',
        /** 应用类型 */
        typeCode: '',
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
        /** 是否可删除: Y-是;N-否 */
        validDel: '',
        /** 有效标志: Y-是;N-否 */
        validFlag: '',
        /** 备注 */
        remarks: '',
    });

    /**
     * 分页列表展示项
     */
    const columns: Partial<ColumnProps>[] = [
        {
            prop: "platTitle",
            label: "平台名称",
            align: "center"
        },
        {
            prop: "appCode",
            label: "应用标识",
            align: "center"
        },
        {
            prop: "appTitle",
            label: "应用名称",
            align: "center"
        },
        {
            prop: "appAsTitle",
            label: "应用名称简称",
            align: "center"
        },
        {
            prop: "appType",
            label: "应用类型",
            align: "center"
        },
        {
            prop: "typeTitle",
            label: "应用类型标题",
            align: "center"
        },
        {
            prop: "askUri",
            label: "访问地址",
            align: "center"
        },
        {
            prop: "iconIco",
            label: "图标",
            align: "center"
        },
        {
            prop: "funcTitle",
            label: "职能名称",
            align: "center"
        },
        {
            prop: "validFlag",
            label: "有效标志",
            align: "center"
        },
        {
            prop: "operation",
            label: "操作",
            width: 350,
            align: "center",
            fixed: "right"
        }
    ];

    /**
     *  新增/修改/查看 弹窗
     */
    const refAppInfo = ref<{
        show: (type: DialogTypeEnum, data?: AppInfo | null) => void,
        onClose: () => void
    }>();

    return {
        queryAppInfoParams,
        tableData,
        appInfo,
        columns,
        refAppInfo
    }

}