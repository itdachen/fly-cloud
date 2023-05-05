package com.github.itdachen.auth.oauth.controller;


import com.github.itdachen.framework.core.response.ServerResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * 获取菜单路由
 *
 * @author 王大宸
 * @date 2023/5/5 14:48
 */
@RestController
@RequestMapping(value = "/authorized/client")
public class AuthorizedClientRoutesController {

    /***
     * 获取菜单/路由
     *
     * @author 王大宸
     * @date 2023/5/5 14:56
     * @return com.github.itdachen.framework.core.response.ServerResponse<java.lang.Object>
     */
    @GetMapping("/routes")
    public ServerResponse<Object> clientRoutes() throws Exception {
        return null;
    }

    /***
     * 获取权限编码
     *
     * @author 王大宸
     * @date 2023/5/5 14:57
     * @return com.github.itdachen.framework.core.response.ServerResponse<java.lang.Object>
     */
    @GetMapping("/permission")
    public ServerResponse<Object> clientPermission() throws Exception {
        return null;
    }

}
