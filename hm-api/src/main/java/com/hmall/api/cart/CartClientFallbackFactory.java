//package com.hmall.api.cart;
//
//import com.hmall.api.item.ItemClient;
//import com.hmall.api.item.dto.ItemDTO;
//import com.hmall.api.item.dto.OrderDetailDTO;
//import com.hmall.common.utils.CollUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.openfeign.FallbackFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.Collection;
//import java.util.List;
//
///**
// * @author Mr.M
// * @version 1.0
// * @description itemClient的通用降级策略
// * @date 2024/8/5 9:50
// */
//@Slf4j
//@Component
//public class CartClientFallbackFactory implements FallbackFactory<CartClient> {
//    @Override
//    public CartClient create(Throwable cause) {
//        return new CartClient() {
//            @Override
//            public void deleteCartItemByIds(Collection<Long> ids){
//                log.error("远程调用CartClient#deleteCartItemByIds方法出现异常，参数：{}", ids, cause);
//                cause.printStackTrace();
//            }
//        };
//
//        }
//}
