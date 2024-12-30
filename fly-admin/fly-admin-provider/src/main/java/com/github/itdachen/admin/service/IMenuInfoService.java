package com.github.itdachen.admin.service;

import com.github.itdachen.admin.sdk.dto.MenuInfoDTO;
import com.github.itdachen.admin.sdk.query.MenuInfoQuery;
import com.github.itdachen.admin.sdk.vo.MenuInfoVO;
import com.github.itdachen.framework.webmvc.service.IBizService;

/**
 * 菜单信息 业务接口
 *
 * @author 王大宸
 * @date 2024-12-30 10:26:52
 */
public interface IMenuInfoService extends IBizService< MenuInfoDTO, MenuInfoVO, MenuInfoQuery, String > {

}
