<template>

  <lay-layer v-model="layerRef.open"
             :title="layerRef.title"
             :area="layerRef.area"
             :shade-close="layerRef.shadeClose"
             :maxmin="layerRef.maxmin">

    <div style="padding: 30px">
      <lay-form :model="tenantInfo"
                isLabelTooltip
                :label-position="'right'"
                labelWidth="130px"
                ref="layTenantInfoForm">

        <lay-form-item label="名称" prop="title">
          <lay-input placeholder="请输入名称" v-model="tenantInfo.title"></lay-input>
        </lay-form-item>

        <lay-form-item label="简称" prop="titleAs">
          <lay-input placeholder="请输入简称" v-model="tenantInfo.titleAs"></lay-input>
        </lay-form-item>

        <lay-form-item label="所属省" prop="provId" mode="inline">
          <lay-select v-model="tenantInfo.provId">
            <lay-select-option value="Y" label="有效"></lay-select-option>
            <lay-select-option value="N" label="无效"></lay-select-option>
          </lay-select>
        </lay-form-item>

        <lay-form-item label="所属市州" prop="cityId" mode="inline">
          <lay-select v-model="tenantInfo.cityId">
            <lay-select-option value="Y" label="有效"></lay-select-option>
            <lay-select-option value="N" label="无效"></lay-select-option>
          </lay-select>
        </lay-form-item>

        <lay-form-item label="所属区县" prop="countyId" mode="inline">
          <lay-select v-model="tenantInfo.countyId">
            <lay-select-option value="Y" label="有效"></lay-select-option>
            <lay-select-option value="N" label="无效"></lay-select-option>
          </lay-select>
        </lay-form-item>

        <lay-form-item label="官网/访问地址" prop="homeUri" mode="inline">
          <lay-input placeholder="请输入官网/访问地址" v-model="tenantInfo.homeUri"></lay-input>
        </lay-form-item>

        <lay-form-item label="联系电话" prop="telephone" mode="inline">
          <lay-input placeholder="请输入联系电话" v-model="tenantInfo.telephone"></lay-input>
        </lay-form-item>

        <lay-form-item label="电子邮箱" prop="eMailBox" mode="inline">
          <lay-input placeholder="请输入电子邮箱" v-model="tenantInfo.eMailBox"></lay-input>
        </lay-form-item>

        <lay-form-item label="传真" prop="facsimile" mode="inline">
          <lay-input placeholder="请输入传真" v-model="tenantInfo.facsimile"></lay-input>
        </lay-form-item>

        <lay-form-item label="有效标志" prop="validFlag" mode="inline">
          <lay-radio v-model="tenantInfo.validFlag" name="validFlag" value="Y">有效</lay-radio>
          <lay-radio v-model="tenantInfo.validFlag" name="validFlag" value="N">无效</lay-radio>
        </lay-form-item>


        <lay-form-item label="备注" prop="remarks">
          <lay-textarea placeholder="请输入备注" v-model="tenantInfo.remarks"></lay-textarea>
        </lay-form-item>
      </lay-form>
    </div>

    <template v-slot:footer>
      <div class="fly-form-footer">
        <lay-button v-if="showToSubmit" class="fly-button fly-ok-button" @click="onTapSubmit">
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


<script setup lang="ts" name="refTenantInfo">
import {reactive, ref} from "vue";
import {FormTypeEnum} from "@/fly/biz/BizModel";
import useTenantInfoComposable from "@/composables/admin/TenantInfoComposable";
import {TenantInfo} from "@/api/admin/model/TenantInfoModel";

const {
  tenantInfo,
  tenantInfoDataHandler
} = useTenantInfoComposable();

const layTenantInfoForm = ref();
const showToSubmit = ref<boolean>(true);

/* 弹窗 */
const layerRef = reactive<any>({
  open: false,
  title: '',
  area: ['1200px', '540px'],
  disabled: false,
  shadeClose: false,
  maxmin: false,
})


/**
 * 提交
 */
function onTapSubmit() {
  layTenantInfoForm.value.validate((isValidate: boolean, model: TenantInfo, errors: any[]) => {
    if (!isValidate) {
      return;
    }
    tenantInfoDataHandler(model);
    onTapClose();
  });

}


//显示弹框
const open = (type: FormTypeEnum, data?: TenantInfo) => {
  if (null !== data && undefined !== data) {
    tenantInfo.value = JSON.parse(JSON.stringify(data))
  } else {
    tenantInfo.value = {
      id: '',  // 主键唯一标识
      parentId: '',  // 上级ID
      parentTitle: '',  // 上级名称
      typeId: 'Y',  // 租户类型代码, 例如:100
      typeTitle: '',  // 租户类型名称, 例如: 系统管理员
      title: '',  // 名称
      titleAs: '',  // 简称
      provId: 'Y',  // 所属省级ID
      provTitle: '',  // 所属省级名称
      cityId: 'Y',  // 所属市州
      cityTitle: '',  // 所属市州名称
      countyId: 'Y',  // 所属区县
      countyTitle: '',  // 所属区县名称
      homeIcon: '',  // 图标
      homeUri: '',  // 官网/访问地址
      ownerId: '',  // 拥有者ID
      ownerNickName: '',  // 拥有者昵称
      telephone: '',  // 联系电话
      eMailBox: '',  // 电子邮箱
      facsimile: '',  // 传真
      validFlag: 'Y',  // 有效标志
      remarks: ''  // 备注
    }
  }
  layerRef.disabled = false;
  showToSubmit.value = true;
  if (FormTypeEnum.VIEW === type) {
    layerRef.disabled = true;
    showToSubmit.value = false;
  }

  layerRef.title = type + '租户信息';
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

</script>


<style scoped>

</style>