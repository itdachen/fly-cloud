import {HttpRequest} from '@/fly/http/rest/HttpRequest';
import {AuthClazzDept, AuthClazzDeptQuery} from '@/api/auth/model/AuthClazzDeptModel';


/**
 * 请求路径
 */
const AUTH_CLAZZ_DEPT_PATH = '/admin/auth/clazz/dept';

/**
 * 部门岗位关联表 接口
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:50
 */
class AuthClazzDeptApi extends HttpRequest< AuthClazzDept, AuthClazzDeptQuery, string > {

    constructor() {
        super(AUTH_CLAZZ_DEPT_PATH)
    }


}

export default AuthClazzDeptApi