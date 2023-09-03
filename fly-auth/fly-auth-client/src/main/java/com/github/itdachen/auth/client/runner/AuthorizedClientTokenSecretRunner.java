package com.github.itdachen.auth.client.runner;

import com.github.itdachen.auth.client.feign.IAuthorizedClientTokenSecretFeign;
import com.github.itdachen.framework.autoconfigure.cloud.jwt.properties.FlyCloudAppClientProperties;
import com.github.itdachen.framework.cloud.jwt.parse.key.AuthClientTokenSecretKey;
import com.github.itdachen.framework.core.response.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Description: 同步 token 解析公钥
 * Created by 王大宸 on 2023/05/01 15:18
 * Created with IntelliJ IDEA.
 */
public class AuthorizedClientTokenSecretRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(AuthorizedClientTokenSecretRunner.class);

    @Autowired
    private AuthClientTokenSecretKey authClientTokenSecretKey;

    private final IAuthorizedClientTokenSecretFeign clientTokenSecretFeign;
    private final FlyCloudAppClientProperties appClientProperties;

    public AuthorizedClientTokenSecretRunner(IAuthorizedClientTokenSecretFeign clientTokenSecretFeign,
                                             FlyCloudAppClientProperties appClientProperties) {
        this.clientTokenSecretFeign = clientTokenSecretFeign;
        this.appClientProperties = appClientProperties;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("正在初始化用户 pubKey");
        try {
            refreshUserSecretKey();
            logger.info("初始化用户 pubKey 完成");
        } catch (Exception e) {
            logger.error("初始化用户 pubKey 失败, 1 分钟后自动重试!", e);
        }
    }

    /***
     * 每隔一分钟同步一次
     *
     * @author 王大宸
     * @date 2023/5/5 15:09
     * @return void
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void refreshUserSecretKey() throws Exception {
        ServerResponse<String> res = clientTokenSecretFeign.getSecretPublicKey(
                appClientProperties.getAppId(),
                appClientProperties.getAppSecret()
        );
        if (res.getSuccess()) {
            this.authClientTokenSecretKey.setTokenPublicKey(res.getData());
        }
    }

}
