package com.github.itdachen.admin.mapper;

import com.github.itdachen.admin.entity.UserInfo;
import com.github.itdachen.admin.sdk.query.UserInfoQuery;
import com.github.itdachen.admin.sdk.vo.UserInfoVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 用户管理 持久层接口
 *
 * @author 王大宸
 * @date 2024-09-02 14:38:09
 */
public interface IUserInfoMapper extends Mapper< UserInfo > {

    /***
     * 查询集合
     *
     * @author 王大宸
     * @date 2024-09-02 14:38:09
     * @param params params
     * @return com.github.itdachen.admin.sdk.vo.userInfoVO
     */
    List< UserInfoVO > list(UserInfoQuery params);

    /***
     * 根据id查询
     *
     * @author 王大宸
     * @date 2024-09-02 14:38:09
     * @param id 需要查询的数据id
     * @return com.github.itdachen.admin.sdk.vo.userInfoVo
     */
        UserInfoVO selectUserInfoVO( String id);

    /***
     * 查询导出数据
     *
     * @author 王大宸
     * @date 2024-09-02 14:38:09
     * @param params 查询参数
     * @return java.util.LinkedHashMap
     */
    List< LinkedHashMap< String, String > > selectUserInfoExpData(UserInfoQuery params);

    /***
     * 批量添加
     *
     * @author 王大宸
     * @date 2024-09-02 14:38:09
     * @param list java.util.List<com.github.itdachen.admin.entity.UserInfo>
     */
    void  batchSave(List< UserInfo > list);

    /***
     * 批量修改
     *
     * @author 王大宸
     * @date 2024-09-02 14:38:09
     * @param list java.util.List<com.github.itdachen.admin.entity.UserInfo>
     */
    void batchUpdate(List< UserInfo > list);

}
