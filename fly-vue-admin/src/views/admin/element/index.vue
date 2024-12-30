<template>

  <lay-container fluid="true" class="fly-container">
    <lay-card class="fly-search-card">
      <lay-form>
            <lay-form-item label="应用ID/appID" prop="appId" mode="inline">
              <lay-input v-model="queryElementInfoParams.appId"></lay-input>
            </lay-form-item>
            <lay-form-item label="菜单ID" prop="menuId" mode="inline">
              <lay-input v-model="queryElementInfoParams.menuId"></lay-input>
            </lay-form-item>
            <lay-form-item label="标题" prop="title" mode="inline">
              <lay-input v-model="queryElementInfoParams.title"></lay-input>
            </lay-form-item>
            <lay-form-item label="类型: button,uri" prop="type" mode="inline">
              <lay-input v-model="queryElementInfoParams.type"></lay-input>
            </lay-form-item>
            <lay-form-item label="是否展示/有效标志: Y-有效;N-无效" prop="validFlag" mode="inline">
              <lay-input v-model="queryElementInfoParams.validFlag"></lay-input>
            </lay-form-item>

        <lay-form-item mode="inline">
          <lay-button class="fly-button fly-button-search" style="margin-left: 20px" type="primary" size="sm"
                      @click=" reloadElementInfoDate(1, flyLayPage.limit) ">
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
                 :columns=" elementInfoColumns "
                 :loading=" tableElementInfoData.loading "
                 :data-source=" tableElementInfoData.rows "
                 @change=" reloadElementInfoDate ">

        <template v-slot:toolbar>
          <lay-button size="sm" type="primary" class="fly-button fly-toolbar-addition" @click=" onTapElementInfoAdd ">
            <lay-icon class="layui-icon-addition"></lay-icon>
            新增
          </lay-button>
        </template>

        <template v-slot:operator="{ row }">
          <lay-button size="sm" type="primary" class="fly-button fly-view-button" @click=" onTapElementInfoView(row) ">
            <lay-icon class="layui-icon-list"></lay-icon>
            查看
          </lay-button>
          <lay-button size="sm" type="primary" class="fly-button fly-edit-button" @click=" onTapElementInfoEdit(row) ">
            <lay-icon class="layui-icon-edit"></lay-icon>
            编辑
          </lay-button>

          <lay-button size="sm" type="danger" class="fly-button fly-remove-button" @click=" removeElementInfoHandler(row.id) ">
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
  </lay-container>


  <RefElementInfo ref="refElementInfoComponent"/>

</template>


<script setup lang="ts" name="elementInfoComponent">
  import {ref, reactive, onMounted} from 'vue';
  import RefElementInfo from './RefElementInfo.vue';

  import useElementInfoComposable from '@/composables/admin/ElementInfoComposable';

  const {
    refElementInfoComponent,
      elementInfo,
    flyLayPage,
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


  /**
   * 上一页, 下一页
   * @param current
   * @param limit
   */
  const previousNextPage = ({current = 1, limit = 10}) => {
    queryElementInfoParams.page = current;
    queryElementInfoParams.limit = limit;
    loadTableElementInfoData(queryElementInfoParams);
  }


  /**
   * 页面初始化, 加载数据
   */
  onMounted(() => {
    reloadElementInfoDate(1, flyLayPage.limit);
  })



</script>


<style scoped>

</style>