package com.ali.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@RefreshScope
public class OrderController {

    /**
     * 服务端口
     */
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("{id}")
    public ResponseEntity<String> getOrder(@PathVariable("id") Long id) {
        return ResponseEntity.ok("订单号 = " + id + "，查询成功，server.port = " + serverPort);
    }

    @Value("${order.info}")
    private String orderInfo;

    @GetMapping("info")
    public String getOrderInfo() {
        return this.orderInfo;
    }

}
