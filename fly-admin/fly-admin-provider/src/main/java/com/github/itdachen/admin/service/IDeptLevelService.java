package com.github.itdachen.admin.service;

import com.github.itdachen.admin.sdk.dto.DeptLevelDTO;
import com.github.itdachen.admin.sdk.query.DeptLevelQuery;
import com.github.itdachen.admin.sdk.vo.DeptLevelVO;
import com.github.itdachen.framework.webmvc.service.IBizService;

import java.util.List;

/**
 * 部门等级管理 业务接口
 *
 * @author 王大宸
 * @date 2024-12-22 20:30:44
 */
public interface IDeptLevelService extends IBizService< DeptLevelDTO, DeptLevelVO, DeptLevelQuery, String > {


    List<DeptLevelVO> findDeptLevelList() throws Exception;

}
