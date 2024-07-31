package com.github.itdachen.auth.oauth.controller;

import com.github.itdachen.framework.context.annotation.CurrentUser;
import com.github.itdachen.framework.context.userdetails.UserInfoDetails;
import com.github.itdachen.framework.core.response.ServerResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * 获取用户信息
 *
 * @author 王大宸
 * @date 2023/5/5 19:17
 */
@RestController
@RequestMapping("/current/user")
public class CurrentUserController {

    /***
     * 获取用户信息
     *
     * @author 王大宸
     * @date 2024/7/30 9:40
     * @param userDetails userDetails
     * @return com.github.itdachen.framework.core.response.ServerResponse<com.github.itdachen.framework.context.userdetails.UserInfoDetails>
     */
    @GetMapping("/details")
    public ServerResponse<UserInfoDetails> userDetails(@CurrentUser UserInfoDetails userDetails) {
        return ServerResponse.ok(userDetails);
    }

}
