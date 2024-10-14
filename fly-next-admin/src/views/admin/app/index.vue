<template>
  <div class="fly-container">
    <div class="fly-main-container">
      <div class="fly-table-box">
        <!-- 表格展示 -->
        <pro-table :data="tableData" :columns="columns" @reload="reloadDate">
          <template #tableHeader="scope">
            <el-form label-width="80px" :inline="true">
              <el-form-item label="应用名称" class="fly-search-item">
                <el-input size="default" placeholder="应用名称" class="ml10" style="max-width: 180px"
                          v-model='queryAppInfoParams.appTitle'></el-input>
              </el-form-item>
              <el-form-item label="应用类型" class="fly-search-item">
                <el-input size="default" placeholder="应用类型" class="ml10" style="max-width: 180px"
                          v-model='queryAppInfoParams.appType'></el-input>
              </el-form-item>
              <el-form-item label="有效标志" class="fly-search-item">
                <el-input size="default" placeholder="有效标志" class="ml10" style="max-width: 180px"
                          v-model='queryAppInfoParams.validFlag'></el-input>
              </el-form-item>
              <el-form-item>
                <el-button v-permission="['admin:app:info:query']"
                           class=" ml10 fly-button fly-button-search"
                           @click='tapSearchHandler(queryAppInfoParams)'>
                  <el-icon>
                    <ele-Search/>
                  </el-icon>
                  搜索
                </el-button>
                <el-button v-permission="['admin:app:info:save']"
                           class="ml10 fly-button fly-toolbar-addition"
                           @click='tapSaveHandler()'>
                  <el-icon>
                    <ele-FolderAdd/>
                  </el-icon>
                  新增
                </el-button>
              </el-form-item>
            </el-form>
          </template>
          <!-- 表格操作 -->
          <template #operation="scope">
            <el-button v-permission="['admin:app:info:view']"
                       :icon="Tickets" class="fly-button fly-view-button"
                       size="small" @click="tapViewHandler(scope.row)">查看
            </el-button>
            <el-button v-permission="['admin:app:info:update']"
                       :icon="Edit" class="fly-button fly-edit-button"
                       @click="tapUpdateHandler(scope.row)">编辑
            </el-button>
            <el-button v-permission="['admin:app:info:delete']"
                       :icon="Delete" class="fly-button fly-remove-button"
                       @click="tapRemoveHandler(scope.row.id, scope.row.appTitle)">删除
            </el-button>
          </template>
        </pro-table>

      </div>
    </div>
  </div>

  <!-- 新增/修改/查看 弹窗 -->
  <RefAppInfo ref="refAppInfo" @bindtap="tapSubmitHandler"></RefAppInfo>

</template>

<script setup lang="ts" name="AppInfoComponent">
import {defineAsyncComponent, onMounted} from 'vue';
import {Search, Edit, View, Delete, Plus, Tickets} from '@element-plus/icons-vue';
import ProTable from '/@/components/table/index.vue';
import useAppInfoComposable from "/@/composables/admin/useAppInfoComposable";

const RefAppInfo = defineAsyncComponent(() => import('./RefAppInfo.vue'));

const {
  refAppInfo,
  tableData,
  columns,
  queryAppInfoParams,
  tapSearchHandler,
  tapSaveHandler,
  tapUpdateHandler,
  tapViewHandler,
  tapRemoveHandler,
  tapSubmitHandler,
  reloadDate,
  loadTableData
} = useAppInfoComposable();

/**
 * 初始化页面时加载
 */
onMounted(() => {
  loadTableData(queryAppInfoParams);
})

</script>

<style lang="scss" scoped>

</style>