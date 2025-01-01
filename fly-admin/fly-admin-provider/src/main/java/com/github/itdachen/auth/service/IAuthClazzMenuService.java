package com.github.itdachen.auth.service;

import com.github.itdachen.auth.sdk.dto.AuthClazzMenuDTO;
import com.github.itdachen.auth.sdk.query.AuthClazzMenuQuery;
import com.github.itdachen.auth.sdk.vo.AuthClazzMenuVO;
import com.github.itdachen.framework.webmvc.service.IBizService;

/**
 * 岗位菜单 业务接口
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:50
 */
public interface IAuthClazzMenuService extends IBizService< AuthClazzMenuDTO, AuthClazzMenuVO, AuthClazzMenuQuery, String > {

}
