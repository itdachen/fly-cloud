/**
 * 区域
 */
export interface AdmArea {
    /** 主键 */
    id?: string,
    /** 名称 */
    title?: string,
    /** 上级ID */
    parentId?: string,
    /** 数据级别：省市区县乡镇街道 */
    level?: string,
}