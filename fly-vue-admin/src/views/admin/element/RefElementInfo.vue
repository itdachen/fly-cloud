<template>
  <lay-layer v-model="layerRef.open"
             :title="layerRef.title"
             :area="layerRef.area"
             :shade-close="layerRef.shadeClose"
             :maxmin="layerRef.maxmin">

    <div style="padding: 20px">
      <lay-form :model="elementInfo" ref="layElementInfoForm">
        <lay-form-item label="认证编码" prop="authCode" mode="inline" :required="layerRef.required">
          <lay-input placeholder="请输入认证编码" v-model="elementInfo.authCode"
                     :disabled="layerRef.disabled"></lay-input>
        </lay-form-item>

        <lay-form-item label="标题" prop="title" mode="inline" :required="layerRef.required">
          <lay-input placeholder="请输入标题" v-model="elementInfo.title" :disabled="layerRef.disabled"></lay-input>
        </lay-form-item>

        <lay-form-item label="类型: button,uri" prop="type" :required="layerRef.required" mode="inline">
          <lay-radio v-model="elementInfo.type" name="action" value="Y" label="有效"
                     :disabled="layerRef.disabled"></lay-radio>
          <lay-radio v-model="elementInfo.type" name="action" value="N" label="无效"
                     :disabled="layerRef.disabled"></lay-radio>
        </lay-form-item>

        <lay-form-item label="接口请求地址" prop="apiUri" mode="inline" :required="layerRef.required">
          <lay-input placeholder="请输入接口请求地址" v-model="elementInfo.apiUri"
                     :disabled="layerRef.disabled"></lay-input>
        </lay-form-item>

        <lay-form-item label="API请求类型: POST/GET/PUT/DELETE" prop="httpMethod" :required="layerRef.required"
                       mode="inline">
          <lay-radio v-model="elementInfo.httpMethod" name="action" value="Y" label="有效"
                     :disabled="layerRef.disabled"></lay-radio>
          <lay-radio v-model="elementInfo.httpMethod" name="action" value="N" label="无效"
                     :disabled="layerRef.disabled"></lay-radio>
        </lay-form-item>

        <lay-form-item label="排序" prop="orderNum" mode="inline" :required="layerRef.required">
          <lay-input placeholder="请输入排序" v-model="elementInfo.orderNum" :disabled="layerRef.disabled"></lay-input>
        </lay-form-item>

        <lay-form-item label="是否展示/有效标志: Y-有效;N-无效" prop="validFlag" :required="layerRef.required"
                       mode="inline">
          <lay-radio v-model="elementInfo.validFlag" name="action" value="Y" label="有效"
                     :disabled="layerRef.disabled"></lay-radio>
          <lay-radio v-model="elementInfo.validFlag" name="action" value="N" label="无效"
                     :disabled="layerRef.disabled"></lay-radio>
        </lay-form-item>

        <lay-form-item label="是否需要授权: Y-需要;N-不需要" prop="hasAuth" :required="layerRef.required" mode="inline">
          <lay-radio v-model="elementInfo.hasAuth" name="action" value="Y" label="有效"
                     :disabled="layerRef.disabled"></lay-radio>
          <lay-radio v-model="elementInfo.hasAuth" name="action" value="N" label="无效"
                     :disabled="layerRef.disabled"></lay-radio>
        </lay-form-item>

        <lay-form-item label="是否需要IP授权: Y-需要;N-不需要" prop="hasIp" :required="layerRef.required" mode="inline">
          <lay-radio v-model="elementInfo.hasIp" name="action" value="Y" label="有效"
                     :disabled="layerRef.disabled"></lay-radio>
          <lay-radio v-model="elementInfo.hasIp" name="action" value="N" label="无效"
                     :disabled="layerRef.disabled"></lay-radio>
        </lay-form-item>

        <lay-form-item label="备注" prop="remarks">
          <lay-textarea placeholder="请输入备注" v-model="elementInfo.remarks"
                        :disabled="layerRef.disabled"></lay-textarea>
        </lay-form-item>

      </lay-form>

    </div>


    <template v-slot:footer>
      <div class="fly-form-footer">
        <lay-button v-if="layerRef.submit" class="fly-button fly-ok-button " @click="saveElementInfoHandler">
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


<script setup lang="ts" name="refElementInfo">
import {reactive, ref} from "vue";
import {FormTypeEnum} from "@/fly/biz/BizModel";
import {ElementInfo} from "@/api/admin/model/ElementInfoModel";
import useTenantInfoComposable from "@/composables/admin/ElementInfoComposable";

const {
  elementInfo,
  elementInfoDataHandler
} = useTenantInfoComposable();

const layElementInfoForm = ref<any>();

/* 弹窗 */
const layerRef = reactive<any>({
  open: false,
  title: '资源(按钮)信息',
  area: ['500px', '550px'], // 宽度,  高度
  disabled: false,
  shadeClose: false,
  maxmin: false,
  submit: true,
})


/**
 * 提交
 */
function saveElementInfoHandler() {
  layElementInfoForm.value.validate((isValidate: boolean, model: ElementInfo, errors: any[]) => {
    if (!isValidate) {
      return;
    }
    elementInfoDataHandler(model);
    // onTapClose(); // 关闭弹窗
    // console.log('数据保存', elementInfo.value);
    //  emit('click', elementInfo)
  });
}


//显示弹框
const open = (type: FormTypeEnum, row?: ElementInfo) => {
  layerRef.title = type + '资源(按钮)信息';
  if (null !== row) {
    elementInfo.value = JSON.parse(JSON.stringify(row))
  } else {
    elementInfo.value = {
      id: '',  // 资源ID
      appId: '',  // 应用ID/appID
      menuId: '',  // 菜单ID
      menuTitle: '',  // 菜单名称
      authCode: '',  // 认证编码
      title: '',  // 标题
      type: '',  // 类型: button,uri
      pageUri: '',  // 页面访问地址
      apiUri: '',  // 接口请求地址
      httpMethod: '',  // API请求类型: POST/GET/PUT/DELETE
      orderNum: '',  // 排序
      thirdParty: '',  // 第三方平台标识
      thirdTitle: '',  // 第三方平台名称
      thirdUri: '',  // 第三方基础访问地址, 例如: http://www.baidu.com
      askUri: '',  // 最终访问地址, 根据第三方平台信息拼接
      validFlag: '',  // 是否展示/有效标志: Y-有效;N-无效
      hasAuth: '',  // 是否需要授权: Y-需要;N-不需要
      hasIp: '',  // 是否需要IP授权: Y-需要;N-不需要
      remarks: ''  // 备注
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