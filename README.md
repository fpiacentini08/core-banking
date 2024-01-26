# core-banking
This is a core-banking demo api 


## Run the app

mvn -Pnative spring-boot:run


## Start a redis instance

docker pull redis
docker run --name some-redis -p 6379:6379 -d redis 