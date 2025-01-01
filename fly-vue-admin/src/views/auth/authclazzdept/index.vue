<template>

  <lay-container fluid="true" class="fly-container">
    <lay-card class="fly-search-card">
      <lay-form>
        <lay-form-item label="岗位名称" prop="clazzTitle" mode="inline">
          <lay-input v-model="queryAuthClazzDeptParams.clazzTitle"></lay-input>
        </lay-form-item>
        <lay-form-item label="有效标志: Y-是;N-否" prop="validFlag" mode="inline">
          <lay-input v-model="queryAuthClazzDeptParams.validFlag"></lay-input>
        </lay-form-item>
        <lay-form-item mode="inline">
          <lay-button class="fly-button fly-button-search" style="margin-left: 20px" type="primary" size="sm"
                      @click=" reloadAuthClazzDeptDate(1, flyLayPage.limit) ">
            <lay-icon class="layui-icon-search"></lay-icon>
            查询
          </lay-button>
        </lay-form-item>
      </lay-form>

    </lay-card>


    <!-- table   :default-toolbar="true"  :height="'100%'" line row nob -->
    <div class="table-box">
      <lay-table :page="false"
                 :columns=" authClazzDeptColumns "
                 :loading=" tableAuthClazzDeptData.loading "
                 :data-source=" tableAuthClazzDeptData.rows "
                 @change=" reloadAuthClazzDeptDate ">

        <template v-slot:toolbar>
          <lay-button size="sm" type="primary" class="fly-button fly-toolbar-addition" @click=" onTapAuthClazzDeptAdd ">
            <lay-icon class="layui-icon-addition"></lay-icon>
            新增
          </lay-button>
        </template>

        <template v-slot:operator="{ row }">
          <lay-button size="sm" type="primary" class="fly-button fly-view-button"
                      @click=" onTapAuthClazzDeptView(row) ">
            <lay-icon class="layui-icon-list"></lay-icon>
            查看
          </lay-button>
          <lay-button size="sm" type="primary" class="fly-button fly-edit-button"
                      @click=" onTapAuthClazzDeptEdit(row) ">
            <lay-icon class="layui-icon-edit"></lay-icon>
            编辑
          </lay-button>

          <lay-button size="sm" type="danger" class="fly-button fly-remove-button"
                      @click=" removeAuthClazzDeptHandler(row.id) ">
            <lay-icon class="layui-icon-delete"></lay-icon>
            删除
          </lay-button>

        </template>
      </lay-table>


      <lay-page v-model="flyLayPage.page"
                v-model:limit="flyLayPage.limit"
                :layout="flyLayPage.layout"
                :pages="flyLayPage.pages"
                :limits="flyLayPage.limits"
                theme="blue"
                :total=" tableAuthClazzDeptData.total"
                @change="previousNextPage" style="margin-top: 15px"></lay-page>

    </div>
  </lay-container>


  <RefAuthClazzDept ref="refAuthClazzDeptComponent"/>

</template>


<script setup lang="ts" name="authClazzDeptComponent">
import {ref, reactive, onMounted} from 'vue';
import RefAuthClazzDept from './RefAuthClazzDept.vue';

import useAuthClazzDeptComposable from '@/composables/auth/AuthClazzDeptComposable';

const {
  refAuthClazzDeptComponent,
  authClazzDept,
  flyLayPage,
  tableAuthClazzDeptData,
  authClazzDeptColumns,
  queryAuthClazzDeptParams,

  /* 加载表格 */
  reloadAuthClazzDeptDate,
  loadTableAuthClazzDeptData,

  /* 新增, 编辑, 查看按钮 */
  onTapAuthClazzDeptAdd,
  onTapAuthClazzDeptEdit,
  onTapAuthClazzDeptView,

  /* 接口 */
  removeAuthClazzDeptHandler,
  authClazzDeptDataHandler
} = useAuthClazzDeptComposable();


/**
 * 上一页, 下一页
 * @param current
 * @param limit
 */
const previousNextPage = ({current = 1, limit = 10}) => {
  queryAuthClazzDeptParams.page = current;
  queryAuthClazzDeptParams.limit = limit;
  loadTableAuthClazzDeptData(queryAuthClazzDeptParams);
}


/**
 * 页面初始化, 加载数据
 */
onMounted(() => {
  reloadAuthClazzDeptDate(1, flyLayPage.limit);
})


</script>


<style scoped>

</style>