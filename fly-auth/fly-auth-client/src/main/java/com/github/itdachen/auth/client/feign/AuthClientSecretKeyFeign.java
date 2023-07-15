package com.github.itdachen.auth.client.feign;

import com.github.itdachen.framework.core.response.ServerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Description: 获取 token 解密秘钥
 * Created by 王大宸 on 2023/05/01 15:07
 * Created with IntelliJ IDEA.
 */
@FeignClient(value = "${fly.cloud.auth.app.service-id}", configuration = {})
public interface AuthClientSecretKeyFeign {

    @RequestMapping(value = "/auth/client/user/secret/key", method = RequestMethod.GET)
    ServerResponse<String> getSecretPublicKey(@RequestParam("appId") String appId,
                                              @RequestParam("appSecret") String appSecret) throws Exception;


}
