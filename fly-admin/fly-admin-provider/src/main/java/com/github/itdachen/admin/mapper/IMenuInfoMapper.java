package com.github.itdachen.admin.mapper;

import com.github.itdachen.admin.entity.MenuInfo;
import com.github.itdachen.admin.sdk.query.MenuInfoQuery;
import com.github.itdachen.admin.sdk.vo.MenuInfoVO;
import com.github.itdachen.framework.context.tree.lay.TreeNode;
import tk.mybatis.mapper.common.Mapper;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 菜单信息 持久层接口
 *
 * @author 王大宸
 * @date 2024-12-30 10:26:52
 */
public interface IMenuInfoMapper extends Mapper<MenuInfo> {

    /***
     * 查询集合
     *
     * @author 王大宸
     * @date 2024-12-30 10:26:52
     * @param params params
     * @return com.github.itdachen.admin.sdk.vo.menuInfoVO
     */
    List<MenuInfoVO> list(MenuInfoQuery params);

    /***
     * 查询树
     *
     * @author 王大宸
     * @date 2024/12/31 10:38
     * @param parentId parentId
     * @return java.util.List<com.github.itdachen.framework.context.tree.lay.TreeNode>
     */
    List<TreeNode> findMenuChildren(String parentId);

    /***
     * 根据id查询
     *
     * @author 王大宸
     * @date 2024-12-30 10:26:52
     * @param id 需要查询的数据id
     * @return com.github.itdachen.admin.sdk.vo.menuInfoVo
     */
    MenuInfoVO selectMenuInfoVO(String id);

    /***
     * 查询导出数据
     *
     * @author 王大宸
     * @date 2024-12-30 10:26:52
     * @param params 查询参数
     * @return java.util.LinkedHashMap
     */
    List<LinkedHashMap<String, String>> selectMenuInfoExpData(MenuInfoQuery params);

    /***
     * 批量添加
     *
     * @author 王大宸
     * @date 2024-12-30 10:26:52
     * @param list java.util.List<com.github.itdachen.admin.entity.MenuInfo>
     */
    void batchSave(List<MenuInfo> list);

    /***
     * 批量修改
     *
     * @author 王大宸
     * @date 2024-12-30 10:26:52
     * @param list java.util.List<com.github.itdachen.admin.entity.MenuInfo>
     */
    void batchUpdate(List<MenuInfo> list);

}
