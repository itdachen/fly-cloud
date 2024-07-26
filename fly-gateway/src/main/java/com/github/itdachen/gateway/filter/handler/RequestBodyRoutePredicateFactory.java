package com.github.itdachen.gateway.filter.handler;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.gateway.handler.AsyncPredicate;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Description:
 * Created by 王大宸 on 2023-09-03 23:17
 * Created with IntelliJ IDEA.
 */
@Component
@Order(1)
public class RequestBodyRoutePredicateFactory extends AbstractRoutePredicateFactory<RequestBodyRoutePredicateFactory.Config> {
    protected static final Log LOGGER = LogFactory.getLog(RequestBodyRoutePredicateFactory.class);
    private final List<HttpMessageReader<?>> messageReaders;

    public RequestBodyRoutePredicateFactory() {
        super(RequestBodyRoutePredicateFactory.Config.class);
        this.messageReaders = HandlerStrategies.withDefaults().messageReaders();
    }

    public RequestBodyRoutePredicateFactory(List<HttpMessageReader<?>> messageReaders) {
        super(RequestBodyRoutePredicateFactory.Config.class);
        this.messageReaders = messageReaders;
    }

    public static final String REQUEST_BODY_ATTR = "requestBodyAttr";


    @Override
    public AsyncPredicate<ServerWebExchange> applyAsync(Config config) {
        return exchange -> {
            HttpMethod method = exchange.getRequest().getMethod();
            if (!HttpMethod.POST.equals(method)
                    && !HttpMethod.PUT.equals(method)) {
                return Mono.just(true);
            }
            Object cachedBody = exchange.getAttribute(REQUEST_BODY_ATTR);
            if (cachedBody != null) {
                try {
                    return Mono.just(true);
                } catch (ClassCastException e) {
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("Predicate test failed because class in predicate does not match the cached body object",
                                e);
                    }
                }
                return Mono.just(true);
            } else {
                return ServerWebExchangeUtils.cacheRequestBodyAndRequest(exchange, (serverHttpRequest) -> ServerRequest.create(exchange.mutate().request(serverHttpRequest).build(), this.messageReaders).bodyToMono(String.class).defaultIfEmpty("").doOnNext((objectValue) -> {
                    if (StringUtils.isBlank(objectValue)) {
                      //  exchange.getAttributes().put(REQUEST_BODY_ATTR, JSON.toJSONString(exchange.getRequest().getQueryParams()));
                    } else {
                        exchange.getAttributes().put(REQUEST_BODY_ATTR, objectValue);
                    }
                }).map((objectValue) -> true));

            }
        };
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        throw new UnsupportedOperationException("ReadBodyPredicateFactory is only async.");
    }

    public static class Config {
        private List<String> sources = new ArrayList<>();

        public List<String> getSources() {
            return sources;
        }

        public RequestBodyRoutePredicateFactory.Config setSources(List<String> sources) {
            this.sources = sources;
            return this;
        }

        public RequestBodyRoutePredicateFactory.Config setSources(String... sources) {
            this.sources = Arrays.asList(sources);
            return this;
        }
    }

}
