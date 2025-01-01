package com.github.itdachen.admin.service;

import com.github.itdachen.admin.sdk.dto.MenuInfoDTO;
import com.github.itdachen.admin.sdk.query.MenuInfoQuery;
import com.github.itdachen.admin.sdk.vo.MenuInfoVO;
import com.github.itdachen.framework.context.tree.lay.LayTree;
import com.github.itdachen.framework.webmvc.service.IBizService;

/**
 * 菜单信息 业务接口
 *
 * @author 王大宸
 * @date 2024-12-30 10:26:52
 */
public interface IMenuInfoService extends IBizService< MenuInfoDTO, MenuInfoVO, MenuInfoQuery, String > {


    LayTree findMenuTree(String appId) throws Exception;

    /***
     * 获取目录菜单树
     *
     * @author 王大宸
     * @date 2025/1/1 16:15
     * @param appId appId
     * @return com.github.itdachen.framework.context.tree.lay.LayTree
     */
    LayTree findDirtMenuTree(String appId)  throws Exception;

}
