package com.github.itdachen.admin.mapper;

import com.github.itdachen.admin.entity.ClazzFunc;
import com.github.itdachen.admin.sdk.query.ClazzFuncQuery;
import com.github.itdachen.admin.sdk.vo.ClazzFuncVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 岗位职能管理表(确定之后不能修改) 持久层接口
 *
 * @author 王大宸
 * @date 2024-12-28 18:40:48
 */
public interface IClazzFuncMapper extends Mapper<ClazzFunc> {

    /***
     * 查询集合
     *
     * @author 王大宸
     * @date 2024-12-28 18:40:48
     * @param params params
     * @return com.github.itdachen.admin.sdk.vo.clazzFuncVO
     */
    List<ClazzFuncVO> list(ClazzFuncQuery params);

    List<ClazzFuncVO> findClazzFuncList(String tenantId);

    /***
     * 根据id查询
     *
     * @author 王大宸
     * @date 2024-12-28 18:40:48
     * @param id 需要查询的数据id
     * @return com.github.itdachen.admin.sdk.vo.clazzFuncVo
     */
    ClazzFuncVO selectClazzFuncVO(String id);

    /***
     * 根据租户ID跟岗位职能代码查询
     *
     * @author 王大宸
     * @date 2024/12/28 19:08
     * @param tenantId tenantId
     * @param funcCode funcCode
     * @return com.github.itdachen.admin.sdk.vo.ClazzFuncVO
     */
    ClazzFuncVO findClazzFuncVO(String tenantId, String funcCode);

    /***
     * 查询导出数据
     *
     * @author 王大宸
     * @date 2024-12-28 18:40:48
     * @param params 查询参数
     * @return java.util.LinkedHashMap
     */
    List<LinkedHashMap<String, String>> selectClazzFuncExpData(ClazzFuncQuery params);

    /***
     * 批量添加
     *
     * @author 王大宸
     * @date 2024-12-28 18:40:48
     * @param list java.util.List<com.github.itdachen.admin.entity.ClazzFunc>
     */
    void batchSave(List<ClazzFunc> list);

    /***
     * 批量修改
     *
     * @author 王大宸
     * @date 2024-12-28 18:40:48
     * @param list java.util.List<com.github.itdachen.admin.entity.ClazzFunc>
     */
    void batchUpdate(List<ClazzFunc> list);

}
