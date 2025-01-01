package com.example.FlightFlex.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ad_recommendations")
public class AdRecommendation {
    @Id
    private int userId;
    private String recommendedAds;

    //Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId){
        this.userId = userId;
    }

    public String getRecommendedAds() {
        return recommendedAds;
    }

    public void setRecommendedAds(String recommendedAds) {
        this.recommendedAds = recommendedAds;
    }
}
