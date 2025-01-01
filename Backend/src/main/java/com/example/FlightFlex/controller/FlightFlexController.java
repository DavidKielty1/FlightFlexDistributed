package com.example.FlightFlex.controller;

import com.example.FlightFlex.grpc.FlightFlexServiceProto.UserIdRequest;
import com.example.FlightFlex.grpc.FlightFlexServiceProto.AdRecommendationsResponse;
import com.example.FlightFlex.grpc.FlightFlexServiceProto.AlternativeDateResponse;
import com.example.FlightFlex.grpc.AdRecommendationsServiceGrpc;
import com.example.FlightFlex.grpc.AlternativeDateServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FlightFlexController {
    private final AdRecommendationsServiceGrpc.AdRecommendationsServiceBlockingStub adStub;
    private final AlternativeDateServiceGrpc.AlternativeDateServiceBlockingStub dateStub;

    public FlightFlexController() {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext()
                .build();
            this.adStub = AdRecommendationsServiceGrpc.newBlockingStub(channel);
            this.dateStub = AlternativeDateServiceGrpc.newBlockingStub(channel);
    }

    @GetMapping("/ads/recommendations")
    public List<String> getRecommendations(@RequestParam int userId) {
        UserIdRequest request = UserIdRequest.newBuilder().setUserId(userId).build();
        AdRecommendationsResponse response = adStub.getAdRecommendations(request);
        return response.getRecommendedAdsList();
    }

    @GetMapping("/alternative/date")
    public String getAlternativeDate(@RequestParam int userId) {
        UserIdRequest request = UserIdRequest.newBuilder().setUserId(userId).build();
        AlternativeDateResponse response = dateStub.getAlternativeDate(request);
        return response.getAlternativeDate();
    }
}
