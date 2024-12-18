import {TIME_OUT} from "./conf/config";
import ApiRequest from "./Interceptor/AxiosInterceptors";

const httpAxios = new ApiRequest({
    baseURL: process.env.VITE_BASE_URL,
    timeout: TIME_OUT,
})

export default httpAxios