import {HttpRequest} from '@/fly/http/rest/HttpRequest';
import {MenuInfo, MenuInfoQuery} from '@/api/admin/model/MenuInfoModel';


/**
 * 请求路径
 */
const MENU_INFO_PATH = '/admin/menu/info';

/**
 * 菜单信息 接口
 *
 * @author 王大宸
 * @date 2024-12-30 10:26:52
 */
class MenuInfoApi extends HttpRequest< MenuInfo, MenuInfoQuery, string > {

    constructor() {
        super(MENU_INFO_PATH)
    }


}

export default MenuInfoApi