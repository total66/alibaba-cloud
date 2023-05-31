package com.ebanma.cloud.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testQPS")
    public String testQps() {
        log.info(Thread.currentThread().getName()+"\t"+"【testQPS】");
        return "流控模式QPS：直连";
    }

    @GetMapping("/testThreads")
    public String testThreads() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        log.info(Thread.currentThread().getName()+"\t"+"【testThreads】");
        return "流控模式Threads：直连";
    }

    @GetMapping("/testQPS-A")
    public String testQpsA() {
        log.info(Thread.currentThread().getName()+"\t"+"【testQpsA】");
        return "流控模式QPS：关联";
    }

    @GetMapping("/testQPS-B")
    public String testQpsB() {
        log.info(Thread.currentThread().getName()+"\t"+"...【testQpsB】");
        return "流控模式QPS：关联";
    }

    @GetMapping("/testQPS-WarmUp")
    public String testQpsWarmUp() {
        log.info(Thread.currentThread().getName()+"\t"+"...【testQpsWarmUp】");
        return "流控模式QPS：预热";
    }

    @GetMapping("/testQPS-Wait")
    public String testQpsWait() {
        log.info(Thread.currentThread().getName()+"\t"+"...【testQpsWait】");
        return "流控模式QPS：排队等待";
    }
    
}