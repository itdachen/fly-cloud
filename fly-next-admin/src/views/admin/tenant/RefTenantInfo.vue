<template>
  <div>
    <dialog-popup :title="dialog.title"
                  :visible="dialog.visible"
                  :width="dialog.width"
                  :showSubmit="dialog.showSubmit"
                  @close="onClose()"
                  @submit="confirm()">

      <template v-slot:content>
        <el-form :model="tenantInfo" ref="formRef"
                 :rules="rules" :disabled="isDisabled"
                 size="default" label-width="120px">

          <el-form-item label="名称" prop="title" class="mb10">
            <el-input v-model="tenantInfo.title" placeholder="请输入名称"/>
          </el-form-item>
          <el-form-item label="简称" prop="titleAs" class="mb10">
            <el-input v-model="tenantInfo.titleAs"/>
          </el-form-item>
          <!--          <el-form-item label="所属省级ID" prop="provId" class="mb10">-->
          <!--            <el-input v-model="tenantInfo.provId" placeholder="请输入所属省级ID"/>-->
          <!--          </el-form-item>-->
          <!--          <el-form-item label="所属省级名称" prop="provTitle" class="mb10">-->
          <!--            <el-input v-model="tenantInfo.provTitle" placeholder="请输入所属省级名称"/>-->
          <!--          </el-form-item>-->
          <!--          <el-form-item label="所属市州" prop="cityId" class="mb10">-->
          <!--            <el-input v-model="tenantInfo.cityId" placeholder="请输入所属市州"/>-->
          <!--          </el-form-item>-->
          <!--          <el-form-item label="所属市州名称" prop="cityTitle" class="mb10">-->
          <!--            <el-input v-model="tenantInfo.cityTitle" placeholder="请输入所属市州名称"/>-->
          <!--          </el-form-item>-->
          <!--          <el-form-item label="所属区县" prop="countyId" class="mb10">-->
          <!--            <el-input v-model="tenantInfo.countyId" placeholder="请输入所属区县"/>-->
          <!--          </el-form-item>-->
          <!--          <el-form-item label="所属区县名称" prop="countyTitle" class="mb10">-->
          <!--            <el-input v-model="tenantInfo.countyTitle" placeholder="请输入所属区县名称"/>-->
          <!--          </el-form-item>-->
          <el-form-item label="图标" prop="homeIcon" class="mb10">
            <el-input v-model="tenantInfo.homeIcon" placeholder="请输入图标"/>
          </el-form-item>
          <el-form-item label="官网/访问地址" prop="homeUri" class="mb10">
            <el-input v-model="tenantInfo.homeUri" placeholder="请输入官网/访问地址"/>
          </el-form-item>
          <!--          <el-form-item label="拥有者ID" prop="ownerId" class="mb10">-->
          <!--            <el-input v-model="tenantInfo.ownerId" placeholder="请输入拥有者ID"/>-->
          <!--          </el-form-item>-->
          <!--          <el-form-item label="拥有者昵称" prop="ownerNickName" class="mb10">-->
          <!--            <el-input v-model="tenantInfo.ownerNickName" placeholder="请输入拥有者昵称"/>-->
          <!--          </el-form-item>-->
          <el-form-item label="联系电话" prop="telephone" class="mb10">
            <el-input v-model="tenantInfo.telephone"/>
          </el-form-item>
          <el-form-item label="电子邮箱" prop="eMailBox" class="mb10">
            <el-input v-model="tenantInfo.eMailBox"/>
          </el-form-item>
          <el-form-item label="传真" prop="facsimile" class="mb10">
            <el-input v-model="tenantInfo.facsimile"/>
          </el-form-item>
          <!--          <el-form-item label="职能代码" prop="funcCode" class="mb10">-->
          <!--            <el-input v-model="tenantInfo.funcCode" placeholder="请输入职能代码"/>-->
          <!--          </el-form-item>-->
          <el-form-item label="有效标志" prop="validFlag" class="mb10">
            <el-radio-group v-model="tenantInfo.validFlag">
              <el-radio v-for="dict in validFlag"
                        :key="dict.value"
                        :label="dict.value">
                {{ dict.label }}
              </el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="备注" prop="remarks" class="mb10">
            <el-input v-model="tenantInfo.remarks" type="textarea" maxlength="100"/>
          </el-form-item>

        </el-form>
      </template>
    </dialog-popup>
  </div>
</template>

<script setup lang="ts" name="RefTenantInfo">
import {reactive, ref} from 'vue';
import {ElForm} from "element-plus/es";
import DialogPopup from '/@/components/dialog/DialogPopup.vue';
import useDialogPopup from '/@/components/dialog/DialogPopup';
import useElementFromComposable from '/@/components/form/ElementFromComposable';
import {DialogTypeEnum} from "/@/components/dialog/DialogModel";
import useTenantInfoBuilder, {TenantInfo} from '/@/api/admin/models/TenantInfoModel';
import useDictDataComposable from "/@/hooks/dict/useDictData";

const {validFlag} = useDictDataComposable();

/**
 * 表单的ref属性
 */
const formRef = ref<InstanceType<typeof ElForm>>();

/**
 * 弹框属性
 */
const {dialog, onShow, onClose} = useDialogPopup();

const {tenantInfo} = useTenantInfoBuilder();

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
const show = (type: DialogTypeEnum, data?: TenantInfo) => {
  //设置弹框的属性
  dialog.width = 800
  dialog.title = type + '租户';
  /* 清空表单内容 */
  resetForm(formRef.value, tenantInfo);
  if (undefined !== data && null !== data) {
    /* 新表单赋值 */
    objCopy(data, tenantInfo)
  } else {
    objCopy({}, tenantInfo);
    tenantInfo.validFlag = 'Y'
  }
  if (DialogTypeEnum.SAVE === type || DialogTypeEnum.UPDATE === type) {
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
  title: [{required: true, message: '名称不能为空', trigger: 'blur'}]
});

//定义事件
const emits = defineEmits(['bindtap'])

const confirm = () => {
  formRef.value?.validate(valid => {
    if (valid) {
      emits('bindtap', tenantInfo)
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