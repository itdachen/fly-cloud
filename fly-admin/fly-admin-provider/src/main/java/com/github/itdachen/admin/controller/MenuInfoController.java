package com.github.itdachen.admin.controller;

import com.github.itdachen.admin.service.IMenuInfoService;
import com.github.itdachen.admin.sdk.dto.MenuInfoDTO;
import com.github.itdachen.admin.sdk.query.MenuInfoQuery;
import com.github.itdachen.admin.sdk.vo.MenuInfoVO;
import com.github.itdachen.framework.context.annotation.FuncTitle;
import com.github.itdachen.framework.context.tree.lay.LayTree;
import com.github.itdachen.framework.core.response.ServerResponse;
import com.github.itdachen.framework.webmvc.controller.BizController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 菜单信息
 *
 * @author 王大宸
 * @date 2024-12-30 10:26:52
 */
@RestController(value = "com.github.itdachen.admin.controller.MenuInfoController")
@RequestMapping("/menu/info")
@FuncTitle("菜单信息")
public class MenuInfoController extends BizController<IMenuInfoService, MenuInfoDTO, MenuInfoVO, MenuInfoQuery, String> {
    private static final Logger logger = LoggerFactory.getLogger(MenuInfoController.class);



    @GetMapping("/tree")
    @ResponseBody
    public ServerResponse<LayTree> findMenuTree(String appId) throws Exception {
        return ServerResponse.ok(bizService.findMenuTree(appId));
    }

    /***
     * 获取目录菜单树
     *
     * @author 王大宸
     * @date 2025/1/1 16:40
     * @param appId appId
     * @return com.github.itdachen.framework.core.response.ServerResponse<com.github.itdachen.framework.context.tree.lay.LayTree>
     */
    @GetMapping("/dirt/tree")
    @ResponseBody
    public ServerResponse<LayTree> findDirtMenuTree(String appId) throws Exception {
        return ServerResponse.ok(bizService.findDirtMenuTree(appId));
    }



}