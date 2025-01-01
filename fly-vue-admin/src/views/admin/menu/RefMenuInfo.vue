<template>
  <lay-layer v-model="layerRef.open"
             :title="layerRef.title"
             :area="layerRef.area"
             :shade-close="layerRef.shadeClose"
             :maxmin="layerRef.maxmin">

    <div style="padding: 20px">
      <lay-form :model="menuInfo" ref="layMenuInfoForm">

        <lay-form-item v-if="menuSuffix" label="所属目录" prop="parentTitle">
          <lay-input v-model="menuInfo.parentTitle" :disabled="layerRef.disabled">
            <template #suffix>
              <lay-button class="fly-button fly-ok-button"
                          style="height: 37px !important;margin-right: -10px !important;"
                          @click="onTapEditParent">
                <lay-icon class="layui-icon-edit"></lay-icon>
                修改上级目录
              </lay-button>
            </template>
          </lay-input>
        </lay-form-item>

        <lay-form-item label="类型" prop="type" :required="layerRef.required">
          <lay-radio v-model="menuInfo.type" name="type" value="dirt" :disabled="layerRef.disabled">目录</lay-radio>
          <lay-radio v-model="menuInfo.type" name="type" value="menu" :disabled="layerRef.disabled">菜单</lay-radio>
          <lay-radio v-model="menuInfo.type" name="type" value="uri" :disabled="layerRef.disabled">外链</lay-radio>
          <lay-radio v-model="menuInfo.type" name="type" value="third" :disabled="layerRef.disabled">第三方平台
          </lay-radio>
        </lay-form-item>

        <lay-form-item label="菜单标题" prop="title" :required="layerRef.required">
          <lay-input v-model="menuInfo.title" :disabled="layerRef.disabled"></lay-input>
        </lay-form-item>

        <lay-form-item label="认证编码" prop="authCode" :required="layerRef.required">
          <lay-input v-model="menuInfo.authCode" :disabled="layerRef.disabled"></lay-input>
        </lay-form-item>

        <lay-form-item label="路由地址" prop="path" :required="layerRef.required">
          <lay-input v-model="menuInfo.path" :disabled="layerRef.disabled"></lay-input>
        </lay-form-item>

        <lay-form-item label="组件地址" prop="componentName" :required="layerRef.required">
          <lay-input v-model="menuInfo.componentName" :disabled="layerRef.disabled"></lay-input>
        </lay-form-item>

        <lay-form-item label="图标" prop="layIcon" mode="inline">
          <!-- showSearch         -->
          <lay-icon-picker v-model="menuInfo.layIcon"></lay-icon-picker>
        </lay-form-item>

        <lay-form-item label="菜单打开方式" prop="openType" mode="inline">
          <lay-select v-model="menuInfo.openType">
            <lay-select-option value="iframe" label="iframe（内嵌）" :disabled="layerRef.disabled"></lay-select-option>
            <lay-select-option value="modal" label="modal（弹层外链）" :disabled="layerRef.disabled"></lay-select-option>
            <lay-select-option value="modal" label="blank（原生跳转）" :disabled="layerRef.disabled"></lay-select-option>
          </lay-select>
        </lay-form-item>


        <lay-form-item label="排序" prop="orderNum" mode="inline">
          <lay-input-number style="width: 100%"
                            v-model="menuInfo.orderNum" position="right"
                            :disabled="layerRef.disabled"></lay-input-number>
        </lay-form-item>

        <lay-form-item label="是否需要授权" prop="hasAuth" mode="inline">
          <lay-radio v-model="menuInfo.hasAuth" name="hasAuth" value="Y" :disabled="layerRef.disabled">需要</lay-radio>
          <lay-radio v-model="menuInfo.hasAuth" name="hasAuth" value="N" :disabled="layerRef.disabled">不需要
          </lay-radio>
        </lay-form-item>

        <lay-form-item label="有效标志" prop="validFlag" mode="inline">
          <lay-radio v-model="menuInfo.validFlag" name="validFlag" value="Y" :disabled="layerRef.disabled">有效
          </lay-radio>
          <lay-radio v-model="menuInfo.validFlag" name="validFlag" value="N" :disabled="layerRef.disabled">无效
          </lay-radio>
        </lay-form-item>

        <!--              <lay-form-item label="是否需要白名单IP" prop="hasIp"   mode="inline">-->
        <!--                <lay-radio v-model="menuInfo.hasIp" name="hasIp" value="Y" :disabled="layerRef.disabled">需要</lay-radio>-->
        <!--                <lay-radio v-model="menuInfo.hasIp" name="hasIp" value="N" :disabled="layerRef.disabled">不需要</lay-radio>-->
        <!--              </lay-form-item>-->

        <lay-form-item label="备注" prop="remarks">
          <lay-textarea placeholder="请输入备注" v-model="menuInfo.remarks"
                        :disabled="layerRef.disabled"></lay-textarea>
        </lay-form-item>

      </lay-form>

    </div>


    <template v-slot:footer>
      <div class="fly-form-footer">
        <lay-button v-if="layerRef.submit" class="fly-button fly-ok-button " @click="saveMenuInfoHandler">
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


  <RefEditParent ref="refEditParentComponent" @click="editParentMenu"/>

</template>


<script setup lang="ts" name="refMenuInfo">
import {reactive, ref} from "vue";
import {FormTypeEnum} from "@/fly/biz/BizModel";
import {MenuInfo} from "@/api/admin/model/MenuInfoModel";
import useTenantInfoComposable from "@/composables/admin/MenuInfoComposable";
import RefEditParent from './RefEditParent.vue';

const {
  menuInfo,
  menuInfoDataHandler,
  refEditParentComponent
} = useTenantInfoComposable();

const layMenuInfoForm = ref<any>();
const menuSuffix = ref(false);


/* 弹窗 */
const layerRef = reactive<any>({
  open: false,
  title: '菜单信息',
  area: ['1200px', '670px'], // 宽度,  高度
  disabled: false,
  shadeClose: false,
  maxmin: false,
  submit: true,
})


/**
 * 提交
 */
function saveMenuInfoHandler() {
  layMenuInfoForm.value.validate((isValidate: boolean, model: MenuInfo, errors: any[]) => {
    if (!isValidate) {
      return;
    }
    menuInfoDataHandler(model);
    // onTapClose(); // 关闭弹窗
    // console.log('数据保存', menuInfo.value);
    //  emit('click', menuInfo)
  });
}


//显示弹框
const open = (type: FormTypeEnum, row?: MenuInfo, appId?: string, parentId?: string, parentTitle?: string) => {
  layerRef.title = type + '菜单信息';
  menuSuffix.value = false;
  if (FormTypeEnum.EDIT === type) {
    menuSuffix.value = true;
  }
  if (null !== row) {
    menuInfo.value = JSON.parse(JSON.stringify(row))
  } else {
    menuInfo.value = {
      id: '',  // 菜单ID
      appId: appId,  // 应用ID/appId
      parentId: parentId,  // 上级ID
      parentTitle: parentTitle,  // 上级菜单
      authCode: '',  // 认证编码
      type: 'dirt',  // 类型:dirt-目录;menu-菜单;uri-外链;third-第三方平台
      title: '',  // 菜单标题
      path: '',  // 访问地址
      layIcon: 'layui-icon-home',  // 图标
      orderNum: '99',  // 排序
      validFlag: 'Y',  // 是否展示/有效标志: Y-有效;N-无效
      openType: 'iframe',  // 菜单打开方式
      hasAuth: 'Y',  // 是否需要授权: Y-需要;N-不需要
      hasIp: 'N',  // 是否需要白名单IP: Y-需要;N-不需要
      affix: '',  // 是否固定
      closable: '',  // 是否可关闭
      thirdParty: '',  // 第三方平台标识
      thirdTitle: '',  // 第三方平台名称
      thirdUri: '',  // 第三方基础访问地址, 例如: http://www.baidu.com
      askUri: '',  // 最终访问地址, 根据第三方平台信息拼接
      componentName: '',  // 组件名称
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

const onTapEditParent = () => {
  refEditParentComponent.value?.open();
}

const editParentMenu = (menuId: string, menuTitle: string) => {
  menuInfo.value.parentId = menuId;
  menuInfo.value.parentTitle = menuTitle;
}


//在组件中，属性和方法，只能对当前template,
//外部需要使用的时候，需要使用 defineExpose 暴露出去给父组件
defineExpose({
  open,
  onTapClose
});

//注册事件
// const emit = defineEmits(['click']);

</script>


<style scoped>

</style>