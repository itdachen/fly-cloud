package com.github.itdachen.auth.client.runner;

import com.github.itdachen.auth.interfaces.TokenUserPubKey;
import com.github.itdachen.auth.interfaces.client.IAuthClientTokenSecretRpc;
import com.github.itdachen.boot.autoconfigure.cloud.jwt.properties.CloudAppClientProperties;
import com.github.itdachen.cloud.jwt.IVerifyTicketPublicKeyHelper;
import com.github.itdachen.cloud.jwt.parse.TokenPublicKey;
import com.github.itdachen.framework.core.response.ServerResponse;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;

import java.security.PublicKey;

/**
 * Description: 同步 token 解析公钥
 * Created by 王大宸 on 2023/05/01 15:18
 * Created with IntelliJ IDEA.
 */
public class AuthorizedClientTokenSecretRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(AuthorizedClientTokenSecretRunner.class);

    @Autowired
    private TokenPublicKey tokenPublicKey;
    @Autowired
    private IVerifyTicketPublicKeyHelper verifyTicketPublicKeyHelper;

    @DubboReference // 远程调用
    private IAuthClientTokenSecretRpc clientTokenSecretRpc;

    private final CloudAppClientProperties appClientProperties;

    public AuthorizedClientTokenSecretRunner(CloudAppClientProperties appClientProperties) {
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
        ServerResponse<TokenUserPubKey> secretPublicKey = clientTokenSecretRpc.getSecretPublicKey(
                appClientProperties.getAppId(),
                appClientProperties.getAppSecret()
        );

        TokenUserPubKey pubKey = secretPublicKey.getData();

        PublicKey publicKey = verifyTicketPublicKeyHelper.publicKey(pubKey.getUserPubKey(), pubKey.getAlgorithm());

        this.tokenPublicKey.setPublicKey(publicKey);
    }

}
