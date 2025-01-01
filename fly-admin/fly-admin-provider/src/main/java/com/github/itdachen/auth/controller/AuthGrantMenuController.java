package com.github.itdachen.auth.controller;

import com.github.itdachen.auth.service.IAuthGrantMenuService;
import com.github.itdachen.auth.sdk.dto.AuthGrantMenuDTO;
import com.github.itdachen.auth.sdk.query.AuthGrantMenuQuery;
import com.github.itdachen.auth.sdk.vo.AuthGrantMenuVO;
import com.github.itdachen.framework.context.annotation.FuncTitle;
import com.github.itdachen.framework.webmvc.controller.BizController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 权限下发
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:49
 */
@RestController(value = "com.github.itdachen.auth.controller.AuthGrantMenuController")
@RequestMapping("/auth/grant/menu")
@FuncTitle("权限下发")
public class AuthGrantMenuController extends BizController< IAuthGrantMenuService, AuthGrantMenuDTO, AuthGrantMenuVO, AuthGrantMenuQuery, String > {
    private static final Logger logger = LoggerFactory.getLogger(AuthGrantMenuController.class);

}