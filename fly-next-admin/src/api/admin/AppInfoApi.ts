import request from "/@/utils/request";

const ADMIN_APP_INFO_PATH = '/admin/app/info';

/**
 * 应用信息接口
 */
export function useAppInfoApi() {

    /**
     * 分页查询
     * @param params
     */
    const page = (params: any) => {
        return request({
            url: ADMIN_APP_INFO_PATH + '/page',
            params: params
        });
    }


    return {
        page
    }
}