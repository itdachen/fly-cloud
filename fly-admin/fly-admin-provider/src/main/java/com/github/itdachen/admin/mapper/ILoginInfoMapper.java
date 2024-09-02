package com.github.itdachen.admin.mapper;

import com.github.itdachen.admin.entity.LoginInfo;
import tk.mybatis.mapper.common.Mapper;

/**
 * 登录信息 持久层接口
 *
 * @author 王大宸
 * @date 2024-09-02 16:16:52
 */
public interface ILoginInfoMapper extends Mapper<LoginInfo> {

    /***
     * 根据登录账号查询是否存在
     *
     * @author 王大宸
     * @date 2024/9/2 17:01
     * @param username username
     * @return java.lang.Integer
     */
    Integer findLoginInfoByUsername(String username);

}
