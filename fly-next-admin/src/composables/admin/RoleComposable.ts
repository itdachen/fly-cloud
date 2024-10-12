import {ref, reactive} from "vue";
import {TableData} from "axios";
import {ColumnProps} from "/@/components/table/interface";
import useStringComposable from '/@/utils/string';
import useTable from '/@/components/table/index';
import {DialogTypeEnum} from "/@/hooks/dialog/Dialog";
import {RoleDto, RoleMenuDto, RoleQuery, RoleVo} from "/@/api/admin/models/RoleModel";
import RoleApi from '/@/api/admin/RoleApi'

const roleApi = new RoleApi();

const {isEmpty} = useStringComposable();

const {successMsg, confirmMsgBox} = useTable();

/**
 * 角色 处理
 *
 * @author 王大宸
 * @date 2022-06-24 15:36:00
 */
export default function useRoleComposable() {

    /**
     * 分页数据
     */
    const tableDataVo = reactive<TableData<RoleVo>>({
        total: 0,
        rows: [],
    })

    /**
     * 实例化对象
     */
    const roleDto = reactive<RoleDto>({
        /* 主键唯一标识 */
        id: '',
        /* 角色名称 */
        name: '',
        /* 描述 */
        remark: '',
        /* 创建时间 */
        createTime: '',
        /* 创建人 */
        createUser: '',
        /* 创建人id */
        createUserId: '',
        /* 更新人 */
        updateUser: '',
        /* 更新时间 */
        updateTime: '',
        /* 更新人id */
        updateUserId: '',
    });

    /**
     * 实例化查询数据对象
     */
    const queryParams = reactive<RoleQuery>({
        page: 1,
        limit: 10,
        /* 角色名称 */
        name: '',
    });

    /**
     *  新增/修改/查看弹窗
     */
    const refRole = ref<{
        show: (type: DialogTypeEnum, data?: RoleVo | RoleDto | null) => void,
        onClose: () => void
    }>();

    /**
     * 搜索处理
     * @param params
     */
    const handlerSearch = (params: RoleQuery) => {
        queryParams.page = 1;
        loadTableData(params)
    }

    /**
     * 表单提交
     * @param data
     */
    const onSubmit = (data: RoleDto) => {
        let id: string | undefined = data.id;
        if (isEmpty(id)) {
            saveInfo(data);
        } else {
            updateInfo(data, id);
        }
    }

    /**
     * 新增接口
     * @param data 新增的数据
     */
    const saveInfo = (data: RoleDto) => {
        roleApi.saveInfo(data).then(res => {
            successMsg(res.msg);
            refRole.value?.onClose();
            loadTableData(queryParams);
        })
    }

    /**
     * 修改数据接口
     * @param data  修改的数据
     * @param id    修改数据的id
     */
    const updateInfo = (data: RoleDto, id: string | undefined) => {
        // roleApi.updateInfo(data, id).then(res => {
        //     successMsg(res.msg);
        //     refRole.value?.onClose();
        //     loadTableData(queryParams);
        // })
    }

    /**
     * 删除
     * @param id 需要删除的时间id
     * @param title 标题
     */
    const handlerRemove = (id: string, title: string) => {
        confirmMsgBox('确定要删除 ' + title + ' 吗?').then(res => {
            // roleApi.remove(id).then(res => {
            //     successMsg(res.msg);
            //     loadTableData(queryParams);
            // })
        })
    }

    /**
     * 重新加载数据
     * @param page
     * @param limit
     */
    const reloadDate = (page: number = 1, limit: number = 10) => {
        queryParams.page = page;
        queryParams.limit = limit;
        loadTableData(queryParams);
    }

    /**
     * 加载分页数据
     * @param params
     */
    const loadTableData = (params: RoleQuery) => {
        console.log('loadTableData', params);

        tableDataVo.total = 15;
        tableDataVo.rows = [];
        let rows = [];
        let a = (params.page - 1) * 10;
        let b = a + params.limit;
        for (let i = a; i < b; i++) {
            rows.push({
                id: i + 1,
                name: '角色' + (i + 1),
                remark: '角色' + (i + 1)
            })
        }

        tableDataVo.rows = rows;
        // roleApi.page(params).then(res => {
        //     tableDataVo.total = res.data.total;
        //     tableDataVo.rows = res.data.rows;
        // })
    }

    /**
     * 新增按钮
     */
    const handlerSave = () => {
        refRole.value?.show(DialogTypeEnum.SAVE, null);
    }

    /**
     * 编辑按钮
     * @param data
     */
    const handlerUpdate = (data: RoleVo) => {
        console.log('handlerUpdate', data)
        refRole.value?.show(DialogTypeEnum.UPDATE, data);
    }

    /**
     * 查看按钮
     * @param data
     */
    const handlerView = (data: RoleVo) => {
        refRole.value?.show(DialogTypeEnum.VIEW, data);
    }

    const columns: Partial<ColumnProps>[] = [
        {
            prop: "name",
            label: "角色名称",
            align: "center"
        },
        {
            prop: "remark",
            label: "描述",
            align: "center"
        },
        {
            prop: "operation",
            label: "操作",
            width: 420,
            align: "center",
            fixed: "right"
        }
    ]

    //表单验证
    const rules = reactive({
        name: [{required: true, message: '角色名称 不能为空', trigger: 'blur'}],
    })

    // @ts-ignore
    /**
     * 菜单树数据
     */
    const treeDate = reactive<ElTreeModel>({
        data: [],
        checked: []
    })

    /**
     * 角色权限组件的ref属性
     */
    const treePopup = ref<{ show: (treeDate: ElTreeModel, title: string) => void }>()

    /**
     * 角色授权
     */
    const roleMenuDto = reactive<RoleMenuDto>({
        /* 角色id */
        roleId: '',
        /* 菜单id, 多个菜单之间用逗号连接 */
        menuId: '',
    })


    const assignPermission = (roleId: string, name: string) => {
        roleMenuDto.roleId = roleId;
        treeDate.data = [];
        treeDate.checked = [];
        // 这里需要使用 then 方法里面要使用 async, 不然数据无法赋值
        roleApi.loadMenuTreeDate(roleId).then(async res => {
            treeDate.data = res.data.data;
            treeDate.checked = res.data.checked;
            treePopup.value?.show(treeDate, '为【 ' + name + ' 】角色授权');
        });
    }


    const select = (ids: string) => {
        roleMenuDto.menuId = ids;
        roleApi.saveRoleMenu(roleMenuDto).then(res => {
            successMsg(res.msg);
        })
    }


    return {
        refRole,
        roleDto,
        tableDataVo,
        columns,
        rules,
        queryParams,
        handlerSave,
        handlerUpdate,
        handlerView,
        reloadDate,
        handlerSearch,
        loadTableData,
        handlerRemove,
        onSubmit,
        treeDate,
        treePopup,
        assignPermission,
        select
    }

}