## POC about TestContainers
In this POC, I explore the use of TestContainers java library. It allows us to use Docker containers within our tests. As a result, we can write self-contained integration tests that depends on external resources.

We can use any external resource in our tests that have a docker image. For example, there are images for databases, web browsers, web servers, and message queues. Therefore, we can run them as ccontainers within our tests.

### Running Integration tests

- To download maven dependencies, compile project and run tests (including integrations)
<br> `./mvnw clean package`

- If you want to just run the tests
<br> `./mvnw test`

  Notice that above commands does not depends on a container previously running. This happens because this library does it automatically for us. 

### Running the system under test (REST API)

- To run the application
  1) first start manually PostgreSQL, a dependency needed by the application server:
<br> `docker run --name some-postgres -e POSTGRES_PASSWORD=mysecretpassword -p 5432:5432 -d postgres:11.7-alpine`
  
  2) the start the application server:
<br> `./mvnw spring-boot:run`
  
  3) and finally, open the browser and navigate to `http://localhost:8080/person`