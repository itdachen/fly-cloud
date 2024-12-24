package com.github.itdachen.admin.service;

import com.github.itdachen.admin.sdk.dto.DeptInfoDTO;
import com.github.itdachen.admin.sdk.query.DeptInfoQuery;
import com.github.itdachen.admin.sdk.vo.DeptInfoVO;
import com.github.itdachen.framework.context.tree.lay.LayTree;
import com.github.itdachen.framework.webmvc.service.IBizService;

import java.util.List;

/**
 * 部门信息 业务接口
 *
 * @author 王大宸
 * @date 2024-12-22 22:30:09
 */
public interface IDeptInfoService extends IBizService<DeptInfoDTO, DeptInfoVO, DeptInfoQuery, String> {


    LayTree findDeptTree() throws Exception;


}
