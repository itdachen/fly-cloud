package com.github.itdachen.auth.mapper;

import com.github.itdachen.auth.entity.AuthClazzMenu;
import com.github.itdachen.auth.sdk.query.AuthClazzMenuQuery;
import com.github.itdachen.auth.sdk.vo.AuthClazzMenuVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 岗位菜单 持久层接口
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:50
 */
public interface IAuthClazzMenuMapper extends Mapper< AuthClazzMenu > {

    /***
     * 查询集合
     *
     * @author 王大宸
     * @date 2025-01-01 17:12:50
     * @param params params
     * @return com.github.itdachen.auth.sdk.vo.authClazzMenuVO
     */
    List< AuthClazzMenuVO > list(AuthClazzMenuQuery params);

    /***
     * 根据id查询
     *
     * @author 王大宸
     * @date 2025-01-01 17:12:50
     * @param id 需要查询的数据id
     * @return com.github.itdachen.auth.sdk.vo.authClazzMenuVo
     */
        AuthClazzMenuVO selectAuthClazzMenuVO( String id);

    /***
     * 查询导出数据
     *
     * @author 王大宸
     * @date 2025-01-01 17:12:50
     * @param params 查询参数
     * @return java.util.LinkedHashMap
     */
    List< LinkedHashMap< String, String > > selectAuthClazzMenuExpData(AuthClazzMenuQuery params);

    /***
     * 批量添加
     *
     * @author 王大宸
     * @date 2025-01-01 17:12:50
     * @param list java.util.List<com.github.itdachen.auth.entity.AuthClazzMenu>
     */
    void  batchSave(List< AuthClazzMenu > list);

    /***
     * 批量修改
     *
     * @author 王大宸
     * @date 2025-01-01 17:12:50
     * @param list java.util.List<com.github.itdachen.auth.entity.AuthClazzMenu>
     */
    void batchUpdate(List< AuthClazzMenu > list);

}
