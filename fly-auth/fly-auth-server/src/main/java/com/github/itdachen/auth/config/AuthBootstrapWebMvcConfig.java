package com.github.itdachen.auth.config;

import com.github.itdachen.framework.cloud.jwt.parse.IVerifyTicketTokenHelper;
import com.github.itdachen.framework.cloud.jwt.parse.interceptor.UserAuthRestInterceptor;
import com.github.itdachen.framework.cloud.jwt.parse.matchers.IRequestPassMatchers;
import com.github.itdachen.framework.cloud.jwt.parse.resolver.UserAuthRestMethodArgumentResolver;
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
    private final IRequestPassMatchers requestPassMatchers;

    public AuthBootstrapWebMvcConfig(IVerifyTicketTokenHelper verifyTicketTokenService,
                                     IRequestPassMatchers requestPassMatchers) {
        this.verifyTicketTokenService = verifyTicketTokenService;
        this.requestPassMatchers = requestPassMatchers;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
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

    @Bean
    public UserAuthRestInterceptor authInterceptor() {
        return new UserAuthRestInterceptor(verifyTicketTokenService);
    }

}
