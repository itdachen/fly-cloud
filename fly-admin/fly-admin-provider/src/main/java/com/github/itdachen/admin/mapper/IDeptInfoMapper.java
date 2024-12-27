package com.github.itdachen.admin.mapper;

import com.github.itdachen.admin.entity.DeptInfo;
import com.github.itdachen.admin.sdk.query.DeptInfoQuery;
import com.github.itdachen.admin.sdk.vo.DeptInfoVO;
import com.github.itdachen.framework.context.tree.lay.TreeNode;
import tk.mybatis.mapper.common.Mapper;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 部门信息 持久层接口
 *
 * @author 王大宸
 * @date 2024-12-22 22:30:09
 */
public interface IDeptInfoMapper extends Mapper<DeptInfo> {

    /***
     * 查询集合
     *
     * @author 王大宸
     * @date 2024-12-22 22:30:09
     * @param params params
     * @return com.github.itdachen.admin.sdk.vo.deptInfoVO
     */
    List<DeptInfoVO> list(DeptInfoQuery params);

    /***
     * 根据部门职能编码查询部门次那次
     *
     * @author 王大宸
     * @date 2024/12/25 16:19
     * @param tenantId tenantId
     * @param funcCode funcCode
     * @return com.github.itdachen.admin.sdk.vo.DeptInfoVO
     */
    DeptInfoVO findDeptInfoByFuncCode(String tenantId, String parentId, String funcCode);

    /***
     * 根据id查询
     *
     * @author 王大宸
     * @date 2024-12-22 22:30:09
     * @param id 需要查询的数据id
     * @return com.github.itdachen.admin.sdk.vo.deptInfoVo
     */
    DeptInfoVO selectDeptInfoVO(String id);

    /***
     * 查询导出数据
     *
     * @author 王大宸
     * @date 2024-12-22 22:30:09
     * @param params 查询参数
     * @return java.util.LinkedHashMap
     */
    List<LinkedHashMap<String, String>> selectDeptInfoExpData(DeptInfoQuery params);

    /***
     * 批量添加
     *
     * @author 王大宸
     * @date 2024-12-22 22:30:09
     * @param list java.util.List<com.github.itdachen.admin.entity.DeptInfo>
     */
    void batchSave(List<DeptInfo> list);

    /***
     * 批量修改
     *
     * @author 王大宸
     * @date 2024-12-22 22:30:09
     * @param list java.util.List<com.github.itdachen.admin.entity.DeptInfo>
     */
    void batchUpdate(List<DeptInfo> list);

    /***
     * 根据上级ID获取下一级ID
     *
     * @author 王大宸
     * @date 2024/12/24 11:10
     * @param tenantId tenantId
     * @param parentId parentId
     * @return java.util.List<com.github.itdachen.framework.context.tree.LayTreeNode.TreeNode>
     */
    List<TreeNode> findDeptChildren(String tenantId, String parentId, String deptFlag);

}
