package com.example.FlightFlex.service;

import com.example.FlightFlex.entity.AlternativeDate;
import com.example.FlightFlex.repository.AlternativeDateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AlternativeDateServiceTest {

    @Mock
    private AlternativeDateRepository repository;

    @InjectMocks
    private AlternativeDateService service;

    @Test
    void findCheaperAlternatives_ShouldReturnMatchingFlights() {

        // Arrange
        String origin = "NYC";
        String destination = "LON";
        double price = 500.0;
        String date = "2025-02-10";

        AlternativeDate flight1 = new AlternativeDate();
        flight1.setFlightId("FL001");
        flight1.setOrigin("NYC");
        flight1.setDestination("LON");
        flight1.setPrice(400.0);
        flight1.setSuggestedDate(new Date());


        
        when(repository.findCheaperAlternatives(
            any(String.class), 
            any(String.class), 
            any(Double.class), 
            any(LocalDate.class), 
            any(LocalDate.class)
        )).thenReturn(Arrays.asList(flight1));

        // Act
        List<AlternativeDate> results = service.findCheaperAlternatives(origin, destination, price, date);

        // Assert
        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals("NYC", results.get(0).getOrigin());
        assertEquals("LON", results.get(0).getDestination());
        assertTrue(results.get(0).getPrice() < price);
    }

    @Test
    void findCheaperAlternatives_WithInvalidDate_ShouldThrowException() {
        // Arrange
        String origin = "NYC";
        String destination = "LON";
        double price = 500.0;
        String invalidDate = "invalid-date";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> 
            service.findCheaperAlternatives(origin, destination, price, invalidDate)
        );
    }
} 