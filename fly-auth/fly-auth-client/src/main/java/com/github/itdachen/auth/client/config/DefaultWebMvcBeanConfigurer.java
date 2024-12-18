package com.github.itdachen.auth.client.config;

import com.github.itdachen.cloud.jwt.IVerifyTicketTokenHelper;
import com.github.itdachen.cloud.jwt.parse.matchers.IRequestPassMatchers;
import com.github.itdachen.cloud.jwt.parse.verified.IVerifiedTicketUrlService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Description: 默认拦截器配置
 * Created by 王大宸 on 2023-09-03 17:51
 * Created with IntelliJ IDEA.
 */
@Configuration
public class DefaultWebMvcBeanConfigurer {

    /**
     * 解析 token 接口
     */
    private final IVerifyTicketTokenHelper verifyTicketTokenService;

    /**
     * 获取不拦截的路径
     */
    private final IRequestPassMatchers requestPassMatchers;

    /**
     * 内部权限拦截
     */
    private final IVerifiedTicketUrlService verifiedTicketUrlService;

    public DefaultWebMvcBeanConfigurer(IVerifyTicketTokenHelper verifyTicketTokenService,
                                       IRequestPassMatchers requestPassMatchers,
                                       IVerifiedTicketUrlService verifiedTicketUrlService) {
        this.verifyTicketTokenService = verifyTicketTokenService;
        this.requestPassMatchers = requestPassMatchers;
        this.verifiedTicketUrlService = verifiedTicketUrlService;
    }

    /***
     * 配置默认的 WebMvcConfigurer 配置
     * 如果有需要, 可以重新写一个类, 实现 WebMvcConfigurer 中的接口即可
     * @author 王大宸
     * @date 2023/9/3 17:56
     * @return org.springframework.web.servlet.config.annotation.WebMvcConfigurer
     */
    @Bean
    @ConditionalOnMissingBean(WebMvcConfigurer.class)
    public WebMvcConfigurer defaultBootstrapWebMvcConfigurer() {
        return new DefaultBootstrapWebMvcConfigurer(verifyTicketTokenService, requestPassMatchers, verifiedTicketUrlService);
    }

}
