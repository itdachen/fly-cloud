
import {layer} from "@layui/layui-vue";

import {StringUtils} from '@/fly/utils/StringUtils';
import useClazzInfoBuilder, {ClazzInfo, ClazzInfoQuery} from '@/api/admin/model/ClazzInfoModel';
import ClazzInfoApi from '@/api/admin/ClazzInfoApi';
import {FormTypeEnum} from "@/fly/biz/BizModel";
import {LayTableUtils} from "@/fly/utils/LayTableUtils";
import useLayTableComposable from "@/fly/table";

const {
    clazzInfo,
    clazzInfoColumns,
    queryClazzInfoParams,
    tableClazzInfoData,
    refClazzInfoComponent
} = useClazzInfoBuilder();
const {flyLayPage} = useLayTableComposable();

const clazzInfoApi = new ClazzInfoApi();


/**
 * 岗位信息 处理
 *
 * @author 王大宸
 * @date 2024-12-28 18:40:48
 */
export default function useClazzInfoComposable() {

    /**
     * 加载分页数据
     * @author 王大宸
     * @param params
     */
    const loadTableClazzInfoData = (params: ClazzInfoQuery) => {
        tableClazzInfoData.loading = true;
        clazzInfoApi.page(params).then(res => {
            tableClazzInfoData.total = res.data.total;
            tableClazzInfoData.rows = res.data.rows;
            tableClazzInfoData.loading = false;
        });
    };

    /**
     * 重新加载数据
     * @author 王大宸
     * @param page
     * @param limit
     */
    const reloadClazzInfoDate = (page: number = 1, limit: number = 10) => {
        queryClazzInfoParams.page = page;
        queryClazzInfoParams.limit = limit;
        loadTableClazzInfoData(queryClazzInfoParams);
    };

    /**
     * 新增按钮
     */
    const onTapClazzInfoAdd = () => {
        refClazzInfoComponent.value?.open(FormTypeEnum.ADD, null);
    }

    /**
     * 编辑按钮
     * @param data 编辑时的数据信息
     */
    const onTapClazzInfoEdit = (data: ClazzInfo) => {
        refClazzInfoComponent.value?.open(FormTypeEnum.EDIT, data);
    }

    /**
     * 查看按钮
     * @param data 查看时的数据信息
     */
    const onTapClazzInfoView = (data: ClazzInfo) => {
        refClazzInfoComponent.value?.open(FormTypeEnum.VIEW, data);
    }

    /**
     * 数据保存处理(新增/编辑)
     * @param data 保存的数据
     */
    const clazzInfoDataHandler = (data: ClazzInfo) => {
      if (StringUtils.isEmpty(data.id)){
         clazzInfoApi.saveInfo(data).then((res) => {
              reloadClazzInfoDate(1, flyLayPage.limit); // 表格重新加载数据
              layer.msg(res.msg, {time: 1500, icon: 1}); // 操作成功提示
              refClazzInfoComponent.value?.onTapClose();  // 关闭弹窗
          })
      } else {
          clazzInfoApi.updateInfo(data, data.id).then((res) => {
              reloadClazzInfoDate(1, flyLayPage.limit);  // 表格重新加载数据
              layer.msg(res.msg, {time: 1500, icon: 1}); // 操作成功提示
              refClazzInfoComponent.value?.onTapClose();  // 关闭弹窗
          })
      }
    }
    

    /**
     * 删除按钮
     * @param id 需要删除的数据主键
     */
    const removeClazzInfoHandler = (id: string, title?: string) => {
        LayTableUtils.remove({
            title: title,
            callback: () => {
                clazzInfoApi.remove(id).then((res) => {
                    reloadClazzInfoDate(1, flyLayPage.limit);
                    layer.msg(res.msg, {time: 1500, icon: 1});
                })
            }
        })
    }


    return {
        refClazzInfoComponent,
        clazzInfo,
        tableClazzInfoData,
        clazzInfoColumns,
        queryClazzInfoParams,
        /* 加载表格 */
        reloadClazzInfoDate,
        loadTableClazzInfoData,

        /* 新增, 编辑, 查看按钮 */
        onTapClazzInfoAdd,
        onTapClazzInfoEdit,
        onTapClazzInfoView,

        /* 接口 */
        removeClazzInfoHandler,
        clazzInfoDataHandler,
        flyLayPage

    };
}