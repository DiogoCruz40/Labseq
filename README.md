# **Getting Started**

### **What this is**

This is a simple template to facilitate the development startup of our LabSeq exercise.
Provided in this project is a stripped down Springboot application, with a Redis database, orchestrated with Docker.

### **Requirements**

- Maven
- Java 17
- Docker

### **Start-up**

Run the following commands in the command line:

    - docker-compose up

## **Instructions**

For a GUI of all the requests available use Swagger on http://localhost:8080/challenge/swagger-ui/index.html

### Curl Instructions

top-zones -
Get the 5 top zones by dropoff or pickup, if you want to order by pickup just put 'PICKUP' on query param order instead.

```shell
curl -X 'GET' 'http://localhost:8080/challenge/top-zones?order=DROPOFF' \
  -H 'accept: application/json'
```

### Tests
Init created for it but tests were not able to be developed in time. With that if you wish can develop from the init files created for it.
