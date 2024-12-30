import {HttpRequest} from '@/fly/http/rest/HttpRequest';
import {ElementInfo, ElementInfoQuery} from '@/api/admin/model/ElementInfoModel';


/**
 * 请求路径
 */
const ELEMENT_INFO_PATH = '/admin/element/info';

/**
 * 资源(按钮)信息 接口
 *
 * @author 王大宸
 * @date 2024-12-30 10:26:53
 */
class ElementInfoApi extends HttpRequest< ElementInfo, ElementInfoQuery, string > {

    constructor() {
        super(ELEMENT_INFO_PATH)
    }


}

export default ElementInfoApi