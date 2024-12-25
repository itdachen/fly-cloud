package com.github.itdachen.admin.service.impl;

import com.github.itdachen.framework.context.BizContextHandler;
import com.github.itdachen.framework.context.constants.YesOrNotConstant;
import com.github.itdachen.framework.context.exception.BizException;
import com.github.itdachen.framework.core.response.TableData;
import com.github.itdachen.framework.webmvc.entity.EntityUtils;
import com.github.itdachen.framework.webmvc.service.impl.BizServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.itdachen.admin.entity.DeptLevel;
import com.github.itdachen.admin.sdk.dto.DeptLevelDTO;
import com.github.itdachen.admin.sdk.query.DeptLevelQuery;
import com.github.itdachen.admin.sdk.vo.DeptLevelVO;
import com.github.itdachen.admin.mapper.IDeptLevelMapper;
import com.github.itdachen.admin.service.IDeptLevelService;
import com.github.itdachen.admin.convert.DeptLevelConvert;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 部门等级管理 业务实现
 *
 * @author 王大宸
 * @date 2024-12-22 20:30:44
 */
@Service
public class DeptLevelServiceImpl extends BizServiceImpl<IDeptLevelMapper, DeptLevel, DeptLevelDTO, DeptLevelVO, DeptLevelQuery, String> implements IDeptLevelService {
    private static final Logger logger = LoggerFactory.getLogger(DeptLevelServiceImpl.class);
    private static final DeptLevelConvert bizConvert = new DeptLevelConvert();
    private final List<String> EXP_FIELDS = new ArrayList<>();

    public DeptLevelServiceImpl() {
        super(bizConvert);
        EXP_FIELDS.add("层级代码");
        EXP_FIELDS.add("层级名称,例如: 00-总部; 11-省级部门; 21-市州级部门; 31-区县级;41-乡镇级;51-社区/街道");
        EXP_FIELDS.add("有效标志: Y-是;N-否");
    }

    /***
     * 分页
     *
     * @author 王大宸
     * @date 2024-12-22 20:30:44
     * @param params params
     * @return com.github.itdachen.framework.core.response.TableData<com.github.itdachen.admin.sdk.vo.deptLevelVo>
     */
    @Override
    public TableData<DeptLevelVO> page(DeptLevelQuery params) throws Exception {
        Page<DeptLevelVO> page = PageHelper.startPage(params.getPage(), params.getLimit());
        params.setDeleteFlag(YesOrNotConstant.N);
        List<DeptLevelVO> list = bizMapper.list(params);
        return new TableData<DeptLevelVO>(page.getTotal(), list);
    }

    /***
     * 新增
     *
     * @author 王大宸
     * @date 2024/12/22 20:38
     * @param deptLevelDTO deptLevelDTO
     * @return com.github.itdachen.admin.sdk.vo.DeptLevelVO
     */
    @Override
    public DeptLevelVO saveInfo(DeptLevelDTO deptLevelDTO) throws Exception {
        DeptLevelVO deptLevelByCode = bizMapper.findDeptLevelByCode(BizContextHandler.getTenantId(), deptLevelDTO.getLevelCode());
        if (null != deptLevelByCode) {
            throw new BizException("层级编码 " + deptLevelDTO.getLevelCode() + " 已经存在！");
        }
        DeptLevel deptLevel = bizConvert.toJavaObject(deptLevelDTO);
        deptLevel.setDeleteFlag(YesOrNotConstant.N);
        EntityUtils.setCreatAndUpdateInfo(deptLevel);
        bizMapper.insertSelective(deptLevel);
        return bizConvert.toJavaObjectVO(deptLevel);
    }

    /***
     * 编辑
     *
     * @author 王大宸
     * @date 2024/12/22 20:38
     * @param deptLevelDTO deptLevelDTO
     * @return com.github.itdachen.admin.sdk.vo.DeptLevelVO
     */
    @Override
    public DeptLevelVO updateInfo(DeptLevelDTO deptLevelDTO) throws Exception {
        DeptLevelVO deptLevelByCode = bizMapper.findDeptLevelByCode(BizContextHandler.getTenantId(), deptLevelDTO.getLevelCode());
        if (null != deptLevelByCode && !deptLevelByCode.getId().equals(deptLevelDTO.getId())) {
            throw new BizException("层级编码 " + deptLevelDTO.getLevelCode() + " 已经存在！");
        }
        DeptLevel deptLevel = bizConvert.toJavaObject(deptLevelDTO);
        deptLevel.setDeleteFlag(YesOrNotConstant.N);
        EntityUtils.setUpdatedInfo(deptLevel);
        bizMapper.updateByPrimaryKeySelective(deptLevel);
        return bizConvert.toJavaObjectVO(deptLevel);
    }

    /***
     * 删除(逻辑删除)
     *
     * @author 王大宸
     * @date 2024/12/22 20:37
     * @param id id
     * @return int
     */
    @Override
    public int deleteByPrimaryKey(String id) throws Exception {
        DeptLevel deptLevel = new DeptLevel();
        deptLevel.setId(id);
        deptLevel.setDeleteFlag(YesOrNotConstant.Y);
        EntityUtils.setUpdatedInfo(deptLevel);
        return bizMapper.updateByPrimaryKeySelective(deptLevel);
    }

    /***
     * 导出
     *
     * @author 王大宸
     * @date 2024-12-22 20:30:44
     * @param params com.github.itdachen.admin.sdk.query.DeptLevelQuery
     * @return void
     */
    @Override
    public void dataExpToExcel(HttpServletRequest request,
                               HttpServletResponse response,
                               DeptLevelQuery params) throws Exception {
        // List<LinkedHashMap<String, String>> list = bizMapper.selectDeptLevelExpData(params);
        //  ExcelExportUtils.exportExcel( request, response, EXP_FIELDS, list, "部门等级管理", true);
    }


    @Override
    public List<DeptLevelVO> findDeptLevelList() throws Exception {
        return bizMapper.findDeptLevelList(BizContextHandler.getTenantId());
    }

}
