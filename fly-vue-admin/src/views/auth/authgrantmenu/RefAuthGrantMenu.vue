<template>
  <lay-layer v-model="layerRef.open"
             :title="layerRef.title"
             :area="layerRef.area"
             :shade-close="layerRef.shadeClose"
             :maxmin="layerRef.maxmin">

    <div style="padding: 20px">
      <lay-form :model="authGrantMenu" ref="layAuthGrantMenuForm">
              <lay-form-item label="appID" prop="appId" mode="inline" :required="layerRef.required" >
                <lay-input placeholder="请输入appID" v-model="authGrantMenu.appId" :disabled="layerRef.disabled"></lay-input>
              </lay-form-item>

              <lay-form-item label="用户ID/人员身份ID" prop="roleId" mode="inline" :required="layerRef.required" >
                <lay-input placeholder="请输入用户ID/人员身份ID" v-model="authGrantMenu.roleId" :disabled="layerRef.disabled"></lay-input>
              </lay-form-item>

              <lay-form-item label="菜单ID" prop="menuId" mode="inline" :required="layerRef.required" >
                <lay-input placeholder="请输入菜单ID" v-model="authGrantMenu.menuId" :disabled="layerRef.disabled"></lay-input>
              </lay-form-item>

      </lay-form>

    </div>


      <template v-slot:footer>
        <div class="fly-form-footer">
          <lay-button v-if="layerRef.submit" class="fly-button fly-ok-button " @click="saveAuthGrantMenuHandler">
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


<script setup lang="ts"  name="refAuthGrantMenu">
  import {reactive, ref} from "vue";
  import {FormTypeEnum} from "@/fly/biz/BizModel";
  import {AuthGrantMenu} from "@/api/auth/model/AuthGrantMenuModel";
  import useTenantInfoComposable from "@/composables/auth/AuthGrantMenuComposable";

  const {
      authGrantMenu,
      authGrantMenuDataHandler
  } = useTenantInfoComposable();

  const layAuthGrantMenuForm = ref<any>();

  /* 弹窗 */
  const layerRef = reactive<any>({
    open: false,
    title: '权限下发',
    area: ['500px', '550px'], // 宽度,  高度
    disabled: false,
    shadeClose: false,
    maxmin: false,
    submit: true,
  })


  /**
   * 提交
   */
  function saveAuthGrantMenuHandler() {
    layAuthGrantMenuForm.value.validate((isValidate: boolean, model: AuthGrantMenu, errors: any[]) => {
      if (!isValidate) {
        return;
      }
      authGrantMenuDataHandler(model);
      // onTapClose(); // 关闭弹窗
      // console.log('数据保存', authGrantMenu.value);
      //  emit('click', authGrantMenu)
    });
  }


  //显示弹框
  const open = (type: FormTypeEnum, row?: AuthGrantMenu) => {
    layerRef.title = type + '权限下发';
    if (null !== row) {
        authGrantMenu.value = JSON.parse(JSON.stringify(row))
    } else {
        authGrantMenu.value = {
              id: '' ,  // ID
              tenantId: '' ,  // 租户ID
              appId: '' ,  // appID
              roleId: '' ,  // 用户ID/人员身份ID
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