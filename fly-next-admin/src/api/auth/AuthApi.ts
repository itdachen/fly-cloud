import request from "/@/http";

/**
 * 认证接口
 */
export default function useAuthApi() {


    /**
     * 登录
     * @param data 登录数据
     */
    const login = (data: any) => {
        return request.post('/auth/oauth/jwt/token', data);
    }


    return {
        login
    }
}