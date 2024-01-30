package com.sjain.microservices.rewardsservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class Transaction {
    @Id
    private Long id;
    private String merchantName;
    private BigDecimal amount;
    private Long partyId;
    private String environment;

    public Transaction() {
    }

    public Transaction(Long id, String merchantName, BigDecimal amount, Long partyId) {
        this.id = id;
        this.merchantName = merchantName;
        this.amount = amount;
        this.partyId = partyId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }
}
