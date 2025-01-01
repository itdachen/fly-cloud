package com.github.itdachen.auth.service.impl;

import com.github.itdachen.framework.core.response.TableData;
import com.github.itdachen.framework.webmvc.service.impl.BizServiceImpl;
import com.github.itdachen.framework.webmvc.poi.WorkBookUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.itdachen.auth.entity.AuthClazzMenu;
import com.github.itdachen.auth.sdk.dto.AuthClazzMenuDTO;
import com.github.itdachen.auth.sdk.query.AuthClazzMenuQuery;
import com.github.itdachen.auth.sdk.vo.AuthClazzMenuVO;
import com.github.itdachen.auth.mapper.IAuthClazzMenuMapper;
import com.github.itdachen.auth.service.IAuthClazzMenuService;
import com.github.itdachen.auth.convert.AuthClazzMenuConvert;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 岗位菜单 业务实现
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:50
 */
@Service
public class AuthClazzMenuServiceImpl extends BizServiceImpl< IAuthClazzMenuMapper, AuthClazzMenu, AuthClazzMenuDTO,  AuthClazzMenuVO, AuthClazzMenuQuery, String > implements IAuthClazzMenuService {
    private static final Logger logger = LoggerFactory.getLogger(AuthClazzMenuServiceImpl.class);
    private static final AuthClazzMenuConvert bizConvert = new AuthClazzMenuConvert();
    private final List<String> EXP_FIELDS = new ArrayList<>();
    public AuthClazzMenuServiceImpl() {
        super(bizConvert);
            EXP_FIELDS.add("应用ID");
            EXP_FIELDS.add("岗位代码");
            EXP_FIELDS.add("岗位名称");
            EXP_FIELDS.add("菜单ID/资源ID");
            EXP_FIELDS.add("菜单名称/资源名称");
    }

    /***
    * 分页
    *
    * @author 王大宸
    * @date 2025-01-01 17:12:50
    * @param params params
    * @return com.github.itdachen.framework.core.response.TableData<com.github.itdachen.auth.sdk.vo.authClazzMenuVo>
    */
    @Override
    public TableData< AuthClazzMenuVO > page(AuthClazzMenuQuery params) throws Exception {
        Page< AuthClazzMenuVO > page = PageHelper.startPage(params.getPage(), params.getLimit());
        List< AuthClazzMenuVO > list = bizMapper.list(params);
        return new TableData< AuthClazzMenuVO >(page.getTotal(), list);
    }


    /***
     * 导出
     *
     * @author 王大宸
     * @date 2025-01-01 17:12:50
     * @param params com.github.itdachen.auth.sdk.query.AuthClazzMenuQuery
     * @return void
     */
    @Override
    public void dataExpToExcel(HttpServletRequest request,
                               HttpServletResponse response,
                               AuthClazzMenuQuery params) throws Exception{
        List < LinkedHashMap < String, String > >  list = bizMapper.selectAuthClazzMenuExpData(params);
       WorkBookUtils.export(request, response)
                .params(params)
                .title("岗位菜单")
                .rowNum(true)
                .fields(EXP_FIELDS)
                .data(list)
                .execute();
    }

}
