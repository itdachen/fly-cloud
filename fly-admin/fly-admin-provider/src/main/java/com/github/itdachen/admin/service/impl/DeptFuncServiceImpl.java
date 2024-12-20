package com.github.itdachen.admin.service.impl;

import com.github.itdachen.framework.context.BizContextHandler;
import com.github.itdachen.framework.context.constants.YesOrNotConstant;
import com.github.itdachen.framework.context.exception.BizException;
import com.github.itdachen.framework.core.response.TableData;
import com.github.itdachen.framework.webmvc.entity.EntityUtils;
import com.github.itdachen.framework.webmvc.service.impl.BizServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.itdachen.admin.entity.DeptFunc;
import com.github.itdachen.admin.sdk.dto.DeptFuncDTO;
import com.github.itdachen.admin.sdk.query.DeptFuncQuery;
import com.github.itdachen.admin.sdk.vo.DeptFuncVO;
import com.github.itdachen.admin.mapper.IDeptFuncMapper;
import com.github.itdachen.admin.service.IDeptFuncService;
import com.github.itdachen.admin.convert.DeptFuncConvert;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 部门职能 业务实现
 *
 * @author 王大宸
 * @date 2024-12-20 14:40:35
 */
@Service
public class DeptFuncServiceImpl extends BizServiceImpl<IDeptFuncMapper, DeptFunc, DeptFuncDTO, DeptFuncVO, DeptFuncQuery, String> implements IDeptFuncService {
    private static final Logger logger = LoggerFactory.getLogger(DeptFuncServiceImpl.class);
    private static final DeptFuncConvert bizConvert = new DeptFuncConvert();
    private final List<String> EXP_FIELDS = new ArrayList<>();

    public DeptFuncServiceImpl() {
        super(bizConvert);
        EXP_FIELDS.add("编码(全局唯一)");
        EXP_FIELDS.add("标题, 例如: 100-领导办公室;200-人事部;300-研发部");
        EXP_FIELDS.add("有效标志: Y-有效;N-无效");
    }

    /***
     * 分页
     *
     * @author 王大宸
     * @date 2024-12-20 14:40:35
     * @param params params
     * @return com.github.itdachen.framework.core.response.TableData<com.github.itdachen.admin.sdk.vo.deptFuncVo>
     */
    @Override
    public TableData<DeptFuncVO> page(DeptFuncQuery params) throws Exception {
        Page<DeptFuncVO> page = PageHelper.startPage(params.getPage(), params.getLimit());
        params.setDeleteFlag(YesOrNotConstant.N);
        List<DeptFuncVO> list = bizMapper.list(params);
        return new TableData<DeptFuncVO>(page.getTotal(), list);
    }

    /***
     * 新增
     *
     * @author 王大宸
     * @date 2024/12/20 14:45
     * @param deptFuncDTO deptFuncDTO
     * @return com.github.itdachen.admin.sdk.vo.DeptFuncVO
     */
    @Override
    public DeptFuncVO saveInfo(DeptFuncDTO deptFuncDTO) throws Exception {
        int i = Integer.parseInt(deptFuncDTO.getFuncCode());
        String funcCod = String.format("%02d", i);
        deptFuncDTO.setFuncCode(funcCod);
        DeptFunc deptFunc = new DeptFunc();
        deptFunc.setTenantId(BizContextHandler.getTenantId());
        deptFunc.setFuncCode(deptFuncDTO.getFuncCode());
        List<DeptFunc> select = bizMapper.select(deptFunc);
        if (!select.isEmpty()) {
            throw new BizException("部门代码 " + deptFuncDTO.getFuncCode() + " 已经存在");
        }
        deptFunc = bizConvert.toJavaObject(deptFuncDTO);
        deptFunc.setDeleteFlag(YesOrNotConstant.N);
        EntityUtils.setCreatAndUpdateInfo(deptFunc);
        bizMapper.insertSelective(deptFunc);
        return bizConvert.toJavaObjectVO(deptFunc);
    }

    /***
     * 更新
     *
     * @author 王大宸
     * @date 2024/12/20 14:46
     * @param deptFuncDTO deptFuncDTO
     * @return com.github.itdachen.admin.sdk.vo.DeptFuncVO
     */
    @Override
    public DeptFuncVO updateInfo(DeptFuncDTO deptFuncDTO) throws Exception {
        int i = Integer.parseInt(deptFuncDTO.getFuncCode());
        String funcCod = String.format("%02d", i);
        deptFuncDTO.setFuncCode(funcCod);
        DeptFunc deptFunc = new DeptFunc();
        deptFunc.setTenantId(BizContextHandler.getTenantId());
        deptFunc.setFuncCode(deptFuncDTO.getFuncCode());
        List<DeptFunc> select = bizMapper.select(deptFunc);
        if (!select.isEmpty() && !deptFuncDTO.getId().equals(select.get(0).getId())) {
            throw new BizException("部门代码 " + deptFuncDTO.getFuncCode() + " 已经存在");
        }
        deptFunc = bizConvert.toJavaObject(deptFuncDTO);
        EntityUtils.setUpdatedInfo(deptFunc);
        bizMapper.updateByPrimaryKeySelective(deptFunc);
        return bizConvert.toJavaObjectVO(deptFunc);
    }


    /***
     * 删除
     *
     * @author 王大宸
     * @date 2024/12/20 14:54
     * @param id id
     * @return int
     */
    @Override
    public int deleteByPrimaryKey(String id) throws Exception {
        DeptFunc deptFunc = new DeptFunc();
        deptFunc.setId(id);
        deptFunc.setDeleteFlag(YesOrNotConstant.Y);
        return bizMapper.updateByPrimaryKeySelective(deptFunc);
    }

    /***
     * 导出
     *
     * @author 王大宸
     * @date 2024-12-20 14:40:35
     * @param params com.github.itdachen.admin.sdk.query.DeptFuncQuery
     * @return void
     */
    @Override
    public void dataExpToExcel(HttpServletRequest request,
                               HttpServletResponse response,
                               DeptFuncQuery params) throws Exception {
        //  List<LinkedHashMap<String, String>> list = bizMapper.selectDeptFuncExpData(params);
        //  ExcelExportUtils.exportExcel( request, response, EXP_FIELDS, list, "部门职能", true);
    }

}
