<template>

  <lay-container fluid="true" class="fly-container">
    <lay-card class="fly-search-card">
      <lay-form>
            <lay-form-item label="租户ID" prop="tenantId" mode="inline">
              <lay-input v-model="queryAuthClazzMenuParams.tenantId"></lay-input>
            </lay-form-item>
            <lay-form-item label="应用ID" prop="appId" mode="inline">
              <lay-input v-model="queryAuthClazzMenuParams.appId"></lay-input>
            </lay-form-item>
            <lay-form-item label="岗位代码" prop="clazzCode" mode="inline">
              <lay-input v-model="queryAuthClazzMenuParams.clazzCode"></lay-input>
            </lay-form-item>
            <lay-form-item label="岗位名称" prop="clazzTitle" mode="inline">
              <lay-input v-model="queryAuthClazzMenuParams.clazzTitle"></lay-input>
            </lay-form-item>
            <lay-form-item label="菜单ID/资源ID" prop="menuId" mode="inline">
              <lay-input v-model="queryAuthClazzMenuParams.menuId"></lay-input>
            </lay-form-item>
            <lay-form-item label="菜单名称/资源名称" prop="menuTitle" mode="inline">
              <lay-input v-model="queryAuthClazzMenuParams.menuTitle"></lay-input>
            </lay-form-item>

        <lay-form-item mode="inline">
          <lay-button class="fly-button fly-button-search" style="margin-left: 20px" type="primary" size="sm"
                      @click=" reloadAuthClazzMenuDate(1, flyLayPage.limit) ">
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
      <lay-table :page="false" :skin="'line'"
                 :height="'90%'"
                 :columns=" authClazzMenuColumns "
                 :loading=" tableAuthClazzMenuData.loading "
                 :data-source=" tableAuthClazzMenuData.rows "
                 @change=" reloadAuthClazzMenuDate ">

        <template v-slot:toolbar>
          <lay-button size="sm" type="primary" class="fly-button fly-toolbar-addition" @click=" onTapAuthClazzMenuAdd ">
            <lay-icon class="layui-icon-addition"></lay-icon>
            新增
          </lay-button>
        </template>

        <template v-slot:operator="{ row }">
          <lay-button size="sm" type="primary" class="fly-button fly-view-button" @click=" onTapAuthClazzMenuView(row) ">
            <lay-icon class="layui-icon-list"></lay-icon>
            查看
          </lay-button>
          <lay-button size="sm" type="primary" class="fly-button fly-edit-button" @click=" onTapAuthClazzMenuEdit(row) ">
            <lay-icon class="layui-icon-edit"></lay-icon>
            编辑
          </lay-button>

          <lay-button size="sm" type="danger" class="fly-button fly-remove-button" @click=" removeAuthClazzMenuHandler(row.id) ">
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
                :total=" tableAuthClazzMenuData.total"
                @change="previousNextPage" style="margin-top: 15px"></lay-page>

    </div>
  </lay-container>


  <RefAuthClazzMenu ref="refAuthClazzMenuComponent"/>

</template>


<script setup lang="ts" name="authClazzMenuComponent">
  import {ref, reactive, onMounted} from 'vue';
  import RefAuthClazzMenu from './RefAuthClazzMenu.vue';

  import useAuthClazzMenuComposable from '@/composables/auth/AuthClazzMenuComposable';

  const {
    refAuthClazzMenuComponent,
      authClazzMenu,
    flyLayPage,
    tableAuthClazzMenuData,
      authClazzMenuColumns,
    queryAuthClazzMenuParams,

    /* 加载表格 */
    reloadAuthClazzMenuDate,
    loadTableAuthClazzMenuData,

    /* 新增, 编辑, 查看按钮 */
    onTapAuthClazzMenuAdd,
    onTapAuthClazzMenuEdit,
    onTapAuthClazzMenuView,

    /* 接口 */
    removeAuthClazzMenuHandler,
    authClazzMenuDataHandler
  } = useAuthClazzMenuComposable();


  /**
   * 上一页, 下一页
   * @param current
   * @param limit
   */
  const previousNextPage = ({current = 1, limit = 10}) => {
    queryAuthClazzMenuParams.page = current;
    queryAuthClazzMenuParams.limit = limit;
    loadTableAuthClazzMenuData(queryAuthClazzMenuParams);
  }


  /**
   * 页面初始化, 加载数据
   */
  onMounted(() => {
    reloadAuthClazzMenuDate(1, flyLayPage.limit);
  })



</script>


<style scoped>

</style>