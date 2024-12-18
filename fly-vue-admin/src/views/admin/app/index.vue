<template>

  <lay-container fluid="true" class="fly-container">
    <lay-card class="fly-search-card">
      <lay-form>
        <lay-form-item label="应用名称" prop="appTitle" mode="inline">
          <lay-input v-model="queryAppInfoParams.appTitle"></lay-input>
        </lay-form-item>
        <lay-form-item label="有效标志" prop="validFlag" mode="inline">
          <lay-select v-model="queryAppInfoParams.validFlag">
            <lay-select-option value="" label="全部"></lay-select-option>
            <lay-select-option value="Y" label="有效"></lay-select-option>
            <lay-select-option value="N" label="无效"></lay-select-option>
          </lay-select>
        </lay-form-item>

        <lay-form-item mode="inline">
          <lay-button class="fly-button fly-button-search" style="margin-left: 20px" type="primary" size="sm"
                      @click=" refreshData ">
            <lay-icon class="layui-icon-search"></lay-icon>
            查询
          </lay-button>
<!--          <lay-button type="reset" class="fly-button fly-button-reset" size="sm">-->
<!--            <lay-icon class="layui-icon-refresh"></lay-icon>-->
<!--            重置-->
<!--          </lay-button>-->
          <lay-button class="fly-button fly-button-exp" size="sm" @click="">
            <lay-icon class="layui-icon-export"></lay-icon>
            导出
          </lay-button>
        </lay-form-item>
      </lay-form>

    </lay-card>

    <!-- :skin="'nob'"  :height="'90%'" -->
    <div class="table-box">
      <lay-table :page="false"
                 :defaultToolbar="true"
                 :skin="'line'"
                 :even="true"
                 :columns="appInfoColumns"
                 :loading="tableAppInfoData.loading"
                 :data-source="tableAppInfoData.rows"
                 @change=" reloadAppInfoDate ">

        <template v-slot:toolbar>
          <!--       openIframe    -->
          <lay-button size="sm" type="primary" class="fly-button fly-toolbar-addition" @click="onTapAppInfoAdd">
            <lay-icon class="layui-icon-addition"></lay-icon>
            新增
          </lay-button>
        </template>

        <template v-slot:operator="{ row }">
          <lay-button size="sm" type="primary" class="fly-button fly-view-button" @click=" onTapAppInfoView(row) ">
            <lay-icon class="layui-icon-list"></lay-icon>
            查看
          </lay-button>
          <lay-button size="sm" type="primary" class="fly-button fly-edit-button" @click=" onTapAppInfoEdit(row) ">
            <lay-icon class="layui-icon-edit"></lay-icon>
            编辑
          </lay-button>

          <lay-button size="sm" type="danger" class="fly-button fly-remove-button"
                      @click=" removeAppInfoHandler(row.id, row.appTitle) ">
            <lay-icon class="layui-icon-delete"></lay-icon>
            删除
          </lay-button>

        </template>
      </lay-table>


      <lay-page v-model="flyLayPage.page"
                :layout="flyLayPage.layout"
                :pages="flyLayPage.pages"
                :limits="flyLayPage.limits"
                v-model:limit="flyLayPage.limit"
                theme="blue"
                :total="tableAppInfoData.total"
                @change="previousNextPage" style="margin-top: 15px"></lay-page>


    </div>
  </lay-container>


  <RefAppInfo ref="refAppInfoForm" @click="refreshData"/>

</template>


<script setup lang="ts">
import {onMounted} from "vue";
import {layer} from "@layui/layui-vue"
import RefAppInfo from './RefAppInfo.vue';

import useAppInfoComposable from '@/composables/admin/AppInfoComposable';


const {
  refAppInfoForm,
  flyLayPage,
  tableAppInfoData,
  appInfoColumns,
  queryAppInfoParams,

  /* 加载表格 */
  reloadAppInfoDate,
  loadTableAppInfoData,

  /* 搜索,新增, 编辑, 查看按钮 */
  onTapAppInfoAdd,
  onTapAppInfoEdit,
  onTapAppInfoView,

  /* 接口 */
  removeAppInfoHandler
} = useAppInfoComposable()


/**
 * 页面初始化, 加载数据
 */
onMounted(() => {
  reloadAppInfoDate(1, 10);
})

/**
 * 上一页, 下一页
 * @param current
 * @param limit
 */
const previousNextPage = ({current = 1, limit = 10}) => {
  queryAppInfoParams.page = current;
  queryAppInfoParams.limit = limit;
  loadTableAppInfoData(queryAppInfoParams);
}

/**
 * 刷新数据
 */
const refreshData = () => {
  queryAppInfoParams.page = 1;
  loadTableAppInfoData(queryAppInfoParams);
}


const openIframe = function () {
  layer.open({
    type: 1,
    title: "标题",
    resize: true,
    area: ['90%', '90%'],
    isHtmlFragment: true,
    content: import('./RefAppInfo.vue')
  })
}


</script>


<style scoped>


</style>