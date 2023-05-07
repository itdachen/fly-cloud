package com.github.itdachen.auth.jwts.parse.matchers.impl;

import com.github.itdachen.auth.jwts.core.properties.JwtsProperties;
import com.github.itdachen.auth.jwts.parse.matchers.IRequestPassMatchers;

import java.util.List;

/**
 * Description:
 * Created by 王大宸 on 2023/05/05 22:14
 * Created with IntelliJ IDEA.
 */
public class DefaultRequestPassMatchers implements IRequestPassMatchers {

    private final JwtsProperties jwtsProperties;

    public DefaultRequestPassMatchers(JwtsProperties jwtsProperties) {
        this.jwtsProperties = jwtsProperties;
    }

    @Override
    public String[] passMatchers() {
        List<String> matchers = jwtsProperties.getMatchers();
        if (null == matchers) {
            return new String[0];
        }
        return matchers.toArray(new String[0]);
    }

}
