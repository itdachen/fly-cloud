<script setup lang="ts">
import {ref} from "vue";

const props = defineProps({
  currentPage: {
    type: Number,
    default: 1,
  },
  pageSize: {
    type: Number,
    default: 10,
  },
  background: {
    type: Boolean,
    default: false,
  },
  disabled: {
    type: Boolean,
    default: false,
  },
  hideOnSinglePage: {
    type: Boolean,
    default: false,
  },
  total: {
    type: Number,
    default: 0,
    required: true,
  },
  layout: {
    type: String,
    default: 'total, sizes, prev, pager, next, jumper',
  },
  prevText: {
    type: String,
    default: '上一页',
  },
  nextText: {
    type: String,
    default: '上一页',
  },
  pageSizes: {
    type: Array,
    default() {
      return [10, 20, 50, 100, 300, 400]
    }
  }
})

const page = ref(props.currentPage)
const limit = ref(props.pageSize)

/* 改变分页查询条数 */
const handleSizeChange = (val) => {
  page.value = 1;
  limit.value = val;
  changeHandler(page.value, limit.value);
}

/* 上一页/下一页 */
const handleCurrentChange = (val) => {
  page.value = val;
  changeHandler(page.value, limit.value);
}

const changeHandler = (page: number, limit: number) => {
  // console.log(`page: ${page}, limit: ${limit}`);
  emits('change', {page: page, limit: limit})
}

let emits = defineEmits(['change'])


</script>

<template>
  <el-pagination style="margin-top: 20px"
                 v-model:current-page="page"
                 v-model:page-size="limit"
                 :page-sizes="pageSizes"
                 :disabled="disabled"
                 :background="background"
                 :layout="layout"
                 :total="total"
                 :prev-text="prevText"
                 :next-text="nextText"
                 :hide-on-single-page="hideOnSinglePage"
                 @size-change="handleSizeChange"
                 @current-change="handleCurrentChange"></el-pagination>
</template>

<style scoped lang="scss">

</style>