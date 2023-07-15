package com.github.itdachen.auth.client;

import com.github.itdachen.framework.cloud.jwt.parse.key.FlyAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Description: 启动认证客户端
 * Created by 王大宸 on 2023/05/01 13:55
 * Created with IntelliJ IDEA.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(FlyAutoConfiguration.class)
@Documented
@Inherited
public @interface EnableFlyAuthClient {
}
