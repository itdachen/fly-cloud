import {layer} from "@layui/layui-vue";

import {StringUtils} from '@/fly/utils/StringUtils';
import useTenantInfoBuilder, {TenantInfo, TenantInfoQuery} from '@/api/admin/model/TenantInfoModel';
import TenantInfoApi from '@/api/admin/TenantInfoApi';
import {FormTypeEnum} from "@/fly/biz/BizModel";
import {LayTableUtils} from "@/fly/utils/LayTableUtils";
import useLayTableComposable from "@/fly/table";

const {
    tenantInfo,
    tenantInfoColumns,
    queryTenantInfoParams,
    tableTenantInfoData,
    refTenantInfoComponent
} = useTenantInfoBuilder();

const {flyLayPage} = useLayTableComposable();

const tenantInfoApi = new TenantInfoApi();


/**
 * 租户/公司信息 处理
 *
 * @author 王大宸
 * @date 2024-12-19 11:07:05
 */
export default function useTenantInfoComposable() {

    /**
     * 加载分页数据
     * @author 王大宸
     * @param params
     */
    const loadTableTenantInfoData = (params: TenantInfoQuery) => {
        tableTenantInfoData.loading = true;
        params.limit = flyLayPage.limit;
        tenantInfoApi.page(params).then(res => {
            tableTenantInfoData.total = res.data.total;
            tableTenantInfoData.rows = res.data.rows;
            tableTenantInfoData.loading = false;
        });
    };

    /**
     * 重新加载数据
     * @author 王大宸
     * @param page
     * @param limit
     */
    const reloadTenantInfoDate = (page: number = 1) => {
        queryTenantInfoParams.page = 1;
        loadTableTenantInfoData(queryTenantInfoParams);
    };

    /**
     * 新增按钮
     */
    const onTapTenantInfoAdd = () => {
        refTenantInfoComponent.value?.open(FormTypeEnum.ADD, null);
    }

    /**
     * 编辑按钮
     * @param data 编辑时的数据信息
     */
    const onTapTenantInfoEdit = (data: TenantInfo) => {
        refTenantInfoComponent.value?.open(FormTypeEnum.EDIT, data);
    }

    /**
     * 查看按钮
     * @param data 查看时的数据信息
     */
    const onTapTenantInfoView = (data: TenantInfo) => {
        refTenantInfoComponent.value?.open(FormTypeEnum.VIEW, data);
    }

    /**
     * 数据保存处理(新增/编辑)
     * @param data 保存的数据
     */
    const tenantInfoDataHandler = (data: TenantInfo) => {
        if (StringUtils.isEmpty(data.id)) {
            tenantInfoApi.saveInfo(data).then((res: { msg: string; }) => {
                queryTenantInfoParams.page = 1;
                queryTenantInfoParams.limit = 10;
                loadTableTenantInfoData(queryTenantInfoParams)
                layer.msg(res.msg, {time: 1500, icon: 1})
            })
        } else {
            tenantInfoApi.updateInfo(data, data.id).then((res: { msg: string; }) => {
                queryTenantInfoParams.page = 1;
                queryTenantInfoParams.limit = 10;
                loadTableTenantInfoData(queryTenantInfoParams)
                layer.msg(res.msg, {time: 1500, icon: 1})
            })
        }
    }


    /**
     * 删除按钮
     * @param id 需要删除的数据主键
     */
    const removeTenantInfoHandler = (id: string, title?: string) => {
        LayTableUtils.remove({
            title: title,
            callback: () => {
                tenantInfoApi.remove(id).then((res: { msg: string; }) => {
                    reloadTenantInfoDate(1);
                    layer.msg(res.msg, {time: 1500, icon: 1});
                })
            }
        })
    }


    const TENANT_TYPE_ARR = [
        {
            value: '100',
            label: '超级管理员'
        },
        {
            value: '200',
            label: '开发者'
        },
        {
            value: '300',
            label: '运营'
        },
        {
            value: '400',
            label: '商铺'
        },
        {
            value: '500',
            label: '消费者'
        },
        {
            value: '999',
            label: '其他'
        }
    ]


    return {
        refTenantInfoComponent,
        tenantInfo,
        flyLayPage,
        tableTenantInfoData,
        tenantInfoColumns,
        queryTenantInfoParams,
        /* 加载表格 */
        reloadTenantInfoDate,
        loadTableTenantInfoData,

        /* 新增, 编辑, 查看按钮 */
        onTapTenantInfoAdd,
        onTapTenantInfoEdit,
        onTapTenantInfoView,

        /* 接口 */
        removeTenantInfoHandler,
        tenantInfoDataHandler,
        TENANT_TYPE_ARR

    };
}