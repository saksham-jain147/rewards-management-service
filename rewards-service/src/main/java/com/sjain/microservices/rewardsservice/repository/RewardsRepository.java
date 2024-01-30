package com.sjain.microservices.rewardsservice.repository;

import com.sjain.microservices.rewardsservice.entity.Rewards;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RewardsRepository extends JpaRepository<Rewards, Long> {
}
