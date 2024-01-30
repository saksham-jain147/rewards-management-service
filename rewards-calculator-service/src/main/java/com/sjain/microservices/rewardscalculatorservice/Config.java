package com.sjain.microservices.rewardscalculatorservice;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class Config {
    @Id
    private String merchantName;
    private BigDecimal multiplier;

    public Config() {
    }

    public Config(String merchantName, BigDecimal multiplier) {
        this.merchantName = merchantName;
        this.multiplier = multiplier;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public BigDecimal getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(BigDecimal multiplier) {
        this.multiplier = multiplier;
    }
}
