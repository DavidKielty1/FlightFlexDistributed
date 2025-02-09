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
