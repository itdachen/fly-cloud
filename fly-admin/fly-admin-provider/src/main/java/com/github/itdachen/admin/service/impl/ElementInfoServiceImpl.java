package com.github.itdachen.admin.service.impl;

import com.github.itdachen.framework.core.response.TableData;
import com.github.itdachen.framework.webmvc.service.impl.BizServiceImpl;
import com.github.itdachen.framework.webmvc.poi.WorkBookUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.itdachen.admin.entity.ElementInfo;
import com.github.itdachen.admin.sdk.dto.ElementInfoDTO;
import com.github.itdachen.admin.sdk.query.ElementInfoQuery;
import com.github.itdachen.admin.sdk.vo.ElementInfoVO;
import com.github.itdachen.admin.mapper.IElementInfoMapper;
import com.github.itdachen.admin.service.IElementInfoService;
import com.github.itdachen.admin.convert.ElementInfoConvert;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 资源(按钮)信息 业务实现
 *
 * @author 王大宸
 * @date 2024-12-30 10:26:53
 */
@Service
public class ElementInfoServiceImpl extends BizServiceImpl< IElementInfoMapper, ElementInfo, ElementInfoDTO,  ElementInfoVO, ElementInfoQuery, String > implements IElementInfoService {
    private static final Logger logger = LoggerFactory.getLogger(ElementInfoServiceImpl.class);
    private static final ElementInfoConvert bizConvert = new ElementInfoConvert();
    private final List<String> EXP_FIELDS = new ArrayList<>();
    public ElementInfoServiceImpl() {
        super(bizConvert);
            EXP_FIELDS.add("认证编码");
            EXP_FIELDS.add("标题");
            EXP_FIELDS.add("类型: button,uri");
            EXP_FIELDS.add("是否展示/有效标志: Y-有效;N-无效");
    }

    /***
    * 分页
    *
    * @author 王大宸
    * @date 2024-12-30 10:26:53
    * @param params params
    * @return com.github.itdachen.framework.core.response.TableData<com.github.itdachen.admin.sdk.vo.elementInfoVo>
    */
    @Override
    public TableData< ElementInfoVO > page(ElementInfoQuery params) throws Exception {
        Page< ElementInfoVO > page = PageHelper.startPage(params.getPage(), params.getLimit());
        List< ElementInfoVO > list = bizMapper.list(params);
        return new TableData< ElementInfoVO >(page.getTotal(), list);
    }


    /***
     * 导出
     *
     * @author 王大宸
     * @date 2024-12-30 10:26:53
     * @param params com.github.itdachen.admin.sdk.query.ElementInfoQuery
     * @return void
     */
    @Override
    public void dataExpToExcel(HttpServletRequest request,
                               HttpServletResponse response,
                               ElementInfoQuery params) throws Exception{
        List < LinkedHashMap < String, String > >  list = bizMapper.selectElementInfoExpData(params);
       WorkBookUtils.export(request, response)
                .params(params)
                .title("资源(按钮)信息")
                .rowNum(true)
                .fields(EXP_FIELDS)
                .data(list)
                .execute();
    }

}
