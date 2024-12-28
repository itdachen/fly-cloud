package com.github.itdachen.admin.service.impl;

import com.github.itdachen.framework.context.BizContextHandler;
import com.github.itdachen.framework.context.constants.YesOrNotConstant;
import com.github.itdachen.framework.context.exception.BizException;
import com.github.itdachen.framework.core.response.TableData;
import com.github.itdachen.framework.webmvc.entity.EntityUtils;
import com.github.itdachen.framework.webmvc.service.impl.BizServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.itdachen.admin.entity.ClazzInfo;
import com.github.itdachen.admin.sdk.dto.ClazzInfoDTO;
import com.github.itdachen.admin.sdk.query.ClazzInfoQuery;
import com.github.itdachen.admin.sdk.vo.ClazzInfoVO;
import com.github.itdachen.admin.mapper.IClazzInfoMapper;
import com.github.itdachen.admin.service.IClazzInfoService;
import com.github.itdachen.admin.convert.ClazzInfoConvert;
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
 * 岗位信息 业务实现
 *
 * @author 王大宸
 * @date 2024-12-28 18:40:48
 */
@Service
public class ClazzInfoServiceImpl extends BizServiceImpl<IClazzInfoMapper, ClazzInfo, ClazzInfoDTO, ClazzInfoVO, ClazzInfoQuery, String> implements IClazzInfoService {
    private static final Logger logger = LoggerFactory.getLogger(ClazzInfoServiceImpl.class);
    private static final ClazzInfoConvert bizConvert = new ClazzInfoConvert();
    private final static List<String> EXP_FIELDS = new ArrayList<>();

    public ClazzInfoServiceImpl() {
        super(bizConvert);
        EXP_FIELDS.add("岗位代码");
        EXP_FIELDS.add("岗位名称, 例如: 信息中心-总部-主管领导岗");
        EXP_FIELDS.add("部门专属岗: Y-是;N-否(暂时不用,备用)");
        EXP_FIELDS.add("部门代码(暂时不用,备用)");
        EXP_FIELDS.add("部门名称(暂时不用,备用)");
        EXP_FIELDS.add("岗位等级代码");
        EXP_FIELDS.add("岗位等级名称");
        EXP_FIELDS.add("岗位职能: 主管领导岗, 业务岗等");
        EXP_FIELDS.add("岗位职能名称: 主管领导岗, 业务岗等");
        EXP_FIELDS.add("有效标志: Y-是;N-否");
    }

    /***
     * 分页
     *
     * @author 王大宸
     * @date 2024-12-28 18:40:48
     * @param params params
     * @return com.github.itdachen.framework.core.response.TableData<com.github.itdachen.admin.sdk.vo.clazzInfoVo>
     */
    @Override
    public TableData<ClazzInfoVO> page(ClazzInfoQuery params) throws Exception {
        params.setDeleteFlag(YesOrNotConstant.N);
        Page<ClazzInfoVO> page = PageHelper.startPage(params.getPage(), params.getLimit());
        List<ClazzInfoVO> list = bizMapper.list(params);
        return new TableData<ClazzInfoVO>(page.getTotal(), list);
    }

    /***
     * 新增
     *
     * @author 王大宸
     * @date 2024/12/28 19:06
     * @param clazzInfoDTO clazzInfoDTO
     * @return com.github.itdachen.admin.sdk.vo.ClazzInfoVO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ClazzInfoVO saveInfo(ClazzInfoDTO clazzInfoDTO) throws Exception {
        ClazzInfoVO clazzInfoVO = bizMapper.findClazzInfoVO(BizContextHandler.getTenantId(), clazzInfoDTO.getClazzCode());
        if (null != clazzInfoVO) {
            throw new BizException("该岗位已经存在！");
        }

        String clazzCode = clazzInfoDTO.getDeptCode() + clazzInfoDTO.getLevelCode() + clazzInfoDTO.getFuncCode();

        ClazzInfo clazzInfo = bizConvert.toJavaObject(clazzInfoDTO);
        clazzInfo.setClazzCode(clazzCode);
        clazzInfo.setDeleteFlag(YesOrNotConstant.N);
        EntityUtils.setCreatAndUpdateInfo(clazzInfo);
        bizMapper.insertSelective(clazzInfo);
        return bizConvert.toJavaObjectVO(clazzInfo);
    }

    /***
     * 更新
     *
     * @author 王大宸
     * @date 2024/12/28 19:17
     * @param clazzInfoDTO clazzInfoDTO
     * @return com.github.itdachen.admin.sdk.vo.ClazzInfoVO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ClazzInfoVO updateInfo(ClazzInfoDTO clazzInfoDTO) throws Exception {
        ClazzInfoVO clazzInfoVO = bizMapper.findClazzInfoVO(BizContextHandler.getTenantId(), clazzInfoDTO.getClazzCode());
        if (null != clazzInfoVO && !clazzInfoVO.getId().equals(clazzInfoDTO.getId())) {
            throw new BizException("该岗位已经存在！");
        }
        ClazzInfo clazzInfo = bizConvert.toJavaObject(clazzInfoDTO);
        clazzInfo.setDeleteFlag(YesOrNotConstant.N);
        EntityUtils.setUpdatedInfo(clazzInfo);
        bizMapper.updateByPrimaryKeySelective(clazzInfo);
        return bizConvert.toJavaObjectVO(clazzInfo);
    }

    /***
     * 删除
     *
     * @author 王大宸
     * @date 2024/12/28 19:17
     * @param id id
     * @return int
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(String id) throws Exception {
        ClazzInfo clazzInfo = new ClazzInfo();
        clazzInfo.setId(id);
        clazzInfo.setDeleteFlag(YesOrNotConstant.Y);
        clazzInfo.setValidFlag(YesOrNotConstant.N);
        EntityUtils.setUpdatedInfo(clazzInfo);
        return bizMapper.updateByPrimaryKeySelective(clazzInfo);
    }

    /***
     * 导出
     *
     * @author 王大宸
     * @date 2024-12-28 18:40:48
     * @param params com.github.itdachen.admin.sdk.query.ClazzInfoQuery
     * @return void
     */
    @Override
    public void dataExpToExcel(HttpServletRequest request,
                               HttpServletResponse response,
                               ClazzInfoQuery params) throws Exception {
        // List<LinkedHashMap<String, String>> list = bizMapper.selectClazzInfoExpData(params);
        // ExcelExportUtils.exportExcel( request, response, EXP_FIELDS, list, "岗位信息", true);
    }

}
