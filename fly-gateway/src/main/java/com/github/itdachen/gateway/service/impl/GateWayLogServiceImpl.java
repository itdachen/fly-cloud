package com.github.itdachen.gateway.service.impl;

import com.github.itdachen.framework.context.entity.ApiLogClient;
import com.github.itdachen.gateway.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Description: 操作日志
 * Created by 王大宸 on 2023-05-26 15:21
 * Created with IntelliJ IDEA.
 */
@Service
public class GateWayLogServiceImpl implements ILogService {


    @Autowired
    private WebClient.Builder webClientBuilder;

    @Override
    public void saveApiLog(ApiLogClient apiLogDto, String token) {

    }

    @Override
    public void updateApiLog(ApiLogClient apiLogDto, String token) {

    }
}
