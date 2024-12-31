<template>

  <lay-container fluid="true" class="fly-container">

    <div style="display: flex; flex-direction: row; margin-top: 20px;background-color: #FFF; ">

      <!--  菜单树    -->
      <div class="fly-tree-container">
        <div class="fly-tree-title">菜单树</div>
        <lay-tree :data="menuTreeData"
                  v-model:selectedKey="menuTreeChecked"
                  :showLine="false"
                  @node-click="menuNodeClick"
                  style="width: 300px; ">
        </lay-tree>
      </div>

      <!--  菜单表格 -->
      <div v-if="showMenuTable" style="margin-left: 50px;border-left: #eee 1px solid;">
        <lay-card class="fly-search-card" style="padding-top: 0 !important;">
          <lay-form>
            <lay-form-item label="菜单标题" prop="title" mode="inline">
              <lay-input v-model="queryMenuInfoParams.title"></lay-input>
            </lay-form-item>
            <lay-form-item label="有效标志" prop="validFlag" mode="inline">
              <lay-select v-model="queryMenuInfoParams.validFlag">
                <lay-select-option value="" label="全部"></lay-select-option>
                <lay-select-option value="Y" label="有效"></lay-select-option>
                <lay-select-option value="N" label="无效"></lay-select-option>
              </lay-select>

            </lay-form-item>

            <lay-form-item mode="inline">
              <lay-button class="fly-button fly-button-search" style="margin-left: 20px" type="primary" size="sm"
                          @click=" reloadMenuInfoDate(1, flyLayPage.limit) ">
                <lay-icon class="layui-icon-search"></lay-icon>
                查询
              </lay-button>
            </lay-form-item>
          </lay-form>

        </lay-card>
        <div class="table-box">
          <lay-table :page="false"
                     :columns=" menuInfoColumns "
                     :default-toolbar="true"
                     :loading=" tableMenuInfoData.loading "
                     :data-source=" tableMenuInfoData.rows "
                     @change=" reloadMenuInfoDate ">

            <template v-slot:toolbar>
              <lay-button v-if="'0' !== parentId" size="sm" type="primary"
                          class="fly-button fly-toolbar-addition" @click=" onTapMenuInfoAdd ">
                <lay-icon class="layui-icon-addition"></lay-icon>
                新增
              </lay-button>
            </template>

            <template v-slot:operator="{ row }">
              <lay-button size="sm" type="primary" class="fly-button fly-view-button" @click=" onTapMenuInfoView(row) ">
                <lay-icon class="layui-icon-list"></lay-icon>
                查看
              </lay-button>
              <lay-button size="sm" type="primary" class="fly-button fly-edit-button" @click=" onTapMenuInfoEdit(row) ">
                <lay-icon class="layui-icon-edit"></lay-icon>
                编辑
              </lay-button>

              <lay-button size="sm" type="danger" class="fly-button fly-remove-button"
                          @click=" removeMenuInfoHandler(row.id) ">
                <lay-icon class="layui-icon-delete"></lay-icon>
                删除
              </lay-button>
            </template>
            <template #title="{ row }">
              <lay-icon :class="row.layIcon"></lay-icon> &nbsp;&nbsp;
              {{ row.title }}
            </template>
            <template #type="{ row }">
              <div v-show="row.type == 'dirt'">
                <lay-tag color="#165DFF" variant="light">目录</lay-tag>
              </div>
              <div v-show="row.type == 'menu'">
                <lay-tag color="#2dc570" variant="light">菜单</lay-tag>
              </div>
              <div v-show="row.type == 'uri'">
                <lay-tag color="#F5319D" variant="light">外链</lay-tag>
              </div>
              <div v-show="row.type == 'third'">
                <lay-tag color="#F5319D" variant="light">第三方平台</lay-tag>
              </div>
            </template>
          </lay-table>
          <lay-page v-model="flyLayPage.page"
                    v-model:limit="flyLayPage.limit"
                    :layout="flyLayPage.layout"
                    :pages="flyLayPage.pages"
                    :limits="flyLayPage.limits"
                    theme="blue"
                    :total=" tableMenuInfoData.total"
                    @change="previousNextPage" style="margin-top: 15px"></lay-page>
        </div>
      </div>

      <!--  按钮表格 -->
      <div v-if="!showMenuTable" style="margin-left: 50px;border-left: #eee 1px solid;">
        <lay-card class="fly-search-card" style="padding-top: 0px !important;">
          <lay-form>
            <lay-form-item label="标题" prop="title" mode="inline">
              <lay-input v-model="queryElementInfoParams.title"></lay-input>
            </lay-form-item>
            <lay-form-item mode="inline">
              <lay-button class="fly-button fly-button-search" style="margin-left: 20px" type="primary" size="sm"
                          @click=" reloadElementInfoDate(1, flyLayPage.limit) ">
                <lay-icon class="layui-icon-search"></lay-icon>
                查询
              </lay-button>
            </lay-form-item>
          </lay-form>

        </lay-card>
        <div class="table-box">
          <lay-table :page="false" :columns=" elementInfoColumns "
                     :loading=" tableElementInfoData.loading "
                     :data-source=" tableElementInfoData.rows "
                     :default-toolbar="true"
                     @change=" reloadElementInfoDate ">

            <template v-slot:toolbar>
              <lay-button size="sm" type="primary" class="fly-button fly-toolbar-addition" @click=" onTapElementAdd ">
                <lay-icon class="layui-icon-addition"></lay-icon>
                新增
              </lay-button>
            </template>

            <template v-slot:operator="{ row }">
              <lay-button size="sm" type="primary" class="fly-button fly-view-button"
                          @click=" onTapElementInfoView(row) ">
                <lay-icon class="layui-icon-list"></lay-icon>
                查看
              </lay-button>
              <lay-button size="sm" type="primary" class="fly-button fly-edit-button"
                          @click=" onTapElementInfoEdit(row) ">
                <lay-icon class="layui-icon-edit"></lay-icon>
                编辑
              </lay-button>

              <lay-button size="sm" type="danger" class="fly-button fly-remove-button"
                          @click=" removeElementInfoHandler(row.id) ">
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
                    :total=" tableElementInfoData.total"
                    @change="previousNextPage" style="margin-top: 15px"></lay-page>

        </div>
      </div>

    </div>
  </lay-container>


  <RefMenuInfo ref="refMenuInfoComponent"/>

  <RefElementInfo ref="refElementInfoComponent"/>

</template>


<script setup lang="ts" name="menuInfoComponent">
import {ref, reactive, onMounted} from 'vue';
import RefMenuInfo from './RefMenuInfo.vue';
import RefElementInfo from './RefElementInfo.vue';

import useMenuInfoComposable from '@/composables/admin/MenuInfoComposable';
import useElementInfoComposable from "@/composables/admin/ElementInfoComposable";

const {
  refMenuInfoComponent,
  menuInfo,
  flyLayPage,
  tableMenuInfoData,
  menuInfoColumns,
  queryMenuInfoParams,

  /* 加载表格 */
  reloadMenuInfoDate,
  loadTableMenuInfoData,

  /* 新增, 编辑, 查看按钮 */
  onTapMenuInfoAdd,
  onTapMenuInfoEdit,
  onTapMenuInfoView,

  /* 接口 */
  removeMenuInfoHandler,
  menuInfoDataHandler,

  appId,
  parentId,
  parentTitle,
  loadMenuTree,
  menuTreeData,
  menuTreeChecked
} = useMenuInfoComposable();

const {
  refElementInfoComponent,
  elementInfo,
  tableElementInfoData,
  elementInfoColumns,
  queryElementInfoParams,

  /* 加载表格 */
  reloadElementInfoDate,
  loadTableElementInfoData,

  /* 新增, 编辑, 查看按钮 */
  onTapElementInfoAdd,
  onTapElementInfoEdit,
  onTapElementInfoView,

  /* 接口 */
  removeElementInfoHandler,
  elementInfoDataHandler
} = useElementInfoComposable();


const showMenuTable = ref<boolean>(true);


/**
 * 上一页, 下一页
 * @param current
 * @param limit
 */
const previousNextPage = ({current = 1, limit = 10}) => {
  queryMenuInfoParams.page = current;
  queryMenuInfoParams.limit = limit;
  loadTableMenuInfoData(queryMenuInfoParams);
}

/**
 * 点击菜单树
 * @param obj
 */
const menuNodeClick = (obj: any) => {
  parentId.value = obj.id;
  parentTitle.value = obj.title;
  appId.value = obj.attr1;
  queryMenuInfoParams.parentId = obj.id;

  let menuType = obj.type;
  if ('menu' === menuType) {
    showMenuTable.value = false;
    queryElementInfoParams.menuId = obj.id;
    loadTableElementInfoData(queryElementInfoParams);
    return;
  }
  if ('uri' === menuType || 'third' === menuType) {
    return;
  }
  showMenuTable.value = true;
  reloadMenuInfoDate(1, flyLayPage.limit);
}


/**
 * 添加按钮
 */
const onTapElementAdd = () => {
  onTapElementInfoAdd(appId.value, parentId.value, parentTitle.value);
}


/**
 * 页面初始化, 加载数据
 */
onMounted(() => {
  reloadMenuInfoDate(1, flyLayPage.limit);
  loadMenuTree();
})


</script>


<style scoped>

</style>