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

```bash id="q1d4h7"
curl -X GET http://localhost:8080/api/v1/rooms
```

```bash id="n3f8k5"
curl -X POST http://localhost:8080/api/v1/rooms -H "Content-Type: application/json" -d "{\"id\":\"R1\",\"name\":\"Library\",\"capacity\":100}"
```

```bash id="w6c2m9"
curl -X GET http://localhost:8080/api/v1/sensors
```

```bash id="z8v1r3"
curl -X POST http://localhost:8080/api/v1/sensors -H "Content-Type: application/json" -d "{\"id\":\"S1\",\"type\":\"Temperature\",\"status\":\"ACTIVE\",\"currentValue\":25.5,\"roomId\":\"R1\"}"
```

```bash id="y5g7n2"
curl -X GET http://localhost:8080/api/v1/sensors/S1/readings
```

---

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

```text id="d9w3l7"
SmartCampusAPI
├── src
├── pom.xml
└── README.md
```

---

## Author

Dileesha Dananji

---

## Conclusion

This project demonstrates a complete RESTful API using Java JAX-RS with CRUD operations, filtering, nested resources, proper exception handling, and logging.
