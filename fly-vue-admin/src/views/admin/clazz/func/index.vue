<template>

  <lay-container fluid="true" class="fly-container">
    <lay-card class="fly-search-card">
      <lay-form>
        <lay-form-item label="岗位职能编码" prop="funcCode" mode="inline">
          <lay-input v-model="queryClazzFuncParams.funcCode"></lay-input>
        </lay-form-item>
        <lay-form-item label="岗位职能标题" prop="funcTitle" mode="inline">
          <lay-input v-model="queryClazzFuncParams.funcTitle"></lay-input>
        </lay-form-item>
        <lay-form-item label="有效标志" prop="validFlag" mode="inline">
          <lay-select v-model="queryClazzFuncParams.validFlag">
            <lay-select-option value="" label="全部"></lay-select-option>
            <lay-select-option value="Y" label="有效"></lay-select-option>
            <lay-select-option value="N" label="无效"></lay-select-option>
          </lay-select>
        </lay-form-item>

        <lay-form-item mode="inline">
          <lay-button class="fly-button fly-button-search" style="margin-left: 20px" type="primary" size="sm"
                      @click=" reloadClazzFuncDate(1, flyLayPage.limit) ">
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
      <lay-table :page="false" :skin="'line'"
                 :height="'90%'"
                 :columns=" clazzFuncColumns "
                 :loading=" tableClazzFuncData.loading "
                 :data-source=" tableClazzFuncData.rows "
                 @change=" reloadClazzFuncDate ">

        <template v-slot:toolbar>
          <lay-button size="sm" type="primary" class="fly-button fly-toolbar-addition" @click=" onTapClazzFuncAdd ">
            <lay-icon class="layui-icon-addition"></lay-icon>
            新增
          </lay-button>
        </template>

        <template v-slot:operator="{ row }">
          <lay-button size="sm" type="primary" class="fly-button fly-view-button" @click=" onTapClazzFuncView(row) ">
            <lay-icon class="layui-icon-list"></lay-icon>
            查看
          </lay-button>
          <lay-button size="sm" type="primary" class="fly-button fly-edit-button" @click=" onTapClazzFuncEdit(row) ">
            <lay-icon class="layui-icon-edit"></lay-icon>
            编辑
          </lay-button>

          <lay-button size="sm" type="danger" class="fly-button fly-remove-button"
                      @click=" removeClazzFuncHandler(row.id) ">
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
                :total=" tableClazzFuncData.total"
                @change="previousNextPage" style="margin-top: 15px"></lay-page>

    </div>
  </lay-container>


  <RefClazzFunc ref="refClazzFuncComponent"/>

</template>


<script setup lang="ts" name="clazzFuncComponent">
import {ref, reactive, onMounted} from 'vue';
import RefClazzFunc from './RefClazzFunc.vue';

import useClazzFuncComposable from '@/composables/admin/ClazzFuncComposable';

const {
  refClazzFuncComponent,
  clazzFunc,
  flyLayPage,
  tableClazzFuncData,
  clazzFuncColumns,
  queryClazzFuncParams,

  /* 加载表格 */
  reloadClazzFuncDate,
  loadTableClazzFuncData,

  /* 新增, 编辑, 查看按钮 */
  onTapClazzFuncAdd,
  onTapClazzFuncEdit,
  onTapClazzFuncView,

  /* 接口 */
  removeClazzFuncHandler,
  clazzFuncDataHandler
} = useClazzFuncComposable();


/**
 * 上一页, 下一页
 * @param current
 * @param limit
 */
const previousNextPage = ({current = 1, limit = 10}) => {
  queryClazzFuncParams.page = current;
  queryClazzFuncParams.limit = limit;
  loadTableClazzFuncData(queryClazzFuncParams);
}


/**
 * 页面初始化, 加载数据
 */
onMounted(() => {
  reloadClazzFuncDate(1, flyLayPage.limit);
})


</script>


<style scoped>

</style>