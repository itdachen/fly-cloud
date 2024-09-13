import HttpRequest from './request/index'
// import localCache from '../utils/cache'

/**
 * http(建议使用 utils 里面的 request)
 */
const request = new HttpRequest({
    baseURL: import.meta.env.VITE_API_URL,
    timeout: 50000,
    withCredentials: true, // 异步请求携带 cookie
    //  retry: 2, //请求失败时,Axios 自动重试的次数。
    //  retryDelay: 1000,//每次重试之间的延迟时间,单位为毫秒
    headers: { //设置请求头信息
        'Content-Type': 'application/json;charset=UTF-8',
        'Authorization': 'Bearer com.github.itdachen',
        'x-requested-with': 'XMLHttpRequest'
    },
    //  cache: true, // 开启当前接口缓存
    //  setExpireTime: 30000, // 当前接口缓存时限
    //   interceptors: {
    //       requestInterceptor: (config: any) => {
    //           const token = Session.get('token')
    //           if (token) {
    //               config.headers.Authorization = `Bearer ${token}`
    //           }
    //           console.log('请求成功的拦截');
    //           return config;
    //       },
    //       requestInterceptorCatch: (err: any) => {
    //           console.log('请求失败的拦截')
    //           return err;
    //       },
    //       responseInterceptor: (res: any) => {
    //           console.log('响应成功的拦截')
    //           return res;
    //       },
    //       responseInterceptorCatch: (err: any) => {
    //           console.log('响应失败的拦截')
    //           return err;
    //       }
    //   }
})
export default request
