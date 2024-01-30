package com.sjain.microservices.rewardsservice.repository;

import com.sjain.microservices.rewardsservice.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByPartyId(Long partyId);
}
