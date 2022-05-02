# Docker

## Build image
``` 
docker build -t gringotts-back:dev . 
```

## Run container
``` 
docker run -it -p 8080:8080 -e SPRING_DATA_MONGODB_HOST=host.docker.internal -e SPRING_DATA_MONGODB_PORT=27017 -e SPRING_DATA_MONGODB_DATABASE=gringotts -e SPRING_DATA_MONGODB_USERNAME=gringotts -e SPRING_DATA_MONGODB_PASSWORD=toor --name gringotts-back-container -d gringotts-back:dev
```