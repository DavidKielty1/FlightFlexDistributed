package com.example.FlightFlex.repository;

import com.example.FlightFlex.entity.UserSegment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserSegmentRepository extends JpaRepository<UserSegment, Integer> {
}
