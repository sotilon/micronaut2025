echo "down datases ........."

cd databases/micronaut_mysql
docker compose down
cd ..
cd ..

cd databases/micronaut_postgresql
docker compose down
cd ..
cd ..

echo "down microservices ........."
cd micronaut_consul
docker compose down
cd ..

cd microservices/docker-compose/micronaut-product
docker compose down
cd ..

cd micronaut-product-backup
docker compose down
cd ..

cd micronaut_customer
docker compose down
cd ..

#docker image ls
docker ps -a
