<script setup lang="ts" name="FlyPopup">

/* 组件使用参数 */
const props = defineProps({
  /* 弹窗标题 */
  title: {
    type: String,
    default: '标题',
  },
  /* 是否展开弹窗 */
  open: {
    type: Boolean,
    default: false,
  },
  /* 弹窗大小: 宽度/高度 */
  area: {
    type: Array,
    default: ['80%', '60%'],
  },
  /* 是否展示提交按钮 */
  showSubmit: {
    type: Boolean,
    default: true,
  },
  /* 是否展示关闭按钮 */
  showClose: {
    type: Boolean,
    default: true,
  },
  maxmin: {
    type: Boolean,
    default: true,
  }
})

const emits = defineEmits(['submit', 'close'])

/**
 * 提交
 */
const onTapSubmit = () => {
  emits('submit')
}

/**
 * 关闭
 */
const onTapClose = () => {
  emits('close')
}


</script>

<template>

  <lay-layer v-bind="open"
             :title="title"
             :area="area"
             :maxmin="maxmin">

    <div style="padding: 20px;">
      <slot name="content"></slot>
    </div>


    <template v-if="!showSubmit && !showClose" v-slot:footer>
      <div class="fly-form-footer">
        <lay-button v-if="showSubmit" class="fly-button fly-ok-button " @click="onTapSubmit">
          <lay-icon class="layui-icon-ok"></lay-icon>
          保存
        </lay-button>
        <lay-button v-if="showClose" class="fly-button fly-close-button" @click="onTapClose">
          <lay-icon class="layui-icon-close"></lay-icon>
          取消
        </lay-button>
      </div>
    </template>

  </lay-layer>
</template>

<style scoped>

</style>