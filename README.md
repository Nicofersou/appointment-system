# Appointment Management System (Spring Boot)

Backend application for managing appointments in service-based businesses (e.g. hair salons, beauty centers, clinics), built with **Java 17** and **Spring Boot**.

The project is designed as a **realistic, enterprise-style backend**, focusing on clean architecture, domain modeling, business rules, and best practices used in professional environments.

---

## 🚀 Project Goals

- Learn and apply **Spring Boot** and **Spring Data JPA** at an enterprise level
- Design a **clean and extensible domain model**
- Implement **real business rules** (availability, scheduling, constraints)
- Prepare the codebase for a future **microservices-oriented architecture**
- Serve as a **portfolio-ready project** for Java/Spring interviews

---

## 🧠 Domain Overview

This system models the core elements of a service appointment business:

### Main Concepts
- **Person** (base entity)
  - **Client**
  - **Worker** (e.g. hairdresser, stylist)
- **BusinessService**
  - Service name, duration, and price
- **Reservations** *(planned)*
- **Availability & Scheduling logic** *(planned)*

### Key Relationships
- A **Worker** can offer multiple **BusinessServices**
- A **BusinessService** can be offered by multiple **Workers**
- Workers have working hours and availability constraints
- Reservations will enforce time, availability, and service duration rules

---

## 🏗️ Architecture

The project follows a **feature-based package structure**, which scales better than layer-based packaging and is closer to real-world Spring Boot projects.

