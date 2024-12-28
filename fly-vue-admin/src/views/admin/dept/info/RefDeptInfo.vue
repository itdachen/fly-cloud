<template>
  <lay-layer v-model="layerRef.open"
             :title="layerRef.title"
             :area="layerRef.area"
             :shade-close="layerRef.shadeClose"
             :maxmin="layerRef.maxmin">

    <div style="padding: 20px">
      <lay-form :model="deptInfo" ref="layDeptInfoForm">

        <lay-form-item label="所在省" prop="provId" :required="layerRef.required" mode="inline">
          <lay-select v-model="deptInfo.provId" @change="provChangeFilter">
            <lay-select-option v-for="item in provList" :key="item.id" :value="item.id">
              {{ item.title }}
            </lay-select-option>
          </lay-select>
        </lay-form-item>

        <lay-form-item label="所在市州" prop="cityId" :required="layerRef.required" mode="inline">
          <lay-select v-model="deptInfo.cityId" @change="cityChangeFilter">
            <lay-select-option v-for="item in cityList" :key="item.id" :value="item.id">
              {{ item.title }}
            </lay-select-option>
          </lay-select>
        </lay-form-item>

        <lay-form-item label="所在区县" prop="countyId" :required="layerRef.required" mode="inline">
          <lay-select v-model="deptInfo.countyId">
            <lay-select-option v-for="item in countyList" :key="item.id" :value="item.id">
              {{ item.title }}
            </lay-select-option>
          </lay-select>
        </lay-form-item>

        <lay-form-item label="部门层级" prop="levelCode" :required="layerRef.required">
          <lay-radio v-model="deptInfo.levelCode"
                     v-for="item in deptLevelList"
                     :value="item.levelCode" :label="item.levelTitle"
                     name="levelCode" :disabled="layerRef.disabled"></lay-radio>
        </lay-form-item>

        <lay-form-item label="部门职能" prop="funcCode" :required="layerRef.required">
          <lay-select v-model="deptInfo.funcCode" @change="funcCodeChangeFilter">
            <lay-select-option v-for="item in deptFuncList"
                               :key="item.funcCode"
                               :value="item.funcCode">
              {{ item.funcTitle }}
            </lay-select-option>
          </lay-select>

        </lay-form-item>


        <lay-form-item label="部门名称" prop="title" :required="layerRef.required">
          <lay-input v-model="deptInfo.title" :disabled="layerRef.disabled"></lay-input>
        </lay-form-item>

        <lay-form-item label="部门简称" prop="titleAs">
          <lay-input v-model="deptInfo.titleAs" :disabled="layerRef.disabled"></lay-input>
        </lay-form-item>


        <!--        <lay-form-item label="部门标志" prop="deptFlag" mode="inline">-->
        <!--          <lay-radio v-model="deptInfo.deptFlag" name="action" value="Y" label="是"-->
        <!--                     :disabled="layerRef.disabled"></lay-radio>-->
        <!--          <lay-radio v-model="deptInfo.deptFlag" name="action" value="N" label="否"-->
        <!--                     :disabled="layerRef.disabled"></lay-radio>-->
        <!--        </lay-form-item>-->


        <lay-form-item label="联系电话" prop="telephone" mode="inline">
          <lay-input v-model="deptInfo.telephone" :disabled="layerRef.disabled"></lay-input>
        </lay-form-item>

        <lay-form-item label="电子邮箱" prop="mailBox" mode="inline">
          <lay-input v-model="deptInfo.mailBox" :disabled="layerRef.disabled"></lay-input>
        </lay-form-item>

        <lay-form-item label="传真" prop="facsimile" mode="inline">
          <lay-input v-model="deptInfo.facsimile" :disabled="layerRef.disabled"></lay-input>
        </lay-form-item>

        <lay-form-item label="是否管理本级" prop="thatLevel" mode="inline">
          <lay-radio v-model="deptInfo.thatLevel" name="action" value="Y" label="是"
                     :disabled="layerRef.disabled"></lay-radio>
          <lay-radio v-model="deptInfo.thatLevel" name="action" value="N" label="否"
                     :disabled="layerRef.disabled"></lay-radio>
        </lay-form-item>

        <lay-form-item label="有效标志" prop="validFlag" mode="inline">
          <lay-radio v-model="deptInfo.validFlag" name="action" value="Y" label="有效"
                     :disabled="layerRef.disabled"></lay-radio>
          <lay-radio v-model="deptInfo.validFlag" name="action" value="N" label="无效"
                     :disabled="layerRef.disabled"></lay-radio>
        </lay-form-item>

        <lay-form-item label="备注" prop="remarks">
          <lay-textarea v-model="deptInfo.remarks" :disabled="layerRef.disabled"></lay-textarea>
        </lay-form-item>

      </lay-form>

    </div>


    <template v-slot:footer>
      <div class="fly-form-footer">
        <lay-button v-if="layerRef.submit" class="fly-button fly-ok-button " @click="saveDeptInfoHandler">
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


<script setup lang="ts" name="refDeptInfo">
import {onMounted, reactive, ref} from "vue";
import {FormTypeEnum} from "@/fly/biz/BizModel";
import {DeptInfo} from "@/api/admin/model/DeptInfoModel";
import useTenantInfoComposable from "@/composables/admin/DeptInfoComposable";
import OpenAdmAreaApi from "@/api/open/OpenAdmAreaApi";
import DeptLevelApi from "@/api/admin/DeptLevelApi";
import DeptFuncApi from "@/api/admin/DeptFuncApi";

const admAreaApi = new OpenAdmAreaApi();

const deptLevelApi = new DeptLevelApi();
const deptFuncApi = new DeptFuncApi()

const {
  deptInfo,
  deptInfoDataHandler
} = useTenantInfoComposable();

const layDeptInfoForm = ref<any>();

/* 弹窗 */
const layerRef = reactive<any>({
  open: false,
  title: '部门信息',
  area: ['1230px', '570px'], // 高度, 宽度
  disabled: false,
  shadeClose: false,
  maxmin: false,
  submit: true,
})

/* 省集合 */
const provList = ref();
/* 市州集合 */
const cityList = ref();
/* 区县集合 */
const countyList = ref();

const deptLevelList = ref();
const deptFuncList = ref();


/**
 * 提交
 */
function saveDeptInfoHandler() {
  layDeptInfoForm.value.validate((isValidate: boolean, model: DeptInfo, errors: any[]) => {
    if (!isValidate) {
      return;
    }

    /* 市州区县回显处理 */
    areaTitleHandler(model);

    /* 部门层级 deptLevelList */
    for (let i = 0; i < deptLevelList.value.length; i++) {
      if (deptLevelList.value[i].levelCode === model.levelCode) {
        model.levelTitle = deptLevelList.value[i].levelTitle;
      }
    }

    /* 部门职能 deptFuncList */
    for (let i = 0; i < deptFuncList.value.length; i++) {
      if (deptFuncList.value[i].funcCode === model.funcCode) {
        model.funcTitle = deptFuncList.value[i].funcTitle;
      }
    }


    /* 数据入库处理 */
    deptInfoDataHandler(model);
    // onTapClose();
    // console.log('数据保存', deptInfo.value);
    //  emit('click', deptInfo)
  });
}


//显示弹框
const open = (type: FormTypeEnum, row?: DeptInfo, parentDeptCode?: string) => {
  layerRef.title = type + '部门信息';
  if (null !== row) {
    deptInfo.value = JSON.parse(JSON.stringify(row));
    provChangeFilter(deptInfo.value.provId, '1');
    cityChangeFilter(deptInfo.value.cityId, '1');
  } else {
    /* 市州集合 */
    cityList.value = [];
    /* 区县集合 */
    countyList.value = [];
    deptInfo.value = {
      id: '',  // 主键唯一标识
      tenantId: '',  // 租户标识/公司标识
      deptCode: '',  // 部门编码
      parentCode: parentDeptCode,  // 上级部门编码
      title: '',  // 部门名称
      titleAs: '',  // 部门简称
      deptFlag: 'Y',  // 部门标志: Y-是;N-否(否的时候为行政区域, 例如: 贵州省贵阳市)
      thatLevel: 'N',  // 是否管理本级: Y-是;N-否
      levelCode: '',  // 部门级次代码: 00-总部/10-省级/20-市州级/30-区县级/40-乡村级
      levelTitle: '',  // 部门级次名称
      funcCode: '',  // 职能代码(dept_func表中 code)
      funcTitle: '',  // 职能名称(dept_func表中title)
      telephone: '',  // 联系电话
      mailBox: '',  // 电子邮箱
      facsimile: '',  // 传真
      provId: '',  // 所属省级ID
      provTitle: '',  // 所属省级名称
      cityId: '',  // 所属市州
      cityTitle: '',  // 所属市州名称
      countyId: '',  // 所属区县
      countyTitle: '',  // 所属区县名称
      address: '',  // 详细地址
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
  admAreaApi.findProvList().then(res => {
    provList.value = res.data;
  });


  deptLevelApi.findList().then(res => {
    deptLevelList.value = res.data;
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


const provChangeFilter = (id: string | undefined, t?: string) => {
  deptInfo.value.provId = id;
  admAreaApi.findCityList(id).then(res => {
    cityList.value = res.data;
    if (0 < res.data.length) {
      if ('1' === t) {
        cityChangeFilter(deptInfo.value.cityId, t);
      } else {
        let cityInfo = res.data[0];
        deptInfo.value.cityId = cityInfo.id;
        deptInfo.value.countyId = '';
        cityChangeFilter(cityInfo.id);
      }
    }
  })
}

const cityChangeFilter = (id: string | undefined, t?: string) => {
  deptInfo.value.cityId = id;
  admAreaApi.findCountyList(id).then(res => {
    countyList.value = res.data;
    if ('1' === t) {
      return;
    }
    if (0 < res.data.length) {
      let countyInfo = res.data[0];
      deptInfo.value.countyId = countyInfo.id;
    }
  })
}

const funcCodeChangeFilter = (funcCode: string) => {
  console.log('funcCode', funcCode);

  areaTitleHandler(deptInfo.value);

  let provTitle = deptInfo.value.provTitle;
  let cityTitle = deptInfo.value.cityTitle;
  let countyTitle = deptInfo.value.countyTitle;

  let title = '';

  if (null !== provTitle && '' !== provTitle && undefined !== provTitle) {
    title = title + provTitle + '省';
  }
  if (null !== cityTitle && '' !== cityTitle && undefined !== cityTitle && '市辖区' !== cityTitle) {
    title = title + cityTitle + '市/州';
  }
  if (null !== countyTitle && '' !== countyTitle && undefined !== countyTitle) {
    title = title + countyTitle + '区/县';
  }

  for (let i = 0; i < deptFuncList.value.length; i++) {
    if (funcCode === deptFuncList.value[i].funcCode) {
      title = title + deptFuncList.value[i].funcTitle;
    }
  }

  deptInfo.value.title = title;
  deptInfo.value.titleAs = title;

}

/**
 * 省/市州/区县名称处理
 * @param model
 */
const areaTitleHandler = (model: DeptInfo) => {
  /* 省/市州/区县回显处理 */
  let provId = model.provId;
  if (null !== provId && '' !== provId && 0 < provList.value.length) {
    for (let i = 0; i < provList.value.length; i++) {
      if (provId === provList.value[i].id) {
        model.provTitle = provList.value[i].title;
      }
    }
  }

  let cityId = model.cityId;
  if (null !== cityId && '' !== cityId && 0 < cityList.value.length) {
    for (let i = 0; i < cityList.value.length; i++) {
      if (cityId === cityList.value[i].id) {
        model.cityTitle = cityList.value[i].title;
      }
    }
  }

  let countyId = model.countyId;
  if (null !== countyId && '' !== countyId && 0 < countyList.value.length) {
    for (let i = 0; i < countyList.value.length; i++) {
      if (countyId === countyList.value[i].id) {
        model.countyTitle = countyList.value[i].title;
      }
    }
  }

}


</script>


<style scoped>
.layui-select {
  width: 100% !important;
}
</style>