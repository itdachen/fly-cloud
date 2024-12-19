import httpAxios from "@/fly/http";
import {AdmArea} from "@/api/open/model/AdmAreaModel";
import {ServerResponse} from "axios";


/**
 * 请求路径
 */
const ADM_AREA_PATH = '/open/adm/area';

/**
 * 区域接口
 */
class OpenAdmAreaApi {

    /**
     * 获取集合
     * @param parentId 上级ID
     * @param level    级次: 1-省级;2-市州级;3-区县级;4-乡镇级/;5-街道办
     */
    list(parentId: string, level: string): Promise<ServerResponse<AdmArea[]>> {
        return httpAxios.get(ADM_AREA_PATH + `/list?parentId=${parentId}&level=${level}`);
    }


    /**
     * 获取省级集合
     */
    findProvList(): Promise<ServerResponse<AdmArea[]>> {
        return httpAxios.get(ADM_AREA_PATH + `/prov/list`);
    }


    /**
     * 获取市州集合
     * @param provId 省级ID
     */
    findCityList(provId: string): Promise<ServerResponse<AdmArea[]>> {
        return httpAxios.get(ADM_AREA_PATH + `/city/${provId}`);
    }


    /**
     * 获取区县集合
     * @param cityId 市州级ID
     */
    findCountyList(cityId: string): Promise<ServerResponse<AdmArea[]>> {
        return httpAxios.get(ADM_AREA_PATH + `/county/${cityId}`);
    }


    /**
     * 获取乡镇集合
     * @param countyId 区县级ID
     */
    findTownList(countyId: string): Promise<ServerResponse<AdmArea[]>> {
        return httpAxios.get(ADM_AREA_PATH + `/town/${countyId}`);
    }

    /**
     * 获取街道集合
     * @param townId 街道级ID
     */
    findStreetList(townId: string): Promise<ServerResponse<AdmArea[]>> {
        return httpAxios.get(ADM_AREA_PATH + `/street/${townId}`);
    }


}

export default OpenAdmAreaApi
