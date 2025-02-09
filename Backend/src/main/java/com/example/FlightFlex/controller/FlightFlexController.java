package com.example.FlightFlex.controller;

import com.example.FlightFlex.entity.AlternativeDate;
import com.example.FlightFlex.service.AlternativeDateService;
import com.example.FlightFlex.grpc.FlightFlexServiceProto.UserIdRequest;
import com.example.FlightFlex.grpc.FlightFlexServiceProto.AdRecommendationsResponse;
import com.example.FlightFlex.grpc.AdRecommendationsServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FlightFlexController {
    private final AdRecommendationsServiceGrpc.AdRecommendationsServiceBlockingStub adStub;
    private final AlternativeDateService alternativeDateService;
    private static final Logger logger = LoggerFactory.getLogger(FlightFlexController.class);

    public FlightFlexController(AlternativeDateService alternativeDateService) {
        this.alternativeDateService = alternativeDateService;
        ManagedChannel channel = ManagedChannelBuilder
                // .forAddress("database", 5432)
                .forAddress("localhost", 50051)
                // .forAddress("grpc-server", 50051)
                .usePlaintext()
                .build();
        this.adStub = AdRecommendationsServiceGrpc.newBlockingStub(channel);
    }

    @GetMapping("/ads/recommendations")
    public List<String> getRecommendations(@RequestParam int userId) {
        UserIdRequest request = UserIdRequest.newBuilder().setUserId(userId).build();
        AdRecommendationsResponse response = adStub.getAdRecommendations(request);
        return response.getRecommendedAdsList();
    }

    @GetMapping("/alternative/date")
    public ResponseEntity<?> getAlternativeDate(
        @RequestParam String origin,
        @RequestParam String destination,
        @RequestParam double price,
        @RequestParam String date
    ) {
        try {
            logger.info("Received request: origin={}, dest={}, price={}, date={}",
                origin, destination, price, date);
            
            System.out.println("Searching alternatives for route: " + origin + " to " + destination);
            List<AlternativeDate> alternatives = alternativeDateService.findCheaperAlternatives(
                origin, destination, price, date
            );

            if (alternatives.get(0).getSuggestedDate() == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No alternative dates found.");
            }



            if (alternatives.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No alternative dates found.");
            }


            return ResponseEntity.ok(alternatives);


        } catch (IllegalArgumentException e) {

            logger.error("Validation error: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            logger.error("Error processing request", e);
            return ResponseEntity.internalServerError().body("An error occurred while processing your request");
        }
    }
}
