package com.github.itdachen.auth.service;

import com.github.itdachen.auth.sdk.dto.AuthClazzDeptDTO;
import com.github.itdachen.auth.sdk.query.AuthClazzDeptQuery;
import com.github.itdachen.auth.sdk.vo.AuthClazzDeptVO;
import com.github.itdachen.framework.webmvc.service.IBizService;

/**
 * 部门岗位关联表 业务接口
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:50
 */
public interface IAuthClazzDeptService extends IBizService< AuthClazzDeptDTO, AuthClazzDeptVO, AuthClazzDeptQuery, String > {

}
