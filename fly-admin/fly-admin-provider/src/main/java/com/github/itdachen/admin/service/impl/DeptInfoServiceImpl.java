package com.github.itdachen.admin.service.impl;

import com.github.itdachen.framework.core.response.TableData;
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
        List<DeptInfoVO> list = bizMapper.list(params);
        return new TableData<DeptInfoVO>(page.getTotal(), list);
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
        // List<LinkedHashMap<String, String>> list = bizMapper.selectDeptInfoExpData(params);
        // ExcelExportUtils.exportExcel( request, response, EXP_FIELDS, list, "部门信息", true);
    }

}
