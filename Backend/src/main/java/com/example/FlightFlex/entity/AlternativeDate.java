package com.example.FlightFlex.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "alternative_dates") // Map to the correct table name
public class AlternativeDate {

    @Id
    @Column(name = "user_id") 
    private int userId;
    
    @Column(name = "suggested_date") 
    private String alternativeDates; 

    @Column(name = "price") 
    private double price;

    @Column(name = "availability") 
    private String availability;

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAlternativeDate() {
        return alternativeDates;
    }

    public void setAlternativeDate(String alternativeDates) {
        this.alternativeDates = alternativeDates;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}
