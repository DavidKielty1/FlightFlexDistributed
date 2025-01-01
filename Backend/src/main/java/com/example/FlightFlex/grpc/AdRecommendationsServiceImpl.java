package com.example.FlightFlex.grpc;

import com.example.FlightFlex.entity.AdRecommendation;
import com.example.FlightFlex.service.AdRecommendationService;
import com.example.FlightFlex.grpc.FlightFlexServiceProto.UserIdRequest;
import com.example.FlightFlex.grpc.FlightFlexServiceProto.AdRecommendationsResponse;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

@Service
public class AdRecommendationsServiceImpl extends AdRecommendationsServiceGrpc.AdRecommendationsServiceImplBase {
    private final AdRecommendationService adRecommendationService;

    public AdRecommendationsServiceImpl(AdRecommendationService service) {
        this.adRecommendationService = service;
    }

    @Override
    public void getAdRecommendations(UserIdRequest request, StreamObserver<AdRecommendationsResponse> responseObserver) {
        AdRecommendation recommendation = adRecommendationService.getRecommendationByUserId(request.getUserId());
        if (recommendation != null) {
            AdRecommendationsResponse response = AdRecommendationsResponse.newBuilder()
                    .addRecommendedAds(recommendation.getRecommendedAds())
                    .build();
            responseObserver.onNext(response);
        } else {
            responseObserver.onNext(AdRecommendationsResponse.newBuilder().build());
        }
        responseObserver.onCompleted();
    }
}
