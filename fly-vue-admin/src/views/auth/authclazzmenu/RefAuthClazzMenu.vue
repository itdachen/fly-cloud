<template>
  <lay-layer v-model="layerRef.open"
             :title="layerRef.title"
             :area="layerRef.area"
             :shade-close="layerRef.shadeClose"
             :maxmin="layerRef.maxmin">

    <div style="padding: 20px">
      <lay-form :model="authClazzMenu" ref="layAuthClazzMenuForm">
              <lay-form-item label="应用ID" prop="appId" mode="inline" :required="layerRef.required" >
                <lay-input placeholder="请输入应用ID" v-model="authClazzMenu.appId" :disabled="layerRef.disabled"></lay-input>
              </lay-form-item>

              <lay-form-item label="岗位代码" prop="clazzCode" mode="inline" :required="layerRef.required" >
                <lay-input placeholder="请输入岗位代码" v-model="authClazzMenu.clazzCode" :disabled="layerRef.disabled"></lay-input>
              </lay-form-item>

              <lay-form-item label="岗位名称" prop="clazzTitle" mode="inline" :required="layerRef.required" >
                <lay-input placeholder="请输入岗位名称" v-model="authClazzMenu.clazzTitle" :disabled="layerRef.disabled"></lay-input>
              </lay-form-item>

              <lay-form-item label="菜单ID/资源ID" prop="menuId" mode="inline" :required="layerRef.required" >
                <lay-input placeholder="请输入菜单ID/资源ID" v-model="authClazzMenu.menuId" :disabled="layerRef.disabled"></lay-input>
              </lay-form-item>

              <lay-form-item label="菜单名称/资源名称" prop="menuTitle" mode="inline" :required="layerRef.required" >
                <lay-input placeholder="请输入菜单名称/资源名称" v-model="authClazzMenu.menuTitle" :disabled="layerRef.disabled"></lay-input>
              </lay-form-item>

      </lay-form>

    </div>


      <template v-slot:footer>
        <div class="fly-form-footer">
          <lay-button v-if="layerRef.submit" class="fly-button fly-ok-button " @click="saveAuthClazzMenuHandler">
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


<script setup lang="ts"  name="refAuthClazzMenu">
  import {reactive, ref} from "vue";
  import {FormTypeEnum} from "@/fly/biz/BizModel";
  import {AuthClazzMenu} from "@/api/auth/model/AuthClazzMenuModel";
  import useTenantInfoComposable from "@/composables/auth/AuthClazzMenuComposable";

  const {
      authClazzMenu,
      authClazzMenuDataHandler
  } = useTenantInfoComposable();

  const layAuthClazzMenuForm = ref<any>();

  /* 弹窗 */
  const layerRef = reactive<any>({
    open: false,
    title: '岗位菜单',
    area: ['500px', '550px'], // 宽度,  高度
    disabled: false,
    shadeClose: false,
    maxmin: false,
    submit: true,
  })


  /**
   * 提交
   */
  function saveAuthClazzMenuHandler() {
    layAuthClazzMenuForm.value.validate((isValidate: boolean, model: AuthClazzMenu, errors: any[]) => {
      if (!isValidate) {
        return;
      }
      authClazzMenuDataHandler(model);
      // onTapClose(); // 关闭弹窗
      // console.log('数据保存', authClazzMenu.value);
      //  emit('click', authClazzMenu)
    });
  }


  //显示弹框
  const open = (type: FormTypeEnum, row?: AuthClazzMenu) => {
    layerRef.title = type + '岗位菜单';
    if (null !== row) {
        authClazzMenu.value = JSON.parse(JSON.stringify(row))
    } else {
        authClazzMenu.value = {
              id: '' ,  // ID
              tenantId: '' ,  // 租户ID
              appId: '' ,  // 应用ID
              clazzCode: '' ,  // 岗位代码
              clazzTitle: '' ,  // 岗位名称
              menuId: '' ,  // 菜单ID/资源ID
              menuTitle: ''  // 菜单名称/资源名称
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