package com.github.itdachen.auth.config;

import com.github.itdachen.auth.jwts.core.properties.JwtsProperties;
import com.github.itdachen.auth.jwts.parse.IVerifyTicketTokenHelper;
import com.github.itdachen.auth.jwts.parse.interceptor.UserAuthRestInterceptor;
import com.github.itdachen.auth.jwts.parse.resolver.UserAuthRestMethodArgumentResolver;
import com.github.itdachen.framework.body.advice.handler.GlobalExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Description:
 * Created by 王大宸 on 2023/04/30 16:04
 * Created with IntelliJ IDEA.
 */
@Configuration
public class AuthBootstrapWebMvcConfig implements WebMvcConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(AuthBootstrapWebMvcConfig.class);
    private final IVerifyTicketTokenHelper verifyTicketTokenService;
    private final JwtsProperties jwtsProperties;

    public AuthBootstrapWebMvcConfig(IVerifyTicketTokenHelper verifyTicketTokenService,
                                     JwtsProperties jwtsProperties) {
        this.verifyTicketTokenService = verifyTicketTokenService;
        this.jwtsProperties = jwtsProperties;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
    }

    @Bean
    public GlobalExceptionHandler getGlobalExceptionHandler() {
        logger.info("正在配置全局异常处理 ...");
        return new GlobalExceptionHandler();
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
                .excludePathPatterns(passMatchers());
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userAuthRestMethodArgumentResolver());
    }

    @Bean
    public UserAuthRestMethodArgumentResolver userAuthRestMethodArgumentResolver() {
        return new UserAuthRestMethodArgumentResolver();
    }

    @Bean
    public UserAuthRestInterceptor authInterceptor() {
        return new UserAuthRestInterceptor(verifyTicketTokenService, jwtsProperties);
    }


    /***
     * 指定项目账号加密策略
     *
     * @author 王大宸
     * @date 2022/10/16 13:55
     * @return org.springframework.security.crypto.password.PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /***
     * 自定义不拦截路径
     *
     * @author 王大宸
     * @date 2023/4/15 16:59
     * @return java.lang.String[]
     */
    private String[] passMatchers() {
        return new String[]{
                "/oauth/jwt/token",
                "/oauth/jwt/**"
        };
    }

}
