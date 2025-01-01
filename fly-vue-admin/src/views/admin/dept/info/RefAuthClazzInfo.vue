<template>
  <lay-layer v-model="layerRef.open"
             :title="layerRef.title"
             :area="layerRef.area"
             :shade-close="layerRef.shadeClose"
             :maxmin="layerRef.maxmin">

    <div style="padding: 20px">

      <lay-container fluid="true" class="fly-container">
        <lay-card class="fly-search-card">
          <lay-form>

            <lay-form-item label="岗位名称" prop="clazzTitle" mode="inline">
              <lay-input v-model="queryClazzInfoParams.clazzTitle"></lay-input>
            </lay-form-item>

            <lay-form-item mode="inline">
              <lay-button class="fly-button fly-button-search" style="margin-left: 20px" type="primary" size="sm"
                          @click=" reloadClazzInfoDate(1, flyLayPage.limit) ">
                <lay-icon class="layui-icon-search"></lay-icon>
                查询
              </lay-button>
            </lay-form-item>
          </lay-form>
        </lay-card>

        <div class="table-box">

          <lay-table :page="false"
                     :columns=" clazzInfoColumns "
                     :loading=" tableClazzInfoData.loading "
                     :data-source=" tableClazzInfoData.rows "
                     v-model:selected-keys="selectedKeys"
                     @change=" reloadClazzInfoDate ">
            <template v-slot:toolbar>
              <lay-button size="sm" type="primary" class="fly-button fly-toolbar-addition"
                          style="width: 120px !important;" @click=" onTapClazzInfoBatchAdd ">
                <lay-icon class="layui-icon-addition"></lay-icon>
                批量添加
              </lay-button>
            </template>

            <template v-slot:operator="{ row }">
              <lay-button size="sm" type="primary" class="fly-button fly-edit-button"
                          @click=" onTapClazzInfo(row) ">
                <lay-icon class="layui-icon-addition"></lay-icon>
                添加
              </lay-button>
            </template>
          </lay-table>


          <lay-page v-model="flyLayPage.page"
                    v-model:limit="flyLayPage.limit"
                    :layout="flyLayPage.layout"
                    :pages="flyLayPage.pages"
                    :limits="flyLayPage.limits"
                    theme="blue"
                    :total=" tableClazzInfoData.total"
                    @change="previousNextPage" style="margin-top: 15px"></lay-page>

        </div>
      </lay-container>
    </div>


  </lay-layer>
</template>


<script setup lang="ts" name="refDeptClazz">
import {reactive, ref} from "vue";
import useClazzInfoComposable from '@/composables/admin/ClazzInfoComposable';
import {DeptInfo} from "@/api/admin/model/DeptInfoModel";
import AuthClazzDeptApi from "@/api/auth/AuthClazzDeptApi";
import {layer} from "@layui/layui-vue";

const authClazzDeptApi = new AuthClazzDeptApi()

const {
  flyLayPage,
  tableClazzInfoData,
  queryClazzInfoParams,

  /* 加载表格 */
  reloadClazzInfoDate,
  loadTableClazzInfoData,
} = useClazzInfoComposable();

/* 批量选择的id值 */
const selectedKeys = ref([]);

const deptInfoModel = ref<DeptInfo>()

/**
 * 分页列表展示项
 */
const clazzInfoColumns = [
  {title: "选项", width: "55px", type: "checkbox", fixed: "left"},
  {title: '岗位代码', key: 'clazzCode', ellipsisTooltip: true, align: 'center'},
  {title: '岗位名称', key: 'clazzTitle', ellipsisTooltip: true, align: 'center'},
  {title: '操作', width: '200px', customSlot: 'operator', key: 'operator', align: 'center', fixed: 'right'}
];

/**
 * 单个添加
 * @param row
 */
const onTapClazzInfo = (row: any) => {
  saveDataHandler(row.id);
}

/**
 * 批量添加
 */
const onTapClazzInfoBatchAdd = () => {
  saveDataHandler(selectedKeys.value.join(','));
}


/**
 * 数据入库
 * @param selectedKeys 选中的数据
 */
const saveDataHandler = (keys: string) => {
  deptInfoModel.value

  let authClazzDept = {
    id: '',  // ID
    ids: keys,
    deptCode: deptInfoModel.value?.deptCode,  // 部门代码
    deptTitle: deptInfoModel.value?.title,  // 部门名称
    deptLevel: deptInfoModel.value?.levelCode,  // 部门层级
    validFlag: 'Y',  // 有效标志: Y-是;N-否
  }

  authClazzDeptApi.saveInfo(authClazzDept).then(res => {
    layer.msg(res.msg, {time: 1500, icon: 1});
    onTapClose();
    emit('click')
  })
  //
  //    /** 部门代码 */
  //    @Column(name = "dept_code")
  //    private String deptCode;
  //
  //    /** 部门名称 */
  //    @Column(name = "dept_title")
  //    private String deptTitle;
  //
  //    /** 部门层级 */
  //    @Column(name = "dept_level")
  //    private String deptLevel;
  //

}


/**
 * 上一页, 下一页
 * @param current
 * @param limit
 */
const previousNextPage = ({current = 1, limit = 10}) => {
  queryClazzInfoParams.page = current;
  queryClazzInfoParams.limit = limit;
  loadTableClazzInfoData(queryClazzInfoParams);
}

/* 弹窗 */
const layerRef = reactive<any>({
  open: false,
  title: '岗位设置',
  area: ['80%', '80%'], // 高度, 宽度
  disabled: false,
  shadeClose: false,
  maxmin: false,
  submit: true,
})

//显示弹框
const open = (deptInfo: DeptInfo) => {
  deptInfoModel.value = deptInfo;
  layerRef.title = '岗位信息';
  queryClazzInfoParams.deptLevelCode = deptInfo.levelCode;
  queryClazzInfoParams.deptFuncCode = deptInfo.funcCode;
  queryClazzInfoParams.validFlag = 'Y';
  loadTableClazzInfoData(queryClazzInfoParams);
  layerRef.open = true;
};


/**
 * 关闭按钮
 */
const onTapClose = () => {
  layerRef.open = false;
}


//在组件中，属性和方法，只能对当前template,
//外部需要使用的时候，需要使用 defineExpose 暴露出去给父组件
defineExpose({
  open,
  onTapClose
});

const emit = defineEmits(['click']);
</script>


<style scoped>
.layui-select {
  width: 100% !important;
}
</style>