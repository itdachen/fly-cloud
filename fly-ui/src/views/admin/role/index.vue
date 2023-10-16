<template>
  <lay-container fluid="true" style="padding: 10px">
    <lay-row :space="10">

      <lay-col :md="24">
        <lay-card>
          <lay-form size="sm" style="margin-top: 20px">
            <lay-row>
              <lay-col :md="3">
                <lay-form-item label="账号：" label-width="50">
                  <lay-input></lay-input>
                </lay-form-item>
              </lay-col>
              <lay-col :md="3">
                <lay-form-item label="邮箱：" label-width="50">
                  <lay-input></lay-input>
                </lay-form-item>
              </lay-col>
              <lay-col :md="6" style="margin-left: 20px">
                <lay-form-item label-width="0">
                  <lay-button type="normal" size="sm"
                              prefix-icon="layui-icon-search">查询
                  </lay-button>
                  <lay-button type="normal" size="sm"
                              prefix-icon="layui-icon-refresh">重置
                  </lay-button>
                  <lay-button type="normal" size="sm"
                              prefix-icon="layui-icon-export">导出
                  </lay-button>
                </lay-form-item>
              </lay-col>
            </lay-row>
          </lay-form>
        </lay-card>
      </lay-col>


      <lay-col :md="24">
        <lay-card>
          <!--    @row="rowClick" -->
          <lay-table :columns="columns" skin="line"
                     :dataSource="dataSource"
                     :default-toolbar="defaultToolbar"
                     v-model:selectedKeys="selectedKeys"
                     @change="change">
            <template v-slot:toolbar>
              <lay-button size="sm" type="primary" class="fly-button fly-toolbar-add" prefix-icon="layui-icon-addition">
                新增
              </lay-button>
              <lay-button size="sm" type="danger" class="fly-button fly-remove-button" prefix-icon="layui-icon-delete">
                删除
              </lay-button>
            </template>
            <template v-slot:operator="{data}">
              <lay-button size="sm" type="normal" prefix-icon="layui-icon-list"
                          class="fly-button fly-tool-button fly-view-button"
                          @click="viewHandler(data)">查看
              </lay-button>
              <lay-button size="sm" type="warm" prefix-icon="layui-icon-edit"
                          class="fly-button fly-tool-button fly-edit-button">编辑
              </lay-button>
              <lay-button size="sm" type="danger" prefix-icon="layui-icon-delete"
                          class="fly-button fly-tool-button  fly-remove-button">删除
              </lay-button>
            </template>
          </lay-table>

          <lay-page v-model="current5"
                    v-model:limit="limit5"
                    :pages="pages5"
                    :total="total5"
                    :show-count="true" :show-page="true"
                    :show-limit="true" :show-refresh="true"
                    :limits="limits3"
                    :showSkip="true"
                    @change="change5" theme="blue"></lay-page>

        </lay-card>
      </lay-col>
    </lay-row>
  </lay-container>
</template>

<script lang="ts" setup>
import {ref, watch} from "vue";
import {layer} from "@layui/layer-vue";


const selectedKeys = ref(["1"]);
const checkbox = ref(true);
const defaultToolbar = ref(true);
// const page = ref({total: 100, limit: 10, current: 2});


const limit5 = ref(10)
const total5 = ref(99)
const pages5 = ref(5);
const current5 = ref(1);
const limits3 = ref([5, 10, 50, 100, 200, 300, 500])
const changeCurrent5 = () => {
  current5.value = 2;
}
const changeLimit5 = () => {
  limit5.value = 20;
}
const change5 = ({current, limit}) => {
  layer.msg("current:" + current + " limit:" + limit);
}


const columns = [
  {
    title: "复选",
    width: "50px",
    type: "checkbox",
    fixed: "left",
  },
  {
    title: "账户",
    width: "200px",
    // customSlot: "username",
    key: "username",
    align: 'center',
  },
  {
    title: "密码",
    width: "180px",
    // customSlot: "password",
    key: "password",
    align: 'center',
  },
  {
    title: "年龄",
    width: "180px",
    key: "age",
  },
  {
    title: "描述",
    key: "remark",
    align: 'center',
    ellipsisTooltip: true,
  },
  {
    title: "操作",
    width: "300px",
    customSlot: "operator",
    key: "operator",
    align: 'center',
    fixed: "right",
  },
];

const dataSource = [
  {
    id: "1",
    username: "shana",
    password: "夏娜",
    remark: "花开堪折直须折,莫待无花空折枝",
    age: "22",
  },
  {
    id: "2",
    username: "shana",
    password: "夏娜",
    remark: "花开堪折直须折,莫待无花空折枝",
    age: "22",
  },
  {
    id: "3",
    username: "shana",
    password: "夏娜",
    remark: "花开堪折直须折,莫待无花空折枝",
    age: "22",
  },
  {
    id: "4",
    username: "shana",
    password: "夏娜",
    remark: "花开堪折直须折,莫待无花空折枝",
    age: "22",
  },
  {
    id: "5",
    username: "shana",
    password: "夏娜",
    remark: "花开堪折直须折,莫待无花空折枝",
    age: "22",
  },
  {
    id: "6",
    username: "shana",
    password: "夏娜",
    remark: "花开堪折直须折,莫待无花空折枝",
    age: "22",
  },
  {
    id: "7",
    username: "shana",
    password: "夏娜",
    remark: "花开堪折直须折,莫待无花空折枝",
    age: "22",
  },
  {
    id: "8",
    username: "shana",
    password: "夏娜",
    remark: "花开堪折直须折,莫待无花空折枝",
    age: "22",
  },
  {
    id: "9",
    username: "shana",
    password: "夏娜",
    remark: "花开堪折直须折,莫待无花空折枝",
    age: "22",
  },
  {
    id: "10",
    username: "shana",
    password: "夏娜",
    remark: "花开堪折直须折,莫待无花空折枝",
    age: "22",
  },
  {
    id: "11",
    username: "shana",
    password: "夏娜",
    remark: "花开堪折直须折,莫待无花空折枝",
    age: "22",
  },
];

const rowClick = function (data: any) {
  console.log(JSON.stringify(data));
};

const rowDoubleClick = function (data: any) {
  console.log(JSON.stringify(data));
};

const change = function ({current, limit}: any) {
  layer.msg("current:" + current + " limit:" + limit);
};


const viewHandler = (data: any) => {
  console.log('viewHandler data', data)
}


</script>


<style>


.fly-button {
  height: 30px;
  width: auto;
  border-radius: 3px;
  margin-top: 5px;
  font-weight: 400;
  line-height: 30px;
  color: #ffffff;
  padding: 0 12px 0 10px;
  font-size: 14px;
  text-align: center;
}


.fly-tool-head {
  display: flex;
  flex-wrap: wrap;
}

.fly-head-left {
  float: left !important;
}

.fly-toolbar-add {
  background-color: #1E9FFF;
  border-color: #31BDEC;
}

.fly-toolbar-add:hover {
  background-color: #31BDEC;
}

.fly-head-form-item {
  margin-top: 5px;
  height: 32px !important;
  margin-bottom: 0 !important;
}

.fly-head-form-item > input {
  height: 32px !important;
  width: 150px;
}

.fly-head-search {
  margin-right: 5px;
  display: flex;
  flex-wrap: wrap;
  align-items: stretch;
  width: auto;
  min-height: 36px;
  white-space: normal;
}

.fly-search-button {
  margin-top: 5px;
  border-radius: 3px;
  background-color: #FFF;
  border: 1px solid #DCDFE6;
  color: #3a3b3b;
  width: 75px;
  height: 36px !important;
  line-height: 36px !important;
}

.fly-search-button:hover {
  background-color: #e8f7f6;
  border-color: #b9e8e3;
  color: #17B3A3;
}

.fly-tool-button {
  display: inline-block;
  white-space: nowrap;
  vertical-align: middle;
  -ms-touch-action: manipulation;
  touch-action: manipulation;
  cursor: pointer;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  background-image: none;
  background-color: #1E9FFF;
  border: 1px solid #1E9FFF;
  background-repeat: no-repeat;
}

.fly-tool-button:first-child {
  margin-left: 0 !important;

}

.fly-tool-button:focus {
  outline: none !important;
}

.fly-tool-button:hover {
  opacity: 0.7;
}

.fly-tool-button i {
  margin-right: 3px;
}

.fly-view-button {
  background-position: 10px center;
  background-size: 20px;
}

.fly-edit-button {
  background-color: #6572ff;
  border-color: #6c87ff;
}

.fly-edit-button:hover {
  background-color: rgba(101, 114, 255, 0.64);
  border-color: rgba(101, 114, 255, 0.13);
}

.fly-remove-button {
  background-position: 10px center;
  background-color: #ff7652;
  border-color: #ff7652;
}


</style>
