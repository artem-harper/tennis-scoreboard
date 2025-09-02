# 🎾 Tennis Match Scoreboard

A real-time tennis match scoring system built with Java Servlets and Hibernate. Track live matches with professional tennis scoring rules.

![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square)
![Hibernate](https://img.shields.io/badge/Hibernate-6.0-blue?style=flat-square)
![H2 Database](https://img.shields.io/badge/H2-Database-green?style=flat-square)
![Servlets](https://img.shields.io/badge/Servlets-API-yellow?style=flat-square)
![License](https://img.shields.io/badge/License-MIT-lightgrey?style=flat-square)

## 📖 Overview

Tennis Match Scoreboard is a web application that allows you to:
- Create and manage tennis matches in real-time
- Track score with professional tennis rules
- View match history and statistics
- Search matches by player names

## 🚀 Features

### 🎯 Core Functionality
- **Real-time scoring** with automatic game/set calculation
- **Match management** - create, update, and complete matches
- **Player database** - store and manage player information
- **Match history** - view completed matches with detailed statistics
- **Search system** - find matches by player name or date

### 🎾 Tennis Rules Implementation
- Professional scoring system (0, 15, 30, 40, Advantage)
- Set and match tracking
- Tie-break support
- Automatic winner determination

### 💻 Technical Features
- Responsive web interface
- RESTful API design
- Database persistence with Hibernate
- In-memory H2 database for development
- Servlet-based MVC architecture

## 🛠️ Technology Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| **Java** | 21 | Main programming language |
| **Hibernate** | 6.0+ | ORM and database management |
| **H2 Database** | 2.0+ | Embedded database |
| **Java Servlets** | 4.0+ | Web controller layer |
| **JSP** | 2.3+ | View templating |
| **Maven** | 3.6+ | Build and dependency management |
| **Tomcat** | 10.0+ | Application server |

## 📦 Installation

### Prerequisites
- Java 21 or higher
- Apache Maven 3.6+
- Tomcat 10+ (optional, embedded available)

### Quick Start

1. **Clone the repository**
```bash
git clone https://github.com/your-username/tennis-scoreboard.git
cd tennis-scoreboard
