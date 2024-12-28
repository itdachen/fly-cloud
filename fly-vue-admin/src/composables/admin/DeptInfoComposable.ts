import {layer} from "@layui/layui-vue";

import {StringUtils} from '@/fly/utils/StringUtils';
import useDeptInfoBuilder, {DeptInfo, DeptInfoQuery} from '@/api/admin/model/DeptInfoModel';
import DeptInfoApi from '@/api/admin/DeptInfoApi';
import {FormTypeEnum} from "@/fly/biz/BizModel";
import {LayTableUtils} from "@/fly/utils/LayTableUtils";
import useLayTableComposable from "@/fly/table";
import {reactive, ref} from "vue";

const {
    deptInfo,
    deptInfoColumns,
    queryDeptInfoParams,
    tableDeptInfoData,
    refDeptInfoComponent
} = useDeptInfoBuilder();
const {flyLayPage} = useLayTableComposable();

const deptInfoApi = new DeptInfoApi();


/**
 * 部门信息 处理
 *
 * @author 王大宸
 * @date 2024-12-22 22:30:09
 */
export default function useDeptInfoComposable() {

    /**
     * 加载分页数据
     * @author 王大宸
     * @param params
     */
    const loadTableDeptInfoData = (params: DeptInfoQuery) => {
        tableDeptInfoData.loading = true;
        params.deptFlag = 'Y';
        deptInfoApi.page(params).then(res => {
            tableDeptInfoData.total = res.data.total;
            tableDeptInfoData.rows = res.data.rows;
            tableDeptInfoData.loading = false;
        });
    };

    const parentDeptCode = ref<string>('0');
    const deptTreeData = ref();
    const deptTreeChecked = ref();

    const loadDeptTree = () => {
        deptInfoApi.findDeptTree('N').then(res => {
            deptTreeChecked.value = res.data.checked;
            deptTreeData.value = res.data.data;
            // if (0 < res.data.checked.length) {
            //     parentDeptId.value = res.data.checked[0];
            // }
        })
    }

    /**
     * 重新加载数据
     * @author 王大宸
     * @param page
     * @param limit
     */
    const reloadDeptInfoDate = (page: number = 1, limit: number = 10) => {
        queryDeptInfoParams.page = page;
        queryDeptInfoParams.limit = limit;
        queryDeptInfoParams.deptFlag = 'Y';
        loadTableDeptInfoData(queryDeptInfoParams);
    };

    /**
     * 新增按钮
     */
    const onTapDeptInfoAdd = () => {
        if ('0' === parentDeptCode.value) {
            return;
        }
        refDeptInfoComponent.value?.open(FormTypeEnum.ADD, null, parentDeptCode);
    }

    /**
     * 编辑按钮
     * @param data 编辑时的数据信息
     */
    const onTapDeptInfoEdit = (data: DeptInfo) => {
        refDeptInfoComponent.value?.open(FormTypeEnum.EDIT, data, data.parentCode);
    }

    /**
     * 查看按钮
     * @param data 查看时的数据信息
     */
    const onTapDeptInfoView = (data: DeptInfo) => {
        refDeptInfoComponent.value?.open(FormTypeEnum.VIEW, data, data.parentCode);
    }

    /**
     * 数据保存处理(新增/编辑)
     * @param data 保存的数据
     */
    const deptInfoDataHandler = (data: DeptInfo) => {
        data.deptFlag = 'Y';
        if (StringUtils.isEmpty(data.id)) {
            deptInfoApi.saveInfo(data).then((res) => {
                reloadDeptInfoDate(1, flyLayPage.limit); // 表格重新加载数据
                layer.msg(res.msg, {time: 1500, icon: 1}); // 操作成功提示
                refDeptInfoComponent.value?.onTapClose();  // 关闭弹窗
            })
        } else {
            deptInfoApi.updateInfo(data, data.id).then((res) => {
                reloadDeptInfoDate(1, flyLayPage.limit);  // 表格重新加载数据
                layer.msg(res.msg, {time: 1500, icon: 1}); // 操作成功提示
                refDeptInfoComponent.value?.onTapClose();  // 关闭弹窗
            })
        }
    }


    /**
     * 删除按钮
     * @param id 需要删除的数据主键
     */
    const removeDeptInfoHandler = (id: string, title?: string) => {
        LayTableUtils.remove({
            title: title,
            callback: () => {
                deptInfoApi.remove(id).then((res) => {
                    reloadDeptInfoDate(1, flyLayPage.limit);
                    loadDeptTree(); // 重新加载树信息
                    layer.msg(res.msg, {time: 1500, icon: 1});
                })
            }
        })
    }


    return {
        refDeptInfoComponent,
        deptInfo,
        tableDeptInfoData,
        deptInfoColumns,
        queryDeptInfoParams,
        /* 加载表格 */
        reloadDeptInfoDate,
        loadTableDeptInfoData,

        /* 新增, 编辑, 查看按钮 */
        onTapDeptInfoAdd,
        onTapDeptInfoEdit,
        onTapDeptInfoView,

        /* 接口 */
        removeDeptInfoHandler,
        deptInfoDataHandler,
        flyLayPage,

        loadDeptTree,
        deptTreeData,
        deptTreeChecked,
        parentDeptCode

    };
}