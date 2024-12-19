package com.github.itdachen.admin.service.impl;

import com.github.itdachen.framework.core.response.TableData;
import com.github.itdachen.framework.webmvc.service.impl.BizServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.itdachen.admin.entity.AdmArea;
import com.github.itdachen.admin.sdk.dto.AdmAreaDTO;
import com.github.itdachen.admin.sdk.query.AdmAreaQuery;
import com.github.itdachen.admin.sdk.vo.AdmAreaVO;
import com.github.itdachen.admin.mapper.IAdmAreaMapper;
import com.github.itdachen.admin.service.IAdmAreaService;
import com.github.itdachen.admin.convert.AdmAreaConvert;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 行政区划代码 业务实现
 *
 * @author 王大宸
 * @date 2024-12-19 09:45:17
 */
@Service
public class AdmAreaServiceImpl extends BizServiceImpl<IAdmAreaMapper, AdmArea, AdmAreaDTO, AdmAreaVO, AdmAreaQuery, String> implements IAdmAreaService {
    private static final Logger logger = LoggerFactory.getLogger(AdmAreaServiceImpl.class);
    private static final AdmAreaConvert bizConvert = new AdmAreaConvert();
    private final List<String> EXP_FIELDS = new ArrayList<>();

    public AdmAreaServiceImpl() {
        super(bizConvert);
        EXP_FIELDS.add("省、直辖市、自治区代码");
        EXP_FIELDS.add("城市代码");
        EXP_FIELDS.add("区县代码");
        EXP_FIELDS.add("乡镇代码");
        EXP_FIELDS.add("街道代码");
        EXP_FIELDS.add("街道名称");
        EXP_FIELDS.add("城乡街道分类代码");
        EXP_FIELDS.add("数据级别：省市区县乡镇街道");
    }

    /***
     * 分页
     *
     * @author 王大宸
     * @date 2024-12-19 09:45:17
     * @param params params
     * @return com.github.itdachen.framework.core.response.TableData<com.github.itdachen.admin.sdk.vo.admAreaVo>
     */
    @Override
    public TableData<AdmAreaVO> page(AdmAreaQuery params) throws Exception {
        Page<AdmAreaVO> page = PageHelper.startPage(params.getPage(), params.getLimit());
        List<AdmAreaVO> list = bizMapper.list(params);
        return new TableData<AdmAreaVO>(page.getTotal(), list);
    }


    /***
     * 导出
     *
     * @author 王大宸
     * @date 2024-12-19 09:45:17
     * @param params com.github.itdachen.admin.sdk.query.AdmAreaQuery
     * @return void
     */
    @Override
    public void dataExpToExcel(HttpServletRequest request, HttpServletResponse response, AdmAreaQuery params) throws Exception {
        //  List<LinkedHashMap<String, String>> list = bizMapper.selectAdmAreaExpData(params);
        //  ExcelExportUtils.exportExcel(request, response, EXP_FIELDS, list, "行政区划代码", true);
    }

}
