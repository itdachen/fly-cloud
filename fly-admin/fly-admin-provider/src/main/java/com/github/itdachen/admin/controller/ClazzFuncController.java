package com.github.itdachen.admin.controller;

import com.github.itdachen.admin.service.IClazzFuncService;
import com.github.itdachen.admin.sdk.dto.ClazzFuncDTO;
import com.github.itdachen.admin.sdk.query.ClazzFuncQuery;
import com.github.itdachen.admin.sdk.vo.ClazzFuncVO;
import com.github.itdachen.framework.context.annotation.FuncTitle;
import com.github.itdachen.framework.core.response.ServerResponse;
import com.github.itdachen.framework.webmvc.controller.BizController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 岗位职能管理表(确定之后不能修改)
 *
 * @author 王大宸
 * @date 2024-12-28 18:40:48
 */
@RestController(value = "com.github.itdachen.admin.controller.ClazzFuncController")
@RequestMapping("/clazz/func")
@FuncTitle("岗位职能管理")
public class ClazzFuncController extends BizController<IClazzFuncService, ClazzFuncDTO, ClazzFuncVO, ClazzFuncQuery, String> {
    private static final Logger logger = LoggerFactory.getLogger(ClazzFuncController.class);

    @GetMapping("/list")
    public ServerResponse<List<ClazzFuncVO>> findClazzFuncList() throws Exception {
        return ServerResponse.ok(bizService.findClazzFuncList());
    }


}