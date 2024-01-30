package com.sjain.microservices.rewardsservice.service;

import com.sjain.microservices.rewardsservice.entity.Rewards;
import com.sjain.microservices.rewardsservice.repository.RewardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RewardsService {
    @Autowired
    private RewardsRepository rewardsRepository;

    public ResponseEntity<String> insertRecord(Rewards rewards){
        try{
            rewardsRepository.saveAndFlush(rewards);
//            return "Success: Record inserted successfully.";
            return ResponseEntity.status(HttpStatus.CREATED).body("Success: Record inserted successfully.");
        } catch (Exception e) {
//            return "Error: Unable to insert record. " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Unable to insert record. " + e.getMessage());
        }
    }
}
