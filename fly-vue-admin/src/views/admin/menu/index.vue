<template>

  <lay-container fluid="true" class="fly-container">
    <lay-card class="fly-search-card">
      <lay-form>
        <lay-form-item label="类型" prop="type" mode="inline">
          <lay-select v-model="queryMenuInfoParams.type">
            <lay-select-option value="" label="全部"></lay-select-option>
            <lay-select-option value="dirt" label="目录"></lay-select-option>
            <lay-select-option value="menu" label="菜单"></lay-select-option>
          </lay-select>

        </lay-form-item>
        <lay-form-item label="菜单标题" prop="title" mode="inline">
          <lay-input v-model="queryMenuInfoParams.title"></lay-input>
        </lay-form-item>
        <lay-form-item label="有效标志" prop="validFlag" mode="inline">
          <lay-select v-model="queryMenuInfoParams.validFlag">
            <lay-select-option value="" label="全部"></lay-select-option>
            <lay-select-option value="Y" label="有效"></lay-select-option>
            <lay-select-option value="N" label="无效"></lay-select-option>
          </lay-select>

        </lay-form-item>

        <lay-form-item mode="inline">
          <lay-button class="fly-button fly-button-search" style="margin-left: 20px" type="primary" size="sm"
                      @click=" reloadMenuInfoDate(1, flyLayPage.limit) ">
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
                 :columns=" menuInfoColumns "
                 :loading=" tableMenuInfoData.loading "
                 :data-source=" tableMenuInfoData.rows "
                 @change=" reloadMenuInfoDate ">

        <template v-slot:toolbar>
          <lay-button size="sm" type="primary" class="fly-button fly-toolbar-addition" @click=" onTapMenuInfoAdd ">
            <lay-icon class="layui-icon-addition"></lay-icon>
            新增
          </lay-button>
        </template>

        <template v-slot:operator="{ row }">
          <lay-button size="sm" type="primary" class="fly-button fly-view-button" @click=" onTapMenuInfoView(row) ">
            <lay-icon class="layui-icon-list"></lay-icon>
            查看
          </lay-button>
          <lay-button size="sm" type="primary" class="fly-button fly-edit-button" @click=" onTapMenuInfoEdit(row) ">
            <lay-icon class="layui-icon-edit"></lay-icon>
            编辑
          </lay-button>

          <lay-button size="sm" type="danger" class="fly-button fly-remove-button"
                      @click=" removeMenuInfoHandler(row.id) ">
            <lay-icon class="layui-icon-delete"></lay-icon>
            删除
          </lay-button>
        </template>

        <template #type="{ row }">
          <div v-show="row.type == 'dirt'">
            <lay-tag color="#165DFF" variant="light">目录</lay-tag>
          </div>
          <div v-show="row.type == 'menu'">
            <lay-tag color="#2dc570" variant="light">菜单</lay-tag>
          </div>
          <div v-show="row.type == 'uri'">
            <lay-tag color="#F5319D" variant="light">外链</lay-tag>
          </div>
          <div v-show="row.type == 'third'">
            <lay-tag color="#F5319D" variant="light">第三方平台</lay-tag>
          </div>
        </template>
      </lay-table>


      <lay-page v-model="flyLayPage.page"
                v-model:limit="flyLayPage.limit"
                :layout="flyLayPage.layout"
                :pages="flyLayPage.pages"
                :limits="flyLayPage.limits"
                theme="blue"
                :total=" tableMenuInfoData.total"
                @change="previousNextPage" style="margin-top: 15px"></lay-page>

    </div>
  </lay-container>


  <RefMenuInfo ref="refMenuInfoComponent"/>

</template>


<script setup lang="ts" name="menuInfoComponent">
import {ref, reactive, onMounted} from 'vue';
import RefMenuInfo from './RefMenuInfo.vue';

import useMenuInfoComposable from '@/composables/admin/MenuInfoComposable';

const {
  refMenuInfoComponent,
  menuInfo,
  flyLayPage,
  tableMenuInfoData,
  menuInfoColumns,
  queryMenuInfoParams,

  /* 加载表格 */
  reloadMenuInfoDate,
  loadTableMenuInfoData,

  /* 新增, 编辑, 查看按钮 */
  onTapMenuInfoAdd,
  onTapMenuInfoEdit,
  onTapMenuInfoView,

  /* 接口 */
  removeMenuInfoHandler,
  menuInfoDataHandler
} = useMenuInfoComposable();


/**
 * 上一页, 下一页
 * @param current
 * @param limit
 */
const previousNextPage = ({current = 1, limit = 10}) => {
  queryMenuInfoParams.page = current;
  queryMenuInfoParams.limit = limit;
  loadTableMenuInfoData(queryMenuInfoParams);
}


/**
 * 页面初始化, 加载数据
 */
onMounted(() => {
  reloadMenuInfoDate(1, flyLayPage.limit);
})


</script>


<style scoped>

</style>