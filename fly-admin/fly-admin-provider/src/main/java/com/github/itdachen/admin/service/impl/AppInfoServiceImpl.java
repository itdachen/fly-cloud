package com.github.itdachen.admin.service.impl;

import com.github.itdachen.framework.core.response.TableData;
import com.github.itdachen.framework.webmvc.service.impl.BizServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.itdachen.admin.entity.AppInfo;
import com.github.itdachen.admin.sdk.dto.AppInfoDTO;
import com.github.itdachen.admin.sdk.query.AppInfoQuery;
import com.github.itdachen.admin.sdk.vo.AppInfoVO;
import com.github.itdachen.admin.mapper.IAppInfoMapper;
import com.github.itdachen.admin.service.IAppInfoService;
import com.github.itdachen.admin.convert.AppInfoConvert;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 应用信息 业务实现
 *
 * @author 王大宸
 * @date 2024-09-06 10:16:14
 */
@Service
public class AppInfoServiceImpl extends BizServiceImpl<IAppInfoMapper, AppInfo, AppInfoDTO, AppInfoVO, AppInfoQuery, String> implements IAppInfoService {
    private static final Logger logger = LoggerFactory.getLogger(AppInfoServiceImpl.class);
    private static final AppInfoConvert bizConvert = new AppInfoConvert();
    private final List<String> EXP_FIELDS = new ArrayList<>();

    public AppInfoServiceImpl() {
        super(bizConvert);
        EXP_FIELDS.add("平台名称");
        EXP_FIELDS.add("应用名称");
        EXP_FIELDS.add("应用类型");
        EXP_FIELDS.add("应用类型标题");
        EXP_FIELDS.add("职能名称");
        EXP_FIELDS.add("有效标志");
    }

    /***
     * 分页
     *
     * @author 王大宸
     * @date 2024-09-06 10:16:14
     * @param params params
     * @return com.github.itdachen.framework.core.response.TableData<com.github.itdachen.admin.sdk.vo.appInfoVo>
     */
    @Override
    public TableData<AppInfoVO> page(AppInfoQuery params) throws Exception {
        Page<AppInfoVO> page = PageHelper.startPage(params.getPage(), params.getLimit());
        List<AppInfoVO> list = bizMapper.list(params);
        return new TableData<AppInfoVO>(page.getTotal(), list);
    }


    /***
     * 导出
     *
     * @author 王大宸
     * @date 2024-09-06 10:16:14
     * @param params com.github.itdachen.admin.sdk.query.AppInfoQuery
     * @return void
     */
    @Override
    public void dataExpToExcel(HttpServletRequest request,
                               HttpServletResponse response,
                               AppInfoQuery params) throws Exception {
        List<LinkedHashMap<String, String>> list = bizMapper.selectAppInfoExpData(params);
        for (String expField : EXP_FIELDS) {
            System.err.println(expField);
        }

        // ExcelExportUtils.exportExcel( request, response, EXP_FIELDS, list, "应用信息", true);
    }

}
