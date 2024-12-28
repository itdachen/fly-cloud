import {HttpRequest} from '@/fly/http/rest/HttpRequest';
import {ClazzFunc, ClazzFuncQuery} from '@/api/admin/model/ClazzFuncModel';
import http from "@/fly/http";
import {ServerResponse} from "axios";


/**
 * 请求路径
 */
const CLAZZ_FUNC_PATH = '/admin/clazz/func';

/**
 * 岗位职能 接口
 *
 * @author 王大宸
 * @date 2024-12-28 18:40:48
 */
class ClazzFuncApi extends HttpRequest<ClazzFunc, ClazzFuncQuery, string> {

    constructor() {
        super(CLAZZ_FUNC_PATH)
    }

    /**
     * 获取集合
     */
    findClazzFuncList(): Promise<ServerResponse<ClazzFunc[]>> {
        return http.get(CLAZZ_FUNC_PATH + '/list');
    }


}

export default ClazzFuncApi