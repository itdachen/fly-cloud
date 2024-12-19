<template>

  <lay-container fluid="true" class="fly-container">
    <lay-card class="fly-search-card">
      <lay-form>
        <lay-form-item label="上级ID" prop="parentId" mode="inline">
          <lay-input v-model="queryTenantInfoParams.parentId"></lay-input>
        </lay-form-item>
        <lay-form-item label="租户类型代码, 例如:100" prop="typeId" mode="inline">
          <lay-input v-model="queryTenantInfoParams.typeId"></lay-input>
        </lay-form-item>
        <lay-form-item label="名称" prop="title" mode="inline">
          <lay-input v-model="queryTenantInfoParams.title"></lay-input>
        </lay-form-item>
        <lay-form-item label="所属省级ID" prop="provId" mode="inline">
          <lay-input v-model="queryTenantInfoParams.provId"></lay-input>
        </lay-form-item>
        <lay-form-item label="所属市州" prop="cityId" mode="inline">
          <lay-input v-model="queryTenantInfoParams.cityId"></lay-input>
        </lay-form-item>
        <lay-form-item label="所属区县" prop="countyId" mode="inline">
          <lay-input v-model="queryTenantInfoParams.countyId"></lay-input>
        </lay-form-item>
        <lay-form-item label="图标" prop="homeIcon" mode="inline">
          <lay-input v-model="queryTenantInfoParams.homeIcon"></lay-input>
        </lay-form-item>
        <lay-form-item label="拥有者ID" prop="ownerId" mode="inline">
          <lay-input v-model="queryTenantInfoParams.ownerId"></lay-input>
        </lay-form-item>

        <lay-form-item mode="inline">
          <lay-button class="fly-button fly-button-search" style="margin-left: 20px" type="primary" size="sm"
                      @click=" reloadTenantInfoDate ">
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


    <!-- table   :default-toolbar="true"  :height="'100%'" line row nob -->
    <div class="table-box">
      <lay-table :page="false" :skin="'nob'"
                 :height="'90%'"
                 :columns=" tenantInfoColumns "
                 :loading=" tableTenantInfoData.loading "
                 :data-source=" tableTenantInfoData.rows "
                 @change=" reloadTenantInfoDate ">

        <template v-slot:toolbar>
          <lay-button size="sm" type="primary" class="fly-button fly-toolbar-addition" @click=" onTapTenantInfoAdd ">
            <lay-icon class="layui-icon-addition"></lay-icon>
            新增
          </lay-button>
        </template>

        <template v-slot:operator="{ row }">
          <lay-button size="sm" type="primary" class="fly-button fly-view-button" @click=" onTapTenantInfoView(row) ">
            <lay-icon class="layui-icon-list"></lay-icon>
            查看
          </lay-button>
          <lay-button size="sm" type="primary" class="fly-button fly-edit-button" @click=" onTapTenantInfoEdit(row) ">
            <lay-icon class="layui-icon-edit"></lay-icon>
            编辑
          </lay-button>

          <lay-button size="sm" type="danger" class="fly-button fly-remove-button"
                      @click=" removeTenantInfoHandler(row.id) ">
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
                :total="tableTenantInfoData.total"
                @change="previousNextPage" style="margin-top: 15px"></lay-page>
    </div>
  </lay-container>


  <RefTenantInfo ref="refTenantInfoComponent"/>

</template>


<script setup lang="ts" name="tenantInfoComponent">
import {ref, reactive} from 'vue';
import {layer} from '@layui/layui-vue';
import RefTenantInfo from './RefTenantInfo.vue';

import useTenantInfoComposable from '@/composables/admin/TenantInfoComposable';
import useLayTableComposable from "@/fly/table";

const {flyLayPage} = useLayTableComposable();


const {
  refTenantInfoComponent,
  tenantInfo,
  tableTenantInfoData,
  tenantInfoColumns,
  queryTenantInfoParams,

  /* 加载表格 */
  reloadTenantInfoDate,
  loadTableTenantInfoData,

  /* 新增, 编辑, 查看按钮 */
  onTapTenantInfoAdd,
  onTapTenantInfoEdit,
  onTapTenantInfoView,

  /* 接口 */
  removeTenantInfoHandler,
  tenantInfoDataHandler
} = useTenantInfoComposable();


/**
 * 上一页, 下一页
 * @param current
 * @param limit
 */
const previousNextPage = ({current = 1, limit = 10}) => {
  queryTenantInfoParams.page = current;
  queryTenantInfoParams.limit = limit;
  loadTableTenantInfoData(queryTenantInfoParams);
}

/**
 * 刷新数据
 */
const refreshData = () => {
  queryTenantInfoParams.page = 1;
  loadTableTenantInfoData(queryTenantInfoParams);
}


</script>


<style scoped>

</style>