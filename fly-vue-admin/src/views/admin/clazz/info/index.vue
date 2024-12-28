<template>

  <lay-container fluid="true" class="fly-container">
    <lay-card class="fly-search-card">
      <lay-form>
        <lay-form-item label="岗位代码" prop="clazzCode" mode="inline">
          <lay-input v-model="queryClazzInfoParams.clazzCode"></lay-input>
        </lay-form-item>
        <lay-form-item label="岗位名称" prop="clazzTitle" mode="inline">
          <lay-input v-model="queryClazzInfoParams.clazzTitle"></lay-input>
        </lay-form-item>

        <lay-form-item label="有效标志" prop="validFlag" mode="inline">
          <lay-select v-model="queryClazzInfoParams.validFlag">
            <lay-select-option value="" label="全部"></lay-select-option>
            <lay-select-option value="Y" label="有效"></lay-select-option>
            <lay-select-option value="N" label="无效"></lay-select-option>
          </lay-select>
        </lay-form-item>

        <lay-form-item mode="inline">
          <lay-button class="fly-button fly-button-search" style="margin-left: 20px" type="primary" size="sm"
                      @click=" reloadClazzInfoDate(1, flyLayPage.limit) ">
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
                 :columns=" clazzInfoColumns "
                 :loading=" tableClazzInfoData.loading "
                 :data-source=" tableClazzInfoData.rows "
                 @change=" reloadClazzInfoDate ">

        <template v-slot:toolbar>
          <lay-button size="sm" type="primary" class="fly-button fly-toolbar-addition" @click=" onTapClazzInfoAdd ">
            <lay-icon class="layui-icon-addition"></lay-icon>
            新增
          </lay-button>
        </template>

        <template v-slot:operator="{ row }">
          <lay-button size="sm" type="primary" class="fly-button fly-view-button" @click=" onTapClazzInfoView(row) ">
            <lay-icon class="layui-icon-list"></lay-icon>
            查看
          </lay-button>
          <lay-button size="sm" type="primary" class="fly-button fly-edit-button" @click=" onTapClazzInfoEdit(row) ">
            <lay-icon class="layui-icon-edit"></lay-icon>
            编辑
          </lay-button>

          <lay-button size="sm" type="danger" class="fly-button fly-remove-button"
                      @click=" removeClazzInfoHandler(row.id) ">
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
                :total=" tableClazzInfoData.total"
                @change="previousNextPage" style="margin-top: 15px"></lay-page>

    </div>
  </lay-container>


  <RefClazzInfo ref="refClazzInfoComponent"/>

</template>


<script setup lang="ts" name="clazzInfoComponent">
import {ref, reactive, onMounted} from 'vue';
import RefClazzInfo from './RefClazzInfo.vue';

import useClazzInfoComposable from '@/composables/admin/ClazzInfoComposable';

const {
  refClazzInfoComponent,
  clazzInfo,
  flyLayPage,
  tableClazzInfoData,
  clazzInfoColumns,
  queryClazzInfoParams,

  /* 加载表格 */
  reloadClazzInfoDate,
  loadTableClazzInfoData,

  /* 新增, 编辑, 查看按钮 */
  onTapClazzInfoAdd,
  onTapClazzInfoEdit,
  onTapClazzInfoView,

  /* 接口 */
  removeClazzInfoHandler,
  clazzInfoDataHandler
} = useClazzInfoComposable();


/**
 * 上一页, 下一页
 * @param current
 * @param limit
 */
const previousNextPage = ({current = 1, limit = 10}) => {
  queryClazzInfoParams.page = current;
  queryClazzInfoParams.limit = limit;
  loadTableClazzInfoData(queryClazzInfoParams);
}


/**
 * 页面初始化, 加载数据
 */
onMounted(() => {
  reloadClazzInfoDate(1, flyLayPage.limit);
})


</script>


<style scoped>

</style>