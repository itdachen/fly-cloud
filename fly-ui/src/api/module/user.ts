import Http from '../http';

export const login = function(loginForm: any) {
    console.log('loginForm', loginForm)
    return Http.post('/auth/oauth/jwt/token', loginForm)
}

export const menu = function() {
    return Http.get('/user/menu') 
}

export const permission = function() {
    return Http.get('/user/permission') 
}