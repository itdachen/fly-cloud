package com.github.itdachen.admin.controller;

import com.github.itdachen.admin.service.IDeptFuncService;
import com.github.itdachen.admin.sdk.dto.DeptFuncDTO;
import com.github.itdachen.admin.sdk.query.DeptFuncQuery;
import com.github.itdachen.admin.sdk.vo.DeptFuncVO;
import com.github.itdachen.framework.context.annotation.FuncTitle;
import com.github.itdachen.framework.webmvc.controller.BizController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 部门职能
 *
 * @author 王大宸
 * @date 2024-12-20 14:40:35
 */
@Controller
@RequestMapping("/dept/func")
@FuncTitle("部门职能")
public class DeptFuncController extends BizController<IDeptFuncService, DeptFuncDTO, DeptFuncVO, DeptFuncQuery, String> {
    private static final Logger logger = LoggerFactory.getLogger(DeptFuncController.class);


}