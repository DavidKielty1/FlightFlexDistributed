# FlightFlexDistributed

Providing tooling to ensure we can give our users the best experience. Matching customers with tailor-suited deals and offers and alternative flights. We give holidayers the power to choose the right journeys for them -- providing detailed alternative flight times and prices to suit their needs!

## Structure:

### Data Processing

pySpark, AWS EMR, EC2, S3, pandas

### Backend

Java Springboot. gRPC with postgresQL.

### Frontend

React. HTTP Restful requests.

## Setup:

### Entire App:

docker-compose up
(--build)

docker-compose down
(-v)

### Tables and Data

cd data/database
bash setup.sh

### Local:

docker-compose up database

cd ./backend
./gradlew clean build
java -jar build/libs/FlightFlex-1.0-SNAPSHOT.jar

cd ./frontend
npm i
npm run start

## Docker/psql commands

docker ps

docker exec -it {Container_ID} psql -U postgres -d flightflexdb
docker exec -it {Container Name} psql -U postgres -d flightflexdb
