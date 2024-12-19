package com.github.itdachen.admin.mapper;

import com.github.itdachen.admin.sdk.vo.AdmAreaModel;

import java.util.List;

/**
 * 行政区划代码 持久层接口
 *
 * @author 王大宸
 * @date 2024-12-19 09:45:17
 */
public interface IAdmAreaOpenMapper {

    /***
     * 获取省级集合
     *
     * @author 王大宸
     * @date 2024/12/19 9:50
     * @return java.util.List<com.github.itdachen.admin.entity.AdmArea>
     */
    List<AdmAreaModel> findProvList();

    /***
     * 获取市州集合
     *
     * @author 王大宸
     * @date 2024/12/19 9:55
     * @param provId 省级代码
     * @return java.util.List<com.github.itdachen.admin.sdk.vo.AdmAreaModel>
     */
    List<AdmAreaModel> findCityList(String provId);

    /***
     * 获取区县集合
     *
     * @author 王大宸
     * @date 2024/12/19 9:57
     * @param cityId 市州ID
     * @return java.util.List<com.github.itdachen.admin.sdk.vo.AdmAreaModel>
     */
    List<AdmAreaModel> findCountyList(String cityId);

    /***
     * 获取乡镇集合
     *
     * @author 王大宸
     * @date 2024/12/19 9:58
     * @param countyId 区县ID
     * @return java.util.List<com.github.itdachen.admin.sdk.vo.AdmAreaModel>
     */
    List<AdmAreaModel> findTownList(String countyId);

    /***
     * 获取街道集合
     *
     * @author 王大宸
     * @date 2024/12/19 10:00
     * @param townId 乡镇ID
     * @return java.util.List<com.github.itdachen.admin.sdk.vo.AdmAreaModel>
     */
    List<AdmAreaModel> findStreetList(String townId);


}
