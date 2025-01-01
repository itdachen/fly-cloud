
import {layer} from "@layui/layui-vue";

import {StringUtils} from '@/fly/utils/StringUtils';
import useAuthGrantMenuBuilder, {AuthGrantMenu, AuthGrantMenuQuery} from '@/api/auth/model/AuthGrantMenuModel';
import AuthGrantMenuApi from '@/api/auth/AuthGrantMenuApi';
import {FormTypeEnum} from "@/fly/biz/BizModel";
import {LayTableUtils} from "@/fly/utils/LayTableUtils";
import useLayTableComposable from "@/fly/table";

const {
    authGrantMenu,
    authGrantMenuColumns,
    queryAuthGrantMenuParams,
    tableAuthGrantMenuData,
    refAuthGrantMenuComponent
} = useAuthGrantMenuBuilder();
const {flyLayPage} = useLayTableComposable();

const authGrantMenuApi = new AuthGrantMenuApi();


/**
 * 权限下发 处理
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:49
 */
export default function useAuthGrantMenuComposable() {

    /**
     * 加载分页数据
     * @author 王大宸
     * @param params
     */
    const loadTableAuthGrantMenuData = (params: AuthGrantMenuQuery) => {
        tableAuthGrantMenuData.loading = true;
        authGrantMenuApi.page(params).then(res => {
            tableAuthGrantMenuData.total = res.data.total;
            tableAuthGrantMenuData.rows = res.data.rows;
            tableAuthGrantMenuData.loading = false;
        });
    };

    /**
     * 重新加载数据
     * @author 王大宸
     * @param page
     * @param limit
     */
    const reloadAuthGrantMenuDate = (page: number = 1, limit: number = 10) => {
        queryAuthGrantMenuParams.page = page;
        queryAuthGrantMenuParams.limit = limit;
        loadTableAuthGrantMenuData(queryAuthGrantMenuParams);
    };

    /**
     * 新增按钮
     */
    const onTapAuthGrantMenuAdd = () => {
        refAuthGrantMenuComponent.value?.open(FormTypeEnum.ADD, null);
    }

    /**
     * 编辑按钮
     * @param data 编辑时的数据信息
     */
    const onTapAuthGrantMenuEdit = (data: AuthGrantMenu) => {
        refAuthGrantMenuComponent.value?.open(FormTypeEnum.EDIT, data);
    }

    /**
     * 查看按钮
     * @param data 查看时的数据信息
     */
    const onTapAuthGrantMenuView = (data: AuthGrantMenu) => {
        refAuthGrantMenuComponent.value?.open(FormTypeEnum.VIEW, data);
    }

    /**
     * 数据保存处理(新增/编辑)
     * @param data 保存的数据
     */
    const authGrantMenuDataHandler = (data: AuthGrantMenu) => {
      if (StringUtils.isEmpty(data.id)){
         authGrantMenuApi.saveInfo(data).then((res) => {
              reloadAuthGrantMenuDate(1, flyLayPage.limit); // 表格重新加载数据
              layer.msg(res.msg, {time: 1500, icon: 1}); // 操作成功提示
              refAuthGrantMenuComponent.value?.onTapClose();  // 关闭弹窗
          })
      } else {
          authGrantMenuApi.updateInfo(data, data.id).then((res) => {
              reloadAuthGrantMenuDate(1, flyLayPage.limit);  // 表格重新加载数据
              layer.msg(res.msg, {time: 1500, icon: 1}); // 操作成功提示
              refAuthGrantMenuComponent.value?.onTapClose();  // 关闭弹窗
          })
      }
    }
    

    /**
     * 删除按钮
     * @param id 需要删除的数据主键
     */
    const removeAuthGrantMenuHandler = (id: string, title?: string) => {
        LayTableUtils.remove({
            title: title,
            callback: () => {
                authGrantMenuApi.remove(id).then((res) => {
                    reloadAuthGrantMenuDate(1, flyLayPage.limit);
                    layer.msg(res.msg, {time: 1500, icon: 1});
                })
            }
        })
    }


    return {
        refAuthGrantMenuComponent,
        authGrantMenu,
        tableAuthGrantMenuData,
        authGrantMenuColumns,
        queryAuthGrantMenuParams,
        /* 加载表格 */
        reloadAuthGrantMenuDate,
        loadTableAuthGrantMenuData,

        /* 新增, 编辑, 查看按钮 */
        onTapAuthGrantMenuAdd,
        onTapAuthGrantMenuEdit,
        onTapAuthGrantMenuView,

        /* 接口 */
        removeAuthGrantMenuHandler,
        authGrantMenuDataHandler,
        flyLayPage

    };
}