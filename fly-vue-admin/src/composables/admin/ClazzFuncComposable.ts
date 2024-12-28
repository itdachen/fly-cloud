
import {layer} from "@layui/layui-vue";

import {StringUtils} from '@/fly/utils/StringUtils';
import useClazzFuncBuilder, {ClazzFunc, ClazzFuncQuery} from '@/api/admin/model/ClazzFuncModel';
import ClazzFuncApi from '@/api/admin/ClazzFuncApi';
import {FormTypeEnum} from "@/fly/biz/BizModel";
import {LayTableUtils} from "@/fly/utils/LayTableUtils";
import useLayTableComposable from "@/fly/table";

const {
    clazzFunc,
    clazzFuncColumns,
    queryClazzFuncParams,
    tableClazzFuncData,
    refClazzFuncComponent
} = useClazzFuncBuilder();
const {flyLayPage} = useLayTableComposable();

const clazzFuncApi = new ClazzFuncApi();


/**
 * 岗位职能管理表(确定之后不能修改) 处理
 *
 * @author 王大宸
 * @date 2024-12-28 18:40:48
 */
export default function useClazzFuncComposable() {

    /**
     * 加载分页数据
     * @author 王大宸
     * @param params
     */
    const loadTableClazzFuncData = (params: ClazzFuncQuery) => {
        tableClazzFuncData.loading = true;
        clazzFuncApi.page(params).then(res => {
            tableClazzFuncData.total = res.data.total;
            tableClazzFuncData.rows = res.data.rows;
            tableClazzFuncData.loading = false;
        });
    };

    /**
     * 重新加载数据
     * @author 王大宸
     * @param page
     * @param limit
     */
    const reloadClazzFuncDate = (page: number = 1, limit: number = 10) => {
        queryClazzFuncParams.page = page;
        queryClazzFuncParams.limit = limit;
        loadTableClazzFuncData(queryClazzFuncParams);
    };

    /**
     * 新增按钮
     */
    const onTapClazzFuncAdd = () => {
        refClazzFuncComponent.value?.open(FormTypeEnum.ADD, null);
    }

    /**
     * 编辑按钮
     * @param data 编辑时的数据信息
     */
    const onTapClazzFuncEdit = (data: ClazzFunc) => {
        refClazzFuncComponent.value?.open(FormTypeEnum.EDIT, data);
    }

    /**
     * 查看按钮
     * @param data 查看时的数据信息
     */
    const onTapClazzFuncView = (data: ClazzFunc) => {
        refClazzFuncComponent.value?.open(FormTypeEnum.VIEW, data);
    }

    /**
     * 数据保存处理(新增/编辑)
     * @param data 保存的数据
     */
    const clazzFuncDataHandler = (data: ClazzFunc) => {
      if (StringUtils.isEmpty(data.id)){
         clazzFuncApi.saveInfo(data).then((res) => {
              reloadClazzFuncDate(1, flyLayPage.limit); // 表格重新加载数据
              layer.msg(res.msg, {time: 1500, icon: 1}); // 操作成功提示
              refClazzFuncComponent.value?.onTapClose();  // 关闭弹窗
          })
      } else {
          clazzFuncApi.updateInfo(data, data.id).then((res) => {
              reloadClazzFuncDate(1, flyLayPage.limit);  // 表格重新加载数据
              layer.msg(res.msg, {time: 1500, icon: 1}); // 操作成功提示
              refClazzFuncComponent.value?.onTapClose();  // 关闭弹窗
          })
      }
    }
    

    /**
     * 删除按钮
     * @param id 需要删除的数据主键
     */
    const removeClazzFuncHandler = (id: string, title?: string) => {
        LayTableUtils.remove({
            title: title,
            callback: () => {
                clazzFuncApi.remove(id).then((res) => {
                    reloadClazzFuncDate(1, flyLayPage.limit);
                    layer.msg(res.msg, {time: 1500, icon: 1});
                })
            }
        })
    }


    return {
        refClazzFuncComponent,
        clazzFunc,
        tableClazzFuncData,
        clazzFuncColumns,
        queryClazzFuncParams,
        /* 加载表格 */
        reloadClazzFuncDate,
        loadTableClazzFuncData,

        /* 新增, 编辑, 查看按钮 */
        onTapClazzFuncAdd,
        onTapClazzFuncEdit,
        onTapClazzFuncView,

        /* 接口 */
        removeClazzFuncHandler,
        clazzFuncDataHandler,
        flyLayPage

    };
}