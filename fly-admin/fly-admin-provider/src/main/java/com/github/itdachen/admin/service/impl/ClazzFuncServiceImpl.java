package com.github.itdachen.admin.service.impl;

import com.github.itdachen.framework.context.BizContextHandler;
import com.github.itdachen.framework.context.constants.YesOrNotConstant;
import com.github.itdachen.framework.context.exception.BizException;
import com.github.itdachen.framework.core.response.TableData;
import com.github.itdachen.framework.webmvc.entity.EntityUtils;
import com.github.itdachen.framework.webmvc.service.impl.BizServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.itdachen.admin.entity.ClazzFunc;
import com.github.itdachen.admin.sdk.dto.ClazzFuncDTO;
import com.github.itdachen.admin.sdk.query.ClazzFuncQuery;
import com.github.itdachen.admin.sdk.vo.ClazzFuncVO;
import com.github.itdachen.admin.mapper.IClazzFuncMapper;
import com.github.itdachen.admin.service.IClazzFuncService;
import com.github.itdachen.admin.convert.ClazzFuncConvert;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 岗位职能管理表(确定之后不能修改) 业务实现
 *
 * @author 王大宸
 * @date 2024-12-28 18:40:48
 */
@Service
public class ClazzFuncServiceImpl extends BizServiceImpl<IClazzFuncMapper, ClazzFunc, ClazzFuncDTO, ClazzFuncVO, ClazzFuncQuery, String> implements IClazzFuncService {
    private static final Logger logger = LoggerFactory.getLogger(ClazzFuncServiceImpl.class);
    private static final ClazzFuncConvert bizConvert = new ClazzFuncConvert();
    private final List<String> EXP_FIELDS = new ArrayList<>();

    public ClazzFuncServiceImpl() {
        super(bizConvert);
        EXP_FIELDS.add("岗位职能编码, 例如: 01-主管领导岗;02-业务岗");
        EXP_FIELDS.add("岗位职能标题, 例如: 01-主管领导岗;02-业务岗");
        EXP_FIELDS.add("有效标志: Y-有效;N-无效");
    }

    /***
     * 分页
     *
     * @author 王大宸
     * @date 2024-12-28 18:40:48
     * @param params params
     * @return com.github.itdachen.framework.core.response.TableData<com.github.itdachen.admin.sdk.vo.clazzFuncVo>
     */
    @Override
    public TableData<ClazzFuncVO> page(ClazzFuncQuery params) throws Exception {
        params.setDeleteFlag(YesOrNotConstant.N);
        params.setTenantId(BizContextHandler.getTenantId());
        Page<ClazzFuncVO> page = PageHelper.startPage(params.getPage(), params.getLimit());
        List<ClazzFuncVO> list = bizMapper.list(params);
        return new TableData<ClazzFuncVO>(page.getTotal(), list);
    }

    /***
     * 新增
     *
     * @author 王大宸
     * @date 2024/12/28 19:03
     * @param clazzFuncDTO clazzFuncDTO
     * @return com.github.itdachen.admin.sdk.vo.ClazzFuncVO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ClazzFuncVO saveInfo(ClazzFuncDTO clazzFuncDTO) throws Exception {
        ClazzFuncVO clazzFuncVO = bizMapper.findClazzFuncVO(BizContextHandler.getTenantId(), clazzFuncDTO.getFuncCode());
        if (null != clazzFuncVO) {
            throw new BizException("该职能编码已经存在！");
        }
        ClazzFunc clazzFunc = bizConvert.toJavaObject(clazzFuncDTO);
        clazzFunc.setDeleteFlag(YesOrNotConstant.N);
        EntityUtils.setCreatAndUpdateInfo(clazzFunc);
        bizMapper.insertSelective(clazzFunc);
        return bizConvert.toJavaObjectVO(clazzFunc);
    }

    /***
     * 更新
     *
     * @author 王大宸
     * @date 2024/12/28 19:14
     * @param clazzFuncDTO clazzFuncDTO
     * @return com.github.itdachen.admin.sdk.vo.ClazzFuncVO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ClazzFuncVO updateInfo(ClazzFuncDTO clazzFuncDTO) throws Exception {
        ClazzFuncVO clazzFuncVO = bizMapper.findClazzFuncVO(BizContextHandler.getTenantId(), clazzFuncDTO.getFuncCode());
        if (null != clazzFuncVO && !clazzFuncDTO.getId().equals(clazzFuncVO.getId())) {
            throw new BizException("该职能编码已经存在！");
        }
        ClazzFunc clazzFunc = bizConvert.toJavaObject(clazzFuncDTO);
        clazzFunc.setDeleteFlag(YesOrNotConstant.N);
        EntityUtils.setUpdatedInfo(clazzFunc);
        bizMapper.updateByPrimaryKeySelective(clazzFunc);
        return bizConvert.toJavaObjectVO(clazzFunc);
    }

    /***
     * 删除
     *
     * @author 王大宸
     * @date 2024/12/28 19:13
     * @param id id
     * @return int
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(String id) throws Exception {
        ClazzFunc clazzFunc = new ClazzFunc();
        clazzFunc.setId(id);
        clazzFunc.setDeleteFlag(YesOrNotConstant.Y);
        clazzFunc.setValidFlag(YesOrNotConstant.N);
        EntityUtils.setUpdatedInfo(clazzFunc);
        return bizMapper.updateByPrimaryKeySelective(clazzFunc);
    }

    /***
     * 导出
     *
     * @author 王大宸
     * @date 2024-12-28 18:40:48
     * @param params com.github.itdachen.admin.sdk.query.ClazzFuncQuery
     * @return void
     */
    @Override
    public void dataExpToExcel(HttpServletRequest request,
                               HttpServletResponse response,
                               ClazzFuncQuery params) throws Exception {
        // List<LinkedHashMap<String, String>> list = bizMapper.selectClazzFuncExpData(params);
        // ExcelExportUtils.exportExcel( request, response, EXP_FIELDS, list, "岗位职能管理表(确定之后不能修改)", true);
    }

    @Override
    public List<ClazzFuncVO> findClazzFuncList() throws Exception {
        return bizMapper.findClazzFuncList(BizContextHandler.getTenantId());
    }
}
