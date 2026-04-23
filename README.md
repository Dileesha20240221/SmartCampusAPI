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

---

## Technologies Used

* Java
* Maven
* JAX-RS (Jersey)
* NetBeans IDE
* Grizzly HTTP Server
* Postman
* GitHub

---

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

---

## API Base URL

```text id="s9p4t1"
http://localhost:8080/api/v1
```

---

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

---

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

---

## Error Handling

| Status Code | Description            |
| ----------- | ---------------------- |
| 404         | Resource not found     |
| 409         | Room has sensors       |
| 422         | Invalid room reference |
| 403         | Sensor unavailable     |
| 500         | Internal server error  |

---

## Project Structure

SmartCampusAPI ├── src │ ├── main │ │ ├── java │ │ │ └── com.smartcampus │ │ │ ├── SmartCampusAPI.java │ │ │ ├── config │ │ │ │ └── ApplicationConfig.java │ │ │ ├── model │ │ │ │ ├── Room.java │ │ │ │ ├── Sensor.java │ │ │ │ └── SensorReading.java │ │ │ ├── resource │ │ │ │ ├── DiscoveryResource.java │ │ │ │ ├── RoomResource.java │ │ │ │ ├── SensorResource.java │ │ │ │ └── SensorReadingResource.java │ │ │ ├── service │ │ │ │ └── DataStore.java │ │ │ ├── exception │ │ │ │ ├── RoomNotEmptyException.java │ │ │ │ ├── LinkedResourceNotFoundException.java │ │ │ │ ├── SensorUnavailableException.java │ │ │ │ ├── RoomNotEmptyMapper.java │ │ │ │ ├── LinkedResourceNotFoundMapper.java │ │ │ │ ├── SensorUnavailableMapper.java │ │ │ │ └── GlobalExceptionMapper.java │ │ │ └── filter │ │ │ └── LoggingFilter.java ├── pom.xml └── README.md

## Conclusion

This project demonstrates a complete RESTful API using Java JAX-RS with CRUD operations, filtering, nested resources, proper exception handling, and logging.
