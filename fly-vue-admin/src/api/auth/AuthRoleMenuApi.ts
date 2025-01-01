import {HttpRequest} from '@/fly/http/rest/HttpRequest';
import {AuthRoleMenu, AuthRoleMenuQuery} from '@/api/auth/model/AuthRoleMenuModel';


/**
 * 请求路径
 */
const AUTH_ROLE_MENU_PATH = '/admin/auth/role/menu';

/**
 * 人员身份菜单 接口
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:49
 */
class AuthRoleMenuApi extends HttpRequest< AuthRoleMenu, AuthRoleMenuQuery, string > {

    constructor() {
        super(AUTH_ROLE_MENU_PATH)
    }


}

export default AuthRoleMenuApi