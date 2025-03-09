echo "starting databases ........."

cd databases/micronaut_mysql
docker compose up -d --build
cd ..
cd ..

cd databases/micronaut_postgresql
docker compose up -d --build
cd ..
cd ..

echo "starting microservices ........."
sleep 10s
slee
cd micronaut_consul
docker compose up -d --build
cd ..

cd microservices/docker-compose/micronaut-product
docker compose up -d --build
cd ..

cd micronaut-product-backup
docker compose up -d --build
cd ..

cd micronaut_customer
docker compose up -d --build
cd ..

#docker image ls
docker ps -a
