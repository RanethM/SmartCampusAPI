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




---

## Error Handling Strategy

The API implements robust error handling using custom exceptions and ExceptionMappers:

| Scenario | Status Code | Explanation |
|--------|--------|------------|
| Room has sensors | 409 Conflict | Prevents data inconsistency |
| Invalid roomId | 422 Unprocessable Entity | Valid request but invalid reference |
| Sensor in maintenance | 403 Forbidden | State-based restriction |
| Unexpected error | 500 Internal Server Error | Global safety net |

All errors return structured JSON responses.

---

## Logging (Observability)

A logging filter is implemented using:
- `ContainerRequestFilter`
- `ContainerResponseFilter`

Logs include:
- Incoming HTTP method and URI
- Outgoing response status

This improves debugging, monitoring, and system observability.

---

## Report Answers

### JAX-RS Lifecycle
JAX-RS creates a new instance of resource classes per request. This avoids concurrency issues but requires shared data structures to be managed carefully using static collections.

---

### HATEOAS
Hypermedia allows clients to dynamically discover available endpoints via links in responses, reducing dependency on static documentation.

---

### IDs vs Full Objects
Returning full objects increases payload size but reduces client-side requests. Returning IDs is lightweight but requires additional calls.

---

### Idempotency of DELETE
DELETE is idempotent because repeated requests result in the same state (resource removed). Subsequent calls return 404 but do not alter system state.

---

### @Consumes Behaviour
If a client sends data in an unsupported format, JAX-RS returns HTTP 415 Unsupported Media Type.

---

### QueryParam vs PathParam
Query parameters are more flexible and suitable for filtering collections, while path parameters are better for identifying specific resources.

---

### Sub-resource Locator Benefits
Separates concerns by delegating nested resource logic to different classes, improving maintainability and scalability.

---

### Why 422 instead of 404
422 indicates that the request is valid but contains incorrect data, whereas 404 refers to a missing endpoint.

---

### Security Risk of Stack Traces
Stack traces expose internal system details such as class names and file paths, which attackers can exploit. Therefore, generic error responses are used.

---

### Logging Filters Advantage
Filters centralize logging logic, avoiding duplication and improving code maintainability.

---

## Author

Name: <YOUR_NAME>  
Module: 5COSC022W – Client-Server Architectures


