import {HttpRequest} from '@/fly/http/rest/HttpRequest';
import {TenantInfo, TenantInfoQuery} from '@/api/admin/model/TenantInfoModel';


/**
 * 请求路径
 */
const TENANT_INFO_PATH = '/admin/tenant/info';

/**
 * 租户/公司信息 接口
 *
 * @author 王大宸
 * @date 2024-12-19 11:07:05
 */
class TenantInfoApi extends HttpRequest< TenantInfo, TenantInfoQuery, string > {

    constructor() {
        super(TENANT_INFO_PATH)
    }


}

export default TenantInfoApi