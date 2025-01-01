<template>
  <lay-layer v-model="layerRef.open"
             :title="layerRef.title"
             :area="layerRef.area"
             :shade-close="layerRef.shadeClose"
             :maxmin="layerRef.maxmin">

    <div style="padding: 20px">
      <lay-form :model="authRoleMenu" ref="layAuthRoleMenuForm">
              <lay-form-item label="应用ID" prop="appId" mode="inline" :required="layerRef.required" >
                <lay-input placeholder="请输入应用ID" v-model="authRoleMenu.appId" :disabled="layerRef.disabled"></lay-input>
              </lay-form-item>

              <lay-form-item label="人员身份ID" prop="roleId" mode="inline" :required="layerRef.required" >
                <lay-input placeholder="请输入人员身份ID" v-model="authRoleMenu.roleId" :disabled="layerRef.disabled"></lay-input>
              </lay-form-item>

              <lay-form-item label="菜单ID" prop="menuId" mode="inline" :required="layerRef.required" >
                <lay-input placeholder="请输入菜单ID" v-model="authRoleMenu.menuId" :disabled="layerRef.disabled"></lay-input>
              </lay-form-item>

      </lay-form>

    </div>


      <template v-slot:footer>
        <div class="fly-form-footer">
          <lay-button v-if="layerRef.submit" class="fly-button fly-ok-button " @click="saveAuthRoleMenuHandler">
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


<script setup lang="ts"  name="refAuthRoleMenu">
  import {reactive, ref} from "vue";
  import {FormTypeEnum} from "@/fly/biz/BizModel";
  import {AuthRoleMenu} from "@/api/auth/model/AuthRoleMenuModel";
  import useTenantInfoComposable from "@/composables/auth/AuthRoleMenuComposable";

  const {
      authRoleMenu,
      authRoleMenuDataHandler
  } = useTenantInfoComposable();

  const layAuthRoleMenuForm = ref<any>();

  /* 弹窗 */
  const layerRef = reactive<any>({
    open: false,
    title: '人员身份菜单',
    area: ['500px', '550px'], // 宽度,  高度
    disabled: false,
    shadeClose: false,
    maxmin: false,
    submit: true,
  })


  /**
   * 提交
   */
  function saveAuthRoleMenuHandler() {
    layAuthRoleMenuForm.value.validate((isValidate: boolean, model: AuthRoleMenu, errors: any[]) => {
      if (!isValidate) {
        return;
      }
      authRoleMenuDataHandler(model);
      // onTapClose(); // 关闭弹窗
      // console.log('数据保存', authRoleMenu.value);
      //  emit('click', authRoleMenu)
    });
  }


  //显示弹框
  const open = (type: FormTypeEnum, row?: AuthRoleMenu) => {
    layerRef.title = type + '人员身份菜单';
    if (null !== row) {
        authRoleMenu.value = JSON.parse(JSON.stringify(row))
    } else {
        authRoleMenu.value = {
              id: '' ,  // ID
              tenantId: '' ,  // 租户ID
              appId: '' ,  // 应用ID
              roleId: '' ,  // 人员身份ID
              menuId: ''  // 菜单ID
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