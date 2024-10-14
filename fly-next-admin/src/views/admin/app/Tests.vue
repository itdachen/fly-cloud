<template>

  <div class="fly-container">


    <div class="fly-main-container">

      <!--      <el-main>-->
      <!-- 表格展示 -->
      <div class="fly-table-box">
        <pro-table :data="tableDataVo" :columns="columns" @reload="reloadDate">
          <template #tableHeader="scope">
            <el-form label-width="80px" :inline="true">
              <el-form-item label="名称" class="fly-search-item">
                <el-input :prefix-icon="Search" placeholder="请输入名称"
                          v-model="queryParams.name"
                          @keyup.enter="handlerSearch(queryParams)"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button :icon="Search"  class="fly-button fly-button-search"
                           @click="handlerSearch(queryParams)">搜索</el-button>

                <el-button size="default" plain class="ml10 fly-button fly-toolbar-addition"
                           @click="handlerSave('add')">
                  <el-icon>
                    <ele-FolderAdd/>
                  </el-icon>
                  新增部门
                </el-button>
              </el-form-item>
            </el-form>
          </template>
          <template #operation="scope">
            <el-button link type="primary" :icon="Tickets"
                       class="fly-button fly-view-button" @click="handlerView(scope.row)">
              查看
            </el-button>
            <el-button link type="primary" :icon="Edit"
                       class="fly-button fly-edit-button" @click="handlerUpdate(scope.row)">
              编辑
            </el-button>
            <el-button link type="primary" :icon="Setting"
                       class="fly-button fly-edit-button"
                       @click="assignPermission(scope.row.id, scope.row.name)">
              分配权限
            </el-button>
            <el-button link type="primary" :icon="Delete"
                       class="fly-button fly-remove-button"
                       @click="handlerRemove(scope.row.id, scope.row.name)">删除
            </el-button>
          </template>
        </pro-table>
      </div>

      <!--      </el-main>-->

    </div>
  </div>
  <!--  &lt;!&ndash; 数据操作:新增/修改/查看 &ndash;&gt;-->
  <!--  <RefRole ref="refRole" @submit="onSubmit"></RefRole>-->

  <!--  &lt;!&ndash; 角色权限 &ndash;&gt;-->
  <!--  <tree-popup ref="treePopup" :show-checkbox="true" @select="select"></tree-popup>-->

</template>

<script setup lang="ts">
import {onMounted} from 'vue';
import {Search, Edit, CirclePlus, View, Setting, Delete, Tickets} from '@element-plus/icons-vue';
// import {Delete, Edit, Refresh, Tickets} from "@element-plus/icons-vue";
import ProTable from '/@/components/table/index.vue';
// import RefRole from './RefRole.vue';
import useRoleComposable from "/@/composables/admin/RoleComposable";
// import TreePopup from '@/components/tree/TreePopup.vue'

const {
  refRole,
  tableDataVo,
  columns,
  queryParams,
  reloadDate,
  handlerSearch,
  loadTableData,
  handlerView,
  handlerSave,
  handlerUpdate,
  handlerRemove,
  onSubmit,
  treePopup,
  assignPermission,
  select,
} = useRoleComposable();

/**
 * 初始化页面时加载
 */
onMounted(() => {
  loadTableData(queryParams);
})

</script>

<style lang="scss" scoped>

</style>