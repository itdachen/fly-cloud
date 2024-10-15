package com.github.itdachen.admin.mapper;

import com.github.itdachen.admin.entity.TenantInfo;
import com.github.itdachen.admin.sdk.query.TenantInfoQuery;
import com.github.itdachen.admin.sdk.vo.TenantInfoVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 租户/公司信息 持久层接口
 *
 * @author 王大宸
 * @date 2024-10-15 16:27:41
 */
public interface ITenantInfoMapper extends Mapper< TenantInfo > {

    /***
     * 查询集合
     *
     * @author 王大宸
     * @date 2024-10-15 16:27:41
     * @param params params
     * @return com.github.itdachen.admin.sdk.vo.tenantInfoVO
     */
    List< TenantInfoVO > list(TenantInfoQuery params);

    /***
     * 根据id查询
     *
     * @author 王大宸
     * @date 2024-10-15 16:27:41
     * @param id 需要查询的数据id
     * @return com.github.itdachen.admin.sdk.vo.tenantInfoVo
     */
        TenantInfoVO selectTenantInfoVO( String id);

    /***
     * 查询导出数据
     *
     * @author 王大宸
     * @date 2024-10-15 16:27:41
     * @param params 查询参数
     * @return java.util.LinkedHashMap
     */
    List< LinkedHashMap< String, String > > selectTenantInfoExpData(TenantInfoQuery params);

    /***
     * 批量添加
     *
     * @author 王大宸
     * @date 2024-10-15 16:27:41
     * @param list java.util.List<com.github.itdachen.admin.entity.TenantInfo>
     */
    void  batchSave(List< TenantInfo > list);

    /***
     * 批量修改
     *
     * @author 王大宸
     * @date 2024-10-15 16:27:41
     * @param list java.util.List<com.github.itdachen.admin.entity.TenantInfo>
     */
    void batchUpdate(List< TenantInfo > list);

}
