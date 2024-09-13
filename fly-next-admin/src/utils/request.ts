import axios, {AxiosInstance} from 'axios';
import {ElMessage, ElMessageBox} from 'element-plus';
import {Session} from '/@/utils/storage';
import qs from 'qs';

// 配置新建一个 axios 实例
const service: AxiosInstance = axios.create({
    baseURL: import.meta.env.VITE_API_URL,
    timeout: 50000,
    headers: { //设置请求头信息
        'Content-Type': 'application/json;charset=UTF-8',
        'Authorization': 'Bearer com.github.itdachen',
        'x-requested-with': 'XMLHttpRequest'
    },
    paramsSerializer: {
        serialize(params) {
            return qs.stringify(params, {allowDots: true});
        },
    },
});

// 添加请求拦截器
service.interceptors.request.use(
    (config) => {
        // 在发送请求之前做些什么 token
        if (Session.get('token')) {
            config.headers!['Authorization'] = 'Bearer ' + `${Session.get('token')}`;
        }
        return config;
    },
    (error) => {
        // 对请求错误做些什么
        return Promise.reject(error);
    }
);

// 添加响应拦截器
service.interceptors.response.use(
    (response) => {
        // 对响应数据做点什么
        const res = response.data;
        /* 操作成功, 返回状态码 200 */
        if (res.status && res.status === 200) {
            return res;
        }
        if (res.status === 401 || res.status === 4001) {
            Session.clear(); // 清除浏览器全部临时缓存
            window.location.href = '/'; // 去登录页
            ElMessageBox.alert('你已被登出，请重新登录', '提示', {}).then((res: any) => {
                console.log('登录信息失效, 请重新登录！', res)
            }).catch((err: any) => {
                console.log('登录信息失效, 请重新登录错误！', err)
            });
        }
        return Promise.reject(service.interceptors.response);
    },
    (error) => {
        // 对响应错误做点什么
        if (error.message.indexOf('timeout') != -1) {
            ElMessage.error('网络超时');
        } else if (error.message == 'Network Error') {
            ElMessage.error('网络连接错误');
        } else {
            if (error.response.data) ElMessage.error(error.response.statusText);
            else ElMessage.error('接口路径找不到');
        }
        return Promise.reject(error);
    }
);

// 导出 axios 实例
export default service;
