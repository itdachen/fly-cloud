package com.github.itdachen.admin.controller;

import com.github.itdachen.admin.service.IClazzInfoService;
import com.github.itdachen.admin.sdk.dto.ClazzInfoDTO;
import com.github.itdachen.admin.sdk.query.ClazzInfoQuery;
import com.github.itdachen.admin.sdk.vo.ClazzInfoVO;
import com.github.itdachen.framework.context.annotation.FuncTitle;
import com.github.itdachen.framework.webmvc.controller.BizController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 岗位信息
 *
 * @author 王大宸
 * @date 2024-12-28 18:40:48
 */
@RestController(value = "com.github.itdachen.admin.controller.ClazzInfoController")
@RequestMapping("/clazz/info")
@FuncTitle("岗位信息")
public class ClazzInfoController extends BizController<IClazzInfoService, ClazzInfoDTO, ClazzInfoVO, ClazzInfoQuery, String> {
    private static final Logger logger = LoggerFactory.getLogger(ClazzInfoController.class);

}