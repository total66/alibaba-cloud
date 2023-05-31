package com.ali.feignClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/order/lb/{id}")
    public ResponseEntity<String> queryOrderLb(@PathVariable("id") Long id) {
        String result = restTemplate.getForObject("http://order-service" + "/api/order/" + id, String.class);
        return ResponseEntity.ok(result);
    }

    @Resource
    private OrderClient orderClient;

    @GetMapping("/order/feign/{id}")
    public ResponseEntity<String> queryOrderFeign(@PathVariable("id") Long id) {
        return orderClient.getOrder(id);
    }
}