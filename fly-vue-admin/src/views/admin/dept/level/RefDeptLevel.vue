<template>
  <lay-layer v-model="layerRef.open"
             :title="layerRef.title"
             :area="layerRef.area"
             :shade-close="layerRef.shadeClose"
             :maxmin="layerRef.maxmin">

    <div style="padding: 20px">
      <lay-form :model="deptLevel" ref="layDeptLevelForm">
        <lay-form-item label="层级代码" prop="levelCode" :required="layerRef.required">
          <lay-input placeholder="请输入层级代码" v-model="deptLevel.levelCode"
                     :disabled="layerRef.disabled"></lay-input>
        </lay-form-item>

        <lay-form-item label="层级名称" prop="levelTitle" :required="layerRef.required">
          <lay-input
              placeholder="请输入层级名称"
              v-model="deptLevel.levelTitle" :disabled="layerRef.disabled"></lay-input>
        </lay-form-item>

        <lay-form-item label="有效标志" prop="validFlag" :required="layerRef.required">
          <lay-radio v-model="deptLevel.validFlag" name="action" value="Y" label="有效"
                     :disabled="layerRef.disabled"></lay-radio>
          <lay-radio v-model="deptLevel.validFlag" name="action" value="N" label="无效"
                     :disabled="layerRef.disabled"></lay-radio>
        </lay-form-item>

        <lay-form-item label="备注" prop="remarks">
          <lay-textarea placeholder="请输入备注" v-model="deptLevel.remarks"
                        :disabled="layerRef.disabled"></lay-textarea>
        </lay-form-item>

      </lay-form>

    </div>


    <template v-slot:footer>
      <div class="fly-form-footer">
        <lay-button v-if="layerRef.submit" class="fly-button fly-ok-button " @click="saveDeptLevelHandler">
          <lay-icon class="layui-icon-ok"></lay-icon>
          保存
        </lay-button>
        <lay-button class="fly-button fly-close-button" @click="onTapClose">
          <lay-icon class="layui-icon-close"></lay-icon>
          关闭
        </lay-button>
      </div>
    </template>


  </lay-layer>
</template>


<script setup lang="ts" name="refDeptLevel">
import {reactive, ref} from "vue";
import {FormTypeEnum} from "@/fly/biz/BizModel";
import {DeptLevel} from "@/api/admin/model/DeptLevelModel";
import useTenantInfoComposable from "@/composables/admin/DeptLevelComposable";

const {
  deptLevel,
  deptLevelDataHandler
} = useTenantInfoComposable();

const layDeptLevelForm = ref<any>();

/* 弹窗 */
const layerRef = reactive<any>({
  open: false,
  title: '部门等级管理',
  area: ['500px', '440px'], // 高度, 宽度
  disabled: false,
  shadeClose: false,
  maxmin: false,
  submit: true,
})


/**
 * 提交
 */
function saveDeptLevelHandler() {
  layDeptLevelForm.value.validate((isValidate: boolean, model: DeptLevel, errors: any[]) => {
    if (!isValidate) {
      return;
    }
    deptLevelDataHandler(model);
    // onTapClose();
    // console.log('数据保存', deptLevel.value);
    //  emit('click', deptLevel)
  });
}


//显示弹框
const open = (type: FormTypeEnum, row?: DeptLevel) => {
  layerRef.title = type + '部门等级管理';
  if (null !== row) {
    deptLevel.value = JSON.parse(JSON.stringify(row))
  } else {
    deptLevel.value = {
      id: '',  // 主键唯一标识
      levelCode: '',  // 层级代码, 例如:00,11,21,31,41,51
      levelTitle: '',  // 层级名称,例如: 00-总部; 11-省级部门; 21-市州级部门; 31-区县级;41-乡镇级;51-社区/街道
      validFlag: 'Y',  // 有效标志: Y-是;N-否
      remarks: ''  // 备注
    }
  }

  layerRef.disabled = false;
  layerRef.submit = true;
  layerRef.required = true;
  if (FormTypeEnum.VIEW === type) {
    layerRef.disabled = true;
    layerRef.submit = false;
    layerRef.required = false;
  }

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

//注册事件
// const emit = defineEmits(['click']);

</script>


<style scoped>

</style>