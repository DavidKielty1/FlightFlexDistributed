package com.example.FlightFlex.grpc;

import com.example.FlightFlex.entity.AlternativeDate;
import com.example.FlightFlex.service.AlternativeDateService;
import com.example.FlightFlex.grpc.FlightFlexServiceProto.UserIdRequest;
import com.example.FlightFlex.grpc.FlightFlexServiceProto.AlternativeDateResponse;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

@Service
public class AlternativeDateServiceImpl extends AlternativeDateServiceGrpc.AlternativeDateServiceImplBase {
    private final AlternativeDateService alternativeDateService;

    public AlternativeDateServiceImpl(AlternativeDateService alternativeDateService) {
        this.alternativeDateService = alternativeDateService;
    }


    @Override
    public void getAlternativeDate(UserIdRequest request, StreamObserver<AlternativeDateResponse> respondStreamObserver) { 
        try {
            AlternativeDate alternativeDate = alternativeDateService.getAlternativeDateByUserId(request.getUserId());
            AlternativeDateResponse response = alternativeDate != null ?
                AlternativeDateResponse.newBuilder()
                    .setAlternativeDate(alternativeDate.getAlternativeDate())
                    .build()
                : AlternativeDateResponse.newBuilder().build();

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
