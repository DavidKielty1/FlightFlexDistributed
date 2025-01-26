#!/bin/bash

# Define the expected container name
EXPECTED_CONTAINER_NAME="flightflexdistributed-database-1"
EXPECTED_IMAGE_NAME="postgres:15-alpine"
EXPECTED_REDIS_NAME="redis-instance"

# Debugging: Verify container detection
echo "Attempting to find the PostgreSQL container..."
DB_CONTAINER=$(docker ps --filter "name=$EXPECTED_CONTAINER_NAME" --format "{{.ID}}")
if [ -z "$DB_CONTAINER" ]; then
    echo "Failed to find container with name $EXPECTED_CONTAINER_NAME."
    DB_CONTAINER=$(docker ps --filter "ancestor=$EXPECTED_IMAGE_NAME" --format "{{.ID}}")
    if [ -z "$DB_CONTAINER" ]; then
        echo "Failed to find container with image $EXPECTED_IMAGE_NAME."
        exit 1
    fi
fi
echo "Found container: $DB_CONTAINER"

echo "Attempting to find the Redis container..."
REDIS_CONTAINER=$(docker ps --filter "name=$EXPECTED_REDIS_NAME" --format "{{.ID}}")
if [ -z "$REDIS_CONTAINER" ]; then
    echo "Redis container not found. Starting a new one..."
    REDIS_CONTAINER=$(docker run --name "$EXPECTED_REDIS_NAME" -d redis)
    echo "Started Redis container: $REDIS_CONTAINER"
else
    echo "Found Redis container: $REDIS_CONTAINER"
fi

# Define SQL files
SQL_SETUP_FILE="/tmp/setup_database.sql"
SQL_DATA_CREATION_FILE="/tmp/data_population.sql"

# Copy SQL files to the container
echo "Copying SQL setup and data files to container: $DB_CONTAINER"
docker cp setup_database.sql "$DB_CONTAINER:$SQL_SETUP_FILE"
docker cp data_population.sql "$DB_CONTAINER:$SQL_DATA_CREATION_FILE"

# Verify files inside the container
echo "Verifying files inside the container..."
docker exec -i "$DB_CONTAINER" sh -c "ls -l $SQL_SETUP_FILE $SQL_DATA_CREATION_FILE"

# Execute the SQL setup file inside the container
echo "Executing SQL setup file inside the container..."
docker exec -i "$DB_CONTAINER" sh -c "psql -U postgres -d flightflexdb -f $SQL_SETUP_FILE"

# Execute the SQL data creation file inside the container
echo "Executing SQL data creation file inside the container..."
docker exec -i "$DB_CONTAINER" sh -c "psql -U postgres -d flightflexdb -f $SQL_DATA_CREATION_FILE"

# Log sample data from each table
echo "Fetching sample data from each table for verification..."
docker exec -i "$DB_CONTAINER" psql -U postgres -d flightflexdb -c "\dt"
docker exec -i "$DB_CONTAINER" psql -U postgres -d flightflexdb -c "SELECT * FROM precomputed_routes LIMIT 5;"
docker exec -i "$DB_CONTAINER" psql -U postgres -d flightflexdb -c "SELECT * FROM alternative_dates LIMIT 5;"
docker exec -i "$DB_CONTAINER" psql -U postgres -d flightflexdb -c "SELECT * FROM ad_recommendations LIMIT 5;"
docker exec -i "$DB_CONTAINER" psql -U postgres -d flightflexdb -c "SELECT * FROM user_segments LIMIT 5;"

# Prepopulate Redis with sample data
echo "Flushing and prepopulating Redis container with sample data..."
docker exec -i "$REDIS_CONTAINER" redis-cli FLUSHALL
docker exec -i "$REDIS_CONTAINER" redis-cli <<EOF
HMSET route:ATL:AMS:FL0001 date "2025-01-26" price "350" availability "Medium"
HMSET route:SFO:HKG:FL0002 date "2025-01-27" price "400" availability "High"
EOF

# Verify Redis prepopulation
echo "Verifying Redis prepopulation..."
docker exec -i "$REDIS_CONTAINER" redis-cli KEYS "route:*"

echo "Database setup and data population complete!"
