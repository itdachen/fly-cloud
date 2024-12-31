import {layer} from "@layui/layui-vue";

import {StringUtils} from '@/fly/utils/StringUtils';
import useMenuInfoBuilder, {MenuInfo, MenuInfoQuery} from '@/api/admin/model/MenuInfoModel';
import MenuInfoApi from '@/api/admin/MenuInfoApi';
import {FormTypeEnum} from "@/fly/biz/BizModel";
import {LayTableUtils} from "@/fly/utils/LayTableUtils";
import useLayTableComposable from "@/fly/table";
import {ref} from "vue";

const {
    menuInfo,
    menuInfoColumns,
    queryMenuInfoParams,
    tableMenuInfoData,
    refMenuInfoComponent,
    refEditParentComponent
} = useMenuInfoBuilder();
const {flyLayPage} = useLayTableComposable();

const menuInfoApi = new MenuInfoApi();


/**
 * 菜单信息 处理
 *
 * @author 王大宸
 * @date 2024-12-30 10:26:52
 */
export default function useMenuInfoComposable() {

    /* 上级菜单ID/上级菜单名称 */
    const appId = ref<string>('');
    const parentId = ref<string>('0');
    const parentTitle = ref<string>('');
    const menuTreeData = ref();
    const menuTreeChecked = ref();

    const loadMenuTree = () => {
        menuInfoApi.findMenuTree('').then(res => {
            menuTreeChecked.value = res.data.checked;
            menuTreeData.value = res.data.data;
        })
    }

    /**
     * 加载分页数据
     * @author 王大宸
     * @param params
     */
    const loadTableMenuInfoData = (params: MenuInfoQuery) => {
        tableMenuInfoData.loading = true;
        menuInfoApi.page(params).then(res => {
            tableMenuInfoData.total = res.data.total;
            tableMenuInfoData.rows = res.data.rows;
            tableMenuInfoData.loading = false;
        });
    };

    /**
     * 重新加载数据
     * @author 王大宸
     * @param page
     * @param limit
     */
    const reloadMenuInfoDate = (page: number = 1, limit: number = 10) => {
        queryMenuInfoParams.page = page;
        queryMenuInfoParams.limit = limit;
        loadTableMenuInfoData(queryMenuInfoParams);
    };

    /**
     * 新增按钮
     */
    const onTapMenuInfoAdd = () => {
        refMenuInfoComponent.value?.open(FormTypeEnum.ADD, null, appId.value, parentId.value, parentTitle.value);
    }

    /**
     * 编辑按钮
     * @param data 编辑时的数据信息
     */
    const onTapMenuInfoEdit = (data: MenuInfo) => {
        refMenuInfoComponent.value?.open(FormTypeEnum.EDIT, data);
    }

    /**
     * 查看按钮
     * @param data 查看时的数据信息
     */
    const onTapMenuInfoView = (data: MenuInfo) => {
        refMenuInfoComponent.value?.open(FormTypeEnum.VIEW, data);
    }

    /**
     * 数据保存处理(新增/编辑)
     * @param data 保存的数据
     */
    const menuInfoDataHandler = (data: MenuInfo) => {
        if (StringUtils.isEmpty(data.id)) {
            menuInfoApi.saveInfo(data).then((res) => {
                reloadMenuInfoDate(1, flyLayPage.limit); // 表格重新加载数据
                layer.msg(res.msg, {time: 1500, icon: 1}); // 操作成功提示
                refMenuInfoComponent.value?.onTapClose();  // 关闭弹窗
            })
        } else {
            menuInfoApi.updateInfo(data, data.id).then((res) => {
                reloadMenuInfoDate(1, flyLayPage.limit);  // 表格重新加载数据
                layer.msg(res.msg, {time: 1500, icon: 1}); // 操作成功提示
                refMenuInfoComponent.value?.onTapClose();  // 关闭弹窗
            })
        }
    }


    /**
     * 删除按钮
     * @param id 需要删除的数据主键
     */
    const removeMenuInfoHandler = (id: string, title?: string) => {
        LayTableUtils.remove({
            title: title,
            callback: () => {
                menuInfoApi.remove(id).then((res) => {
                    reloadMenuInfoDate(1, flyLayPage.limit);
                    layer.msg(res.msg, {time: 1500, icon: 1});
                })
            }
        })
    }


    return {
        refMenuInfoComponent,
        refEditParentComponent,
        menuInfo,
        tableMenuInfoData,
        menuInfoColumns,
        queryMenuInfoParams,
        /* 加载表格 */
        reloadMenuInfoDate,
        loadTableMenuInfoData,

        /* 新增, 编辑, 查看按钮 */
        onTapMenuInfoAdd,
        onTapMenuInfoEdit,
        onTapMenuInfoView,

        /* 接口 */
        removeMenuInfoHandler,
        menuInfoDataHandler,
        flyLayPage,

        appId,
        parentId,
        parentTitle,
        loadMenuTree,
        menuTreeData,
        menuTreeChecked

    };
}