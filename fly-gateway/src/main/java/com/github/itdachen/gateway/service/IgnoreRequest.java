package com.github.itdachen.gateway.service;

/**
 * Description: 判断是否不需要认证
 * Created by 王大宸 on 2023-05-26 15:25
 * Created with IntelliJ IDEA.
 */
public interface IgnoreRequest {


    Boolean verifyUriMatchers(String uri);


}
