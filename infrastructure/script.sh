#!/bin/bash

docker-compose up -d

docker-compose exec broker kafka-topics \
  --create \
  --if-not-exists \
  --bootstrap-server localhost:9092 \
  --replication-factor 1 \
  --partitions 3 \
  --topic my-topic
