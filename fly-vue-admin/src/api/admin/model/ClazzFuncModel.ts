/*
 ++++++++++++++++++++++++++++++++++
 + 岗位职能管理表(确定之后不能修改)
 + @author 王大宸
 + @date 2024-12-28 18:40:48
 ++++++++++++++++++++++++++++++++++
 */

import {BizQuery} from "@/fly/biz/BizModel";
import {reactive, ref} from "vue";
import {TableData} from "axios";

/**
 * 岗位职能管理表(确定之后不能修改) 查询参数
 *
 * @author 王大宸
 * @date 2024-12-28 18:40:48
 */
export interface ClazzFuncQuery extends BizQuery {
    /** 岗位职能编码, 例如: 01-主管领导岗;02-业务岗 */
    funcCode?: string,
    /** 岗位职能标题, 例如: 01-主管领导岗;02-业务岗 */
    funcTitle?: string,
    /** 有效标志: Y-有效;N-无效 */
    validFlag?: string,
}


/**
 * 岗位职能管理表(确定之后不能修改) 向后端传值对象
 *
 * @author 王大宸
 * @date 2024-12-28 18:40:48
 */
export interface ClazzFunc {
    /** ID/类型代码: 例如:100,200,300 */
    id?: string,
    /** 岗位职能编码, 例如: 01-主管领导岗;02-业务岗 */
    funcCode?: string,
    /** 岗位职能标题, 例如: 01-主管领导岗;02-业务岗 */
    funcTitle?: string,
    /** 有效标志: Y-有效;N-无效 */
    validFlag?: string,
    /** 备注 */
    remarks?: string,
}


/**
 * 初始化方法
 */
export default function useClazzFuncBuilder() {

    /**
     * 实例化查询数据对象
     */
    const queryClazzFuncParams = reactive<ClazzFuncQuery>({
        page: 1,
        limit: 10,
        /** 岗位职能编码, 例如: 01-主管领导岗;02-业务岗 */
        funcCode: '',
        /** 岗位职能标题, 例如: 01-主管领导岗;02-业务岗 */
        funcTitle: '',
        /** 有效标志: Y-有效;N-无效 */
        validFlag: '',
    });

    /**
     * 分页数据
     */
    const tableClazzFuncData = reactive<TableData<ClazzFunc>>({
        loading: false,
        total: 0,
        rows: [],
    });

    /**
     * 实例化对象
     */
    const clazzFunc = ref<ClazzFunc>({
        /** ID/类型代码: 例如:100,200,300 */
        id: '',
        /** 岗位职能编码, 例如: 01-主管领导岗;02-业务岗 */
        funcCode: '',
        /** 岗位职能标题, 例如: 01-主管领导岗;02-业务岗 */
        funcTitle: '',
        /** 有效标志: Y-有效;N-无效 */
        validFlag: '',
        /** 备注 */
        remarks: '',
    });

    /**
     * 分页列表展示项
     */
    const clazzFuncColumns = [
        {title: '岗位职能编码', key: 'funcCode', ellipsisTooltip: true, align: 'center'},
        {
            title: '岗位职能标题',
            key: 'funcTitle',
            ellipsisTooltip: true,
            align: 'center'
        },
        {title: '有效标志', key: 'validFlag', ellipsisTooltip: true, align: 'center',
            width: 180,
            customSlot: function (obj: any) {
                return 'Y' === obj.data.validFlag ? '有效' : '无效';

            }},
        {title: '操作', width: '280px', customSlot: 'operator', key: 'operator', align: 'center', fixed: 'right'}
    ];

    /**
     *  新增/修改/查看 弹窗
     */
    const refClazzFuncComponent = ref();

    return {
        queryClazzFuncParams,
        tableClazzFuncData,
        clazzFunc,
        clazzFuncColumns,
        refClazzFuncComponent
    }

}