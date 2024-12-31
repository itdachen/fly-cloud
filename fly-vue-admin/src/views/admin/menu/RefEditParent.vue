<template>
  <lay-layer v-model="layerRef.open"
             :title="layerRef.title"
             :area="layerRef.area"
             :shade-close="layerRef.shadeClose"
             :maxmin="layerRef.maxmin">

    <div style="padding: 20px">
      <!--  菜单树    -->
      <div class="fly-tree-container" style="width: 300px; ">
        <lay-tree :data="menuTreeData"
                  :showLine="false"
                  @node-click="menuNodeClick"
                  style="width: 300px; ">
        </lay-tree>
      </div>

    </div>


<!--    <template v-slot:footer>-->
<!--      <div class="fly-form-footer">-->
<!--        <lay-button v-if="layerRef.submit" class="fly-button fly-ok-button " @click="saveParentMenuHandler">-->
<!--          <lay-icon class="layui-icon-ok"></lay-icon>-->
<!--          保存-->
<!--        </lay-button>-->
<!--        <lay-button class="fly-button fly-close-button" @click="onTapClose">-->
<!--          <lay-icon class="layui-icon-close"></lay-icon>-->
<!--          关闭-->
<!--        </lay-button>-->
<!--      </div>-->
<!--    </template>-->


  </lay-layer>
</template>


<script setup lang="ts" name="refEditParentComponent">
import {onMounted, reactive, ref} from "vue";
import useMenuInfoComposable from "@/composables/admin/MenuInfoComposable";


const {
  menuTreeData,
  loadMenuTree
} = useMenuInfoComposable();

/* 弹窗 */
const layerRef = reactive<any>({
  open: false,
  title: '资源(按钮)信息',
  area: ['350px', '550px'], // 宽度,  高度
  disabled: false,
  shadeClose: false,
  maxmin: false,
  submit: true,
})

const menuId = ref('');
const menuTitle = ref('');


/**
 * 提交
 */
function saveParentMenuHandler() {
  // onTapClose(); // 关闭弹窗
  // console.log('数据保存', elementInfo.value);
  emit('click', menuId.value, menuTitle.value)
}

onMounted(() => {
  loadMenuTree();
})


//显示弹框
const open = () => {
  layerRef.open = true;
};


/**
 * 关闭按钮
 */
const onTapClose = () => {
  layerRef.open = false;
}

/**
 * 菜单树点击
 * @param obj
 */
const menuNodeClick = (obj: any) => {
  onTapClose();
  emit('click', obj.id, obj.title);
}


//在组件中，属性和方法，只能对当前template,
//外部需要使用的时候，需要使用 defineExpose 暴露出去给父组件
defineExpose({
  open,
  onTapClose
});

//注册事件
const emit = defineEmits(['click']);

</script>


<style scoped>

</style>