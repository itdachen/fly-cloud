<template>
  <lay-container fluid="true" class="fly-container">
    <lay-card class="fly-search-card">


      <lay-form>
        <lay-form-item label="电话号码" prop="username" mode="inline">
          <lay-input v-model="queryParams.username"></lay-input>
        </lay-form-item>
<!--        <lay-form-item label="密码" prop="password" mode="inline">-->
<!--          <lay-input  v-model="queryParams.password"></lay-input>-->
<!--        </lay-form-item>-->
        <lay-form-item mode="inline">
          <lay-button class="fly-button fly-button-search" style="margin-left: 20px" type="primary" size="sm"
                      @click="loadTableData">
            <lay-icon class="layui-icon-search"></lay-icon>
            查询
          </lay-button>
          <lay-button class="fly-button fly-button-reset" size="sm" @click="resetQueryFormValue">
            <lay-icon class="layui-icon-refresh"></lay-icon>
            重置
          </lay-button>
          <lay-button class="fly-button fly-button-exp" size="sm" @click="">
            <lay-icon class="layui-icon-export"></lay-icon>
            导出
          </lay-button>
        </lay-form-item>
      </lay-form>

    </lay-card>



    <!-- table   :default-toolbar="true"  :height="'100%'" line row nob -->
    <div class="table-box">
      <lay-table :page="false" :skin="'nob'"
                 :height="'90%'"
                 :columns="tableColumns"
                 :loading="loading"
                 :data-source="dataSource"
                 v-model:selected-keys="selectedKeys"
                 @change="loadTableData">

        <!--      <lay-table :page="tablePage" :height="'100%'" :skin="'line'"-->
        <!--                 :columns="tableColumns"-->
        <!--                 :loading="loading"-->
        <!--                 :default-toolbar="true"-->
        <!--                 :data-source="dataSource"-->
        <!--                 v-model:selected-keys="selectedKeys"-->
        <!--                 @change="loadTableData">-->

        <template #status="{ row }">
          <lay-switch :model-value="row.status" @change="changeStatus($event, row)"></lay-switch>
        </template>

        <template #avatar="{ row }">
          <lay-avatar :src="row.avatar"></lay-avatar>
        </template>

        <template v-slot:toolbar>
          <lay-button size="sm" type="primary" class="fly-button fly-toolbar-addition" @click="onTapAdd()">
            <lay-icon class="layui-icon-addition"></lay-icon>
            新增
          </lay-button>

          <lay-button size="sm" class="fly-button" @click="onTapRemove">
            <lay-icon class="layui-icon-delete"></lay-icon>
            删除
          </lay-button>

          <lay-button size="sm" class="fly-button" @click="onTapImport">
            <lay-icon class="layui-icon-upload-drag"></lay-icon>
            导入
          </lay-button>
        </template>

        <template v-slot:operator="{ row }">
          <lay-button size="sm" type="primary" class="fly-button fly-view-button" @click="onTapView(row)">
            <lay-icon class="layui-icon-list"></lay-icon>
            查看
          </lay-button>
          <lay-button size="sm" type="primary" class="fly-button fly-edit-button" @click="onTapEdit(row)">
            <lay-icon class="layui-icon-edit"></lay-icon>
            编辑
          </lay-button>

          <lay-button size="sm" type="danger" class="fly-button fly-remove-button" @click="removeHandler(row.id)">
            <lay-icon class="layui-icon-delete"></lay-icon>
            删除
          </lay-button>

          <!--          <lay-popconfirm content="确定要删除此用户吗?" @confirm="confirm" @cancel="cancel">-->
          <!--            <lay-button size="sm" type="danger" class="fly-button fly-remove-button">-->
          <!--              <lay-icon class="layui-icon-delete"></lay-icon>-->
          <!--              删除-->
          <!--            </lay-button>-->
          <!--          </lay-popconfirm>-->
        </template>




      </lay-table>


      <lay-page v-model="current5"
                :layout="layout5"
                v-model:limit="limit5"
                :pages="pages5"
                :total="total5"
                theme="blue"
                @change="change5" style="margin-top: 15px"></lay-page>

    </div>


    <lay-layer v-model="visibleImport" title="导入用户" :area="['380px', '380px']">
      <lay-upload :beforeUpload="beforeUpload10" style="margin: 60px"
                  url="https://www.mocky.io/v2/5cc8019d300000980a055e76"
                  v-model="file1" field="file" :auto="false" :drag="true">
        <template #preview>
          {{ file1[0]?.name }}
        </template>
      </lay-upload>
      <div style="width: 100%; text-align: center">
        只能上传小于1000KB的文件
      </div>
    </lay-layer>
  </lay-container>


  <AddAndEdit ref="addAndEditRef" @click="reset3"/>

</template>


<script setup lang="ts">
import {ref, reactive} from 'vue'
import {layer} from '@layui/layui-vue'
import AddAndEdit from "./add.vue";

import useUserInfoComposable from "@/composables/admin/UserInfoComposable";

const {
//  title,
  addAndEditRef,
  tableColumns,
  tablePage,
  tableData,
  queryParams,
  loadTableData,
  handleCurrentChange,
  handleSizeChange,
  removeHandler,
  onTapAdd,
  onTapEdit,
  onTapView
} = useUserInfoComposable();


const reset3 = (data: any) => {
  console.log('reset3', data)
}


/******* 割裂带 ************************************************************************************************************************/

const visibleImport = ref(false)
const file1 = ref<any>([]);

const change5 = ({current = 1, limit = 10}) => {
  layer.msg("current:" + current + " limit:" + limit);
  loadDataSource(current, limit);
}


const limit5 = ref(10);
const total5 = ref(99);
const pages5 = ref(7);
const current5 = ref(1);
const layout5 = ref(['count', 'prev', 'page', 'next', 'limits',  'refresh', 'skip'])
const changeCurrent5 = () => {
  current5.value = 2;
}
const changeLimit5 = () => {
  limit5.value = 20;
}

function onTapImport() {
  visibleImport.value = true
}

const searchQuery = ref();
const resetQueryFormValue = () => {
  searchQuery.value.reset();
}


function toSearch() {
  page.current = 1
  change(page)
}

const loading = ref(false)
const selectedKeys = ref<string[]>([])


const pageLayout = ref(['prev', 'page', 'next', 'limits', 'count', 'refresh', 'skip']);

const page = reactive({
  current: 1,
  limit: 10,
  total: 100,
  limits: [10, 20, 50, 100, 200, 500, 1000],
  theme: 'blue',
  prev: '上一页',
  next: '下一页'
})

const columns = ref([
  {title: '选项', width: '60px', type: 'checkbox', fixed: 'left'},
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
const change = (page: any) => {
  console.log('change page', page)
  loading.value = true
  setTimeout(() => {
    dataSource.value = loadDataSource(page.current, page.limit)
    loading.value = false
  }, 1000)
}
const sortChange = (key: any, sort: number) => {
  layer.msg(`字段${key} - 排序${sort}, 你可以利用 sort-change 实现服务端排序`)
}

const dataSource = ref([
  {
    id: '1',
    name: '张三1',
    avatar:
        'https://tse1-mm.cn.bing.net/th/id/OIP-C.0fLeVmNXnV-6Eom3FEUNjgAAAA?w=196&h=196&c=7&r=0&o=5&dpr=1.5&pid=1.7',
    email: 'test@qq.com',
    sex: '男',
    city: '浙江杭州',
    age: '18',
    remark: '花开堪折直须折,莫待无花空折枝.',
    joinTime: '2022-02-09',
    status: true
  },
  {
    id: '2',
    name: '张三2',
    avatar:
        'https://tse1-mm.cn.bing.net/th/id/OIP-C.0fLeVmNXnV-6Eom3FEUNjgAAAA?w=196&h=196&c=7&r=0&o=5&dpr=1.5&pid=1.7',
    email: 'test@qq.com',
    sex: '男',
    city: '浙江杭州',
    age: '20',
    remark: '花开堪折直须折,莫待无花空折枝.',
    joinTime: '2022-02-09',
    status: true
  },
  {
    id: '3',
    name: '张三3',
    avatar:
        'https://tse1-mm.cn.bing.net/th/id/OIP-C.0fLeVmNXnV-6Eom3FEUNjgAAAA?w=196&h=196&c=7&r=0&o=5&dpr=1.5&pid=1.7',
    email: 'test@qq.com',
    sex: '男',
    city: '浙江杭州',
    age: '20',
    remark: '花开堪折直须折,莫待无花空折枝.',
    joinTime: '2022-02-09',
    status: true
  },
  {
    id: '4',
    name: '张三4',
    avatar:
        'https://tse1-mm.cn.bing.net/th/id/OIP-C.0fLeVmNXnV-6Eom3FEUNjgAAAA?w=196&h=196&c=7&r=0&o=5&dpr=1.5&pid=1.7',
    email: 'test@qq.com',
    sex: '男',
    city: '浙江杭州',
    age: '20',
    remark: '花开堪折直须折,莫待无花空折枝.',
    joinTime: '2022-02-09',
    status: true
  },
  {
    id: '5',
    name: '张三5',
    avatar:
        'https://tse1-mm.cn.bing.net/th/id/OIP-C.0fLeVmNXnV-6Eom3FEUNjgAAAA?w=196&h=196&c=7&r=0&o=5&dpr=1.5&pid=1.7',
    email: 'test@qq.com',
    sex: '男',
    city: '浙江杭州',
    age: '20',
    remark: '花开堪折直须折,莫待无花空折枝.',
    joinTime: '2022-02-09',
    status: true
  },
  {
    id: '6',
    name: '张三6',
    avatar:
        'https://tse1-mm.cn.bing.net/th/id/OIP-C.0fLeVmNXnV-6Eom3FEUNjgAAAA?w=196&h=196&c=7&r=0&o=5&dpr=1.5&pid=1.7',
    email: 'test@qq.com',
    sex: '男',
    city: '浙江杭州',
    age: '20',
    remark: '花开堪折直须折,莫待无花空折枝.',
    joinTime: '2022-02-09',
    status: true
  },
  {
    id: '7',
    name: '张三7',
    avatar:
        'https://tse1-mm.cn.bing.net/th/id/OIP-C.0fLeVmNXnV-6Eom3FEUNjgAAAA?w=196&h=196&c=7&r=0&o=5&dpr=1.5&pid=1.7',
    email: 'test@qq.com',
    sex: '男',
    city: '浙江杭州',
    age: '18',
    remark: '花开堪折直须折,莫待无花空折枝.',
    joinTime: '2022-02-09',
    status: true
  },
  {
    id: '8',
    name: '张三8',
    avatar:
        'https://tse1-mm.cn.bing.net/th/id/OIP-C.0fLeVmNXnV-6Eom3FEUNjgAAAA?w=196&h=196&c=7&r=0&o=5&dpr=1.5&pid=1.7',
    email: 'test@qq.com',
    sex: '男',
    city: '浙江杭州',
    age: '20',
    remark: '花开堪折直须折,莫待无花空折枝.',
    joinTime: '2022-02-09',
    status: true
  },
  {
    id: '9',
    name: '张三9',
    avatar:
        'https://tse1-mm.cn.bing.net/th/id/OIP-C.0fLeVmNXnV-6Eom3FEUNjgAAAA?w=196&h=196&c=7&r=0&o=5&dpr=1.5&pid=1.7',
    email: 'test@qq.com',
    sex: '男',
    city: '浙江杭州',
    age: '20',
    remark: '花开堪折直须折,莫待无花空折枝.',
    joinTime: '2022-02-09',
    status: true
  },
  // {
  //   id: '10',
  //   name: '张三10',
  //   avatar:
  //       'https://tse1-mm.cn.bing.net/th/id/OIP-C.0fLeVmNXnV-6Eom3FEUNjgAAAA?w=196&h=196&c=7&r=0&o=5&dpr=1.5&pid=1.7',
  //   email: 'test@qq.com',
  //   sex: '男',
  //   city: '浙江杭州',
  //   age: '20',
  //   remark: '花开堪折直须折,莫待无花空折枝.',
  //   joinTime: '2022-02-09',
  //   status: true
  // }
])

const changeStatus = (isChecked: boolean, row: any) => {
  dataSource.value.forEach((item) => {
    if (item.id === row.id) {
      layer.msg('Success', {icon: 1}, () => {
        item.status = isChecked
      })
    }
  })
}

const loadDataSource = (page: number, pageSize: number) => {
  let response = []
  let startIndex = (page - 1) * pageSize + 1
  let endIndex = page * pageSize -2;
  for (let i = startIndex; i <= endIndex; i++) {
    response.push({
      id: `${i}`,
      age: '18',
      sex: '男',
      avatar:
          'https://tse1-mm.cn.bing.net/th/id/OIP-C.0fLeVmNXnV-6Eom3FEUNjgAAAA?w=196&h=196&c=7&r=0&o=5&dpr=1.5&pid=1.7',
      name: `张三${i}`,
      email: 'test@qq.com',
      remark: '花开堪折直须折,莫待无花空折枝.',
      joinTime: '2022-02-09',
      city: '浙江杭州',
      status: true
    })
  }
  return response
}
const model11 = ref<any>({})
const layFormRef11 = ref()
const visible11 = ref(false)
const title = ref('新增');

function onTapRemove() {
  if (selectedKeys.value.length == 0) {
    layer.msg('您未选择数据，请先选择要删除的数据', {icon: 3, time: 2000})
    return
  }
  layer.confirm('您将删除所有选中的数据？', {
    title: '提示',
    btn: [
      {
        text: '确定',
        callback: (id: any) => {
          layer.msg('您已成功删除')
          layer.close(id)
        }
      },
      {
        text: '取消',
        callback: (id: any) => {
          layer.msg('您已取消操作')
          layer.close(id)
        }
      }
    ]
  })
}

function toSubmit() {
  layer.msg('保存成功！', {icon: 1, time: 1000})
  visible11.value = false
}

function toCancel() {
  visible11.value = false
}

function confirm() {
  layer.msg('您已成功删除')
}

function cancel() {
  layer.msg('您已取消操作')
}

const beforeUpload10 = (file: File) => {
  var isOver = false
  if (file.size > 1000) {
    isOver = true
    layer.msg(`file size over 1000 KB`, {icon: 2})
  }
  return new Promise((resolver) => resolver(true))
}

/***********************************************************************************************************************/
const space = ref(20);

const schema2 = reactive({
  text1: {
    label: '栅格布局12',
    type: 'input',
    props: {
      type: 'text',
      placeholder: '请输入姓名',
    },
    colProps: {
      md: 12
    }
  },
  text2: {
    label: '栅格布局12',
    type: 'input',
    props: {
      type: 'text',
      placeholder: '请输入姓名',
    },
    colProps: {
      md: 12
    }
  },
  text3: {
    label: '栅格布局6',
    type: 'input',
    props: {
      type: 'text',
      placeholder: '请输入姓名',
    },
    colProps: {
      md: 6
    }
  },
  text4: {
    label: '栅格布局6',
    type: 'input',
    props: {
      type: 'text',
      placeholder: '请输入姓名',
    },
    colProps: {
      md: 6
    }
  },
  text5: {
    label: '栅格布局6',
    type: 'input',
    props: {
      type: 'text',
      placeholder: '请输入姓名',
    },
    colProps: {
      md: 6
    }
  },
  text6: {
    label: '栅格布局6',
    type: 'input',
    props: {
      type: 'text',
      placeholder: '请输入姓名',
    },
    colProps: {
      md: 6
    }
  },
})

</script>


<style scoped>

</style>