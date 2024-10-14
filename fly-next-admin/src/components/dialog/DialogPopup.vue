<template>
  <el-dialog :title="title"
             top="10vh"
             :model-value="visible"
             :width="width + 'px'"
             :before-close="onClose"
             :append-to-body="true"
             :close-on-click-modal="false"
             class="fly-el-dialog">
    <div class="fly-dialog-content">
      <slot name="content"></slot>
    </div>
    <template #footer>
      <div class="fly-dialog-footer">
        <div class="dialog-footer-button">
          <el-button class="fly-button fly-ok-button" v-if="showSubmit" @click="onSubmit">
            <el-icon style="margin-right: 5px">
              <ele-Check/>
            </el-icon>
            提交
          </el-button>
          <el-button class="fly-button fly-close-button" v-if="showClose" @click="onClose">
            <el-icon style="margin-right: 5px">
              <ele-Close/>
            </el-icon>
            取消
          </el-button>
        </div>
      </div>
    </template>

  </el-dialog>
</template>
<script setup lang="ts">
const props = defineProps({
  title: {
    type: String,
    default: '表单',
  },
  visible: {
    type: Boolean,
    default: false,
  },
  width: {
    type: Number,
    default: 55,
  },
  height: {
    type: Number,
    default: 75,
  },
  showSubmit: {
    type: Boolean,
    default: true,
  },
  showClose: {
    type: Boolean,
    default: true,
  }
})

let emits = defineEmits(['submit', 'close'])

// 提交
const onSubmit = () => {
  emits('submit')
}

// 取消
const onClose = () => {
  emits('close')
}

</script>
<style>

.el-dialog__footer {
  height: 60px;
  line-height: 30px;
  padding-top: 0;
}

.el-dialog__header {
  margin: 0;
  background-color: #ffffff;
  /*  width: 100%;
    padding: 0 30px;
    height: 5vh;
    line-height: 5vh;
    border-radius: 5px 5px 0 0;
    border-bottom: 1px #ebeef5 solid;*/
}


.fly-el-dialog {
  background-color: #FFFFFF;
}

.fly-dialog-content {
  overflow-x: initial;
  overflow: auto;
  background-color: #FFFFFF;
  /*  margin-bottom: 30px;*/
}


.fly-dialog-footer {
  border-top: 1px #ebeef5 solid;
  width: 100%;
  overflow: auto;
  position: absolute;
  /*  bottom: 20px;*/
  margin-left: -16px;
}

.dialog-footer-button {
  margin-top: 15px;
  margin-right: 30px;
  width: 200px;
  float: right;
}

</style>
