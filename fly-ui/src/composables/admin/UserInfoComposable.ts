import {TableData} from "axios";
import {onMounted, reactive, ref} from "vue";
import {layer} from "@layui/layui-vue";
import {UserInfoQuery} from "./UserInfoModel";

/**
 * 用户管理
 */
export default function useUserInfoComposable() {

    const title = ref('微服务认证客户端');


    const addAndEditRef = ref();

    /**
     * 分页数据
     */
    const tableData = reactive<TableData<any>>({
        loading: false,
        total: 0,
        rows: [],
    })

    /**
     * 实例化对象
     */
    const queryParams = reactive<UserInfoQuery>({
        page: 1,
        limit: 10,
        username: '',
        password: ''
    });

    /**
     * 加载分页数据
     * @param queryParams
     */
    const loadTableData = (queryParams: UserInfoQuery) => {
        //  console.log('loadTableData', queryParams.value)
        console.log('加载表格数据', queryParams);
    }


    const onTapAdd = () => {
        console.log('onTapAdd');
        addAndEditRef.value?.open('新增', '用户管理', null);
    }

    const onTapEdit = (data: any) => {
        console.log('onTapEdit, id: ' + data.id);
        addAndEditRef.value?.open('编辑', '用户管理', data);
    }

    const onTapView = (data: any) => {
        console.log('onTapView, id: ' + data.id);
        addAndEditRef.value?.open('查看', '用户管理', data);
    }


    /**
     * 删除按钮
     * @param id 需要删除的时间id
     */
    const removeHandler = (id: string) => {
        console.log('removeHandler, id: ' + id)

        layer.confirm('您将删除所有选中的数据？', {
            title: '系统提示',
            icon: 3,
            btn: [
                {
                    text: '确定',
                    callback: (layerId: any) => {
                        layer.msg('操作成功！', {time: 1500, icon: 1})
                        layer.close(layerId);
                        //     authClientApi.remove(id).then(res => {
                        //         loadTableData(queryParams);
                        //         successMsg(res.msg)
                        //     })

                    }
                },
                {
                    text: '取消',
                    callback: (layerId: any) => {
                        // layer.msg('您已取消操作')
                        layer.close(layerId)
                    }
                }
            ]
        })

        // confirmMsgBox().then(res => {
        //     authClientApi.remove(id).then(res => {
        //         loadTableData(queryParams);
        //         successMsg(res.msg)
        //     })
        // }).catch(err => {
        // })
    }


    /**
     * 上一页/下一页 按钮 previousNextPage
     * @param val 第多少页
     */
    const handleCurrentChange = (val: number) => {
        queryParams.page = val;
        loadTableData(queryParams);
    }

    /**
     * 修改每页展示多少条
     * @param val 需要展示的条数
     */
    const handleSizeChange = (val: number) => {
        queryParams.limit = val;
        loadTableData(queryParams);
    }


    const tableColumns = ref([
        //   {title: '选项', width: '60px', type: 'checkbox', fixed: 'left'},
        {title: '编号', width: '80px', key: 'id', fixed: 'left', sort: 'desc'},
        {title: '头像', width: '50px', key: 'avatar', customSlot: 'avatar'},
        {title: '姓名', width: '80px', key: 'name', sort: 'desc'},
        {title: '状态', width: '80px', key: 'status', customSlot: 'status'},
        {title: '邮箱', width: '120px', key: 'email'},
        {title: '性别', width: '80px', key: 'sex'},
        {title: '年龄', width: '80px', key: 'age'},
        {title: '城市', width: '120px', key: 'city'},
        {title: '签名', width: '260px', key: 'remark'},
        {title: '时间', width: '120px', key: 'joinTime'},
        {
            title: '操作',
            width: '220px',
            customSlot: 'operator',
            key: 'operator',
            align: "center",
            fixed: 'right'
        }
    ])


    const tablePage = reactive({
        current: 1,
        limit: 10,
        total: 120,
        limits: [10, 20, 50, 100, 200, 500, 1000],
        theme: 'blue',
        layout: ['count', 'prev', 'page', 'next', 'limits', 'refresh', 'skip'],
        prev: '上一页',
        next: '下一页',
    })


    //首次加载
    onMounted(() => {
        console.log('onMounted ===>')


        // getDeptList();
        // nextTick(() => {
        //     tableHeigth.value = window.innerHeight - 200
        // })
    })


    return {
        title,
        addAndEditRef,
        tableData,
        queryParams,
        loadTableData,
        handleCurrentChange,
        handleSizeChange,
        removeHandler,
        tableColumns,
        tablePage,
        onTapAdd,
        onTapEdit,
        onTapView
    }


}