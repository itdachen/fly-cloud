package com.github.itdachen.admin.lock;

import com.github.itdachen.admin.lock.annotation.RedisLock;
import com.github.itdachen.boot.autoconfigure.AppHelper;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Optional;

/**
 * RedisLockAspect
 *
 * @author 王大宸
 * @date 2024-09-03 10:14
 */
@Aspect
@Component
//@ConditionalOnBean(RedissonClient.class)
//@AutoConfigureAfter(RedissonAutoConfiguration.class)
public class RedisLockAspect {
    private static final Logger logger = LoggerFactory.getLogger(RedisLockAspect.class);

    /**
     * redisson 客户端
     */
    @Autowired
    private RedissonClient redissonClient;


    @Around("@annotation(com.github.itdachen.admin.lock.annotation.RedisLock)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 通过jointPoint 获取注解对应的Method
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        RedisLock redisLock = method.getAnnotation(RedisLock.class);
        String key = redisLock.key();
        Object[] args = joinPoint.getArgs();
        key = parseSpEl(key, method, args);
        String appId = AppHelper.app().properties().getAppId();
        if (StringUtils.isNotEmpty(appId)) {
            key = appId + ":" + parseSpEl(key, method, args);
        }
        RLock lock = redissonClient.getLock(key);
        lock.lock(redisLock.leaseTime(), redisLock.timeUnit());
        //   boolean success = lock.tryLock(redisLock.leaseTime(), redisLock.timeUnit());
        try {
            return joinPoint.proceed();
        } catch (Exception e) {
            throw e;
        } finally {
            // 释放分布式锁, 看门狗机制停止续期
            lock.unlock();
        }
    }


    private static final ExpressionParser PARSE = new SpelExpressionParser();
    private static final DefaultParameterNameDiscoverer PARAMETER_NAME_DISCOVERERA = new DefaultParameterNameDiscoverer();

    public static String parseSpEl(String key, Method method, Object[] args) {
        String[] params = Optional.ofNullable(PARAMETER_NAME_DISCOVERERA.getParameterNames(method)).orElse(new String[]{});//解析参数名
        EvaluationContext context = new StandardEvaluationContext();//el解析需要的上下文对象
        for (int i = 0; i < params.length; i++) {
            context.setVariable(params[i], args[i]);//所有参数都作为原材料扔进去
        }
        // 解析springEl表达式
        Expression expression = PARSE.parseExpression(key);
        return expression.getValue(context, String.class);
    }


}
