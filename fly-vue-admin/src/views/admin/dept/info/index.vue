<template>

  <lay-container fluid="true" class="fly-container">

    <div style="display: flex; flex-direction: row; margin-top: 20px;background-color: #FFF; ">

      <div class="fly-tree-container">
        <div class="fly-tree-title">机构树</div>
        <lay-tree :data="deptTreeData"
                  v-model:selectedKey="deptTreeChecked"
                  :showLine="false"
                  @node-click="deptNodeClick"
                  style="width: 500px; ">
        </lay-tree>
      </div>

      <div style="margin-left: 50px;border-left: #eee 1px solid;">
        <lay-card class="fly-search-card">
          <lay-form>
            <lay-form-item label="部门名称" prop="title" mode="inline">
              <lay-input v-model="queryDeptInfoParams.title"></lay-input>
            </lay-form-item>
            <lay-form-item mode="inline">
              <lay-button class="fly-button fly-button-search" style="margin-left: 20px" type="primary" size="sm"
                          @click=" reloadDeptInfoDate(1, flyLayPage.limit) ">
                <lay-icon class="layui-icon-search"></lay-icon>
                查询
              </lay-button>
              <!--              <lay-button class="fly-button fly-button-reset" size="sm" @click=" resetQueryFormValue ">-->
              <!--                <lay-icon class="layui-icon-refresh"></lay-icon>-->
              <!--                重置-->
              <!--              </lay-button>-->
              <!--              <lay-button class="fly-button fly-button-exp" size="sm" @click="">-->
              <!--                <lay-icon class="layui-icon-export"></lay-icon>-->
              <!--                导出-->
              <!--              </lay-button>-->
            </lay-form-item>
          </lay-form>

        </lay-card>
        <div class="table-box">
          <lay-table :page="false" :skin="'line'"
                     :height="'90%'"
                     :columns=" deptInfoColumns "
                     :loading=" tableDeptInfoData.loading "
                     :data-source=" tableDeptInfoData.rows "
                     @change=" reloadDeptInfoDate ">

            <template v-slot:toolbar>
              <lay-button v-if="'0' != parentDeptCode" size="sm" type="primary" class="fly-button fly-toolbar-addition"
                          @click=" onTapDeptInfoAdd ">
                <lay-icon class="layui-icon-addition"></lay-icon>
                新增
              </lay-button>
            </template>

            <template v-slot:operator="{ row }">
              <lay-button size="sm" type="primary" class="fly-button fly-view-button" @click=" onTapDeptInfoView(row) ">
                <lay-icon class="layui-icon-list"></lay-icon>
                查看
              </lay-button>
              <lay-button size="sm" type="primary" class="fly-button fly-edit-button" @click=" onTapDeptClazz(row) ">
                <lay-icon class="layui-icon-auz"></lay-icon>
                岗位设置
              </lay-button>
              <lay-button size="sm" type="primary" class="fly-button fly-edit-button" @click=" onTapDeptInfoEdit(row) ">
                <lay-icon class="layui-icon-edit"></lay-icon>
                编辑
              </lay-button>

              <lay-button size="sm" type="danger" class="fly-button fly-remove-button"
                          @click=" removeDeptInfoHandler(row.id) ">
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
                    :total=" tableDeptInfoData.total"
                    @change="previousNextPage" style="margin-top: 15px"></lay-page>

        </div>
      </div>

    </div>


  </lay-container>


  <RefDeptInfo ref="refDeptInfoComponent"/>

  <RefDeptClazz  ref="refDeptClazzComponent"/>

</template>


<script setup lang="ts" name="deptInfoComponent">
import {ref, reactive, onMounted} from 'vue';
import RefDeptInfo from './RefDeptInfo.vue';
import RefDeptClazz from './RefDeptClazz.vue';

import useDeptInfoComposable from '@/composables/admin/DeptInfoComposable';
import {DeptInfo} from "@/api/admin/model/DeptInfoModel";

const {
  refDeptInfoComponent,
  deptInfo,
  flyLayPage,
  tableDeptInfoData,
  deptInfoColumns,
  queryDeptInfoParams,

  /* 加载表格 */
  reloadDeptInfoDate,
  loadTableDeptInfoData,

  /* 新增, 编辑, 查看按钮 */
  onTapDeptInfoAdd,
  onTapDeptInfoEdit,
  onTapDeptInfoView,

  /* 接口 */
  removeDeptInfoHandler,
  deptInfoDataHandler,
  loadDeptTree,
  deptTreeData,
  deptTreeChecked,
  parentDeptCode
} = useDeptInfoComposable();

/* 部门岗位弹窗 */
const refDeptClazzComponent = ref();


/**
 * 上一页, 下一页
 * @param current
 * @param limit
 */
const previousNextPage = ({current = 1, limit = 10}) => {
  queryDeptInfoParams.page = current;
  queryDeptInfoParams.limit = limit;
  loadTableDeptInfoData(queryDeptInfoParams);
}


/**
 * 页面初始化, 加载数据
 */
onMounted(() => {
  reloadDeptInfoDate(1, flyLayPage.limit);
  loadDeptTree();
})

const deptNodeClick = (obj: any) => {
  queryDeptInfoParams.parentCode = obj.id;
  parentDeptCode.value = obj.id;
  reloadDeptInfoDate(1, flyLayPage.limit);
}


/**
 * 岗位设置
 * @param row
 */
const onTapDeptClazz = (row: DeptInfo) => {
  refDeptClazzComponent.value?.open(row);
}


</script>


<style scoped>

</style>