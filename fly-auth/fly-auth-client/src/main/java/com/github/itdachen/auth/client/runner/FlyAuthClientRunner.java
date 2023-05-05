package com.github.itdachen.auth.client.runner;

import com.github.itdachen.auth.jwts.parse.key.AuthClientTokenSecretKey;
import com.github.itdachen.auth.client.feign.AuthClientSecretKeyFeign;
import com.github.itdachen.auth.jwts.core.properties.JwtsProperties;
import com.github.itdachen.framework.core.response.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Description: 同步 token 解析公钥
 * Created by 王大宸 on 2023/05/01 15:18
 * Created with IntelliJ IDEA.
 */
@Configuration
public class FlyAuthClientRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(FlyAuthClientRunner.class);

    @Autowired
    private AuthClientTokenSecretKey authClientTokenSecretKey;

    private final AuthClientSecretKeyFeign serviceAuthFeign;
    private final JwtsProperties authProperties;

    public FlyAuthClientRunner(AuthClientSecretKeyFeign serviceAuthFeign,
                               JwtsProperties authProperties) {
        this.serviceAuthFeign = serviceAuthFeign;
        this.authProperties = authProperties;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("正在初始化加载用户pubKey失败");
        try {
            refreshUserSecretKey();
        } catch (Exception e) {
            logger.error("初始化加载用户pubKey失败,1分钟后自动重试!", e);
        }
        logger.info("初始化加载用户pubKey完成");
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
        ServerResponse<String> response = serviceAuthFeign.getSecretPublicKey(authProperties.getApp().getAppId(), authProperties.getApp().getAppSecret());
        if (response.getSuccess()) {
            this.authClientTokenSecretKey.setTokenPublicKey(response.getData());
        }
    }


}
