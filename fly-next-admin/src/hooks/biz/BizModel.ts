/**
 * 传输参数总类, 属所有的都继承他
 */
export interface BizDto {
}

/**
 * 接收参数总类, 所有的都继承他
 */
export interface BizVo {
}

/**
 * 查询参数总类, 所有的都继承他
 */
export interface BizQuery {
    page: number,
    limit: number
}