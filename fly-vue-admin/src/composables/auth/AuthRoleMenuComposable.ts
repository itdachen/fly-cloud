
import {layer} from "@layui/layui-vue";

import {StringUtils} from '@/fly/utils/StringUtils';
import useAuthRoleMenuBuilder, {AuthRoleMenu, AuthRoleMenuQuery} from '@/api/auth/model/AuthRoleMenuModel';
import AuthRoleMenuApi from '@/api/auth/AuthRoleMenuApi';
import {FormTypeEnum} from "@/fly/biz/BizModel";
import {LayTableUtils} from "@/fly/utils/LayTableUtils";
import useLayTableComposable from "@/fly/table";

const {
    authRoleMenu,
    authRoleMenuColumns,
    queryAuthRoleMenuParams,
    tableAuthRoleMenuData,
    refAuthRoleMenuComponent
} = useAuthRoleMenuBuilder();
const {flyLayPage} = useLayTableComposable();

const authRoleMenuApi = new AuthRoleMenuApi();


/**
 * 人员身份菜单 处理
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:49
 */
export default function useAuthRoleMenuComposable() {

    /**
     * 加载分页数据
     * @author 王大宸
     * @param params
     */
    const loadTableAuthRoleMenuData = (params: AuthRoleMenuQuery) => {
        tableAuthRoleMenuData.loading = true;
        authRoleMenuApi.page(params).then(res => {
            tableAuthRoleMenuData.total = res.data.total;
            tableAuthRoleMenuData.rows = res.data.rows;
            tableAuthRoleMenuData.loading = false;
        });
    };

    /**
     * 重新加载数据
     * @author 王大宸
     * @param page
     * @param limit
     */
    const reloadAuthRoleMenuDate = (page: number = 1, limit: number = 10) => {
        queryAuthRoleMenuParams.page = page;
        queryAuthRoleMenuParams.limit = limit;
        loadTableAuthRoleMenuData(queryAuthRoleMenuParams);
    };

    /**
     * 新增按钮
     */
    const onTapAuthRoleMenuAdd = () => {
        refAuthRoleMenuComponent.value?.open(FormTypeEnum.ADD, null);
    }

    /**
     * 编辑按钮
     * @param data 编辑时的数据信息
     */
    const onTapAuthRoleMenuEdit = (data: AuthRoleMenu) => {
        refAuthRoleMenuComponent.value?.open(FormTypeEnum.EDIT, data);
    }

    /**
     * 查看按钮
     * @param data 查看时的数据信息
     */
    const onTapAuthRoleMenuView = (data: AuthRoleMenu) => {
        refAuthRoleMenuComponent.value?.open(FormTypeEnum.VIEW, data);
    }

    /**
     * 数据保存处理(新增/编辑)
     * @param data 保存的数据
     */
    const authRoleMenuDataHandler = (data: AuthRoleMenu) => {
      if (StringUtils.isEmpty(data.id)){
         authRoleMenuApi.saveInfo(data).then((res) => {
              reloadAuthRoleMenuDate(1, flyLayPage.limit); // 表格重新加载数据
              layer.msg(res.msg, {time: 1500, icon: 1}); // 操作成功提示
              refAuthRoleMenuComponent.value?.onTapClose();  // 关闭弹窗
          })
      } else {
          authRoleMenuApi.updateInfo(data, data.id).then((res) => {
              reloadAuthRoleMenuDate(1, flyLayPage.limit);  // 表格重新加载数据
              layer.msg(res.msg, {time: 1500, icon: 1}); // 操作成功提示
              refAuthRoleMenuComponent.value?.onTapClose();  // 关闭弹窗
          })
      }
    }
    

    /**
     * 删除按钮
     * @param id 需要删除的数据主键
     */
    const removeAuthRoleMenuHandler = (id: string, title?: string) => {
        LayTableUtils.remove({
            title: title,
            callback: () => {
                authRoleMenuApi.remove(id).then((res) => {
                    reloadAuthRoleMenuDate(1, flyLayPage.limit);
                    layer.msg(res.msg, {time: 1500, icon: 1});
                })
            }
        })
    }


    return {
        refAuthRoleMenuComponent,
        authRoleMenu,
        tableAuthRoleMenuData,
        authRoleMenuColumns,
        queryAuthRoleMenuParams,
        /* 加载表格 */
        reloadAuthRoleMenuDate,
        loadTableAuthRoleMenuData,

        /* 新增, 编辑, 查看按钮 */
        onTapAuthRoleMenuAdd,
        onTapAuthRoleMenuEdit,
        onTapAuthRoleMenuView,

        /* 接口 */
        removeAuthRoleMenuHandler,
        authRoleMenuDataHandler,
        flyLayPage

    };
}