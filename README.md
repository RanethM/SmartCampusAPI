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

# Part 1: Service Architecture & Setup 

## 1. Project & Application Configuration 

**Answer:**
The default lifecycle of a resource class is a per-request. This means that the new instance of the resource class is created for each incoming HTTP request. This behavior is managed by the JAX-RS runtime, which ensures that each request is handled independently. 
This design makes resource classes thread safe as each request is handled by a separate instance and there is no shared state between them at the object level. 
However, in this coursework data is stored using shared in-memory resources such as Maps or Lists. Due to this they are not thread safe. 
If multiple requests try to modify these shared data structures at the same time, it can lead to race conditions or inconsistent data. To avoid this, developers should use thread-safe collections or apply synchronization when updating shared data. 
In summary, while JAX-RS resource instances are created per request and are safely shared, in-memory data must be carefully managed to prevent concurrency issues. 

## 2.  The” Discovery” Endpoint 

**Answer:**
Hypermedia (HATEOAS – Hypermedia as the Engine of Application State) is considered a hallmark of advanced RESTful design because the API includes links to related resources within its responses. This allows clients to dynamically navigate the API without needing prior knowledge of all endpoints.
Instead of relying on hardcoded URLs or external documentation, the client can follow the links provided in response to discovering available actions and resources. This makes the API more self-descriptive and adaptable to changes.
For client developers, this approach reduces dependency on static documentation, minimizes errors caused by outdated URLs, and improves flexibility. If the API structure changes, clients can continue to function correctly by using the links provided in responses, rather than requiring code updates.

#  Part 2: Room Management 

## 1. Room Resource Implementation 

**Answer:**
Sending back only room IDs cuts down the quantity of information transmitted through the network, hence improving performance and minimizing network traffic, particularly when there are many rooms. But this would require the client to send more requests to get the details about each room, thereby increasing the load on the client and making more API requests.
Conversely, sending back-room objects means that the client will have all the required information on one request, thus reducing the complexity of operations performed by the client. However, this will increase the volume of the data returned, potentially affecting network performance in large datasets.
In conclusion, sending IDs is efficient in terms of network traffic, while sending full objects is convenient in terms of client operations.

## 2. Room Deletion & Safety Logic 

**Answer:**
Yes, the DELETE operation is idempotent in this implementation. An operation is considered to be idempotent if making the same request multiple times results in the same final state in the server. 
When a DELETE request is sent for a specific room the room will be removed if it exists in the system. If the same DELETE request is sent again to the same room, the room will no longer exist, and the server will return a not found (404) response. However, after the first deletion the state of the system will remain unchanged. 
Thus, even if the client mistakenly sends the DELETE request multiple times the outcome is consistent where the room will remain deleted since repeated requests will not produce side effects beyond the initial operation.

# Part 3: Sensor Operations & Linking 

## 1. Sensor Resource & Integrity 

**Answer:**
The  @Consumes(MediaType.APPLICATION_JSON) annotation indicates that the POST method can accept JSON-only input. If another media type is used, for example, text/plain or application/xml, JAX-RS would not find any matching between the media type and the method.

This means that the response sent back by JAX-RS would be a HTTP 415 – Unsupported Media Type message indicating that the server is unable to handle the request due to its unsupported media type.

The above ensures that the server receives input only in the required media type, avoiding any conversion errors.

## 2. Filtered Retrieval & Search 

**Answer:**
@QueryParam should be used for filtering since it provides for a more flexible option in terms of designing queries on a set of resources. Using query parameters will enable us to include filters in requests in such a way that it does not affect the API endpoint design, as we may do it like this: /Api/v1/sensors? Type=CO2.

However, using path-based filtering like this: /api/v1/sensors/type/CO2 implies the fact that filters become an integral part of the resource URI itself. Therefore, implementing path-based filtering is not as convenient when there are several filters (such as sensors’ type, status, or ranges) because URI becomes more complicated.

As seen from the above, query parameters provide us with a more scalable solution because multiple filters can be added (as in the example /api/v1/sensors?type=CO2&status=ACTIVE).

# Part 4: Deep Nesting with Sub - Resources (20 Marks)

## 1. The Sub-Resource Locator Pattern 

**Answer:**
A Sub-Resource Locator design pattern helps make the architecture of a RESTful API better because it allows nesting resources via different classes instead of creating a big class that will have to handle everything. There is no need to define everything like sensors/{id}/readings/{rid} in one class; the logic will belong to specific resource classes.

This way, each class will deal with some part of the whole process and will not be involved in any other processes that do not belong to it. In this way, it will make an application cleaner and more modular.

As we know, when there are a lot of nested paths that exist within one big class, the application turns out to be quite messy. Using sub-resources locators will make the whole system much more scalable since it is easy to introduce changes without changing anything in many places.

In general, this pattern makes code easier to manage and understand.

# Part 5: Advanced Error Handling, Exception Mapping & Logging 

## 2. Dependency Validation (422 Unprocessable Entity) 

**Answer:**
Status code HTTP 422 non-Processable Entity is viewed as more semantically meaningful compared to HTTP 404 Not Found if the problem is a non-existent reference within a properly structured JSON body because the request was valid in structure and semantics yet included logically inconsistent information.

This means that the client tried to create a particular resource, such as a sensor, with an inexistent roomId. Since the endpoint is valid and accessible, then the use of status code 404 is inappropriate since it usually implies the absence of the requested resource or endpoint in question.

On the contrary, the use of HTTP 422 status code shows that the request was valid in structure, yet it could not be executed because of the presence of logically incorrect data.

## 4.The Global Safety Net (500)

**Answer:**
Exposing internal Java stack traces to external API consumers can be a huge security risk, as it reveals details about the system's internal structure and implementation. A stack trace can show class names, package structures, file paths, method calls, and even the technologies and frameworks in use.

The attacker could then use this information to identify potential vulnerabilities such as outdated libraries, insecure components, or specific entry points into the application. It can also reveal queries to the database, configuration information or logic flaws that could be exploited.

Further, stack traces can also assist attackers in understanding how the system handles requests, which facilitates tailoring attacks to the system, such as injection attacks or denial-of-service attacks.

For these reasons, it is important to hide internal errors from users and instead return generic error messages while logging detailed information securely on the server side.

## 5. API Request & Response Logging Filters

**Answer:**
It is advantageous to use filters from JAX-RS to handle cross-cutting concerns such as logging as it allows for the implementation of functionalities applicable to all requests and responses. The need to include a Logger.info () statement in every request and response will not arise anymore since a single filter will manage the capture of all incoming requests and all responses.

In addition to reducing code redundancy, which makes the system simpler to maintain, logging of all requests will take place using a standard format without being dependent on individual programmers to provide such logging at all endpoints. Filters allow for easy updating of a system whenever changes to some functionalities arise.

Otherwise, having such cross-cutting concerns handled manually leads to code repetition, which increases the likelihood of omitting some loggings and makes system maintenance difficult.

## Author

Name: Raneth Kavendra Mendis  
Module: 5COSC022C.2 – Client-Server Architectures

