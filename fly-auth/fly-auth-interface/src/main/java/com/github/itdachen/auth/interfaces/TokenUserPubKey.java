package com.github.itdachen.auth.interfaces;

import java.io.Serializable;

/**
 * TokenUserPubKey
 *
 * @author 王大宸
 * @date 2023-12-28 20:53
 */
public class TokenUserPubKey implements Serializable {
    private static final long serialVersionUID = 2599565458276273529L;

    /**
     * 公钥 base64
     */
    private String userPubKey;

    /**
     * 算法
     */
    private String algorithm;

    public TokenUserPubKey(String userPubKey, String algorithm) {
        this.userPubKey = userPubKey;
        this.algorithm = algorithm;
    }

    public String getUserPubKey() {
        return userPubKey;
    }

    public void setUserPubKey(String userPubKey) {
        this.userPubKey = userPubKey;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }
}
