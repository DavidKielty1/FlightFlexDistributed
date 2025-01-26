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
    user_id INT,
    flight_id VARCHAR(10),
    suggested_date DATE,
    origin VARCHAR(10),
    destination VARCHAR(10),
    price DECIMAL(10, 2),
    availability VARCHAR(10),
    PRIMARY KEY (user_id, flight_id)
);

-- Create table for user segments
CREATE TABLE user_segments (
    user_id INT PRIMARY KEY,
    prediction INT
);

-- Create table for precomputed routes
CREATE TABLE precomputed_routes (
    origin VARCHAR(10),
    destination VARCHAR(10),
    average_price DECIMAL(10, 2),
    availability VARCHAR(10),
    top_airlines TEXT
);
s
-- Import data into tables
COPY ad_recommendations(user_id, recommended_ads)
FROM '/tmp/ad_recommendations.csv'
DELIMITER ',' CSV HEADER;

COPY alternative_dates(user_id, flight_id, suggested_date, origin, destination, price, availability)
FROM '/tmp/alternative_dates.csv'
DELIMITER ',' CSV HEADER;

COPY user_segments(user_id, prediction)
FROM '/tmp/user_segments.csv'
DELIMITER ',' CSV HEADER;

COPY precomputed_routes(origin, destination, average_price, availability, top_airlines)
FROM '/tmp/precomputed_routes.csv'
DELIMITER ',' CSV HEADER;
