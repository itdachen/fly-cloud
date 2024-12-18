import {AppInfo, AppInfoQuery} from "@/api/admin/model/AppInfoModel";
import {HttpRequest} from "@/fly/http/rest/HttpRequest";
import {ServerResponse} from "axios";
import httpAxios from "@/fly/http";

/**
 * 请求路径
 */
const APP_INFO_PATH = '/admin/app/info';

/**
 * 应用信息 接口
 *
 * @author 王大宸
 * @date 2024-09-05 10:00:59
 */
class AppInfoApi extends HttpRequest<AppInfo, AppInfoQuery, string> {

    constructor() {
        super(APP_INFO_PATH)
    }

    /**
     * 获取秘钥
     */
    findAppSecret(): Promise<ServerResponse<string>> {
        return httpAxios.get(APP_INFO_PATH + `/secret`);
    }


}

export default AppInfoApi