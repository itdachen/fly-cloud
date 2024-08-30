/**
* 连接超时
*/
const TIME_OUT = 10000;

/**
 * SpringSecurity OAuth2 认证时, 需要携带的客户端认证
 */
const CLIENT_SECRET = 'com.github.itdachen';

/**
 * 不需要携带 token 的请求路径
 */
const OPEN_URL = '/api/open,/api/admin/open,/auth/oauth/jwt/token,/auth/oauth/jwt/refresh';

/**
 * 不携带 token 时的客户端秘钥
 */
const BASIC = 'Basic ' + CLIENT_SECRET;

/**
 * token 前缀
 */
const TOKEN_TYPE = 'Bearer ';

const CONTENT_TYPE = 'application/json;charset=UTF-8'


export {TIME_OUT, OPEN_URL, BASIC, TOKEN_TYPE, CONTENT_TYPE}