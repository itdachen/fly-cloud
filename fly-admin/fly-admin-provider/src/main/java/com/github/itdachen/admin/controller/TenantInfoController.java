package com.github.itdachen.admin.controller;

import com.github.itdachen.admin.service.ITenantInfoService;
import com.github.itdachen.admin.sdk.dto.TenantInfoDTO;
import com.github.itdachen.admin.sdk.query.TenantInfoQuery;
import com.github.itdachen.admin.sdk.vo.TenantInfoVO;
import com.github.itdachen.framework.context.annotation.FuncTitle;
import com.github.itdachen.framework.webmvc.controller.BizController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 租户/公司信息
 *
 * @author 王大宸
 * @date 2024-10-15 16:27:41
 */
@RestController
@RequestMapping("/tenant/info")
@FuncTitle("租户/公司信息")
public class TenantInfoController extends BizController<ITenantInfoService, TenantInfoDTO, TenantInfoVO, TenantInfoQuery, String> {
    private static final Logger logger = LoggerFactory.getLogger(TenantInfoController.class);


}