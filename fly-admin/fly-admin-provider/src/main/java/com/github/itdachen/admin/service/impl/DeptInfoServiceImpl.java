package com.github.itdachen.admin.service.impl;

import com.github.itdachen.framework.context.BizContextHandler;
import com.github.itdachen.framework.context.constants.DeptLevelConstants;
import com.github.itdachen.framework.context.constants.DeptLevelFormatConstants;
import com.github.itdachen.framework.context.constants.UserTypeConstant;
import com.github.itdachen.framework.context.constants.YesOrNotConstant;
import com.github.itdachen.framework.context.exception.BizException;
import com.github.itdachen.framework.context.tree.lay.LayTree;
import com.github.itdachen.framework.context.tree.lay.LayTreeNode;
import com.github.itdachen.framework.context.tree.lay.TreeNode;
import com.github.itdachen.framework.core.response.TableData;
import com.github.itdachen.framework.core.utils.StringUtils;
import com.github.itdachen.framework.webmvc.entity.EntityUtils;
import com.github.itdachen.framework.webmvc.poi.WorkBookUtils;
import com.github.itdachen.framework.webmvc.service.impl.BizServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.itdachen.admin.entity.DeptInfo;
import com.github.itdachen.admin.sdk.dto.DeptInfoDTO;
import com.github.itdachen.admin.sdk.query.DeptInfoQuery;
import com.github.itdachen.admin.sdk.vo.DeptInfoVO;
import com.github.itdachen.admin.mapper.IDeptInfoMapper;
import com.github.itdachen.admin.service.IDeptInfoService;
import com.github.itdachen.admin.convert.DeptInfoConvert;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 部门信息 业务实现
 *
 * @author 王大宸
 * @date 2024-12-22 22:30:09
 */
@Service
public class DeptInfoServiceImpl extends BizServiceImpl<IDeptInfoMapper, DeptInfo, DeptInfoDTO, DeptInfoVO, DeptInfoQuery, String> implements IDeptInfoService {
    private static final Logger logger = LoggerFactory.getLogger(DeptInfoServiceImpl.class);
    private static final DeptInfoConvert bizConvert = new DeptInfoConvert();
    private final List<String> EXP_FIELDS = new ArrayList<>();

    public DeptInfoServiceImpl() {
        super(bizConvert);
        EXP_FIELDS.add("部门名称");
        EXP_FIELDS.add("部门简称");
        EXP_FIELDS.add("是否管理本级");
        EXP_FIELDS.add("部门级次名称");
        EXP_FIELDS.add("联系电话");
        EXP_FIELDS.add("电子邮箱");
        EXP_FIELDS.add("传真");
        EXP_FIELDS.add("职能");
        EXP_FIELDS.add("所属省级名称");
        EXP_FIELDS.add("所属市州名称");
        EXP_FIELDS.add("所属区县名称");
        EXP_FIELDS.add("详细地址");
        EXP_FIELDS.add("有效标志");
    }

    /***
     * 分页
     *
     * @author 王大宸
     * @date 2024-12-22 22:30:09
     * @param params params
     * @return com.github.itdachen.framework.core.response.TableData<com.github.itdachen.admin.sdk.vo.deptInfoVo>
     */
    @Override
    public TableData<DeptInfoVO> page(DeptInfoQuery params) throws Exception {
        Page<DeptInfoVO> page = PageHelper.startPage(params.getPage(), params.getLimit());
        params.setDeleteFlag(YesOrNotConstant.N);
        List<DeptInfoVO> list = bizMapper.list(params);
        return new TableData<DeptInfoVO>(page.getTotal(), list);
    }

    /***
     * 新增
     *
     * @author 王大宸
     * @date 2024/12/25 16:17
     * @param deptInfoDTO deptInfoDTO
     * @return com.github.itdachen.admin.sdk.vo.DeptInfoVO
     */
    @Override
    public DeptInfoVO saveInfo(DeptInfoDTO deptInfoDTO) throws Exception {
        String areaCode = "";
        if (DeptLevelConstants.PROV_LEVEL.equals(deptInfoDTO.getLevelCode())) {
            areaCode = deptInfoDTO.getProvId();
        }
        if (DeptLevelConstants.CITY_LEVEL.equals(deptInfoDTO.getLevelCode())) {
            areaCode = deptInfoDTO.getCityId();
        }
        if (DeptLevelConstants.COUNT_LEVEL.equals(deptInfoDTO.getLevelCode())) {
            areaCode = deptInfoDTO.getCountyId();
        }
        String deptCode = DeptLevelFormatConstants.obtainDeptId(areaCode, deptInfoDTO.getLevelCode(), deptInfoDTO.getFuncCode());

        DeptInfoVO deptInfoVO = bizMapper.findDeptInfoVO(BizContextHandler.getTenantId(), deptCode);
        if (null != deptInfoVO) {
            String msg = "该部门职能已经存在！";
            if (YesOrNotConstant.Y.equals(deptInfoVO.getDeptFlag())) {
                msg += "处于删除状态！";
            }
            if (YesOrNotConstant.N.equals(deptInfoVO.getValidFlag())) {
                msg += "处于无效状态！";
            }
            throw new BizException(msg);
        }

        DeptInfo deptInfo = bizConvert.toJavaObject(deptInfoDTO);
        deptInfo.setDeleteFlag(YesOrNotConstant.N);
        EntityUtils.setCreatAndUpdateInfo(deptInfo);
        deptInfo.setDeptCode(deptCode);
        if (StringUtils.isEmpty(deptInfo.getTitleAs())) {
            deptInfo.setTitleAs(deptInfo.getTitle());
        }

        bizMapper.insertSelective(deptInfo);
        return bizConvert.toJavaObjectVO(deptInfo);
    }

    /***
     * 编辑
     *
     * @author 王大宸
     * @date 2024/12/25 16:18
     * @param deptInfoDTO deptInfoDTO
     * @return com.github.itdachen.admin.sdk.vo.DeptInfoVO
     */
    @Override
    public DeptInfoVO updateInfo(DeptInfoDTO deptInfoDTO) throws Exception {
        DeptInfoVO deptInfoDbVO = selectByPrimaryKey(deptInfoDTO.getId());
        DeptInfoVO deptInfoVO = bizMapper.findDeptInfoVO(BizContextHandler.getTenantId(), deptInfoDbVO.getDeptCode());
        if (null != deptInfoVO && !deptInfoVO.getDeptCode().equals(deptInfoDTO.getDeptCode())) {
            String msg = "该部门职能已经存在！";
            if (YesOrNotConstant.Y.equals(deptInfoVO.getDeptFlag())) {
                msg += "处于删除状态！";
            }
            if (YesOrNotConstant.N.equals(deptInfoVO.getValidFlag())) {
                msg += "处于无效状态！";
            }
            throw new BizException(msg);
        }

        DeptInfo deptInfo = bizConvert.toJavaObject(deptInfoDTO);
        deptInfo.setDeleteFlag(YesOrNotConstant.N);
        EntityUtils.setUpdatedInfo(deptInfo);
        if (StringUtils.isEmpty(deptInfo.getTitleAs())) {
            deptInfo.setTitleAs(deptInfo.getTitle());
        }
        deptInfo.setDeptCode(deptInfoDbVO.getDeptCode());
        bizMapper.updateByPrimaryKeySelective(deptInfo);
        return bizConvert.toJavaObjectVO(deptInfo);
    }

    /***
     * 删除, 逻辑删除
     *
     * @author 王大宸
     * @date 2024/12/25 16:15
     * @param id id
     * @return int
     */
    @Override
    public int deleteByPrimaryKey(String id) throws Exception {
        DeptInfo deptInfo = new DeptInfo();
        deptInfo.setId(id);
        deptInfo.setDeleteFlag(YesOrNotConstant.Y);
        deptInfo.setValidFlag(YesOrNotConstant.N);
        EntityUtils.setUpdatedInfo(deptInfo);
        return bizMapper.updateByPrimaryKeySelective(deptInfo);
    }

    /***
     * 导出
     *
     * @author 王大宸
     * @date 2024-12-22 22:30:09
     * @param params com.github.itdachen.admin.sdk.query.DeptInfoQuery
     * @return void
     */
    @Override
    public void dataExpToExcel(HttpServletRequest request,
                               HttpServletResponse response,
                               DeptInfoQuery params) throws Exception {
        List<LinkedHashMap<String, String>> list = bizMapper.selectDeptInfoExpData(params);
        WorkBookUtils.export(request, response)
                .params(params)
                .title("部门信息")
                .rowNum(true)
                .fields(EXP_FIELDS)
                .data(list)
                .execute();
    }

    @Override
    public LayTree findDeptTree(String deptFlag) throws Exception {
        TreeNode treeNode = null;
        String userType = BizContextHandler.getUserType();
        if (UserTypeConstant.SUPER_ADMINISTRATOR.equals(BizContextHandler.getUserType())) {
            treeNode = new TreeNode(BizContextHandler.getTenantId(), BizContextHandler.getTenantTitle(), true);
        } else {
            treeNode = new TreeNode(BizContextHandler.getDeptId(), BizContextHandler.getDeptTitle(), true);
        }


        List<TreeNode> list = new ArrayList<>();
        List<TreeNode> deptChildren = new ArrayList<>();

        if (UserTypeConstant.SUPER_ADMINISTRATOR.equals(BizContextHandler.getUserType())) {
            deptChildren = findDeptChildren(BizContextHandler.getTenantId(), BizContextHandler.getTenantId(), deptFlag);
        } else {
            deptChildren = findDeptChildren(BizContextHandler.getTenantId(), BizContextHandler.getDeptId(), deptFlag);
        }

        treeNode.setChildren(deptChildren);
        list.add(treeNode);

        List<String> checked = new ArrayList<>();
        checked.add(BizContextHandler.getTenantId());
        //  checked.add(BizContextHandler.getDeptId());
        return new LayTree(checked, list);
    }


    /***
     * 递归, 查询部门树
     *
     * @author 王大宸
     * @date 2024/12/28 15:47
     * @param tenantId tenantId
     * @param parentCode parentCode
     * @param deptFlag deptFlag
     * @return java.util.List<com.github.itdachen.framework.context.tree.lay.TreeNode>
     */
    private List<TreeNode> findDeptChildren(String tenantId, String parentCode, String deptFlag) {
        List<TreeNode> list = bizMapper.findDeptChildren(tenantId, parentCode, deptFlag);
        if (null == list || list.isEmpty()) {
            return new ArrayList<>();
        }

        List<TreeNode> children = new ArrayList<>();
        for (TreeNode treeNode : list) {
            children = bizMapper.findDeptChildren(tenantId, treeNode.getId(), deptFlag);
            if (null == children || children.isEmpty()) {
                continue;
            }
            treeNode.setChildren(children);
            findDeptChildren(tenantId, treeNode.getId(), deptFlag);
        }
        return list;
    }


}
