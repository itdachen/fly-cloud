package com.github.itdachen.admin.service;

import com.github.itdachen.admin.sdk.dto.DeptFuncDTO;
import com.github.itdachen.admin.sdk.query.DeptFuncQuery;
import com.github.itdachen.admin.sdk.vo.DeptFuncVO;
import com.github.itdachen.framework.webmvc.service.IBizService;

import java.util.List;

/**
 * 部门职能 业务接口
 *
 * @author 王大宸
 * @date 2024-12-20 14:40:35
 */
public interface IDeptFuncService extends IBizService< DeptFuncDTO, DeptFuncVO, DeptFuncQuery, String > {


    List<DeptFuncVO> findDeptFuncList() throws Exception;


}
