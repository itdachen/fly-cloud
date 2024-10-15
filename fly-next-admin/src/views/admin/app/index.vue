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
              <el-form-item label="前端或后端" prop="appType" class="mb10">
                <el-select v-model="queryAppInfoParams.appType" placeholder="请选择" style="width: 180px">
                  <el-option label="全部" value=""/>
                  <el-option :key="'BACK'" :label="'后端'" :value="'BACK'"/>
                  <el-option :key="'VIEW'" :label="'前端'" :value="'VIEW'"/>
                </el-select>
              </el-form-item>
              <el-form-item label="应用类型" class="fly-search-item">
                <el-select v-model="queryAppInfoParams.appCode" placeholder="请选择" style="width: 180px">
                  <el-option label="全部" value=""/>
                  <el-option v-for="item in appCodeOptions"
                             :key="item.value"
                             :label="item.label"
                             :value="item.value"/>
                </el-select>
              </el-form-item>
              <el-form-item label="有效标志" class="fly-search-item">
                <el-select v-model="queryAppInfoParams.validFlag" placeholder="请选择有效标志" style="width: 180px">
                  <el-option label="全部" value=""/>
                  <el-option v-for="item in validFlag"
                             :key="item.value"
                             :label="item.label"
                             :value="item.value"/>
                </el-select>
              </el-form-item>
              <el-form-item>
                <el-button v-permission="['admin:app:info:query']" :icon="Search"
                           class=" ml10 fly-button fly-button-search"
                           @click='tapSearchHandler(queryAppInfoParams)'>
                  搜索
                </el-button>
                <el-button v-permission="['admin:app:info:save']" :icon="FolderAdd"
                           class="ml10 fly-button fly-toolbar-addition"
                           @click='tapSaveHandler()'>
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
import {FolderAdd, Search, Edit, Delete, Tickets} from '@element-plus/icons-vue';
import ProTable from '/@/components/table/index.vue';
import useAppInfoComposable from "/@/composables/admin/useAppInfoComposable";
import useDictDataComposable from "/@/hooks/dict/useDictData";

const RefAppInfo = defineAsyncComponent(() => import('./RefAppInfo.vue'));

const {validFlag} = useDictDataComposable();

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


/**
 * 应用类型
 */
const appCodeOptions = [
  {
    value: 'BS',
    label: '业务系统'
  },
  {
    value: 'VIEW',
    label: '可视化大屏',
  },
  {
    value: 'OSS',
    label: '文件上传',
  },
  {
    value: 'UMS',
    label: '统一管理系统',
  },
  {
    value: 'GATEWAY',
    label: '网关',
  }
]


</script>

<style lang="scss" scoped>

</style>