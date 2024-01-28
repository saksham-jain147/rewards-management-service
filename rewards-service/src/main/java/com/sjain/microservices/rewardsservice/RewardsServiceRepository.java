package com.sjain.microservices.rewardsservice;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RewardsServiceRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByPartyId(Long partyId);
}
