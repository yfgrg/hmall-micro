package com.hmall.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author Mr.M
 * @version 1.0
 * @description 第一个网关过滤器
 * @date 2024/8/7 14:41
 */
@Component
@Slf4j
public class FirstFilterGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {

    @Override
    public GatewayFilter apply(Object config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                ServerHttpRequest request = exchange.getRequest();

                log.info("请求路径：{}",request.getPath());
                log.info("网关过滤器FirstFilterGatewayFilterFactory执行啦...");
                //放行
                return chain.filter(exchange);
                //拦截 返回401状态码
                //exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                //return exchange.getResponse().setComplete();
            }
        };
    }
    

}