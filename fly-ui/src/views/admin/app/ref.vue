<template>
  <lay-layer :title="layerRef.title" :area="layerRef.area" v-model="layerRef.open">
    <div style="padding: 20px">
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

        <lay-form-item label="职能代码" prop="funcCode">
          <lay-input v-model="appInfo.funcCode"></lay-input>
        </lay-form-item>

        <lay-form-item label="职能名称" prop="funcTitle">
          <lay-input v-model="appInfo.funcTitle"></lay-input>
        </lay-form-item>

        <lay-form-item label="是否可删除" prop="validDel">
          <lay-input v-model="appInfo.validDel"></lay-input>
        </lay-form-item>

        <lay-form-item label="有效标志" prop="validFlag">
          <lay-input v-model="appInfo.validFlag"></lay-input>
        </lay-form-item>

        <lay-form-item label="备注" prop="remarks">
          <lay-input v-model="appInfo.remarks"></lay-input>
        </lay-form-item>
      </lay-form>

    </div>
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
  </lay-layer>

</template>


<script setup lang="ts">
import {reactive, ref} from "vue";
import {layer} from "@layui/layui-vue";
import {FormTypeEnum} from "@/hooks/biz/BizModel";

const showToSubmit = ref<boolean>(true);

/* 弹窗 */
const layerRef = reactive<any>({
  open: false,
  title: '',
  area: ['1200px', '800px'],
  disabled: false
})

const layAppInfoForm = ref<any>();

const appInfo = ref<any>({});

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
  console.log('数据保存', appInfo.value);
  emit('click', appInfo)
}


//显示弹框
const open = (type: FormTypeEnum, title: string, row?: any) => {
  console.log('type: ', type);
  console.log('row: ', row);

  layAppInfoForm.value?.resetFields();

  if (null != row) {
    appInfo.value = JSON.parse(JSON.stringify(row))
  }

  if (FormTypeEnum.ADD === type || FormTypeEnum.EDIT === type){
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

/**************************************************************************************************************/

const items4 = ref([
  {label: '选项1', value: 1},
  {label: '选项2', value: 2},
  {label: '选项3', value: 3, disabled: true},
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