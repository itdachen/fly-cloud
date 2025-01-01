import {layer} from "@layui/layui-vue";

import {StringUtils} from '@/fly/utils/StringUtils';
import useAuthClazzDeptBuilder, {AuthClazzDept, AuthClazzDeptQuery} from '@/api/auth/model/AuthClazzDeptModel';
import AuthClazzDeptApi from '@/api/auth/AuthClazzDeptApi';
import {FormTypeEnum} from "@/fly/biz/BizModel";
import {LayTableUtils} from "@/fly/utils/LayTableUtils";
import useLayTableComposable from "@/fly/table";
import {DeptInfo} from "@/api/admin/model/DeptInfoModel";

const {
    authClazzDept,
    authClazzDeptColumns,
    queryAuthClazzDeptParams,
    tableAuthClazzDeptData,
    refAuthClazzDeptComponent
} = useAuthClazzDeptBuilder();
const {flyLayPage} = useLayTableComposable();

const authClazzDeptApi = new AuthClazzDeptApi();


/**
 * 部门岗位关联表 处理
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:50
 */
export default function useAuthClazzDeptComposable() {

    /**
     * 加载分页数据
     * @author 王大宸
     * @param params
     */
    const loadTableAuthClazzDeptData = (params: AuthClazzDeptQuery) => {
        tableAuthClazzDeptData.loading = true;
        authClazzDeptApi.page(params).then(res => {
            tableAuthClazzDeptData.total = res.data.total;
            tableAuthClazzDeptData.rows = res.data.rows;
            tableAuthClazzDeptData.loading = false;
        });
    };

    /**
     * 重新加载数据
     * @author 王大宸
     * @param page
     * @param limit
     */
    const reloadAuthClazzDeptDate = (page: number = 1, limit: number = 10) => {
        queryAuthClazzDeptParams.page = page;
        queryAuthClazzDeptParams.limit = limit;
        loadTableAuthClazzDeptData(queryAuthClazzDeptParams);
    };

    /**
     * 新增按钮
     * @param deptInfo   部门信息
     */
    const onTapAuthClazzDeptAdd = (deptInfo : DeptInfo) => {
        refAuthClazzDeptComponent.value?.open(deptInfo);
    }

    /**
     * 编辑按钮
     * @param data 编辑时的数据信息
     */
    const onTapAuthClazzDeptEdit = (data: AuthClazzDept) => {
        refAuthClazzDeptComponent.value?.open(FormTypeEnum.EDIT, data);
    }

    /**
     * 查看按钮
     * @param data 查看时的数据信息
     */
    const onTapAuthClazzDeptView = (data: AuthClazzDept) => {
        refAuthClazzDeptComponent.value?.open(FormTypeEnum.VIEW, data);
    }

    /**
     * 数据保存处理(新增/编辑)
     * @param data 保存的数据
     */
    const authClazzDeptDataHandler = (data: AuthClazzDept) => {
        if (StringUtils.isEmpty(data.id)) {
            authClazzDeptApi.saveInfo(data).then((res) => {
                reloadAuthClazzDeptDate(1, flyLayPage.limit); // 表格重新加载数据
                layer.msg(res.msg, {time: 1500, icon: 1}); // 操作成功提示
                refAuthClazzDeptComponent.value?.onTapClose();  // 关闭弹窗
            })
        } else {
            authClazzDeptApi.updateInfo(data, data.id).then((res) => {
                reloadAuthClazzDeptDate(1, flyLayPage.limit);  // 表格重新加载数据
                layer.msg(res.msg, {time: 1500, icon: 1}); // 操作成功提示
                refAuthClazzDeptComponent.value?.onTapClose();  // 关闭弹窗
            })
        }
    }


    /**
     * 删除按钮
     * @param id 需要删除的数据主键
     */
    const removeAuthClazzDeptHandler = (id: string, title?: string) => {
        LayTableUtils.remove({
            title: title,
            callback: () => {
                authClazzDeptApi.remove(id).then((res) => {
                    reloadAuthClazzDeptDate(1, flyLayPage.limit);
                    layer.msg(res.msg, {time: 1500, icon: 1});
                })
            }
        })
    }


    return {
        refAuthClazzDeptComponent,
        authClazzDept,
        tableAuthClazzDeptData,
        authClazzDeptColumns,
        queryAuthClazzDeptParams,
        /* 加载表格 */
        reloadAuthClazzDeptDate,
        loadTableAuthClazzDeptData,

        /* 新增, 编辑, 查看按钮 */
        onTapAuthClazzDeptAdd,
        onTapAuthClazzDeptEdit,
        onTapAuthClazzDeptView,

        /* 接口 */
        removeAuthClazzDeptHandler,
        authClazzDeptDataHandler,
        flyLayPage

    };
}