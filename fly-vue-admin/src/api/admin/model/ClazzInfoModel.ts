/*
 ++++++++++++++++++++++++++++++++++
 + 岗位信息
 + @author 王大宸
 + @date 2024-12-28 18:40:48
 ++++++++++++++++++++++++++++++++++
 */

import {BizQuery} from "@/fly/biz/BizModel";
import {reactive, ref} from "vue";
import {TableData} from "axios";

/**
 * 岗位信息 查询参数
 *
 * @author 王大宸
 * @date 2024-12-28 18:40:48
 */
export interface ClazzInfoQuery extends BizQuery {
    /** 岗位名称, 例如: 信息中心-总部-主管领导岗 */
    clazzTitle?: string,
    /** 部门等级代码 */
    deptLevelCode?: string,
    /** 部门职能代码 */
    deptFuncCode?: string,
    /** 岗位职能: 主管领导岗, 业务岗等 */
    clazzFuncCode?: string,
    /** 有效标志: Y-是;N-否 */
    validFlag?: string,
}


/**
 * 岗位信息 向后端传值对象
 *
 * @author 王大宸
 * @date 2024-12-28 18:40:48
 */
export interface ClazzInfo {
    /** 主键唯一标识 */
    id?: string,
    /** 岗位代码 */
    clazzCode?: string,
    /** 岗位名称, 例如: 信息中心-总部-主管领导岗 */
    clazzTitle?: string,
    /** 部门等级代码 */
    deptLevelCode?: string,
    /** 部门等级名称 */
    deptLevelTitle?: string,
    /** 部门职能代码 */
    deptFuncCode?: string,
    /** 部门职能名称 */
    deptFuncTitle?: string,
    /** 岗位职能: 主管领导岗, 业务岗等 */
    clazzFuncCode?: string,
    /** 岗位职能名称: 主管领导岗, 业务岗等 */
    clazzFuncTitle?: string,
    /** 部门专属岗: Y-是;N-否(暂时不用,备用) */
    deptFlag?: string,
    /** 部门代码(暂时不用,备用) */
    deptCode?: string,
    /** 部门名称(暂时不用,备用) */
    deptTitle?: string,
    /** 有效标志: Y-是;N-否 */
    validFlag?: string,
    /** 备注 */
    remarks?: string
}


/**
 * 初始化方法
 */
export default function useClazzInfoBuilder() {

    /**
     * 实例化查询数据对象
     */
    const queryClazzInfoParams = reactive<ClazzInfoQuery>({
        page: 1,
        limit: 10,
        /** 岗位名称, 例如: 信息中心-总部-主管领导岗 */
        clazzTitle: '',
        /** 部门等级代码 */
        deptLevelCode: '',
        /** 部门职能代码 */
        deptFuncCode: '',
        /** 岗位职能: 主管领导岗, 业务岗等 */
        clazzFuncCode: '',
        /** 有效标志: Y-是;N-否 */
        validFlag: '',
    });

    /**
     * 分页数据
     */
    const tableClazzInfoData = reactive<TableData<ClazzInfo>>({
        loading: false,
        total: 0,
        rows: [],
    });

    /**
     * 实例化对象
     */
    const clazzInfo = ref<ClazzInfo>({
        /** 主键唯一标识 */
        id: '',
        /** 岗位代码 */
        clazzCode: '',
        /** 岗位名称, 例如: 信息中心-总部-主管领导岗 */
        clazzTitle: '',
        /** 部门等级代码 */
        deptLevelCode: '',
        /** 部门等级名称 */
        deptLevelTitle: '',
        /** 部门职能代码 */
        deptFuncCode: '',
        /** 部门职能名称 */
        deptFuncTitle: '',
        /** 岗位职能: 主管领导岗, 业务岗等 */
        clazzFuncCode: '',
        /** 岗位职能名称: 主管领导岗, 业务岗等 */
        clazzFuncTitle: '',
        /** 部门专属岗: Y-是;N-否(暂时不用,备用) */
        deptFlag: '',
        /** 部门代码(暂时不用,备用) */
        deptCode: '',
        /** 部门名称(暂时不用,备用) */
        deptTitle: '',
        /** 有效标志: Y-是;N-否 */
        validFlag: '',
        /** 备注 */
        remarks: ''
    });

    /**
     * 分页列表展示项
     */
    const clazzInfoColumns = [
        {title: '岗位代码', key: 'clazzCode', ellipsisTooltip: true, align: 'center'},
        {title: '岗位名称', key: 'clazzTitle', ellipsisTooltip: true, align: 'center'},
        {
            title: '有效标志', key: 'validFlag', ellipsisTooltip: true, align: 'center',
            width: 180,
            customSlot: function (obj: any) {
                return 'Y' === obj.data.validFlag ? '有效' : '无效';

            }
        },
        {title: '操作', width: '280px', customSlot: 'operator', key: 'operator', align: 'center', fixed: 'right'}
    ];

    /**
     *  新增/修改/查看 弹窗
     */
    const refClazzInfoComponent = ref();

    return {
        queryClazzInfoParams,
        tableClazzInfoData,
        clazzInfo,
        clazzInfoColumns,
        refClazzInfoComponent
    }

}