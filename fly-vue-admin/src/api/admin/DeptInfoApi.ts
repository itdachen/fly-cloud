import {HttpRequest} from '@/fly/http/rest/HttpRequest';
import {DeptInfo, DeptInfoQuery} from '@/api/admin/model/DeptInfoModel';
import http from "@/fly/http";


/**
 * 请求路径
 */
const DEPT_INFO_PATH = '/admin/dept/info';

/**
 * 部门信息 接口
 *
 * @author 王大宸
 * @date 2024-12-22 22:30:09
 */
class DeptInfoApi extends HttpRequest<DeptInfo, DeptInfoQuery, string> {

    constructor() {
        super(DEPT_INFO_PATH)
    }

    /**
     * 获取部门树
     */
    findDeptTree(deptFlag: string) {
        return http.get(DEPT_INFO_PATH + '/tree?deptFlag=' + deptFlag);
    }


}

export default DeptInfoApi