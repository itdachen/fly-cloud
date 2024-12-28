import {HttpRequest} from '@/fly/http/rest/HttpRequest';
import {ClazzInfo, ClazzInfoQuery} from '@/api/admin/model/ClazzInfoModel';


/**
 * 请求路径
 */
const CLAZZ_INFO_PATH = '/admin/clazz/info';

/**
 * 岗位信息 接口
 *
 * @author 王大宸
 * @date 2024-12-28 18:40:48
 */
class ClazzInfoApi extends HttpRequest< ClazzInfo, ClazzInfoQuery, string > {

    constructor() {
        super(CLAZZ_INFO_PATH)
    }


}

export default ClazzInfoApi