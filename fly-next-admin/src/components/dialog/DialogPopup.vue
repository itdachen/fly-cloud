<template>
  <div class="Dailog">
    <el-dialog :title="title"
               top="10vh"
               :model-value="visible"
               :before-close="onClose"
               :append-to-body="true"
               :close-on-click-modal="false"
               :width="width + 'vw'">
      <el-scrollbar :style="{height:height + 'vh'}">
        <div class="container">
          <slot name="content"></slot>
        </div>
      </el-scrollbar>
      <div class="dialog-footer">
        <el-button class="Close" v-if="showSubmit" @click="onSubmit">提交</el-button>
        <el-button class="Confirm" v-if="showClose" @click="onClose">取消</el-button>
      </div>
    </el-dialog>
  </div>
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

let emits = defineEmits(['submit', 'onClose'])

// 提交
const onSubmit = () => {
  emits('submit')
}

// 取消
const onClose = () => {
  emits('onClose')
}

</script>
<style>
.container {
  overflow-x: initial;
  overflow: auto;
  padding-bottom: 100px;
}


.el-dialog {
  padding: 0;
  background-color: #F1ECE7;
  border-radius: 1rem;
  position: relative;
  top: 0;
  bottom: 0;
}

.el-dialog__header {
  width: 94%;
  padding: 0 3%;
  margin: 0;
  height: 6vh;
  line-height: 6vh;
  background-color: #FAF7F4;
  border-radius: 1rem 1rem 0 0;
}

.el-dialog__title {
  font-size: 14px;
  font-family: PingFang SC-Medium, PingFang SC;
  font-weight: 600;
  color: #2F3050;
}

.el-dialog__headerbtn {
  width: 28px;
  height: 28px;
  right: 3%;
  top: 15px;
}

.el-dialog__headerbtn:hover {
  background-color: #F1ECE7;
  color: #2F3050;
  border-radius: 4px;
}

.el-icon {
  font-size: 14px;
}

.el-icon:hover {
  color: #2F3050;
}

.el-dialog__body {
  width: 100%;
  /*height: 74vh;*/
  margin: 0 auto;
  padding: 0;
}

.dialog-footer {
  width: 300px;
  overflow: auto;
  position: absolute;
  left: 50%;
  margin-left: -150px;
  bottom: 20px;
}

.Close {
  width: 120px;
  height: 48px;
  line-height: 48px;
  color: #fff;
  background: #36375B;
  border-radius: 10px 10px 10px 10px;
  margin-right: 20px;
}

.Confirm {
  width: 120px;
  height: 48px;
  line-height: 48px;
  color: #36375B;
  background: #EDE2D8;
  border-radius: 10px 10px 10px 10px;
}

.Close:focus, .Close:hover {
  background-color: #2F3050;
  color: #FFFFFF;
}

.Confirm:focus, .Confirm:hover {
  background-color: #EDE2D8;
  color: #36375B;
}
</style>
