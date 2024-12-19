package com.github.itdachen.admin.service.impl;

import com.github.itdachen.admin.mapper.IAdmAreaOpenMapper;
import com.github.itdachen.admin.sdk.vo.AdmAreaModel;
import com.github.itdachen.admin.service.IAdmAreaOpenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 行政区划代码 业务实现
 *
 * @author 王大宸
 * @date 2024-12-19 09:45:17
 */
@Service
public class AdmAreaOpenServiceImpl implements IAdmAreaOpenService {
    private static final Logger logger = LoggerFactory.getLogger(AdmAreaOpenServiceImpl.class);

    @Autowired
    private IAdmAreaOpenMapper openAdmAreaMapper;

    /***
     * 获取省级集合
     *
     * @author 王大宸
     * @date 2024/12/19 9:50
     * @return java.util.List<com.github.itdachen.admin.entity.AdmArea>
     */
    @Override
    public List<AdmAreaModel> findProvList() throws Exception {
        return openAdmAreaMapper.findProvList();
    }

    /***
     * 获取市州集合
     *
     * @author 王大宸
     * @date 2024/12/19 9:55
     * @param provId 省级代码
     * @return java.util.List<com.github.itdachen.admin.sdk.vo.AdmAreaModel>
     */
    @Override
    public List<AdmAreaModel> findCityList(String provId) throws Exception {
        return openAdmAreaMapper.findCityList(provId);
    }

    /***
     * 获取区县集合
     *
     * @author 王大宸
     * @date 2024/12/19 9:57
     * @param cityId 市州ID
     * @return java.util.List<com.github.itdachen.admin.sdk.vo.AdmAreaModel>
     */
    @Override
    public List<AdmAreaModel> findCountyList(String cityId) throws Exception {
        return openAdmAreaMapper.findCountyList(cityId);
    }

    /***
     * 获取乡镇集合
     *
     * @author 王大宸
     * @date 2024/12/19 9:58
     * @param countyId 区县ID
     * @return java.util.List<com.github.itdachen.admin.sdk.vo.AdmAreaModel>
     */
    @Override
    public List<AdmAreaModel> findTownList(String countyId) throws Exception {
        return openAdmAreaMapper.findTownList(countyId);
    }

    /***
     * 获取街道集合
     *
     * @author 王大宸
     * @date 2024/12/19 10:00
     * @param townId 乡镇ID
     * @return java.util.List<com.github.itdachen.admin.sdk.vo.AdmAreaModel>
     */
    @Override
    public List<AdmAreaModel> findStreetList(String townId) throws Exception {
        return openAdmAreaMapper.findStreetList(townId);
    }

    /***
     * 获取区域集合
     *
     * @author 王大宸
     * @date 2024/12/19 10:36
     * @param parentId 上级ID
     * @param level    层级
     * @return java.util.List<com.github.itdachen.admin.sdk.vo.AdmAreaModel>
     */
    @Override
    public List<AdmAreaModel> findAdmAreaList(String parentId, String level) throws Exception {
        if ("5".equals(level)) {
            return openAdmAreaMapper.findStreetList(parentId);
        }
        if ("4".equals(level)) {
            return openAdmAreaMapper.findTownList(parentId);
        }
        if ("3".equals(level)) {
            return openAdmAreaMapper.findCountyList(parentId);
        }
        if ("2".equals(level)) {
            return openAdmAreaMapper.findCityList(parentId);
        }
        return openAdmAreaMapper.findProvList();
    }

}
