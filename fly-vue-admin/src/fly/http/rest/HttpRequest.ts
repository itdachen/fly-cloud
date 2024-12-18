import {ServerResponse, TableData} from "axios";
import {BizQuery} from "@/fly/biz/BizModel";

import httpAxios from "@/fly/http/index";
import {StringUtils} from "@/fly/utils/StringUtils";

/**
 * 基于 Restful Api 接口规范, 封装常用请求
 *
 * T: 请求参数
 * Q: 分页查询参数
 * V: 相应对象
 * PK: 数据 id 的类型
 */
export class HttpRequest<T, Q extends BizQuery, PK> {

    protected path: string = "";

    /**
     * 构造函数
     * @param path 后端 controller 类上路径
     */
    constructor(path: string) {
        if (StringUtils.isEmpty(path)) {
            console.warn("请检查 path 是否为空")
            path = '';
        }
        this.path = path;
    }

    /**
     * 分页 接口
     * @param params 查询参数对象
     */
    page(params: Q): Promise<ServerResponse<TableData<T>>> {
        return httpAxios.get(this.path + `/page${this.params(params)}`);
    }

    /**
     * 新增接口
     * @param obj 需要新增的数据
     */
    saveInfo(obj: T): Promise<ServerResponse<T>> {
        return httpAxios.post(this.path, obj);
    }

    /**
     * 更新数据接口
     * @param obj 需要更新的数据
     * @param id  数据id
     */
    updateInfo(obj: T, id: PK | undefined): Promise<ServerResponse<T>> {
        return httpAxios.put(this.path + `/${id}`, obj);
    }

    /**
     * 根据id删除数据接口
     * @param id 需要删除的数据id
     */
    remove(id: PK): Promise<ServerResponse<T>> {
        return httpAxios.delete(this.path + `/${id}`);
    }

    /**
     * 根据id查询
     * @param id 需要查询的数据id
     */
    getById(id: string): Promise<ServerResponse<T>> {
        return httpAxios.get(this.path + `/${id}`);
    }

    /**
     * 将对象拼接到请求路径上
     * @param params
     */
    private params(params: Q) {
        let result = '';
        if (null === params || undefined === params) {
            return result;
        }
        let item;
        for (item in params) {
            // @ts-ignore
            if (params[item] && String(params[item])) {
                // @ts-ignore
                result += `&${item}=${params[item]}`;
            }
        }
        if (result) {
            result = '?' + result.slice(1);
        }
        return result;
    }

}