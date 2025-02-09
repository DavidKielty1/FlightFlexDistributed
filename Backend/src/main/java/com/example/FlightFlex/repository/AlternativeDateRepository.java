package com.example.FlightFlex.repository;

import com.example.FlightFlex.entity.AlternativeDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDate;

@Repository
public interface AlternativeDateRepository extends JpaRepository<AlternativeDate, String> {
    @Query("SELECT a FROM AlternativeDate a WHERE " +
           "a.origin = :origin AND " +
           "a.destination = :destination AND " +
           "a.price < :price AND " +
           "CAST(a.suggestedDate AS date) BETWEEN :startDate AND :endDate " +
           "ORDER BY a.price ASC")
    List<AlternativeDate> findCheaperAlternatives(
        @Param("origin") String origin,
        @Param("destination") String destination,
        @Param("price") double price,
        @Param("startDate") LocalDate startDate,
        @Param("endDate") LocalDate endDate
    );
}
