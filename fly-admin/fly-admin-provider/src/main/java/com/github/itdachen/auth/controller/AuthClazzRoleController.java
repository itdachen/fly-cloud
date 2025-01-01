package com.github.itdachen.auth.controller;

import com.github.itdachen.auth.service.IAuthClazzRoleService;
import com.github.itdachen.auth.sdk.dto.AuthClazzRoleDTO;
import com.github.itdachen.auth.sdk.query.AuthClazzRoleQuery;
import com.github.itdachen.auth.sdk.vo.AuthClazzRoleVO;
import com.github.itdachen.framework.context.annotation.FuncTitle;
import com.github.itdachen.framework.webmvc.controller.BizController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 身份岗位管理表
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:50
 */
@RestController(value = "com.github.itdachen.auth.controller.AuthClazzRoleController")
@RequestMapping("/auth/clazz/role")
@FuncTitle("身份岗位管理表")
public class AuthClazzRoleController extends BizController< IAuthClazzRoleService, AuthClazzRoleDTO, AuthClazzRoleVO, AuthClazzRoleQuery, String > {
    private static final Logger logger = LoggerFactory.getLogger(AuthClazzRoleController.class);

}