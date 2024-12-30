package com.github.itdachen.admin.service;

import com.github.itdachen.admin.sdk.dto.ElementInfoDTO;
import com.github.itdachen.admin.sdk.query.ElementInfoQuery;
import com.github.itdachen.admin.sdk.vo.ElementInfoVO;
import com.github.itdachen.framework.webmvc.service.IBizService;

/**
 * 资源(按钮)信息 业务接口
 *
 * @author 王大宸
 * @date 2024-12-30 10:26:53
 */
public interface IElementInfoService extends IBizService< ElementInfoDTO, ElementInfoVO, ElementInfoQuery, String > {

}
