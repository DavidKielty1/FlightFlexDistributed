#!/bin/bash

# Set the container name
DB_CONTAINER="1c441a95d2cb" # Replace with your database container name

# Copy CSV files into the container

docker cp ../processed/ad_recommendations.csv 1c441a95d2cb:/tmp/ad_recommendations.csv
docker cp ../processed/alternative_dates.csv 1c441a95d2cb:/tmp/alternative_dates.csv
docker cp ../processed/user_segments.csv 1c441a95d2cb:/tmp/user_segments.csv
