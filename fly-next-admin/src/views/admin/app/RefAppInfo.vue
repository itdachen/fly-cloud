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
          <el-form-item label="平台ID" prop="platId" class="mb10">
            <el-input v-model="appInfo.platId" placeholder="请输入平台ID"/>
          </el-form-item>
          <el-form-item label="平台名称" prop="platTitle" class="mb10">
            <el-input v-model="appInfo.platTitle" placeholder="请输入平台名称"/>
          </el-form-item>
          <el-form-item label="app秘钥" prop="appSecret" class="mb10">
            <el-input v-model="appInfo.appSecret" placeholder="请输入app秘钥"/>
          </el-form-item>
          <el-form-item label="应用标识" prop="appCode" class="mb10">
            <el-input v-model="appInfo.appCode" placeholder="请输入应用标识"/>
          </el-form-item>
          <el-form-item label="应用名称" prop="appTitle" class="mb10">
            <el-input v-model="appInfo.appTitle" placeholder="请输入应用名称"/>
          </el-form-item>
          <el-form-item label="应用名称简称" prop="appAsTitle" class="mb10">
            <el-input v-model="appInfo.appAsTitle" placeholder="请输入应用名称简称"/>
          </el-form-item>

          <!--          <el-form-item label="应用类型: BACK-后端;VIEW-前端" prop="appType"  class="mb10">-->
          <!--            <el-select v-model="appInfo.appType" placeholder="请选择应用类型: BACK-后端;VIEW-前端">-->
          <!--              <el-option-->
          <!--                  v-for="dict in dict.type.yes_or_no"-->
          <!--                  :key="dict.value"-->
          <!--                  :label="dict.label"-->
          <!--                  :value="dict.value" ></el-option>-->
          <!--            </el-select>-->
          <!--          </el-form-item>-->

          <!--          <el-form-item label="应用类型"  class="mb10">-->
          <!--            <el-checkbox-group v-model="appInfo.typeCode">-->
          <!--              <el-checkbox v-for="dict in dict.type.yes_or_no"-->
          <!--                           :key="dict.value"-->
          <!--                           :label="dict.value">-->
          <!--                {{dict.label}}-->
          <!--              </el-checkbox>-->
          <!--            </el-checkbox-group>-->
          <!--          </el-form-item>-->

          <el-form-item label="访问地址" prop="askUri" class="mb10">
            <el-input v-model="appInfo.askUri" placeholder="请输入访问地址"/>
          </el-form-item>
          <el-form-item label="图标" prop="iconIco" class="mb10">
            <el-input v-model="appInfo.iconIco" placeholder="请输入图标"/>
          </el-form-item>
          <el-form-item label="有效标志" prop="validFlag" class="mb10">
            <el-input v-model="appInfo.validFlag" placeholder="请输入有效标志"/>
          </el-form-item>
          <el-form-item label="备注" prop="remarks" class="mb10">
            <el-input v-model="appInfo.remarks" type="textarea" maxlength="100" placeholder="备注"/>
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
    objCopy(data, appInfo)
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
  appTitle: [{required: true, message: '应用名称不能为空', trigger: 'blur'}],
  appType: [{required: true, message: '应用类型不能为空', trigger: 'blur'}],
  validFlag: [{required: true, message: '有效标志不能为空', trigger: 'blur'}],
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

</script>

<style scoped lang="scss">

</style>