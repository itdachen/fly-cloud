package com.github.itdachen.gateway.service;

/**
 * Description:
 * Created by 王大宸 on 2023-05-26 15:32
 * Created with IntelliJ IDEA.
 */
public interface IAuditFilterService {

    /***
     * 接口审计
     *
     * @author 王大宸
     * @date 2023/5/26 15:33
     * @param ip     请求IP
     * @param apiUri 请求API地址
     * @param method 请求方式
     * @param code   响应码
     * @param executeTime 响应时间
     * @return void
     */
    void auditFilter(String ip, String apiUri, String method, Integer code, Long executeTime);


}
