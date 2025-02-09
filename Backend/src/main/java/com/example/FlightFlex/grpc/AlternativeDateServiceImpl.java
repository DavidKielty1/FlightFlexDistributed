package com.example.FlightFlex.grpc;

import com.example.FlightFlex.entity.AlternativeDate;
import com.example.FlightFlex.service.AlternativeDateService;
import com.example.FlightFlex.grpc.FlightFlexServiceProto.UserIdRequest;
import com.example.FlightFlex.grpc.FlightFlexServiceProto.AlternativeDateResponse;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AlternativeDateServiceImpl extends AlternativeDateServiceGrpc.AlternativeDateServiceImplBase {
    private final AlternativeDateService alternativeDateService;

    public AlternativeDateServiceImpl(AlternativeDateService alternativeDateService) {
        this.alternativeDateService = alternativeDateService;
    }


    @Override
    public void getAlternativeDate(UserIdRequest request, StreamObserver<AlternativeDateResponse> respondStreamObserver) { 
        try {
            // Use default values for testing
            List<AlternativeDate> alternatives = alternativeDateService.findCheaperAlternatives(
                "NYC", "LON", 500.0, "2025-02-08"
            );
            
            AlternativeDateResponse response = alternatives.isEmpty() ?
                AlternativeDateResponse.newBuilder().build() :
                AlternativeDateResponse.newBuilder()
                    .setAlternativeDate(alternatives.get(0).getSuggestedDate().toString())
                    .build();

            respondStreamObserver.onNext(response);
            respondStreamObserver.onCompleted();
        } catch (Exception e) {
            respondStreamObserver.onError(Status.INTERNAL
                .withDescription("An error occurred")
                .withCause(e)
                .asRuntimeException());
        }
    }
}
