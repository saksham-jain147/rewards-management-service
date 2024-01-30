package com.sjain.microservices.rewardsservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Rewards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rewardId;
    private Long partyId;
    private Long points;

    public Rewards() {
    }

    public Rewards(Long partyId, Long points) {
        this.partyId = partyId;
        this.points = points;
    }

    public Long getRewardId() {
        return rewardId;
    }

    public void setRewardId(Long rewardId) {
        this.rewardId = rewardId;
    }

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }
}
