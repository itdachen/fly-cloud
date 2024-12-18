/**
 * 登录时传参
 */
export interface LoginModel {
    username: string,
    password: string
}

/**
 * 登录成功, 返回数据类型
 */
export interface AccessTokenModel {
    access_token: string,
    token_type: string,
    refresh_token: string,
    expires_in: number | string,
    scope: string,
    info: UserInfo
}

/**
 * 登录成功, 返回的用户数据信息
 */
export interface UserInfo {
    avatar: string,
    departId: string,
    departTitle: string,
    email: string,
    postId: string,
    postTitle: string,
    sex: string,
    shopId: string,
    telephone: string,
    tenantId: string,
    userName: string,
    userType: string
}