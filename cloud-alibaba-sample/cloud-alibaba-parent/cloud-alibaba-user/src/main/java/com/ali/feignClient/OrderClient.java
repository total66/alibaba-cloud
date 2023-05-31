package com.ali.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "order-service", fallback = OrderClient.OrderFallbackService.class)
public interface OrderClient {

    @GetMapping("/api/order/{id}")
    public ResponseEntity<String> getOrder(@PathVariable("id") Long id);

    /**
     * Java允许在接口内部编写内部类?
     * interface从头到尾只做一件事：去实例化。也就是interface内部可以有成员，但只能是static或abstract的成员。
     * 更准确的说就是不依赖对象实例而存在的成员，这会使得interface的内聚性更强。
     */
    @Component
    static class OrderFallbackService implements OrderClient {

        @Override
        public ResponseEntity<String> getOrder(Long id) {
            return new ResponseEntity<String>("feign调用，异常降级方法", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}