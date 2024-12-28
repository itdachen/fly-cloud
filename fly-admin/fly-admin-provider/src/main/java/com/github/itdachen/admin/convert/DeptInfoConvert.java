package com.github.itdachen.admin.convert;

import com.github.itdachen.admin.entity.DeptInfo;
import com.github.itdachen.admin.sdk.dto.DeptInfoDTO;
import com.github.itdachen.admin.sdk.vo.DeptInfoVO;
import com.github.itdachen.framework.webmvc.convert.IBizConvertMapper;

/**
 * 部门信息 类型转换
 *
 * @author 王大宸
 * @date 2024-12-22 22:30:09
 */
public class DeptInfoConvert implements IBizConvertMapper<DeptInfo, DeptInfoDTO, DeptInfoVO> {

    @Override
    public DeptInfo toJavaObject(DeptInfoDTO deptInfoDTO) {
        if (null == deptInfoDTO) {
            return null;
        }
        DeptInfo deptInfo = new DeptInfo();
        deptInfo.setId(deptInfoDTO.getId());
        deptInfo.setTenantId(deptInfoDTO.getTenantId());
        deptInfo.setDeptCode(deptInfoDTO.getDeptCode());
        deptInfo.setParentCode(deptInfoDTO.getParentCode());
        deptInfo.setTitle(deptInfoDTO.getTitle());
        deptInfo.setTitleAs(deptInfoDTO.getTitleAs());
        deptInfo.setDeptFlag(deptInfoDTO.getDeptFlag());
        deptInfo.setThatLevel(deptInfoDTO.getThatLevel());
        deptInfo.setLevelCode(deptInfoDTO.getLevelCode());
        deptInfo.setLevelTitle(deptInfoDTO.getLevelTitle());
        deptInfo.setFuncCode(deptInfoDTO.getFuncCode());
        deptInfo.setFuncTitle(deptInfoDTO.getFuncTitle());
        deptInfo.setTelephone(deptInfoDTO.getTelephone());
        deptInfo.setMailBox(deptInfoDTO.getMailBox());
        deptInfo.setFacsimile(deptInfoDTO.getFacsimile());
        deptInfo.setProvId(deptInfoDTO.getProvId());
        deptInfo.setProvTitle(deptInfoDTO.getProvTitle());
        deptInfo.setCityId(deptInfoDTO.getCityId());
        deptInfo.setCityTitle(deptInfoDTO.getCityTitle());
        deptInfo.setCountyId(deptInfoDTO.getCountyId());
        deptInfo.setCountyTitle(deptInfoDTO.getCountyTitle());
        deptInfo.setAddress(deptInfoDTO.getAddress());
        deptInfo.setValidFlag(deptInfoDTO.getValidFlag());
        deptInfo.setRemarks(deptInfoDTO.getRemarks());
        return deptInfo;
    }


    @Override
    public DeptInfoVO toJavaObjectVO(DeptInfo deptInfo) {
        if (null == deptInfo) {
            return null;
        }
        DeptInfoVO deptInfoVO = new DeptInfoVO();
        deptInfoVO.setId(deptInfo.getId());
        deptInfoVO.setTenantId(deptInfo.getTenantId());
        deptInfoVO.setDeptCode(deptInfo.getDeptCode());
        deptInfoVO.setParentCode(deptInfo.getParentCode());
        deptInfoVO.setTitle(deptInfo.getTitle());
        deptInfoVO.setTitleAs(deptInfo.getTitleAs());
        deptInfoVO.setDeptFlag(deptInfo.getDeptFlag());
        deptInfoVO.setThatLevel(deptInfo.getThatLevel());
        deptInfoVO.setLevelCode(deptInfo.getLevelCode());
        deptInfoVO.setLevelTitle(deptInfo.getLevelTitle());
        deptInfoVO.setFuncCode(deptInfo.getFuncCode());
        deptInfoVO.setFuncTitle(deptInfo.getFuncTitle());
        deptInfoVO.setTelephone(deptInfo.getTelephone());
        deptInfoVO.setMailBox(deptInfo.getMailBox());
        deptInfoVO.setFacsimile(deptInfo.getFacsimile());
        deptInfoVO.setProvId(deptInfo.getProvId());
        deptInfoVO.setProvTitle(deptInfo.getProvTitle());
        deptInfoVO.setCityId(deptInfo.getCityId());
        deptInfoVO.setCityTitle(deptInfo.getCityTitle());
        deptInfoVO.setCountyId(deptInfo.getCountyId());
        deptInfoVO.setCountyTitle(deptInfo.getCountyTitle());
        deptInfoVO.setAddress(deptInfo.getAddress());
        deptInfoVO.setValidFlag(deptInfo.getValidFlag());
        deptInfoVO.setRemarks(deptInfo.getRemarks());
        return deptInfoVO;
    }

    public DeptInfo toJavaObject(DeptInfoVO deptInfoVO) {
        if (null == deptInfoVO) {
            return null;
        }
        DeptInfo deptInfo = new DeptInfo();
        deptInfo.setId(deptInfoVO.getId());
        deptInfo.setTenantId(deptInfoVO.getTenantId());
        deptInfo.setDeptCode(deptInfoVO.getDeptCode());
        deptInfo.setParentCode(deptInfoVO.getParentCode());
        deptInfo.setTitle(deptInfoVO.getTitle());
        deptInfo.setTitleAs(deptInfoVO.getTitleAs());
        deptInfo.setDeptFlag(deptInfoVO.getDeptFlag());
        deptInfo.setThatLevel(deptInfoVO.getThatLevel());
        deptInfo.setLevelCode(deptInfoVO.getLevelCode());
        deptInfo.setLevelTitle(deptInfoVO.getLevelTitle());
        deptInfo.setFuncCode(deptInfoVO.getFuncCode());
        deptInfo.setFuncTitle(deptInfoVO.getFuncTitle());
        deptInfo.setTelephone(deptInfoVO.getTelephone());
        deptInfo.setMailBox(deptInfoVO.getMailBox());
        deptInfo.setFacsimile(deptInfoVO.getFacsimile());
        deptInfo.setProvId(deptInfoVO.getProvId());
        deptInfo.setProvTitle(deptInfoVO.getProvTitle());
        deptInfo.setCityId(deptInfoVO.getCityId());
        deptInfo.setCityTitle(deptInfoVO.getCityTitle());
        deptInfo.setCountyId(deptInfoVO.getCountyId());
        deptInfo.setCountyTitle(deptInfoVO.getCountyTitle());
        deptInfo.setAddress(deptInfoVO.getAddress());
        deptInfo.setValidFlag(deptInfoVO.getValidFlag());
        deptInfo.setRemarks(deptInfoVO.getRemarks());
        return deptInfo;
    }

    public DeptInfoVO toJavaObjectVO(DeptInfoDTO deptInfoDTO) {
        if (null == deptInfoDTO) {
            return null;
        }
        DeptInfoVO deptInfoVO = new DeptInfoVO();
        deptInfoVO.setId(deptInfoDTO.getId());
        deptInfoVO.setTenantId(deptInfoDTO.getTenantId());
        deptInfoVO.setDeptCode(deptInfoDTO.getDeptCode());
        deptInfoVO.setParentCode(deptInfoDTO.getParentCode());
        deptInfoVO.setTitle(deptInfoDTO.getTitle());
        deptInfoVO.setTitleAs(deptInfoDTO.getTitleAs());
        deptInfoVO.setDeptFlag(deptInfoDTO.getDeptFlag());
        deptInfoVO.setThatLevel(deptInfoDTO.getThatLevel());
        deptInfoVO.setLevelCode(deptInfoDTO.getLevelCode());
        deptInfoVO.setLevelTitle(deptInfoDTO.getLevelTitle());
        deptInfoVO.setFuncCode(deptInfoDTO.getFuncCode());
        deptInfoVO.setFuncTitle(deptInfoDTO.getFuncTitle());
        deptInfoVO.setTelephone(deptInfoDTO.getTelephone());
        deptInfoVO.setMailBox(deptInfoDTO.getMailBox());
        deptInfoVO.setFacsimile(deptInfoDTO.getFacsimile());
        deptInfoVO.setProvId(deptInfoDTO.getProvId());
        deptInfoVO.setProvTitle(deptInfoDTO.getProvTitle());
        deptInfoVO.setCityId(deptInfoDTO.getCityId());
        deptInfoVO.setCityTitle(deptInfoDTO.getCityTitle());
        deptInfoVO.setCountyId(deptInfoDTO.getCountyId());
        deptInfoVO.setCountyTitle(deptInfoDTO.getCountyTitle());
        deptInfoVO.setAddress(deptInfoDTO.getAddress());
        deptInfoVO.setValidFlag(deptInfoDTO.getValidFlag());
        deptInfoVO.setRemarks(deptInfoDTO.getRemarks());
        return deptInfoVO;
    }


}