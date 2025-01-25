#!/bin/bash

# Set the container name
DB_CONTAINER="0db3e64e3d74" # Replace with your database container name

# Copy CSV files into the container

docker cp ../processed/ad_recommendations.csv 0db3e64e3d74:/tmp/ad_recommendations.csv
docker cp ../processed/alternative_dates.csv 0db3e64e3d74:/tmp/alternative_dates.csv
docker cp ../processed/user_segments.csv 0db3e64e3d74:/tmp/user_segments.csv
docker cp table-creation.sql 0db3e64e3d74:/tmp/table-creation.sql

# Commands to run
# docker exec -it 0db3e64e3d74 psql -U postgres -d flightflexdb -f /tmp/table-creation.sql
# \dt
# SELECT * FROM ad_recommendations;



## Large script
# #!/bin/bash

# # Get the container ID dynamically based on the running PostgreSQL container
# DB_CONTAINER=$(docker ps --filter "ancestor=postgres:15-alpine" --format "{{.ID}}")

# # Ensure a valid container ID was found
# if [ -z "$DB_CONTAINER" ]; then
#   echo "PostgreSQL container is not running. Please start the container and try again."
#   exit 1
# fi

# echo "Using PostgreSQL container: $DB_CONTAINER"

# # Paths to your files
# CSV_DIR="../processed"
# SQL_FILE="./table-creation.sql"

# # Ensure files exist before proceeding
# if [ ! -f "$SQL_FILE" ] || [ ! -f "$CSV_DIR/ad_recommendations.csv" ] || [ ! -f "$CSV_DIR/alternative_dates.csv" ] || [ ! -f "$CSV_DIR/user_segments.csv" ]; then
#   echo "Required files not found. Ensure the SQL file and CSVs exist in the correct paths."
#   exit 1
# fi

# # Copy the CSV files and SQL file into the container
# echo "Copying files into the container..."
# docker cp "$CSV_DIR/ad_recommendations.csv" "$DB_CONTAINER:/tmp/ad_recommendations.csv"
# docker cp "$CSV_DIR/alternative_dates.csv" "$DB_CONTAINER:/tmp/alternative_dates.csv"
# docker cp "$CSV_DIR/user_segments.csv" "$DB_CONTAINER:/tmp/user_segments.csv"
# docker cp "$SQL_FILE" "$DB_CONTAINER:/tmp/table-creation.sql"

# # Execute the SQL file inside the container
# echo "Running SQL file inside the container..."
# docker exec -i "$DB_CONTAINER" psql -U postgres -d flightflexdb -f /tmp/table-creation.sql

# # Verify the tables were created
# echo "Verifying tables in the database..."
# docker exec -i "$DB_CONTAINER" psql -U postgres -d flightflexdb -c "\dt"

# # Query the `ad_recommendations` table to ensure data was imported
# echo "Previewing data in the ad_recommendations table..."
# docker exec -i "$DB_CONTAINER" psql -U postgres -d flightflexdb -c "SELECT * FROM ad_recommendations;"
