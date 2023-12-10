package com.github.itdachen.auth.client.runner;

import com.github.itdachen.auth.interfaces.client.IAuthClientTokenSecretRpc;
import com.github.itdachen.framework.autoconfigure.cloud.jwt.properties.FlyCloudAppClientProperties;
import com.github.itdachen.framework.cloud.jwt.parse.AuthClientTokenSecretKey;
import com.github.itdachen.framework.core.response.ServerResponse;
import org.apache.dubbo.config.annotation.DubboReference;
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

    @DubboReference // 远程调用
    private IAuthClientTokenSecretRpc clientTokenSecretRpc;

    private final FlyCloudAppClientProperties appClientProperties;

    public AuthorizedClientTokenSecretRunner(FlyCloudAppClientProperties appClientProperties) {
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
        try {
            ServerResponse<String> secretPublicKey = clientTokenSecretRpc.getSecretPublicKey(
                    appClientProperties.getAppId(),
                    appClientProperties.getAppSecret()
            );
            this.authClientTokenSecretKey.setTokenPublicKey(secretPublicKey.getData());
        } catch (Exception e) {
            logger.error("初始化 public key 失败, 1 分钟后自动重试!", e);
        }
    }

}
