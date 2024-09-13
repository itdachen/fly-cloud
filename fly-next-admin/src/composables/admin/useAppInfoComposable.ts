import StringUtils from '/@/fly/utils/StringUtils';
import useCustomTableComposable from '/@/hooks/table/TableComposables';
import {DialogTypeEnum} from '/@/components/dialog/DialogModel';
import useAppInfoBuilder, {AppInfo, AppInfoQuery} from "/@/api/admin/models/AppInfoModel";
import AppInfoApi from '/@/api/admin/AppInfoApi'

const appInfoApi = new AppInfoApi();
const {successMsg, confirmMsgBox} = useCustomTableComposable();
const {
    appInfo,
    columns,
    queryAppInfoParams,
    tableData,
    refAppInfo
} = useAppInfoBuilder();


/**
 * 应用信息 处理
 *
 * @author 王大宸
 * @date 2024-09-13 11:13:39
 */
export default function useAppInfoComposable() {

    /**
     * 加载分页数据
     * @author 王大宸
     * @param params
     */
    const loadTableData = (params: AppInfoQuery) => {
        appInfoApi.page(params).then(res => {
            tableData.total = res.data.total;
            tableData.rows = res.data.rows;
        });
    };

    /**
     * 重新加载数据
     * @author 王大宸
     * @param page
     * @param limit
     */
    const reloadDate = (page: number = 1, limit: number = 10) => {
        queryAppInfoParams.page = page;
        queryAppInfoParams.limit = limit;
        loadTableData(queryAppInfoParams);
    };

    /**
     * 搜索处理
     * @author 王大宸
     * @param params
     */
    const tapSearchHandler = (params: AppInfoQuery) => {
        params.page = 1;
        loadTableData(params);
    };

    /**
     * 表单提交
     * @author 王大宸
     * @param data 需要提交的参数
     */
    const tapSubmitHandler = (data: AppInfo) => {
        let id: string | undefined = data.id;
        if (StringUtils.isEmpty(id)) {
            appInfoApi.saveInfo(data).then(res => {
                successMsg(res.msg);
                refAppInfo.value?.onClose();
                loadTableData(queryAppInfoParams);
            });
        } else {
            appInfoApi.updateInfo(data, data.id).then(res => {
                successMsg(res.msg);
                refAppInfo.value?.onClose();
                loadTableData(queryAppInfoParams);
            });
        }
    };

    /**
     * 删除
     * @author 王大宸
     * @param id 需要删除的时间id
     * @param title 标题
     */
    const tapRemoveHandler = (id: string, title: string) => {
        confirmMsgBox('数据删除后将无法恢复，确定要删除 ' + title + ' 吗?').then(res => {
            appInfoApi.remove(id).then(res => {
                successMsg(res.msg);
                loadTableData(queryAppInfoParams);
            });
        });
    };


    /**
     * 新增按钮处理
     * @author 王大宸
     */
    const tapSaveHandler = () => {
        refAppInfo.value?.show(DialogTypeEnum.SAVE, null);
    };

    /**
     * 编辑按钮
     * @author 王大宸
     * @param data
     */
    const tapUpdateHandler = (data: AppInfo) => {
        refAppInfo.value?.show(DialogTypeEnum.UPDATE, data);
    };

    /**
     * 查看按钮处理
     * @author 王大宸
     * @param data
     */
    const tapViewHandler = (data: AppInfo) => {
        refAppInfo.value?.show(DialogTypeEnum.VIEW, data);
    };

    return {
        refAppInfo,
        appInfo,
        tableData,
        columns,
        queryAppInfoParams,
        tapSearchHandler,
        tapSaveHandler,
        tapUpdateHandler,
        tapViewHandler,
        tapRemoveHandler,
        tapSubmitHandler,
        reloadDate,
        loadTableData
    };
}