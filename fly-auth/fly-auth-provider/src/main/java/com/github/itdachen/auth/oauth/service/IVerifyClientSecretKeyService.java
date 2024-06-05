package com.github.itdachen.auth.oauth.service;

/**
 * Description:
 * Created by 王大宸 on 2023/05/05 15:02
 * Created with IntelliJ IDEA.
 */
public interface IVerifyClientSecretKeyService {

    /***
     * 校验客户端
     *
     * @author 王大宸
     * @date 2023/5/5 15:04
     * @param appId 客户端ID
     * @param appSecret 客户端秘钥
     * @return void
     */
    void verifyClient(String appId, String appSecret) throws Exception;

}
