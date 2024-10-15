import {StringUtils} from '/@/fly/utils/StringUtils';
import useTable from '/@/components/table/index';
import {DialogTypeEnum} from "/@/components/dialog/DialogModel";
import useTenantInfoBuilder, {TenantInfo, TenantInfoQuery} from "/@/api/admin/models/TenantInfoModel";
import TenantInfoApi from '/@/api/admin/TenantInfoApi'

const tenantInfoApi = new TenantInfoApi();
const {successMsg, confirmMsgBox} = useTable();
const {
    tenantInfo,
    columns,
    tenantInfoParams,
    tableData,
    refTenantInfo
} = useTenantInfoBuilder();


/**
 * 租户/公司信息 处理
 *
 * @author 王大宸
 * @date 2024-10-15 16:27:41
 */
export default function useTenantInfoComposable() {

    /**
     * 加载分页数据
     * @author 王大宸
     * @param params
     */
    const loadTableData = (params: TenantInfoQuery) => {
        tenantInfoApi.page(params).then(res => {
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
        tenantInfoParams.page = page;
        tenantInfoParams.limit = limit;
        loadTableData(tenantInfoParams);
    };

    /**
     * 搜索处理
     * @author 王大宸
     * @param params
     */
    const searchTenantInfo = (params: TenantInfoQuery) => {
        params.page = 1;
        loadTableData(params);
    };

    /**
     * 表单提交
     * @author 王大宸
     * @param data 需要提交的参数
     */
    const tapTenantInfo = (data: TenantInfo) => {
        let id: string | undefined = data.id;
        if (StringUtils.isEmpty(id)) {
            tenantInfoApi.saveInfo(data).then(res => {
                successMsg(res.msg);
                refTenantInfo.value?.onClose();
                loadTableData(tenantInfoParams);
            });
        } else {
            tenantInfoApi.updateInfo(data, data.id).then(res => {
                successMsg(res.msg);
                refTenantInfo.value?.onClose();
                loadTableData(tenantInfoParams);
            });
        }
    };

    /**
     * 删除
     * @author 王大宸
     * @param id   需要删除的时间id
     * @param text 删除提示
     */
    const removeTenantInfo = (id: string, text: string) => {
        confirmMsgBox(text).then(() => {
            tenantInfoApi.remove(id).then(res => {
                successMsg(res.msg);
                tenantInfoParams.page = 1;
                loadTableData(tenantInfoParams);
            });
        });
    };


    /**
     * 新增按钮处理
     * @author 王大宸
     */
    const onTapTenantInfoAdd = () => {
        refTenantInfo.value?.show(DialogTypeEnum.ADD, null);
    };

    /**
     * 编辑按钮
     * @author 王大宸
     * @param data
     */
    const onTapTenantInfoEdit = (data: TenantInfo) => {
        refTenantInfo.value?.show(DialogTypeEnum.EDIT, data);
    };

    /**
     * 查看按钮处理
     * @author 王大宸
     * @param data
     */
    const onTapTenantInfoView = (data: TenantInfo) => {
        refTenantInfo.value?.show(DialogTypeEnum.VIEW, data);
    };

    return {
        refTenantInfo,
        tenantInfo,
        tableData,
        columns,
        tenantInfoParams,

        /* 加载表格 */
        searchTenantInfo,
        reloadDate,
        loadTableData,

        /* 新增, 编辑, 查看按钮 */
        onTapTenantInfoAdd,
        onTapTenantInfoEdit,
        onTapTenantInfoView,

        /* 接口 */
        removeTenantInfo,
        tapTenantInfo
    };
}