package com.hmall.api.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service")
public interface UserClient {

    @PutMapping("/inner/users/money/deduct")
    void deductMoney(@RequestParam("userId") Long userId,@RequestParam("pw") String pw, @RequestParam("amount") Integer amount);
}
