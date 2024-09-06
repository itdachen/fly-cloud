package com.github.itdachen.admin.mapper;

import com.github.itdachen.admin.entity.AppInfo;
import com.github.itdachen.admin.sdk.query.AppInfoQuery;
import com.github.itdachen.admin.sdk.vo.AppInfoVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 应用信息 持久层接口
 *
 * @author 王大宸
 * @date 2024-09-06 10:16:14
 */
public interface IAppInfoMapper extends Mapper<AppInfo> {

    /***
     * 查询集合
     *
     * @author 王大宸
     * @date 2024-09-06 10:16:14
     * @param params params
     * @return com.github.itdachen.admin.sdk.vo.appInfoVO
     */
    List<AppInfoVO> list(AppInfoQuery params);

    /***
     * 根据id查询
     *
     * @author 王大宸
     * @date 2024-09-06 10:16:14
     * @param id 需要查询的数据id
     * @return com.github.itdachen.admin.sdk.vo.appInfoVo
     */
    AppInfoVO selectAppInfoVO(String id);

    /***
     * 查询导出数据
     *
     * @author 王大宸
     * @date 2024-09-06 10:16:14
     * @param params 查询参数
     * @return java.util.LinkedHashMap
     */
    List<LinkedHashMap<String, String>> selectAppInfoExpData(AppInfoQuery params);

    /***
     * 批量添加
     *
     * @author 王大宸
     * @date 2024-09-06 10:16:14
     * @param list java.util.List<com.github.itdachen.admin.entity.AppInfo>
     */
    void batchSave(List<AppInfo> list);

    /***
     * 批量修改
     *
     * @author 王大宸
     * @date 2024-09-06 10:16:14
     * @param list java.util.List<com.github.itdachen.admin.entity.AppInfo>
     */
    void batchUpdate(List<AppInfo> list);

}
