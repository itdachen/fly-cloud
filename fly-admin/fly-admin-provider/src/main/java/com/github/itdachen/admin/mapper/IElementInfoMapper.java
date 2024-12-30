package com.github.itdachen.admin.mapper;

import com.github.itdachen.admin.entity.ElementInfo;
import com.github.itdachen.admin.sdk.query.ElementInfoQuery;
import com.github.itdachen.admin.sdk.vo.ElementInfoVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 资源(按钮)信息 持久层接口
 *
 * @author 王大宸
 * @date 2024-12-30 10:26:53
 */
public interface IElementInfoMapper extends Mapper< ElementInfo > {

    /***
     * 查询集合
     *
     * @author 王大宸
     * @date 2024-12-30 10:26:53
     * @param params params
     * @return com.github.itdachen.admin.sdk.vo.elementInfoVO
     */
    List< ElementInfoVO > list(ElementInfoQuery params);

    /***
     * 根据id查询
     *
     * @author 王大宸
     * @date 2024-12-30 10:26:53
     * @param id 需要查询的数据id
     * @return com.github.itdachen.admin.sdk.vo.elementInfoVo
     */
        ElementInfoVO selectElementInfoVO( String id);

    /***
     * 查询导出数据
     *
     * @author 王大宸
     * @date 2024-12-30 10:26:53
     * @param params 查询参数
     * @return java.util.LinkedHashMap
     */
    List< LinkedHashMap< String, String > > selectElementInfoExpData(ElementInfoQuery params);

    /***
     * 批量添加
     *
     * @author 王大宸
     * @date 2024-12-30 10:26:53
     * @param list java.util.List<com.github.itdachen.admin.entity.ElementInfo>
     */
    void  batchSave(List< ElementInfo > list);

    /***
     * 批量修改
     *
     * @author 王大宸
     * @date 2024-12-30 10:26:53
     * @param list java.util.List<com.github.itdachen.admin.entity.ElementInfo>
     */
    void batchUpdate(List< ElementInfo > list);

}
