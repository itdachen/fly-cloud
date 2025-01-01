/*
 ++++++++++++++++++++++++++++++++++
 + 部门岗位关联表
 + @author 王大宸
 + @date 2025-01-01 17:12:50
 ++++++++++++++++++++++++++++++++++
 */

import {BizQuery} from "@/fly/biz/BizModel";
import {reactive, ref} from "vue";
import {TableData} from "axios";

/**
 * 部门岗位关联表 查询参数
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:50
 */
export interface AuthClazzDeptQuery extends BizQuery {
    /** 租户ID */
    tenantId?: string,
    /** 部门代码 */
    deptCode?: string,
    /** 部门名称 */
    deptTitle?: string,
    /** 部门层级 */
    deptLevel?: string,
    /** 岗位代码 */
    clazzCode?: string,
    /** 岗位名称 */
    clazzTitle?: string,
    /** 有效标志: Y-是;N-否 */
    validFlag?: string,
    /** 删除标志: Y-已删除;N-未删除 */
    deleteFlag?: string,
}


/**
 * 部门岗位关联表 向后端传值对象
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:50
 */
export interface AuthClazzDept {
    /** ID */
    id?: string,
    ids?: string,
    /** 租户ID */
    tenantId?: string,
    /** 部门代码 */
    deptCode?: string,
    /** 部门名称 */
    deptTitle?: string,
    /** 部门层级 */
    deptLevel?: string,
    /** 岗位代码 */
    clazzCode?: string,
    /** 岗位名称 */
    clazzTitle?: string,
    /** 有效标志: Y-是;N-否 */
    validFlag?: string,
    /** 备注 */
    remarks?: string
}


/**
 * 初始化方法
 */
export default function useAuthClazzDeptBuilder() {

    /**
     * 实例化查询数据对象
     */
    const queryAuthClazzDeptParams = reactive<AuthClazzDeptQuery>({
        page: 1,
        limit: 10,
        /** 租户ID */
        tenantId: '',
        /** 部门代码 */
        deptCode: '',
        /** 部门名称 */
        deptTitle: '',
        /** 部门层级 */
        deptLevel: '',
        /** 岗位代码 */
        clazzCode: '',
        /** 岗位名称 */
        clazzTitle: '',
        /** 有效标志: Y-是;N-否 */
        validFlag: '',
        /** 删除标志: Y-已删除;N-未删除 */
        deleteFlag: ''
    });

    /**
     * 分页数据
     */
    const tableAuthClazzDeptData = reactive<TableData<AuthClazzDept>>({
        loading: false,
        total: 0,
        rows: [],
    });

    /**
     * 实例化对象
     */
    const authClazzDept = ref<AuthClazzDept>({
        /** ID */
        id: '',
        /** 租户ID */
        tenantId: '',
        /** 部门代码 */
        deptCode: '',
        /** 部门名称 */
        deptTitle: '',
        /** 部门层级 */
        deptLevel: '',
        /** 岗位代码 */
        clazzCode: '',
        /** 岗位名称 */
        clazzTitle: '',
        /** 有效标志: Y-是;N-否 */
        validFlag: '',
        /** 备注 */
        remarks: ''
    });

    /**
     * 分页列表展示项
     */
    const authClazzDeptColumns = [
        // {title: '部门代码', key: 'deptCode',  ellipsisTooltip: true, align: 'center'},
        // {title: '部门名称', key: 'deptTitle',  ellipsisTooltip: true, align: 'center'},
        // {title: '部门层级', key: 'deptLevel',  ellipsisTooltip: true, align: 'center'},
        {title: '岗位代码', key: 'clazzCode', ellipsisTooltip: true, align: 'center'},
        {title: '岗位名称', key: 'clazzTitle', ellipsisTooltip: true, align: 'center'},
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
    const refAuthClazzDeptComponent = ref();

    return {
        queryAuthClazzDeptParams,
        tableAuthClazzDeptData,
        authClazzDept,
        authClazzDeptColumns,
        refAuthClazzDeptComponent
    }

}