/**
 * 表单
 */
export default function useElementFromComposable() {

    /**
     * 刷新表单
     * @param fromRef
     * @param obj
     */
    const resetForm = (fromRef: any, obj: any) => {
        //清空数据
        Object.keys(obj).forEach(key => {
            obj[key] = ''
        })
        //清除表单的验证
        if (fromRef) {
            fromRef.resetFields();
            fromRef.clearValidate();
        }
    }

    /**
     * 数据拷贝
     * @param obj1
     * @param obj2
     */
    const objCopy = (obj1: any, obj2: any) => {
        Object.keys(obj2).forEach(key => {
            obj2[key] = obj1[key]
        })
    }

    return {
        resetForm,
        objCopy
    }
}
