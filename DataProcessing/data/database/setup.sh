#!/bin/bash

# Define the expected container name
EXPECTED_CONTAINER_NAME="flightflexdistributed-database-1"
EXPECTED_IMAGE_NAME="postgres:15-alpine"

# Try to find the container ID by name
DB_CONTAINER=$(docker ps --filter "name=$EXPECTED_CONTAINER_NAME" --format "{{.ID}}")

# If no container is found by name, attempt to find by image name
if [ -z "$DB_CONTAINER" ]; then
  echo "No PostgreSQL container found with name '$EXPECTED_CONTAINER_NAME'. Attempting to find by image name..."
  DB_CONTAINER=$(docker ps --filter "ancestor=$EXPECTED_IMAGE_NAME" --format "{{.ID}}")
fi

# Ensure a valid container ID was found
if [ -z "$DB_CONTAINER" ]; then
  echo "PostgreSQL container is not running. Please start the container and try again."
  exit 1
fi

echo "Using PostgreSQL container: $DB_CONTAINER"

# Base directory
BASE_DIR="$(pwd -W)" # Get Windows-compatible path

# Define paths to the files
CSV_DIR="$BASE_DIR/../processed"
SQL_FILE="$BASE_DIR/table-creation.sql"

# Debug: Show paths being used
echo "Base Directory: $BASE_DIR"
echo "CSV Directory: $CSV_DIR"
echo "SQL File: $SQL_FILE"

# Copy files to the container
echo "Copying files to the container..."
docker cp "$CSV_DIR/ad_recommendations.csv" "$DB_CONTAINER:/tmp/ad_recommendations.csv"
docker cp "$CSV_DIR/alternative_dates.csv" "$DB_CONTAINER:/tmp/alternative_dates.csv"
docker cp "$CSV_DIR/user_segments.csv" "$DB_CONTAINER:/tmp/user_segments.csv"
docker cp "$CSV_DIR/precomputed_routes.csv" "$DB_CONTAINER:/tmp/precomputed_routes.csv"
docker cp "$SQL_FILE" "$DB_CONTAINER:/tmp/table-creation.sql"

# Debug: Check files in the container
echo "Listing files in /tmp inside the container..."
docker exec -i "$DB_CONTAINER" ls /tmp

# Execute the SQL script inside the container
echo "Executing SQL script inside the container..."
docker exec -i "$DB_CONTAINER" psql -U postgres -d flightflexdb -f /tmp/table-creation.sql

# Verify tables
echo "Verifying database tables..."
docker exec -i "$DB_CONTAINER" psql -U postgres -d flightflexdb -c "\dt"
docker exec -i "$DB_CONTAINER" psql -U postgres -d flightflexdb -c "SELECT * FROM alternative_dates LIMIT 5;"
docker exec -i "$DB_CONTAINER" psql -U postgres -d flightflexdb -c "SELECT * FROM precomputed_routes LIMIT 5;"

# Redis setup (if required)
REDIS_CONTAINER=$(docker ps --filter "ancestor=redis" --format "{{.ID}}")

if [ -z "$REDIS_CONTAINER" ]; then
  echo "No Redis container found. Starting a new Redis container..."
  REDIS_CONTAINER="redis-instance"
  docker run --name "$REDIS_CONTAINER" -d redis
else
  echo "Using existing Redis container: $REDIS_CONTAINER"
fi

# Optional: Add Redis commands to preload common data if necessary
echo "Loading sample data into Redis..."
docker exec -i "$REDIS_CONTAINER" redis-cli <<EOF
HMSET route:ATL:AMS date "2025-01-05" price 211 availability "Low"
HMSET route:SFO:HKG date "2025-01-06" price 398 availability "Medium"
EOF

echo "Setup complete!"
