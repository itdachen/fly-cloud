package com.github.itdachen.admin.controller;

import com.github.itdachen.admin.service.IDeptLevelService;
import com.github.itdachen.admin.sdk.dto.DeptLevelDTO;
import com.github.itdachen.admin.sdk.query.DeptLevelQuery;
import com.github.itdachen.admin.sdk.vo.DeptLevelVO;
import com.github.itdachen.framework.context.annotation.FuncTitle;
import com.github.itdachen.framework.webmvc.controller.BizController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 部门等级管理
 *
 * @author 王大宸
 * @date 2024-12-22 20:30:44
 */
@RestController
@RequestMapping("/dept/level")
@FuncTitle("部门等级管理")
public class DeptLevelController extends BizController<IDeptLevelService, DeptLevelDTO, DeptLevelVO, DeptLevelQuery, String> {
    private static final Logger logger = LoggerFactory.getLogger(DeptLevelController.class);


}