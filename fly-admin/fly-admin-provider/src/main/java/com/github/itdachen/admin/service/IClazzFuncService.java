package com.github.itdachen.admin.service;

import com.github.itdachen.admin.sdk.dto.ClazzFuncDTO;
import com.github.itdachen.admin.sdk.query.ClazzFuncQuery;
import com.github.itdachen.admin.sdk.vo.ClazzFuncVO;
import com.github.itdachen.framework.webmvc.service.IBizService;

import java.util.List;

/**
 * 岗位职能管理表(确定之后不能修改) 业务接口
 *
 * @author 王大宸
 * @date 2024-12-28 18:40:48
 */
public interface IClazzFuncService extends IBizService<ClazzFuncDTO, ClazzFuncVO, ClazzFuncQuery, String> {


    /***
     * 获取可用列表
     *
     * @author 王大宸
     * @date 2024/12/28 19:44
     * @return java.util.List<com.github.itdachen.admin.sdk.vo.ClazzFuncVO>
     */
    List<ClazzFuncVO> findClazzFuncList() throws Exception;

}
