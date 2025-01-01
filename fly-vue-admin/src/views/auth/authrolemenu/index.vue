<template>

  <lay-container fluid="true" class="fly-container">
    <lay-card class="fly-search-card">
      <lay-form>
            <lay-form-item label="租户ID" prop="tenantId" mode="inline">
              <lay-input v-model="queryAuthRoleMenuParams.tenantId"></lay-input>
            </lay-form-item>
            <lay-form-item label="应用ID" prop="appId" mode="inline">
              <lay-input v-model="queryAuthRoleMenuParams.appId"></lay-input>
            </lay-form-item>
            <lay-form-item label="人员身份ID" prop="roleId" mode="inline">
              <lay-input v-model="queryAuthRoleMenuParams.roleId"></lay-input>
            </lay-form-item>
            <lay-form-item label="菜单ID" prop="menuId" mode="inline">
              <lay-input v-model="queryAuthRoleMenuParams.menuId"></lay-input>
            </lay-form-item>

        <lay-form-item mode="inline">
          <lay-button class="fly-button fly-button-search" style="margin-left: 20px" type="primary" size="sm"
                      @click=" reloadAuthRoleMenuDate(1, flyLayPage.limit) ">
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
                 :columns=" authRoleMenuColumns "
                 :loading=" tableAuthRoleMenuData.loading "
                 :data-source=" tableAuthRoleMenuData.rows "
                 @change=" reloadAuthRoleMenuDate ">

        <template v-slot:toolbar>
          <lay-button size="sm" type="primary" class="fly-button fly-toolbar-addition" @click=" onTapAuthRoleMenuAdd ">
            <lay-icon class="layui-icon-addition"></lay-icon>
            新增
          </lay-button>
        </template>

        <template v-slot:operator="{ row }">
          <lay-button size="sm" type="primary" class="fly-button fly-view-button" @click=" onTapAuthRoleMenuView(row) ">
            <lay-icon class="layui-icon-list"></lay-icon>
            查看
          </lay-button>
          <lay-button size="sm" type="primary" class="fly-button fly-edit-button" @click=" onTapAuthRoleMenuEdit(row) ">
            <lay-icon class="layui-icon-edit"></lay-icon>
            编辑
          </lay-button>

          <lay-button size="sm" type="danger" class="fly-button fly-remove-button" @click=" removeAuthRoleMenuHandler(row.id) ">
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
                :total=" tableAuthRoleMenuData.total"
                @change="previousNextPage" style="margin-top: 15px"></lay-page>

    </div>
  </lay-container>


  <RefAuthRoleMenu ref="refAuthRoleMenuComponent"/>

</template>


<script setup lang="ts" name="authRoleMenuComponent">
  import {ref, reactive, onMounted} from 'vue';
  import RefAuthRoleMenu from './RefAuthRoleMenu.vue';

  import useAuthRoleMenuComposable from '@/composables/auth/AuthRoleMenuComposable';

  const {
    refAuthRoleMenuComponent,
      authRoleMenu,
    flyLayPage,
    tableAuthRoleMenuData,
      authRoleMenuColumns,
    queryAuthRoleMenuParams,

    /* 加载表格 */
    reloadAuthRoleMenuDate,
    loadTableAuthRoleMenuData,

    /* 新增, 编辑, 查看按钮 */
    onTapAuthRoleMenuAdd,
    onTapAuthRoleMenuEdit,
    onTapAuthRoleMenuView,

    /* 接口 */
    removeAuthRoleMenuHandler,
    authRoleMenuDataHandler
  } = useAuthRoleMenuComposable();


  /**
   * 上一页, 下一页
   * @param current
   * @param limit
   */
  const previousNextPage = ({current = 1, limit = 10}) => {
    queryAuthRoleMenuParams.page = current;
    queryAuthRoleMenuParams.limit = limit;
    loadTableAuthRoleMenuData(queryAuthRoleMenuParams);
  }


  /**
   * 页面初始化, 加载数据
   */
  onMounted(() => {
    reloadAuthRoleMenuDate(1, flyLayPage.limit);
  })



</script>


<style scoped>

</style>