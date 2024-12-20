/*
 ++++++++++++++++++++++++++++++++++
 + 部门职能
 + @author 王大宸
 + @date 2024-12-20 14:40:35
 ++++++++++++++++++++++++++++++++++
 */

import {BizQuery} from "@/fly/biz/BizModel";
import {reactive, ref} from "vue";
import {TableData} from "axios";

/**
 * 部门职能 查询参数
 *
 * @author 王大宸
 * @date 2024-12-20 14:40:35
 */
export interface DeptFuncQuery extends BizQuery {
    /** 租户ID */
    tenantId?: string,
    /** 编码(全局唯一) */
    funcCode?: string,
    /** 标题, 例如: 100-领导办公室;200-人事部;300-研发部 */
    funcTitle?: string,
    /** 有效标志: Y-有效;N-无效 */
    validFlag?: string,
}


/**
 * 部门职能 向后端传值对象
 *
 * @author 王大宸
 * @date 2024-12-20 14:40:35
 */
export interface DeptFunc {
    /** ID/类型代码: 例如:100,200,300 */
    id?: string,
    /** 租户ID */
    tenantId?: string,
    /** 编码(全局唯一) */
    funcCode?: string,
    /** 标题, 例如: 100-领导办公室;200-人事部;300-研发部 */
    funcTitle?: string,
    /** 有效标志: Y-有效;N-无效 */
    validFlag?: string,
    /** 备注 */
    remarks?: string
}


/**
 * 初始化方法
 */
export default function useDeptFuncBuilder() {

    /**
     * 实例化查询数据对象
     */
    const queryDeptFuncParams = reactive<DeptFuncQuery>({
        page: 1,
        limit: 10,
        /** 租户ID */
        tenantId: '',
        /** 编码(全局唯一) */
        funcCode: '',
        /** 标题, 例如: 100-领导办公室;200-人事部;300-研发部 */
        funcTitle: '',
        /** 有效标志: Y-有效;N-无效 */
        validFlag: ''
    });

    /**
     * 分页数据
     */
    const tableDeptFuncData = reactive<TableData<DeptFunc>>({
        loading: false,
        total: 0,
        rows: [],
    });

    /**
     * 实例化对象
     */
    const deptFunc = ref<DeptFunc>({
        /** ID/类型代码: 例如:100,200,300 */
        id: '',
        /** 租户ID */
        tenantId: '',
        /** 编码(全局唯一) */
        funcCode: '',
        /** 标题, 例如: 100-领导办公室;200-人事部;300-研发部 */
        funcTitle: '',
        /** 有效标志: Y-有效;N-无效 */
        validFlag: '',
        /** 备注 */
        remarks: ''
    });

    /**
     * 分页列表展示项
     */
    const deptFuncColumns = [
        {title: '职能编码', key: 'funcCode', ellipsisTooltip: true, align: 'center'},
        {
            title: '职能名称',
            key: 'funcTitle',
            ellipsisTooltip: true,
            align: 'center'
        },
        {
            title: '有效标志', key: 'validFlag', ellipsisTooltip: true, align: 'center',
            width: 180,
            customSlot: function (obj: any) {
                return 'Y' === obj.data.validFlag ? '有效' : '无效';

            }
        },
        {title: '操作', width: '300px', customSlot: 'operator', key: 'operator', align: 'center', fixed: 'right'}
    ];

    /**
     *  新增/修改/查看 弹窗
     */
    const refDeptFuncComponent = ref();

    return {
        queryDeptFuncParams,
        tableDeptFuncData,
        deptFunc,
        deptFuncColumns,
        refDeptFuncComponent
    }

}