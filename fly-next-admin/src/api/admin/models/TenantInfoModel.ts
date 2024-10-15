import {BizQuery} from "/@/fly/biz/BizModel";
import {reactive, ref} from "vue";
import {ColumnProps} from '/@/components/table/interface';
import {DialogTypeEnum} from "/@/components/dialog/DialogModel";
import {TableData} from "axios";

/**
 * 租户/公司信息 查询参数
 *
 * @author 王大宸
 * @date 2024-10-15 16:27:41
 */
export interface TenantInfoQuery extends BizQuery {
    /** 名称 */
    title?: string,
    /** 所属省级ID */
    provId?: string,
    /** 所属市州 */
    cityId?: string,
    /** 所属区县 */
    countyId?: string,
    /** 拥有者ID */
    ownerId?: string,
    /** 拥有者昵称 */
    ownerNickName?: string,
    /** 是否可删除: Y-是;N-否 */
    validDel?: string,
    /** 有效标志: Y-是;N-否 */
    validFlag?: string,
}


/**
 * 租户/公司信息 向后端传值对象
 *
 * @author 王大宸
 * @date 2024-10-15 16:27:41
 */
export interface TenantInfo {
    /** 主键唯一标识 */
    id?: string,
    /** 上级ID */
    parentId?: string,
    /** 上级名称 */
    parentTitle?: string,
    /** 租户类型代码, 例如:100 */
    typeId?: string,
    /** 租户类型名称, 例如: 系统管理员 */
    typeTitle?: string,
    /** 名称 */
    title?: string,
    /** 简称 */
    titleAs?: string,
    /** 所属省级ID */
    provId?: string,
    /** 所属省级名称 */
    provTitle?: string,
    /** 所属市州 */
    cityId?: string,
    /** 所属市州名称 */
    cityTitle?: string,
    /** 所属区县 */
    countyId?: string,
    /** 所属区县名称 */
    countyTitle?: string,
    /** 图标 */
    homeIcon?: string,
    /** 官网/访问地址 */
    homeUri?: string,
    /** 拥有者ID */
    ownerId?: string,
    /** 拥有者昵称 */
    ownerNickName?: string,
    /** 联系电话 */
    telephone?: string,
    /** 电子邮箱 */
    eMailBox?: string,
    /** 传真 */
    facsimile?: string,
    /** 职能代码 */
    funcCode?: string,
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
export default function useTenantInfoBuilder() {
    /**
     * 实例化查询数据对象
     */
    const tenantInfoParams = reactive<TenantInfoQuery>({
        page: 1,
        limit: 10,
        /** 名称 */
        title: '',
        /** 所属省级ID */
        provId: '',
        /** 所属市州 */
        cityId: '',
        /** 所属区县 */
        countyId: '',
        /** 拥有者ID */
        ownerId: '',
        /** 拥有者昵称 */
        ownerNickName: '',
        /** 是否可删除: Y-是;N-否 */
        validDel: '',
        /** 有效标志: Y-是;N-否 */
        validFlag: ''
    });

    /**
     * 分页数据
     */
    const tableData = reactive<TableData<TenantInfo>>({
        total: 0,
        rows: [],
    });

    /**
     * 实例化对象
     */
    const tenantInfo = reactive<TenantInfo>({
        /** 主键唯一标识 */
        id: '',
        /** 上级ID */
        parentId: '',
        /** 上级名称 */
        parentTitle: '',
        /** 租户类型代码, 例如:100 */
        typeId: '',
        /** 租户类型名称, 例如: 系统管理员 */
        typeTitle: '',
        /** 名称 */
        title: '',
        /** 简称 */
        titleAs: '',
        /** 所属省级ID */
        provId: '',
        /** 所属省级名称 */
        provTitle: '',
        /** 所属市州 */
        cityId: '',
        /** 所属市州名称 */
        cityTitle: '',
        /** 所属区县 */
        countyId: '',
        /** 所属区县名称 */
        countyTitle: '',
        /** 图标 */
        homeIcon: '',
        /** 官网/访问地址 */
        homeUri: '',
        /** 拥有者ID */
        ownerId: '',
        /** 拥有者昵称 */
        ownerNickName: '',
        /** 联系电话 */
        telephone: '',
        /** 电子邮箱 */
        eMailBox: '',
        /** 传真 */
        facsimile: '',
        /** 职能代码 */
        funcCode: '',
        /** 是否可删除: Y-是;N-否 */
        validDel: '',
        /** 有效标志: Y-是;N-否 */
        validFlag: '',
        /** 备注 */
        remarks: ''
    });

    /**
     * 分页列表展示项
     */
    const columns: Partial<ColumnProps>[] = [
        {
            prop: "title",
            label: "名称",
            align: "center"
        },
        {
            prop: "titleAs",
            label: "简称",
            align: "center"
        },
        {
            prop: "homeIcon",
            label: "图标",
            align: "center"
        },
        {
            prop: "ownerNickName",
            label: "拥有者昵称",
            align: "center"
        },
        {
            prop: "validFlag",
            label: "有效标志",
            width: 150,
            tag: true,
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
    const refTenantInfo = ref<{
        show: (type: DialogTypeEnum, data?: TenantInfo | null) => void,
        onClose: () => void
    }>();

    return {
        tenantInfoParams,
        tableData,
        tenantInfo,
        columns,
        refTenantInfo
    }

}