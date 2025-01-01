
import {layer} from "@layui/layui-vue";

import {StringUtils} from '@/fly/utils/StringUtils';
import useAuthClazzMenuBuilder, {AuthClazzMenu, AuthClazzMenuQuery} from '@/api/auth/model/AuthClazzMenuModel';
import AuthClazzMenuApi from '@/api/auth/AuthClazzMenuApi';
import {FormTypeEnum} from "@/fly/biz/BizModel";
import {LayTableUtils} from "@/fly/utils/LayTableUtils";
import useLayTableComposable from "@/fly/table";

const {
    authClazzMenu,
    authClazzMenuColumns,
    queryAuthClazzMenuParams,
    tableAuthClazzMenuData,
    refAuthClazzMenuComponent
} = useAuthClazzMenuBuilder();
const {flyLayPage} = useLayTableComposable();

const authClazzMenuApi = new AuthClazzMenuApi();


/**
 * 岗位菜单 处理
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:50
 */
export default function useAuthClazzMenuComposable() {

    /**
     * 加载分页数据
     * @author 王大宸
     * @param params
     */
    const loadTableAuthClazzMenuData = (params: AuthClazzMenuQuery) => {
        tableAuthClazzMenuData.loading = true;
        authClazzMenuApi.page(params).then(res => {
            tableAuthClazzMenuData.total = res.data.total;
            tableAuthClazzMenuData.rows = res.data.rows;
            tableAuthClazzMenuData.loading = false;
        });
    };

    /**
     * 重新加载数据
     * @author 王大宸
     * @param page
     * @param limit
     */
    const reloadAuthClazzMenuDate = (page: number = 1, limit: number = 10) => {
        queryAuthClazzMenuParams.page = page;
        queryAuthClazzMenuParams.limit = limit;
        loadTableAuthClazzMenuData(queryAuthClazzMenuParams);
    };

    /**
     * 新增按钮
     */
    const onTapAuthClazzMenuAdd = () => {
        refAuthClazzMenuComponent.value?.open(FormTypeEnum.ADD, null);
    }

    /**
     * 编辑按钮
     * @param data 编辑时的数据信息
     */
    const onTapAuthClazzMenuEdit = (data: AuthClazzMenu) => {
        refAuthClazzMenuComponent.value?.open(FormTypeEnum.EDIT, data);
    }

    /**
     * 查看按钮
     * @param data 查看时的数据信息
     */
    const onTapAuthClazzMenuView = (data: AuthClazzMenu) => {
        refAuthClazzMenuComponent.value?.open(FormTypeEnum.VIEW, data);
    }

    /**
     * 数据保存处理(新增/编辑)
     * @param data 保存的数据
     */
    const authClazzMenuDataHandler = (data: AuthClazzMenu) => {
      if (StringUtils.isEmpty(data.id)){
         authClazzMenuApi.saveInfo(data).then((res) => {
              reloadAuthClazzMenuDate(1, flyLayPage.limit); // 表格重新加载数据
              layer.msg(res.msg, {time: 1500, icon: 1}); // 操作成功提示
              refAuthClazzMenuComponent.value?.onTapClose();  // 关闭弹窗
          })
      } else {
          authClazzMenuApi.updateInfo(data, data.id).then((res) => {
              reloadAuthClazzMenuDate(1, flyLayPage.limit);  // 表格重新加载数据
              layer.msg(res.msg, {time: 1500, icon: 1}); // 操作成功提示
              refAuthClazzMenuComponent.value?.onTapClose();  // 关闭弹窗
          })
      }
    }
    

    /**
     * 删除按钮
     * @param id 需要删除的数据主键
     */
    const removeAuthClazzMenuHandler = (id: string, title?: string) => {
        LayTableUtils.remove({
            title: title,
            callback: () => {
                authClazzMenuApi.remove(id).then((res) => {
                    reloadAuthClazzMenuDate(1, flyLayPage.limit);
                    layer.msg(res.msg, {time: 1500, icon: 1});
                })
            }
        })
    }


    return {
        refAuthClazzMenuComponent,
        authClazzMenu,
        tableAuthClazzMenuData,
        authClazzMenuColumns,
        queryAuthClazzMenuParams,
        /* 加载表格 */
        reloadAuthClazzMenuDate,
        loadTableAuthClazzMenuData,

        /* 新增, 编辑, 查看按钮 */
        onTapAuthClazzMenuAdd,
        onTapAuthClazzMenuEdit,
        onTapAuthClazzMenuView,

        /* 接口 */
        removeAuthClazzMenuHandler,
        authClazzMenuDataHandler,
        flyLayPage

    };
}