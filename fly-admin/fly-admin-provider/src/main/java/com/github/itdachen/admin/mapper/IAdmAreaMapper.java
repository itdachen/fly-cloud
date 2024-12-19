package com.github.itdachen.admin.mapper;

import com.github.itdachen.admin.entity.AdmArea;
import com.github.itdachen.admin.sdk.query.AdmAreaQuery;
import com.github.itdachen.admin.sdk.vo.AdmAreaModel;
import com.github.itdachen.admin.sdk.vo.AdmAreaVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 行政区划代码 持久层接口
 *
 * @author 王大宸
 * @date 2024-12-19 09:45:17
 */
public interface IAdmAreaMapper extends Mapper<AdmArea> {

    /***
     * 查询集合
     *
     * @author 王大宸
     * @date 2024-12-19 09:45:17
     * @param params params
     * @return com.github.itdachen.admin.sdk.vo.admAreaVO
     */
    List<AdmAreaVO> list(AdmAreaQuery params);

    /***
     * 根据id查询
     *
     * @author 王大宸
     * @date 2024-12-19 09:45:17
     * @param id 需要查询的数据id
     * @return com.github.itdachen.admin.sdk.vo.admAreaVo
     */
    AdmAreaVO selectAdmAreaVO(String id);

    /***
     * 查询导出数据
     *
     * @author 王大宸
     * @date 2024-12-19 09:45:17
     * @param params 查询参数
     * @return java.util.LinkedHashMap
     */
    List<LinkedHashMap<String, String>> selectAdmAreaExpData(AdmAreaQuery params);

    /***
     * 批量添加
     *
     * @author 王大宸
     * @date 2024-12-19 09:45:17
     * @param list java.util.List<com.github.itdachen.admin.entity.AdmArea>
     */
    void batchSave(List<AdmArea> list);

    /***
     * 批量修改
     *
     * @author 王大宸
     * @date 2024-12-19 09:45:17
     * @param list java.util.List<com.github.itdachen.admin.entity.AdmArea>
     */
    void batchUpdate(List<AdmArea> list);

}
