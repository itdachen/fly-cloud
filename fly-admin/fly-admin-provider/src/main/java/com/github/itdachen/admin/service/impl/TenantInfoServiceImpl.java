package com.github.itdachen.admin.service.impl;

import com.github.itdachen.framework.core.response.TableData;
import com.github.itdachen.framework.webmvc.service.impl.BizServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.itdachen.admin.entity.TenantInfo;
import com.github.itdachen.admin.sdk.dto.TenantInfoDTO;
import com.github.itdachen.admin.sdk.query.TenantInfoQuery;
import com.github.itdachen.admin.sdk.vo.TenantInfoVO;
import com.github.itdachen.admin.mapper.ITenantInfoMapper;
import com.github.itdachen.admin.service.ITenantInfoService;
import com.github.itdachen.admin.convert.TenantInfoConvert;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 租户/公司信息 业务实现
 *
 * @author 王大宸
 * @date 2024-10-15 16:27:41
 */
@Service
public class TenantInfoServiceImpl extends BizServiceImpl< ITenantInfoMapper, TenantInfo, TenantInfoDTO,  TenantInfoVO, TenantInfoQuery, String > implements ITenantInfoService {
    private static final Logger logger = LoggerFactory.getLogger(TenantInfoServiceImpl.class);
    private static final TenantInfoConvert bizConvert = new TenantInfoConvert();
    private final List<String> EXP_FIELDS = new ArrayList<>();
    public TenantInfoServiceImpl() {
        super(bizConvert);
            EXP_FIELDS.add("名称");
            EXP_FIELDS.add("所属省级名称");
            EXP_FIELDS.add("所属市州名称");
            EXP_FIELDS.add("所属区县名称");
            EXP_FIELDS.add("官网/访问地址");
            EXP_FIELDS.add("拥有者昵称");
            EXP_FIELDS.add("联系电话");
            EXP_FIELDS.add("电子邮箱");
            EXP_FIELDS.add("传真");
    }

    /***
    * 分页
    *
    * @author 王大宸
    * @date 2024-10-15 16:27:41
    * @param params params
    * @return com.github.itdachen.framework.core.response.TableData<com.github.itdachen.admin.sdk.vo.tenantInfoVo>
    */
    @Override
    public TableData< TenantInfoVO > page(TenantInfoQuery params) throws Exception {
        Page< TenantInfoVO > page = PageHelper.startPage(params.getPage(), params.getLimit());
        List< TenantInfoVO > list = bizMapper.list(params);
        return new TableData< TenantInfoVO >(page.getTotal(), list);
    }


    /***
     * 导出
     *
     * @author 王大宸
     * @date 2024-10-15 16:27:41
     * @param params com.github.itdachen.admin.sdk.query.TenantInfoQuery
     * @return void
     */
    @Override
    public void dataExpToExcel(HttpServletRequest request,
                               HttpServletResponse response,
                               TenantInfoQuery params) throws Exception{
        List<LinkedHashMap<String, String>> list = bizMapper.selectTenantInfoExpData(params);
     //   ExcelExportUtils.exportExcel( request, response, EXP_FIELDS, list, "租户/公司信息", true);
    }

}
