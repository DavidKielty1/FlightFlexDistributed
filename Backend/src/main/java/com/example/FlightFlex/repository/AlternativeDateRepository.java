package com.example.FlightFlex.repository;

import com.example.FlightFlex.entity.AlternativeDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AlternativeDateRepository extends JpaRepository<AlternativeDate, Integer> {
    Optional<AlternativeDate> findByUserId(int userId);
}
