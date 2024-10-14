import {reactive} from 'vue';
import {DialogModel} from "./DialogModel";

/**
 * 弹窗统一封装
 */
export default function dialogPopup() {

    //定义弹框属性
    const dialog = reactive<DialogModel>({
        title: '',
        visible: false,
        width: 800,
        /*        height: 600,*/
        showSubmit: true
    })

    //展示
    const onShow = () => {
        dialog.visible = true;
    }

    //关闭
    const onClose = () => {
        dialog.visible = false;
    }

    // 确定
    const onConfirm = () => {
        dialog.visible = false;
    }

    return {
        dialog,
        onShow,
        onClose,
        onConfirm
    }

}
