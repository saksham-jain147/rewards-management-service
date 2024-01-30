package com.sjain.microservices.rewardscalculatorservice;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RewardsCalculatorServiceRepository extends JpaRepository<Config, String> {
    Config findByMerchantName(String merchantName);
}
