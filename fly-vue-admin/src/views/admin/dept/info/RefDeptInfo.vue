<template>
  <lay-layer v-model="layerRef.open"
             :title="layerRef.title"
             :area="layerRef.area"
             :shade-close="layerRef.shadeClose"
             :maxmin="layerRef.maxmin">

    <div style="padding: 20px">
      <lay-form :model="deptInfo" ref="layDeptInfoForm">
              <lay-form-item label="部门名称" prop="title" mode="inline" :required="layerRef.required" >
                <lay-input placeholder="请输入部门名称" v-model="deptInfo.title" :disabled="layerRef.disabled"></lay-input>
              </lay-form-item>

              <lay-form-item label="部门简称" prop="titleAs" mode="inline" :required="layerRef.required" >
                <lay-input placeholder="请输入部门简称" v-model="deptInfo.titleAs" :disabled="layerRef.disabled"></lay-input>
              </lay-form-item>

              <lay-form-item label="部门标志: Y-是;N-否" prop="deptFlag"   mode="inline">
                <lay-radio v-model="deptInfo.deptFlag" name="action" value="Y" label="有效" :disabled="layerRef.disabled"></lay-radio>
                <lay-radio v-model="deptInfo.deptFlag" name="action" value="N" label="无效" :disabled="layerRef.disabled"></lay-radio>
              </lay-form-item>

              <lay-form-item label="是否管理本级: Y-是;N-否" prop="thatLevel"   mode="inline">
                <lay-radio v-model="deptInfo.thatLevel" name="action" value="Y" label="有效" :disabled="layerRef.disabled"></lay-radio>
                <lay-radio v-model="deptInfo.thatLevel" name="action" value="N" label="无效" :disabled="layerRef.disabled"></lay-radio>
              </lay-form-item>

              <lay-form-item label="部门级次代码: 00-总部/10-省级/20-市州级/30-区县级/40-乡村级" prop="levelCode"  :required="layerRef.required" mode="inline">
                <lay-select v-model="deptInfo.levelCode">
                <lay-select-option value="Y" label="有效" :disabled="layerRef.disabled"></lay-select-option>
                <lay-select-option value="N" label="无效" :disabled="layerRef.disabled"></lay-select-option>
                </lay-select>
              </lay-form-item>

              <lay-form-item label="联系电话" prop="telephone" mode="inline" :required="layerRef.required" >
                <lay-input placeholder="请输入联系电话" v-model="deptInfo.telephone" :disabled="layerRef.disabled"></lay-input>
              </lay-form-item>

              <lay-form-item label="电子邮箱" prop="mailBox" mode="inline" :required="layerRef.required" >
                <lay-input placeholder="请输入电子邮箱" v-model="deptInfo.mailBox" :disabled="layerRef.disabled"></lay-input>
              </lay-form-item>

              <lay-form-item label="传真" prop="facsimile" mode="inline" :required="layerRef.required" >
                <lay-input placeholder="请输入传真" v-model="deptInfo.facsimile" :disabled="layerRef.disabled"></lay-input>
              </lay-form-item>

              <lay-form-item label="职能代码(dept_func表中 code)" prop="funcCode" mode="inline" :required="layerRef.required" >
                <lay-input placeholder="请输入职能代码(dept_func表中 code)" v-model="deptInfo.funcCode" :disabled="layerRef.disabled"></lay-input>
              </lay-form-item>

              <lay-form-item label="所属省级ID" prop="provId"  :required="layerRef.required" mode="inline">
                <lay-select v-model="deptInfo.provId">
                <lay-select-option value="Y" label="有效" :disabled="layerRef.disabled"></lay-select-option>
                <lay-select-option value="N" label="无效" :disabled="layerRef.disabled"></lay-select-option>
                </lay-select>
              </lay-form-item>

              <lay-form-item label="所属市州" prop="cityId"  :required="layerRef.required" mode="inline">
                <lay-select v-model="deptInfo.cityId">
                <lay-select-option value="Y" label="有效" :disabled="layerRef.disabled"></lay-select-option>
                <lay-select-option value="N" label="无效" :disabled="layerRef.disabled"></lay-select-option>
                </lay-select>
              </lay-form-item>

              <lay-form-item label="所属区县" prop="countyId"  :required="layerRef.required" mode="inline">
                <lay-select v-model="deptInfo.countyId">
                <lay-select-option value="Y" label="有效" :disabled="layerRef.disabled"></lay-select-option>
                <lay-select-option value="N" label="无效" :disabled="layerRef.disabled"></lay-select-option>
                </lay-select>
              </lay-form-item>

              <lay-form-item label="有效标志: Y-是;N-否" prop="validFlag"  :required="layerRef.required" mode="inline">
                <lay-radio v-model="deptInfo.validFlag" name="action" value="Y" label="有效" :disabled="layerRef.disabled"></lay-radio>
                <lay-radio v-model="deptInfo.validFlag" name="action" value="N" label="无效" :disabled="layerRef.disabled"></lay-radio>
              </lay-form-item>

              <lay-form-item label="备注" prop="remarks"  >
                <lay-textarea placeholder="请输入备注" v-model="deptInfo.remarks" :disabled="layerRef.disabled"></lay-textarea>
              </lay-form-item>

      </lay-form>

    </div>


      <template v-slot:footer>
        <div class="fly-form-footer">
          <lay-button v-if="layerRef.submit" class="fly-button fly-ok-button " @click="saveDeptInfoHandler">
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


<script setup lang="ts"  name="refDeptInfo">
  import {reactive, ref} from "vue";
  import {FormTypeEnum} from "@/fly/biz/BizModel";
  import {DeptInfo} from "@/api/admin/model/DeptInfoModel";
  import useTenantInfoComposable from "@/composables/admin/DeptInfoComposable";

  const {
      deptInfo,
      deptInfoDataHandler
  } = useTenantInfoComposable();

  const layDeptInfoForm = ref<any>();

  /* 弹窗 */
  const layerRef = reactive<any>({
    open: false,
    title: '部门信息',
    area: ['500px', '550px'], // 高度, 宽度
    disabled: false,
    shadeClose: false,
    maxmin: false,
    submit: true,
  })


  /**
   * 提交
   */
  function saveDeptInfoHandler() {
    layDeptInfoForm.value.validate((isValidate: boolean, model: DeptInfo, errors: any[]) => {
      if (!isValidate) {
        return;
      }
      deptInfoDataHandler(model);
      onTapClose();
      // console.log('数据保存', deptInfo.value);
      //  emit('click', deptInfo)
    });
  }


  //显示弹框
  const open = (type: FormTypeEnum, row?: DeptInfo) => {
    layerRef.title = type + '部门信息';
    if (null !== row) {
        deptInfo.value = JSON.parse(JSON.stringify(row))
    } else {
        deptInfo.value = {
              id: '' ,  // 主键唯一标识
              parentId: '' ,  // 上级ID
              title: '' ,  // 部门名称
              titleAs: '' ,  // 部门简称
              deptFlag: '' ,  // 部门标志: Y-是;N-否
              thatLevel: '' ,  // 是否管理本级: Y-是;N-否
              levelCode: '' ,  // 部门级次代码: 00-总部/10-省级/20-市州级/30-区县级/40-乡村级
              levelTitle: '' ,  // 部门级次名称
              telephone: '' ,  // 联系电话
              mailBox: '' ,  // 电子邮箱
              facsimile: '' ,  // 传真
              funcCode: '' ,  // 职能代码(dept_func表中 code)
              funcTitle: '' ,  // 职能名称(dept_func表中title)
              provId: '' ,  // 所属省级ID
              provTitle: '' ,  // 所属省级名称
              cityId: '' ,  // 所属市州
              cityTitle: '' ,  // 所属市州名称
              countyId: '' ,  // 所属区县
              countyTitle: '' ,  // 所属区县名称
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