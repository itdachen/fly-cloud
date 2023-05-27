package com.github.itdachen.gateway.service.impl;

import com.github.itdachen.gateway.service.IAuditFilterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Description: 接口审计记录
 * Created by 王大宸 on 2023-05-26 15:34
 * Created with IntelliJ IDEA.
 */
@Service
public class GatewayAuditFilterService implements IAuditFilterService {
    private static final Logger logger = LoggerFactory.getLogger(GatewayAuditFilterService.class);

    @Override
    public void auditFilter(String ip, String apiUri, String method, Integer code, Long executeTime) {
        // 当前仅记录日志，后续可以添加日志队列，来过滤请求慢的接口
        logger.info("来自IP地址：{}, 的请求接口：{}, 请求方法: {}, 响应状态码：{}, 请求耗时：{} ms",
                ip, apiUri, method, code, executeTime);
    }

}
