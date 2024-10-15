<template>
  <div>
    <dialog-popup :title="dialog.title"
                  :visible="dialog.visible"
                  :width="dialog.width"
                  :showSubmit="dialog.showSubmit"
                  @close="onClose()"
                  @submit="confirm()">

      <template v-slot:content>
        <el-form :model="appInfo" :rules="rules" :disabled="isDisabled"
                 ref="formRef" size="default" label-width="120px">
          <!--          <el-form-item label="平台ID" prop="platId" class="mb10">-->
          <!--            <el-input v-model="appInfo.platId" placeholder="请输入平台ID"/>-->
          <!--          </el-form-item>-->
          <!--          <el-form-item label="平台名称" prop="platTitle" class="mb10">-->
          <!--            <el-input v-model="appInfo.platTitle" placeholder="请输入平台名称"/>-->
          <!--          </el-form-item>-->

          <el-form-item v-if="showAppId" label="app id" prop="id" class="mb10">
            <el-input v-model="appInfo.id" readonly="readonly"/>
          </el-form-item>
          <el-form-item v-if="showAppId" label="app secret" prop="appSecret" class="mb10">
            <el-input v-model="appInfo.appSecret" readonly="readonly"/>
          </el-form-item>

          <el-form-item label="应用名称" prop="appTitle" class="mb10">
            <el-input v-model="appInfo.appTitle" placeholder="请输入应用名称"/>
          </el-form-item>
          <el-form-item label="应用标识" prop="appCode" class="mb10">
            <el-input v-model="appInfo.appCode" placeholder="请输入应用标识"/>
          </el-form-item>
          <el-form-item label="应用名称简称" prop="appAsTitle" class="mb10">
            <el-input v-model="appInfo.appAsTitle"/>
          </el-form-item>
          <el-form-item label="前端或后端" prop="appType" class="mb10">
            <el-radio-group v-model="appInfo.appType">
              <el-radio value="BACK">后端</el-radio>
              <el-radio value="VIEW">前端</el-radio>
            </el-radio-group>
          </el-form-item>

          <el-form-item label="应用类型" class="mb10">
            <el-select v-model="appInfo.typeCode" placeholder="请选择" style="width: 240px">
              <el-option v-for="item in appCodeOptions"
                         :key="item.value"
                         :label="item.label"
                         :value="item.value"/>
            </el-select>
          </el-form-item>

          <el-form-item label="访问地址" prop="askUri" class="mb10">
            <el-input v-model="appInfo.askUri">
<!--              <el-select v-model="select" placeholder="Select" style="width: 115px">-->
<!--                <el-option label="Restaurant" value="1" />-->
<!--                <el-option label="Order No." value="2" />-->
<!--                <el-option label="Tel" value="3" />-->
<!--              </el-select>-->
            </el-input>
          </el-form-item>
          <el-form-item label="图标" prop="iconIco" class="mb10">
            <el-input v-model="appInfo.iconIco"/>
          </el-form-item>
          <el-form-item label="有效标志" prop="validFlag" class="mb10">
            <el-radio-group v-model="appInfo.validFlag">
              <el-radio v-for="dict in validFlag"
                        :key="dict.value"
                        :label="dict.value">
                {{ dict.label }}
              </el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="备注" prop="remarks" class="mb10">
            <el-input v-model="appInfo.remarks" type="textarea" maxlength="100"/>
          </el-form-item>

        </el-form>
      </template>
    </dialog-popup>
  </div>
</template>

<script setup lang="ts" name="RefAppInfo">
import {reactive, ref} from 'vue';
import DialogPopup from '/@/components/dialog/DialogPopup.vue';
import useDialogPopup from '/@/components/dialog/DialogPopup';
import useElementFromComposable from '/@/components/form/ElementFromComposable';
import useAppInfoBuilder, {AppInfo} from '/@/api/admin/models/AppInfoModel';
import {DialogTypeEnum} from "/@/components/dialog/DialogModel";
import {ElForm} from "element-plus/es";
import useDictDataComposable from "/@/hooks/dict/useDictData";

const {validFlag} = useDictDataComposable();


//表单的ref属性
const formRef = ref<InstanceType<typeof ElForm>>();
/**
 * 弹框属性
 */
const {dialog, onShow, onClose} = useDialogPopup();

const {appInfo} = useAppInfoBuilder();

const isDisabled = ref<boolean>(false);

/**
 * 表单属性
 */
const {
  resetForm,
  objCopy
} = useElementFromComposable();

const showAppId = ref(true);


/**
 * 弹框
 * @param type 弹窗类型
 * @param data 展示的数据
 */
const show = (type: DialogTypeEnum, data?: AppInfo) => {
  //设置弹框的属性
  dialog.width = 800
  dialog.title = type + '应用信息';
  /* 清空表单内容 */
  resetForm(formRef.value, appInfo);
  if (undefined !== data && null !== data) {
    /* 新表单赋值 */
    objCopy(data, appInfo);
    showAppId.value = true;
  } else {
    objCopy({}, appInfo);
    showAppId.value = false;
    appInfo.validFlag = 'Y'
    appInfo.appType = 'BACK';
    appInfo.typeCode = 'BS';
  }
  if (DialogTypeEnum.ADD === type || DialogTypeEnum.EDIT === type) {
    dialog.showSubmit = true
    isDisabled.value = false
  }
  if (DialogTypeEnum.VIEW === type) {
    dialog.showSubmit = false
    isDisabled.value = true
  }

  onShow();
}

/**
 * 表单验证
 */
const rules = reactive({
  appCode: [{required: true, message: '应用标识不能为空', trigger: 'blur'}],
  appTitle: [{required: true, message: '应用名称不能为空', trigger: 'blur'}]
});

//定义事件
const emits = defineEmits(['bindtap'])

const confirm = () => {
  formRef.value?.validate(valid => {
    if (valid) {
      emits('bindtap', appInfo)
    }
  })
}

defineExpose({
  show,
  onClose
})

/**
 * 应用类型
 */
const appCodeOptions = [
  {
    value: 'BS',
    label: '业务系统'
  },
  {
    value: 'VIEW',
    label: '可视化大屏',
  },
  {
    value: 'OSS',
    label: '文件上传',
  },
  {
    value: 'UMS',
    label: '统一管理系统',
  },
  {
    value: 'GATEWAY',
    label: '网关',
  }
]


</script>

<style scoped lang="scss">

</style>