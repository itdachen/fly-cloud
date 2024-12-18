<template>
  <lay-layer :title="layerRef.title" :area="layerRef.area" v-model="layerRef.open">
    <div style="padding: 20px">
      <lay-form :model="model11" ref="layLayerForm" required>
        <lay-form-item label="姓名" prop="name">
          <lay-input v-model="model11.name" :disabled="layerRef.disabled"></lay-input>
        </lay-form-item>
        <lay-form-item label="年龄" prop="age">
          <lay-input v-model="model11.age" :disabled="layerRef.disabled"></lay-input>
        </lay-form-item>
        <lay-form-item label="性别" prop="sex">
          <lay-select v-model="model11.sex" style="width: 100%" :disabled="layerRef.disabled">
            <lay-select-option value="男" label="男"></lay-select-option>
            <lay-select-option value="女" label="女"></lay-select-option>
          </lay-select>
        </lay-form-item>
        <lay-form-item label="城市" prop="city">
          <lay-input v-model="model11.city" :disabled="layerRef.disabled"></lay-input>
        </lay-form-item>
        <lay-form-item label="email" prop="email">
          <lay-input v-model="model11.email" :disabled="layerRef.disabled"></lay-input>
        </lay-form-item>
        <lay-form-item label="描述" prop="remark">
          <lay-textarea placeholder="请输入描述" v-model="model11.remark" :disabled="layerRef.disabled"></lay-textarea>
        </lay-form-item>
      </lay-form>
      <div style="width: 100%; text-align: right">
        <lay-button v-if="showToSubmit" size="sm" class="fly-button fly-ok-button" @click="toSubmit">
          <lay-icon class="layui-icon-ok"></lay-icon>
          保存
        </lay-button>
        <lay-button size="sm" class="fly-button fly-close-button" @click="onTapClose">
          <lay-icon class="layui-icon-close"></lay-icon>
          取消
        </lay-button>
      </div>
    </div>
  </lay-layer>
</template>


<script setup lang="ts">
import {reactive, ref} from "vue";
import {layer} from "@layui/layui-vue";

const showToSubmit = ref<any>(true);

/* 弹窗 */
const layerRef = reactive<any>({
  open: false,
  title: '',
  area: ['500px', '550px'],
  disabled: false
})

const layLayerForm = ref<any>();

const model11 = ref<any>({});

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
  console.log('数据保存', model11.value)
  // layer.msg('保存成功！', {icon: 1, time: 1000});
  // layerRef.open = false;
  emit('click', model11)
}


//显示弹框
const open = (type: string, title: string, row?: any) => {
  console.log('type: ', type);
  console.log('row: ', row);

  layLayerForm.value?.resetFields();

  if (null != row) {
    model11.value = JSON.parse(JSON.stringify(row))
  }

  if ('查看' === type) {
    layerRef.disabled = true;
    showToSubmit.value = false;
  }

  layerRef.open = true;
  layerRef.title = type + title;


  ///设置弹框的宽度
  // dialog.width = 650;
  // dialog.height = 250;
  // //设置弹框的标题
  // type == EditType.ADD
  //     ? (dialog.title = Title.ADD)
  //     : (dialog.title = Title.EDIT);
  // //显示
  // onShow();
  // //清空表单
  // //   global.$resetForm(addDeptForm.value, dialogModel);
  // nextTick(() => {
  //   if (EditType.EDIT === type) {
  //     //把要编辑的数据，放到表单绑定的model里面
  //     global.$objCoppy(row, dialogModel);
  //     let info = JSON.parse(JSON.stringify(row))
  //     model11.value = info
  //   }
  // });
  // layLayerForm.value?.resetFields();
  // //设置编辑属性
  // dialogModel.type = type;
};

//在组件中，属性和方法，只能对当前template,
//外部需要使用的时候，需要使用 defineExpose 暴露出去给父组件
defineExpose({
  open,
});

//注册事件
const emit = defineEmits(['click']);

/**************************************************************************************************************/

const visible11 = ref(true);


// const title = ref('新增');

const layFormRef11 = ref();


function toCancel() {
  visible11.value = false
}

// 清除校验
const clearValidate11 = function () {
  layFormRef11.value.clearValidate()
}
// 重置表单
const reset11 = function () {
  layFormRef11.value.reset()
}


// const props = defineProps({
//   title: {
//     type: String,
//     default: '标题',
//   },
//   area: {
//     type: Array,
//     default: ['500px', '550px']
//   },
//   open: {
//     type: Boolean,
//     default: ref(false)
//   }
// })


</script>


<style scoped>
.fly-button {
  height: 33px;
  line-height: 33px;
  width: auto;
  border-radius: 3px;
//margin-top: 5px; font-weight: 400; //color: #ffffff; padding: 0 12px 0 10px; font-size: 16px; text-align: center; //transition: transform 0.3s ease; /* 平滑变化 */
}

.fly-button:hover {
//transform: scale(1.1); /* 放大10% */
}

.fly-button i {
  font-size: 18px !important;
//margin-right: 2px;
}

</style>