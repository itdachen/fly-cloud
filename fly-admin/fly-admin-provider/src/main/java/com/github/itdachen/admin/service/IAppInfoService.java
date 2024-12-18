package com.github.itdachen.admin.service;

import com.github.itdachen.admin.sdk.dto.AppInfoDTO;
import com.github.itdachen.admin.sdk.query.AppInfoQuery;
import com.github.itdachen.admin.sdk.vo.AppInfoVO;
import com.github.itdachen.framework.webmvc.service.IBizService;

/**
 * 应用信息 业务接口
 *
 * @author 王大宸
 * @date 2024-09-06 10:16:14
 */
public interface IAppInfoService extends IBizService<AppInfoDTO, AppInfoVO, AppInfoQuery, String> {

    /***
     * 获取秘钥
     *
     * @author 王大宸
     * @date 2024/12/18 20:58
     * @return java.lang.String
     */
    String createSecret() throws Exception;

}
