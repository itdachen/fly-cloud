package com.github.itdachen.auth.oauth.service.impl;

import com.github.itdachen.auth.oauth.service.IVerifyClientSecretKeyService;
import org.springframework.stereotype.Service;

/**
 * Description:
 * Created by 王大宸 on 2023/05/05 15:03
 * Created with IntelliJ IDEA.
 */
@Service
public class VerifyClientSecretKeyServiceImpl implements IVerifyClientSecretKeyService {

    /***
     * 校验客户端
     *
     * @author 王大宸
     * @date 2023/5/5 15:04
     * @param appId 客户端ID
     * @param appSecret 客户端秘钥
     * @return void
     */
    @Override
    public void verifyClient(String appId, String appSecret) throws Exception {

    }


}
