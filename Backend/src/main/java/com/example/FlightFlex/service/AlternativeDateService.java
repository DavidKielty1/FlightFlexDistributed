package com.example.FlightFlex.service;

import com.example.FlightFlex.repository.AlternativeDateRepository;
import com.example.FlightFlex.entity.AlternativeDate;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlternativeDateService {
    private final AlternativeDateRepository repository;

    public AlternativeDateService(AlternativeDateRepository repository) {
        this.repository = repository;
    }

    public AlternativeDate getAlternativeDateByUserId(int userId) {
        Optional<AlternativeDate> result = repository.findByUserId(userId);
        return result.orElse(null);
    }
}
