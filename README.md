# IncognitoCampus — Backend

Spring Boot backend powering the IncognitoCampus anonymous chat platform.
Handles REST APIs, WebSocket messaging, MongoDB persistence, and Gemini AI
summarization. Deployed on Railway.

**Frontend repo:** [IncognitoCampus Frontend](https://github.com/Kumargaurav126/incognito-campus-frontend)  
**Live app:** [incognito-campus-frontend.vercel.app](https://incognito-campus-frontend.vercel.app)

---

## Tech stack

| Layer          | Technology              |
| -------------- | ----------------------- |
| Framework      | Spring Boot, Spring MVC |
| Real-time      | WebSocket (STOMP)       |
| Database       | MongoDB                 |
| AI Integration | Gemini AI API           |
| Deploy         | Railway                 |

---

## API overview

### User

| Method | Endpoint             | Description                  |
| ------ | -------------------- | ---------------------------- |
| POST   | `/api/user/register` | Register with anonymous name |
| GET    | `/api/user/{id}`     | Fetch user profile           |

### Room

| Method | Endpoint          | Description       |
| ------ | ----------------- | ----------------- |
| GET    | `/api/rooms`      | List all rooms    |
| GET    | `/api/rooms/{id}` | Get room by ID    |
| POST   | `/api/rooms`      | Create a new room |

### Message

| Method | Endpoint                       | Description               |
| ------ | ------------------------------ | ------------------------- |
| GET    | `/api/messages/{roomId}`       | Fetch messages for a room |
| DELETE | `/api/messages/{roomId}/clear` | Manually clear messages   |

### WebSocket

| Destination            | Description                    |
| ---------------------- | ------------------------------ |
| `/app/chat.send`       | Send a message to a room       |
| `/topic/room/{roomId}` | Subscribe to a room's messages |

---

## Architecture

- WebSocket layer uses STOMP protocol over SockJS for reliable real-time
  bidirectional communication
- MongoDB TTL index on messages collection auto-deletes documents after
  24 hours — no scheduled job needed
- Gemini AI API called on-demand to summarize recent messages in a room,
  returning structured digests
- Stateless REST layer sits alongside the WebSocket server in the same
  Spring Boot application context

---

## Running locally

### Prerequisites

- Java 25+
- Maven
- MongoDB (local or Atlas URI)
- Gemini API key

### Setup

```bash
git clone https://github.com/Kumargaurav126/incognito-campus-backend
cd incognito-campus-backend
```

Create `src/main/resources/application.properties`:

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/incognito_campus
gemini.api.key=YOUR_API_KEY
```

Then run:

```bash
mvn clean install
java -jar target/incognito-campus.jar
```

Server starts on `http://localhost:8080`

---

## Project structure

```
src/main/java/com/incognitocampus/
├── controller/        # REST controllers
├── websocket/         # WebSocket config and handlers
├── service/           # Business logic
├── repository/        # MongoDB repositories
├── model/             # Entity classes
└── config/            # Spring and security config
```
