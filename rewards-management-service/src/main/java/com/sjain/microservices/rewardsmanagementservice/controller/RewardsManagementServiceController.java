package com.sjain.microservices.rewardsmanagementservice.controller;

import com.sjain.microservices.rewardsmanagementservice.service.RewardsManagementService;
import com.sjain.microservices.rewardsmanagementservice.dto.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RewardsManagementServiceController {
    @Autowired
    private RewardsManagementService rewardsManagementService;
    @GetMapping("/rewards-management-service/party_id/{partyId}")
    public ResponseEntity<String> calculateRewards(@PathVariable Long partyId){

        List<Transaction> transactions = rewardsManagementService.fetchTransactions(partyId);
        try {
            Long totalRewards = 0L;
            for (Transaction transaction : transactions) {
                Long pointsForEachTransaction = rewardsManagementService.getPointsForEachTransaction(transaction.getMerchantName(), transaction.getAmount());
                if (pointsForEachTransaction != -1L) {
                    totalRewards += pointsForEachTransaction;
                } else {
                    throw new RuntimeException("Rewards-Calculator-Service refused to connect");
                }
            }
            System.out.println(partyId + "--RestTemplate-->" + totalRewards);
            return rewardsManagementService.saveRewards(partyId, totalRewards);
        } catch(Exception e){
            String errorMessage = "Failure: " + e.getMessage();
            return ResponseEntity.status(500).body(errorMessage); // Internal Server Error
        }
    }

    @GetMapping("/rewards-management-service-feign/party_id/{partyId}")
    public ResponseEntity<String> calculateRewardsFeign(@PathVariable Long partyId){

        try {
            List<Transaction> transactions = rewardsManagementService.fetchTransactionsFeign(partyId);
            Long totalRewards = 0L;
            for (Transaction transaction : transactions) {
                Long pointsForEachTransaction = rewardsManagementService.getPointsForEachTransactionFeign(transaction.getMerchantName(), transaction.getAmount());
                if (pointsForEachTransaction != -1L) {
                    totalRewards += pointsForEachTransaction;
                } else {
                    throw new RuntimeException("Rewards-Calculator-Service refused to connect");
                }
            }
            System.out.println(partyId + "--Feign-->" + totalRewards);
            return rewardsManagementService.saveRewardsFeign(partyId, totalRewards);
        } catch(Exception e){
            String errorMessage = "Failure: " + e.getMessage();
            return ResponseEntity.status(500).body(errorMessage); // Internal Server Error
        }
    }
}
