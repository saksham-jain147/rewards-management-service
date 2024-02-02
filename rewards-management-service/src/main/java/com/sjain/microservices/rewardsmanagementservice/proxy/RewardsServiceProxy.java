package com.sjain.microservices.rewardsmanagementservice.proxy;

import com.sjain.microservices.rewardsmanagementservice.dto.Transaction;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//@FeignClient(name="rewards-service", url="localhost:8000")
@FeignClient(name="rewards-service") // If we don't specify url, Feign client will automatically pick-up available instances from eureka
public interface RewardsServiceProxy {
    @GetMapping("/rewards-service/fetch-transactions/party_id/{partyId}")
    public List<Transaction> fetchTransactions(@PathVariable Long partyId);

    @GetMapping("/rewards-service/save-rewards/party_id/{partyId}/reward_points/{rewardPoints}")
    public ResponseEntity<String> saveRewards(
            @PathVariable Long partyId,
            @PathVariable Long rewardPoints);
}
