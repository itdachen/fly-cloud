import httpAxios from "@/fly/http/index";
import {ServerResponse} from "axios";
import {AccessTokenModel} from "./model/LoginModel";

/**
 * 认证接口
 */
class LoginApi {

    /**
     * 登录
     * @param params
     */
    login(params: any): Promise<ServerResponse<AccessTokenModel>> {
        return httpAxios.request({
            url: '/auth/oauth/jwt/token',
            method: 'POST',
            data: params
        })
    }


    captcha() {

    }


}


export default LoginApi;