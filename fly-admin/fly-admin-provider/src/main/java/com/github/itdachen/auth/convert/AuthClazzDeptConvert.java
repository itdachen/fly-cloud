package com.github.itdachen.auth.convert;

import com.github.itdachen.auth.entity.AuthClazzDept;
import com.github.itdachen.auth.sdk.dto.AuthClazzDeptDTO;
import com.github.itdachen.auth.sdk.vo.AuthClazzDeptVO;
import com.github.itdachen.framework.webmvc.convert.IBizConvertMapper;

/**
 * 部门岗位关联表 类型转换
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:50
 */
public class AuthClazzDeptConvert implements IBizConvertMapper< AuthClazzDept, AuthClazzDeptDTO, AuthClazzDeptVO > {

    @Override
    public AuthClazzDept toJavaObject(AuthClazzDeptDTO authClazzDeptDTO) {
        if (null == authClazzDeptDTO){
            return null;
        }
        AuthClazzDept authClazzDept = new AuthClazzDept();
                authClazzDept.setId(authClazzDeptDTO.getId());
                authClazzDept.setTenantId(authClazzDeptDTO.getTenantId());
                authClazzDept.setDeptCode(authClazzDeptDTO.getDeptCode());
                authClazzDept.setDeptTitle(authClazzDeptDTO.getDeptTitle());
                authClazzDept.setDeptLevel(authClazzDeptDTO.getDeptLevel());
                authClazzDept.setClazzCode(authClazzDeptDTO.getClazzCode());
                authClazzDept.setClazzTitle(authClazzDeptDTO.getClazzTitle());
                authClazzDept.setValidFlag(authClazzDeptDTO.getValidFlag());
                authClazzDept.setRemarks(authClazzDeptDTO.getRemarks());
        return authClazzDept;
    }


    @Override
    public AuthClazzDeptVO toJavaObjectVO(AuthClazzDept authClazzDept) {
        if (null == authClazzDept){
            return null;
        }
            AuthClazzDeptVO authClazzDeptVO = new AuthClazzDeptVO();
                    authClazzDeptVO.setId(authClazzDept.getId());
                    authClazzDeptVO.setTenantId(authClazzDept.getTenantId());
                    authClazzDeptVO.setDeptCode(authClazzDept.getDeptCode());
                    authClazzDeptVO.setDeptTitle(authClazzDept.getDeptTitle());
                    authClazzDeptVO.setDeptLevel(authClazzDept.getDeptLevel());
                    authClazzDeptVO.setClazzCode(authClazzDept.getClazzCode());
                    authClazzDeptVO.setClazzTitle(authClazzDept.getClazzTitle());
                    authClazzDeptVO.setValidFlag(authClazzDept.getValidFlag());
                    authClazzDeptVO.setRemarks(authClazzDept.getRemarks());
        return authClazzDeptVO;
    }

    public AuthClazzDept toJavaObject(AuthClazzDeptVO authClazzDeptVO) {
        if (null == authClazzDeptVO){
            return null;
        }
        AuthClazzDept authClazzDept = new AuthClazzDept();
                authClazzDept.setId(authClazzDeptVO.getId());
                authClazzDept.setTenantId(authClazzDeptVO.getTenantId());
                authClazzDept.setDeptCode(authClazzDeptVO.getDeptCode());
                authClazzDept.setDeptTitle(authClazzDeptVO.getDeptTitle());
                authClazzDept.setDeptLevel(authClazzDeptVO.getDeptLevel());
                authClazzDept.setClazzCode(authClazzDeptVO.getClazzCode());
                authClazzDept.setClazzTitle(authClazzDeptVO.getClazzTitle());
                authClazzDept.setValidFlag(authClazzDeptVO.getValidFlag());
                authClazzDept.setRemarks(authClazzDeptVO.getRemarks());
        return authClazzDept;
    }

    public AuthClazzDeptVO toJavaObjectVO(AuthClazzDeptDTO authClazzDeptDTO) {
        if (null == authClazzDeptDTO){
            return null;
        }
            AuthClazzDeptVO authClazzDeptVO = new AuthClazzDeptVO();
                    authClazzDeptVO.setId(authClazzDeptDTO.getId());
                    authClazzDeptVO.setTenantId(authClazzDeptDTO.getTenantId());
                    authClazzDeptVO.setDeptCode(authClazzDeptDTO.getDeptCode());
                    authClazzDeptVO.setDeptTitle(authClazzDeptDTO.getDeptTitle());
                    authClazzDeptVO.setDeptLevel(authClazzDeptDTO.getDeptLevel());
                    authClazzDeptVO.setClazzCode(authClazzDeptDTO.getClazzCode());
                    authClazzDeptVO.setClazzTitle(authClazzDeptDTO.getClazzTitle());
                    authClazzDeptVO.setValidFlag(authClazzDeptDTO.getValidFlag());
                    authClazzDeptVO.setRemarks(authClazzDeptDTO.getRemarks());
        return authClazzDeptVO;
    }


}