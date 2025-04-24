# Order Service

This repository contains the source code for the **Order Service**, a Spring Boot application.

## Overview

The Order Service is a microservice responsible for managing orders within the Next Digital application ecosystem. It provides functionalities for creating, retrieving, updating, and potentially processing orders.

This project is built using:

* **Java 21** (recommendad [sdkman.io](https://sdkman.io/) to handle different java versions)
* **Spring Boot 3.4.4**
* **Maven** as the build tool
* **Lombok** to reduce boilerplate code

## Dependencies

The project relies on the following core dependencies:

* `spring-boot-starter-actuator`: Provides production-ready features to monitor and manage the application.
* `spring-boot-starter-web`: Enables building web applications and RESTful APIs.
* `lombok`: A Java library that reduces boilerplate code, especially for POJOs.
* `spring-boot-starter-test`: Provides essential utilities for writing unit and integration tests.
* `junit:junit`: A widely used Java testing framework (version 3.8.1 is included).

## Build

To build the application, ensure you have Java 21 and Maven installed. Navigate to the root directory of the project in your terminal and run the following Maven command:

```shell
make install
```
or
```bash
mvn clean install
```

This command will:

1.  Clean the project.
2.  Compile the source code 
```shell
make compile
```
3.  Run any tests.
4.  Package the application into an executable JAR file (typically located in the `target` directory).

## Run the Application

Once the application is built, you can run it using the following Maven command from the project's root directory:

```shell
make run
```
or
```bash
mvn spring-boot:run
```

Alternatively, you can run the generated JAR file directly from the `target` directory:

```bash
java -jar target/order-0.0.1-SNAPSHOT.jar
```

The application will typically start on the default Spring Boot port (8080) unless configured otherwise.

## Configuration

Application-specific configurations (e.g., server port, database connections, etc.) can be managed through Spring Boot's standard configuration files, such as `application.properties` or `application.yml`, usually located in the `src/main/resources` directory.

## Testing

The project includes basic testing capabilities using `spring-boot-starter-test` and `junit`. You can run the tests using the following Maven command:

```bash
mvn test
```
or
```shell
make test
```

# Query API with curl commands

Sure! Here are the `curl` commands to interact with your API running on `localhost:8080`.

---

### ✅ Create an Order

```bash
curl -X POST http://localhost:8080/api/orders \
  -H "Content-Type: application/json"
```

---

### ✅ Get All Orders

```bash
curl http://localhost:8080/api/orders
```

---

### ✅ Pay an Order

Let’s say you got this response from creating an order:

```json
{
  "id": 1,
  "status": "CREATED",
  "items": []
}
```

Now you can pay it with:

```bash
curl -X POST http://localhost:8080/api/orders/1/pay \
  -H "Content-Type: application/json" \
  -d '{
        "id": 1,
        "status": "CREATED",
        "items": []
      }'
```

> ⚠️ Make sure the JSON you send in `-d` matches the structure of your actual `Order`.
