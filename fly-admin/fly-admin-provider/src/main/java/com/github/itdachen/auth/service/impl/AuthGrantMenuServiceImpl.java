package com.github.itdachen.auth.service.impl;

import com.github.itdachen.framework.core.response.TableData;
import com.github.itdachen.framework.webmvc.service.impl.BizServiceImpl;
import com.github.itdachen.framework.webmvc.poi.WorkBookUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.itdachen.auth.entity.AuthGrantMenu;
import com.github.itdachen.auth.sdk.dto.AuthGrantMenuDTO;
import com.github.itdachen.auth.sdk.query.AuthGrantMenuQuery;
import com.github.itdachen.auth.sdk.vo.AuthGrantMenuVO;
import com.github.itdachen.auth.mapper.IAuthGrantMenuMapper;
import com.github.itdachen.auth.service.IAuthGrantMenuService;
import com.github.itdachen.auth.convert.AuthGrantMenuConvert;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 权限下发 业务实现
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:49
 */
@Service
public class AuthGrantMenuServiceImpl extends BizServiceImpl< IAuthGrantMenuMapper, AuthGrantMenu, AuthGrantMenuDTO,  AuthGrantMenuVO, AuthGrantMenuQuery, String > implements IAuthGrantMenuService {
    private static final Logger logger = LoggerFactory.getLogger(AuthGrantMenuServiceImpl.class);
    private static final AuthGrantMenuConvert bizConvert = new AuthGrantMenuConvert();
    private final List<String> EXP_FIELDS = new ArrayList<>();
    public AuthGrantMenuServiceImpl() {
        super(bizConvert);
            EXP_FIELDS.add("appID");
            EXP_FIELDS.add("用户ID/人员身份ID");
            EXP_FIELDS.add("菜单ID");
    }

    /***
    * 分页
    *
    * @author 王大宸
    * @date 2025-01-01 17:12:49
    * @param params params
    * @return com.github.itdachen.framework.core.response.TableData<com.github.itdachen.auth.sdk.vo.authGrantMenuVo>
    */
    @Override
    public TableData< AuthGrantMenuVO > page(AuthGrantMenuQuery params) throws Exception {
        Page< AuthGrantMenuVO > page = PageHelper.startPage(params.getPage(), params.getLimit());
        List< AuthGrantMenuVO > list = bizMapper.list(params);
        return new TableData< AuthGrantMenuVO >(page.getTotal(), list);
    }


    /***
     * 导出
     *
     * @author 王大宸
     * @date 2025-01-01 17:12:49
     * @param params com.github.itdachen.auth.sdk.query.AuthGrantMenuQuery
     * @return void
     */
    @Override
    public void dataExpToExcel(HttpServletRequest request,
                               HttpServletResponse response,
                               AuthGrantMenuQuery params) throws Exception{
        List < LinkedHashMap < String, String > >  list = bizMapper.selectAuthGrantMenuExpData(params);
       WorkBookUtils.export(request, response)
                .params(params)
                .title("权限下发")
                .rowNum(true)
                .fields(EXP_FIELDS)
                .data(list)
                .execute();
    }

}
