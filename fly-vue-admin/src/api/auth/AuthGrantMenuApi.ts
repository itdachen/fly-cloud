import {HttpRequest} from '@/fly/http/rest/HttpRequest';
import {AuthGrantMenu, AuthGrantMenuQuery} from '@/api/auth/model/AuthGrantMenuModel';


/**
 * 请求路径
 */
const AUTH_GRANT_MENU_PATH = '/admin/auth/grant/menu';

/**
 * 权限下发 接口
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:49
 */
class AuthGrantMenuApi extends HttpRequest< AuthGrantMenu, AuthGrantMenuQuery, string > {

    constructor() {
        super(AUTH_GRANT_MENU_PATH)
    }


}

export default AuthGrantMenuApi