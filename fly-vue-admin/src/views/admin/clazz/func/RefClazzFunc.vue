<template>
  <lay-layer v-model="layerRef.open"
             :title="layerRef.title"
             :area="layerRef.area"
             :shade-close="layerRef.shadeClose"
             :maxmin="layerRef.maxmin">

    <div style="padding: 20px">
      <lay-form :model="clazzFunc" ref="layClazzFuncForm">
        <lay-form-item label="岗位职能编码 " prop="funcCode" :required="layerRef.required">
          <lay-input v-model="clazzFunc.funcCode" :disabled="layerRef.disabled"></lay-input>
        </lay-form-item>

        <lay-form-item label="岗位职能标题" prop="funcTitle" :required="layerRef.required">
          <lay-input v-model="clazzFunc.funcTitle" :disabled="layerRef.disabled"></lay-input>
        </lay-form-item>

        <lay-form-item label="有效标志" prop="validFlag" :required="layerRef.required">
          <lay-radio v-model="clazzFunc.validFlag" name="action" value="Y" label="有效"
                     :disabled="layerRef.disabled"></lay-radio>
          <lay-radio v-model="clazzFunc.validFlag" name="action" value="N" label="无效"
                     :disabled="layerRef.disabled"></lay-radio>
        </lay-form-item>

        <lay-form-item label="备注" prop="remarks">
          <lay-textarea placeholder="请输入备注" v-model="clazzFunc.remarks"
                        :disabled="layerRef.disabled"></lay-textarea>
        </lay-form-item>

      </lay-form>

    </div>


    <template v-slot:footer>
      <div class="fly-form-footer">
        <lay-button v-if="layerRef.submit" class="fly-button fly-ok-button " @click="saveClazzFuncHandler">
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


<script setup lang="ts" name="refClazzFunc">
import {reactive, ref} from "vue";
import {FormTypeEnum} from "@/fly/biz/BizModel";
import {ClazzFunc} from "@/api/admin/model/ClazzFuncModel";
import useTenantInfoComposable from "@/composables/admin/ClazzFuncComposable";

const {
  clazzFunc,
  clazzFuncDataHandler
} = useTenantInfoComposable();

const layClazzFuncForm = ref<any>();

/* 弹窗 */
const layerRef = reactive<any>({
  open: false,
  title: '岗位职能',
  area: ['600px', '450px'], // 宽度,  高度
  disabled: false,
  shadeClose: false,
  maxmin: false,
  submit: true,
})


/**
 * 提交
 */
function saveClazzFuncHandler() {
  layClazzFuncForm.value.validate((isValidate: boolean, model: ClazzFunc, errors: any[]) => {
    if (!isValidate) {
      return;
    }
    clazzFuncDataHandler(model);
  });
}


//显示弹框
const open = (type: FormTypeEnum, row?: ClazzFunc) => {
  layerRef.title = type + '岗位职能';
  if (null !== row) {
    clazzFunc.value = JSON.parse(JSON.stringify(row))
  } else {
    clazzFunc.value = {
      id: '',  // ID/类型代码: 例如:100,200,300
      funcCode: '',  // 岗位职能编码, 例如: 01-主管领导岗;02-业务岗
      funcTitle: '',  // 岗位职能标题, 例如: 01-主管领导岗;02-业务岗
      validFlag: 'Y',  // 有效标志: Y-有效;N-无效
      remarks: '',  // 备注
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