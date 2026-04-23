# SmartCampusAPI

## Project Overview

SmartCampusAPI is a RESTful API developed using **Java, Maven, and JAX-RS (Jersey)**.
This system simulates a Smart Campus environment by managing:

* Rooms
* Sensors
* Sensor Readings

The API supports:

* CRUD operations
* Filtering with query parameters
* Sub-resource routing
* Exception handling
* Logging filters


## Technologies Used

* Java
* Maven
* JAX-RS (Jersey)
* NetBeans IDE
* Grizzly HTTP Server
* Postman
* GitHub


## How to Build and Run the Project

### Requirements

* Java JDK 8+
* Maven
* NetBeans / IntelliJ IDEA

### Steps

1. Open the project in your IDE.
2. Clean and Build the project.
3. Run `SmartCampusAPI.java`
4. Server starts at:

```text id="m7n8x2"
http://localhost:8080/api/v1/
```


## API Base URL

```text id="s9p4t1"
http://localhost:8080/api/v1
```


## API Endpoints

| Method | Endpoint                  | Description        |
| ------ | ------------------------- | ------------------ |
| GET    | /                         | Discovery endpoint |
| GET    | /rooms                    | Get all rooms      |
| POST   | /rooms                    | Create room        |
| GET    | /rooms/{id}               | Get room by ID     |
| DELETE | /rooms/{id}               | Delete room        |
| GET    | /sensors                  | Get all sensors    |
| GET    | /sensors?type=Temperature | Filter sensors     |
| POST   | /sensors                  | Create sensor      |
| GET    | /sensors/{id}/readings    | Get readings       |
| POST   | /sensors/{id}/readings    | Add reading        |


## Sample curl Commands

## Sample curl Commands

### 1. Get Discovery Endpoint

```bash id="z0m4t7"
curl -X GET http://localhost:8080/api/v1/
```

### 2. Create Room

```bash id="u3v1r8"
curl -X POST http://localhost:8080/api/v1/rooms \
-H "Content-Type: application/json" \
-d "{\"id\":\"R1\",\"name\":\"Library\",\"capacity\":100}"
```

### 3. Get All Rooms

```bash id="g9x2m5"
curl -X GET http://localhost:8080/api/v1/rooms
```

### 4. Create Sensor

```bash id="m5n8q1"
curl -X POST http://localhost:8080/api/v1/sensors \
-H "Content-Type: application/json" \
-d "{\"id\":\"S1\",\"type\":\"Temperature\",\"status\":\"ACTIVE\",\"currentValue\":25.5,\"roomId\":\"R1\"}"
```

### 5. Get All Sensors

```bash id="h4p7c2"
curl -X GET http://localhost:8080/api/v1/sensors
```

### 6. Filter Sensors by Type

```bash id="k2f6z9"
curl -X GET "http://localhost:8080/api/v1/sensors?type=Temperature"
```

### 7. Add Sensor Reading

```bash id="r7d1w4"
curl -X POST http://localhost:8080/api/v1/sensors/S1/readings \
-H "Content-Type: application/json" \
-d "{\"id\":\"READ1\",\"timestamp\":1710000000,\"value\":27.2}"
```

### 8. Get Sensor Readings

```bash id="y6j3p8"
curl -X GET http://localhost:8080/api/v1/sensors/S1/readings
```

### 9. Test 404 Error

```bash id="n8s5e1"
curl -X GET http://localhost:8080/api/v1/sensors/INVALID/readings
```

### 10. Test 409 Conflict

```bash id="t1q9v6"
curl -X DELETE http://localhost:8080/api/v1/rooms/R1
```


## Example JSON Requests

### Create Room

```json id="f4b8p1"
{
  "id": "R1",
  "name": "Library",
  "capacity": 100
}
```

### Create Sensor

```json id="u7j3q9"
{
  "id": "S1",
  "type": "Temperature",
  "status": "ACTIVE",
  "currentValue": 25.5,
  "roomId": "R1"
}
```

### Add Reading

```json id="k2m6t4"
{
  "id": "READ1",
  "timestamp": 1710000000,
  "value": 27.2
}
```

## Error Handling

| Status Code | Description            |
| ----------- | ---------------------- |
| 404         | Resource not found     |
| 409         | Room has sensors       |
| 422         | Invalid room reference |
| 403         | Sensor unavailable     |
| 500         | Internal server error  |


## Project Structure

SmartCampusAPI
├── src
│   └── main
│       └── java
│           └── com.smartcampus
│               ├── model
│               │   ├── Room.java
│               │   ├── Sensor.java
│               │   └── SensorReading.java
│               │
│               ├── resource
│               │   ├── DiscoveryResource.java
│               │   ├── RoomResource.java
│               │   ├── SensorResource.java
│               │   └── SensorReadingResource.java
│               │
│               ├── service
│               │   └── DataStore.java
│               │
│               ├── exception
│               │   ├── GlobalExceptionMapper.java
│               │   ├── LinkedResourceNotFoundException.java
│               │   ├── LinkedResourceNotFoundMapper.java
│               │   ├── RoomNotEmptyException.java
│               │   ├── RoomNotEmptyMapper.java
│               │   ├── SensorUnavailableException.java
│               │   └── SensorUnavailableMapper.java
│               │
│               ├── filter
│               │   └── LoggingFilter.java
│               │
│               └── smartcampusapi
│                   ├── SmartCampusAPI.java
│                   └── SmartCampusApplication.java
│
├── pom.xml
└── README.md

## Conclusion

This project demonstrates a complete RESTful API using Java JAX-RS with CRUD operations, filtering, nested resources, proper exception handling, and logging.

## Conceptual Report Answers

### Part 1 – Service Architecture & Setup

1. Project & Application Configuration
The default setting of JAX-RS resource classes establishes request scope as their standard operational mode. This results in the creation of a fresh resource class instance for every incoming HTTP request. The system operates resource classes as single-instance entities except when users establish special configuration settings.
The design enhances thread safety because request handling processes use separate instance variables for their operations. The system must handle static class storage of in-memory data structures which include HashMaps and Lists as shared resources. The system allows multiple requests to access shared collections which creates the risk of race conditions and data inconsistency. The system needs to implement either synchronized collections or thread-safe structures which include ConcurrentHashMap for its operations.
2. The “Discovery” Endpoint
The advanced RESTful design requires hypermedia because it lets web services deliver response links which help users locate associated resources and determine their next action steps.
The room response provides links which lead to both its sensors and its deletion interface. This enables client developers to explore API navigation through dynamic discovery methods which go beyond static documentation.
The system provides these advantages to users:
Easier client integration
Better API discoverability
Reduced dependency on hardcoded URLs
Easier API evolution without breaking clients

### Part 2 – Room Management

1. Room Resource Implementation
The system returns only room IDs which leads to smaller response sizes and decreased network bandwidth requirements when multiple rooms exist in the system. The system provides faster performance for basic listings.
The system delivers complete room details through its full room object return method which enables users to obtain all required information without making additional requests.
Therefore:
IDs only = lighter and faster
Full objects = more convenient and informative
The optimal solution requires evaluation of operational requirements and system efficiency demands.

2. Room Deletion & Safety Logic - Is DELETE Idempotent?
Yes, DELETE operations maintain their idempotent nature because multiple request submissions will produce the same outcome.
The first successful deletion of a room through DELETE request allows users to delete the room again but DELETE request will not have any effect because the room already exists as deleted.
The system responds to repeated requests with the same blocked result because the room still has sensors which need to be eliminated.
The system state remains stable because identical DELETE requests will not produce any alterations to the system.

### Part 3 – Sensor Operations

1. Sensor Resource & Integrity
This annotation tells JAX-RS that the endpoint only accepts JSON request bodies.
If a client sends another format such as:
•	text/plain
•	application/xml
Then JAX-RS may reject the request with:
•	415 Unsupported Media Type
This protects the API from unsupported or incorrectly formatted input data.

2. Filtered Retrieval & Search
Using query parameters such as:
GET /sensors?type=CO2
is generally better for filtering collections than using path parameters such as:
/sensors/type/CO2
Reasons:
•	Query parameters are standard for searching/filtering
•	Multiple filters can be added easily
•	Resource path remains clean
•	Better readability
Therefore, query parameters are more flexible and RESTful for collection filtering.

### Part 4 – Deep Nesting with Sub - Resources

1. The Sub-Resource Locator Pattern
The Sub-Resource Locator pattern delegates nested paths to dedicated classes.
Example:
/sensors/{id}/readings
handled by SensorReadingResource.
Benefits:
•	Cleaner code organization
•	Smaller controller classes
•	Easier maintenance
•	Better scalability for large APIs
•	Separation of responsibilities
This is better than placing every nested route inside one large controller class.

2. Historical Data Management
The API must change the current sensor value of the parent sensor whenever new sensor readings arrive. 
The system ensures three outcomes through its design:
The system provides an accurate present status of the latest sensor. 
The system maintains both the historical records and current conditions in perfect alignment. 
The system delivers identical information to clients through all available data transmission methods. 
The system needs this update because it protects measurement records from becoming disconnected from present sensor measurements.

### Part 5 – Advanced Error Handling, Exception Mapping & Logging

1. Dependency Validation (422 Unprocessable Entity)
HTTP 422 Unprocessable Entity is more semantically accurate when the request body is valid JSON but contains invalid data such as a roomId that does not exist.
404 usually means the requested URL resource itself was not found.
Therefore:
•	404 = URL resource missing
•	422 = request accepted but data inside is invalid

2. The Global Safety Net (500)
The process of showing internal Java stack traces to users will disclose confidential technical data which includes Package names Class names File paths Framework versions Database details and Internal logic flow information. The attackers will utilize this data to discover security weaknesses and conduct their specific attack operations. The system needs to provide generic error messages which do not include complete stack trace information to users.

3. API Request & Response Logging Filters
The automatic execution of filters for all requests and responses makes them perfect for handling cross-cutting concerns which include logging. The system provides multiple benefits which exceed the advantages of using Logger statements through manual input. The system provides centralized logging functions which handle all logging activities across different system components. The system reduces code duplication through its centralized logging functions. The system enables better system maintenance through its centralized logging functions. The system provides uniform logging practices which apply to all system endpoints. The system provides resource methods which operate without any unnecessary elements except business logic. The system improves code quality and code observability through this process.

