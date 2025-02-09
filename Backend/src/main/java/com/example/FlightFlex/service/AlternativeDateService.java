package com.example.FlightFlex.service;

import com.example.FlightFlex.repository.AlternativeDateRepository;
import com.example.FlightFlex.entity.AlternativeDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDate;

@Service
public class AlternativeDateService {
    private final AlternativeDateRepository repository;
    private final Logger logger = LoggerFactory.getLogger(AlternativeDateService.class);

    public AlternativeDateService(AlternativeDateRepository repository) {
        this.repository = repository;
    }

    private void validateRequest(String origin, String destination, double price, String date) {
        if (origin == null || origin.trim().isEmpty()) {
            throw new IllegalArgumentException("Origin is required");
        }
        if (destination == null || destination.trim().isEmpty()) {
            throw new IllegalArgumentException("Destination is required");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
        try {
            LocalDate.parse(date);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date format");
        }
    }

    public List<AlternativeDate> findCheaperAlternatives(String origin, String destination, double price, String date) {
        try {
            validateRequest(origin, destination, price, date);
            
            logger.info("Parsing date: {}", date);
            LocalDate searchDate = LocalDate.parse(date);
            LocalDate startDate = searchDate.minusDays(3);
            LocalDate endDate = searchDate.plusDays(3);

            logger.info("Search parameters: origin={}, dest={}, price={}, searchDate={}, range={} to {}",
                origin, destination, price, searchDate, startDate, endDate);

            // Check if dates match column type
            logger.info("Database column type check - suggestedDate from first record: {}",
                repository.findAll().stream().findFirst().map(AlternativeDate::getSuggestedDate).orElse(null));

            List<AlternativeDate> results = repository.findCheaperAlternatives(
                origin, destination, price, startDate, endDate);

            logger.info("Found {} alternatives", results.size());
            return results;
        } catch (Exception e) {
            logger.error("Error in findCheaperAlternatives", e);
            throw e;
        }
    }
}
