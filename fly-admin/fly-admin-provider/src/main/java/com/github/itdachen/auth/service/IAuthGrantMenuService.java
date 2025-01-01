package com.github.itdachen.auth.service;

import com.github.itdachen.auth.sdk.dto.AuthGrantMenuDTO;
import com.github.itdachen.auth.sdk.query.AuthGrantMenuQuery;
import com.github.itdachen.auth.sdk.vo.AuthGrantMenuVO;
import com.github.itdachen.framework.webmvc.service.IBizService;

/**
 * 权限下发 业务接口
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:49
 */
public interface IAuthGrantMenuService extends IBizService< AuthGrantMenuDTO, AuthGrantMenuVO, AuthGrantMenuQuery, String > {

}
