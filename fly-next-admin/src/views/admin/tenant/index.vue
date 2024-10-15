<template>
  <div class="fly-container">
    <div class="fly-main-container">
      <div class="fly-table-box">
        <!-- 表格展示 -->
        <pro-table :data="tableData" :columns="columns" @reload="reloadDate">
          <template #tableHeader="scope">
            <el-form label-width="80px" :inline="true">
              <el-form-item label="名称" class="fly-search-item">
                <el-input size="default" placeholder="名称" class="ml10" style="max-width: 180px"
                          v-model='tenantInfoParams.title'></el-input>
              </el-form-item>
<!--              <el-form-item label="所属省级ID" class="fly-search-item">-->
<!--                <el-input size="default" placeholder="所属省级ID" class="ml10" style="max-width: 180px"-->
<!--                          v-model='tenantInfoParams.provId'></el-input>-->
<!--              </el-form-item>-->
<!--              <el-form-item label="所属市州" class="fly-search-item">-->
<!--                <el-input size="default" placeholder="所属市州" class="ml10" style="max-width: 180px"-->
<!--                          v-model='tenantInfoParams.cityId'></el-input>-->
<!--              </el-form-item>-->
<!--              <el-form-item label="所属区县" class="fly-search-item">-->
<!--                <el-input size="default" placeholder="所属区县" class="ml10" style="max-width: 180px"-->
<!--                          v-model='tenantInfoParams.countyId'></el-input>-->
<!--              </el-form-item>-->
<!--              <el-form-item label="拥有者ID" class="fly-search-item">-->
<!--                <el-input size="default" placeholder="拥有者ID" class="ml10" style="max-width: 180px"-->
<!--                          v-model='tenantInfoParams.ownerId'></el-input>-->
<!--              </el-form-item>-->
<!--              <el-form-item label="拥有者昵称" class="fly-search-item">-->
<!--                <el-input size="default" placeholder="拥有者昵称" class="ml10" style="max-width: 180px"-->
<!--                          v-model='tenantInfoParams.ownerNickName'></el-input>-->
<!--              </el-form-item>-->
              <el-form-item label="有效标志" class="fly-search-item">
                <el-select v-model="tenantInfoParams.validFlag" placeholder="请选择" style="width: 180px">
                  <el-option :key="''" :label="'全部'" :value="''"/>
                  <el-option v-for="item in validFlag"
                             :key="item.value"
                             :label="item.label"
                             :value="item.value"/>
                </el-select>
              </el-form-item>
              <el-form-item>
                <el-button v-permission="['admin:tenant:info:query']"
                           class="ml10 fly-button fly-button-search"
                           :icon="Search" @click='searchTenantInfo(tenantInfoParams)'>
                  搜索
                </el-button>
                <el-button v-permission="['admin:tenant:info:save']"
                           class="ml10 fly-button fly-toolbar-addition"
                           :icon="FolderAdd" @click='onTapTenantInfoAdd()'>
                  新增
                </el-button>
              </el-form-item>
            </el-form>
          </template>
          <!-- 表格操作 -->
          <template #operation="scope">
            <el-button v-permission="['admin:tenant:info:view']"
                       :icon="Tickets" class="fly-button fly-view-button"
                       @click="onTapTenantInfoView(scope.row)">查看
            </el-button>
            <el-button v-permission="['admin:tenant:info:update']"
                       :icon="Edit" class="fly-button fly-edit-button"
                       @click="onTapTenantInfoEdit(scope.row)">编辑
            </el-button>
            <el-button v-permission="['admin:tenant:info:delete']"
                       :icon="Delete" class="fly-button fly-remove-button"
                       @click="removeTenantInfo(scope.row.id, scope.row.name)">删除
            </el-button>
          </template>
        </pro-table>

      </div>
    </div>

    <!-- 新增/修改/查看 弹窗 -->
    <RefTenantInfo ref="refTenantInfo" @bindtap="tapTenantInfo"></RefTenantInfo>

  </div>
</template>

<script setup lang="ts" name="TenantInfoComponent">
import {defineAsyncComponent, onMounted} from 'vue';
import {FolderAdd, Search, Edit, Delete, Tickets} from '@element-plus/icons-vue';
import ProTable from '/@/components/table/index.vue';
import useTenantInfoComposable from "/@/composables/admin/useTenantInfoComposable";
import useDictDataComposable from "/@/hooks/dict/useDictData";

const RefTenantInfo = defineAsyncComponent(() => import('./RefTenantInfo.vue'));

const {
  refTenantInfo,
  tableData,
  columns,
  tenantInfoParams,
  searchTenantInfo,
  onTapTenantInfoAdd,
  onTapTenantInfoEdit,
  onTapTenantInfoView,
  removeTenantInfo,
  tapTenantInfo,
  reloadDate,
  loadTableData
} = useTenantInfoComposable();

const {validFlag} = useDictDataComposable();

/**
 * 初始化页面时加载
 */
onMounted(() => {
  loadTableData(tenantInfoParams);
})

</script>

<style lang="scss" scoped>

</style>