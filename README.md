# Java Interview Deep Dive

A comprehensive interview preparation repository covering **Java, REST API, Microservices, Spring Boot, Kafka, and Scalable Systems**. This project includes both theoretical knowledge and a working Spring Boot demo application showcasing Java 8+ features.

## 📚 Repository Structure

### Interview Preparation Materials
- **[README.md](README.md)** - This file (repo overview)
- **[DETAILED_ANSWERS.md](DETAILED_ANSWERS.md)** - In-depth Q&A covering Java, REST API, Microservices, Spring Boot, Kafka, and SOLID principles
- **[SCALABLE_APPLICATIONS.md](SCALABLE_APPLICATIONS.md)** - Deep-dive questions (6 levels) on scalable systems, real-time & batch processing

### Demo Application
A Spring Boot REST API demonstrating:
- **Java 8+ Features**: Streams, lambdas, Optional, Date/Time API, functional interfaces
- **REST Endpoints**: Filtering, mapping, grouping, sorting with streams
- **Advanced Patterns**: Function composition, strategy pattern, lazy evaluation

**Package**: `com.antigravity.interviewdeepdive`  
**Main Class**: `InterviewDeepDiveApplication`

## 🎯 Topics Covered

| Category | Topics |
|----------|--------|
| **Java** | Core language, concurrency (volatile, AtomicInteger), streams & lambdas, collections |
| **REST API** | Design principles, HTTP semantics, versioning, pagination, security (OAuth2, JWT) |
| **Microservices** | Architecture patterns, resilience (Circuit Breaker), data consistency, event-driven design |
| **Spring Boot** | Configuration, dependency injection, testing (@SpringBootTest, MockMvc) |
| **Kafka** | Producer/consumer, partitions, offsets, exactly-once semantics, consumer groups |
| **Scalable Systems** | CAP theorem, real-time processing, batch processing (Spark, MapReduce), Lambda/Kappa architectures |

## 🚀 Quick Start

### Prerequisites
- Java 17
- Maven

### Run the Application
```bash
./mvnw spring-boot:run
```

### Sample Endpoints
```bash
# Get employees by department
curl "http://localhost:8080/api/streams/filter?dept=IT"

# Group employees by department
curl "http://localhost:8080/api/streams/group-by-dept"

# Calculate age from birth date
curl "http://localhost:8080/api/features/age?birthDate=1990-05-15"

# Demo function composition
curl "http://localhost:8080/api/features/functional?input=hello&mode=upper"
```

## 📖 How to Use This Repo

1. **Study Theory**: Read [DETAILED_ANSWERS.md](DETAILED_ANSWERS.md) for comprehensive Q&A with scenario-based questions
2. **Deep Dive**: Explore [SCALABLE_APPLICATIONS.md](SCALABLE_APPLICATIONS.md) for advanced systems design topics
3. **Hands-On Practice**: Run the Spring Boot app and experiment with the REST endpoints
4. **Code Review**: Study the service implementations to see real-world Java 8+ patterns in action

## 🎓 Interview Mindset

When tackling interview questions:
- **Problem-solving**: Break down scenarios, identify constraints, propose concrete solutions
- **Trade-off analysis**: Discuss pros/cons of alternatives (sync vs async, relational vs NoSQL)
- **Debugging approach**: Explain how to reproduce issues, gather logs/metrics, isolate root causes
- **Best practices**: Reference patterns (Circuit Breaker, Idempotent Consumer, SOLID principles)
- **Communication**: Articulate solutions clearly, balancing technical depth and business impact

## 📁 Project Layout

```
├── src/main/java/com/antigravity/interviewdeepdive/
│   ├── InterviewDeepDiveApplication.java  # Main Spring Boot app
│   ├── controller/
│   │   └── DemoController.java            # REST endpoints
│   ├── service/
│   │   ├── StreamService.java             # Stream API demos
│   │   └── FeatureService.java            # Java 8+ feature demos
│   └── model/
│       └── Employee.java                  # Sample domain model (record)
├── DETAILED_ANSWERS.md                    # Core interview Q&A
├── SCALABLE_APPLICATIONS.md               # Advanced systems Q&A
└── pom.xml                                # Maven configuration
```

## 🔗 Further Reading

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Java SE 17 Documentation](https://docs.oracle.com/en/java/javase/17/)
- [Apache Kafka Documentation](https://kafka.apache.org/documentation/)

---

*Prepare with confidence. Good luck with your interviews!*
