package com.github.itdachen.auth.controller;

import com.github.itdachen.auth.service.IAuthClazzMenuService;
import com.github.itdachen.auth.sdk.dto.AuthClazzMenuDTO;
import com.github.itdachen.auth.sdk.query.AuthClazzMenuQuery;
import com.github.itdachen.auth.sdk.vo.AuthClazzMenuVO;
import com.github.itdachen.framework.context.annotation.FuncTitle;
import com.github.itdachen.framework.webmvc.controller.BizController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 岗位菜单
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:50
 */
@RestController(value = "com.github.itdachen.auth.controller.AuthClazzMenuController")
@RequestMapping("/auth/clazz/menu")
@FuncTitle("岗位菜单")
public class AuthClazzMenuController extends BizController< IAuthClazzMenuService, AuthClazzMenuDTO, AuthClazzMenuVO, AuthClazzMenuQuery, String > {
    private static final Logger logger = LoggerFactory.getLogger(AuthClazzMenuController.class);

}