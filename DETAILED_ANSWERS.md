# Detailed Answers for Interview Preparation Guide

## 1. Java
### a) Core Language
- **Question:** Explain the difference between `==` and `.equals()` for objects. When would you override `equals()` and `hashCode()`?
- **Answer:** `==` checks for reference equality – whether two references point to the exact same object in memory. `.equals()` is a method that, by default in `Object`, also checks reference equality, but many classes (e.g., `String`, `Integer`, collections) override it to provide logical equality based on the object's state. You override `equals()` when logical equality is needed (e.g., two `User` objects with the same `id` should be considered equal). When overriding `equals()`, you must also override `hashCode()` so that equal objects produce the same hash code, ensuring correct behavior in hash‑based collections like `HashMap` and `HashSet`.

- **Scenario Diagnosis:** Duplicate keys in a `HashMap<User, Integer>` usually mean `User.equals()` is not overridden correctly or `hashCode()` is inconsistent, causing the map to treat distinct `User` instances as different keys. Implement proper `equals()`/`hashCode()` based on immutable identifier fields.

### b) Concurrency
- **Question:** How does `volatile` differ from `AtomicInteger`? When would you choose one over the other?
- **Answer:** `volatile` guarantees visibility of writes to other threads – a read always sees the latest write, but it does **not** provide atomic read‑modify‑write operations. `AtomicInteger` provides atomic operations like `incrementAndGet()` that combine read‑modify‑write safely without external synchronization. Use `volatile` for simple flags or when you only need visibility. Use `AtomicInteger` when you need atomic increments, decrements, or compare‑and‑set semantics.

- **Scenario Diagnosis:** `ConcurrentModificationException` often occurs when a collection is modified while iterating without proper synchronization. Replace the collection with a thread‑safe variant (`CopyOnWriteArrayList`, `ConcurrentHashMap`) or synchronize the iteration, or use streams that handle concurrency safely.

### c) Streams & Lambdas
- **Question:** What are the characteristics of a *parallel* stream? Explain potential pitfalls.
- **Answer:** Parallel streams split the data source into multiple sub‑streams processed concurrently using the ForkJoinPool common pool. They can speed up CPU‑bound operations but introduce overhead, non‑deterministic ordering, and thread‑safety concerns. Pitfalls include mutable shared state, reliance on order, and operations that are not thread‑safe (e.g., `ArrayList.add`). Use `parallel()` only when the operation is stateless, side‑effect‑free, and the data size justifies the overhead.

- **Scenario Refactor:** To safely convert a legacy loop that builds `Map<String, List<Item>>`, you can use:
```java
Map<String, List<Item>> result = items.stream()
    .collect(Collectors.groupingBy(Item::getCategory,
        Collectors.toList()));
```
If parallelism is desired, ensure the downstream collector is thread‑safe (`groupingByConcurrent`).

---

## 2. REST API
### a) Design Principles
- **Question:** What are the benefits of using proper HTTP status codes? Give examples of when to return `400`, `404`, `409`, and `500`.
- **Answer:** Proper status codes convey intent to clients, enable generic error handling, and improve API discoverability. `400 Bad Request` – malformed request syntax or validation failure. `404 Not Found` – resource does not exist. `409 Conflict` – request conflicts with current state (e.g., duplicate username). `500 Internal Server Error` – unexpected server error not caused by client input.

- **Scenario Improvement:** Returning `200 OK` with an error payload hides the error nature from HTTP clients, prevents automatic retry logic, and violates REST semantics. Instead, map business errors to appropriate 4xx codes and include a structured error body.

### b) Versioning & Pagination
- **Question:** How would you version a public API without breaking existing clients?
- **Answer:** Use URI versioning (`/api/v1/...`) or header versioning (`Accept: application/vnd.myapp.v1+json`). Keep older versions stable, deprecate gradually, and provide migration guides.

- **Scenario Pagination:** Implement cursor‑based pagination:
```json
GET /orders?cursor=abc123&limit=20
```
Response includes `nextCursor` and `previousCursor` fields. This avoids offset issues with large datasets.

### c) Security
- **Question:** Explain the difference between OAuth2 *client credentials* and *authorization code* flows.
- **Answer:** Client credentials flow is for machine‑to‑machine communication; the client authenticates itself and receives an access token without user involvement. Authorization code flow involves a user authenticating via a browser, the server returns an authorization code, which the client exchanges for a token – suitable for delegated user access.

- **Scenario Key Rotation:** Use a JWKS endpoint that publishes current public keys. Configure the JWT validator to fetch keys dynamically and cache them with a short TTL, allowing seamless rotation.

---

## 3. Micro‑services
### a) Architecture
- **Question:** What are the trade‑offs between synchronous HTTP calls and asynchronous messaging?
- **Answer:** Synchronous calls provide immediate response and simpler flow but increase coupling and latency; failures propagate quickly. Asynchronous messaging decouples services, improves resilience and scalability, but adds eventual consistency, complexity, and requires handling message ordering and duplication.

- **Scenario Redesign:** Replace the 5‑hop chain with an event‑driven approach: Service A publishes an `OrderCreated` event; downstream services react to the event, perform their work, and emit further events. Use a saga orchestrator or compensation actions for failures.

### b) Resilience Patterns
- **Question:** Describe the Circuit Breaker pattern and its typical states.
- **Answer:** Circuit Breaker prevents cascading failures by short‑circuiting calls to a failing service. States: **Closed** (calls flow normally), **Open** (calls fail fast), **Half‑Open** (limited test calls to see if service recovered). After successful calls, it transitions back to Closed.

- **Scenario Implementation:** Using Resilience4j:
```java
CircuitBreakerConfig config = CircuitBreakerConfig.custom()
    .failureRateThreshold(50)
    .waitDurationInOpenState(Duration.ofSeconds(30))
    .build();
CircuitBreaker breaker = CircuitBreaker.of("serviceB", config);
Supplier<String> decorated = CircuitBreaker.decorateSupplier(breaker, () -> restTemplate.getForObject(...));
```
Provide a fallback method that returns cached data or a default response.

### c) Data Consistency
- **Question:** Explain the concept of *eventual consistency*.
- **Answer:** Eventual consistency guarantees that, given no new updates, all replicas will converge to the same state eventually. It trades immediate consistency for availability and partition tolerance.

- **Scenario Solution:** Use optimistic locking with a version field (`@Version` in JPA) or implement a saga pattern where each service records its step and compensates if later steps fail.

---

## 4. Spring Boot
### a) Configuration
- **Question:** How does Spring Boot’s `@ConfigurationProperties` differ from `@Value`?
- **Answer:** `@ConfigurationProperties` binds a group of properties to a POJO, supporting type‑safe hierarchical configuration and validation. `@Value` injects a single property value using SpEL, less suited for complex structures.

- **Scenario Externalization:** Create `application-dev.yml` and `application-prod.yml`. Activate via `--spring.profiles.active=dev` or `SPRING_PROFILES_ACTIVE` env var. Use `@ConfigurationProperties(prefix="myapp")` to map properties.

### b) Dependency Injection
- **Question:** What is the difference between `@Component`, `@Service`, and `@Repository`?
- **Answer:** All are stereotypes for component scanning. `@Component` is generic. `@Service` indicates business logic layer; `@Repository` adds DAO exception translation.

- **Scenario Diagnosis:** Bean not injected could be due to missing component scan (package not covered), multiple constructors without `@Autowired`, or proxying issues (e.g., using `@Transactional` on a private method). Verify `@SpringBootApplication` scan base packages and ensure a single public constructor.

### c) Testing
- **Question:** How would you write an integration test for a REST controller that hits the database?
- **Answer:** Use `@SpringBootTest` with `@AutoConfigureMockMvc` and an embedded H2 database. Load test data via `@Sql` or `TestEntityManager`. Example:
```java
@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerIT {
    @Autowired MockMvc mockMvc;
    @Test void shouldCreateOrder() throws Exception {
        mockMvc.perform(post("/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"item\":\"book\"}"))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").exists());
    }
}
```

---

## 5. Kafka
### a) Core Concepts
- **Question:** What is the difference between *at‑least‑once* and *exactly‑once* delivery semantics?
- **Answer:** At‑least‑once may deliver duplicates (producer retries, consumer re‑processing). Exactly‑once guarantees each record is processed once, typically using idempotent producers and transactional consumers.

- **Scenario Idempotency:** Store processed message IDs in a database and check before processing, or use Kafka Streams with exactly‑once processing (`processing.guarantee=exactly_once_v2`).

### b) Partitioning & Ordering
- **Question:** How does Kafka ensure ordering within a partition?
- **Answer:** Records within a partition are written sequentially and assigned incremental offsets. Consumers read in offset order, preserving order.

- **Scenario Key Strategy:** Use a composite key like `userId` to ensure all events for a user go to the same partition, while adding a hash of another attribute to distribute load evenly.

### c) Consumer Groups & Offsets
- **Question:** Explain how consumer group rebalancing works.
- **Answer:** When members join or leave, the coordinator redistributes partitions among consumers. During rebalance, processing pauses, and offsets may be committed. Improper configuration can cause duplicate processing or latency spikes.

- **Scenario Tuning:** Increase `max.poll.interval.ms` to allow longer processing time, reduce `session.timeout.ms`, and enable `enable.auto.commit=false` with manual commits after successful processing.

---

## Mindset Checklist for Candidates (Answers Recap)
- **Problem‑solving**
  - Understand requirements
    - Break down scenario into steps
- **Trade‑off analysis**
  - Identify alternatives
    - Compare pros/cons
- **Debugging approach**
  - Reproduce issue
    - Gather logs/metrics
- **Best practices**
  - SOLID principles
    - Single Responsibility, Open/Closed, Liskov Substitution, Interface Segregation, Dependency Inversion
- **Communication**
  - Clear articulation
    - Tailor depth to audience

### SOLID Principles
- **Question:** What are the five SOLID principles and why are they important in software design?
- **Answer:** 
  - **S**ingle Responsibility: a class should have one reason to change.
  - **O**pen/Closed: software entities should be open for extension but closed for modification.
  - **L**iskov Substitution: objects of a superclass should be replaceable with objects of a subclass without affecting correctness.
  - **I**nterface Segregation: many client‑specific interfaces are better than one general‑purpose interface.
  - **D**ependency Inversion: depend on abstractions, not concretions.

---

*Use this document to study detailed answers, deepen your understanding, and practice explaining concepts with confidence.*
