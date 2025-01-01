package com.example.FlightFlex.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserSegment {
    @Id
    private int userId;
    private int prediction;

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPrediction() {
        return prediction;
    }

    public void setPrediction(int prediction) {
        this.prediction = prediction;
    }
}
