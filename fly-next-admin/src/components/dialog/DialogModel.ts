/**
 * 弹框属性类型
 */
export type DialogModel = {
    title: string,
    visible: boolean,
    height: number,
    width: number,
    showSubmit?: boolean
}

/**
 * 弹框标题
 */
export enum DialogTypeEnum {
    ADD = '新增',
    EDIT = '编辑',
    SEE = '查看'
}

