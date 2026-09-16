package com.hmall.cart.controller.inner;


import com.hmall.cart.service.ICartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@Api(tags = "购物车内部接口")
@RestController
@RequestMapping("/inner/carts")
@RequiredArgsConstructor
@Slf4j
public class CartControllerInner {
    private final ICartService cartService;

    @ApiOperation("批量删除购物车中商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id"),
            @ApiImplicitParam(name = "ids", value = "购物车条目id集合")
    })
    @DeleteMapping
    public void deleteCartItemByIds(@RequestParam("userId") Long userId,@RequestParam("ids") Collection<Long> ids){
        cartService.removeByItemIds(userId,ids);
    }
}