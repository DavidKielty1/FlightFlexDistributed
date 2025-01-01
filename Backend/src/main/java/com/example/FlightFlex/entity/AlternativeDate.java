package com.example.FlightFlex.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AlternativeDate {
    @Id
    private int userId;
    private String alternativeDate; // Use `LocalDate` if you prefer Java's date type
    private double price;
    private String availability;

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAlternativeDate() {
        return alternativeDate;
    }

    public void setAlternativeDate(String alternativeDate) {
        this.alternativeDate = alternativeDate;
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
