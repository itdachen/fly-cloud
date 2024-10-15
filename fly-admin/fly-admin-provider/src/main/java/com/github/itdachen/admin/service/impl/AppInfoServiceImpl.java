package com.github.itdachen.admin.service.impl;

import com.github.itdachen.framework.context.constants.YesOrNotConstant;
import com.github.itdachen.framework.context.exception.BizException;
import com.github.itdachen.framework.context.id.IdUtils;
import com.github.itdachen.framework.core.response.TableData;
import com.github.itdachen.framework.core.utils.StringUtils;
import com.github.itdachen.framework.webmvc.entity.EntityUtils;
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
import org.springframework.transaction.annotation.Transactional;

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
     * 新增
     *
     * @author 王大宸
     * @date 2024/10/15 11:28
     * @param appInfoDTO appInfoDTO
     * @return com.github.itdachen.admin.sdk.vo.AppInfoVO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AppInfoVO saveInfo(AppInfoDTO appInfoDTO) throws Exception {
        if (StringUtils.isEmpty(appInfoDTO.getAppCode())) {
            throw new BizException("应用标识不能为空！");
        }
        AppInfoVO appInfoByAppCode = bizMapper.findAppInfoByAppCode(appInfoDTO.getAppCode());
        if (null != appInfoByAppCode) {
            throw new BizException("应用标识【" + appInfoDTO.getAppCode() + "】已经存在！");
        }
        AppInfo appInfo = bizConvert.toJavaObject(appInfoDTO);
        EntityUtils.setCreatAndUpdateInfo(appInfo);
        if (StringUtils.isEmpty(appInfo.getAppSecret())) {
            appInfo.setAppSecret(IdUtils.guid());
        }
        appInfo.setId(IdUtils.guid());
        appInfo.setValidDel(YesOrNotConstant.Y);
        bizMapper.insertSelective(appInfo);
        return bizConvert.toJavaObjectVO(appInfo);
    }

    /***
    * 更新
    *
    * @author 王大宸
    * @date 2024/10/15 15:04
     * @param appInfoDTO appInfoDTO
    * @return com.github.itdachen.admin.sdk.vo.AppInfoVO
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AppInfoVO updateInfo(AppInfoDTO appInfoDTO) throws Exception {
        if (StringUtils.isEmpty(appInfoDTO.getAppCode())) {
            throw new BizException("应用标识不能为空！");
        }
        AppInfoVO appInfoByAppCode = bizMapper.findAppInfoByAppCode(appInfoDTO.getAppCode());
        if (null != appInfoByAppCode && !appInfoDTO.getId().equals(appInfoByAppCode.getId())) {
            throw new BizException("应用标识【" + appInfoDTO.getAppCode() + "】已经存在！");
        }
        AppInfo appInfo = bizConvert.toJavaObject(appInfoDTO);
        EntityUtils.setUpdatedInfo(appInfo);
        if (StringUtils.isEmpty(appInfo.getAppSecret())) {
            appInfo.setAppSecret(IdUtils.guid());
        }
        appInfo.setValidDel(YesOrNotConstant.Y);
        bizMapper.updateByPrimaryKeySelective(appInfo);
        return bizConvert.toJavaObjectVO(appInfo);
    }

    @Override
    public int deleteByPrimaryKey(String id) throws Exception {
        AppInfoVO appInfoVO = bizMapper.selectAppInfoVO(id);
        if (YesOrNotConstant.N.equals(appInfoVO.getValidDel())) {
            throw new BizException("该应用无法被删除");
        }
        return bizMapper.deleteByPrimaryKey(id);
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
