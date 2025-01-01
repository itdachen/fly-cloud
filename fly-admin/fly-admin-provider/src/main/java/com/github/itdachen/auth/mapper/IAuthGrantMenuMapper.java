package com.github.itdachen.auth.mapper;

import com.github.itdachen.auth.entity.AuthGrantMenu;
import com.github.itdachen.auth.sdk.query.AuthGrantMenuQuery;
import com.github.itdachen.auth.sdk.vo.AuthGrantMenuVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 权限下发 持久层接口
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:49
 */
public interface IAuthGrantMenuMapper extends Mapper< AuthGrantMenu > {

    /***
     * 查询集合
     *
     * @author 王大宸
     * @date 2025-01-01 17:12:49
     * @param params params
     * @return com.github.itdachen.auth.sdk.vo.authGrantMenuVO
     */
    List< AuthGrantMenuVO > list(AuthGrantMenuQuery params);

    /***
     * 根据id查询
     *
     * @author 王大宸
     * @date 2025-01-01 17:12:49
     * @param id 需要查询的数据id
     * @return com.github.itdachen.auth.sdk.vo.authGrantMenuVo
     */
        AuthGrantMenuVO selectAuthGrantMenuVO( String id);

    /***
     * 查询导出数据
     *
     * @author 王大宸
     * @date 2025-01-01 17:12:49
     * @param params 查询参数
     * @return java.util.LinkedHashMap
     */
    List< LinkedHashMap< String, String > > selectAuthGrantMenuExpData(AuthGrantMenuQuery params);

    /***
     * 批量添加
     *
     * @author 王大宸
     * @date 2025-01-01 17:12:49
     * @param list java.util.List<com.github.itdachen.auth.entity.AuthGrantMenu>
     */
    void  batchSave(List< AuthGrantMenu > list);

    /***
     * 批量修改
     *
     * @author 王大宸
     * @date 2025-01-01 17:12:49
     * @param list java.util.List<com.github.itdachen.auth.entity.AuthGrantMenu>
     */
    void batchUpdate(List< AuthGrantMenu > list);

}
