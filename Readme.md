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

docker-compose up --build

### Local:

docker-compose up database

cd ./backend
./gradlew clean build
java -jar build/libs/FlightFlex.jar

cd ./frontend
npm i
npm run start
