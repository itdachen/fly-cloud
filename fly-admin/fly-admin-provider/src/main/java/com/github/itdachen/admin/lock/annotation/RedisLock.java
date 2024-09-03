package com.github.itdachen.admin.lock.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 分布式锁
 *
 * @author 王大宸
 * @date 2024-09-03 10:05
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisLock {
    /**
     * 锁的资源, Key.支持 spring EL 表达式
     * 例如: key = "'itdachen' + ':' + #params.id"
     */
    String key() default "fly:itdachen:redis_lock";

    /**
     * 锁类型
     */
    String lockType() default "reentrant_lock";

    /**
     * 获取锁等等时间, 默认 3 秒
     */
    long waitTime() default 3000L;

    /**
     * 锁自定释放时间, 默认 30 秒
     */
    long leaseTime() default 30000L;

    /**
     * 时间单位, 默认毫秒 (获取锁等待时间和持锁回见都用此单位)
     */
    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;

}
