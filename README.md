# Spring Docker Template

A minimal **Spring Boot + MySQL** template that runs locally using **Docker Compose**/**Java Program*** or in production using **Docker**.

This repo includes:
- `Dockerfile` (multi-stage build: builds the Spring Boot JAR with Maven, then runs it on a slim JDK image) for production.
- `docker-compose.yml` (starts **MySQL 8** + the **Spring** service together) for development/local deployment.

---

## Why Docker?

Docker packages your app (and its runtime) into a container so you get:
- **Consistent environments** (works the same on every machine)
- No need to install software locally
- **Easy deployment** (one-command build)
- Prevents **"It works on my machine"**
---

## Why Docker Compose?

Compose lets you define and run **multiple services** together (here: `spring` + `mysql`) with:
- one command to build and start everything
- service discovery (your Spring container can reach MySQL by service name)
- repeatable configuration (ports, env vars, health checks)

---

## Prerequisites

Install:
- **Docker Desktop** (includes Docker Engine)
- **Docker Compose** (included with Docker Desktop)

Verify:
```bash
docker --version
docker compose version
```

---

## Quick Start (Recommended): Run with Docker Compose

From the repository root:

```bash
docker compose up --build
```

This will:
1. Pull `mysql:8`
2. Build your Spring app image from `Dockerfile`
3. Start both containers

### Ports (as configured)

- Spring app: **http://localhost:8081** (host) → `8080` (container)
- MySQL: **localhost:3307** (host) → `3306` (container)

---

## What `docker-compose.yml` does

It defines two services:

### 1) `mysql`
- Image: `mysql:8`
- Exposes MySQL on host port `3307`
- Sets environment variables:
  - `MYSQL_ROOT_PASSWORD=passIs1234`
  - `MYSQL_DATABASE=db`

### 2) `spring`
- Builds from the local `Dockerfile` (`build: .`)
- Exposes the app on host port `8081`
- Sets `SERVER_PORT=8080` inside the container

---

## Deploy using Docker (without Compose)

Use this if you want to run just the Spring container (or if your database is managed elsewhere).

### 1) Build the image

```bash
docker build -t spring-docker-template:latest .
```

### 2) Run the container

```bash
docker run --rm -p 8081:8080 --name spring_container spring-docker-template:latest
```

Now open:
- http://localhost:8081

> Note: This project is designed to run with MySQL. If you run the Spring container without a reachable database, you may need to configure datasource settings (for example via environment variables) depending on your Spring configuration.
