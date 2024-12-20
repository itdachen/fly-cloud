package com.github.itdachen.admin.mapper;

import com.github.itdachen.admin.entity.DeptFunc;
import com.github.itdachen.admin.sdk.query.DeptFuncQuery;
import com.github.itdachen.admin.sdk.vo.DeptFuncVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 部门职能 持久层接口
 *
 * @author 王大宸
 * @date 2024-12-20 14:40:35
 */
public interface IDeptFuncMapper extends Mapper<DeptFunc> {

    /***
     * 查询集合
     *
     * @author 王大宸
     * @date 2024-12-20 14:40:35
     * @param params params
     * @return com.github.itdachen.admin.sdk.vo.deptFuncVO
     */
    List<DeptFuncVO> list(DeptFuncQuery params);

    /***
     * 根据id查询
     *
     * @author 王大宸
     * @date 2024-12-20 14:40:35
     * @param id 需要查询的数据id
     * @return com.github.itdachen.admin.sdk.vo.deptFuncVo
     */
    DeptFuncVO selectDeptFuncVO(String id);

    /***
     * 查询导出数据
     *
     * @author 王大宸
     * @date 2024-12-20 14:40:35
     * @param params 查询参数
     * @return java.util.LinkedHashMap
     */
    List<LinkedHashMap<String, String>> selectDeptFuncExpData(DeptFuncQuery params);

    /***
     * 批量添加
     *
     * @author 王大宸
     * @date 2024-12-20 14:40:35
     * @param list java.util.List<com.github.itdachen.admin.entity.DeptFunc>
     */
    void batchSave(List<DeptFunc> list);

    /***
     * 批量修改
     *
     * @author 王大宸
     * @date 2024-12-20 14:40:35
     * @param list java.util.List<com.github.itdachen.admin.entity.DeptFunc>
     */
    void batchUpdate(List<DeptFunc> list);

}
