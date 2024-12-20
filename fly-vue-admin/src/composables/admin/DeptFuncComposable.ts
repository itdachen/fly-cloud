import {layer} from "@layui/layui-vue";

import {StringUtils} from '@/fly/utils/StringUtils';
import useDeptFuncBuilder, {DeptFunc, DeptFuncQuery} from '@/api/admin/model/DeptFuncModel';
import DeptFuncApi from '@/api/admin/DeptFuncApi';
import {FormTypeEnum} from "@/fly/biz/BizModel";
import {LayTableUtils} from "@/fly/utils/LayTableUtils";
import useLayTableComposable from "@/fly/table";

const {
    deptFunc,
    deptFuncColumns,
    queryDeptFuncParams,
    tableDeptFuncData,
    refDeptFuncComponent
} = useDeptFuncBuilder();

const {flyLayPage} = useLayTableComposable();

const deptFuncApi = new DeptFuncApi();


/**
 * 部门职能 处理
 *
 * @author 王大宸
 * @date 2024-12-20 14:40:35
 */
export default function useDeptFuncComposable() {

    /**
     * 加载分页数据
     * @author 王大宸
     * @param params
     */
    const loadTableDeptFuncData = (params: DeptFuncQuery) => {
        tableDeptFuncData.loading = true;
        deptFuncApi.page(params).then(res => {
            tableDeptFuncData.total = res.data.total;
            tableDeptFuncData.rows = res.data.rows;
            tableDeptFuncData.loading = false;
        });
    };

    /**
     * 重新加载数据
     * @author 王大宸
     * @param page
     * @param limit
     */
    const reloadDeptFuncDate = (page: number = 1, limit: number = 10) => {
        queryDeptFuncParams.page = page;
        queryDeptFuncParams.limit = limit;
        loadTableDeptFuncData(queryDeptFuncParams);
    };

    /**
     * 新增按钮
     */
    const onTapDeptFuncAdd = () => {
        refDeptFuncComponent.value?.open(FormTypeEnum.ADD, null);
    }

    /**
     * 编辑按钮
     * @param data 编辑时的数据信息
     */
    const onTapDeptFuncEdit = (data: DeptFunc) => {
        refDeptFuncComponent.value?.open(FormTypeEnum.EDIT, data);
    }

    /**
     * 查看按钮
     * @param data 查看时的数据信息
     */
    const onTapDeptFuncView = (data: DeptFunc) => {
        refDeptFuncComponent.value?.open(FormTypeEnum.VIEW, data);
    }

    /**
     * 数据保存处理(新增/编辑)
     * @param data 保存的数据
     */
    const deptFuncDataHandler = (data: DeptFunc) => {
        if (StringUtils.isEmpty(data.id)) {
            deptFuncApi.saveInfo(data).then((res: { msg: string; }) => {
                reloadDeptFuncDate(1, flyLayPage.limit);
                layer.msg(res.msg, {time: 1500, icon: 1})
            })
        } else {
            deptFuncApi.updateInfo(data, data.id).then((res: { msg: string; }) => {
                reloadDeptFuncDate(1, flyLayPage.limit);
                layer.msg(res.msg, {time: 1500, icon: 1})
            })
        }
    }


    /**
     * 删除按钮
     * @param id 需要删除的数据主键
     */
    const removeDeptFuncHandler = (id: string, title?: string) => {
        LayTableUtils.remove({
            title: title,
            callback: () => {
                deptFuncApi.remove(id).then((res: { msg: string; }) => {
                    reloadDeptFuncDate(1, flyLayPage.limit);
                    layer.msg(res.msg, {time: 1500, icon: 1});
                })
            }
        })
    }


    return {
        refDeptFuncComponent,
        deptFunc,
        flyLayPage,
        tableDeptFuncData,
        deptFuncColumns,
        queryDeptFuncParams,
        /* 加载表格 */
        reloadDeptFuncDate,
        loadTableDeptFuncData,

        /* 新增, 编辑, 查看按钮 */
        onTapDeptFuncAdd,
        onTapDeptFuncEdit,
        onTapDeptFuncView,

        /* 接口 */
        removeDeptFuncHandler,
        deptFuncDataHandler

    };
}