import {ElMessage} from 'element-plus'
import {ElMessageBox} from 'element-plus'


/**
 * 消息提示封装
 */
export default function useTable() {

    const successMsg = (text = '操作成功') => {
        ElMessage({
            type: 'success',
            message: text,
        })
    }

    /**
     * 删除确认
     * @param val1 消息
     * @param val2 是否自定义消息
     */
    const confirmMsgBox = (val1: string | undefined, val2: boolean = false) => {
        let msg: string | undefined = '数据删除后将无法恢复，确定删除该数据吗?';
        if (val1) {
            msg = '确定要删除 ' + val1 + ' 吗? 数据删除后将无法恢复！';
        }
        if (val2) {
            msg = val1;
        }
        return ElMessageBox.confirm(msg, '系统提示', {
            cancelButtonText: '取消',
            confirmButtonText: '确定',
            type: 'warning',
        })
    }


    return {
        successMsg,
        confirmMsgBox
    }

}