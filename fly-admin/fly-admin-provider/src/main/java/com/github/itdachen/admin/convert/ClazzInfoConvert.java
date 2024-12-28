package com.github.itdachen.admin.convert;

import com.github.itdachen.admin.entity.ClazzInfo;
import com.github.itdachen.admin.sdk.dto.ClazzInfoDTO;
import com.github.itdachen.admin.sdk.vo.ClazzInfoVO;
import com.github.itdachen.framework.webmvc.convert.IBizConvertMapper;

/**
 * 岗位信息 类型转换
 *
 * @author 王大宸
 * @date 2024-12-28 20:42:52
 */
public class ClazzInfoConvert implements IBizConvertMapper<ClazzInfo, ClazzInfoDTO, ClazzInfoVO> {

    @Override
    public ClazzInfo toJavaObject(ClazzInfoDTO clazzInfoDTO) {
        if (null == clazzInfoDTO) {
            return null;
        }
        ClazzInfo clazzInfo = new ClazzInfo();
        clazzInfo.setId(clazzInfoDTO.getId());
        clazzInfo.setTenantId(clazzInfoDTO.getTenantId());
        clazzInfo.setClazzCode(clazzInfoDTO.getClazzCode());
        clazzInfo.setClazzTitle(clazzInfoDTO.getClazzTitle());
        clazzInfo.setDeptLevelCode(clazzInfoDTO.getDeptLevelCode());
        clazzInfo.setDeptLevelTitle(clazzInfoDTO.getDeptLevelTitle());
        clazzInfo.setDeptFuncCode(clazzInfoDTO.getDeptFuncCode());
        clazzInfo.setDeptFuncTitle(clazzInfoDTO.getDeptFuncTitle());
        clazzInfo.setClazzFuncCode(clazzInfoDTO.getClazzFuncCode());
        clazzInfo.setClazzFuncTitle(clazzInfoDTO.getClazzFuncTitle());
        clazzInfo.setDeptFlag(clazzInfoDTO.getDeptFlag());
        clazzInfo.setDeptCode(clazzInfoDTO.getDeptCode());
        clazzInfo.setDeptTitle(clazzInfoDTO.getDeptTitle());
        clazzInfo.setValidFlag(clazzInfoDTO.getValidFlag());
        clazzInfo.setRemarks(clazzInfoDTO.getRemarks());
        return clazzInfo;
    }


    @Override
    public ClazzInfoVO toJavaObjectVO(ClazzInfo clazzInfo) {
        if (null == clazzInfo) {
            return null;
        }
        ClazzInfoVO clazzInfoVO = new ClazzInfoVO();
        clazzInfoVO.setId(clazzInfo.getId());
        clazzInfoVO.setTenantId(clazzInfo.getTenantId());
        clazzInfoVO.setClazzCode(clazzInfo.getClazzCode());
        clazzInfoVO.setClazzTitle(clazzInfo.getClazzTitle());
        clazzInfoVO.setDeptLevelCode(clazzInfo.getDeptLevelCode());
        clazzInfoVO.setDeptLevelTitle(clazzInfo.getDeptLevelTitle());
        clazzInfoVO.setDeptFuncCode(clazzInfo.getDeptFuncCode());
        clazzInfoVO.setDeptFuncTitle(clazzInfo.getDeptFuncTitle());
        clazzInfoVO.setClazzFuncCode(clazzInfo.getClazzFuncCode());
        clazzInfoVO.setClazzFuncTitle(clazzInfo.getClazzFuncTitle());
        clazzInfoVO.setDeptFlag(clazzInfo.getDeptFlag());
        clazzInfoVO.setDeptCode(clazzInfo.getDeptCode());
        clazzInfoVO.setDeptTitle(clazzInfo.getDeptTitle());
        clazzInfoVO.setValidFlag(clazzInfo.getValidFlag());
        clazzInfoVO.setRemarks(clazzInfo.getRemarks());
        return clazzInfoVO;
    }

    public ClazzInfo toJavaObject(ClazzInfoVO clazzInfoVO) {
        if (null == clazzInfoVO) {
            return null;
        }
        ClazzInfo clazzInfo = new ClazzInfo();
        clazzInfo.setId(clazzInfoVO.getId());
        clazzInfo.setTenantId(clazzInfoVO.getTenantId());
        clazzInfo.setClazzCode(clazzInfoVO.getClazzCode());
        clazzInfo.setClazzTitle(clazzInfoVO.getClazzTitle());
        clazzInfo.setDeptLevelCode(clazzInfoVO.getDeptLevelCode());
        clazzInfo.setDeptLevelTitle(clazzInfoVO.getDeptLevelTitle());
        clazzInfo.setDeptFuncCode(clazzInfoVO.getDeptFuncCode());
        clazzInfo.setDeptFuncTitle(clazzInfoVO.getDeptFuncTitle());
        clazzInfo.setClazzFuncCode(clazzInfoVO.getClazzFuncCode());
        clazzInfo.setClazzFuncTitle(clazzInfoVO.getClazzFuncTitle());
        clazzInfo.setDeptFlag(clazzInfoVO.getDeptFlag());
        clazzInfo.setDeptCode(clazzInfoVO.getDeptCode());
        clazzInfo.setDeptTitle(clazzInfoVO.getDeptTitle());
        clazzInfo.setValidFlag(clazzInfoVO.getValidFlag());
        clazzInfo.setRemarks(clazzInfoVO.getRemarks());
        return clazzInfo;
    }

    public ClazzInfoVO toJavaObjectVO(ClazzInfoDTO clazzInfoDTO) {
        if (null == clazzInfoDTO) {
            return null;
        }
        ClazzInfoVO clazzInfoVO = new ClazzInfoVO();
        clazzInfoVO.setId(clazzInfoDTO.getId());
        clazzInfoVO.setTenantId(clazzInfoDTO.getTenantId());
        clazzInfoVO.setClazzCode(clazzInfoDTO.getClazzCode());
        clazzInfoVO.setClazzTitle(clazzInfoDTO.getClazzTitle());
        clazzInfoVO.setDeptLevelCode(clazzInfoDTO.getDeptLevelCode());
        clazzInfoVO.setDeptLevelTitle(clazzInfoDTO.getDeptLevelTitle());
        clazzInfoVO.setDeptFuncCode(clazzInfoDTO.getDeptFuncCode());
        clazzInfoVO.setDeptFuncTitle(clazzInfoDTO.getDeptFuncTitle());
        clazzInfoVO.setClazzFuncCode(clazzInfoDTO.getClazzFuncCode());
        clazzInfoVO.setClazzFuncTitle(clazzInfoDTO.getClazzFuncTitle());
        clazzInfoVO.setDeptFlag(clazzInfoDTO.getDeptFlag());
        clazzInfoVO.setDeptCode(clazzInfoDTO.getDeptCode());
        clazzInfoVO.setDeptTitle(clazzInfoDTO.getDeptTitle());
        clazzInfoVO.setValidFlag(clazzInfoDTO.getValidFlag());
        clazzInfoVO.setRemarks(clazzInfoDTO.getRemarks());
        return clazzInfoVO;
    }


}