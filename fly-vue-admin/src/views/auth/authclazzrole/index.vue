<template>

  <lay-container fluid="true" class="fly-container">
    <lay-card class="fly-search-card">
      <lay-form>
            <lay-form-item label="租户ID" prop="tenantId" mode="inline">
              <lay-input v-model="queryAuthClazzRoleParams.tenantId"></lay-input>
            </lay-form-item>
            <lay-form-item label="应用ID" prop="appId" mode="inline">
              <lay-input v-model="queryAuthClazzRoleParams.appId"></lay-input>
            </lay-form-item>
            <lay-form-item label="岗位代码" prop="clazzCode" mode="inline">
              <lay-input v-model="queryAuthClazzRoleParams.clazzCode"></lay-input>
            </lay-form-item>
            <lay-form-item label="岗位名称" prop="clazzTitle" mode="inline">
              <lay-input v-model="queryAuthClazzRoleParams.clazzTitle"></lay-input>
            </lay-form-item>
            <lay-form-item label="身份ID" prop="roleId" mode="inline">
              <lay-input v-model="queryAuthClazzRoleParams.roleId"></lay-input>
            </lay-form-item>
            <lay-form-item label="有效标志: Y-是;N-否" prop="validFlag" mode="inline">
              <lay-input v-model="queryAuthClazzRoleParams.validFlag"></lay-input>
            </lay-form-item>
            <lay-form-item label="删除标志: Y-已删除;N-未删除" prop="deleteFlag" mode="inline">
              <lay-input v-model="queryAuthClazzRoleParams.deleteFlag"></lay-input>
            </lay-form-item>

        <lay-form-item mode="inline">
          <lay-button class="fly-button fly-button-search" style="margin-left: 20px" type="primary" size="sm"
                      @click=" reloadAuthClazzRoleDate(1, flyLayPage.limit) ">
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
                 :columns=" authClazzRoleColumns "
                 :loading=" tableAuthClazzRoleData.loading "
                 :data-source=" tableAuthClazzRoleData.rows "
                 @change=" reloadAuthClazzRoleDate ">

        <template v-slot:toolbar>
          <lay-button size="sm" type="primary" class="fly-button fly-toolbar-addition" @click=" onTapAuthClazzRoleAdd ">
            <lay-icon class="layui-icon-addition"></lay-icon>
            新增
          </lay-button>
        </template>

        <template v-slot:operator="{ row }">
          <lay-button size="sm" type="primary" class="fly-button fly-view-button" @click=" onTapAuthClazzRoleView(row) ">
            <lay-icon class="layui-icon-list"></lay-icon>
            查看
          </lay-button>
          <lay-button size="sm" type="primary" class="fly-button fly-edit-button" @click=" onTapAuthClazzRoleEdit(row) ">
            <lay-icon class="layui-icon-edit"></lay-icon>
            编辑
          </lay-button>

          <lay-button size="sm" type="danger" class="fly-button fly-remove-button" @click=" removeAuthClazzRoleHandler(row.id) ">
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
                :total=" tableAuthClazzRoleData.total"
                @change="previousNextPage" style="margin-top: 15px"></lay-page>

    </div>
  </lay-container>


  <RefAuthClazzRole ref="refAuthClazzRoleComponent"/>

</template>


<script setup lang="ts" name="authClazzRoleComponent">
  import {ref, reactive, onMounted} from 'vue';
  import RefAuthClazzRole from './RefAuthClazzRole.vue';

  import useAuthClazzRoleComposable from '@/composables/auth/AuthClazzRoleComposable';

  const {
    refAuthClazzRoleComponent,
      authClazzRole,
    flyLayPage,
    tableAuthClazzRoleData,
      authClazzRoleColumns,
    queryAuthClazzRoleParams,

    /* 加载表格 */
    reloadAuthClazzRoleDate,
    loadTableAuthClazzRoleData,

    /* 新增, 编辑, 查看按钮 */
    onTapAuthClazzRoleAdd,
    onTapAuthClazzRoleEdit,
    onTapAuthClazzRoleView,

    /* 接口 */
    removeAuthClazzRoleHandler,
    authClazzRoleDataHandler
  } = useAuthClazzRoleComposable();


  /**
   * 上一页, 下一页
   * @param current
   * @param limit
   */
  const previousNextPage = ({current = 1, limit = 10}) => {
    queryAuthClazzRoleParams.page = current;
    queryAuthClazzRoleParams.limit = limit;
    loadTableAuthClazzRoleData(queryAuthClazzRoleParams);
  }


  /**
   * 页面初始化, 加载数据
   */
  onMounted(() => {
    reloadAuthClazzRoleDate(1, flyLayPage.limit);
  })



</script>


<style scoped>

</style>