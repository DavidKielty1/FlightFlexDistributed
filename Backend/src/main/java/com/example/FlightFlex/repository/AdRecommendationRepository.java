package com.example.FlightFlex.repository;

import com.example.FlightFlex.entity.AdRecommendation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdRecommendationRepository extends JpaRepository<AdRecommendation, Integer> {

}
