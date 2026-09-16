package com.hmall.api.cart;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@FeignClient("cart-service")
public interface CartClient {
    @DeleteMapping("/inner/carts")
    void deleteCartItemByIds(@RequestParam("userId") Long userId,@RequestParam("ids") Collection<Long> ids);
}