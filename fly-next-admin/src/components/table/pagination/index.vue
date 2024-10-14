<template>
  <el-pagination :currentPage="page"
                 :page-size="limit"
                 :total="total"
                 :page-sizes="[10, 25, 50, 100]"
                 @size-change="handleSizeChange"
                 @current-change="handleCurrentChange"
                 layout="total, sizes, prev, pager, next, jumper"
                 prev-text="上一页"
                 next-text="下一页"></el-pagination>
</template>

<script setup lang="ts" name="pagination">

import {ref} from "vue";

const props = defineProps({
  /* 总条数 */
  total: {
    type: Number,
    default: 0
  },
  /*当前显示的第几页*/
  currentPage: {
    type: Number,
    default: 1
  },
  /* 每页显示条目个数 */
  pageSize: {
    type: Number,
    default: 10
  },
  /* 是否显示背景色 */
  background: {
    type: Boolean,
    default: false
  },
})


const page = ref(props.currentPage);
const limit = ref(props.pageSize);

let emits = defineEmits(['change']);


/**
 * 页面数据修改
 * @param page  当前多少页
 * @param limit 当前页多少条数据
 */
const pageLimitChange = (page: number, limit: number) => {
  // console.log(`pageLimitChange, page: ${page}, limit: ${limit}`)
  // pageChangeHandler
  emits('change', page, limit)
}


/**
 * 修改每页展示多少条
 * @param val 需要展示的条数
 */
const handleSizeChange = (val: number) => {
  page.value = 1;
  limit.value = val;
  pageLimitChange(page.value, val);
}

/**
 * 上一页/下一页 按钮 previousNextPage
 * @param val 第多少页
 */
const handleCurrentChange = (val: number) => {
  page.value = val;
  pageLimitChange(val, limit.value);
}

</script>


<style scoped>
.table-box .el-pagination {
  justify-content: flex-start;
}
</style>