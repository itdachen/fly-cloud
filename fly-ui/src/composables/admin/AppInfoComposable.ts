import {layer} from "@layui/layui-vue";

import {StringUtils} from '@/fly/utils/StringUtils';
import useAppInfoBuilder, {AppInfo, AppInfoQuery} from "@/api/admin/model/AppInfoModel";
import AppInfoApi from '@/api/admin/AppInfoApi';
import {FormTypeEnum} from "@/hooks/biz/BizModel";
import {LayTableUtils} from "@/fly/utils/LayTableUtils";
import {onMounted} from "vue";

const appInfoApi = new AppInfoApi();
const {
    appInfo,
    appInfoColumns,
    queryAppInfoParams,
    tableAppInfoData,
    refAppInfo
} = useAppInfoBuilder();


/**
 * 应用信息 处理
 *
 * @author 王大宸
 * @date 2024-09-05 10:00:59
 */
export default function useAppInfoComposable() {

    /**
     * 加载分页数据
     * @author 王大宸
     * @param params
     */
    const loadTableAppInfoData = (params: AppInfoQuery) => {
        tableAppInfoData.loading = true;
        appInfoApi.page(params).then(res => {
            tableAppInfoData.total = res.data.total;
            tableAppInfoData.rows = res.data.rows;
            tableAppInfoData.loading = false;
        });
    };

    /**
     * 重新加载数据
     * @author 王大宸
     * @param page
     * @param limit
     */
    const reloadAppInfoDate = (page: number = 1, limit: number = 10) => {
        queryAppInfoParams.page = page;
        queryAppInfoParams.limit = limit;
        loadTableAppInfoData(queryAppInfoParams);
    };

    /**
     * 查询
     */
    const onTapAppInfoSearch = () => {
        refAppInfo.value?.open('新增', '应用信息', null);
    }

    /**
     * 新增按钮
     */
    const onTapAppInfoAdd = () => {
        refAppInfo.value?.open(FormTypeEnum.ADD, '应用信息', null);
    }

    /**
     * 编辑按钮
     * @param data 编辑时的数据信息
     */
    const onTapAppInfoEdit = (data: AppInfo) => {
        refAppInfo.value?.open(FormTypeEnum.EDIT, '应用信息', data);
    }

    /**
     * 查看按钮
     * @param data 查看时的数据信息
     */
    const onTapAppInfoView = (data: AppInfo) => {
        refAppInfo.value?.open(FormTypeEnum.VIEW, '应用信息', data);
    }


    /**
     * 数据保存处理
     * @param data 需要保存的数据
     */
    const appInfoDataHandler = (data: AppInfo) => {
        if (StringUtils.isEmpty(data.id)) {
            appInfoApi.saveInfo(data).then((res: { msg: string; }) => {
                loadTableAppInfoData(queryAppInfoParams);
                layer.msg(res.msg, {time: 1500, icon: 1});
            })
        } else {
            appInfoApi.updateInfo(data, data.id).then((res: { msg: string; }) => {
                loadTableAppInfoData(queryAppInfoParams);
                layer.msg(res.msg, {time: 1500, icon: 1});
            })
        }
    }


    /**
     * 删除按钮
     * @param id 需要删除的数据主键
     * @param title
     */
    const removeAppInfoHandler = (id: string, title?: string) => {
        LayTableUtils.remove({
            title: title,
            callback: () => {
                appInfoApi.remove(id).then((res: { msg: string; }) => {
                    loadTableAppInfoData(queryAppInfoParams);
                    layer.msg(res.msg, {time: 1500, icon: 1})
                })
            }
        })
    }



    return {
        refAppInfo,
        appInfo,
        tableAppInfoData,
        appInfoColumns,
        queryAppInfoParams,

        /* 加载表格 */
        reloadAppInfoDate,
        loadTableAppInfoData,

        /* 搜索,新增, 编辑, 查看按钮 */
        onTapAppInfoSearch,
        onTapAppInfoAdd,
        onTapAppInfoEdit,
        onTapAppInfoView,

        /* 接口 */
        removeAppInfoHandler,
        appInfoDataHandler

    };
}