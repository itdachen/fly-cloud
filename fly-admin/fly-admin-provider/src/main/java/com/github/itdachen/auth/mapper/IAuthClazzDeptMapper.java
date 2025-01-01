package com.github.itdachen.auth.mapper;

import com.github.itdachen.auth.entity.AuthClazzDept;
import com.github.itdachen.auth.sdk.query.AuthClazzDeptQuery;
import com.github.itdachen.auth.sdk.vo.AuthClazzDeptVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 部门岗位关联表 持久层接口
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:50
 */
public interface IAuthClazzDeptMapper extends Mapper<AuthClazzDept> {

    /***
     * 查询集合
     *
     * @author 王大宸
     * @date 2025-01-01 17:12:50
     * @param params params
     * @return com.github.itdachen.auth.sdk.vo.authClazzDeptVO
     */
    List<AuthClazzDeptVO> list(AuthClazzDeptQuery params);

    /***
     * 根据id查询
     *
     * @author 王大宸
     * @date 2025-01-01 17:12:50
     * @param id 需要查询的数据id
     * @return com.github.itdachen.auth.sdk.vo.authClazzDeptVo
     */
    AuthClazzDeptVO selectAuthClazzDeptVO(String id);

    /***
     * 查询导出数据
     *
     * @author 王大宸
     * @date 2025-01-01 17:12:50
     * @param params 查询参数
     * @return java.util.LinkedHashMap
     */
    List<LinkedHashMap<String, String>> selectAuthClazzDeptExpData(AuthClazzDeptQuery params);

    /***
     * 批量添加
     *
     * @author 王大宸
     * @date 2025-01-01 17:12:50
     * @param list java.util.List<com.github.itdachen.auth.entity.AuthClazzDept>
     */
    void batchSave(List<AuthClazzDept> list);

    /***
     * 批量修改
     *
     * @author 王大宸
     * @date 2025-01-01 17:12:50
     * @param list java.util.List<com.github.itdachen.auth.entity.AuthClazzDept>
     */
    void batchUpdate(List<AuthClazzDept> list);

}
