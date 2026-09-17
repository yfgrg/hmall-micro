package com.hmall.api.pay;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "pay-service", path = "/pay-orders")
public interface PayClient {

    @GetMapping("/status/{orderId}")
    Integer getStatus(@PathVariable("orderId") Long orderId);

    @PutMapping("/{orderId}/close")
    void closePayOrder(@PathVariable("orderId") Long orderId);
}
