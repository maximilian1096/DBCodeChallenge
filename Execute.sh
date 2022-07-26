#!/bin/bash

docker run --name DBCodeChallenge -d -p 5432:5432 -e POSTGRES_PASSWORD=bdcode -e POSTGRES_DB=DBCodeChallenge postgres
./mvnw spring-boot:run
docker stop DBCodeChallenge
docker rm DBCodeChallenge

