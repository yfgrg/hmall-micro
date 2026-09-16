//package com.hmall.trade.config;
//
//import com.hmall.common.utils.UserContext;
//import feign.RequestInterceptor;
//import feign.RequestTemplate;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * Feign调用拦截器配置类
// *
// * @Author mr.wu
// * @Date 2025-6-16 22:09
// */
//@Configuration
//public class FeignInterceptorConfig {
//
//    /**
//     * Feign调用拦截器类
//     * @return
//     */
//    @Bean
//    public RequestInterceptor userInfoRequestInterceptor(){
//        return new RequestInterceptor() {
//            @Override
//            public void apply(RequestTemplate requestTemplate) {
//                //1. 获取ThreadLocal中的userId
//                Long userId = UserContext.getUser();
//
//                //2. 如果非空则设置到请求头，传给被调用的微服务
//                if(userId!=null) {
//                    requestTemplate.header("user-info",userId.toString());
//                }
//            }
//        };
//    }
//}