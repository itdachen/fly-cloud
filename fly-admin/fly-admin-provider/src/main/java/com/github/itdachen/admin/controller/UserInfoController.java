package com.github.itdachen.admin.controller;

import com.github.itdachen.admin.service.IUserInfoService;
import com.github.itdachen.admin.sdk.dto.UserInfoDTO;
import com.github.itdachen.admin.sdk.query.UserInfoQuery;
import com.github.itdachen.admin.sdk.vo.UserInfoVO;
import com.github.itdachen.framework.context.annotation.FuncTitle;
import com.github.itdachen.framework.webmvc.controller.BizController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理
 *
 * @author 王大宸
 * @date 2024-09-02 14:38:09
 */
@RestController(value = "com.github.itdachen.admin.controller.UserInfoController")
@RequestMapping("/user/info")
@FuncTitle("用户管理")
public class UserInfoController extends BizController<IUserInfoService, UserInfoDTO, UserInfoVO, UserInfoQuery, String> {
    private static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);

}