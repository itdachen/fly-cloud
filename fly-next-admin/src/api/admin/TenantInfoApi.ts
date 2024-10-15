import {HttpRequest} from "/@/http/request";
import {TenantInfo, TenantInfoQuery} from "/@/api/admin/models/TenantInfoModel";

/**
 * 请求路径
 */
const TENANT_INFO_PATH = '/admin/tenant/info';

/**
 * 租户/公司信息 接口
 *
 * @author 王大宸
 * @date 2024-10-15 16:27:41
 */
class TenantInfoApi extends HttpRequest< TenantInfo, TenantInfoQuery, string > {

    constructor() {
        super(TENANT_INFO_PATH)
    }

}

export default TenantInfoApi;