<template>
  <div class="table-box">
    <!-- 表格头部 操作按钮 -->
    <div class="table-header" style="display: flex;">
      <div class="header-button-lf" style="width: 100%;">
        <slot name="tableHeader" :ids="selectedListIds" :isSelected="isSelected"></slot>
      </div>
      <div class="header-button-ri" v-if="toolButton" style="width: 100px; line-height: 50px!important;">
        <el-button round size="mini" :icon="Refresh" circle @click="reloadDate"></el-button>
        <el-button round size="mini" :icon="Operation" circle @click="openColSetting"></el-button>
      </div>
    </div>

    <el-table height="600"
              fit="true"
              ref="tableRef"
              :data="data.rows"
              :border="border"
              :default-expand-all="expandAll"
              :row-key="rowKey"
              :stripe="stripe"
              @selection-change="selectionChange"
              :tree-props="{ children: childrenName }">

      <template v-for="item in tableColumns" :key="item">
        <!-- selection || index -->
        <el-table-column v-if="item.type === 'selection' || item.type === 'index'"
                         :type="item.type"
                         :reserve-selection="item.type === 'selection'"
                         :label="item.label"
                         :width="item.width"
                         :align="item.align"
                         :fixed="item.fixed">
        </el-table-column>
        <!-- expand（展开查看详情，请使用作用域插槽） -->
        <el-table-column v-if="item.type === 'expand'"
                         :type="item.type"
                         :label="item.label"
                         :width="item.width"
                         :align="item.align"
                         :fixed="item.fixed"
                         v-slot="scope">
          <slot :name="item.type" :row="scope.row"></slot>
        </el-table-column>
        <!-- other -->
        <el-table-column v-if="!item.type && item.prop && item.isShow"
                         :prop="item.prop"
                         :label="item.label"
                         :width="item.width"
                         :align="item.align"
                         :sortable="item.sortable"
                         :show-overflow-tooltip="item.prop !== 'operation'"
                         :resizable="true"
                         :fixed="item.fixed">
          <!-- 自定义 header (使用组件渲染 tsx 语法) -->
          <template #header v-if="item.renderHeader">
            <component :is="item.renderHeader" :row="item"></component>
          </template>

          <!-- 自定义配置每一列 slot（使用作用域插槽） -->
          <template #default="scope">
            <slot :name="item.prop" :row="scope.row">
              <!-- 图片(自带预览) -->
              <el-image v-if="item.image"
                        :src="scope.row[item.prop!]"
                        :preview-src-list="[scope.row[item.prop!]]"
                        fit="cover"
                        class="table-image"
                        preview-teleported/>
              <!-- tag 标签（自带格式化内容） -->
              <el-tag v-else-if="item.tag" :type="filterEnum(scope.row[item.prop!],item.enum,'tag')">
                {{
                  item.enum?.length ? filterEnum(scope.row[item.prop!], item.enum) : defaultFormat(0, 0, scope.row[item.prop!])
                }}
              </el-tag>
              <!-- 文字（自带格式化内容） -->
              <span v-else>
								{{
                  item.enum?.length ? filterEnum(scope.row[item.prop!], item.enum) : defaultFormat(0, 0, scope.row[item.prop!])
                }}
							</span>
            </slot>
          </template>

        </el-table-column>
      </template>
    </el-table>

    <!--  分页  -->
    <pagination style="margin-top: 15px;"
                v-if="pagination"
                :currentPage="currentPage"
                :total="data.total"
                :pageSize="pageSize"
                @handleSizeChange="handleSizeChange"
                @handleCurrentChange="handleCurrentChange"></pagination>

    <!-- 列设置 -->
    <ColSetting v-if="toolButton" ref="colRef" :tableRef="tableRef" :colSetting="colSetting"></ColSetting>

  </div>
</template>

<script setup lang="ts" name="proTable">
import {ref, computed} from "vue";
import Pagination from './pagination/index.vue';
import ColSetting from './coll/ColSetting.vue';
import {ColumnProps} from "/@/components/table/interface";
import {filterEnum, defaultFormat} from "/@/utils/util";
import {Refresh, Operation} from "@element-plus/icons-vue";

const currentPage = ref(1);

const pageSize = ref(10)

// 是否选中数据
const isSelected = ref<boolean>(false);
// 选中的数据列表
const selectedList = ref([]);
// 当前选中的所有ids(数组)
const selectedListIds = computed((): string[] => {
  let ids: string[] = [];
  selectedList.value.forEach(item => {
    ids.push(item["id"]);
  });
  return ids;
});

const selectionChange = (rowArr: any) => {
  rowArr.length === 0 ? (isSelected.value = false) : (isSelected.value = true);
  selectedList.value = rowArr;
};

interface ProTableProps {
  columns: Partial<ColumnProps>[]; // 列配置项
  data?: any; // 表格展示数据
  border?: boolean; // 表格是否显示边框 ==> 非必传（默认为true）
  stripe?: boolean; // 是否带斑马纹表格 ==> 非必传（默认为true）
  childrenName?: string; // 当数据存在 children 时，指定 children key 名字 ==> 非必传（默认为"children"）
  pagination?: boolean; // 是否展示分页, 默认展示
  toolButton?: boolean; // 是否显示表格功能按钮 ==> 非必传（默认为true）
  rowKey?: string;
  expandAll?: boolean; // 树结构是否展开全部
}


// 接受父组件参数，配置默认值
const props = withDefaults(defineProps<ProTableProps>(), {
  columns: () => [],
  data: {
    type: Object,
    default: {
      total: 0,
      rows: []
    }
  },
  // 表格是否显示边框 ==> 非必传（默认为false）
  border: false,
  /* 是否带斑马纹表格 ==> 非必传（默认为true） */
  stripe: true,
  /* 是否展示分页, 默认展示 */
  pagination: true,
  /* 当数据存在 children 时，指定 children key 名字 ==> 非必传（默认为"children"） */
  childrenName: 'children',
  /* 是否显示表格功能按钮 ==> 非必传（默认为true） */
  toolButton: true,
  rowKey: 'id',
  expandAll: false
});

/**
 * 修改每页展示多少条
 * @param val 需要展示的条数
 */
const handleSizeChange = (val: number) => {
  pageSize.value = val;
  currentPage.value = 1;
  searchFunction(currentPage.value, val);
}

/**
 * 上一页/下一页 按钮
 * @param val 第多少页
 */
const handleCurrentChange = (val: number) => {
  currentPage.value = val;
  searchFunction(val, pageSize.value);
}

/* 重载数据 */
const reloadDate = () => {
  currentPage.value = 1;
  searchFunction(currentPage.value, pageSize.value)
}

const emits = defineEmits(['reload'])

/**
 * 重新加载数据
 * @param page   当前页
 * @param limit  当前页展示多少条数据
 */
const searchFunction = (page: number = 1, limit: number = 10) => {
  emits('reload', page, limit);
}


// 表格 DOM 元素
const tableRef = ref();

// 表格列配置项处理（添加 isShow 属性，控制显示/隐藏）
const tableColumns = ref<Partial<ColumnProps>[]>();
tableColumns.value = props.columns.map(item => {
  return {
    ...item,
    isShow: true
  };
});

// * 列设置
const colRef = ref();
// 过滤掉不需要设置显隐的列
const colSetting = tableColumns.value.filter((item: Partial<ColumnProps>) => {
  return item.type !== "selection" && item.type !== "index" && item.type !== "expand";
});
const openColSetting = () => {
  colRef.value.openColSetting();
};

// 过滤需要搜索的配置项
const searchColumns = props.columns.filter(item => item.search);

</script>

<style lang="scss" scoped>
.table-box .el-table {
  flex: none;
}

.header-button-lf .el-form--inline .el-form-item {
  vertical-align: super;
  margin-bottom: 0 !important;
}

/* 表头颜色, 但是没有生效 */
.el-table th.el-table__cell > .cell {
  color: #909399 !important;
}

</style>
