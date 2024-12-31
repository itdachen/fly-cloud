import {layer} from "@layui/layui-vue";

import {StringUtils} from '@/fly/utils/StringUtils';
import useElementInfoBuilder, {ElementInfo, ElementInfoQuery} from '@/api/admin/model/ElementInfoModel';
import ElementInfoApi from '@/api/admin/ElementInfoApi';
import {FormTypeEnum} from "@/fly/biz/BizModel";
import {LayTableUtils} from "@/fly/utils/LayTableUtils";
import useLayTableComposable from "@/fly/table";

const {
    elementInfo,
    elementInfoColumns,
    queryElementInfoParams,
    tableElementInfoData,
    refElementInfoComponent
} = useElementInfoBuilder();
const {flyLayPage} = useLayTableComposable();

const elementInfoApi = new ElementInfoApi();


/**
 * 资源(按钮)信息 处理
 *
 * @author 王大宸
 * @date 2024-12-30 10:26:53
 */
export default function useElementInfoComposable() {

    /**
     * 加载分页数据
     * @author 王大宸
     * @param params
     */
    const loadTableElementInfoData = (params: ElementInfoQuery) => {
        tableElementInfoData.loading = true;
        elementInfoApi.page(params).then(res => {
            tableElementInfoData.total = res.data.total;
            tableElementInfoData.rows = res.data.rows;
            tableElementInfoData.loading = false;
        });
    };

    /**
     * 重新加载数据
     * @author 王大宸
     * @param page
     * @param limit
     */
    const reloadElementInfoDate = (page: number = 1, limit: number = 10) => {
        queryElementInfoParams.page = page;
        queryElementInfoParams.limit = limit;
        loadTableElementInfoData(queryElementInfoParams);
    };

    /**
     * 新增按钮
     */
    const onTapElementInfoAdd = (appId?: string, menuId?: string, menuTitle?: string) => {
        console.log('appId: ', appId);
        console.log('menuId: ', menuId);
        console.log('menuTitle: ', menuTitle);
        refElementInfoComponent.value?.open(FormTypeEnum.ADD, null, appId, menuId, menuTitle);
    }

    /**
     * 编辑按钮
     * @param data 编辑时的数据信息
     */
    const onTapElementInfoEdit = (data: ElementInfo) => {
        refElementInfoComponent.value?.open(FormTypeEnum.EDIT, data);
    }

    /**
     * 查看按钮
     * @param data 查看时的数据信息
     */
    const onTapElementInfoView = (data: ElementInfo) => {
        refElementInfoComponent.value?.open(FormTypeEnum.VIEW, data);
    }

    /**
     * 数据保存处理(新增/编辑)
     * @param data 保存的数据
     */
    const elementInfoDataHandler = (data: ElementInfo) => {
        if (StringUtils.isEmpty(data.id)) {
            elementInfoApi.saveInfo(data).then((res) => {
                reloadElementInfoDate(1, flyLayPage.limit); // 表格重新加载数据
                layer.msg(res.msg, {time: 1500, icon: 1}); // 操作成功提示
                refElementInfoComponent.value?.onTapClose();  // 关闭弹窗
            })
        } else {
            elementInfoApi.updateInfo(data, data.id).then((res) => {
                reloadElementInfoDate(1, flyLayPage.limit);  // 表格重新加载数据
                layer.msg(res.msg, {time: 1500, icon: 1}); // 操作成功提示
                refElementInfoComponent.value?.onTapClose();  // 关闭弹窗
            })
        }
    }


    /**
     * 删除按钮
     * @param id 需要删除的数据主键
     */
    const removeElementInfoHandler = (id: string, title?: string) => {
        LayTableUtils.remove({
            title: title,
            callback: () => {
                elementInfoApi.remove(id).then((res) => {
                    reloadElementInfoDate(1, flyLayPage.limit);
                    layer.msg(res.msg, {time: 1500, icon: 1});
                })
            }
        })
    }


    return {
        refElementInfoComponent,
        elementInfo,
        tableElementInfoData,
        elementInfoColumns,
        queryElementInfoParams,
        /* 加载表格 */
        reloadElementInfoDate,
        loadTableElementInfoData,

        /* 新增, 编辑, 查看按钮 */
        onTapElementInfoAdd,
        onTapElementInfoEdit,
        onTapElementInfoView,

        /* 接口 */
        removeElementInfoHandler,
        elementInfoDataHandler,
        flyLayPage

    };
}