This project includes the backend microservice of the car service.



![Car Service Backend Architecture](backend.jpg)


### 1. Run the following command to build and create backend jar file:
```sh
mvn package -Dmaven.test.skip=true
```

### 2. Run the following command to start the backend containers of the car-service:
```sh
docker-compose up
```


### 3. Run the following command to run the tests
```sh
mvn clean test
```

### 4. Run the following command to generate a test coverage report
```sh
mvn jacoco:report
```
Open the "target/site/jacoco/index.html" file to see the test coverage report.

Resources:
1. https://www.udemy.com/course/spring-boot-egitimi/
2. https://www.toptal.com/java/spring-boot-rest-api-error-handling
3. https://www.baeldung.com/jacoco

