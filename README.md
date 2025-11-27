# GPS Fleet Tracking System

Backend system for real-time GPS fleet tracking, trip detection, alerts, geofencing, and offline monitoring using **Spring Boot**, **JPA/Hibernate**, **MySQL**, and **REST APIs**.

## 🚀 Features
- Receive GPS pings in real-time
- Detect automatic Trip Start & Trip End
- Overspeed alerts
- Idle detection (in & out of trip)
- Geofencing (enter / exit)
- Offline device alerting
- Concurrent device state management
- Reverse geocoding address lookup
- Modeling for fleets, vehicles, drivers, devices
- Persistent Trip Events and Alerts tracking

## 🛠 Tech Stack
| Layer | Technology |
|--------|-------------|
| Backend Framework | Spring Boot 3 |
| ORM | JPA + Hibernate |
| Database | MySQL |
| Task Scheduling | Spring @Scheduled |
| Concurrency | ExecutorService + BlockingQueue |
| Build Tool | Maven |
| Security | Spring Security (Basic auth for testing) |

---

## ⚙ System Architecture
```
Client -> POST /gps/send-ping  --->  PingController -> Service -> Save GpsPing
                                          |                    
                                    TripProcessor.enqueue(ping)
                                          |
                                        Worker Thread
                                          |
                               Rule Engine & Device State Map
                                          |
                               Trip / Alerts / Geofence logic
                                          |
                                        Save results to DB
```
---

## 📍 Database Entities
- Fleet
- Driver
- Vehicle
- Device
- GPS Ping
- Trip
- Trip Event
- Alert

Trip detection uses movement history and distance calculations using **Haversine formula**.

---

## 📦 REST API Endpoints
### Fleet Management
```http
POST /gps/create-fleet
```
```json
{
  "name": "Logistics Fleet A"
}
```

### Send GPS Ping
```http
POST /gps/send-ping
```
```json
{
  "deviceId": "dev-1001",
  "lat": 19.0760,
  "lon": 72.8777,
  "speedKmh": 45,
  "heading": 90,
  "sentAt": "2025-01-01T10:00:00Z"
}
```

Example result activities:
- 🚀 auto start trip after enough movement
- ⛔ auto end trip after 6 min stationary
- ⚠ overspeed alert when >120 km/h
- 💤 idle alert after 60 sec
- 🟥 offline alert after 180 sec

---

## 🧠 Trip Processing Flow
```
Receive ping -> Fetch or create DeviceState
            -> Apply rules (start trip, idle, geofence etc)
            -> Update state
            -> Store Trip/Alert events
```
Rules are checked in this order:
1. Trip Start
2. Trip End
3. Overspeed
4. Idle Detection
5. Geofence Enter/Exit
6. Offline

---

## 🧪 Testing With Fake GPS Samples
Example sequence for trip start + overspeed + idle + end:
```json
[ 
 {"lat":19.07,"lon":72.87,"speedKmh":0,"sentAt":"10:00:00Z"},
 {"lat":19.0703,"lon":72.8703,"speedKmh":20,"sentAt":"10:00:05Z"},
 {"lat":19.0715,"lon":72.8715,"speedKmh":130,"sentAt":"10:00:10Z"},
 {"lat":19.0715,"lon":72.8715,"speedKmh":0,"sentAt":"10:01:20Z"},
 {"lat":19.0715,"lon":72.8715,"speedKmh":0,"sentAt":"10:07:30Z"}
]
```

---

## 🧵 Concurrency
- `LinkedBlockingQueue<GpsPing>` buffers incoming points
- Single worker thread ensures deterministic state
- ConcurrentHashMap stores device states safely

---

## 🔐 Security
Currently basic auth enabled only for development/testing.
Production expected: JWT + roles.

---

## 💡 Future Improvements
- Polygon geofences
- Driver scoring
- Kafka stream processing
- Redis caching for state store
- GraphQL / Websocket live location streaming
- Fleet dashboards & reports UI

---

## 🏁 Running the App
```
mvn clean install
mvn spring-boot:run
```

DB: configure in `application.properties`
```
spring.datasource.url=jdbc:mysql://localhost:3306/gpsfleet
spring.datasource.username=root
spring.datasource.password=yourpass
```

---

## 👤 Author
Vibhuti — Java Backend / Spring Boot Developer

---

## 📄 License
This project is open for educational and portfolio purposes.
