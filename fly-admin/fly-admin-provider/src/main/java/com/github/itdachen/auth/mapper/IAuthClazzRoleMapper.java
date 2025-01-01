package com.github.itdachen.auth.mapper;

import com.github.itdachen.auth.entity.AuthClazzRole;
import com.github.itdachen.auth.sdk.query.AuthClazzRoleQuery;
import com.github.itdachen.auth.sdk.vo.AuthClazzRoleVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 身份岗位管理表 持久层接口
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:50
 */
public interface IAuthClazzRoleMapper extends Mapper< AuthClazzRole > {

    /***
     * 查询集合
     *
     * @author 王大宸
     * @date 2025-01-01 17:12:50
     * @param params params
     * @return com.github.itdachen.auth.sdk.vo.authClazzRoleVO
     */
    List< AuthClazzRoleVO > list(AuthClazzRoleQuery params);

    /***
     * 根据id查询
     *
     * @author 王大宸
     * @date 2025-01-01 17:12:50
     * @param id 需要查询的数据id
     * @return com.github.itdachen.auth.sdk.vo.authClazzRoleVo
     */
        AuthClazzRoleVO selectAuthClazzRoleVO( String id);

    /***
     * 查询导出数据
     *
     * @author 王大宸
     * @date 2025-01-01 17:12:50
     * @param params 查询参数
     * @return java.util.LinkedHashMap
     */
    List< LinkedHashMap< String, String > > selectAuthClazzRoleExpData(AuthClazzRoleQuery params);

    /***
     * 批量添加
     *
     * @author 王大宸
     * @date 2025-01-01 17:12:50
     * @param list java.util.List<com.github.itdachen.auth.entity.AuthClazzRole>
     */
    void  batchSave(List< AuthClazzRole > list);

    /***
     * 批量修改
     *
     * @author 王大宸
     * @date 2025-01-01 17:12:50
     * @param list java.util.List<com.github.itdachen.auth.entity.AuthClazzRole>
     */
    void batchUpdate(List< AuthClazzRole > list);

}
