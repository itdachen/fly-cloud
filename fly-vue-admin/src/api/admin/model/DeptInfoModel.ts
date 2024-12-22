/*
 ++++++++++++++++++++++++++++++++++
 + 部门信息
 + @author 王大宸
 + @date 2024-12-22 22:30:09
 ++++++++++++++++++++++++++++++++++
 */

import {BizQuery} from "@/fly/biz/BizModel";
import {reactive, ref} from "vue";
import {TableData} from "axios";

/**
 * 部门信息 查询参数
 *
 * @author 王大宸
 * @date 2024-12-22 22:30:09
 */
export interface DeptInfoQuery extends BizQuery {
    /** 上级ID */
    parentId?: string,
    /** 部门名称 */
    title?: string,
}


/**
 * 部门信息 向后端传值对象
 *
 * @author 王大宸
 * @date 2024-12-22 22:30:09
 */
export interface DeptInfo {
    /** 主键唯一标识 */
    id?: string,
    /** 上级ID */
    parentId?: string,
    /** 部门名称 */
    title?: string,
    /** 部门简称 */
    titleAs?: string,
    /** 部门标志: Y-是;N-否 */
    deptFlag?: string,
    /** 是否管理本级: Y-是;N-否 */
    thatLevel?: string,
    /** 部门级次代码: 00-总部/10-省级/20-市州级/30-区县级/40-乡村级 */
    levelCode?: string,
    /** 部门级次名称 */
    levelTitle?: string,
    /** 联系电话 */
    telephone?: string,
    /** 电子邮箱 */
    mailBox?: string,
    /** 传真 */
    facsimile?: string,
    /** 职能代码(dept_func表中 code) */
    funcCode?: string,
    /** 职能名称(dept_func表中title) */
    funcTitle?: string,
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
    /** 有效标志: Y-是;N-否 */
    validFlag?: string,
    /** 备注 */
    remarks?: string
}


/**
 * 初始化方法
 */
export default function useDeptInfoBuilder() {

    /**
     * 实例化查询数据对象
     */
    const queryDeptInfoParams = reactive<DeptInfoQuery>({
        page: 1,
        limit: 10,
        /** 上级ID */
        parentId: '',
        /** 部门名称 */
        title: ''
    });

    /**
     * 分页数据
     */
    const tableDeptInfoData = reactive<TableData<DeptInfo>>({
        total: 0,
        rows: [],
        loading: false
    });

    /**
     * 实例化对象
     */
    const deptInfo = ref<DeptInfo>({
        /** 主键唯一标识 */
        id: '',
        /** 上级ID */
        parentId: '',
        /** 部门名称 */
        title: '',
        /** 部门简称 */
        titleAs: '',
        /** 部门标志: Y-是;N-否 */
        deptFlag: '',
        /** 是否管理本级: Y-是;N-否 */
        thatLevel: '',
        /** 部门级次代码: 00-总部/10-省级/20-市州级/30-区县级/40-乡村级 */
        levelCode: '',
        /** 部门级次名称 */
        levelTitle: '',
        /** 联系电话 */
        telephone: '',
        /** 电子邮箱 */
        mailBox: '',
        /** 传真 */
        facsimile: '',
        /** 职能代码(dept_func表中 code) */
        funcCode: '',
        /** 职能名称(dept_func表中title) */
        funcTitle: '',
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
        /** 有效标志: Y-是;N-否 */
        validFlag: '',
        /** 备注 */
        remarks: ''
    });

    /**
     * 分页列表展示项
     */
    const deptInfoColumns = [
        {title: '部门名称', key: 'title', ellipsisTooltip: true, align: 'left'},
       // {title: '部门简称', key: 'titleAs', ellipsisTooltip: true, align: 'center'},
        // {title: '联系电话', key: 'telephone', ellipsisTooltip: true, align: 'center'},
        // {title: '电子邮箱', key: 'mailBox', ellipsisTooltip: true, align: 'center'},
        // {title: '传真', key: 'facsimile', ellipsisTooltip: true, align: 'center'},
        {
            title: '有效标志', key: 'validFlag', ellipsisTooltip: true, align: 'center',
            width: 150,
            customSlot: function (obj: any) {
                return 'Y' === obj.data.validFlag ? '有效' : '无效';

            }
        },
        {title: '操作', width: '280px', customSlot: 'operator', key: 'operator', align: 'center', fixed: 'right'}
    ];

    /**
     *  新增/修改/查看 弹窗
     */
    const refDeptInfoComponent = ref();

    return {
        queryDeptInfoParams,
        tableDeptInfoData,
        deptInfo,
        deptInfoColumns,
        refDeptInfoComponent
    }

}