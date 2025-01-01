package com.example.FlightFlex.utils;


import org.springframework.stereotype.Component;

@Component
public class UserValidationUtil {
    public boolean isValidUserId(int userId) {
        // Logic to validate user ID
        return userId > 0;
    }
}
