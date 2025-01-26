-- Drop tables if they exist
DROP TABLE IF EXISTS ad_recommendations;
DROP TABLE IF EXISTS alternative_dates;
DROP TABLE IF EXISTS user_segments;
DROP TABLE IF EXISTS precomputed_routes;

-- Create table for ad recommendations
CREATE TABLE ad_recommendations (
    user_id INT PRIMARY KEY,
    recommended_ads TEXT
);

-- Create table for alternative dates
CREATE TABLE alternative_dates (
    flight_id VARCHAR(10) PRIMARY KEY,
    suggested_date DATE,
    origin VARCHAR(10),
    destination VARCHAR(10),
    price DECIMAL(10, 2),
    availability VARCHAR(10)
);

-- Create table for user segments
CREATE TABLE user_segments (
    user_id INT PRIMARY KEY,
    prediction INT
);

-- Create table for precomputed routes
CREATE TABLE precomputed_routes (
    flight_id VARCHAR(10) PRIMARY KEY,
    origin VARCHAR(10),
    destination VARCHAR(10),
    average_price DECIMAL(10, 2),
    availability VARCHAR(10),
    top_airlines TEXT
);
