package com.github.itdachen.dashboard.config;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * Description:
 * Created by 王大宸 on 2023-07-16 1:35
 * Created with IntelliJ IDEA.
 */
@Configuration
public class SecuritySecureConfig {

    /**
     * 应用上下文路径
     */
    private final String adminContextPath;

    public SecuritySecureConfig(AdminServerProperties adminServerProperties) {
        this.adminContextPath = adminServerProperties.getContextPath();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        SavedRequestAwareAuthenticationSuccessHandler
                successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl(adminContextPath + "/");

        http.authorizeHttpRequests(request -> request
                .requestMatchers(
                        adminContextPath + "/assets/**",
                        adminContextPath + "/login",
                        adminContextPath + "/actuator",
                        adminContextPath + "/actuator/**",
                        adminContextPath + "/instances",
                        adminContextPath + "/instances/**"
                )
                .permitAll()
                .anyRequest().authenticated());


        http.formLogin(login -> login
                .loginPage(adminContextPath + "/login")
                .successHandler(successHandler)
        );
        http.logout(logout -> logout
                .logoutUrl(adminContextPath + "/logout")
        );

        //  http.httpBasic().csrf(csrf ->csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()));

        return http.build();
    }


}
