package com.github.itdachen.admin.controller;

import com.github.itdachen.admin.sdk.vo.AdmAreaModel;
import com.github.itdachen.admin.service.IAdmAreaOpenService;
import com.github.itdachen.framework.context.annotation.FuncTitle;
import com.github.itdachen.framework.context.annotation.IgnoreUserToken;
import com.github.itdachen.framework.core.response.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 行政区划代码
 *
 * @author 王大宸
 * @date 2024-12-19 09:45:17
 */
@RestController
@RequestMapping("/open/adm/area")
@FuncTitle("行政区划代码")
@IgnoreUserToken
public class AdmAreaOpenController {
    private static final Logger logger = LoggerFactory.getLogger(AdmAreaOpenController.class);

    private final IAdmAreaOpenService admAreaService;

    public AdmAreaOpenController(IAdmAreaOpenService admAreaService) {
        this.admAreaService = admAreaService;
    }

    /***
     * 获取省级集合
     *
     * @author 王大宸
     * @date 2024/12/19 9:51
     * @return com.github.itdachen.framework.core.response.ServerResponse<java.util.List < com.github.itdachen.admin.entity.AdmArea>>
     */
    @GetMapping("/prov/list")
    public ServerResponse<List<AdmAreaModel>> findProvList() throws Exception {
        return ServerResponse.ok(admAreaService.findProvList());
    }

    /***
     * 获取市州集合
     *
     * @author 王大宸
     * @date 2024/12/19 10:01
     * @param provId 省级代码
     * @return com.github.itdachen.framework.core.response.ServerResponse<java.util.List < com.github.itdachen.admin.sdk.vo.AdmAreaModel>>
     */
    @GetMapping("/city/{provId}")
    public ServerResponse<List<AdmAreaModel>> findCityList(@PathVariable("provId") String provId) throws Exception {
        return ServerResponse.ok(admAreaService.findCityList(provId));
    }

    /***
     * 获取区县集合
     *
     * @author 王大宸
     * @date 2024/12/19 10:00
     * @param cityId 市州ID
     * @return com.github.itdachen.framework.core.response.ServerResponse<java.util.List < com.github.itdachen.admin.sdk.vo.AdmAreaModel>>
     */
    @GetMapping("/county/{cityId}")
    public ServerResponse<List<AdmAreaModel>> findCountyList(@PathVariable("cityId") String cityId) throws Exception {
        return ServerResponse.ok(admAreaService.findCountyList(cityId));
    }

    /***
     * 获取乡镇集合
     *
     * @author 王大宸
     * @date 2024/12/19 10:00
     * @param countyId 区县ID
     * @return com.github.itdachen.framework.core.response.ServerResponse<java.util.List < com.github.itdachen.admin.sdk.vo.AdmAreaModel>>
     */
    @GetMapping("/town/{countyId}")
    public ServerResponse<List<AdmAreaModel>> findTownList(@PathVariable("countyId") String countyId) throws Exception {
        return ServerResponse.ok(admAreaService.findTownList(countyId));
    }

    /***
     * 获取街道集合
     *
     * @author 王大宸
     * @date 2024/12/19 10:00
     * @param townId 乡镇ID
     * @return com.github.itdachen.framework.core.response.ServerResponse<java.util.List < com.github.itdachen.admin.sdk.vo.AdmAreaModel>>
     */
    @GetMapping("/street/{townId}")
    public ServerResponse<List<AdmAreaModel>> findStreetList(@PathVariable("townId") String townId) throws Exception {
        return ServerResponse.ok(admAreaService.findStreetList(townId));
    }

    /***
     * 获取集合
     *
     * @author 王大宸
     * @date 2024/12/19 10:35
     * @param parentId parentId
     * @param level level
     * @return com.github.itdachen.framework.core.response.ServerResponse<java.util.List < com.github.itdachen.admin.sdk.vo.AdmAreaModel>>
     */
    @GetMapping("/list")
    public ServerResponse<List<AdmAreaModel>> findAdmAreaList(String parentId, String level) throws Exception {
        return ServerResponse.ok(admAreaService.findAdmAreaList(parentId, level));
    }


}