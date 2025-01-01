package com.github.itdachen.auth.service.impl;

import com.github.itdachen.framework.core.response.TableData;
import com.github.itdachen.framework.webmvc.service.impl.BizServiceImpl;
import com.github.itdachen.framework.webmvc.poi.WorkBookUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.itdachen.auth.entity.AuthClazzRole;
import com.github.itdachen.auth.sdk.dto.AuthClazzRoleDTO;
import com.github.itdachen.auth.sdk.query.AuthClazzRoleQuery;
import com.github.itdachen.auth.sdk.vo.AuthClazzRoleVO;
import com.github.itdachen.auth.mapper.IAuthClazzRoleMapper;
import com.github.itdachen.auth.service.IAuthClazzRoleService;
import com.github.itdachen.auth.convert.AuthClazzRoleConvert;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 身份岗位管理表 业务实现
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:50
 */
@Service
public class AuthClazzRoleServiceImpl extends BizServiceImpl< IAuthClazzRoleMapper, AuthClazzRole, AuthClazzRoleDTO,  AuthClazzRoleVO, AuthClazzRoleQuery, String > implements IAuthClazzRoleService {
    private static final Logger logger = LoggerFactory.getLogger(AuthClazzRoleServiceImpl.class);
    private static final AuthClazzRoleConvert bizConvert = new AuthClazzRoleConvert();
    private final List<String> EXP_FIELDS = new ArrayList<>();
    public AuthClazzRoleServiceImpl() {
        super(bizConvert);
            EXP_FIELDS.add("应用ID");
            EXP_FIELDS.add("岗位代码");
            EXP_FIELDS.add("岗位名称");
            EXP_FIELDS.add("身份ID");
            EXP_FIELDS.add("身份名称");
            EXP_FIELDS.add("有效标志: Y-是;N-否");
    }

    /***
    * 分页
    *
    * @author 王大宸
    * @date 2025-01-01 17:12:50
    * @param params params
    * @return com.github.itdachen.framework.core.response.TableData<com.github.itdachen.auth.sdk.vo.authClazzRoleVo>
    */
    @Override
    public TableData< AuthClazzRoleVO > page(AuthClazzRoleQuery params) throws Exception {
        Page< AuthClazzRoleVO > page = PageHelper.startPage(params.getPage(), params.getLimit());
        List< AuthClazzRoleVO > list = bizMapper.list(params);
        return new TableData< AuthClazzRoleVO >(page.getTotal(), list);
    }


    /***
     * 导出
     *
     * @author 王大宸
     * @date 2025-01-01 17:12:50
     * @param params com.github.itdachen.auth.sdk.query.AuthClazzRoleQuery
     * @return void
     */
    @Override
    public void dataExpToExcel(HttpServletRequest request,
                               HttpServletResponse response,
                               AuthClazzRoleQuery params) throws Exception{
        List < LinkedHashMap < String, String > >  list = bizMapper.selectAuthClazzRoleExpData(params);
       WorkBookUtils.export(request, response)
                .params(params)
                .title("身份岗位管理表")
                .rowNum(true)
                .fields(EXP_FIELDS)
                .data(list)
                .execute();
    }

}
