package com.github.itdachen.admin.service;

import com.github.itdachen.admin.sdk.dto.TenantInfoDTO;
import com.github.itdachen.admin.sdk.query.TenantInfoQuery;
import com.github.itdachen.admin.sdk.vo.TenantInfoVO;
import com.github.itdachen.framework.webmvc.service.IBizService;

/**
 * 租户/公司信息 业务接口
 *
 * @author 王大宸
 * @date 2024-10-15 16:27:41
 */
public interface ITenantInfoService extends IBizService< TenantInfoDTO, TenantInfoVO, TenantInfoQuery, String > {

}
