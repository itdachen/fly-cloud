package com.github.itdachen.auth.controller;

import com.github.itdachen.auth.service.IAuthClazzDeptService;
import com.github.itdachen.auth.sdk.dto.AuthClazzDeptDTO;
import com.github.itdachen.auth.sdk.query.AuthClazzDeptQuery;
import com.github.itdachen.auth.sdk.vo.AuthClazzDeptVO;
import com.github.itdachen.framework.context.annotation.FuncTitle;
import com.github.itdachen.framework.webmvc.controller.BizController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 部门岗位关联表
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:50
 */
@RestController(value = "com.github.itdachen.auth.controller.AuthClazzDeptController")
@RequestMapping("/auth/clazz/dept")
@FuncTitle("部门岗位关联表")
public class AuthClazzDeptController extends BizController< IAuthClazzDeptService, AuthClazzDeptDTO, AuthClazzDeptVO, AuthClazzDeptQuery, String > {
    private static final Logger logger = LoggerFactory.getLogger(AuthClazzDeptController.class);

}