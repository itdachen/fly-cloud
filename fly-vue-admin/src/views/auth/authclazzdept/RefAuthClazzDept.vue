<template>
  <lay-layer v-model="layerRef.open"
             :title="layerRef.title"
             :area="layerRef.area"
             :shade-close="layerRef.shadeClose"
             :maxmin="layerRef.maxmin">

    <div style="padding: 20px">
      <lay-form :model="authClazzDept" ref="layAuthClazzDeptForm">
              <lay-form-item label="部门代码" prop="deptCode" mode="inline" :required="layerRef.required" >
                <lay-input placeholder="请输入部门代码" v-model="authClazzDept.deptCode" :disabled="layerRef.disabled"></lay-input>
              </lay-form-item>

              <lay-form-item label="部门名称" prop="deptTitle" mode="inline" :required="layerRef.required" >
                <lay-input placeholder="请输入部门名称" v-model="authClazzDept.deptTitle" :disabled="layerRef.disabled"></lay-input>
              </lay-form-item>

              <lay-form-item label="部门层级" prop="deptLevel" mode="inline" :required="layerRef.required" >
                <lay-input placeholder="请输入部门层级" v-model="authClazzDept.deptLevel" :disabled="layerRef.disabled"></lay-input>
              </lay-form-item>

              <lay-form-item label="岗位代码" prop="clazzCode" mode="inline" :required="layerRef.required" >
                <lay-input placeholder="请输入岗位代码" v-model="authClazzDept.clazzCode" :disabled="layerRef.disabled"></lay-input>
              </lay-form-item>

              <lay-form-item label="岗位名称" prop="clazzTitle" mode="inline" :required="layerRef.required" >
                <lay-input placeholder="请输入岗位名称" v-model="authClazzDept.clazzTitle" :disabled="layerRef.disabled"></lay-input>
              </lay-form-item>

              <lay-form-item label="有效标志: Y-是;N-否" prop="validFlag" mode="inline" :required="layerRef.required" >
                <lay-input placeholder="请输入有效标志: Y-是;N-否" v-model="authClazzDept.validFlag" :disabled="layerRef.disabled"></lay-input>
              </lay-form-item>

              <lay-form-item label="备注" prop="remarks"  >
                <lay-textarea placeholder="请输入备注" v-model="authClazzDept.remarks" :disabled="layerRef.disabled"></lay-textarea>
              </lay-form-item>

      </lay-form>

    </div>


      <template v-slot:footer>
        <div class="fly-form-footer">
          <lay-button v-if="layerRef.submit" class="fly-button fly-ok-button " @click="saveAuthClazzDeptHandler">
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


<script setup lang="ts"  name="refAuthClazzDept">
  import {reactive, ref} from "vue";
  import {FormTypeEnum} from "@/fly/biz/BizModel";
  import {AuthClazzDept} from "@/api/auth/model/AuthClazzDeptModel";
  import useTenantInfoComposable from "@/composables/auth/AuthClazzDeptComposable";

  const {
      authClazzDept,
      authClazzDeptDataHandler
  } = useTenantInfoComposable();

  const layAuthClazzDeptForm = ref<any>();

  /* 弹窗 */
  const layerRef = reactive<any>({
    open: false,
    title: '部门岗位关联表',
    area: ['500px', '550px'], // 宽度,  高度
    disabled: false,
    shadeClose: false,
    maxmin: false,
    submit: true,
  })


  /**
   * 提交
   */
  function saveAuthClazzDeptHandler() {
    layAuthClazzDeptForm.value.validate((isValidate: boolean, model: AuthClazzDept, errors: any[]) => {
      if (!isValidate) {
        return;
      }
      authClazzDeptDataHandler(model);
      // onTapClose(); // 关闭弹窗
      // console.log('数据保存', authClazzDept.value);
      //  emit('click', authClazzDept)
    });
  }


  //显示弹框
  const open = (type: FormTypeEnum, row?: AuthClazzDept) => {
    layerRef.title = type + '部门岗位关联表';
    if (null !== row) {
        authClazzDept.value = JSON.parse(JSON.stringify(row))
    } else {
        authClazzDept.value = {
              id: '' ,  // ID
              tenantId: '' ,  // 租户ID
              deptCode: '' ,  // 部门代码
              deptTitle: '' ,  // 部门名称
              deptLevel: '' ,  // 部门层级
              clazzCode: '' ,  // 岗位代码
              clazzTitle: '' ,  // 岗位名称
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