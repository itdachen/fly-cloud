<template>
  <lay-layer v-model="layerRef.open"
             :title="layerRef.title"
             :area="layerRef.area"
             :shade-close="layerRef.shadeClose"
             :maxmin="layerRef.maxmin">

    <div style="padding: 20px">
      <lay-form :model="deptFunc" ref="layDeptFuncForm">
        <lay-form-item label="职能编码" prop="funcCode" :required="layerRef.required">
          <lay-input placeholder="请输入职能编码，例如：10" v-model="deptFunc.funcCode"
                     maxlength="2" :disabled="layerRef.disabled"></lay-input>
        </lay-form-item>

        <lay-form-item label="职能名称" prop="funcTitle" :required="layerRef.required">
          <lay-input placeholder="请输入职能名称" v-model="deptFunc.funcTitle"
                     maxlength="30" :disabled="layerRef.disabled"></lay-input>
        </lay-form-item>

        <lay-form-item label="有效标志" prop="validFlag" :required="layerRef.required">
          <lay-radio v-model="deptFunc.validFlag" name="validFlag" value="Y" label="有效"
                     :disabled="layerRef.disabled"></lay-radio>
          <lay-radio v-model="deptFunc.validFlag" name="validFlag" value="N" label="无效"
                     :disabled="layerRef.disabled"></lay-radio>
        </lay-form-item>

        <lay-form-item label="备注" prop="remarks">
          <lay-textarea placeholder="请输入备注" v-model="deptFunc.remarks"
                        :disabled="layerRef.disabled"></lay-textarea>
        </lay-form-item>

      </lay-form>

    </div>


    <template v-slot:footer>
      <div class="fly-form-footer">
        <lay-button v-if="layerRef.submit" class="fly-button fly-ok-button " @click="toSubmit">
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


<script setup lang="ts" name="refDeptFunc">
import {reactive, ref} from "vue";
import {FormTypeEnum} from "@/fly/biz/BizModel";
import {DeptFunc} from "@/api/admin/model/DeptFuncModel";
import useTenantInfoComposable from "@/composables/admin/DeptFuncComposable";

const {
  deptFunc,
  deptFuncDataHandler
} = useTenantInfoComposable();

const layDeptFuncForm = ref<any>();

/* 弹窗 */
const layerRef = reactive<any>({
  open: false,
  title: '',
  area: ['550px', '440px'],
  disabled: false,
  shadeClose: false,
  maxmin: false,
  submit: true
})


/**
 * 提交
 */
function toSubmit() {
//  console.log('数据保存', deptFunc.value);
  layDeptFuncForm.value.validate((isValidate: boolean, model: DeptFunc, errors: any[]) => {
    if (!isValidate) {
      return;
    }
    deptFuncDataHandler(model);
    onTapClose();
    emit('click', deptFunc)

  });
}


//显示弹框
const open = (type: FormTypeEnum, row?: DeptFunc) => {
  layerRef.title = type + '部门职能';
  if (null !== row) {
    deptFunc.value = JSON.parse(JSON.stringify(row))
  } else {
    deptFunc.value = {
      id: '',  // ID/类型代码: 例如:100,200,300
      tenantId: '',  // 租户ID
      funcCode: '',  // 编码(全局唯一)
      funcTitle: '',  // 标题, 例如: 100-领导办公室;200-人事部;300-研发部
      validFlag: 'Y',  // 有效标志: Y-有效;N-无效
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
});

//注册事件
const emit = defineEmits(['click']);

</script>


<style scoped>

</style>