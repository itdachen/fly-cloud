<template>
  <lay-layer v-model="layerRef.open"
             :title="layerRef.title"
             :area="layerRef.area"
             :shade-close="layerRef.shadeClose"
             :maxmin="layerRef.maxmin">

    <div style="padding: 20px">
      <lay-form :model="clazzInfo" ref="layClazzInfoForm">
        <lay-form-item label="岗位层级" prop="levelCode" :required="layerRef.required">
          <lay-radio v-model="clazzInfo.levelCode"
                     v-for="item in deptLevelList"
                     :value="item.levelCode" :label="item.levelTitle"
                     name="levelCode" :disabled="layerRef.disabled"></lay-radio>
        </lay-form-item>

        <lay-form-item label="部门职能" prop="deptCode" mode="inline" :required="layerRef.required">
          <lay-select v-model="clazzInfo.deptCode" :disabled="layerRef.disabled">
            <lay-select-option v-for="item in deptFuncList" :key="item.funcCode" :value="item.funcCode">
              {{ item.funcTitle }}
            </lay-select-option>
          </lay-select>
        </lay-form-item>

        <lay-form-item label="岗位职能" prop="funcCode" mode="inline" :required="layerRef.required">
          <lay-select v-model="clazzInfo.funcCode" @change="clazzTitleHandler(clazzInfo)" :disabled="layerRef.disabled">
            <lay-select-option v-for="item in clazzFuncList" :key="item.funcCode" :value="item.funcCode">
              {{ item.funcTitle }}
            </lay-select-option>
          </lay-select>
        </lay-form-item>

        <!--        <lay-form-item label="岗位代码" prop="clazzCode" :required="layerRef.required">-->
        <!--          <lay-input v-model="clazzInfo.clazzCode" :disabled="layerRef.disabled"></lay-input>-->
        <!--        </lay-form-item>-->

        <lay-form-item label="岗位名称" prop="clazzTitle" :required="layerRef.required">
          <lay-input v-model="clazzInfo.clazzTitle" :disabled="layerRef.disabled"></lay-input>
        </lay-form-item>


        <lay-form-item label="有效标志" prop="validFlag" mode="inline">
          <lay-radio v-model="clazzInfo.validFlag" name="validFlag" value="Y" label="有效"
                     :disabled="layerRef.disabled"></lay-radio>
          <lay-radio v-model="clazzInfo.validFlag" name="validFlag" value="N" label="无效"
                     :disabled="layerRef.disabled"></lay-radio>
        </lay-form-item>

        <lay-form-item label="备注" prop="remarks">
          <lay-textarea placeholder="请输入备注" v-model="clazzInfo.remarks"
                        :disabled="layerRef.disabled"></lay-textarea>
        </lay-form-item>

      </lay-form>

    </div>


    <template v-slot:footer>
      <div class="fly-form-footer">
        <lay-button v-if="layerRef.submit" class="fly-button fly-ok-button " @click="saveClazzInfoHandler">
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


<script setup lang="ts" name="refClazzInfo">
import {onMounted, reactive, ref} from "vue";
import {FormTypeEnum} from "@/fly/biz/BizModel";
import {ClazzInfo} from "@/api/admin/model/ClazzInfoModel";
import useTenantInfoComposable from "@/composables/admin/ClazzInfoComposable";
import DeptLevelApi from "@/api/admin/DeptLevelApi";
import ClazzFuncApi from "@/api/admin/ClazzFuncApi";
import DeptFuncApi from "@/api/admin/DeptFuncApi";

const deptFuncApi = new DeptFuncApi()

const deptLevelApi = new DeptLevelApi();
const clazzFuncApi = new ClazzFuncApi()

const {
  clazzInfo,
  clazzInfoDataHandler
} = useTenantInfoComposable();

const layClazzInfoForm = ref<any>();

const deptLevelList = ref();
const clazzFuncList = ref();
const deptFuncList = ref();

/* 弹窗 */
const layerRef = reactive<any>({
  open: false,
  title: '岗位信息',
  area: ['800px', '500px'], // 宽度, 高度
  disabled: false,
  shadeClose: false,
  maxmin: false,
  submit: true,
})


/**
 * 提交
 */
function saveClazzInfoHandler() {
  layClazzInfoForm.value.validate((isValidate: boolean, model: ClazzInfo, errors: any[]) => {
    if (!isValidate) {
      return;
    }

    selectDeptHandler(model);

    clazzInfoDataHandler(model);
  });
}


const clazzTitleHandler = (model: ClazzInfo) => {
  selectDeptHandler(model);
  console.log('clazzTitleHandler', model)
  let clazzTitle = model.deptTitle + '-' + model.levelTitle + '-' + model.funcTitle;

  model.clazzTitle = clazzTitle;


}


/**
 * 生成岗位名称
 * @param model
 */
const selectDeptHandler = (model: ClazzInfo) => {
  for (let i = 0; i < deptLevelList.value.length; i++) {
    if (model.levelCode === deptLevelList.value[i].levelCode) {
      model.levelTitle = deptLevelList.value[i].levelTitle;
      break;
    }
  }

  for (let i = 0; i < clazzFuncList.value.length; i++) {
    if (model.funcCode === clazzFuncList.value[i].funcCode) {
      model.funcTitle = clazzFuncList.value[i].funcTitle;
      break;
    }
  }

  for (let i = 0; i < deptFuncList.value.length; i++) {
    if (model.deptCode === deptFuncList.value[i].deptCode) {
      model.deptTitle = deptFuncList.value[i].deptTitle;
      break;
    }
  }
}

//显示弹框
const open = (type: FormTypeEnum, row?: ClazzInfo) => {
  layerRef.title = type + '岗位信息';
  if (null !== row) {
    clazzInfo.value = JSON.parse(JSON.stringify(row))
  } else {
    clazzInfo.value = {
      id: '',  // 主键唯一标识
      clazzCode: '',  // 岗位代码
      clazzTitle: '',  // 岗位名称, 例如: 信息中心-总部-主管领导岗
      levelCode: '00',  // 岗位等级代码
      levelTitle: '',  // 岗位等级名称
      funcCode: '',  // 岗位职能: 主管领导岗, 业务岗等
      funcTitle: '',  // 岗位职能名称: 主管领导岗, 业务岗等
      validFlag: 'Y',  // 有效标志: Y-是;N-否
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


/**
 * 页面初始化, 加载数据
 */
onMounted(() => {
  deptLevelApi.findList().then(res => {
    deptLevelList.value = res.data;
  })

  clazzFuncApi.findClazzFuncList().then(res => {
    clazzFuncList.value = res.data;
  })

  deptFuncApi.findList().then(res => {
    deptFuncList.value = res.data;
  })

})


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