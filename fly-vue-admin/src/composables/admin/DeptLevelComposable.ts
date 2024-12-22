
import {layer} from "@layui/layui-vue";

import {StringUtils} from '@/fly/utils/StringUtils';
import useDeptLevelBuilder, {DeptLevel, DeptLevelQuery} from '@/api/admin/model/DeptLevelModel';
import DeptLevelApi from '@/api/admin/DeptLevelApi';
import {FormTypeEnum} from "@/fly/biz/BizModel";
import {LayTableUtils} from "@/fly/utils/LayTableUtils";
import useLayTableComposable from "@/fly/table";

const {
    deptLevel,
    deptLevelColumns,
    queryDeptLevelParams,
    tableDeptLevelData,
    refDeptLevelComponent
} = useDeptLevelBuilder();
const {flyLayPage} = useLayTableComposable();

const deptLevelApi = new DeptLevelApi();


/**
 * 部门等级管理 处理
 *
 * @author 王大宸
 * @date 2024-12-22 20:30:44
 */
export default function useDeptLevelComposable() {

    /**
     * 加载分页数据
     * @author 王大宸
     * @param params
     */
    const loadTableDeptLevelData = (params: DeptLevelQuery) => {
        tableDeptLevelData.loading = true;
        deptLevelApi.page(params).then(res => {
            tableDeptLevelData.total = res.data.total;
            tableDeptLevelData.rows = res.data.rows;
            tableDeptLevelData.loading = false;
        });
    };

    /**
     * 重新加载数据
     * @author 王大宸
     * @param page
     * @param limit
     */
    const reloadDeptLevelDate = (page: number = 1, limit: number = 10) => {
        queryDeptLevelParams.page = page;
        queryDeptLevelParams.limit = limit;
        loadTableDeptLevelData(queryDeptLevelParams);
    };

    /**
     * 新增按钮
     */
    const onTapDeptLevelAdd = () => {
        refDeptLevelComponent.value?.open(FormTypeEnum.ADD, null);
    }

    /**
     * 编辑按钮
     * @param data 编辑时的数据信息
     */
    const onTapDeptLevelEdit = (data: DeptLevel) => {
        refDeptLevelComponent.value?.open(FormTypeEnum.EDIT, data);
    }

    /**
     * 查看按钮
     * @param data 查看时的数据信息
     */
    const onTapDeptLevelView = (data: DeptLevel) => {
        refDeptLevelComponent.value?.open(FormTypeEnum.VIEW, data);
    }

    /**
     * 数据保存处理(新增/编辑)
     * @param data 保存的数据
     */
    const deptLevelDataHandler = (data: DeptLevel) => {
      if (StringUtils.isEmpty(data.id)){
         deptLevelApi.saveInfo(data).then((res) => {
              reloadDeptLevelDate(1, flyLayPage.limit); // 表格重新加载数据
              layer.msg(res.msg, {time: 1500, icon: 1}); // 操作成功提示
              refDeptLevelComponent.value?.onTapClose();  // 关闭弹窗
          })
      } else {
          deptLevelApi.updateInfo(data, data.id).then((res) => {
              reloadDeptLevelDate(1, flyLayPage.limit);  // 表格重新加载数据
              layer.msg(res.msg, {time: 1500, icon: 1}); // 操作成功提示
              refDeptLevelComponent.value?.onTapClose();  // 关闭弹窗
          })
      }
    }
    

    /**
     * 删除按钮
     * @param id 需要删除的数据主键
     */
    const removeDeptLevelHandler = (id: string, title?: string) => {
        LayTableUtils.remove({
            title: title,
            callback: () => {
                deptLevelApi.remove(id).then((res) => {
                    reloadDeptLevelDate(1, flyLayPage.limit);
                    layer.msg(res.msg, {time: 1500, icon: 1});
                })
            }
        })
    }


    return {
        refDeptLevelComponent,
        deptLevel,
        tableDeptLevelData,
        deptLevelColumns,
        queryDeptLevelParams,
        /* 加载表格 */
        reloadDeptLevelDate,
        loadTableDeptLevelData,

        /* 新增, 编辑, 查看按钮 */
        onTapDeptLevelAdd,
        onTapDeptLevelEdit,
        onTapDeptLevelView,

        /* 接口 */
        removeDeptLevelHandler,
        deptLevelDataHandler,
        flyLayPage

    };
}