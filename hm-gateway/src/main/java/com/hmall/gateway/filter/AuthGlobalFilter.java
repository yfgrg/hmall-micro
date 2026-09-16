package com.hmall.gateway.filter;

import cn.hutool.core.util.StrUtil;
import com.hmall.gateway.config.AuthProperties;
import com.hmall.gateway.utils.JwtTool;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 身份校验过滤器
 */
@RequiredArgsConstructor
@EnableConfigurationProperties(AuthProperties.class)
@Component
public class AuthGlobalFilter implements GlobalFilter {

    private final AuthProperties authProperties;

    private final JwtTool jwtTool;

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //1. 获取请求实例和响应实例
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        //2. 获取请求路径
        String path = request.getURI().getPath();

        //3. 判断是否可放行(判断当前请求路径是否是白名单,使用antPathMatcher进行匹配)
        long count = authProperties.getExcludePaths().stream().filter(x -> antPathMatcher.match(x, path)).count();
        if(count>0) {
            return chain.filter(exchange);
        }

        //4. 获取请求头中的token
        String token = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        //5. 如果Token为空则响应401
        if(StrUtil.isBlank(token)) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        //6. 解析Token
        try {
            Long userId = jwtTool.parseToken(token);
            //7. 放行前设置用户id
            request.mutate().header("user-info",userId.toString());
            //8. 解析Token成功则放行
            return chain.filter(exchange);
        } catch (Exception e) {
            e.printStackTrace();
            //8. 如果Token解析失败则响应401
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

    }
}