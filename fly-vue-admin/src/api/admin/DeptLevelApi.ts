import {HttpRequest} from '@/fly/http/rest/HttpRequest';
import {DeptLevel, DeptLevelQuery} from '@/api/admin/model/DeptLevelModel';
import http from "@/fly/http";
import {ServerResponse} from "axios";


/**
 * 请求路径
 */
const DEPT_LEVEL_PATH = '/admin/dept/level';

/**
 * 部门等级管理 接口
 *
 * @author 王大宸
 * @date 2024-12-22 20:30:44
 */
class DeptLevelApi extends HttpRequest<DeptLevel, DeptLevelQuery, string> {

    constructor() {
        super(DEPT_LEVEL_PATH)
    }

    findList(): Promise<ServerResponse<DeptLevel[]>> {
        return http.get(DEPT_LEVEL_PATH + '/list');
    }


}

export default DeptLevelApi