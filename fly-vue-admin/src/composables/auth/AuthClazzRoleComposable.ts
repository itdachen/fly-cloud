
import {layer} from "@layui/layui-vue";

import {StringUtils} from '@/fly/utils/StringUtils';
import useAuthClazzRoleBuilder, {AuthClazzRole, AuthClazzRoleQuery} from '@/api/auth/model/AuthClazzRoleModel';
import AuthClazzRoleApi from '@/api/auth/AuthClazzRoleApi';
import {FormTypeEnum} from "@/fly/biz/BizModel";
import {LayTableUtils} from "@/fly/utils/LayTableUtils";
import useLayTableComposable from "@/fly/table";

const {
    authClazzRole,
    authClazzRoleColumns,
    queryAuthClazzRoleParams,
    tableAuthClazzRoleData,
    refAuthClazzRoleComponent
} = useAuthClazzRoleBuilder();
const {flyLayPage} = useLayTableComposable();

const authClazzRoleApi = new AuthClazzRoleApi();


/**
 * 身份岗位管理表 处理
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:50
 */
export default function useAuthClazzRoleComposable() {

    /**
     * 加载分页数据
     * @author 王大宸
     * @param params
     */
    const loadTableAuthClazzRoleData = (params: AuthClazzRoleQuery) => {
        tableAuthClazzRoleData.loading = true;
        authClazzRoleApi.page(params).then(res => {
            tableAuthClazzRoleData.total = res.data.total;
            tableAuthClazzRoleData.rows = res.data.rows;
            tableAuthClazzRoleData.loading = false;
        });
    };

    /**
     * 重新加载数据
     * @author 王大宸
     * @param page
     * @param limit
     */
    const reloadAuthClazzRoleDate = (page: number = 1, limit: number = 10) => {
        queryAuthClazzRoleParams.page = page;
        queryAuthClazzRoleParams.limit = limit;
        loadTableAuthClazzRoleData(queryAuthClazzRoleParams);
    };

    /**
     * 新增按钮
     */
    const onTapAuthClazzRoleAdd = () => {
        refAuthClazzRoleComponent.value?.open(FormTypeEnum.ADD, null);
    }

    /**
     * 编辑按钮
     * @param data 编辑时的数据信息
     */
    const onTapAuthClazzRoleEdit = (data: AuthClazzRole) => {
        refAuthClazzRoleComponent.value?.open(FormTypeEnum.EDIT, data);
    }

    /**
     * 查看按钮
     * @param data 查看时的数据信息
     */
    const onTapAuthClazzRoleView = (data: AuthClazzRole) => {
        refAuthClazzRoleComponent.value?.open(FormTypeEnum.VIEW, data);
    }

    /**
     * 数据保存处理(新增/编辑)
     * @param data 保存的数据
     */
    const authClazzRoleDataHandler = (data: AuthClazzRole) => {
      if (StringUtils.isEmpty(data.id)){
         authClazzRoleApi.saveInfo(data).then((res) => {
              reloadAuthClazzRoleDate(1, flyLayPage.limit); // 表格重新加载数据
              layer.msg(res.msg, {time: 1500, icon: 1}); // 操作成功提示
              refAuthClazzRoleComponent.value?.onTapClose();  // 关闭弹窗
          })
      } else {
          authClazzRoleApi.updateInfo(data, data.id).then((res) => {
              reloadAuthClazzRoleDate(1, flyLayPage.limit);  // 表格重新加载数据
              layer.msg(res.msg, {time: 1500, icon: 1}); // 操作成功提示
              refAuthClazzRoleComponent.value?.onTapClose();  // 关闭弹窗
          })
      }
    }
    

    /**
     * 删除按钮
     * @param id 需要删除的数据主键
     */
    const removeAuthClazzRoleHandler = (id: string, title?: string) => {
        LayTableUtils.remove({
            title: title,
            callback: () => {
                authClazzRoleApi.remove(id).then((res) => {
                    reloadAuthClazzRoleDate(1, flyLayPage.limit);
                    layer.msg(res.msg, {time: 1500, icon: 1});
                })
            }
        })
    }


    return {
        refAuthClazzRoleComponent,
        authClazzRole,
        tableAuthClazzRoleData,
        authClazzRoleColumns,
        queryAuthClazzRoleParams,
        /* 加载表格 */
        reloadAuthClazzRoleDate,
        loadTableAuthClazzRoleData,

        /* 新增, 编辑, 查看按钮 */
        onTapAuthClazzRoleAdd,
        onTapAuthClazzRoleEdit,
        onTapAuthClazzRoleView,

        /* 接口 */
        removeAuthClazzRoleHandler,
        authClazzRoleDataHandler,
        flyLayPage

    };
}