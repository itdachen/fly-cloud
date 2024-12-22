package com.github.itdachen.admin.mapper;

import com.github.itdachen.admin.entity.DeptLevel;
import com.github.itdachen.admin.sdk.query.DeptLevelQuery;
import com.github.itdachen.admin.sdk.vo.DeptLevelVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 部门等级管理 持久层接口
 *
 * @author 王大宸
 * @date 2024-12-22 20:30:44
 */
public interface IDeptLevelMapper extends Mapper<DeptLevel> {

    /***
     * 查询集合
     *
     * @author 王大宸
     * @date 2024-12-22 20:30:44
     * @param params params
     * @return com.github.itdachen.admin.sdk.vo.deptLevelVO
     */
    List<DeptLevelVO> list(DeptLevelQuery params);

    /***
     * 根据id查询
     *
     * @author 王大宸
     * @date 2024-12-22 20:30:44
     * @param id 需要查询的数据id
     * @return com.github.itdachen.admin.sdk.vo.deptLevelVo
     */
    DeptLevelVO selectDeptLevelVO(String id);

    /***
     * 查询导出数据
     *
     * @author 王大宸
     * @date 2024-12-22 20:30:44
     * @param params 查询参数
     * @return java.util.LinkedHashMap
     */
    List<LinkedHashMap<String, String>> selectDeptLevelExpData(DeptLevelQuery params);

    /***
     * 批量添加
     *
     * @author 王大宸
     * @date 2024-12-22 20:30:44
     * @param list java.util.List<com.github.itdachen.admin.entity.DeptLevel>
     */
    void batchSave(List<DeptLevel> list);

    /***
     * 批量修改
     *
     * @author 王大宸
     * @date 2024-12-22 20:30:44
     * @param list java.util.List<com.github.itdachen.admin.entity.DeptLevel>
     */
    void batchUpdate(List<DeptLevel> list);

    /***
     * 根据层级代码查询
     *
     * @author 王大宸
     * @date 2024/12/22 20:40
     * @param tenantId tenantId
     * @param levelCode levelCode
     * @return com.github.itdachen.admin.entity.DeptLevel
     */
    DeptLevelVO findDeptLevelByCode(String tenantId, String levelCode);

}
