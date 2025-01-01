package com.github.itdachen.auth.convert;

import com.github.itdachen.auth.entity.AuthClazzMenu;
import com.github.itdachen.auth.sdk.dto.AuthClazzMenuDTO;
import com.github.itdachen.auth.sdk.vo.AuthClazzMenuVO;
import com.github.itdachen.framework.webmvc.convert.IBizConvertMapper;

/**
 * 岗位菜单 类型转换
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:50
 */
public class AuthClazzMenuConvert implements IBizConvertMapper< AuthClazzMenu, AuthClazzMenuDTO, AuthClazzMenuVO > {

    @Override
    public AuthClazzMenu toJavaObject(AuthClazzMenuDTO authClazzMenuDTO) {
        if (null == authClazzMenuDTO){
            return null;
        }
        AuthClazzMenu authClazzMenu = new AuthClazzMenu();
                authClazzMenu.setId(authClazzMenuDTO.getId());
                authClazzMenu.setTenantId(authClazzMenuDTO.getTenantId());
                authClazzMenu.setAppId(authClazzMenuDTO.getAppId());
                authClazzMenu.setClazzCode(authClazzMenuDTO.getClazzCode());
                authClazzMenu.setClazzTitle(authClazzMenuDTO.getClazzTitle());
                authClazzMenu.setMenuId(authClazzMenuDTO.getMenuId());
                authClazzMenu.setMenuTitle(authClazzMenuDTO.getMenuTitle());
        return authClazzMenu;
    }


    @Override
    public AuthClazzMenuVO toJavaObjectVO(AuthClazzMenu authClazzMenu) {
        if (null == authClazzMenu){
            return null;
        }
            AuthClazzMenuVO authClazzMenuVO = new AuthClazzMenuVO();
                    authClazzMenuVO.setId(authClazzMenu.getId());
                    authClazzMenuVO.setTenantId(authClazzMenu.getTenantId());
                    authClazzMenuVO.setAppId(authClazzMenu.getAppId());
                    authClazzMenuVO.setClazzCode(authClazzMenu.getClazzCode());
                    authClazzMenuVO.setClazzTitle(authClazzMenu.getClazzTitle());
                    authClazzMenuVO.setMenuId(authClazzMenu.getMenuId());
                    authClazzMenuVO.setMenuTitle(authClazzMenu.getMenuTitle());
        return authClazzMenuVO;
    }

    public AuthClazzMenu toJavaObject(AuthClazzMenuVO authClazzMenuVO) {
        if (null == authClazzMenuVO){
            return null;
        }
        AuthClazzMenu authClazzMenu = new AuthClazzMenu();
                authClazzMenu.setId(authClazzMenuVO.getId());
                authClazzMenu.setTenantId(authClazzMenuVO.getTenantId());
                authClazzMenu.setAppId(authClazzMenuVO.getAppId());
                authClazzMenu.setClazzCode(authClazzMenuVO.getClazzCode());
                authClazzMenu.setClazzTitle(authClazzMenuVO.getClazzTitle());
                authClazzMenu.setMenuId(authClazzMenuVO.getMenuId());
                authClazzMenu.setMenuTitle(authClazzMenuVO.getMenuTitle());
        return authClazzMenu;
    }

    public AuthClazzMenuVO toJavaObjectVO(AuthClazzMenuDTO authClazzMenuDTO) {
        if (null == authClazzMenuDTO){
            return null;
        }
            AuthClazzMenuVO authClazzMenuVO = new AuthClazzMenuVO();
                    authClazzMenuVO.setId(authClazzMenuDTO.getId());
                    authClazzMenuVO.setTenantId(authClazzMenuDTO.getTenantId());
                    authClazzMenuVO.setAppId(authClazzMenuDTO.getAppId());
                    authClazzMenuVO.setClazzCode(authClazzMenuDTO.getClazzCode());
                    authClazzMenuVO.setClazzTitle(authClazzMenuDTO.getClazzTitle());
                    authClazzMenuVO.setMenuId(authClazzMenuDTO.getMenuId());
                    authClazzMenuVO.setMenuTitle(authClazzMenuDTO.getMenuTitle());
        return authClazzMenuVO;
    }


}