<template>
  <lay-layer v-model="layerRef.open"
             :title="layerRef.title"
             :area="layerRef.area"
             :shade-close="layerRef.shadeClose"
             :maxmin="layerRef.maxmin">

    <div style="padding: 0 20px">

      <lay-container fluid="true" class="fly-container" style="height: 100% !important;">
        <lay-card class="fly-search-card" style="padding-top: 0!important;">
          <lay-form>
            <lay-form-item label="岗位名称" prop="clazzTitle" mode="inline">
              <lay-input v-model="queryAuthClazzDeptParams.clazzTitle"></lay-input>
            </lay-form-item>
            <lay-form-item label="有效标志" prop="validFlag" mode="inline">
              <lay-select v-model="queryAuthClazzDeptParams.validFlag">
                <lay-select-option value="" label="全部"></lay-select-option>
                <lay-select-option value="Y" label="有效"></lay-select-option>
                <lay-select-option value="N" label="无效"></lay-select-option>
              </lay-select>
            </lay-form-item>
            <lay-form-item mode="inline">
              <lay-button class="fly-button fly-button-search" style="margin-left: 20px" type="primary" size="sm"
                          @click=" reloadAuthClazzDeptDate(1, flyLayPage.limit) ">
                <lay-icon class="layui-icon-search"></lay-icon>
                查询
              </lay-button>
            </lay-form-item>
          </lay-form>

        </lay-card>

        <div class="table-box">
          <lay-table :page="false"
                     :columns=" authClazzDeptColumns "
                     :loading=" tableAuthClazzDeptData.loading "
                     :data-source=" tableAuthClazzDeptData.rows "
                     @change=" reloadAuthClazzDeptDate ">

            <template v-slot:toolbar>
              <lay-button size="sm" type="primary" class="fly-button fly-toolbar-addition"
                          @click=" onTapAuthAdd ">
                <lay-icon class="layui-icon-addition"></lay-icon>
                新增
              </lay-button>
            </template>

            <template v-slot:operator="{ row }">
              <lay-button size="sm" type="primary" class="fly-button fly-edit-button"
                          @click=" onTapEditAuthClazzDept(row) ">
                <lay-icon class="layui-icon-edit"></lay-icon>
                编辑
              </lay-button>

              <lay-button size="sm" type="danger" class="fly-button fly-remove-button"
                          @click=" removeAuthClazzDeptHandler(row.id) ">
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
                    :total=" tableAuthClazzDeptData.total"
                    @change="previousNextPage" style="margin-top: 15px"></lay-page>

        </div>
      </lay-container>
    </div>

    <RefAuthClazzInfo ref="refAuthClazzDeptComponent" @click="reloadAuthClazzDept"/>


    <RefAuthClazzDept ref="refAuthClazzDeptEditComponent" @click="reloadAuthClazzDept"></RefAuthClazzDept>

    <!--    <template v-slot:footer>-->
    <!--      <div class="fly-form-footer">-->
    <!--        <lay-button class="fly-button fly-close-button" @click="onTapClose">-->
    <!--          <lay-icon class="layui-icon-close"></lay-icon>-->
    <!--          关闭-->
    <!--        </lay-button>-->
    <!--      </div>-->
    <!--    </template>-->


  </lay-layer>
</template>


<script setup lang="ts" name="refDeptClazz">
import {onMounted, reactive, ref} from "vue";
import {DeptInfo} from "@/api/admin/model/DeptInfoModel";
import RefAuthClazzInfo from "./RefAuthClazzInfo.vue";
import RefAuthClazzDept from "./RefAuthClazzDept.vue";


import useAuthClazzDeptComposable from '@/composables/auth/AuthClazzDeptComposable';
import {FormTypeEnum} from "@/fly/biz/BizModel";

const {
  refAuthClazzDeptComponent,
  authClazzDept,
  flyLayPage,
  tableAuthClazzDeptData,
  authClazzDeptColumns,
  queryAuthClazzDeptParams,

  /* 加载表格 */
  reloadAuthClazzDeptDate,
  loadTableAuthClazzDeptData,

  /* 新增, 编辑, 查看按钮 */
  onTapAuthClazzDeptAdd,

  /* 接口 */
  removeAuthClazzDeptHandler,
} = useAuthClazzDeptComposable();

const deptInf = ref<DeptInfo>({});

/**
 * 上一页, 下一页
 * @param current
 * @param limit
 */
const previousNextPage = ({current = 1, limit = 10}) => {
  queryAuthClazzDeptParams.page = current;
  queryAuthClazzDeptParams.limit = limit;
  loadTableAuthClazzDeptData(queryAuthClazzDeptParams);
}


/**
 * 页面初始化, 加载数据
 */
onMounted(() => {
  reloadAuthClazzDeptDate(1, flyLayPage.limit);
})


/* 弹窗 */
const layerRef = reactive<any>({
  open: false,
  title: '岗位设置',
  area: ['98%', '98%'], // 高度, 宽度
  disabled: false,
  shadeClose: false,
  maxmin: false,
  submit: true,
})

//显示弹框
const open = (row: DeptInfo) => {
  layerRef.title = '岗位设置';
  deptInf.value = row;
  layerRef.open = true;
};

const onTapAuthAdd = () => {
  onTapAuthClazzDeptAdd(deptInf.value);
}


/**
 * 关闭按钮
 */
const onTapClose = () => {
  layerRef.open = false;
}

const refAuthClazzDeptEditComponent = ref();

/**
 * 编辑
 * @param row
 */
const onTapEditAuthClazzDept = (row: any) => {
  refAuthClazzDeptEditComponent.value?.open(FormTypeEnum.EDIT, row);
}

//在组件中，属性和方法，只能对当前template,
//外部需要使用的时候，需要使用 defineExpose 暴露出去给父组件
defineExpose({
  open,
  onTapClose
});

const reloadAuthClazzDept = () => {
  reloadAuthClazzDeptDate(1, flyLayPage.limit);
}

</script>


<style scoped>
.layui-select {
  width: 100% !important;
}
</style>