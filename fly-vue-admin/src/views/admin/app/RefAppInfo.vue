<template>


  <lay-layer v-model="open"
             :title="title"
             :area="['70%', '72%']"
             :shade-close="false"
             :maxmin="true">


    <lay-form :model="appInfo"
              ref="refFormAppInfo"
              isLabelTooltip
              :label-position="'right'"
              labelWidth="130px" style="margin: 20px">

      <lay-form-item label="应用名称" prop="appTitle" :required="required">
        <lay-input v-model="appInfo.appTitle" :disabled="disabledForm"></lay-input>
      </lay-form-item>

      <lay-form-item label="应用标识" prop="appCode" :required="required">
        <lay-input v-model="appInfo.appCode" :disabled="disabledForm"></lay-input>
      </lay-form-item>

      <lay-form-item label="app秘钥" prop="appSecret">
        <lay-input v-model="appInfo.appSecret" :disabled="disabledForm">
          <template v-if="appSecretSuffix" #suffix>
            <lay-button class="fly-button fly-ok-button"
                        style="height: 37px !important;margin-right: -10px !important;"
                        @click="onTapRefreshAppSecret">
              <lay-icon class="layui-icon-refresh"></lay-icon>
              重置
            </lay-button>
          </template>
        </lay-input>
      </lay-form-item>

      <lay-form-item label="简称" prop="appAsTitle">
        <lay-input v-model="appInfo.appAsTitle" :disabled="disabledForm"></lay-input>
      </lay-form-item>

      <lay-form-item label="前端后端" prop="appType" mode="inline">
        <lay-select v-model="appInfo.appType" :disabled="disabledForm">
          <lay-select-option value="BACK" label="后端"></lay-select-option>
          <lay-select-option value="LAY_VUE" label="前端(LAY_VUE)"></lay-select-option>
          <lay-select-option value="WE_CHAT_APPLET" label="微信小程序"></lay-select-option>
        </lay-select>
      </lay-form-item>

      <lay-form-item label="应用类型" prop="typeCode" mode="inline">
        <lay-select v-model="appInfo.typeCode" :disabled="disabledForm">
          <lay-select-option v-for="item in APP_CODE_ARR" :key="item.value" :value="item.value">
            {{ item.label }}
          </lay-select-option>

          <!--          <lay-select-option value="BS" label="业务系统"></lay-select-option>-->
          <!--          <lay-select-option value="OSS" label="文件上传"></lay-select-option>-->
          <!--          <lay-select-option value="UMS" label="统一系统管理"></lay-select-option>-->
          <!--          <lay-select-option value="ACTUATOR" label="监控"></lay-select-option>-->
          <!--          <lay-select-option value="GATEWAY" label="网关"></lay-select-option>-->
          <!--          <lay-select-option value="APPLET" label="小程序"></lay-select-option>-->
          <!--          <lay-select-option value="OTHER" label="其他"></lay-select-option>-->
        </lay-select>
      </lay-form-item>

      <lay-form-item label="有效标志" prop="validFlag" mode="inline">
        <lay-radio v-model="appInfo.validFlag" name="validFlag" value="Y" :disabled="disabledForm">有效</lay-radio>
        <lay-radio v-model="appInfo.validFlag" name="validFlag" value="N" :disabled="disabledForm">无效</lay-radio>
      </lay-form-item>

      <lay-form-item label="访问地址" prop="askUri">
        <lay-input v-model="appInfo.askUri" :disabled="disabledForm"></lay-input>
      </lay-form-item>

      <lay-form-item label="图标" prop="iconIco">
        <lay-input v-model="appInfo.iconIco" :disabled="disabledForm"></lay-input>
      </lay-form-item>

      <lay-form-item label="备注" prop="remarks">
        <lay-textarea placeholder="请输入备注" v-model="appInfo.remarks" :disabled="disabledForm"></lay-textarea>
      </lay-form-item>
    </lay-form>


    <!--  v-if="!showSubmit && !showClose" -->
    <template v-slot:footer>
      <div class="fly-form-footer">
        <lay-button v-if="showSubmit" class="fly-button fly-ok-button " @click="onTapSubmit">
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


<script setup lang="ts" name="refAppInfo">
import {reactive, ref} from "vue";

import useAppInfoComposable from '@/composables/admin/AppInfoComposable';
import {FormTypeEnum} from "@/fly/biz/BizModel";
import {AppInfo} from "@/api/admin/model/AppInfoModel";

let {
  appInfo,
  appInfoDataHandler
} = useAppInfoComposable();


const title = ref<string>('应用');
const open = ref<boolean>(false);
const refFormAppInfo = ref();
const showSubmit = ref<boolean>(true);
const appSecretSuffix = ref<boolean>(false);
const disabledForm = ref<boolean>(false);
const required = ref<boolean>(true);

//显示弹框
const openPopup = (type: FormTypeEnum, data?: AppInfo) => {
  if (null !== data && undefined !== data) {
    appInfo.value = JSON.parse(JSON.stringify(data));
  } else {
    appInfo.value = {
      appTitle: '',
      appCode: '',
      appSecret: '',
      appAsTitle: '',
      validFlag: 'Y',
      appType: 'BACK',
      typeCode: 'BS',
      askUri: '',
      iconIco: '',
      remarks: ''
    };
  }

  appSecretSuffix.value = false;
  showSubmit.value = true;
  disabledForm.value = false;
  required.value = true;
  if (FormTypeEnum.EDIT === type) {
    appSecretSuffix.value = true;
  }
  if (FormTypeEnum.VIEW === type) {
    showSubmit.value = false;
    disabledForm.value = true;
    required.value = false;
  }

  title.value = type + '应用信息';
  open.value = true;


};

// 在组件中，属性和方法，只能对当前template,
// 外部需要使用的时候，需要使用 defineExpose 暴露出去给父组件
defineExpose({
  openPopup,
});

/**********************************************************************/


/**********************************************************************************************/


/**
 * 关闭按钮
 */
const onTapClose = () => {
  open.value = false;
}


/**
 * 提交
 */
function onTapSubmit() {
  // appInfoDataHandler(appInfo);
  // popupProps.open = false;
  // emit('click', appInfo);


  refFormAppInfo.value.validate((isValidate: boolean, model: AppInfo, errors: any[]) => {
    console.log('isValidate', isValidate);
    console.log('model', model);
    if (!isValidate) {
      return;
    }

    appInfoDataHandler(model);
    open.value = false;
  });
}


//注册事件
const emit = defineEmits(['click']);

const onTapRefreshAppSecret = () => {
  console.log('onTapRefreshAppSecret')
}

/**
 * 应用类型
 */
const APP_CODE_ARR = [
  {
    value: 'BS',
    label: '业务系统'
  },
  {
    value: 'OSS',
    label: '文件上传'
  },
  {
    value: 'UMS',
    label: '统一系统管理'
  },
  {
    value: 'ACTUATOR',
    label: '监控'
  },
  {
    value: 'GATEWAY',
    label: '网关'
  },
  {
    value: 'APPLET',
    label: '小程序'
  },
  {
    value: 'OTHER',
    label: '其他'
  },
];


</script>


<style scoped>

</style>