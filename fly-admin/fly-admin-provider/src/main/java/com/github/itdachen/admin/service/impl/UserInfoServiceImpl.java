package com.github.itdachen.admin.service.impl;

import com.github.itdachen.admin.entity.LoginInfo;
import com.github.itdachen.admin.mapper.ILoginInfoMapper;
import com.github.itdachen.framework.context.BizContextHandler;
import com.github.itdachen.framework.context.constants.UserTypeConstant;
import com.github.itdachen.framework.context.constants.YesOrNotConstant;
import com.github.itdachen.framework.context.exception.BizException;
import com.github.itdachen.framework.core.response.TableData;
import com.github.itdachen.framework.webmvc.entity.EntityUtils;
import com.github.itdachen.framework.webmvc.service.impl.BizServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.itdachen.admin.entity.UserInfo;
import com.github.itdachen.admin.sdk.dto.UserInfoDTO;
import com.github.itdachen.admin.sdk.query.UserInfoQuery;
import com.github.itdachen.admin.sdk.vo.UserInfoVO;
import com.github.itdachen.admin.mapper.IUserInfoMapper;
import com.github.itdachen.admin.service.IUserInfoService;
import com.github.itdachen.admin.convert.UserInfoConvert;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 用户管理 业务实现
 *
 * @author 王大宸
 * @date 2024-09-02 14:38:09
 */
@Service
public class UserInfoServiceImpl extends BizServiceImpl<IUserInfoMapper, UserInfo, UserInfoDTO, UserInfoVO, UserInfoQuery, String> implements IUserInfoService {
    private static final Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);
    private static final UserInfoConvert bizConvert = new UserInfoConvert();
    private final List<String> EXP_FIELDS = new ArrayList<>();

    private final PasswordEncoder passwordEncoder;
    private final ILoginInfoMapper loginInfoMapper;

    public UserInfoServiceImpl(ILoginInfoMapper loginInfoMapper,
                               PasswordEncoder passwordEncoder) {
        super(bizConvert);

        EXP_FIELDS.add("昵称");
        EXP_FIELDS.add("身份证号码");
        EXP_FIELDS.add("性别");
        EXP_FIELDS.add("电话号码");
        EXP_FIELDS.add("电子邮箱");
        this.loginInfoMapper = loginInfoMapper;
        this.passwordEncoder = passwordEncoder;
    }

    /***
     * 分页
     *
     * @author 王大宸
     * @date 2024-09-02 14:38:09
     * @param params params
     * @return com.github.itdachen.framework.core.response.TableData<com.github.itdachen.admin.sdk.vo.userInfoVo>
     */
    @Override
    public TableData<UserInfoVO> page(UserInfoQuery params) throws Exception {
        params.setTenantId(BizContextHandler.getTenantId());
        Page<UserInfoVO> page = PageHelper.startPage(params.getPage(), params.getLimit());
        List<UserInfoVO> list = bizMapper.list(params);
        return new TableData<UserInfoVO>(page.getTotal(), list);
    }

    /***
     * 新增
     *
     * @author 王大宸
     * @date 2024/9/2 16:21
     * @param userInfoDTO userInfoDTO
     * @return com.github.itdachen.admin.sdk.vo.UserInfoVO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserInfoVO saveInfo(UserInfoDTO userInfoDTO) throws Exception {
        Integer hasUsername = loginInfoMapper.findLoginInfoByUsername(userInfoDTO.getTelephone());
        if (null != hasUsername) {
            throw new BizException("电话号码【" + userInfoDTO.getTelephone() + "】已经存在");
        }

        UserInfo userInfo = bizConvert.toJavaObject(userInfoDTO);
        EntityUtils.setCreateInfo(userInfo);
        userInfo.setValidFlag(YesOrNotConstant.Y);
        userInfo.setDelFlag(YesOrNotConstant.N);
        userInfo.setUserType(UserTypeConstant.INSIDE_USER);
        bizMapper.insertSelective(userInfo);

        /* 密码加密 */
        String password = passwordEncoder.encode(userInfo.getTelephone());

        LoginInfo loginInfo = LoginInfo.builder()
                .id(userInfo.getId())
                .username(userInfo.getTelephone())
                .password(password)
                .lastPassword(password)
                .validFlag(userInfo.getValidFlag())
                .delFlag(userInfo.getDelFlag())
                .expTime(LocalDateTime.now())
                .lastTime(LocalDateTime.now())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        loginInfoMapper.insertSelective(loginInfo);
        return bizConvert.toJavaObjectVO(userInfo);
    }

    /***
     * 更新
     *
     * @author 王大宸
     * @date 2024/9/2 16:30
     * @param userInfoDTO userInfoDTO
     * @return com.github.itdachen.admin.sdk.vo.UserInfoVO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserInfoVO updateInfo(UserInfoDTO userInfoDTO) throws Exception {
        return super.updateInfo(userInfoDTO);
    }


    /***
     * 删除
     *
     * @author 王大宸
     * @date 2024/9/2 16:30
     * @param id id
     * @return int
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(String id) throws Exception {
        return super.deleteByPrimaryKey(id);
    }

    /***
     * 导出
     *
     * @author 王大宸
     * @date 2024-09-02 14:38:09
     * @param params com.github.itdachen.admin.sdk.query.UserInfoQuery
     * @return void
     */
    @Override
    public void dataExpToExcel(HttpServletRequest request,
                               HttpServletResponse response,
                               UserInfoQuery params) throws Exception {
        for (String expField : EXP_FIELDS) {
            System.out.println(expField);
        }
        List<LinkedHashMap<String, String>> list = bizMapper.selectUserInfoExpData(params);
        //   ExcelExportUtils.exportExcel(request, response, EXP_FIELDS, list, "用户管理", true);
    }

}
