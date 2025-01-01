package com.github.itdachen.auth.service.impl;

import com.github.itdachen.admin.mapper.IClazzInfoMapper;
import com.github.itdachen.admin.sdk.vo.ClazzInfoVO;
import com.github.itdachen.framework.context.BizContextHandler;
import com.github.itdachen.framework.context.constants.YesOrNotConstant;
import com.github.itdachen.framework.context.exception.BizException;
import com.github.itdachen.framework.core.response.TableData;
import com.github.itdachen.framework.core.utils.StringUtils;
import com.github.itdachen.framework.webmvc.entity.EntityUtils;
import com.github.itdachen.framework.webmvc.service.impl.BizServiceImpl;
import com.github.itdachen.framework.webmvc.poi.WorkBookUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.itdachen.auth.entity.AuthClazzDept;
import com.github.itdachen.auth.sdk.dto.AuthClazzDeptDTO;
import com.github.itdachen.auth.sdk.query.AuthClazzDeptQuery;
import com.github.itdachen.auth.sdk.vo.AuthClazzDeptVO;
import com.github.itdachen.auth.mapper.IAuthClazzDeptMapper;
import com.github.itdachen.auth.service.IAuthClazzDeptService;
import com.github.itdachen.auth.convert.AuthClazzDeptConvert;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 部门岗位关联表 业务实现
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:50
 */
@Service
public class AuthClazzDeptServiceImpl extends BizServiceImpl<IAuthClazzDeptMapper, AuthClazzDept, AuthClazzDeptDTO, AuthClazzDeptVO, AuthClazzDeptQuery, String> implements IAuthClazzDeptService {
    private static final Logger logger = LoggerFactory.getLogger(AuthClazzDeptServiceImpl.class);
    private static final AuthClazzDeptConvert bizConvert = new AuthClazzDeptConvert();
    private final List<String> EXP_FIELDS = new ArrayList<>();

    @Autowired
    private IClazzInfoMapper clazzInfoMapper;

    public AuthClazzDeptServiceImpl() {
        super(bizConvert);
        EXP_FIELDS.add("部门代码");
        EXP_FIELDS.add("部门名称");
        EXP_FIELDS.add("部门层级");
        EXP_FIELDS.add("岗位名称");
    }

    /***
     * 分页
     *
     * @author 王大宸
     * @date 2025-01-01 17:12:50
     * @param params params
     * @return com.github.itdachen.framework.core.response.TableData<com.github.itdachen.auth.sdk.vo.authClazzDeptVo>
     */
    @Override
    public TableData<AuthClazzDeptVO> page(AuthClazzDeptQuery params) throws Exception {
        params.setTenantId(BizContextHandler.getTenantId());
        Page<AuthClazzDeptVO> page = PageHelper.startPage(params.getPage(), params.getLimit());
        List<AuthClazzDeptVO> list = bizMapper.list(params);
        return new TableData<AuthClazzDeptVO>(page.getTotal(), list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AuthClazzDeptVO saveInfo(AuthClazzDeptDTO authClazzDeptDTO) throws Exception {
        if (StringUtils.isEmpty(authClazzDeptDTO.getIds())) {
            throw new BizException("IDS 不能为空!");
        }
        List<String> clazzIds = new ArrayList<>(Arrays.asList(authClazzDeptDTO.getIds().split(",")));
        if (clazzIds.isEmpty()) {
            throw new BizException("请选择需要添加的岗位! ");
        }
        List<ClazzInfoVO> clazzInfos = clazzInfoMapper.findClazzInfoById(clazzIds);
        if (clazzIds.isEmpty()) {
            throw new BizException("岗位信息不存在, 请刷新数据! ");
        }

        List<AuthClazzDept> list = new ArrayList<>();
        AuthClazzDept clazzDept = new AuthClazzDept();
        for (ClazzInfoVO clazzInfo : clazzInfos) {
            clazzDept = bizConvert.toJavaObject(authClazzDeptDTO);
            clazzDept.setClazzCode(clazzInfo.getClazzCode());
            clazzDept.setClazzTitle(clazzInfo.getClazzTitle());
            clazzDept.setValidFlag(YesOrNotConstant.Y);
            clazzDept.setDeleteFlag(YesOrNotConstant.N);
            EntityUtils.setCreatAndUpdateInfo(clazzDept);
            list.add(clazzDept);
        }
        bizMapper.batchSave(list);
        return null;
    }

    /***
     * 导出
     *
     * @author 王大宸
     * @date 2025-01-01 17:12:50
     * @param params com.github.itdachen.auth.sdk.query.AuthClazzDeptQuery
     * @return void
     */
    @Override
    public void dataExpToExcel(HttpServletRequest request,
                               HttpServletResponse response,
                               AuthClazzDeptQuery params) throws Exception {
        List<LinkedHashMap<String, String>> list = bizMapper.selectAuthClazzDeptExpData(params);
        WorkBookUtils.export(request, response)
                .params(params)
                .title("部门岗位关联表")
                .rowNum(true)
                .fields(EXP_FIELDS)
                .data(list)
                .execute();
    }

}
