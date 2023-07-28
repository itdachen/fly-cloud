<template>
  <lay-container fluid="true" style="padding: 10px">
    <lay-row space="10">
      <lay-col span="24">
        <lay-card>
          <lay-table :columns="columns" id="id"  skin="line"
                     :dataSource="dataSource"
                     v-model:selectedKeys="selectedKeys"
                     :checkbox="checkbox"
                     :default-toolbar="defaultToolbar">
            <!--  @row="rowClick" -->
            <template v-slot:toolbar>
              <div class="fly-tool-head">
                <div class="fly-head-search">
                  <lay-form-item mode="inline" class="fly-head-form-item">
                    <lay-select v-model="value" placeholder="请选择">
                      <lay-select-option :value="1" label="学习"></lay-select-option>
                      <lay-select-option :value="2" label="编码"></lay-select-option>
                      <lay-select-option :value="3" label="运动"></lay-select-option>
                    </lay-select>
                  </lay-form-item>
                  <lay-form-item mode="inline" class="fly-head-form-item">
                    <lay-input placeholder="请输入姓名"></lay-input>
                  </lay-form-item>
                  <lay-button size="sm" prefix-icon="layui-icon-search" class="fly-search-button">查询</lay-button>
                  <lay-button size="sm" prefix-icon="layui-icon-addition" class="fly-add-button" @click="saveButton">
                    新增
                  </lay-button>
                </div>
              </div>
            </template>
            <template v-slot:operator="{data}">
              <lay-button size="sm" type="normal" prefix-icon="layui-icon-list"
                          class="fly-tool-button fly-view-button"
                          @click="viewHandler(data)">查看
              </lay-button>
              <lay-button size="sm" type="warm" prefix-icon="layui-icon-edit"
                          class="fly-tool-button fly-edit-button">编辑
              </lay-button>
              <lay-button size="sm" type="danger" prefix-icon="layui-icon-delete"
                          class="fly-tool-button  fly-remove-button">删除
              </lay-button>
            </template>
          </lay-table>
          <lay-page v-model="current5" v-model:limit="limit5" :pages="pages5" :total="total5"
                    :show-count="true" :show-page="true"
                    :show-limit="true" :show-refresh="true"
                    :limits="limits3"
                    :showSkip="true" @change="change5" theme="blue"></lay-page>
        </lay-card>
      </lay-col>
    </lay-row>
  </lay-container>

  <!--  :area="['800px', '500px']" -->
  <lay-layer title="编辑" v-model="visible11" :maxmin="true"
             :shade="true" :btn="action11">
    <div style="padding: 20px;">
      <lay-form :model="model11" ref="layFormRef11" required>
        <lay-form-item label="账户" prop="username">
          <lay-input v-model="model11.username"></lay-input>
        </lay-form-item>
        <lay-form-item label="密码" prop="password">
          <lay-input v-model="model11.password" type="password">></lay-input>
        </lay-form-item>
        <lay-form-item label="爱好" prop="hobby">
          <lay-select v-model="model11.hobby" multiple style="width:100%;">
            <lay-select-option value="1" label="学习"></lay-select-option>
            <lay-select-option value="2" label="编码"></lay-select-option>
            <lay-select-option value="3" label="运动"></lay-select-option>
          </lay-select>
        </lay-form-item>
        <lay-form-item label="特长" prop="specialty">
          <lay-radio v-model="model11.specialty" name="specialty" value="1">写作</lay-radio>
          <lay-radio v-model="model11.specialty" name="specialty" value="2">画画</lay-radio>
          <lay-radio v-model="model11.specialty" name="specialty" value="3">编码</lay-radio>
        </lay-form-item>
        <lay-form-item label="描述" prop="desc">
          <lay-textarea placeholder="请输入描述" v-model="model11.desc"></lay-textarea>
        </lay-form-item>
      </lay-form>
    </div>
  </lay-layer>

</template>

<script lang="ts" setup>
import {ref, reactive} from 'vue'
import {layer} from '@layui/layui-vue'

const value = ref(null);

const selectedKeys = ref(["1"]);
const checkbox = ref(true);
const defaultToolbar = ref(true);


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
    title: "账户",
    width: "200px",
    key: "username",
    align: 'center',
    ellipsisTooltip: true
  },
  {
    title: "密码",
    width: "180px",
    key: "password",
    align: 'center',
  },
  {
    title: "年龄",
    width: "180px",
    key: "age",
    align: 'center',
  },
  {
    title: "操作",
    width: "180px",
    customSlot: "operator",
    key: "operator",
    align: 'center',
  },
];

const dataSource = [
  {
    id: "1",
    username: "rootrootrootrootrootrootrootrootrootrootrootrootrootrootrootrootrootrootrootrootrootrootrootrootrootrootrootrootrootrootrootrootrootrootrootrootrootrootrootrootrootrootrootrootrootrootrootrootrootrootroot",
    password: "root",
    age: "18"
  },
  {id: "2", username: "woow", password: "woow", age: "20"},
  {id: "3", username: "woow2", password: "woow", age: "20"},
  {id: "4", username: "woow3", password: "woow", age: "20"},
];

// const rowClick = function (data: any) {
//   console.log(JSON.stringify(data));
// };

// const rowDoubleClick = function (data: any) {
//   console.log(JSON.stringify(data));
// };


const viewHandler = (data: any) => {
  console.log('viewHandler data', data.id)
}


/** 弹窗 *****/

const saveButton = () => {
  visible11.value = !visible11.value;
}

const model11 = reactive({
  username: "admin"
})

const layFormRef11 = ref();

const visible11 = ref(false);

const changeVisible11 = () => {
  visible11.value = !visible11.value;
}

const action11 = ref([
  {
    text: "确认",
    callback: () => {
      layer.confirm("确认操作", {shade: false});
    }
  },
  {
    text: "取消",
    callback: () => {
      layer.confirm("取消操作", {shade: false});
    }
  }
])


</script>

<style>

.fly-tool-head {
  display: flex;
  flex-wrap: wrap;
}

.fly-head-left {
  float: left !important;
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

.fly-add-button {
  color: #ffffff;
  border-radius: 3px;
  background-color: #1E9FFF;
  border-color: #31BDEC;
  margin-top: 5px;
  width: 75px;
  height: 36px !important;
  line-height: 36px !important;
}

.fly-add-button:hover {
  background-color: #31BDEC;
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
  height: 30px;
  width: auto;
  display: inline-block;
  padding: 2px 12px 2px 10px;
  font-size: 14px;
  font-weight: 400;
  line-height: 1.42857143;
  text-align: center;
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
  border-radius: 3px;
  color: #ffffff !important;
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
