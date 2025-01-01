import {HttpRequest} from '@/fly/http/rest/HttpRequest';
import {AuthClazzMenu, AuthClazzMenuQuery} from '@/api/auth/model/AuthClazzMenuModel';


/**
 * 请求路径
 */
const AUTH_CLAZZ_MENU_PATH = '/admin/auth/clazz/menu';

/**
 * 岗位菜单 接口
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:50
 */
class AuthClazzMenuApi extends HttpRequest< AuthClazzMenu, AuthClazzMenuQuery, string > {

    constructor() {
        super(AUTH_CLAZZ_MENU_PATH)
    }


}

export default AuthClazzMenuApi