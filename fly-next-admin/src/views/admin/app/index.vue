<template>
  <div class="fly-container">


    <div class="fly-main-container">

      <div class="fly-query-container">

        <el-form ref="ruleFormRef" label-width="120px">
          <el-row :gutter="20">
            <el-col :span="4" style="margin-top: 3px">
              <el-form-item label="输入框A">
                <el-input></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="4" style="margin-top: 3px">
              <el-form-item label="输入框B">
                <el-input></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="4" style="margin-top: 3px">
              <el-form-item label="输入框c">
                <el-input></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="4" style="margin-top: 8px">
              <el-button size="default" type="primary" class="ml10">
                <el-icon>
                  <ele-Search/>
                </el-icon>
                查询
              </el-button>
              <el-button size="default" type="primary" class="ml10" @click="resetForm()">
                <el-icon>
                  <ele-Refresh/>
                </el-icon>
                重置
              </el-button>
              <el-button size="default" type="primary" class="ml10">
                <el-icon>
                  <ele-FolderOpened/>
                </el-icon>
                导出
              </el-button>
            </el-col>
          </el-row>
        </el-form>

        <!--        <el-form :inline="true" class="demo-form-inline" style="display: flex;">-->
        <!--          <el-form-item label="角色名称">-->
        <!--            <el-input size="default" placeholder="请输入角色名称" style="max-width: 180px"></el-input>-->
        <!--          </el-form-item>-->
        <!--          <el-form-item label="角色名称">-->
        <!--            <el-input label="角色名称" size="default" placeholder="请输入角色名称" style="max-width: 180px"></el-input>-->
        <!--          </el-form-item>-->
        <!--          <el-form-item label="角色名称">-->
        <!--            <el-input label="角色名称" size="default" placeholder="请输入角色名称" style="max-width: 180px"></el-input>-->
        <!--          </el-form-item>-->
        <!--          <el-form-item label="角色名称">-->
        <!--            <el-input label="角色名称" size="default" placeholder="请输入角色名称" style="min-width: 180px"></el-input>-->
        <!--          </el-form-item>-->
        <!--          <div style="margin-top: 5px;">-->
        <!--            <el-button size="default" type="primary" class="ml10">-->
        <!--              <el-icon> <ele-Search/> </el-icon>-->
        <!--              查询-->
        <!--            </el-button>-->
        <!--            <el-button size="default" type="primary" class="ml10">-->
        <!--              <el-icon><Refresh /></el-icon>-->
        <!--              重置-->
        <!--            </el-button>-->
        <!--            <el-button size="default" type="primary" class="ml10">-->
        <!--              <el-icon><ele-FolderOpened /></el-icon>-->
        <!--              导出-->
        <!--            </el-button>-->

        <!--          </div>-->

        <!--        </el-form>-->
      </div>

      <div style="    margin-top: 20px;">
        <el-button size="default" type="success" class="ml10">
          <el-icon>
            <ele-FolderAdd/>
          </el-icon>
          新增角色
        </el-button>
      </div>

      <div class="fly-table-box">
        <el-table :data="tableData" style="width: 100%">
          <el-table-column prop="date" label="Date" align="center"/>
          <el-table-column prop="name" label="Name" align="center"/>
          <el-table-column prop="address" label="Address" align="center"/>
          <el-table-column fixed="right" align="center" label="操作" min-width="40">
            <template #default>
              <el-button link type="primary" :icon="Tickets"
                         class="fly-button fly-view-button" @click="handleClick">
                查看
              </el-button>
              <el-button link type="primary" :icon="Edit"
                         class="fly-button fly-edit-button" @click="handleClick">
                编辑
              </el-button>
              <el-button link type="primary" :icon="Delete"
                         class="fly-button fly-remove-button">删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>

        <FlyPagination :total="200" @change="onCurrentChange"></FlyPagination>

      </div>
    </div>

  </div>

</template>

<script setup>
import {ref} from "vue";
import {Delete, Edit, Refresh, Tickets} from "@element-plus/icons-vue";
import FlyPagination from "/@/components/pagination/FlyPagination.vue";
// import {FormInstance} from 'element-plus';


// 分页改变
const onCurrentChange = (val) => {
  console.log(`page: ${val.page} , limit: ${val.limit}`)
};

const tableData = [
  {
    date: '2016-05-03',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-02',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-04',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-01',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
]

// const ruleFormRef = ref<FormInstance>();
//
// const resetForm = (formEl: FormInstance | undefined) => {
//   if (!formEl) return
//   formEl.resetFields()
// }

</script>

