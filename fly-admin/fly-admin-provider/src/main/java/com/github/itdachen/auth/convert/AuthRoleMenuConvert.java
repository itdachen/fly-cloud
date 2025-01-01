package com.github.itdachen.auth.convert;

import com.github.itdachen.auth.entity.AuthRoleMenu;
import com.github.itdachen.auth.sdk.dto.AuthRoleMenuDTO;
import com.github.itdachen.auth.sdk.vo.AuthRoleMenuVO;
import com.github.itdachen.framework.webmvc.convert.IBizConvertMapper;

/**
 * 人员身份菜单 类型转换
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:49
 */
public class AuthRoleMenuConvert implements IBizConvertMapper< AuthRoleMenu, AuthRoleMenuDTO, AuthRoleMenuVO > {

    @Override
    public AuthRoleMenu toJavaObject(AuthRoleMenuDTO authRoleMenuDTO) {
        if (null == authRoleMenuDTO){
            return null;
        }
        AuthRoleMenu authRoleMenu = new AuthRoleMenu();
                authRoleMenu.setId(authRoleMenuDTO.getId());
                authRoleMenu.setTenantId(authRoleMenuDTO.getTenantId());
                authRoleMenu.setAppId(authRoleMenuDTO.getAppId());
                authRoleMenu.setRoleId(authRoleMenuDTO.getRoleId());
                authRoleMenu.setMenuId(authRoleMenuDTO.getMenuId());
        return authRoleMenu;
    }


    @Override
    public AuthRoleMenuVO toJavaObjectVO(AuthRoleMenu authRoleMenu) {
        if (null == authRoleMenu){
            return null;
        }
            AuthRoleMenuVO authRoleMenuVO = new AuthRoleMenuVO();
                    authRoleMenuVO.setId(authRoleMenu.getId());
                    authRoleMenuVO.setTenantId(authRoleMenu.getTenantId());
                    authRoleMenuVO.setAppId(authRoleMenu.getAppId());
                    authRoleMenuVO.setRoleId(authRoleMenu.getRoleId());
                    authRoleMenuVO.setMenuId(authRoleMenu.getMenuId());
        return authRoleMenuVO;
    }

    public AuthRoleMenu toJavaObject(AuthRoleMenuVO authRoleMenuVO) {
        if (null == authRoleMenuVO){
            return null;
        }
        AuthRoleMenu authRoleMenu = new AuthRoleMenu();
                authRoleMenu.setId(authRoleMenuVO.getId());
                authRoleMenu.setTenantId(authRoleMenuVO.getTenantId());
                authRoleMenu.setAppId(authRoleMenuVO.getAppId());
                authRoleMenu.setRoleId(authRoleMenuVO.getRoleId());
                authRoleMenu.setMenuId(authRoleMenuVO.getMenuId());
        return authRoleMenu;
    }

    public AuthRoleMenuVO toJavaObjectVO(AuthRoleMenuDTO authRoleMenuDTO) {
        if (null == authRoleMenuDTO){
            return null;
        }
            AuthRoleMenuVO authRoleMenuVO = new AuthRoleMenuVO();
                    authRoleMenuVO.setId(authRoleMenuDTO.getId());
                    authRoleMenuVO.setTenantId(authRoleMenuDTO.getTenantId());
                    authRoleMenuVO.setAppId(authRoleMenuDTO.getAppId());
                    authRoleMenuVO.setRoleId(authRoleMenuDTO.getRoleId());
                    authRoleMenuVO.setMenuId(authRoleMenuDTO.getMenuId());
        return authRoleMenuVO;
    }


}