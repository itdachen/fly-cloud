package com.github.itdachen.admin.service;

import com.github.itdachen.admin.sdk.dto.ClazzInfoDTO;
import com.github.itdachen.admin.sdk.query.ClazzInfoQuery;
import com.github.itdachen.admin.sdk.vo.ClazzInfoVO;
import com.github.itdachen.framework.webmvc.service.IBizService;

/**
 * 岗位信息 业务接口
 *
 * @author 王大宸
 * @date 2024-12-28 18:40:48
 */
public interface IClazzInfoService extends IBizService< ClazzInfoDTO, ClazzInfoVO, ClazzInfoQuery, String > {

}
