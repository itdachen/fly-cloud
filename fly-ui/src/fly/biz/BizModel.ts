/**
 * 查询参数总类, 所有的都继承他
 */
export interface BizQuery {
    page: number,
    limit: number,
    // timeType?: QueryTimeType, // 查询时间类型
    // startTime?: string, // 查询开始时间
    // endTime?: string // 查询结束时间
}

/**
 * 分页
 */
export interface LayPage {
    /* 默认分页页面 */
    page: number,
    /* 每页查询条数 */
    limit: number,
    /* 可选择的查询额页面条数 */
    limits: number[],
    layout: string[],
    pages: number
}

/**
 * 查询时间类型
 */
export enum QueryTimeType {

    /**
     * 昨天
     */
    YESTERDAY = "YESTERDAY",

    /**
     * 今天
     */
    TODAY = "TODAY",

    /**
     * 本周
     */
    YEAR_WEEK = "YEAR_WEEK",

    /**
     * 上周
     */
    LAST_YEAR_WEEK = "LAST_YEAR_WEEK",

    /**
     * 近7天(包含今天)
     */
    NEARLY_SAVE_DAYS = "NEARLY_SAVE_DAYS",

    /**
     * 本月
     */
    MONTH = "MONTH",

    /**
     * 上个月
     */
    LAST_MONTH = "LAST_MONTH",

    /**
     * 近 6 个月
     */
    NEARLY_SEX_MONTH = "NEARLY_SEX_MONTH",

    /**
     * 本季度
     */
    QUARTER = "QUARTER",

    /**
     * 上季度
     */
    LAST_QUARTER = "LAST_QUARTER",

    /**
     * 今年
     */
    YEAR = "YEAR",

    /**
     * 去年
     */
    LAST_YEAR = "LAST_YEAR",

    /**
     * 自定义
     */
    CUSTOM = "CUSTOM"
}