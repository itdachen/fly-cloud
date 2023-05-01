package com.github.itdachen.crypto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Description: 加密方式
 * Created by 王大宸 on 2023/04/30 14:30
 * Created with IntelliJ IDEA.
 */
@Configuration
public class SecurityCoreCryptoConfig {

    /**
     * 这里使用 SpringSecurity 自带的加密方式
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /***
     * 多种加密解析器
     * 数据库存放密码形式: {id}密码
     * 例如: bcrypt 加密形式: {bcrypt}$2a$10$W0iZ.jnBsq37Z968RLIvXu9H6d31lAXQG3PbCrL/dOiuLrLZ9qCIC
     *      SHA-256 加密形式: {SHA-256}Bsq37Z968RLIvXu9H6d31lAXQG3PbCrL/dOiuLrLZ9qCIC
     * @author 王大宸
     * @date 2022/9/23 9:49
     * @return org.springframework.security.crypto.password.PasswordEncoder
     */
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        // 默认编码算法的 Id
//        String idForEncode = CryptoTypeConstant.BCRYPT;
//        // 要支持的多种编码器
//        Map<String, PasswordEncoder> encoders = Map.of(
//                idForEncode, new BCryptPasswordEncoder(),
//                CryptoTypeConstant.SHA_1, new MessageDigestPasswordEncoder("SHA-1"),
//                CryptoTypeConstant.SHA_256, new Sha256PasswordEncoder()
//        );
//        return new DelegatingPasswordEncoder(idForEncode, encoders);
//    }


}
