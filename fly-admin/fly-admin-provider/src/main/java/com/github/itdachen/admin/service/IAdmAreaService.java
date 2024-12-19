package com.github.itdachen.admin.service;

import com.github.itdachen.admin.sdk.dto.AdmAreaDTO;
import com.github.itdachen.admin.sdk.query.AdmAreaQuery;
import com.github.itdachen.admin.sdk.vo.AdmAreaVO;
import com.github.itdachen.framework.webmvc.service.IBizService;

/**
 * 行政区划代码 业务接口
 *
 * @author 王大宸
 * @date 2024-12-19 09:45:17
 */
public interface IAdmAreaService extends IBizService<AdmAreaDTO, AdmAreaVO, AdmAreaQuery, String> {

}
