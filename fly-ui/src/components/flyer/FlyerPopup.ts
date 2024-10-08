import {reactive} from "vue";

/**
 * 弹窗基本参数
 */
interface FlyPopup {
    open: boolean,
    title: string,
    area: string[],
    maxmin: boolean,
    disabled: boolean,
    showSubmit: boolean,
    showClose: boolean
}


/**
 * 弹窗操作
 */
export default function flyPopupComposable() {

    /**
     * 弹窗基础参数
     */
    const popupProps = reactive<FlyPopup>({
        title: '弹窗',
        open: false,
        area: ['90%', '60%'],
        maxmin: true,
        disabled: false,
        showSubmit: true,
        showClose: true
    })

    /**
     * 打开
     */
    const onOpenPopup = () => {
        popupProps.open = true;
    }

    /**
     * 关闭
     */
    const onClosePopup = () => {
        popupProps.open = false;
    }


    return {
        popupProps,
        onOpenPopup,
        onClosePopup
    }

}