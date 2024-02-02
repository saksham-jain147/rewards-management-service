package com.sjain.microservices.rewardsmanagementservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name="rewards-calculator-service", url="localhost:8200")
public interface RewardsCalculatorServiceProxy {
    @GetMapping("/rewards-calculator-service/merchant_name/{merchantName}/amount/{amount}")
    public Long calculateRewards(
            @PathVariable String merchantName,
            @PathVariable BigDecimal amount
    );
}
