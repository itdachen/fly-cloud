import {HttpRequest} from "/@/http/request";
import {AppInfo, AppInfoQuery} from "/@/api/admin/models/AppInfoModel";

/**
 * 请求路径
 */
const APP_INFO_PATH = '/admin/app/info';

/**
 * 应用信息 接口
 *
 * @author 王大宸
 * @date 2024-10-14 09:53:36
 */
class AppInfoApi extends HttpRequest< AppInfo, AppInfoQuery, string > {

    constructor() {
        super(APP_INFO_PATH)
    }

}

export default AppInfoApi;