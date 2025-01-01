package com.github.itdachen.auth.service;

import com.github.itdachen.auth.sdk.dto.AuthRoleMenuDTO;
import com.github.itdachen.auth.sdk.query.AuthRoleMenuQuery;
import com.github.itdachen.auth.sdk.vo.AuthRoleMenuVO;
import com.github.itdachen.framework.webmvc.service.IBizService;

/**
 * 人员身份菜单 业务接口
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:49
 */
public interface IAuthRoleMenuService extends IBizService< AuthRoleMenuDTO, AuthRoleMenuVO, AuthRoleMenuQuery, String > {

}
