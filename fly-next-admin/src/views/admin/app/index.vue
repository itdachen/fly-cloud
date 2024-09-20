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

<script setup lang="tsx" name="useProTable">
import {ref, reactive} from "vue";
import {useRouter} from "vue-router";
import {ElMessage, ElMessageBox} from "element-plus";
import ProTable from "/@/components/ProTable/index.vue";
import {ProTableInstance, ColumnProps} from "/@/components/ProTable/interface";
import {CirclePlus, Delete, EditPen, Download, Upload, View, Refresh} from "@element-plus/icons-vue";
// ProTable 实例
const proTable = ref<ProTableInstance>();

// 如果表格需要初始化请求参数，直接定义传给 ProTable (之后每次请求都会自动带上该参数，此参数更改之后也会一直带上，改变此参数会自动刷新表格数据)
const initParam = reactive({type: 1});

// dataCallback 是对于返回的表格数据做处理，如果你后台返回的数据不是 list && total 这些字段，可以在这里进行处理成这些字段
// 或者直接去 hooks/useTable.ts 文件中把字段改为你后端对应的就行
const dataCallback = (data: any) => {
  return {
    list: [],
    total: 0
  };
};

// 表格配置项
const columns = reactive<ColumnProps[]>([
  {prop: "gender", label: "性别" },
  {prop: "idCard", label: "身份证号"},
  {prop: "email", label: "邮箱"},
  {prop: "address", label: "居住地址"},
  { prop: "status",label: "用户状态",},
  {prop: "operation", label: "操作", fixed: "right", width: 330}
]);

// 表格拖拽排序
const sortTable = ({newIndex, oldIndex}: { newIndex?: number; oldIndex?: number }) => {
  console.log(newIndex, oldIndex);
  console.log(proTable.value?.tableData);
  ElMessage.success("修改列表排序成功");
};

// 导出用户列表
const downloadFile = async () => {
  ElMessageBox.confirm("确认导出用户数据?", "温馨提示", {type: "warning"}).then(() =>{

      }
      // useDownload(exportUserInfo, "用户列表", proTable.value?.searchParam)
  );
};


const getTableList = () => {
  return [];
}

</script>

