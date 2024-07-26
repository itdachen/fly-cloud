package com.github.itdachen.gateway.config;

import feign.codec.Decoder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

/**
 * GatewayConfig
 *
 * @author 王大宸
 * @date 2024-07-26 10:45
 */
@Configuration
public class GatewayLoadbalancerConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    @LoadBalanced //  坑, 集群支持，负载均衡，必须要有 @LoadBalanced，不然请求会失败
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }

//    @Bean
//    public Decoder feignDecoder() {
//        return new ResponseEntityDecoder(new SpringDecoder(feignHttpMessageConverter()));
//    }
//
//    public ObjectFactory<HttpMessageConverters> feignHttpMessageConverter() {
//        final HttpMessageConverters httpMessageConverters = new HttpMessageConverters(new GateWayMappingJackson2HttpMessageConverter());
//        return new ObjectFactory<HttpMessageConverters>() {
//            @Override
//            public HttpMessageConverters getObject() throws BeansException {
//                return httpMessageConverters;
//            }
//        };
//    }
//
//    public static class GateWayMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
//        GateWayMappingJackson2HttpMessageConverter() {
//            List<MediaType> mediaTypes = new ArrayList<>();
//            mediaTypes.add(MediaType.valueOf(MediaType.TEXT_HTML_VALUE + ";charset=UTF-8"));
//            setSupportedMediaTypes(mediaTypes);
//        }
//    }

}
