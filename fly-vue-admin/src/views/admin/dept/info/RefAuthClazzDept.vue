<template>
  <lay-layer v-model="layerRef.open"
             :title="layerRef.title"
             :area="layerRef.area"
             :shade-close="layerRef.shadeClose"
             :maxmin="layerRef.maxmin">

    <div style="padding: 20px">
      <lay-form :model="authClazzDept" ref="layAuthClazzDeptForm">
        <lay-form-item label="部门代码" prop="deptCode">
          <lay-input v-model="authClazzDept.deptCode" :disabled="true"></lay-input>
        </lay-form-item>
        <lay-form-item label="部门名称">
          <lay-input v-model="authClazzDept.deptTitle" :disabled="true"></lay-input>
        </lay-form-item>

        <lay-form-item label="岗位代码" prop="clazzCode">
          <lay-input v-model="authClazzDept.clazzCode" :disabled="true"></lay-input>
        </lay-form-item>

        <lay-form-item label="岗位名称" prop="clazzTitle">
          <lay-input v-model="authClazzDept.clazzTitle" :disabled="true"></lay-input>
        </lay-form-item>

        <lay-form-item label="有效标志" prop="validFlag" mode="inline" :required="layerRef.required">
          <lay-radio v-model="authClazzDept.validFlag" name="validFlag" value="Y" label="有效"
                     :disabled="layerRef.disabled"></lay-radio>
          <lay-radio v-model="authClazzDept.validFlag" name="validFlag" value="N" label="无效"
                     :disabled="layerRef.disabled"></lay-radio>
        </lay-form-item>

        <lay-form-item label="备注" prop="remarks">
          <lay-textarea v-model="authClazzDept.remarks" :disabled="layerRef.disabled"></lay-textarea>
        </lay-form-item>

      </lay-form>

    </div>


    <template v-slot:footer>
      <div class="fly-form-footer">
        <lay-button v-if="layerRef.submit" class="fly-button fly-ok-button " @click="saveAuthClazzDeptHandler">
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


<script setup lang="ts" name="refAuthClazzDept">
import {reactive, ref} from "vue";
import {FormTypeEnum} from "@/fly/biz/BizModel";
import {AuthClazzDept} from "@/api/auth/model/AuthClazzDeptModel";
import useTenantInfoComposable from "@/composables/auth/AuthClazzDeptComposable";
import {layer} from "@layui/layui-vue";
import AuthClazzDeptApi from "@/api/auth/AuthClazzDeptApi";

const authClazzDeptApi = new AuthClazzDeptApi();

const {
  authClazzDept,
  refAuthClazzDeptComponent,
  authClazzDeptDataHandler
} = useTenantInfoComposable();

const layAuthClazzDeptForm = ref<any>();

/* 弹窗 */
const layerRef = reactive<any>({
  open: false,
  title: '部门岗位关联表',
  area: ['800px', '560px'], // 宽度,  高度
  disabled: false,
  shadeClose: false,
  maxmin: false,
  submit: true,
})


/**
 * 提交
 */
function saveAuthClazzDeptHandler() {
  layAuthClazzDeptForm.value.validate((isValidate: boolean, model: AuthClazzDept, errors: any[]) => {
    if (!isValidate) {
      return;
    }
    authClazzDeptApi.updateInfo(model, model.id).then((res) => {
      layer.msg(res.msg, {time: 1500, icon: 1}); // 操作成功提示
      onTapClose();  // 关闭弹窗
      emit('click')
    })
  });
}


//显示弹框
const open = (type: FormTypeEnum, row?: AuthClazzDept) => {
  layerRef.title = type + '部门岗位';
  if (null !== row) {
    authClazzDept.value = JSON.parse(JSON.stringify(row))
  } else {
    authClazzDept.value = {
      id: '',  // ID
      tenantId: '',  // 租户ID
      deptCode: '',  // 部门代码
      deptTitle: '',  // 部门名称
      deptLevel: '',  // 部门层级
      clazzCode: '',  // 岗位代码
      clazzTitle: '',  // 岗位名称
      validFlag: '',  // 有效标志: Y-是;N-否
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
 const emit = defineEmits(['click']);

</script>


<style scoped>

</style>