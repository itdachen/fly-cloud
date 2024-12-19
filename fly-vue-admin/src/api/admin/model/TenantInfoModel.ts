/*
 ++++++++++++++++++++++++++++++++++
 + 租户/公司信息
 + @author 王大宸
 + @date 2024-12-19 11:07:05
 ++++++++++++++++++++++++++++++++++
 */

import {BizQuery} from "@/fly/biz/BizModel";
import {reactive, ref} from "vue";
import {TableData} from "axios";

/**
 * 租户/公司信息 查询参数
 *
 * @author 王大宸
 * @date 2024-12-19 11:07:05
 */
export interface TenantInfoQuery extends BizQuery {
    /** 上级ID */
    parentId?: string,
    /** 租户类型代码, 例如:100 */
    typeId?: string,
    /** 名称 */
    title?: string,
    /** 所属省级ID */
    provId?: string,
    /** 所属市州 */
    cityId?: string,
    /** 所属区县 */
    countyId?: string,
    /** 图标 */
    homeIcon?: string,
    /** 拥有者ID */
    ownerId?: string,
}


/**
 * 租户/公司信息 向后端传值对象
 *
 * @author 王大宸
 * @date 2024-12-19 11:07:05
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
    const queryTenantInfoParams = reactive<TenantInfoQuery>({
        page: 1,
        limit: 10,
        /** 上级ID */
        parentId: '',
        /** 租户类型代码, 例如:100 */
        typeId: '',
        /** 名称 */
        title: '',
        /** 所属省级ID */
        provId: '',
        /** 所属市州 */
        cityId: '',
        /** 所属区县 */
        countyId: '',
        /** 图标 */
        homeIcon: '',
        /** 拥有者ID */
        ownerId: ''
    });

    /**
     * 分页数据
     */
    const tableTenantInfoData = reactive<TableData<TenantInfo>>({
        loading: false,
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
        /** 有效标志: Y-是;N-否 */
        validFlag: '',
        /** 备注 */
        remarks: ''
    });

    /**
     * 分页列表展示项
     */
    const tenantInfoColumns = [
        {title: '名称', key: 'title', ellipsisTooltip: true, align: 'center'},
        {title: '简称', key: 'titleAs', ellipsisTooltip: true, align: 'center'},
        {title: '拥有者昵称', key: 'ownerNickName', ellipsisTooltip: true, align: 'center'},
        {title: '电子邮箱', key: 'eMailBox', ellipsisTooltip: true, align: 'center'},
        {title: '有效标志: Y-是;N-否', key: 'validFlag', ellipsisTooltip: true, align: 'center'},
        {title: '操作', width: '280px', customSlot: 'operator', key: 'operator', align: 'center', fixed: 'right'}
    ];

    /**
     *  新增/修改/查看 弹窗
     */
    const refTenantInfoComponent = ref();

    return {
        queryTenantInfoParams,
        tableTenantInfoData,
        tenantInfo,
        tenantInfoColumns,
        refTenantInfoComponent
    }

}