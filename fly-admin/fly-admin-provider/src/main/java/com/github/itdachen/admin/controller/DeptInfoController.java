package com.github.itdachen.admin.controller;

import com.github.itdachen.admin.service.IDeptInfoService;
import com.github.itdachen.admin.sdk.dto.DeptInfoDTO;
import com.github.itdachen.admin.sdk.query.DeptInfoQuery;
import com.github.itdachen.admin.sdk.vo.DeptInfoVO;
import com.github.itdachen.framework.context.annotation.FuncTitle;
import com.github.itdachen.framework.context.tree.lay.LayTree;
import com.github.itdachen.framework.core.response.ServerResponse;
import com.github.itdachen.framework.webmvc.controller.BizController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 部门信息
 *
 * @author 王大宸
 * @date 2024-12-22 22:30:09
 */
@Controller
@RequestMapping("/dept/info")
@FuncTitle("部门信息")
public class DeptInfoController extends BizController<IDeptInfoService, DeptInfoDTO, DeptInfoVO, DeptInfoQuery, String> {
    private static final Logger logger = LoggerFactory.getLogger(DeptInfoController.class);


    /***
     * 获取部门树
     *
     * @author 王大宸
     * @date 2024/12/24 10:37
     * @return com.github.itdachen.framework.core.response.ServerResponse<java.util.List < com.github.itdachen.framework.context.tree.LayTreeNode>>
     */
    @GetMapping("/tree")
    @ResponseBody
    public ServerResponse<LayTree> findDeptTree() throws Exception {
        return ServerResponse.ok(bizService.findDeptTree());
    }


}