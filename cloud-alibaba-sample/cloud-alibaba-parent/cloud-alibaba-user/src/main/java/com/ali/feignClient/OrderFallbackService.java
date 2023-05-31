package com.ali.feignClient;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderFallbackService implements OrderClient {
    
    @Override
    public ResponseEntity<String> getOrder(Long id) {
        return new ResponseEntity<String>("feign调用，异常降级方法", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    
}