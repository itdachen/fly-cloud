package com.github.itdachen.auth.controller;

import com.github.itdachen.auth.service.IAuthRoleMenuService;
import com.github.itdachen.auth.sdk.dto.AuthRoleMenuDTO;
import com.github.itdachen.auth.sdk.query.AuthRoleMenuQuery;
import com.github.itdachen.auth.sdk.vo.AuthRoleMenuVO;
import com.github.itdachen.framework.context.annotation.FuncTitle;
import com.github.itdachen.framework.webmvc.controller.BizController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 人员身份菜单
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:49
 */
@RestController(value = "com.github.itdachen.auth.controller.AuthRoleMenuController")
@RequestMapping("/auth/role/menu")
@FuncTitle("人员身份菜单")
public class AuthRoleMenuController extends BizController< IAuthRoleMenuService, AuthRoleMenuDTO, AuthRoleMenuVO, AuthRoleMenuQuery, String > {
    private static final Logger logger = LoggerFactory.getLogger(AuthRoleMenuController.class);

}