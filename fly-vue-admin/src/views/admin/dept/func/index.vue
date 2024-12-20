<template>

  <lay-container fluid="true" class="fly-container">
    <lay-card class="fly-search-card">
      <lay-form>
        <lay-form-item label="职能编码" prop="funcCode" mode="inline">
          <lay-input v-model="queryDeptFuncParams.funcCode"></lay-input>
        </lay-form-item>
        <lay-form-item label="职能名称" prop="funcTitle" mode="inline">
          <lay-input v-model="queryDeptFuncParams.funcTitle"></lay-input>
        </lay-form-item>
        <lay-form-item label="有效标志" prop="validFlag" mode="inline">
          <lay-select v-model="queryDeptFuncParams.validFlag">
            <lay-select-option value="" label="全部"></lay-select-option>
            <lay-select-option value="Y" label="有效"></lay-select-option>
            <lay-select-option value="N" label="无效"></lay-select-option>
          </lay-select>
        </lay-form-item>

        <lay-form-item mode="inline">
          <lay-button class="fly-button fly-button-search" style="margin-left: 20px" type="primary" size="sm"
                      @click=" reloadDeptFuncDate(1, flyLayPage.limit) ">
            <lay-icon class="layui-icon-search"></lay-icon>
            查询
          </lay-button>
          <!--          <lay-button class="fly-button fly-button-reset" size="sm" @click=" resetQueryFormValue ">-->
          <!--            <lay-icon class="layui-icon-refresh"></lay-icon>-->
          <!--            重置-->
          <!--          </lay-button>-->
          <!--          <lay-button class="fly-button fly-button-exp" size="sm" @click="">-->
          <!--            <lay-icon class="layui-icon-export"></lay-icon>-->
          <!--            导出-->
          <!--          </lay-button>-->
        </lay-form-item>
      </lay-form>

    </lay-card>


    <!-- table   :default-toolbar="true"  :height="'100%'" line row nob -->
    <div class="table-box">
      <lay-table :page="false" :skin="'nob'"
                 :height="'90%'"
                 :columns=" deptFuncColumns "
                 :loading=" tableDeptFuncData.loading "
                 :data-source=" tableDeptFuncData.rows "
                 @change=" reloadDeptFuncDate ">

        <template v-slot:toolbar>
          <lay-button size="sm" type="primary" class="fly-button fly-toolbar-addition" @click=" onTapDeptFuncAdd ">
            <lay-icon class="layui-icon-addition"></lay-icon>
            新增
          </lay-button>
        </template>

        <template v-slot:operator="{ row }">
          <lay-button size="sm" type="primary" class="fly-button fly-view-button" @click=" onTapDeptFuncView(row) ">
            <lay-icon class="layui-icon-list"></lay-icon>
            查看
          </lay-button>
          <lay-button size="sm" type="primary" class="fly-button fly-edit-button" @click=" onTapDeptFuncEdit(row) ">
            <lay-icon class="layui-icon-edit"></lay-icon>
            编辑
          </lay-button>

          <lay-button size="sm" type="danger" class="fly-button fly-remove-button"
                      @click=" removeDeptFuncHandler(row.id) ">
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
                :total=" tableDeptFuncData.total"
                @change="previousNextPage" style="margin-top: 15px"></lay-page>

    </div>
  </lay-container>


  <RefDeptFunc ref="refDeptFuncComponent"/>

</template>


<script setup lang="ts" name="deptFuncComponent">
import {ref, reactive, onMounted} from 'vue';
import RefDeptFunc from './RefDeptFunc.vue';

import useDeptFuncComposable from '@/composables/admin/DeptFuncComposable';

const {
  refDeptFuncComponent,
  deptFunc,
  flyLayPage,
  tableDeptFuncData,
  deptFuncColumns,
  queryDeptFuncParams,

  /* 加载表格 */
  reloadDeptFuncDate,
  loadTableDeptFuncData,

  /* 新增, 编辑, 查看按钮 */
  onTapDeptFuncAdd,
  onTapDeptFuncEdit,
  onTapDeptFuncView,

  /* 接口 */
  removeDeptFuncHandler,
  deptFuncDataHandler
} = useDeptFuncComposable();


/**
 * 上一页, 下一页
 * @param current
 * @param limit
 */
const previousNextPage = ({current = 1, limit = 10}) => {
  queryDeptFuncParams.page = current;
  queryDeptFuncParams.limit = limit;
  loadTableDeptFuncData(queryDeptFuncParams);
}


/**
 * 页面初始化, 加载数据
 */
onMounted(() => {
  reloadDeptFuncDate(1, flyLayPage.limit);
})


</script>


<style scoped>

</style>