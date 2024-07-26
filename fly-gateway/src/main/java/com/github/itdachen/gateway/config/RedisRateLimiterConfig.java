package com.github.itdachen.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 限流策略配置
 *
 * @author 王大宸
 * @date 2024-07-26 10:50
 */
@Configuration
@SuppressWarnings("all")
public class RedisRateLimiterConfig {

    /***
     * 按URL限流,即以每秒内请求数按URL分组统计，超出限流的url请求都将返回 429 状态
     *
     * 如果不使用@Primary注解，会报如下错误，需要注意
     *  Description:
     *
     * Parameter 1 of method requestRateLimiterGatewayFilterFactory in org.springframework.cloud.gateway.config.GatewayAutoConfiguration required a single bean, but 2 were found:
     * 	- apiKeyResolver: defined by method 'apiKeyResolver' in class path resource [com/home/config/RequestRateLimiterConfig.class]
     * 	- ipKeyResolver: defined by method 'ipKeyResolver' in class path resource [com/home/config/RequestRateLimiterConfig.class]
     *
     * Action:
     *
     * Consider marking one of the beans as @Primary, updating the consumer to accept multiple beans, or using @Qualifier to identify the bean that should be consumed
     *
     *
     * 配置方法 (ipKeyResolver 也是一样的, 差不多) :
     *   - name: RequestRateLimiter #请求数限流 名字不能随便写
     *     args:
     *        key-resolver: "#{@ipKeyResolver}"
     *        redis-rate-limiter.replenishRate: 1 #令牌桶每秒填充平均速率
     *        redis-rate-limiter.burstCapacity: 1 #令牌桶总容量
     *
     * @author 王大宸
     * @date 2024/7/26 10:51
     * @return org.springframework.cloud.gateway.filter.ratelimit.KeyResolver
     */
    @Bean
    @Primary
    public KeyResolver apiKeyResolver() {
        //写法1
        return exchange -> Mono.just(
                exchange.getRequest()
                        .getPath()
                        .toString()
        );

        //写法2
//        return new KeyResolver() {
//            @Override
//            public Mono<String> resolve(ServerWebExchange exchange) {
//                return Mono.just(exchange.getRequest()
//                        .getPath().value());
//            }
//        };
    }

    /***
     * 按IP来限流
     *
     * @author 王大宸
     * @date 2024/7/26 10:51
     * @return org.springframework.cloud.gateway.filter.ratelimit.KeyResolver
     */
    @Bean
    public KeyResolver ipKeyResolver() {
        return new KeyResolver() {
            @Override
            public Mono<String> resolve(ServerWebExchange exchange) {
                return Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
            }
        };
    }


    /***
     * 根据请求参数中的userId进行限流, 需要将用户id暴露给前端
     * 请求地址写法：http://127.0.0.1:8080/api/user/123?userId=1
     * @author 王大宸
     * @date 2024/7/26 11:02
     * @return org.springframework.cloud.gateway.filter.ratelimit.KeyResolver
     */
    @Bean
    public KeyResolver userKeyResolver() {
        return exchange -> Mono.just(
                exchange.getRequest()
                        .getQueryParams()
                        .getFirst("userId")
        );
    }

}
