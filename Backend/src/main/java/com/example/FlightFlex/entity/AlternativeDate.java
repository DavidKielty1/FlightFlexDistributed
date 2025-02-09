package com.example.FlightFlex.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name = "alternative_dates")
public class AlternativeDate {
    @Id
    @Column(name = "flight_id")
    private String flightId;
    
    @Column(name = "suggested_date")
    @Temporal(TemporalType.DATE)
    private Date suggestedDate;
    
    @Column(name = "origin")
    private String origin;
    
    @Column(name = "destination")
    private String destination;
    
    @Column(name = "price")
    private double price;
    
    @Column(name = "availability")
    private String availability;

    // Getters and Setters
    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public Date getSuggestedDate() {
        return suggestedDate;
    }

    public void setSuggestedDate(Date suggestedDate) {
        this.suggestedDate = suggestedDate;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
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
