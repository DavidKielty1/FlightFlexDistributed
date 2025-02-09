package com.example.FlightFlex.controller;

import com.example.FlightFlex.entity.AlternativeDate;
import com.example.FlightFlex.repository.AlternativeDateRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;

@SpringBootTest
@AutoConfigureMockMvc
public class FlightFlexControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AlternativeDateRepository repository;

    @Test
    void getAlternativeDate_ShouldReturnFlights() throws Exception {
        // Seed the database
        AlternativeDate flight = new AlternativeDate();
        flight.setFlightId("FL001");
        flight.setOrigin("NYC");
        flight.setDestination("LON");
        flight.setPrice(400.0);
        flight.setSuggestedDate(Date.from(LocalDate.of(2025, 2, 8).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        flight.setAvailability("Available");
        repository.save(flight);

        // Act & Assert
        mockMvc.perform(get("/alternative/date")
                .param("origin", "NYC")
                .param("destination", "LON")
                .param("price", "500")
                .param("date", "2025-02-10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].origin").value("NYC"))
                .andExpect(jsonPath("$[0].destination").value("LON"))
                .andExpect(jsonPath("$[0].price").value(400.0));
    }
}
