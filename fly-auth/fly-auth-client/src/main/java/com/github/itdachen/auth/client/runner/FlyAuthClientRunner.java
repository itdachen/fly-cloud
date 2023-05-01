package com.github.itdachen.auth.client.runner;

import com.github.itdachen.auth.client.config.AuthClientTokenSecretKey;
import com.github.itdachen.auth.client.feign.AuthClientSecretKeyFeign;
import com.github.itdachen.auth.client.properties.FlyAuthProperties;
import com.github.itdachen.framework.core.response.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Description:
 * Created by 王大宸 on 2023/05/01 15:18
 * Created with IntelliJ IDEA.
 */
@Configuration
@EnableConfigurationProperties(FlyAuthProperties.class)
public class FlyAuthClientRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(FlyAuthClientRunner.class);

    @Autowired
    private AuthClientTokenSecretKey userAuthConfig;

    private final AuthClientSecretKeyFeign serviceAuthFeign;
    private final FlyAuthProperties authProperties;

    public FlyAuthClientRunner(AuthClientSecretKeyFeign serviceAuthFeign,
                               FlyAuthProperties authProperties) {
        this.serviceAuthFeign = serviceAuthFeign;
        this.authProperties = authProperties;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("初始化加载用户 secret key");
        try {
            refreshUserSecretKey();
        } catch (Exception e) {
            logger.error("初始化加载用户pubKey失败,1分钟后自动重试!", e);
        }
        logger.info("初始化用户 secret key 完成");
    }

    @Scheduled(cron = "0 0/1 * * * ?")
    public void refreshUserSecretKey() throws Exception {
        ServerResponse<String> response = serviceAuthFeign.getSecretPublicKey(authProperties.getClient().getId(), authProperties.getClient().getSecretKey());
        if (response.getSuccess()) {
            this.userAuthConfig.setTokenPublicKey(response.getData());
        }
    }


}
