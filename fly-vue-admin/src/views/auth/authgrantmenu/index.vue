<template>

  <lay-container fluid="true" class="fly-container">
    <lay-card class="fly-search-card">
      <lay-form>
            <lay-form-item label="租户ID" prop="tenantId" mode="inline">
              <lay-input v-model="queryAuthGrantMenuParams.tenantId"></lay-input>
            </lay-form-item>
            <lay-form-item label="appID" prop="appId" mode="inline">
              <lay-input v-model="queryAuthGrantMenuParams.appId"></lay-input>
            </lay-form-item>
            <lay-form-item label="用户ID/人员身份ID" prop="roleId" mode="inline">
              <lay-input v-model="queryAuthGrantMenuParams.roleId"></lay-input>
            </lay-form-item>
            <lay-form-item label="菜单ID" prop="menuId" mode="inline">
              <lay-input v-model="queryAuthGrantMenuParams.menuId"></lay-input>
            </lay-form-item>

        <lay-form-item mode="inline">
          <lay-button class="fly-button fly-button-search" style="margin-left: 20px" type="primary" size="sm"
                      @click=" reloadAuthGrantMenuDate(1, flyLayPage.limit) ">
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
                 :columns=" authGrantMenuColumns "
                 :loading=" tableAuthGrantMenuData.loading "
                 :data-source=" tableAuthGrantMenuData.rows "
                 @change=" reloadAuthGrantMenuDate ">

        <template v-slot:toolbar>
          <lay-button size="sm" type="primary" class="fly-button fly-toolbar-addition" @click=" onTapAuthGrantMenuAdd ">
            <lay-icon class="layui-icon-addition"></lay-icon>
            新增
          </lay-button>
        </template>

        <template v-slot:operator="{ row }">
          <lay-button size="sm" type="primary" class="fly-button fly-view-button" @click=" onTapAuthGrantMenuView(row) ">
            <lay-icon class="layui-icon-list"></lay-icon>
            查看
          </lay-button>
          <lay-button size="sm" type="primary" class="fly-button fly-edit-button" @click=" onTapAuthGrantMenuEdit(row) ">
            <lay-icon class="layui-icon-edit"></lay-icon>
            编辑
          </lay-button>

          <lay-button size="sm" type="danger" class="fly-button fly-remove-button" @click=" removeAuthGrantMenuHandler(row.id) ">
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
                :total=" tableAuthGrantMenuData.total"
                @change="previousNextPage" style="margin-top: 15px"></lay-page>

    </div>
  </lay-container>


  <RefAuthGrantMenu ref="refAuthGrantMenuComponent"/>

</template>


<script setup lang="ts" name="authGrantMenuComponent">
  import {ref, reactive, onMounted} from 'vue';
  import RefAuthGrantMenu from './RefAuthGrantMenu.vue';

  import useAuthGrantMenuComposable from '@/composables/auth/AuthGrantMenuComposable';

  const {
    refAuthGrantMenuComponent,
      authGrantMenu,
    flyLayPage,
    tableAuthGrantMenuData,
      authGrantMenuColumns,
    queryAuthGrantMenuParams,

    /* 加载表格 */
    reloadAuthGrantMenuDate,
    loadTableAuthGrantMenuData,

    /* 新增, 编辑, 查看按钮 */
    onTapAuthGrantMenuAdd,
    onTapAuthGrantMenuEdit,
    onTapAuthGrantMenuView,

    /* 接口 */
    removeAuthGrantMenuHandler,
    authGrantMenuDataHandler
  } = useAuthGrantMenuComposable();


  /**
   * 上一页, 下一页
   * @param current
   * @param limit
   */
  const previousNextPage = ({current = 1, limit = 10}) => {
    queryAuthGrantMenuParams.page = current;
    queryAuthGrantMenuParams.limit = limit;
    loadTableAuthGrantMenuData(queryAuthGrantMenuParams);
  }


  /**
   * 页面初始化, 加载数据
   */
  onMounted(() => {
    reloadAuthGrantMenuDate(1, flyLayPage.limit);
  })



</script>


<style scoped>

</style>