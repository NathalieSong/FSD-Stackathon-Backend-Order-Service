docker-compose down
docker image rm emart-order-service
docker build . -t emart-order-service
docker-compose up -d