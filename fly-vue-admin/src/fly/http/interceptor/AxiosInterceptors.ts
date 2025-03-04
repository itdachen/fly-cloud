import axios, {AxiosInstance, AxiosRequestConfig, AxiosResponse, ServerResponse} from "axios";
import {BASIC, TOKEN_TYPE, TIME_OUT, OPEN_URL, CONTENT_TYPE} from "@/fly/http/conf/config";
import {layer} from "@layui/layui-vue";

import {StringUtils} from '@/fly/utils/StringUtils';
import {ResultStatusCode} from "@/fly/http/enums/ResultStatusCode";
import {useRouter} from 'vue-router';
// import {useUserStore} from '@/store/user';

// const userStore = useUserStore();
const router = useRouter();

class ApiRequest {
    private instance: AxiosInstance;
    private readonly config: AxiosRequestConfig;

    constructor(option: AxiosRequestConfig) {
        this.config = option
        // 配置全局参数
        this.instance = axios.create(this.config)
        this.interceptors()
    }

    private interceptors() {
        /**
         * 请求拦截器
         */
        this.instance.interceptors.request.use((config) => {
                removePending(config)
                //    addPending(config)
                let url: string | undefined = config.url;
                if (undefined == url) {
                    console.warn('请求路径不能为空');
                    url = '/404'
                }
                // console.log('token ==>', userStore.token);
                // let access_token = userStore.token;
                config.headers = basicHeaders();
                // if (StringUtils.isEmpty(access_token)) {
                //     config.headers.Authorization = TOKEN_TYPE + 'access_token';
                // }
                let strings = StringUtils.split(OPEN_URL, ',');
                let isOpen = false; // false 需要拦截, true 的时候, 是不需要拦截的
                for (let i = 0; i < strings.length; i++) {
                    if (url.indexOf(strings[i]) !== -1) {
                        isOpen = true;
                        break
                    }
                }
                const token = this.getToken();
                //   let token = userStore.token;
                // 需要认证 但是没有token的存在
                if (!isOpen && StringUtils.isEmpty(token)) {
                    //  ElMessage.error('请先登录')
                    // 清空本地缓存
                    localStorage.clear();
                    // 路由跳转
                    router.push('/Login');
                }
                if (!StringUtils.isEmpty(token)) {
                    config.headers = {
                        'content-type': CONTENT_TYPE,
                        'Authorization': TOKEN_TYPE + token
                    }
                }
                return config
            }, (error) => Promise.reject(error)
        )

        /**
         * 相应拦截器
         */
        this.instance.interceptors.response.use((response: AxiosResponse<ServerResponse<any>>) => {
                removePending(response.config)
                const res = response.data;
                if (ResultStatusCode.SUCCESS === res.status) {
                    return res
                }
                if (ResultStatusCode.ERROR === res.status) {
                    layer.msg(res.msg, {time: 1500, icon: 2});
                    return Promise.reject(res);
                }
                //  ElMessage.error(res.msg)
                /* 登录 token 异常, 这里加入路由前置守卫, 跳转到登录页面 */
                if (res.status == ResultStatusCode.CLIENT_TOKEN_EXCEPTION) {
                    // 清空本地缓存
                    localStorage.clear();
                    // 路由跳转
                    router.push('/login')
                }
                return Promise.reject(res)
            }, (error) => {
                error.config && removePending(error.config)
                httpErrorStatusHandle(error)
                return Promise.reject(error)
            }
        )
    }

    /**
     * 获取 token
     */
    private getToken = () => {
        return localStorage.getItem('token') as string
    }

    /**
     * 请求操作
     * @param config
     */
    request(config: AxiosRequestConfig): Promise<ServerResponse<any>> {
        return this.instance.request<ServerResponse<any>, ServerResponse<any>>(config)
    }

    /**
     * get 请求路径
     * @param url 请求路径
     */
    get(url: string = '') {
        return this.request({
            url: url,
            method: 'GET',
        })
    }

    /**
     * post 请求
     * @param url  请求路径
     * @param data 请求参数
     */
    post(url: string = '', data: any = {}) {
        return this.request({
            url: url,
            method: 'POST',
            data: data
        })
    }

    /**
     * put 请求
     * @param url   请求路径
     * @param data  请求参数
     */
    put(url: string = '', data: any = {}) {
        return this.request({
            url: url,
            method: 'PUT',
            data: data
        })
    }

    /**
     * 删除请求
     * @param url 请求路径
     */
    delete(url: string = '') {
        return this.request({
            url: url,
            method: 'DELETE'
        })
    }


}

export default ApiRequest;

/**
 * 处理异常
 * @param {*} error
 */
function httpErrorStatusHandle(error: any) {
    //  const userStore = useUserStore()
    // 处理被取消的请求
    if (axios.isCancel(error)) return console.error('请求的重复请求：' + error.message)
    let message = ''
    if (error && error.response) {
        switch (error.response.status) {
            case 429:
                message = '访问人数太多 请稍后再试！'
                break
            case 302:
                message = '接口重定向了！'
                break
            case 400:
                message = '参数不正确！'
                break
            case 401:
                message = '您未登录，或者登录已经超时，请先登录！'
                break
            case 403:
                message = '您没有权限操作！'
                break
            case 404:
                message = `请求地址出错: ${error.response.config.url}`
                break // 在正确域名下
            case 408:
                message = '请求超时！'
                break
            case 409:
                message = '系统已存在相同数据！'
                break
            case 500:
                message = '服务器内部错误！'
                break
            case 501:
                message = '服务未实现！'
                break
            case 502:
                message = '网关错误！'
                break
            case 503:
                message = '服务不可用！'
                break
            case 504:
                message = '服务暂时无法访问，请稍后再试！'
                break
            case 505:
                message = 'HTTP版本不受支持！'
                break
            default:
                message = '异常问题，请联系管理员！'
        }
    }
    if (error.message.includes('timeout')) {
        message = '网络请求超时！'
    }
    if (error.message.includes('Network')) {
        message = window.navigator.onLine ? '服务端异常！' : '您断网了！'
    }
    // ElMessage({
    //     type: 'error',
    //     message,
    // })
}

const pendingMap = new Map()

/**
 * 储存每个请求的唯一cancel回调, 以此为标识
 * @param {*} config
 */
function addPending(config: AxiosRequestConfig) {
    const pendingKey = getPendingKey(config)
    config.cancelToken = config.cancelToken || new axios.CancelToken((cancel) => {
        if (!pendingMap.has(pendingKey)) {
            pendingMap.set(pendingKey, cancel)
        }
    })
}

/**
 * 删除重复的请求
 * @param {*} config
 */
function removePending(config: AxiosRequestConfig) {
    const pendingKey = getPendingKey(config)
    if (pendingMap.has(pendingKey)) {
        const cancelToken = pendingMap.get(pendingKey)
        // 如你不明白此处为什么需要传递pendingKey可以看文章下方的补丁解释
        cancelToken(pendingKey)
        pendingMap.delete(pendingKey)
    }
}

/**
 * 生成唯一的每个请求的唯一key
 * @param {*} config
 * @returns
 */
function getPendingKey(config: AxiosRequestConfig) {
    let {url, method, params, data} = config
    if (typeof data === 'string') data = JSON.parse(data) // response里面返回的config.data是个字符串对象
    return [url, method, JSON.stringify(params), JSON.stringify(data)].join('&')
}

function basicHeaders() {
    return {
        'content-type': CONTENT_TYPE,
        'Authorization': BASIC
    }
}