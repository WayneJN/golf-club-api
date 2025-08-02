# ⛳ Golf Club API

A Spring Boot RESTful API for managing golf club members, tournaments, and participation. Includes flexible search functionality and Docker support for easy deployment.

---

## 🐳 Running the Project with Docker

### ✅ Prerequisites

- Docker installed on your machine
- Maven build completed (`./mvnw clean package`)

### 🚀 Start the Application

```bash
docker-compose up --build

## xThis will:

- Build the Spring Boot application image

- Start the MySQL database container

- Launch the API on http://localhost:8080

### 🛑 Stop the Application

```bash
docker-compose down

To remove volumes and start fresh:

```bash
docker-compose down -v


###🔍 Supported Search APIs
##👥 Member Search

Endpoint: GET /api/members/search Query Parameters:

name — partial or full name match (case-insensitive)

phone — partial phone number match

tournamentStartDate — filters members participating in tournaments starting on or after this date

Example:
GET /api/members/search?name=wayne&tournamentStartDate=2025-08-01

## ⛳ Tournament Search

Endpoint: GET /api/tournaments/search Query Parameters:

startDate — filters tournaments starting on or after this date

location — partial location match (case-insensitive)

memberId — filters tournaments that include the specified 

Example:
GET /api/tournaments/search?location=pebble&memberId=1


## 📦 API Overview

| Resource      | Endpoint                                         | Method | Description                          |
|---------------|--------------------------------------------------|--------|--------------------------------------|
| Members       | `/api/members`                                   | GET    | List all members                     |
|               | `/api/members`                                   | POST   | Add a new member                     |
|               | `/api/members/search`                            | GET    | Search members                       |
| Tournaments   | `/api/tournaments`                               | GET    | List all tournaments                 |
|               | `/api/tournaments`                               | POST   | Add a new tournament                 |
|               | `/api/tournaments/search`                        | GET    | Search tournaments                   |
| Participation | `/api/tournaments/{id}/members/{memberId}`       | POST   | Add member to tournament             |
|               | `/api/tournaments/{id}/members/{memberId}`       | DELETE | Remove member from tournament        |



