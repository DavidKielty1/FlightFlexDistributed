-- Drop tables if they exist
DROP TABLE IF EXISTS ad_recommendations;
DROP TABLE IF EXISTS alternative_dates;
DROP TABLE IF EXISTS user_segments;

-- Create tables
CREATE TABLE ad_recommendations (
    user_id INT PRIMARY KEY,
    recommended_ads TEXT
);

CREATE TABLE alternative_dates (
    user_id INT PRIMARY KEY,
    suggested_date DATE,
    price DECIMAL(10, 2),
    availability TEXT
);

CREATE TABLE user_segments (
    user_id INT PRIMARY KEY,
    prediction INT
);

-- Import data into tables
COPY ad_recommendations(user_id, recommended_ads)
FROM '/tmp/ad_recommendations.csv'
DELIMITER ',' CSV HEADER;

COPY alternative_dates(user_id, suggested_date, price, availability)
FROM '/tmp/alternative_dates.csv'
DELIMITER ',' CSV HEADER;

COPY user_segments(user_id, prediction)
FROM '/tmp/user_segments.csv'
DELIMITER ',' CSV HEADER;