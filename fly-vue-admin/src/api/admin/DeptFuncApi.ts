import {HttpRequest} from '@/fly/http/rest/HttpRequest';
import {DeptFunc, DeptFuncQuery} from '@/api/admin/model/DeptFuncModel';
import http from "@/fly/http";
import {ServerResponse} from "axios";


/**
 * 请求路径
 */
const DEPT_FUNC_PATH = '/admin/dept/func';

/**
 * 部门职能 接口
 *
 * @author 王大宸
 * @date 2024-12-20 14:40:35
 */
class DeptFuncApi extends HttpRequest<DeptFunc, DeptFuncQuery, string> {

    constructor() {
        super(DEPT_FUNC_PATH)
    }

    findList(): Promise<ServerResponse<DeptFunc>> {
        return http.get(DEPT_FUNC_PATH + '/list');
    }


}

export default DeptFuncApi