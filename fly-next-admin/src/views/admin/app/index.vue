<template>
  <div class="table-box">
    <ProTable ref="proTable"
               :columns="columns"
               :request-api="getTableList"
               :init-param="initParam"
               :data-callback="dataCallback"
               @drag-sort="sortTable">
      <!-- 表格 header 按钮 -->
      <template #tableHeader="scope">
        <el-button type="primary" :icon="CirclePlus" @click="openDrawer('新增')">新增用户</el-button>
        <el-button type="primary" :icon="Upload" plain @click="batchAdd">批量添加用户</el-button>
        <el-button type="primary" :icon="Download" plain @click="downloadFile">导出用户数据</el-button>
        <!--        <el-button type="primary" plain @click="toDetail">To 子集详情页面</el-button>-->
        <!--        <el-button type="danger" :icon="Delete" plain :disabled="!scope.isSelected"-->
        <!--                   @click="batchDelete(scope.selectedListIds)">-->
        <!--          批量删除用户-->
        <!--        </el-button>-->
      </template>
      <!-- Expand -->
      <template #expand="scope">
        {{ scope.row }}
      </template>
      <!-- usernameHeader -->
      <template #usernameHeader="scope">
        <el-button type="primary" @click="ElMessage.success('我是通过作用域插槽渲染的表头')">
          {{ scope.column.label }}
        </el-button>
      </template>
      <!-- createTime -->
      <template #createTime="scope">
        <el-button type="primary" link @click="ElMessage.success('我是通过作用域插槽渲染的内容')">
          {{ scope.row.createTime }}
        </el-button>
      </template>
      <!-- 表格操作 -->
      <template #operation="scope">
        <el-button type="primary" link :icon="View">查看</el-button>
        <el-button type="primary" link :icon="EditPen">编辑</el-button>
        <el-button type="primary" link :icon="Refresh">重置密码</el-button>
        <el-button type="primary" link :icon="Delete">删除</el-button>
      </template>
    </ProTable>
  </div>
</template>

<script setup>
import {ref, reactive} from "vue";
import {ElMessage, ElMessageBox} from "element-plus";
import {CirclePlus, Delete, EditPen, Download, Upload, View, Refresh} from "@element-plus/icons-vue";
import ProTable from '/@/components/ProTable/index.vue';

// ProTable 实例
const proTable = ref({});

// 如果表格需要初始化请求参数，直接定义传给 ProTable (之后每次请求都会自动带上该参数，此参数更改之后也会一直带上，改变此参数会自动刷新表格数据)
const initParam = reactive({type: 1});

// dataCallback 是对于返回的表格数据做处理，如果你后台返回的数据不是 list && total 这些字段，可以在这里进行处理成这些字段
// 或者直接去 hooks/useTable.ts 文件中把字段改为你后端对应的就行
const dataCallback = (data) => {
  console.log(data)
  // return {
  //   list: [],
  //   total: 0
  // };
};

// 表格配置项
const columns = [
  {prop: "platTitle", label: "平台名称", align: "center"},
  {prop: "appCode", label: "应用标识", align: "center"},
  {prop: "appTitle", label: "应用名称", align: "center"},
  {prop: "appAsTitle", label: "应用名称简称", align: "center"},
  {prop: "appType", label: "应用类型", align: "center"},
  {prop: "askUri", label: "访问地址", align: "center"},
  {prop: "iconIco", label: "图标", align: "center"},
  {prop: "funcTitle", label: "职能名称", align: "center"},
  {prop: "validFlag", label: "有效标志", align: "center"},
  {prop: "operation", label: "操作", fixed: "right", width: 330}
];

// 表格拖拽排序
const sortTable = ({newIndex, oldIndex}) => {
  console.log(newIndex, oldIndex);
  console.log(proTable.value?.tableData);
  ElMessage.success("修改列表排序成功");
};

// 导出用户列表
const downloadFile = async () => {
  ElMessageBox.confirm("确认导出用户数据?", "温馨提示", {type: "warning"}).then(() => {

      }
      // useDownload(exportUserInfo, "用户列表", proTable.value?.searchParam)
  );
};


const getTableList = () => {
  return [];
}

</script>

