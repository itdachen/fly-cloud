package com.github.itdachen.auth.convert;

import com.github.itdachen.auth.entity.AuthGrantMenu;
import com.github.itdachen.auth.sdk.dto.AuthGrantMenuDTO;
import com.github.itdachen.auth.sdk.vo.AuthGrantMenuVO;
import com.github.itdachen.framework.webmvc.convert.IBizConvertMapper;

/**
 * 权限下发 类型转换
 *
 * @author 王大宸
 * @date 2025-01-01 17:12:49
 */
public class AuthGrantMenuConvert implements IBizConvertMapper< AuthGrantMenu, AuthGrantMenuDTO, AuthGrantMenuVO > {

    @Override
    public AuthGrantMenu toJavaObject(AuthGrantMenuDTO authGrantMenuDTO) {
        if (null == authGrantMenuDTO){
            return null;
        }
        AuthGrantMenu authGrantMenu = new AuthGrantMenu();
                authGrantMenu.setId(authGrantMenuDTO.getId());
                authGrantMenu.setTenantId(authGrantMenuDTO.getTenantId());
                authGrantMenu.setAppId(authGrantMenuDTO.getAppId());
                authGrantMenu.setRoleId(authGrantMenuDTO.getRoleId());
                authGrantMenu.setMenuId(authGrantMenuDTO.getMenuId());
        return authGrantMenu;
    }


    @Override
    public AuthGrantMenuVO toJavaObjectVO(AuthGrantMenu authGrantMenu) {
        if (null == authGrantMenu){
            return null;
        }
            AuthGrantMenuVO authGrantMenuVO = new AuthGrantMenuVO();
                    authGrantMenuVO.setId(authGrantMenu.getId());
                    authGrantMenuVO.setTenantId(authGrantMenu.getTenantId());
                    authGrantMenuVO.setAppId(authGrantMenu.getAppId());
                    authGrantMenuVO.setRoleId(authGrantMenu.getRoleId());
                    authGrantMenuVO.setMenuId(authGrantMenu.getMenuId());
        return authGrantMenuVO;
    }

    public AuthGrantMenu toJavaObject(AuthGrantMenuVO authGrantMenuVO) {
        if (null == authGrantMenuVO){
            return null;
        }
        AuthGrantMenu authGrantMenu = new AuthGrantMenu();
                authGrantMenu.setId(authGrantMenuVO.getId());
                authGrantMenu.setTenantId(authGrantMenuVO.getTenantId());
                authGrantMenu.setAppId(authGrantMenuVO.getAppId());
                authGrantMenu.setRoleId(authGrantMenuVO.getRoleId());
                authGrantMenu.setMenuId(authGrantMenuVO.getMenuId());
        return authGrantMenu;
    }

    public AuthGrantMenuVO toJavaObjectVO(AuthGrantMenuDTO authGrantMenuDTO) {
        if (null == authGrantMenuDTO){
            return null;
        }
            AuthGrantMenuVO authGrantMenuVO = new AuthGrantMenuVO();
                    authGrantMenuVO.setId(authGrantMenuDTO.getId());
                    authGrantMenuVO.setTenantId(authGrantMenuDTO.getTenantId());
                    authGrantMenuVO.setAppId(authGrantMenuDTO.getAppId());
                    authGrantMenuVO.setRoleId(authGrantMenuDTO.getRoleId());
                    authGrantMenuVO.setMenuId(authGrantMenuDTO.getMenuId());
        return authGrantMenuVO;
    }


}