package com.github.itdachen.admin.convert;

import com.github.itdachen.admin.entity.DeptFunc;
import com.github.itdachen.admin.sdk.dto.DeptFuncDTO;
import com.github.itdachen.admin.sdk.vo.DeptFuncVO;
import com.github.itdachen.framework.webmvc.convert.IBizConvertMapper;

/**
 * 部门职能 类型转换
 *
 * @author 王大宸
 * @date 2024-12-20 14:40:35
 */
public class DeptFuncConvert implements IBizConvertMapper< DeptFunc, DeptFuncDTO, DeptFuncVO > {

    @Override
    public DeptFunc toJavaObject(DeptFuncDTO deptFuncDTO) {
        if (null == deptFuncDTO){
            return null;
        }
        DeptFunc deptFunc = new DeptFunc();
                deptFunc.setId(deptFuncDTO.getId());
                deptFunc.setTenantId(deptFuncDTO.getTenantId());
                deptFunc.setFuncCode(deptFuncDTO.getFuncCode());
                deptFunc.setFuncTitle(deptFuncDTO.getFuncTitle());
                deptFunc.setValidFlag(deptFuncDTO.getValidFlag());
                deptFunc.setRemarks(deptFuncDTO.getRemarks());
        return deptFunc;
    }


    @Override
    public DeptFuncVO toJavaObjectVO(DeptFunc deptFunc) {
        if (null == deptFunc){
            return null;
        }
            DeptFuncVO deptFuncVO = new DeptFuncVO();
                    deptFuncVO.setId(deptFunc.getId());
                    deptFuncVO.setTenantId(deptFunc.getTenantId());
                    deptFuncVO.setFuncCode(deptFunc.getFuncCode());
                    deptFuncVO.setFuncTitle(deptFunc.getFuncTitle());
                    deptFuncVO.setValidFlag(deptFunc.getValidFlag());
                    deptFuncVO.setRemarks(deptFunc.getRemarks());
        return deptFuncVO;
    }

    public DeptFunc toJavaObject(DeptFuncVO deptFuncVO) {
        if (null == deptFuncVO){
            return null;
        }
        DeptFunc deptFunc = new DeptFunc();
                deptFunc.setId(deptFuncVO.getId());
                deptFunc.setTenantId(deptFuncVO.getTenantId());
                deptFunc.setFuncCode(deptFuncVO.getFuncCode());
                deptFunc.setFuncTitle(deptFuncVO.getFuncTitle());
                deptFunc.setValidFlag(deptFuncVO.getValidFlag());
                deptFunc.setRemarks(deptFuncVO.getRemarks());
        return deptFunc;
    }

    public DeptFuncVO toJavaObjectVO(DeptFuncDTO deptFuncDTO) {
        if (null == deptFuncDTO){
            return null;
        }
            DeptFuncVO deptFuncVO = new DeptFuncVO();
                    deptFuncVO.setId(deptFuncDTO.getId());
                    deptFuncVO.setTenantId(deptFuncDTO.getTenantId());
                    deptFuncVO.setFuncCode(deptFuncDTO.getFuncCode());
                    deptFuncVO.setFuncTitle(deptFuncDTO.getFuncTitle());
                    deptFuncVO.setValidFlag(deptFuncDTO.getValidFlag());
                    deptFuncVO.setRemarks(deptFuncDTO.getRemarks());
        return deptFuncVO;
    }


}