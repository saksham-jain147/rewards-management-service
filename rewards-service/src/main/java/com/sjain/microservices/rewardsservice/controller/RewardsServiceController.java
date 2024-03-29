package com.sjain.microservices.rewardsservice.controller;

import com.sjain.microservices.rewardsservice.repository.TransactionRepository;
import com.sjain.microservices.rewardsservice.entity.Rewards;
import com.sjain.microservices.rewardsservice.entity.Transaction;
import com.sjain.microservices.rewardsservice.service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RewardsServiceController {

    @Autowired
    private TransactionRepository repository;
    @Autowired
    private RewardsService rewardsService;
    @Autowired
    private Environment environment;
    @GetMapping("/rewards-service/fetch-transactions/party_id/{partyId}")
    public List<Transaction> fetchTransactions(@PathVariable Long partyId){
//        return new Transaction(1001L, "amazon", BigDecimal.valueOf(70000), partyId);

//        //Testing with Dummy Data
//        List<Transaction> transactions = new ArrayList<>();
//        String port = environment.getProperty("local.server.port");
//        Transaction t1 = new Transaction(1001L, "amazon", BigDecimal.valueOf(70000), partyId);
//        t1.setEnvironment(port);
//        transactions.add(t1);
//        Transaction t2 = new Transaction(1004L, "flipkart", BigDecimal.valueOf(6000), partyId);
//        t2.setEnvironment(port);
//        transactions.add(t2);
//        return transactions;

        List<Transaction> transactions = repository.findByPartyId(partyId);
//        if(transactions.size()==0){
//            throw new RuntimeException("Unable to find transactions for party_id " + partyId);
//        }
        String port = environment.getProperty("local.server.port");
        transactions.forEach(transaction -> transaction.setEnvironment(port));
        return transactions;
    }

    @GetMapping("/rewards-service/save-rewards/party_id/{partyId}/reward_points/{rewardPoints}")
    public ResponseEntity<String> saveRewards(
            @PathVariable Long partyId,
            @PathVariable Long rewardPoints){
//        String result = rewardsService.insertRecord(new Rewards(partyId, rewardPoints));
//        return ResponseEntity.ok(result);
        return rewardsService.insertRecord(new Rewards(partyId, rewardPoints));
    }

}
