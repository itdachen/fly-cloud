package com.github.itdachen.auth.mapper;

import com.github.itdachen.auth.entity.AuthRoleMenu;
import com.github.itdachen.auth.sdk.query.AuthRoleMenuQuery;
import com.github.itdachen.auth.sdk.vo.AuthRoleMenuVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 人员身份菜单 持久层接口
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:49
 */
public interface IAuthRoleMenuMapper extends Mapper< AuthRoleMenu > {

    /***
     * 查询集合
     *
     * @author 王大宸
     * @date 2025-01-01 17:12:49
     * @param params params
     * @return com.github.itdachen.auth.sdk.vo.authRoleMenuVO
     */
    List< AuthRoleMenuVO > list(AuthRoleMenuQuery params);

    /***
     * 根据id查询
     *
     * @author 王大宸
     * @date 2025-01-01 17:12:49
     * @param id 需要查询的数据id
     * @return com.github.itdachen.auth.sdk.vo.authRoleMenuVo
     */
        AuthRoleMenuVO selectAuthRoleMenuVO( String id);

    /***
     * 查询导出数据
     *
     * @author 王大宸
     * @date 2025-01-01 17:12:49
     * @param params 查询参数
     * @return java.util.LinkedHashMap
     */
    List< LinkedHashMap< String, String > > selectAuthRoleMenuExpData(AuthRoleMenuQuery params);

    /***
     * 批量添加
     *
     * @author 王大宸
     * @date 2025-01-01 17:12:49
     * @param list java.util.List<com.github.itdachen.auth.entity.AuthRoleMenu>
     */
    void  batchSave(List< AuthRoleMenu > list);

    /***
     * 批量修改
     *
     * @author 王大宸
     * @date 2025-01-01 17:12:49
     * @param list java.util.List<com.github.itdachen.auth.entity.AuthRoleMenu>
     */
    void batchUpdate(List< AuthRoleMenu > list);

}
