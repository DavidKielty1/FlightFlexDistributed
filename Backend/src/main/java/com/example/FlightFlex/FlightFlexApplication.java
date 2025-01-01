package com.example.FlightFlex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.ApplicationContext;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import com.example.FlightFlex.grpc.AdRecommendationsServiceImpl;
import com.example.FlightFlex.grpc.AlternativeDateServiceImpl;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.FlightFlex.controller", "com.example.FlightFlex.grpc", "com.example.FlightFlex.entity", "com.example.FlightFlex.repository", "com.example.FlightFlex.config", "com.example.FlightFlex.service"})
@EnableJpaRepositories(basePackages = "com.example.FlightFlex.repository")
@EntityScan(basePackages = "com.example.FlightFlex.entity")
public class FlightFlexApplication {

    private Server server;

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(FlightFlexApplication.class, args); // Start Spring Boot
        System.out.println("Beans in application context:");
        for (String beanName : ctx.getBeanDefinitionNames()) {
            System.out.println(beanName);
        }

        // Start the gRPC server
        FlightFlexApplication app = new FlightFlexApplication();
        try {
            app.startGrpcServer(
                ctx.getBean(AdRecommendationsServiceImpl.class),
                ctx.getBean(AlternativeDateServiceImpl.class)
            );
        } catch (Exception e) {
            System.err.println("Failed to start gRPC server: " + e.getMessage());
        }
    }

    private void startGrpcServer(AdRecommendationsServiceImpl adService, AlternativeDateServiceImpl dateService) throws Exception {
        server = ServerBuilder.forPort(50051)
                .addService(adService)  // Register AdRecommendationsService
                .addService(dateService)
                .build()
                .start();
        System.out.println("gRPC server started on port 50051");

        // Hook for shutdown
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down gRPC server");
            if (server != null) {
                server.shutdown();
            }
        }));

        server.awaitTermination(); // Keep the gRPC server running
    }
}
