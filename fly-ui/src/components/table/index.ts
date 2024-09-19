import {reactive} from "vue";
import {LayPage} from "@/fly/biz/BizModel";

/**
 * 表格
 */
export default function useLayTableComposable() {

    /**
     * 分页组件基础参数
     */
    const flyLayPage = reactive<LayPage>({
        /* 默认分页页面 */
        page: 1,
        /* 每页查询条数 */
        limit: 10,
        /* 可选择的查询额页面条数 */
        limits: [10, 30, 50, 100, 200],
        /* 导航 */
        layout: ['prev', 'page', 'next', 'limits', 'count', 'refresh', 'skip'],
        pages: 5
    })


    return {
        flyLayPage
    }
}