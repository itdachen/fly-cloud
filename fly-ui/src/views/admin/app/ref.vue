<template>

  <lay-layer :title="layerRef.title"
             :area="layerRef.area"
             :maxmin="true"
             v-model="layerRef.open"
             :btn="refAppInfoAction">
    <div style="padding: 20px; ">
      <lay-form :model="appInfo" ref="layAppInfoForm" labelWidth="120px">

        <lay-form-item label="平台ID" prop="platId" required="true">
          <lay-input v-model="appInfo.platId"></lay-input>
        </lay-form-item>

        <lay-form-item label="平台名称" prop="platTitle">
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
          <lay-input v-model="appInfo.remarks"></lay-input>
        </lay-form-item>
      </lay-form>

    </div>
    <!--    <div class="fly-form-footer">-->
    <!--      <lay-button v-if="showToSubmit" class="fly-button fly-ok-button " @click="toSubmit">-->
    <!--        <lay-icon class="layui-icon-ok"></lay-icon>-->
    <!--        保存-->
    <!--      </lay-button>-->
    <!--      <lay-button class="fly-button fly-close-button" @click="onTapClose">-->
    <!--        <lay-icon class="layui-icon-close"></lay-icon>-->
    <!--        取消-->
    <!--      </lay-button>-->
    <!--    </div>-->
  </lay-layer>

</template>


<script setup lang="ts" name="refAppInfo">
import {reactive, ref} from "vue";
import {FormTypeEnum} from "@/hooks/biz/BizModel";

import useAppInfoComposable from '@/composables/admin/AppInfoComposable';

let {
  appInfo,
  appInfoDataHandler
} = useAppInfoComposable();

const showToSubmit = ref<boolean>(true);

/* 弹窗  '1200px', '800px' */
const layerRef = reactive<any>({
  open: false,
  title: '',
  area: ['90%', '60%'],
  disabled: false
})

const layAppInfoForm = ref<any>();


/**
 * 操作按钮
 */
const refAppInfoAction = ref([
  {
    text: "确认",
    class: 'fly-button fly-ok-button',
    callback: () => {
      appInfoDataHandler(appInfo);
      layerRef.open = false;
      emit('click', appInfo);
    }
  },
  {
    text: "取消",
    class: 'fly-button fly-close-button',
    callback: () => {
      layerRef.open = false;
    }
  }
])


/**
 * 关闭按钮
 */
const onTapClose = () => {
  layerRef.open = false;
}


/**
 * 提交
 */
function toSubmit() {
  appInfoDataHandler(appInfo);
  layerRef.open = false;
  emit('click', appInfo)
}


//显示弹框
const open = (type: FormTypeEnum, title: string, row?: any) => {
  layAppInfoForm.value?.resetFields();

  /* 清空表单内容 */
  resetForm(layAppInfoForm.value, row);

  objCopy(row,appInfo )
  // if (null != row) {
  //   objCopy(appInfo,row )
  //
  // //  appInfo = JSON.parse(JSON.stringify(row))
  // } else {
  //   appInfo = {};
  // }

  if (FormTypeEnum.ADD === type || FormTypeEnum.EDIT === type) {
    showToSubmit.value = true;
  }

  if (FormTypeEnum.VIEW === type) {
    layerRef.disabled = true;
    showToSubmit.value = false;
  }

  layerRef.open = true;
  layerRef.title = type + title;
};

//在组件中，属性和方法，只能对当前template,
//外部需要使用的时候，需要使用 defineExpose 暴露出去给父组件
defineExpose({
  open,
});

//注册事件
const emit = defineEmits(['click']);


/**
 * 对象快速复制：var1 复制到 var2
 * @param var1
 * @param var2
 */
const objCopy = (var1: any, var2: any) => {
  debugger
  if (null == var1) {
    var2 = null;
    return;
  }
  Object.keys(var2).forEach(key => {
    var2[key] = var1[key]
  })
}

/**
 * 重置表单
 */
const resetForm = (fromRef: any, obj: any) => {
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