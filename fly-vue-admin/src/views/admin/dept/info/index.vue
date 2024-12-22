<template>

  <lay-container fluid="true" class="fly-container">

    <div style="display: flex; flex-direction: row; margin-top: 20px;background-color: #FFF; ">

      <div style="width: 500px; padding: 20px">
        <lay-tree :data="treeData"
                  v-model:selectedKey="selectedKey"
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
        <div class="table-box">
          <lay-table :page="false" :skin="'line'"
                     :height="'90%'"
                     :columns=" deptInfoColumns "
                     :loading=" tableDeptInfoData.loading "
                     :data-source=" tableDeptInfoData.rows "
                     @change=" reloadDeptInfoDate ">

            <template v-slot:toolbar>
              <lay-button size="sm" type="primary" class="fly-button fly-toolbar-addition" @click=" onTapDeptInfoAdd ">
                <lay-icon class="layui-icon-addition"></lay-icon>
                新增
              </lay-button>
            </template>

            <template v-slot:operator="{ row }">
              <lay-button size="sm" type="primary" class="fly-button fly-view-button" @click=" onTapDeptInfoView(row) ">
                <lay-icon class="layui-icon-list"></lay-icon>
                查看
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

</template>


<script setup lang="ts" name="deptInfoComponent">
import {ref, reactive, onMounted} from 'vue';
import RefDeptInfo from './RefDeptInfo.vue';

import useDeptInfoComposable from '@/composables/admin/DeptInfoComposable';

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
  deptInfoDataHandler
} = useDeptInfoComposable();


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
})


const selectedKey = ref('1')

const treeData = [
  {
    id: '0',
    title: '太虚十境--',
    spread: true,
    children: [
      {
        id: '1',
        title: '太虚十境贵州省十境--',
        children: [
          {
            id: '1-1',
            title: '太虚十境贵州省贵阳市十境',
            children: [
              {
                id: '1-1-1',
                title: '太虚十境贵州省观山湖区第二太虚十境',
                children: []
              }
            ]
          }
        ]
      },
      {
        id: '2',
        title: '太虚十境云南省十境--',
        children: [
          {
            id: '2-1',
            title: '2-1-',
            children: []
          }
        ]
      }
    ]
  },



]

const deptNodeClick = (obj: any) => {
  console.log('deptNodeClick', obj)
}


</script>


<style scoped>

</style>