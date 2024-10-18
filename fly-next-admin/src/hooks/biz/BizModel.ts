/**
 * 查询参数总类, 所有的都继承他
 */
export interface BizQuery {
    page: number,
    limit: number
}

/**
 * 数据字典
 */
export interface DictData {
    /* 显示文本 */
    label: string,
    /* 值 */
    value: string,
    /* 是否选择 */
    checked?: string,
}