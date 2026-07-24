# 🚀 PagePulse

PagePulse is a lightweight web page auditing tool built with **Spring Boot** and **React**. It analyzes a given website URL and provides key insights such as HTTP status, response time, page title, meta description, heading count, image accessibility, and approximate word count.

## ✨ Features

- 🔍 Analyze any public website by entering its URL
- 🌐 Retrieve HTTP response status
- ⚡ Measure page response time
- 📄 Extract page title
- 📝 Fetch meta description
- 📌 Count H1 headings
- 🖼️ Detect images missing `alt` attributes
- 📊 Calculate approximate word count
- ❌ Gracefully handle invalid URLs, unreachable websites, and non-HTML responses

## 🛠️ Tech Stack

### Backend
- Java 21
- Spring Boot
- Spring Web
- Jsoup
- Maven

### Frontend
- React
- Vite
- Axios
- CSS

## 📊 Audit Report Includes

- HTTP Status Code
- Response Time
- Page Title
- Meta Description
- H1 Heading Count
- Images Missing `alt` Text
- Approximate Word Count

## 📬 API Endpoint

**POST** `/api/audit`

### Request

```json
{
  "url": "https://spring.io"
}
```

### Sample Response

```json
{
  "status": 200,
  "responseTime": 245,
  "title": "Spring",
  "metaDescription": "Spring makes Java simple...",
  "h1Count": 1,
  "imagesWithoutAlt": 0,
  "wordCount": 1458
}
```

## 🌐 Live Demo

**Frontend:** https://website-audit-tool-eight-ebon.vercel.app/

**Backend API:** https://your-render-url.onrender.com


## 👩‍💻 Author

**Ananya Singh**

GitHub: https://github.com/Ananya0Singh



Built for the **Digital Heroes Training Task**.
