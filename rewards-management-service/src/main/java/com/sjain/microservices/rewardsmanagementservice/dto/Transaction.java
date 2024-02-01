package com.sjain.microservices.rewardsmanagementservice.dto;

import java.math.BigDecimal;

public class Transaction {
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

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", merchantName='" + merchantName + '\'' +
                ", amount=" + amount +
                ", partyId=" + partyId +
                ", environment='" + environment + '\'' +
                '}';
    }
}
