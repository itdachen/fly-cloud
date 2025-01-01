<template>
  <lay-layer v-model="layerRef.open"
             :title="layerRef.title"
             :area="layerRef.area"
             :shade-close="layerRef.shadeClose"
             :maxmin="layerRef.maxmin">

    <div style="padding: 20px">
      <lay-form :model="authClazzRole" ref="layAuthClazzRoleForm">
              <lay-form-item label="应用ID" prop="appId" mode="inline" :required="layerRef.required" >
                <lay-input placeholder="请输入应用ID" v-model="authClazzRole.appId" :disabled="layerRef.disabled"></lay-input>
              </lay-form-item>

              <lay-form-item label="岗位代码" prop="clazzCode" mode="inline" :required="layerRef.required" >
                <lay-input placeholder="请输入岗位代码" v-model="authClazzRole.clazzCode" :disabled="layerRef.disabled"></lay-input>
              </lay-form-item>

              <lay-form-item label="岗位名称" prop="clazzTitle" mode="inline" :required="layerRef.required" >
                <lay-input placeholder="请输入岗位名称" v-model="authClazzRole.clazzTitle" :disabled="layerRef.disabled"></lay-input>
              </lay-form-item>

              <lay-form-item label="身份ID" prop="roleId" mode="inline" :required="layerRef.required" >
                <lay-input placeholder="请输入身份ID" v-model="authClazzRole.roleId" :disabled="layerRef.disabled"></lay-input>
              </lay-form-item>

              <lay-form-item label="身份名称" prop="roleTitle" mode="inline" :required="layerRef.required" >
                <lay-input placeholder="请输入身份名称" v-model="authClazzRole.roleTitle" :disabled="layerRef.disabled"></lay-input>
              </lay-form-item>

              <lay-form-item label="有效标志: Y-是;N-否" prop="validFlag" mode="inline" :required="layerRef.required" >
                <lay-input placeholder="请输入有效标志: Y-是;N-否" v-model="authClazzRole.validFlag" :disabled="layerRef.disabled"></lay-input>
              </lay-form-item>

              <lay-form-item label="备注" prop="remarks"  >
                <lay-textarea placeholder="请输入备注" v-model="authClazzRole.remarks" :disabled="layerRef.disabled"></lay-textarea>
              </lay-form-item>

      </lay-form>

    </div>


      <template v-slot:footer>
        <div class="fly-form-footer">
          <lay-button v-if="layerRef.submit" class="fly-button fly-ok-button " @click="saveAuthClazzRoleHandler">
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


<script setup lang="ts"  name="refAuthClazzRole">
  import {reactive, ref} from "vue";
  import {FormTypeEnum} from "@/fly/biz/BizModel";
  import {AuthClazzRole} from "@/api/auth/model/AuthClazzRoleModel";
  import useTenantInfoComposable from "@/composables/auth/AuthClazzRoleComposable";

  const {
      authClazzRole,
      authClazzRoleDataHandler
  } = useTenantInfoComposable();

  const layAuthClazzRoleForm = ref<any>();

  /* 弹窗 */
  const layerRef = reactive<any>({
    open: false,
    title: '身份岗位管理表',
    area: ['500px', '550px'], // 宽度,  高度
    disabled: false,
    shadeClose: false,
    maxmin: false,
    submit: true,
  })


  /**
   * 提交
   */
  function saveAuthClazzRoleHandler() {
    layAuthClazzRoleForm.value.validate((isValidate: boolean, model: AuthClazzRole, errors: any[]) => {
      if (!isValidate) {
        return;
      }
      authClazzRoleDataHandler(model);
      // onTapClose(); // 关闭弹窗
      // console.log('数据保存', authClazzRole.value);
      //  emit('click', authClazzRole)
    });
  }


  //显示弹框
  const open = (type: FormTypeEnum, row?: AuthClazzRole) => {
    layerRef.title = type + '身份岗位管理表';
    if (null !== row) {
        authClazzRole.value = JSON.parse(JSON.stringify(row))
    } else {
        authClazzRole.value = {
              id: '' ,  // 主键唯一标识
              tenantId: '' ,  // 租户ID
              appId: '' ,  // 应用ID
              clazzCode: '' ,  // 岗位代码
              clazzTitle: '' ,  // 岗位名称
              roleId: '' ,  // 身份ID
              roleTitle: '' ,  // 身份名称
              validFlag: '' ,  // 有效标志: Y-是;N-否
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