/**
 * table 表格通用操作
 */
import {reactive} from "vue";
import {ElMessage, ElMessageBox} from "element-plus";

export default function useCustomTableComposable() {

    /**
     * 初始化分页表格基本参数
     */
    const elPage = reactive<TablePageInfo>({
        pageSizes: [10, 30, 50, 100, 200, 300, 500],
        hideOnSinglePage: true,
        data: [],
        showPage: true
    })

    /**
     * 删除确认
     * @param text 消息
     */
    const confirmMsgBox = (text = '确定删除数据吗?') => {
        return ElMessageBox.confirm(text, '系统提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
        })
    }

    /**
     * 成功提示
     * @param text 提示的消息
     */
    const successMsg = (text = '操作成功') => {
        ElMessage({
            type: 'success',
            message: text,
        })
    }

    return {
        elPage,
        confirmMsgBox,
        successMsg
    }

}

/**
 * 通用表格属性
 */
export interface TablePageInfo {
    pageSizes: Array<number>,
    hideOnSinglePage: boolean,
    data: Array<any>,
    showPage: boolean
}
