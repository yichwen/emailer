# build spring boot
cd emailer
mvn clean package

# docker build mail-service
cd mail-service
docker build -t mail-service .

# docker build mail-provider
cd ../mail-provider
docker build -t mail-provider .

# build frontend
cd ../../frontend
ng build

# docker build frontnend
docker build -t mail-frontend .