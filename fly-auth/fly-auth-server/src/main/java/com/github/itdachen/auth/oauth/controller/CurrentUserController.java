package com.github.itdachen.auth.oauth.controller;

import com.github.itdachen.framework.context.annotation.CurrentUser;
import com.github.itdachen.framework.context.userdetails.CurrentUserDetails;
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

    @GetMapping("/details")
    public ServerResponse<CurrentUserDetails> userDetails(@CurrentUser CurrentUserDetails userDetails) {
        return ServerResponse.okData(userDetails);
    }

}
