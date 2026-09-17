package com.hmall.common.constants;

/**
 * @author Mr.M
 * @version 1.0
 * @description MQ常量配置
 * @date 2024/8/30 11:45
 */
public interface MqConstants {
    String PAY_EXCHANGE_NAME = "pay.direct";
    String PAY_SUCCESS_KEY = "pay.success";
    String PAY_SUCCESS_QUEUE = "pay.success.queue";
    String DELAY_EXCHANGE_NAME = "trade.delay.direct";
    String DELAY_ORDER_QUEUE_NAME = "trade.delay.order.queue";
    String DELAY_ORDER_KEY = "delay.order.query";
}