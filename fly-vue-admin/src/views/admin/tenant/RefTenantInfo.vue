<template>
  <lay-layer :title="layerRef.title"
             :area="layerRef.area"
             v-model="layerRef.open"
             :shade-close="false"
             :maxmin="true">

    <div style="padding: 20px">
      <lay-form :model="tenantInfo" ref="layTenantInfoForm">


        <lay-form-item label="名称" prop="title" mode="inline">
          <lay-input placeholder="请输入名称" v-model="tenantInfo.title"></lay-input>
        </lay-form-item>

        <lay-form-item label="简称" prop="titleAs" mode="inline">
          <lay-input placeholder="请输入简称" v-model="tenantInfo.titleAs"></lay-input>
        </lay-form-item>

        <lay-form-item label="所属省级ID" prop="provId" mode="inline">
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

        <lay-form-item label="图标" prop="homeIcon" mode="inline">
          <lay-input placeholder="请输入图标" v-model="tenantInfo.homeIcon"></lay-input>
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

        <lay-form-item label="有效标志: Y-是;N-否" prop="validFlag" mode="inline">
          <lay-radio v-model="tenantInfo.validFlag" name="action" value="Y" label="有效"></lay-radio>
          <lay-radio v-model="tenantInfo.validFlag" name="action" value="N" label="无效"></lay-radio>
        </lay-form-item>

        <lay-form-item label="备注" prop="remarks">
          <lay-textarea placeholder="请输入备注" v-model="tenantInfo.remarks"></lay-textarea>
        </lay-form-item>
      </lay-form>


      <template v-slot:footer>
        <div class="fly-form-footer">
          <lay-button v-if="showToSubmit" class="fly-button fly-ok-button " @click="toSubmit">
            <lay-icon class="layui-icon-ok"></lay-icon>
            保存
          </lay-button>
          <lay-button class="fly-button fly-close-button" @click="onTapClose">
            <lay-icon class="layui-icon-close"></lay-icon>
            关闭
          </lay-button>
        </div>
      </template>

<!--      <div style="width: 100%; text-align: right">-->
<!--        <lay-button v-if="showToSubmit" size="sm" class="fly-button fly-ok-button" @click="toSubmit">-->
<!--          <lay-icon class="layui-icon-ok"></lay-icon>-->
<!--          保存-->
<!--        </lay-button>-->
<!--        <lay-button size="sm" class="fly-button fly-close-button" @click="onTapClose">-->
<!--          <lay-icon class="layui-icon-close"></lay-icon>-->
<!--          取消-->
<!--        </lay-button>-->
<!--      </div>-->
    </div>
  </lay-layer>
</template>


<script setup lang="ts" name="refTenantInfoComponent">
import {reactive, ref} from "vue";
import {layer} from "@layui/layui-vue";
import {FormTypeEnum} from "@/fly/biz/BizModel";

const showToSubmit = ref<boolean>(true);

/* 弹窗 */
const layerRef = reactive<any>({
  open: false,
  title: '',
  area: ['500px', '550px'],
  disabled: false
})

const layTenantInfoForm = ref<any>();

const tenantInfo = ref<any>({});

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
  console.log('数据保存', tenantInfo.value);
  emit('click', tenantInfo)
}


//显示弹框
const open = (type: FormTypeEnum, row?: any) => {
  if (null != row) {
    tenantInfo.value = JSON.parse(JSON.stringify(row))
  } else {
    tenantInfo.value = {
      id: '',  // 主键唯一标识
      parentId: '',  // 上级ID
      parentTitle: '',  // 上级名称
      typeId: '',  // 租户类型代码, 例如:100
      typeTitle: '',  // 租户类型名称, 例如: 系统管理员
      title: '',  // 名称
      titleAs: '',  // 简称
      provId: '',  // 所属省级ID
      provTitle: '',  // 所属省级名称
      cityId: '',  // 所属市州
      cityTitle: '',  // 所属市州名称
      countyId: '',  // 所属区县
      countyTitle: '',  // 所属区县名称
      homeIcon: '',  // 图标
      homeUri: '',  // 官网/访问地址
      ownerId: '',  // 拥有者ID
      ownerNickName: '',  // 拥有者昵称
      telephone: '',  // 联系电话
      eMailBox: '',  // 电子邮箱
      facsimile: '',  // 传真
      validFlag: '',  // 有效标志: Y-是;N-否
      remarks: ''  // 备注
    }
  }

  if (FormTypeEnum.VIEW === type) {
    layerRef.disabled = true;
    showToSubmit.value = false;
  }

  layerRef.open = true;
  layerRef.title = type + '租户/公司信息';
};

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