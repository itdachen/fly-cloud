/*
 ++++++++++++++++++++++++++++++++++
 + 部门等级管理
 + @author 王大宸
 + @date 2024-12-22 20:30:44
 ++++++++++++++++++++++++++++++++++
 */

import {BizQuery} from "@/fly/biz/BizModel";
import {reactive, ref} from "vue";
import {TableData} from "axios";

/**
 * 部门等级管理 查询参数
 *
 * @author 王大宸
 * @date 2024-12-22 20:30:44
 */
export interface DeptLevelQuery extends BizQuery {
    /** 层级代码 */
    levelCode?: string,
    /** 层级名称,例如: 00-总部; 11-省级部门; 21-市州级部门; 31-区县级;41-乡镇级;51-社区/街道 */
    levelTitle?: string,
    /** 有效标志: Y-是;N-否 */
    validFlag?: string
}


/**
 * 部门等级管理 向后端传值对象
 *
 * @author 王大宸
 * @date 2024-12-22 20:30:44
 */
export interface DeptLevel {
    /** 主键唯一标识 */
    id?: string,
    /** 租户ID */
    tenantId?: string,
    /** 层级代码 , 例如:00,11,21,31,41,51*/
    levelCode?: string,
    /** 层级名称,例如: 00-总部; 11-省级部门; 21-市州级部门; 31-区县级;41-乡镇级;51-社区/街道 */
    levelTitle?: string,
    /** 有效标志: Y-是;N-否 */
    validFlag?: string,
    /** 备注 */
    remarks?: string
}


/**
 * 初始化方法
 */
export default function useDeptLevelBuilder() {

    /**
     * 实例化查询数据对象
     */
    const queryDeptLevelParams = reactive<DeptLevelQuery>({
        page: 1,
        limit: 10,
        /** 层级代码 */
        levelCode: '',
        /** 层级名称,例如: 00-总部; 11-省级部门; 21-市州级部门; 31-区县级;41-乡镇级;51-社区/街道 */
        levelTitle: '',
        /** 有效标志: Y-是;N-否 */
        validFlag: '',
    });

    /**
     * 分页数据
     */
    const tableDeptLevelData = reactive<TableData<DeptLevel>>({
        loading: false,
        total: 0,
        rows: [],
    });

    /**
     * 实例化对象
     */
    const deptLevel = ref<DeptLevel>({
        /** 主键唯一标识, 例如:00,11,21,31,41,51 */
        id: '',
        /** 租户ID */
        tenantId: '',
        /** 层级代码 */
        levelCode: '',
        /** 层级名称,例如: 00-总部; 11-省级部门; 21-市州级部门; 31-区县级;41-乡镇级;51-社区/街道 */
        levelTitle: '',
        /** 有效标志: Y-是;N-否 */
        validFlag: '',
        /** 备注 */
        remarks: ''
    });

    /**
     * 分页列表展示项
     */
    const deptLevelColumns = [
        {title: '层级代码', key: 'levelCode', ellipsisTooltip: true, align: 'center'},
        {
            title: '层级名称',
            key: 'levelTitle',
            ellipsisTooltip: true,
            align: 'center'
        },
        {
            title: '有效标志', key: 'validFlag', ellipsisTooltip: true, align: 'center',
            customSlot: function (obj: any) {
                return 'Y' === obj.data.validFlag ? '有效' : '无效';

            }
        },
        {title: '操作', width: '280px', customSlot: 'operator', key: 'operator', align: 'center', fixed: 'right'}
    ];

    /**
     *  新增/修改/查看 弹窗
     */
    const refDeptLevelComponent = ref();

    return {
        queryDeptLevelParams,
        tableDeptLevelData,
        deptLevel,
        deptLevelColumns,
        refDeptLevelComponent
    }

}