package com.github.itdachen.crypto.encoder;

import org.apache.commons.codec.binary.Base64;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * Description: Sha256 加密
 * Created by 王大宸 on 2023/04/30 14:32
 * Created with IntelliJ IDEA.
 */
public class Sha256PasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(charSequence.toString().getBytes(StandardCharsets.UTF_8));
            return new String(Base64.encodeBase64(digest));
        } catch (Exception e) {
            return null;
        }
    }
    
    /***
     * 校验是否一致
     *
     * @author 王大宸
     * @date 2023/4/30 14:36
     * @param charSequence  未加密字符串
     * @param s             已加密字符串
     * @return boolean
     */
    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return this.encode(charSequence.toString()).equals(s);
    }

}
