package com.hmall.trade.listener;

import com.hmall.api.pay.PayClient;
import com.hmall.common.constants.MqConstants;
import com.hmall.trade.domain.po.Order;
import com.hmall.trade.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderDelayMessageListener {

    private final IOrderService orderService;
    private final PayClient payClient;

    @RabbitListener(bindings = @QueueBinding(
        value = @Queue(name = MqConstants.DELAY_ORDER_QUEUE_NAME),
        exchange = @Exchange(name = MqConstants.DELAY_EXCHANGE_NAME, delayed = "true"),
        key = MqConstants.DELAY_ORDER_KEY
    ))
    public void listenOrderDelayMessage(Long orderId) {
        // 1.查询订单
        Order order = orderService.getById(orderId);
        // 2.订单不存在或者状态已经改变，无需处理
        if (order == null || !Integer.valueOf(1).equals(order.getStatus())) {
            return;
        }
        // 3.查询支付流水状态
        Integer status = payClient.getStatus(orderId);
        // 4.已支付，标记订单状态为已支付
        if (Integer.valueOf(3).equals(status)) {
            orderService.markOrderPaySuccess(orderId);
            return;
        }
        // 5.未支付，关闭订单并恢复库存
        orderService.cancelOrder(orderId);
    }
}
