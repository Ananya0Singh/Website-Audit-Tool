# PagePulse – Website Audit Tool

PagePulse is a full-stack web application that analyzes publicly accessible websites and provides key SEO and performance insights. Users can submit a URL to receive metrics such as HTTP status, response time, page title, meta description, H1 count, word count, and images missing `alt` attributes.

## Features

- Website analysis using a URL
- HTTP status and response time measurement
- Page title and meta description extraction
- H1 heading count
- Word count analysis
- Detection of images without `alt` attributes
- RESTful API built with Spring Boot
- Responsive React frontend
- Unit testing with JUnit 5

## Tech Stack

**Backend**
- Java 21
- Spring Boot 3.5
- Maven
- Jsoup

**Frontend**
- React
- Vite
- Axios

**Testing**
- JUnit 5

**Deployment**
- Backend: Render
- Frontend: Vercel
- Docker

## Live Demo

**Frontend:** https://website-audit-tool-eight-ebon.vercel.app/

**Backend API:** https://website-audit-tool-1lql.onrender.com

## Setup

### Backend

```bash
git clone https://github.com/Ananya0Singh/Website-Audit-Tool.git
cd pagepulse-backend
mvn clean install
mvn spring-boot:run
```

Runs on:

```
http://localhost:8080
```

### Frontend

```bash
cd pagepulse-frontend
npm install
npm run dev
```

> **Note:** The frontend is configured to use the deployed Render backend. To use a local backend, update the API base URL in the frontend configuration.

## API Contract

### Analyze Website

**Endpoint**

```
POST /api/audit
```

**Request**

```json
{
  "url": "https://example.com"
}
```

**Success Response (200)**

```json
{
  "status": 200,
  "responseTime": 184,
  "title": "Example Domain",
  "metaDescription": "Example description",
  "h1Count": 1,
  "imagesWithoutAlt": 0,
  "wordCount": 245
}
```

**Error Responses**

**Empty URL**

```json
{
  "message": "URL cannot be empty"
}
```

**Invalid URL**

```json
{
  "message": "Invalid URL"
}
```

**Website Unreachable**

```json
{
  "message": "Website unreachable"
}
```

## Running Tests

```bash
mvn test
```

The test suite covers:
- Successful website analysis
- Empty URL validation
- Invalid URL validation
- Unreachable website handling

## Design Decisions

### 1. Jsoup for HTML Parsing
Jsoup was chosen because it provides a reliable and efficient way to fetch webpages and extract HTML elements such as titles, meta tags, headings, and images with minimal code.

### 2. Separate Frontend and Backend
The application follows a client-server architecture with a React frontend and a Spring Boot backend. This separation improves maintainability, scalability, and allows each layer to evolve independently.

### 3. Centralized Validation and Exception Handling
Input validation and custom exception handling are implemented in the backend to ensure consistent error responses, prevent unnecessary processing, and provide meaningful feedback for invalid or unreachable URLs.

## Repository

GitHub: https://github.com/Ananya0Singh/Website-Audit-Tool

## Demo Video

Loom: 

## Author

**Ananya Singh**
