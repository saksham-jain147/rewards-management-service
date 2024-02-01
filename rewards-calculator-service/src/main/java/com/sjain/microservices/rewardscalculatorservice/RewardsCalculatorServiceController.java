package com.sjain.microservices.rewardscalculatorservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class RewardsCalculatorServiceController {
    @Autowired
    private RewardsCalculatorServiceRepository repository;
    @GetMapping("/rewards-calculator-service/merchant_name/{merchantName}/amount/{amount}")
    public Long calculateRewards(
            @PathVariable String merchantName,
            @PathVariable BigDecimal amount
            ){
        Config config = repository.findByMerchantName(merchantName);
        if(config != null) {
            return (config.getMultiplier().multiply(amount)).longValue();
        } else { // Case when there are no reward multiplier configured for merchant
            return 0L;
        }
    }
}
