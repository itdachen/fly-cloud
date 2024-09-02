package com.github.itdachen.admin.service;

import com.github.itdachen.admin.sdk.dto.UserInfoDTO;
import com.github.itdachen.admin.sdk.query.UserInfoQuery;
import com.github.itdachen.admin.sdk.vo.UserInfoVO;
import com.github.itdachen.framework.webmvc.service.IBizService;

/**
 * 用户管理 业务接口
 *
 * @author 王大宸
 * @date 2024-09-02 14:38:09
 */
public interface IUserInfoService extends IBizService< UserInfoDTO, UserInfoVO, UserInfoQuery, String > {

}
