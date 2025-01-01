package com.github.itdachen.auth.service;

import com.github.itdachen.auth.sdk.dto.AuthClazzRoleDTO;
import com.github.itdachen.auth.sdk.query.AuthClazzRoleQuery;
import com.github.itdachen.auth.sdk.vo.AuthClazzRoleVO;
import com.github.itdachen.framework.webmvc.service.IBizService;

/**
 * 身份岗位管理表 业务接口
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:50
 */
public interface IAuthClazzRoleService extends IBizService< AuthClazzRoleDTO, AuthClazzRoleVO, AuthClazzRoleQuery, String > {

}
