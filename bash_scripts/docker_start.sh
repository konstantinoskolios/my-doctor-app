#!/bin/bash

# shellcheck disable=SC2164
cd ../docker
pwd

# Stop and remove Docker Compose containers
docker-compose down --remove-orphans

docker volume prune
docker system prune


# Start Docker Compose
docker-compose up -d

# Check if Docker Compose failed
if [ $? -ne 0 ]; then
    echo "Docker Compose encountered an error."
fi

cd ../liquibase/changelog

liquibase update

if [ $? -eq 0 ]; then
    echo "Liquibase update executed successfully."
else
    echo "Liquibase update encountered an error."
fi
