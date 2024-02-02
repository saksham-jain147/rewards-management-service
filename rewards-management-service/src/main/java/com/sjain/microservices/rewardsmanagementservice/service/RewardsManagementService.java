package com.sjain.microservices.rewardsmanagementservice.service;

import com.sjain.microservices.rewardsmanagementservice.RewardsCalculatorServiceProxy;
import com.sjain.microservices.rewardsmanagementservice.RewardsServiceProxy;
import com.sjain.microservices.rewardsmanagementservice.dto.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Service
public class RewardsManagementService {
    @Autowired
    private RewardsServiceProxy rewardsServiceProxy;
    @Autowired
    private RewardsCalculatorServiceProxy rewardsCalculatorServiceProxy;
    public List<Transaction> fetchTransactions(Long partyId) {
        String serviceUrl = "http://localhost:8000/rewards-service/fetch-transactions/party_id/" + partyId;
        try {
            // Making an HTTP GET request to rewards-service
            ResponseEntity<List<Transaction>> responseEntity = new RestTemplate().exchange(
                    serviceUrl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Transaction>>() {}
            );

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                List<Transaction> transactions = responseEntity.getBody();
//                transactions.forEach(System.out::println);
                return transactions;
            } else {
                System.err.println("Unable to fetch records for party_id. Status code: " + responseEntity.getStatusCode());
                return Collections.emptyList();
            }
        } catch (Exception e) {
            System.err.println("Rewards-Service Failure: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public Long getPointsForEachTransaction(String merchantName, BigDecimal amount){
        String serviceUrl = "http://localhost:8200/rewards-calculator-service/merchant_name/" + merchantName + "/amount/" + amount;

        try {
            // Making an HTTP GET request to rewards-calculator-service
            ResponseEntity<Long> responseEntity = new RestTemplate().getForEntity(serviceUrl, Long.class);
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                return responseEntity.getBody();
            } else {
                System.err.println("Unable to fetch records for party_id. Status code: " + responseEntity.getStatusCode());
                return 0L;
            }
        } catch (Exception e) {
            System.err.println("Rewards-Calculator-Service Failure: " + e.getMessage());
            return -1L;
        }
    }

    public ResponseEntity<String> saveRewards(Long partyId, Long rewardPoints){
        String serviceUrl = "http://localhost:8000/rewards-service/save-rewards/party_id/" + partyId + "/reward_points/" + rewardPoints;
        try {
            // Making an HTTP GET request to rewards-service
            ResponseEntity<String> responseEntity = new RestTemplate().getForEntity(serviceUrl, String.class);

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.ok(responseEntity.getBody() + " - via RestTemplate");
            } else {
                String errorMessage = "Error: " + responseEntity.getStatusCode();
                return ResponseEntity.status(responseEntity.getStatusCode()).body(errorMessage);
            }
        } catch (Exception e) {
            String errorMessage = "Failure: " + e.getMessage();
            return ResponseEntity.status(500).body(errorMessage); // Internal Server Error
        }
    }

    public List<Transaction> fetchTransactionsFeign(Long partyId) {
        try {
            // Making an HTTP GET request to rewards-service
            List<Transaction> transactions = rewardsServiceProxy.fetchTransactions(partyId);
            return transactions;
        } catch (Exception e) {
            System.err.println("Rewards-Service Failure: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public Long getPointsForEachTransactionFeign(String merchantName, BigDecimal amount){
        try {
            // Making an HTTP GET request to rewards-calculator-service
            Long rewards = rewardsCalculatorServiceProxy.calculateRewards(merchantName, amount);
            return rewards;
        } catch (Exception e) {
            System.err.println("Rewards-Calculator-Service Failure: " + e.getMessage());
            return -1L;
        }
    }

    public ResponseEntity<String> saveRewardsFeign(Long partyId, Long rewardPoints){
        try {
            // Making an HTTP GET request to rewards-service
            ResponseEntity<String> responseEntity = rewardsServiceProxy.saveRewards(partyId, rewardPoints);
            return ResponseEntity.ok(responseEntity.getBody() + " - via Feign");

        } catch (Exception e) {
            String errorMessage = "Failure: " + e.getMessage();
            return ResponseEntity.status(500).body(errorMessage); // Internal Server Error
        }
    }

}
