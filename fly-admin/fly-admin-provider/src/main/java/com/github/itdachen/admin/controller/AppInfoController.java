package com.github.itdachen.admin.controller;

import com.github.itdachen.admin.service.IAppInfoService;
import com.github.itdachen.admin.sdk.dto.AppInfoDTO;
import com.github.itdachen.admin.sdk.query.AppInfoQuery;
import com.github.itdachen.admin.sdk.vo.AppInfoVO;
import com.github.itdachen.framework.context.annotation.FuncTitle;
import com.github.itdachen.framework.core.response.ServerResponse;
import com.github.itdachen.framework.webmvc.controller.BizController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 应用信息
 *
 * @author 王大宸
 * @date 2024-09-06 10:16:14
 */
@RestController(value = "com.github.itdachen.admin.controller.AppInfoController")
@RequestMapping("/app/info")
@FuncTitle("应用信息")
public class AppInfoController extends BizController<IAppInfoService, AppInfoDTO, AppInfoVO, AppInfoQuery, String> {
    private static final Logger logger = LoggerFactory.getLogger(AppInfoController.class);


    /***
     * 获取秘钥
     *
     * @author 王大宸
     * @date 2024/12/18 20:59
     * @return com.github.itdachen.framework.core.response.ServerResponse<java.lang.String>
     */
    @GetMapping("/secret")
    public ServerResponse<String> createSecret() throws Exception {
        return ServerResponse.ok("获取秘钥成功！", bizService.createSecret());
    }


}