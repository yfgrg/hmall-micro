package com.hmall.trade.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mr.M
 * @version 1.0
 * @description mq配置类
 * @date 2024/8/13 19:28
 */
@Configuration
public class MesaageConfig {

    @Bean
    public MessageConverter messageConverter() {
        // 定义消息转换器
        Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();
        //设置消息id,自动生成一个id，可用于消息幂等性处理
        jackson2JsonMessageConverter.setCreateMessageIds(true);
        return jackson2JsonMessageConverter;
    }
}