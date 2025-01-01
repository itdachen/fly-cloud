package com.github.itdachen.auth.service.impl;

import com.github.itdachen.framework.core.response.TableData;
import com.github.itdachen.framework.webmvc.service.impl.BizServiceImpl;
import com.github.itdachen.framework.webmvc.poi.WorkBookUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.itdachen.auth.entity.AuthRoleMenu;
import com.github.itdachen.auth.sdk.dto.AuthRoleMenuDTO;
import com.github.itdachen.auth.sdk.query.AuthRoleMenuQuery;
import com.github.itdachen.auth.sdk.vo.AuthRoleMenuVO;
import com.github.itdachen.auth.mapper.IAuthRoleMenuMapper;
import com.github.itdachen.auth.service.IAuthRoleMenuService;
import com.github.itdachen.auth.convert.AuthRoleMenuConvert;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 人员身份菜单 业务实现
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:49
 */
@Service
public class AuthRoleMenuServiceImpl extends BizServiceImpl< IAuthRoleMenuMapper, AuthRoleMenu, AuthRoleMenuDTO,  AuthRoleMenuVO, AuthRoleMenuQuery, String > implements IAuthRoleMenuService {
    private static final Logger logger = LoggerFactory.getLogger(AuthRoleMenuServiceImpl.class);
    private static final AuthRoleMenuConvert bizConvert = new AuthRoleMenuConvert();
    private final List<String> EXP_FIELDS = new ArrayList<>();
    public AuthRoleMenuServiceImpl() {
        super(bizConvert);
            EXP_FIELDS.add("应用ID");
            EXP_FIELDS.add("人员身份ID");
            EXP_FIELDS.add("菜单ID");
    }

    /***
    * 分页
    *
    * @author 王大宸
    * @date 2025-01-01 17:12:49
    * @param params params
    * @return com.github.itdachen.framework.core.response.TableData<com.github.itdachen.auth.sdk.vo.authRoleMenuVo>
    */
    @Override
    public TableData< AuthRoleMenuVO > page(AuthRoleMenuQuery params) throws Exception {
        Page< AuthRoleMenuVO > page = PageHelper.startPage(params.getPage(), params.getLimit());
        List< AuthRoleMenuVO > list = bizMapper.list(params);
        return new TableData< AuthRoleMenuVO >(page.getTotal(), list);
    }


    /***
     * 导出
     *
     * @author 王大宸
     * @date 2025-01-01 17:12:49
     * @param params com.github.itdachen.auth.sdk.query.AuthRoleMenuQuery
     * @return void
     */
    @Override
    public void dataExpToExcel(HttpServletRequest request,
                               HttpServletResponse response,
                               AuthRoleMenuQuery params) throws Exception{
        List < LinkedHashMap < String, String > >  list = bizMapper.selectAuthRoleMenuExpData(params);
       WorkBookUtils.export(request, response)
                .params(params)
                .title("人员身份菜单")
                .rowNum(true)
                .fields(EXP_FIELDS)
                .data(list)
                .execute();
    }

}
