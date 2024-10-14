<template>
  <div>
    <div class="template-container layout-padding">
      <div class="template-container-padding layout-padding-auto layout-padding-view">
        <!-- 表格展示 -->
        <pro-table :data="tableData" :columns="columns" @reloadDate="reloadDate">
          <template #tableHeader="scope">
            <div class="system-user-search mb15">
              <el-input size="default" placeholder="平台ID" class="ml10" style="max-width: 180px"
                        v-model='queryAppInfoParams.platId'></el-input>
              <el-input size="default" placeholder="应用标识" class="ml10" style="max-width: 180px"
                        v-model='queryAppInfoParams.appCode'></el-input>
              <el-input size="default" placeholder="应用名称" class="ml10" style="max-width: 180px"
                        v-model='queryAppInfoParams.appTitle'></el-input>
              <el-input size="default" placeholder="应用类型: BACK-后端;VIEW-前端" class="ml10" style="max-width: 180px"
                        v-model='queryAppInfoParams.appType'></el-input>
              <el-input size="default" placeholder="应用类型" class="ml10" style="max-width: 180px"
                        v-model='queryAppInfoParams.typeCode'></el-input>
              <el-input size="default" placeholder="职能代码" class="ml10" style="max-width: 180px"
                        v-model='queryAppInfoParams.funcCode'></el-input>
              <el-input size="default" placeholder="有效标志: Y-是;N-否" class="ml10" style="max-width: 180px"
                        v-model='queryAppInfoParams.validFlag'></el-input>
              <el-button size="default" type="primary" :icon="Search" class="ml10"
                         v-permission="['admin:app:info:query']"
                         @click='tapSearchHandler(queryAppInfoParams)'> 搜索
              </el-button>
              <el-button size="default" type="success" :icon="Plus" class="ml10"
                         v-permission="['admin:app:info:save']"
                         @click='tapSaveHandler()'> 新增
              </el-button>
            </div>
          </template>
          <!-- 表格操作 -->
          <template #operation="scope">
            <el-button v-permission="['admin:app:info:view']" type="primary" plain :icon="View"
                       size="small" @click="tapViewHandler(scope.row)">查看
            </el-button>
            <el-button v-permission="['admin:app:info:update']" type="primary" plain :icon="Edit"
                       color="#626aef" size="small"
                       @click="tapUpdateHandler(scope.row)">编辑
            </el-button>
            <el-button v-permission="['admin:app:info:delete']" type="warning" plain :icon="Delete"
                       size="small" @click="tapRemoveHandler(scope.row.id, scope.row.name)">删除
            </el-button>
          </template>
        </pro-table>

      </div>
    </div>

    <!-- 新增/修改/查看 弹窗 -->
    <RefAppInfo ref="refAppInfo" @bindtap="tapSubmitHandler"></RefAppInfo>

  </div>
</template>

<script setup lang="ts" name="AppInfoComponent">
import {defineAsyncComponent, onMounted} from 'vue';
import {Search, Edit, View, Delete, Plus} from '@element-plus/icons-vue';
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