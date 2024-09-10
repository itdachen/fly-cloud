<template>

  <lay-container fluid="true" class="fly-container">
    <lay-card class="fly-search-card">
      <lay-form>
        <lay-form-item label="平台ID" prop="platId" mode="inline">
          <lay-input v-model="queryAppInfoParams.platId"></lay-input>
        </lay-form-item>
        <lay-form-item label="应用名称" prop="appTitle" mode="inline">
          <lay-input v-model="queryAppInfoParams.appTitle"></lay-input>
        </lay-form-item>
        <lay-form-item label="应用类型" prop="appType" mode="inline">
          <lay-input v-model="queryAppInfoParams.appType"></lay-input>
        </lay-form-item>
        <lay-form-item label="有效标志" prop="validFlag" mode="inline">
          <lay-input v-model="queryAppInfoParams.validFlag"></lay-input>
        </lay-form-item>

        <lay-form-item mode="inline">
          <lay-button class="fly-button fly-button-search" style="margin-left: 20px" type="primary" size="sm"
                      @click=" refreshData ">
            <lay-icon class="layui-icon-search"></lay-icon>
            查询
          </lay-button>
          <lay-button class="fly-button fly-button-reset" size="sm" @click=" resetQueryFormValue ">
            <lay-icon class="layui-icon-refresh"></lay-icon>
            重置
          </lay-button>
          <lay-button class="fly-button fly-button-exp" size="sm" @click="">
            <lay-icon class="layui-icon-export"></lay-icon>
            导出
          </lay-button>
        </lay-form-item>
      </lay-form>

    </lay-card>

<!-- :skin="'nob'" -->
    <div class="table-box">
      <lay-table :page="false"
                 :height="'90%'"
                 :columns=" appInfoColumns "
                 :loading=" tableAppInfoData.loading "
                 :data-source=" tableAppInfoData.rows "
                 @change=" reloadAppInfoDate ">

        <template v-slot:toolbar>
          <lay-button size="sm" type="primary" class="fly-button fly-toolbar-addition" @click=" onTapAppInfoAdd ">
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


      <lay-page v-model="tableAppInfoData.page"
                :layout="tableAppInfoData.layout"
                v-model:limit="tableAppInfoData.limit"
                :pages="tableAppInfoData.pages"
                :total="tableAppInfoData.total"
                :limits="tableAppInfoData.limits"
                theme="blue"
                @change="previousNextPage" style="margin-top: 15px"></lay-page>

    </div>
  </lay-container>


  <AddAndEdit ref="refAppInfo" @click="refreshData"/>

</template>


<script setup lang="ts">
import AddAndEdit from './ref.vue';

import useAppInfoComposable from '@/composables/admin/AppInfoComposable';

const {
  refAppInfo,
  tableAppInfoData,
  appInfoColumns,
  queryAppInfoParams,

  /* 加载表格 */
  reloadAppInfoDate,
  loadTableAppInfoData,

  /* 新增, 编辑, 查看按钮 */
  onTapAppInfoAdd,
  onTapAppInfoEdit,
  onTapAppInfoView,

  /* 接口 */
  removeAppInfoHandler
} = useAppInfoComposable();


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

</script>


<style scoped>
.layui-table-cell,
.layui-table-tool-panel li {
  text-overflow: unset !important;
  white-space: unset !important;
  word-wrap: break-word !important;
  word-break: break-all;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2;
  overflow: hidden;
}
</style>