import {ServerResponse, TableData} from "axios";
import {BizQuery} from "/@/fly/biz/BizModel";

import request from "../index";
import {StringUtils} from "/@/fly/utils/StringUtils";

/**
 * 基于 Restful Api 接口规范, 封装常用请求
 *
 * T: 数据实体
 * Q: 查询参数
 * PK: 数据主键类型
 */
export class HttpRequest<T, Q extends BizQuery, PK> {

    private path: string = "";

    /**
     * 构造函数
     * @param path
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
        return request({
            url: this.path + '/page',
            params: params,
            method: 'GET'
        });
    }

    /**
     * 新增接口
     * @param obj 需要新增的数据
     */
    saveInfo(obj: T): Promise<ServerResponse<T>> {
        return request({
            url: this.path,
            data: obj,
            method: 'POST'
        });
    }

    /**
     * 更新数据接口
     * @param obj 需要更新的数据
     * @param id  数据id
     */
    updateInfo(obj: T, id: PK | undefined): Promise<ServerResponse<T>> {
        return request({
            url: this.path + `/${id}`,
            data: obj,
            method: 'PUT'
        });
    }

    /**
     * 根据id删除数据接口
     * @param id 需要删除的数据id
     */
    remove(id: PK): Promise<ServerResponse<T>> {
        return request({
            url: this.path + `/${id}`,
            method: 'DELETE'
        });
    }

    /**
     * 根据id查询
     * @param id 需要查询的数据id
     */
    getById(id: string): Promise<ServerResponse<T>> {
        return request({
            url: this.path + `/${id}`,
            method: 'GET'
        });
    }


    /**
     * 导出
     * @param params 导出参数
     * @param filename 文件名称
     */
    expData(params: Q, filename: string): void {
        // let url = this.path + `/page${this.uriParams(params)}`;
        request({
            url: this.path + '/exp',
            params: params,
            method: 'GET',
            responseType: 'blob', // 重要：设置响应类型为blob
        }).then(res => {
            if (StringUtils.isEmpty(filename)) {
                filename = '导出数据';
            }
            // 创建一个临时的url代表下载链接
            const url = window.URL.createObjectURL(new Blob([res.data], {type: 'application/vnd.ms-excel'}));
            const link = document.createElement('a');
            link.href = url;
            link.setAttribute('download', filename);
            document.body.appendChild(link);
            // 触发下载
            link.click();
        }).catch((error) => {
            console.error('下载出错:', error);
        });
    }


    /**
     * 将对象拼接到请求路径上
     * @param params
     */
    private uriParams(params: Q) {
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
