package com.example.FlightFlex.service;

import com.example.FlightFlex.entity.AdRecommendation;
import com.example.FlightFlex.repository.AdRecommendationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdRecommendationService {
    private final AdRecommendationRepository repository;

    public AdRecommendationService(AdRecommendationRepository repository) {
        this.repository = repository;
    }

    // Common logic used by both REST and gRPC
    public List<AdRecommendation> getAllRecommendations() {
        return repository.findAll();
    }

    public AdRecommendation getRecommendationByUserId(int userId) {
        return repository.findById(userId).orElse(null);
    }
}
