package com.hmall.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class PrintAnyGlobalFilter  implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 编写过滤器逻辑
        log.info("打印全局过滤器");
        // 放行
        return chain.filter(exchange);

        // 拦截
//        ServerHttpResponse response = exchange.getResponse();
//        response.setRawStatusCode(401);
//        return response.setComplete();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
