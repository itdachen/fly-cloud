package com.github.itdachen.gateway.service;

import com.github.itdachen.framework.context.entity.ApiLogClient;

/**
 * Description:
 * Created by 王大宸 on 2023-05-26 15:10
 * Created with IntelliJ IDEA.
 */
public interface ILogService {

    void saveApiLog(ApiLogClient apiLogDto, String token);

    void updateApiLog(ApiLogClient apiLogDto, String token);

}
