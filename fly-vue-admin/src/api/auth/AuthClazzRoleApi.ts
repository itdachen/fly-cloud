import {HttpRequest} from '@/fly/http/rest/HttpRequest';
import {AuthClazzRole, AuthClazzRoleQuery} from '@/api/auth/model/AuthClazzRoleModel';


/**
 * 请求路径
 */
const AUTH_CLAZZ_ROLE_PATH = '/admin/auth/clazz/role';

/**
 * 身份岗位管理表 接口
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:50
 */
class AuthClazzRoleApi extends HttpRequest< AuthClazzRole, AuthClazzRoleQuery, string > {

    constructor() {
        super(AUTH_CLAZZ_ROLE_PATH)
    }


}

export default AuthClazzRoleApi