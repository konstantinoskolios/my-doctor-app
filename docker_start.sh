#!/bin/bash


# Stop and remove Docker Compose containers
docker-compose down --remove-orphans

docker volume prune -f
docker system prune -f

# Start Docker Compose
docker-compose up --remove-orphans -d

# Check if Docker Compose failed
if [ $? -ne 0 ]; then
    echo "Docker Compose encountered an error."
fi

# shellcheck disable=SC2164
cd liquibase/changelog

liquibase update

if [ $? -eq 0 ]; then
    echo "Liquibase update executed successfully."
else
    echo "Liquibase update encountered an error."
fi
