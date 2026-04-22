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





