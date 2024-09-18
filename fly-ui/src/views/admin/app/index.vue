<template>

  <lay-container fluid="true" class="fly-container">
    <lay-card class="fly-search-card">
      <lay-form>
        <lay-form-item label="应用名称" prop="appTitle" mode="inline">
          <lay-input v-model="queryAppInfoParams.appTitle"></lay-input>
        </lay-form-item>
        <lay-form-item label="有效标志" prop="validFlag" mode="inline">
          <lay-input v-model="queryAppInfoParams.validFlag"></lay-input>
        </lay-form-item>

        <lay-form-item mode="inline">
          <lay-button class="fly-button fly-button-search" style="margin-left: 20px" type="primary" size="sm"
                      @click=" refreshData ">
            <lay-icon class="layui-icon-search"></lay-icon>
            查询
          </lay-button>
          <lay-button class="fly-button fly-button-reset" size="sm">
            <lay-icon class="layui-icon-refresh"></lay-icon>
            重置
          </lay-button>
          <lay-button class="fly-button fly-button-exp" size="sm" @click="">
            <lay-icon class="layui-icon-export"></lay-icon>
            导出
          </lay-button>
        </lay-form-item>
      </lay-form>

    </lay-card>

    <!-- :skin="'nob'"  :height="'90%'" -->
    <div class="table-box">
      <lay-table :page="false"
                 :defaultToolbar="true"
                 :skin="'line'"
                 :even="true"
                 :columns="appInfoColumns"
                 :loading="tableAppInfoData.loading"
                 :data-source="tableAppInfoData.rows"
                 @change=" reloadAppInfoDate ">

        <template v-slot:toolbar>
          <lay-button size="sm" type="primary" class="fly-button fly-toolbar-addition" @click=" onTapAppInfoAdd ">
            <lay-icon class="layui-icon-addition"></lay-icon>
            新增
          </lay-button>
        </template>

        <template v-slot:operator="{ row }">
          <lay-button size="sm" type="primary" class="fly-button fly-view-button" @click=" onTapAppInfoView(row) ">
            <lay-icon class="layui-icon-list"></lay-icon>
            查看
          </lay-button>
          <lay-button size="sm" type="primary" class="fly-button fly-edit-button" @click=" onTapAppInfoEdit(row) ">
            <lay-icon class="layui-icon-edit"></lay-icon>
            编辑
          </lay-button>

          <lay-button size="sm" type="danger" class="fly-button fly-remove-button"
                      @click=" removeAppInfoHandler(row.id, row.appTitle) ">
            <lay-icon class="layui-icon-delete"></lay-icon>
            删除
          </lay-button>

        </template>
      </lay-table>


      <lay-page v-model="layPage.page"
                :layout="layPage.layout"
                :pages="layPage.pages"
                :limits="layPage.limits"
                v-model:limit="layPage.limit"
                theme="blue"
                :total="tableAppInfoData.total"
                @change="previousNextPage" style="margin-top: 15px"></lay-page>


    </div>
  </lay-container>


  <!-- <AddAndEdit ref="refAppInfo" @click="refreshData"/>-->

</template>


<script setup lang="ts" name="appInfoComponent">
import {onMounted, reactive, ref} from "vue";

/**
 * 页面初始化, 加载数据
 */
onMounted(() => {
  reloadAppInfoDate(1, 10);
})

/**
 * 重新加载数据
 * @author 王大宸
 * @param page
 * @param limit
 */
const reloadAppInfoDate = (page: number = 1, limit: number = 10) => {
  queryAppInfoParams.page = page;
  queryAppInfoParams.limit = limit;
  loadTableAppInfoData(queryAppInfoParams);
};


/**
 * 上一页, 下一页
 * @param current
 * @param limit
 */
const previousNextPage = ({current = 1, limit = 10}) => {
  queryAppInfoParams.page = current;
  queryAppInfoParams.limit = limit;
  loadTableAppInfoData(queryAppInfoParams);
}

/**
 * 刷新数据
 */
const refreshData = () => {
  queryAppInfoParams.page = 1;
  loadTableAppInfoData(queryAppInfoParams);
}

/********************************************************************************************/


/**
 * 新增按钮
 */
const onTapAppInfoAdd = () => {
  console.log('onTapAppInfoAdd')
}

/**
 * 编辑按钮
 * @param data 编辑时的数据信息
 */
const onTapAppInfoEdit = (data: AppInfo) => {
  console.log('onTapAppInfoEdit', data)
}

/**
 * 查看按钮
 * @param data 查看时的数据信息
 */
const onTapAppInfoView = (data: AppInfo) => {
  console.log('onTapAppInfoView', data)
}

/**
 * 删除按钮
 * @param id 需要删除的数据主键
 * @param title
 */
const removeAppInfoHandler = (id: string, title?: string) => {
  console.log('id: ', id)
}


/**
 * 加载分页数据
 * @author 王大宸
 * @param params
 */
const loadTableAppInfoData = (params: AppInfoQuery) => {
  tableAppInfoData.total = 16
  tableAppInfoData.rows = [];
  for (let i = 0; i < 10; i++) {
    tableAppInfoData.rows.push({
      appAsTitle: "1NNNNNNNNNNNNNNNNNNNN",
      appCode: "HGGGGGGG",
      appSecret: "1RRRRRRRRRRRRRRRRRRRRR",
      appTitle: "NNNNNNNNNNNNNNNNN",
      appType: "1",
      askUri: "1",
      funcCode: "1",
      funcTitle: "1",
      iconIco: "1",
      id: (i + 1) + '',
      platId: "12333333333333333222222222222222",
      platTitle: "112312321331",
      remarks: "1",
      typeCode: "1",
      typeTitle: "1",
      validDel: "N",
      validFlag: "Y",
    })
  }
};


/**
 * 分页组件基础参数
 */
const layPage = reactive<LayPage>({
  /* 默认分页页面 */
  page: 1,
  /* 每页查询条数 */
  limit: 10,
  /* 可选择的查询额页面条数 */
  limits: [10, 30, 50, 100, 200],
  /* 导航 */
  layout: ['prev', 'page', 'next', 'limits', 'count', 'refresh', 'skip'],
  pages: 7
})


/**
 * 分页数据
 */
const tableAppInfoData = reactive<TableData<AppInfo>>({
  loading: false, /* 是否显示加载框 */
  total: 0, /* 数据总条数 */
  rows: [], /* 展示的数据 */
});


/**
 * 实例化查询数据对象
 */
const queryAppInfoParams = reactive({
  page: 1,
  limit: 10,
  /** 应用名称 */
  appTitle: '',
  /** 有效标志: Y-是;N-否 */
  validFlag: ''
});


/**
 * 实例化对象
 */
const appInfo = reactive<AppInfo>({
  /** appID */
  id: '',
  /** 平台ID */
  platId: '',
  /** 平台名称 */
  platTitle: '',
  /** app秘钥 */
  appSecret: '',
  /** 应用标识 */
  appCode: '',
  /** 应用名称 */
  appTitle: '',
  /** 应用名称简称 */
  appAsTitle: '',
  /** 应用类型: BACK-后端;VIEW-前端 */
  appType: '',
  /** 应用类型 */
  typeCode: '',
  /** 应用类型标题 */
  typeTitle: '',
  /** 访问地址 */
  askUri: '',
  /** 图标 */
  iconIco: '',
  /** 职能代码 */
  funcCode: '',
  /** 职能名称 */
  funcTitle: '',
  /** 是否可删除: Y-是;N-否 */
  validDel: '',
  /** 有效标志: Y-是;N-否 */
  validFlag: '',
  /** 备注 */
  remarks: ''
});

/**
 * 分页列表展示项
 */
const appInfoColumns = [
  {title: '平台名称', key: 'platTitle', ellipsisTooltip: true, align: 'center'},
  {title: '应用标识', key: 'appCode', ellipsisTooltip: true, align: 'center'},
  {title: '应用名称', key: 'appTitle', ellipsisTooltip: true, align: 'center'},
  {title: '应用类型标题', key: 'typeTitle', ellipsisTooltip: true, align: 'center'},
  {title: '访问地址', key: 'askUri', ellipsisTooltip: true, align: 'center'},
  {title: '图标', key: 'iconIco', ellipsisTooltip: true, align: 'center'},
  {title: '有效标志', key: 'validFlag', ellipsisTooltip: true, align: 'center'},
  {
    title: '操作',
    width: '280px',
    customSlot: 'operator',
    key: 'operator',
    align: 'center',
    fixed: 'right',
    ignoreExport: true
  }
];

/**
 *  新增/修改/查看 弹窗
 */
const refAppInfo = ref();

/**
 * 应用信息 查询参数
 *
 * @author 王大宸
 * @date 2024-09-05 10:00:59
 */
export interface AppInfoQuery extends BizQuery {
  /** 应用名称 */
  appTitle?: string,
  /** 有效标志: Y-是;N-否 */
  validFlag?: string,
}


/**
 * 应用信息 向后端传值对象
 *
 * @author 王大宸
 * @date 2024-09-05 10:00:59
 */
export interface AppInfo {
  /** appID */
  id?: string,
  /** 平台ID */
  platId?: string,
  /** 平台名称 */
  platTitle?: string,
  /** app秘钥 */
  appSecret?: string,
  /** 应用标识 */
  appCode?: string,
  /** 应用名称 */
  appTitle?: string,
  /** 应用名称简称 */
  appAsTitle?: string,
  /** 应用类型: BACK-后端;VIEW-前端 */
  appType?: string,
  /** 应用类型 */
  typeCode?: string,
  /** 应用类型标题 */
  typeTitle?: string,
  /** 访问地址 */
  askUri?: string,
  /** 图标 */
  iconIco?: string,
  /** 职能代码 */
  funcCode?: string,
  /** 职能名称 */
  funcTitle?: string,
  /** 是否可删除: Y-是;N-否 */
  validDel?: string,
  /** 有效标志: Y-是;N-否 */
  validFlag?: string,
  /** 备注 */
  remarks?: string
}


/**
 * 查询参数总类, 所有的都继承他
 */
export interface BizQuery {
  page: number,
  limit: number,
  // startTime?: string, // 开始时间
  // endTime?: string // 结束时间
}

/**
 * 分页
 */
export interface LayPage {
  /* 默认分页页面 */
  page: number,
  /* 每页查询条数 */
  limit: number,
  /* 可选择的查询额页面条数 */
  limits: number[],
  layout: string[],
  pages: number
}


/**
 * table 表格返回数据
 */
interface TableData<T> {
  loading: boolean, // 是否加载
  total: number,
  rows: T[],
}


</script>


<style scoped>


.fly-button {
  height: 33px;
  line-height: 33px;
  width: auto;
  border-radius: 3px;
  /*margin-top: 5px; font-weight: 400; //color: #ffffff; padding: 0 12px 0 10px; font-size: 16px; text-align: center; //transition: transform 0.3s ease; !* 平滑变化 *!*/
}

.fly-button:hover {
  /*transform: scale(1.1); !* 放大10% *!*/
}

.fly-button i {
  font-size: 18px !important;
  /*margin-right: 2px;*/
}

.layui-table-tool .layui-btn {
  letter-spacing: 2px !important;
}

.layui-table-cell-content .layui-btn {
  letter-spacing: 2px !important;
}

.fly-toolbar-addition {
  background-color: #1E9FFF !important;
  border-color: #31BDEC !important;
}

.fly-toolbar-addition:hover {
  background-color: #31BDEC !important;
}


.fly-edit-button {
  background-color: #6572ff !important;
  border-color: #6c87ff !important;
}

.fly-edit-button:hover {
  background-color: rgba(101, 114, 255, 0.64);
  border-color: rgba(101, 114, 255, 0.13);
}

.fly-remove-button {
  background-repeat: no-repeat !important;
  background-position: 10px center !important;
  color: #ffffff !important;
  background-color: #ff7652 !important;
  border-color: #fc8160 !important;
}

.fly-view-button {
  background-color: #1E9FFF !important;
  border-color: #31BDEC !important;
}

.fly-ok-button {
  display: inline-block;
  white-space: nowrap !important;
  text-align: center !important;
  cursor: pointer !important;
  background: #438de3 !important;
  border: #8dc5f5 1px solid !important;
  color: #FFFFFF !important;
  margin-right: 2px;
  /*padding: 0 15px !important;*/
  letter-spacing: 2px;
}

.fly-ok-button:hover {
  background-color: #8dc5f5 !important;
}

.fly-form-footer {
  width: 100%;
  text-align: right;
  border-top: 1px solid #f0f0f0;
  padding: 10px 30px 10px 30px;
  border-top: #f3f3f3 1px solid;
  background-color: #FFFFFF;
}

.fly-close-button {
  display: inline-block;
  white-space: nowrap;
  text-align: center;
  cursor: pointer;
  border: 1px solid #C9C9C9;
  background-color: #fff;
  color: #555;
  letter-spacing: 2px;
}

.fly-close-button:hover {
  background-color: #e8f7f6;
  border-color: #b9e8e3;
  color: #79ada7;
}

.fly-container {
  height: calc(100vh - 110px);
  margin-top: 10px;
  box-sizing: border-box;
  overflow: hidden;
}


.fly-button-search {
  background-color: #e8f7f6 !important;
  border-color: #b9e8e3 !important;
  color: #17B3A3 !important;
  letter-spacing: 3px;
}

.fly-button-search:hover {
  background-color: #e8f7f6 !important;
  border-color: #b9e8e3 !important;
  color: #17B3A3 !important;
  letter-spacing: 4px;
}


.fly-button-reset {
  background-color: #1e9fff !important;
  border-color: #1e9fff !important;
  color: #FFFFFF !important;
  letter-spacing: 3px !important;
}

.fly-button-reset:hover {
  letter-spacing: 4px;
}

.fly-button-exp {
  border-radius: 3px;
  background-color: #16baaa !important;
  border-color: #16baaa !important;
  color: #FFFFFF !important;
  height: 30px !important;
  line-height: 30px !important;
  letter-spacing: 3px !important;
}

.fly-button-exp:hover {
  letter-spacing: 4px;
}

.fly-search-card {
  margin-bottom: 0 !important;
  padding-top: 30px;
}

.layui-layer-btn .layui-layer-btn-r {
  border-top: 1px solid #E0E0E0 !important;
}

.layui-table-fixed-right {
  border-left: none !important;
}

.layui-table thead tr th {
  font-size: 18px !important;
  font-weight: bold;
}


</style>