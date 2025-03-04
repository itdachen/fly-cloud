package com.github.itdachen.admin.service.impl;

import com.github.itdachen.framework.context.BizContextHandler;
import com.github.itdachen.framework.context.tree.lay.LayTree;
import com.github.itdachen.framework.context.tree.lay.TreeNode;
import com.github.itdachen.framework.core.response.TableData;
import com.github.itdachen.framework.core.utils.StringUtils;
import com.github.itdachen.framework.webmvc.service.impl.BizServiceImpl;
import com.github.itdachen.framework.webmvc.poi.WorkBookUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.itdachen.admin.entity.MenuInfo;
import com.github.itdachen.admin.sdk.dto.MenuInfoDTO;
import com.github.itdachen.admin.sdk.query.MenuInfoQuery;
import com.github.itdachen.admin.sdk.vo.MenuInfoVO;
import com.github.itdachen.admin.mapper.IMenuInfoMapper;
import com.github.itdachen.admin.service.IMenuInfoService;
import com.github.itdachen.admin.convert.MenuInfoConvert;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 菜单信息 业务实现
 *
 * @author 王大宸
 * @date 2024-12-30 10:26:52
 */
@Service
public class MenuInfoServiceImpl extends BizServiceImpl<IMenuInfoMapper, MenuInfo, MenuInfoDTO, MenuInfoVO, MenuInfoQuery, String> implements IMenuInfoService {
    private static final Logger logger = LoggerFactory.getLogger(MenuInfoServiceImpl.class);
    private static final MenuInfoConvert bizConvert = new MenuInfoConvert();
    private final List<String> EXP_FIELDS = new ArrayList<>();
    private static final String ROOT_ID = "4F17C3ED134AD4DA5C830DE9F3D7C02D";

    public MenuInfoServiceImpl() {
        super(bizConvert);
        EXP_FIELDS.add("应用ID/appId");
        EXP_FIELDS.add("上级ID");
        EXP_FIELDS.add("上级菜单");
        EXP_FIELDS.add("认证编码");
        EXP_FIELDS.add("类型:dirt-目录;menu-菜单;uri-外链;third-第三方平台");
        EXP_FIELDS.add("菜单标题");
        EXP_FIELDS.add("访问地址");
        EXP_FIELDS.add("图标");
        EXP_FIELDS.add("排序");
        EXP_FIELDS.add("是否展示/有效标志: Y-有效;N-无效");
        EXP_FIELDS.add("菜单打开方式");
        EXP_FIELDS.add("是否需要授权: Y-需要;N-不需要");
        EXP_FIELDS.add("是否需要白名单IP: Y-需要;N-不需要");
    }

    /***
     * 分页
     *
     * @author 王大宸
     * @date 2024-12-30 10:26:52
     * @param params params
     * @return com.github.itdachen.framework.core.response.TableData<com.github.itdachen.admin.sdk.vo.menuInfoVo>
     */
    @Override
    public TableData<MenuInfoVO> page(MenuInfoQuery params) throws Exception {
        if (null == params.getParentId()) {
            params.setParentId(ROOT_ID);
        }
        Page<MenuInfoVO> page = PageHelper.startPage(params.getPage(), params.getLimit());
        List<MenuInfoVO> list = bizMapper.list(params);
        return new TableData<MenuInfoVO>(page.getTotal(), list);
    }


    /***
     * 导出
     *
     * @author 王大宸
     * @date 2024-12-30 10:26:52
     * @param params com.github.itdachen.admin.sdk.query.MenuInfoQuery
     * @return void
     */
    @Override
    public void dataExpToExcel(HttpServletRequest request,
                               HttpServletResponse response,
                               MenuInfoQuery params) throws Exception {
        List<LinkedHashMap<String, String>> list = bizMapper.selectMenuInfoExpData(params);
        WorkBookUtils.export(request, response)
                .params(params)
                .title("菜单信息")
                .rowNum(true)
                .fields(EXP_FIELDS)
                .data(list)
                .execute();
    }

    /***
     * 获取菜单树
     *
     * @author 王大宸
     * @date 2024/12/31 10:26
     * @return com.github.itdachen.framework.context.tree.lay.LayTree
     */
    @Override
    public LayTree findMenuTree(String appId) throws Exception {
        if (StringUtils.isEmpty(appId)) {
            appId = ROOT_ID;
        }
        List<TreeNode> list = new ArrayList<>();
        TreeNode treeNode = new TreeNode(appId, "太虚十境", true);
        treeNode.setType("dirt");
        treeNode.setIcon("layui-icon-home");
        treeNode.setAttr1(appId);
        List<TreeNode> deptChildren = findMenuChildren(appId);

        treeNode.setChildren(deptChildren);

        list.add(treeNode);
        List<String> checked = new ArrayList<>();
        checked.add(appId);
        return new LayTree(checked, list);
    }


    /***
     * 获取目录菜单树
     *
     * @author 王大宸
     * @date 2025/1/1 15:28
     * @param appId appId
     * @return com.github.itdachen.framework.context.tree.lay.LayTree
     */
    @Override
    public LayTree findDirtMenuTree(String appId) throws Exception {
        if (StringUtils.isEmpty(appId)) {
            appId = ROOT_ID;
        }
        List<TreeNode> list = new ArrayList<>();
        TreeNode treeNode = new TreeNode(appId, "太虚十境", true);
        treeNode.setType("dirt");
        treeNode.setIcon("layui-icon-home");
        treeNode.setAttr1(appId);
        List<TreeNode> deptChildren = findDirtMenuTreeChildren(appId);

        treeNode.setChildren(deptChildren);

        list.add(treeNode);
        List<String> checked = new ArrayList<>();
        checked.add(appId);
        return new LayTree(checked, list);
    }

    /***
     * 获取目录菜单树
     *
     * @author 王大宸
     * @date 2025/1/1 15:28
     * @param parentId parentId
     * @return java.util.List<com.github.itdachen.framework.context.tree.lay.TreeNode>
     */
    private List<TreeNode> findDirtMenuTreeChildren(String parentId) {
        List<TreeNode> list = bizMapper.findDirtMenuTreeChildren(parentId);
        for (TreeNode treeNode : list) {
            treeNode.setChildren(findDirtMenuTreeChildren(treeNode.getId()));
        }
        return list;
    }

    /***
     * 获取菜单树
     *
     * @author 王大宸
     * @date 2025/1/1 15:28
     * @param parentId parentId
     * @return java.util.List<com.github.itdachen.framework.context.tree.lay.TreeNode>
     */
    private List<TreeNode> findMenuChildren(String parentId) {
        List<TreeNode> list = bizMapper.findMenuChildren(parentId);
        for (TreeNode treeNode : list) {
            treeNode.setChildren(findMenuChildren(treeNode.getId()));
        }
        return list;
    }

}
