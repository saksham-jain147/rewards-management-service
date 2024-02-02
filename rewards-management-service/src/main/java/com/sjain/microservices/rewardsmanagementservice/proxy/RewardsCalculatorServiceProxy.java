package com.sjain.microservices.rewardsmanagementservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

//@FeignClient(name="rewards-calculator-service", url="localhost:8200")
@FeignClient(name="rewards-calculator-service") // If we don't specify url, Feign client will automatically pick-up available instances from eureka
public interface RewardsCalculatorServiceProxy {
    @GetMapping("/rewards-calculator-service/merchant_name/{merchantName}/amount/{amount}")
    public Long calculateRewards(
            @PathVariable String merchantName,
            @PathVariable BigDecimal amount
    );
}
