package com.github.itdachen.auth.client.config;

import com.github.itdachen.cloud.jwt.IVerifyTicketTokenHelper;
import com.github.itdachen.cloud.jwt.parse.interceptor.UserAuthRestInterceptor;
import com.github.itdachen.cloud.jwt.parse.matchers.IRequestPassMatchers;
import com.github.itdachen.cloud.jwt.parse.resolver.UserAuthRestMethodArgumentResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Description: 默认拦截器配置
 * Created by 王大宸 on 2023/05/05 22:11
 * Created with IntelliJ IDEA.
 */
public class DefaultBootstrapWebMvcConfigurer implements WebMvcConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(DefaultBootstrapWebMvcConfigurer.class);
    private final IVerifyTicketTokenHelper verifyTicketTokenService;
    private final IRequestPassMatchers requestPassMatchers;

    public DefaultBootstrapWebMvcConfigurer(IVerifyTicketTokenHelper verifyTicketTokenService,
                                            IRequestPassMatchers requestPassMatchers) {
        this.verifyTicketTokenService = verifyTicketTokenService;
        this.requestPassMatchers = requestPassMatchers;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    /***
     * 拦截器配置
     *
     * @author 王大宸
     * @date 2022/9/25 16:28
     * @param registry registry
     * @return void
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(requestPassMatchers.passMatchers());
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userAuthRestMethodArgumentResolver());
    }

    @Bean
    public UserAuthRestMethodArgumentResolver userAuthRestMethodArgumentResolver() {
        return new UserAuthRestMethodArgumentResolver();
    }

    /***
     * token 解析拦截器
     *
     * @author 王大宸
     * @date 2023/9/3 17:30
     * @return com.github.itdachen.framework.cloud.jwt.parse.interceptor.UserAuthRestInterceptor
     */
    @Bean
    public UserAuthRestInterceptor authInterceptor() {
        return new UserAuthRestInterceptor(verifyTicketTokenService);
    }

}
