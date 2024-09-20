<template>

  <lay-layer :title="layLayerForm.title"
             :area="layLayerForm.area"
             :maxmin="true"
             v-model="layLayerForm.open">
    <div style="padding: 20px; ">

      <lay-form :model="appInfo" ref="refFormAppInfo"
                :label-position="'right'"
                labelWidth="130px">

        <lay-form-item label="平台ID" prop="platId" required="true">
          <lay-input v-model="appInfo.platId"></lay-input>
        </lay-form-item>

        <lay-form-item label="平台名称" prop="platTitle" required="true">
          <lay-input v-model="appInfo.platTitle"></lay-input>
        </lay-form-item>

        <lay-form-item label="app秘钥" prop="appSecret">
          <lay-input v-model="appInfo.appSecret"></lay-input>
        </lay-form-item>

        <lay-form-item label="应用标识" prop="appCode">
          <lay-input v-model="appInfo.appCode"></lay-input>
        </lay-form-item>

        <lay-form-item label="应用名称" prop="appTitle">
          <lay-input v-model="appInfo.appTitle"></lay-input>
        </lay-form-item>

        <lay-form-item label="应用名称简称" prop="appAsTitle">
          <lay-input v-model="appInfo.appAsTitle"></lay-input>
        </lay-form-item>

        <lay-form-item label="应用类型" prop="appType">
          <lay-select v-model="appInfo.appType" :items="items4" :options="items4"></lay-select>
        </lay-form-item>

        <lay-form-item label="应用类型" prop="typeCode">
          <lay-checkbox-group v-model="appInfo.typeCode">
            <lay-checkbox name="typeCode" skin="primary" value="1">写作</lay-checkbox>
            <lay-checkbox name="typeCode" skin="primary" value="2">画画</lay-checkbox>
            <lay-checkbox name="typeCode" skin="primary" value="3">运动</lay-checkbox>
          </lay-checkbox-group>
        </lay-form-item>

        <lay-form-item label="应用类型标题" prop="typeTitle">
          <lay-input v-model="appInfo.typeTitle"></lay-input>
        </lay-form-item>

        <lay-form-item label="访问地址" prop="askUri">
          <lay-input v-model="appInfo.askUri"></lay-input>
        </lay-form-item>

        <lay-form-item label="图标" prop="iconIco">
          <lay-input v-model="appInfo.iconIco"></lay-input>
        </lay-form-item>

        <lay-form-item label="有效标志" prop="validFlag">
          <lay-input v-model="appInfo.validFlag"></lay-input>
        </lay-form-item>

        <lay-form-item label="备注" prop="remarks">
          <lay-textarea placeholder="请输入备注" v-model="appInfo.remarks"></lay-textarea>
        </lay-form-item>
      </lay-form>

    </div>


    <template v-slot:footer>
      <div class="fly-form-footer">
        <lay-button v-if="showToSubmit" class="fly-button fly-ok-button " @click="toSubmit">
          <lay-icon class="layui-icon-ok"></lay-icon>
          保存
        </lay-button>
        <lay-button class="fly-button fly-close-button" @click="onTapClose">
          <lay-icon class="layui-icon-close"></lay-icon>
          取消
        </lay-button>
      </div>
    </template>

  </lay-layer>

</template>


<script setup lang="ts" name="refAppInfo">
import {reactive, ref} from "vue";
import {FormTypeEnum} from "@/hooks/biz/BizModel";

import useAppInfoComposable from '@/composables/admin/AppInfoComposable';
import {AppInfo} from "@/api/admin/model/AppInfoModel";

let {
  appInfo,
  appInfoDataHandler
} = useAppInfoComposable();

const showToSubmit = ref<boolean>(true);

/* 弹窗  '1200px', '800px' */
const layLayerForm = reactive<any>({
  open: false,
  title: '',
  area: ['90%', '60%'],
  disabled: false
})

const refFormAppInfo = ref();

/**
 * 关闭按钮
 */
const onTapClose = () => {
  layLayerForm.open = false;
}


/**
 * 提交
 */
function toSubmit() {
  console.log('appInfo', appInfo);


  appInfoDataHandler(appInfo);
  onTapClose()
  emit('click', appInfo);

  // refFormAppInfo.value.validate((isValidate: boolean, model: AppInfo, errors: any[]) => {
  //   if (!isValidate) {
  //     return;
  //   }
  //   appInfoDataHandler(appInfo);
  //   layerRef.open = false;
  //   emit('click', appInfo);
  // })
}


//显示弹框
const openPopup = (type: FormTypeEnum, title: string, data?: AppInfo) => {
  // refFormAppInfo.value?.resetFields();
  showToSubmit.value = true;
  refFormAppInfo.value.reset();

  if (null !== data && undefined !== data) {
    appInfo = JSON.parse(JSON.stringify(data))
   // objCopy(data, appInfo);
  } else {
    appInfo = {};
  }

  if (FormTypeEnum.VIEW === type) {
    layLayerForm.disabled = true;
    showToSubmit.value = false;
  }

  layLayerForm.title = type + title;

 // console.log('layerRef', layLayerForm);
  layLayerForm.open = true;

};

//在组件中，属性和方法，只能对当前template,
//外部需要使用的时候，需要使用 defineExpose 暴露出去给父组件
defineExpose({
  openPopup,
});

//注册事件
const emit = defineEmits(['click']);


/**
 * 对象快速复制：var1 复制到 var2
 * @param var1
 * @param var2
 */
const objCopy = (var1: any, var2: any) => {
  if (null == var1) {
    var2 = {};
    return;
  }

  Object.keys(var1).forEach(key => {
    var2[key] = var1[key]
  })


}

/**
 * 重置表单
 */
const resetForm = (fromRef: any, obj: any) => {
  if (null === obj || undefined === obj) {
    return;
  }
  //清空数据
  Object.keys(obj).forEach(key => {
    obj[key] = ''
  })
  //清除表单的验证
  if (fromRef) {
    fromRef.resetFields();
    fromRef.clearValidate();
  }
}

/**************************************************************************************************************/

const items4 = ref([
  {label: '选项1', value: 1},
  {label: '选项2', value: 2},
  {label: '选项3', value: 3},
])

const visible11 = ref(true);


// const title = ref('新增');

const layFormRef11 = ref();


function toCancel() {
  visible11.value = false
}

// 清除校验
const clearValidate11 = function () {
  layFormRef11.value.clearValidate()
}
// 重置表单
const reset11 = function () {
  layFormRef11.value.reset()
}
</script>


<style scoped>

</style>