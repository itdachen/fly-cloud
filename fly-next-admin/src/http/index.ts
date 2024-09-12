import axios from "axios";
import {TIMEOUT} from './conf/index';
import {Session} from "/@/utils/storage";


const httpRequest = axios.create({
    baseURL: process.env.VITE_BASE_URL, // 设置基本的请求 URL
    timeout: TIMEOUT, // 请求超时时间
    withCredentials: true, // 异步请求携带 cookie
    headers: { //设置请求头信息
        'Content-Type': 'application/json;charset=UTF-8',
        'Authorization': 'Bearer com.github.itdachen',
        'x-requested-with': 'XMLHttpRequest'
    },

    // interceptors: {
    //     requestInterceptor: (config) => {
    //           let token = Session.get('token')
    //         if (token) {
    //             if (config && config.headers) {
    //                 config.headers.Authorization = `Bearer ${token}`
    //             }
    //         }
    //         return config
    //     },
    //     requestInterceptorCatch: (err) => {
    //         console.log(err)
    //         return err
    //     },
    //     responseInterceptor: (config) => {
    //         return config
    //     },
    //     responseInterceptorCatch: (err) => {
    //         console.log(err)
    //         return err
    //     }
    // }
});

export default httpRequest;