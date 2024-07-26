package com.github.itdachen.auth;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Description:
 * Created by 王大宸 on 2023/04/30 13:33
 * Created with IntelliJ IDEA.
 */
@SpringBootTest
public class AuthBootstrapTests {
    private static final Logger logger = LoggerFactory.getLogger(AuthBootstrapTests.class);


    @Test
    public void context() {
        System.err.println("context");
    }


}
