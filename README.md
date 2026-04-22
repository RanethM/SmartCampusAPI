# Smart Campus API (JAX-RS)

## Overview

This project is a RESTful API developed using JAX-RS for managing a Smart Campus system. It allows the management of Rooms, Sensors, and Sensor Readings.

The API demonstrates core RESTful principles including resource-based design, proper HTTP methods, nested resources, filtering, and structured error handling.

Key features include:
- Room management (CRUD operations)
- Sensor registration with validation
- Sensor readings with historical tracking
- Sub-resource locator pattern
- Custom exception handling (409, 422, 403, 500)
- Logging using JAX-RS filters



## Technologies Used

- Java (JDK 8+)
- JAX-RS (Jersey)
- Maven
- Apache Tomcat
- JSON (Jackson)



## How to Run

1. Clone the repository:

git clone [https://github.com/RanethM/SmartCampusAPI](https://github.com/RanethM/SmartCampusAPI)


2. Open the project in NetBeans (or any Java IDE)

3. Build using Maven:

mvn clean install

4. Deploy the generated WAR file to Apache Tomcat

5. Start the server

6. Access the API at:

[https://localhost:8080/SmartCampusAPI/api/v1](http://localhost:8080/SmartCampusAPI/api/v1)



## API Design (REST Principles)

- Resources are modeled as:
- `/rooms`
- `/sensors`
- `/sensors/{id}/readings`
- Stateless communication between client and server
- Proper use of HTTP methods (GET, POST, DELETE)
- Meaningful HTTP status codes
- JSON-based responses





## API Endpoints

### Discovery

GET /api/v1

### Rooms

GET /rooms
POST /rooms
GET /rooms/{roomId}
DELETE /rooms/{roomId}


### Sensors

GET /sensors
GET /sensors?type=...
POST /sensors


### Sensor Readings

GET /sensors/{sensorId}/readings
POST /sensors/{sensorId}/readings


## Sample curl Commands

### Get all rooms

curl -X GET http://localhost:8080/SmartCampusAPI/api/v1/rooms


### Create room

curl -X POST http://localhost:8080/SmartCampusAPI/api/v1/rooms

-H "Content-Type: application/json"
-d '{"id":"R3","name":"Lecture Hall","capacity":100}'


### Create sensor

curl -X POST http://localhost:8080/SmartCampusAPI/api/v1/sensors

-H "Content-Type: application/json"
-d '{"id":"S1","type":"Temperature","status":"ACTIVE","currentValue":0,"roomId":"R1"}'


### Filter sensors

curl -X GET "http://localhost:8080/SmartCampusAPI/api/v1/sensors?type=Temperature
"


### Add sensor reading

curl -X POST http://localhost:8080/SmartCampusAPI/api/v1/sensors/S1/readings

-H "Content-Type: application/json"
-d '{"id":"RD1","timestamp":1710000000000,"value":25.5}'


