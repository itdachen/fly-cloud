package com.github.itdachen.admin.controller;

import com.github.itdachen.admin.service.IElementInfoService;
import com.github.itdachen.admin.sdk.dto.ElementInfoDTO;
import com.github.itdachen.admin.sdk.query.ElementInfoQuery;
import com.github.itdachen.admin.sdk.vo.ElementInfoVO;
import com.github.itdachen.framework.context.annotation.FuncTitle;
import com.github.itdachen.framework.webmvc.controller.BizController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 资源(按钮)信息
 *
 * @author 王大宸
 * @date 2024-12-30 10:26:53
 */
@RestController(value = "com.github.itdachen.admin.controller.ElementInfoController")
@RequestMapping("/element/info")
@FuncTitle("资源(按钮)信息")
public class ElementInfoController extends BizController< IElementInfoService, ElementInfoDTO, ElementInfoVO, ElementInfoQuery, String > {
    private static final Logger logger = LoggerFactory.getLogger(ElementInfoController.class);

}