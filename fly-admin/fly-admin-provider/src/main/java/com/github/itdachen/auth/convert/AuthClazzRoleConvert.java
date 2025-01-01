package com.github.itdachen.auth.convert;

import com.github.itdachen.auth.entity.AuthClazzRole;
import com.github.itdachen.auth.sdk.dto.AuthClazzRoleDTO;
import com.github.itdachen.auth.sdk.vo.AuthClazzRoleVO;
import com.github.itdachen.framework.webmvc.convert.IBizConvertMapper;

/**
 * 身份岗位管理表 类型转换
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:50
 */
public class AuthClazzRoleConvert implements IBizConvertMapper< AuthClazzRole, AuthClazzRoleDTO, AuthClazzRoleVO > {

    @Override
    public AuthClazzRole toJavaObject(AuthClazzRoleDTO authClazzRoleDTO) {
        if (null == authClazzRoleDTO){
            return null;
        }
        AuthClazzRole authClazzRole = new AuthClazzRole();
                authClazzRole.setId(authClazzRoleDTO.getId());
                authClazzRole.setTenantId(authClazzRoleDTO.getTenantId());
                authClazzRole.setAppId(authClazzRoleDTO.getAppId());
                authClazzRole.setClazzCode(authClazzRoleDTO.getClazzCode());
                authClazzRole.setClazzTitle(authClazzRoleDTO.getClazzTitle());
                authClazzRole.setRoleId(authClazzRoleDTO.getRoleId());
                authClazzRole.setRoleTitle(authClazzRoleDTO.getRoleTitle());
                authClazzRole.setValidFlag(authClazzRoleDTO.getValidFlag());
                authClazzRole.setRemarks(authClazzRoleDTO.getRemarks());
        return authClazzRole;
    }


    @Override
    public AuthClazzRoleVO toJavaObjectVO(AuthClazzRole authClazzRole) {
        if (null == authClazzRole){
            return null;
        }
            AuthClazzRoleVO authClazzRoleVO = new AuthClazzRoleVO();
                    authClazzRoleVO.setId(authClazzRole.getId());
                    authClazzRoleVO.setTenantId(authClazzRole.getTenantId());
                    authClazzRoleVO.setAppId(authClazzRole.getAppId());
                    authClazzRoleVO.setClazzCode(authClazzRole.getClazzCode());
                    authClazzRoleVO.setClazzTitle(authClazzRole.getClazzTitle());
                    authClazzRoleVO.setRoleId(authClazzRole.getRoleId());
                    authClazzRoleVO.setRoleTitle(authClazzRole.getRoleTitle());
                    authClazzRoleVO.setValidFlag(authClazzRole.getValidFlag());
                    authClazzRoleVO.setRemarks(authClazzRole.getRemarks());
        return authClazzRoleVO;
    }

    public AuthClazzRole toJavaObject(AuthClazzRoleVO authClazzRoleVO) {
        if (null == authClazzRoleVO){
            return null;
        }
        AuthClazzRole authClazzRole = new AuthClazzRole();
                authClazzRole.setId(authClazzRoleVO.getId());
                authClazzRole.setTenantId(authClazzRoleVO.getTenantId());
                authClazzRole.setAppId(authClazzRoleVO.getAppId());
                authClazzRole.setClazzCode(authClazzRoleVO.getClazzCode());
                authClazzRole.setClazzTitle(authClazzRoleVO.getClazzTitle());
                authClazzRole.setRoleId(authClazzRoleVO.getRoleId());
                authClazzRole.setRoleTitle(authClazzRoleVO.getRoleTitle());
                authClazzRole.setValidFlag(authClazzRoleVO.getValidFlag());
                authClazzRole.setRemarks(authClazzRoleVO.getRemarks());
        return authClazzRole;
    }

    public AuthClazzRoleVO toJavaObjectVO(AuthClazzRoleDTO authClazzRoleDTO) {
        if (null == authClazzRoleDTO){
            return null;
        }
            AuthClazzRoleVO authClazzRoleVO = new AuthClazzRoleVO();
                    authClazzRoleVO.setId(authClazzRoleDTO.getId());
                    authClazzRoleVO.setTenantId(authClazzRoleDTO.getTenantId());
                    authClazzRoleVO.setAppId(authClazzRoleDTO.getAppId());
                    authClazzRoleVO.setClazzCode(authClazzRoleDTO.getClazzCode());
                    authClazzRoleVO.setClazzTitle(authClazzRoleDTO.getClazzTitle());
                    authClazzRoleVO.setRoleId(authClazzRoleDTO.getRoleId());
                    authClazzRoleVO.setRoleTitle(authClazzRoleDTO.getRoleTitle());
                    authClazzRoleVO.setValidFlag(authClazzRoleDTO.getValidFlag());
                    authClazzRoleVO.setRemarks(authClazzRoleDTO.getRemarks());
        return authClazzRoleVO;
    }


}