# Operations & Modern Architecture – Deep Dive

**Target Audience:** Principle Architect / Director of Engineering.
**Focus:** Operational Excellence, SRE Culture, deeply descriptive descriptive answers (no one-liners).
**Tone:** Conversational, Experienced, Honest. "War stories" over textbook definitions.

---

## 1. Modern Software Architecture & Service Design

### Q1: We have a massive monolithic e-commerce application that is becoming impossible to deploy. Describe your strategy for decomposing this into a Service-Oriented Architecture (SOA) or Microservices. How do you decide what becomes a service?
**Answer:**
"I wouldn't just start hacking away at the code. The first looking I look at is the **organization structure**, not the codebase. Conway's Law is real – if we have 50 people working on one database, we have a communication problem, not just a software problem.

My strategy is 'Strangler Fig'. We don't rewrite. We build *new* features as separate services (e.g., a new Review system) and route traffic to them. For existing code, identifying 'Bounded Contexts' is the hardest part. I look for data ownership. If the 'Order' table is touched by 10 different modules, that's our knot. I would start by decoupling the database first—creating a logical API for 'Inventory' even if it's still inside the monolith—before physically extracting it."

#### Depth 1
**Q:** You mentioned decoupling the database first. What happens when two services need to join data (e.g., User + Orders) for a report?
**A:** "In a monolith, we just write a SQL JOIN. In microservices, we lose that luxury. I strongly advise **against** distributed joins (making HTTP calls in a loop). Instead, I'd use **Data Composition** at the API Gateway or Frontend layer for simple screens. For complex reports, we need to replicate data. We use an event stream (Kafka) where the 'User' service publishes updates, and the 'Order' service (or a Reporting service) consumes them and updates its own local read-optimized copy. It’s eventual consistency, but it's fast."

##### Depth 2
**Q:** Eventual consistency sounds risky for Inventory. What if I sell the last item twice?
**A:** "That is the classic 'Over-selling' problem. We have two choices: Pessimistic locking (Distributed Lock) which kills performance, or Optimistic concurrency. I prefer **Optimistic**. We let the order go through. Then, during the fulfillment process, if we catch the double-sell, we trigger a 'Compensating Transaction'—we apologize to the user and issue a refund + coupon. It’s a business decision, not just technical. Amazon would rather take the order and fix it later than block the checkout button."

###### Depth 3
**Q:** How do you handle a "Distributed Transaction" that spans Payment, Inventory, and Shipping services?
**A:** "We use the **Saga Pattern**. It's like a relay race. Payment Service charges the card. If successful, it emits an event. Inventory Service hears it and reserves the item. If Inventory fails (out of stock), it emits a 'Failed' event. The Payment Service listens for that and issues a refund. I prefer **Orchestration** (a central brain, like AWS Step Functions or a custom process manager) over Choreography (services talking nicely to each other) because with Choreography, it gets very hard to visualize 'where is the order stuck?' after you have 20 services."

###### Depth 4
**Q:** Managing 50 services means 50 deploy pipelines. How do you prevent "Configuration Drift"?
**A:** "I treat Infrastructure as Code (IaC) religiously. No one SSHs into a box. If a server is misbehaving, we shoot it; we don't fix it. Use Terraform or Helm charts. Ideally, we move to **GitOps** (ArgoCD). The state of the cluster must strictly match what is in the Git repo. If someone manually changes a connection string in Production, the GitOps agent should detect the drift and overwrite it back immediately. It forces discipline."

###### Depth 5
**Q:** What is your strategy for "Service Discovery" when services spin up and down dynamically?
**A:** "In the old days, we updated DNS or HAProxy configs. Now, inside Kubernetes, Kube-DNS handles it. But deeper than that, I look at **Client-Side Load Balancing**. The client (calling service) knows the list of available IP addresses (via a sidecar like Envoy or a library). This allows the client to be smart—'This IP keeps giving me 500 errors, I will blacklist it locally for 1 minute'. A central load balancer creates a bottleneck and a single point of failure."

###### Depth 6
**Q:** How do you test this distributed mess? Integration testing seems impossible.
**A:** "You are right, spinning up 50 microservices on a developer laptop to run integration tests is a nightmare and a lie—it never matches Prod. I push for **Consumer-Driven Contract Testing** (like Pact). The 'Order' team defines a contract: 'I expect the User API to return JSON like `{id: int}`'. The User team’s CI pipeline runs a mock test against that contract. If they break the contract, their build fails. We catch breaking changes at compile time, not runtime. It saves us from needing a massive staging environment."

###### Depth 7
**Q:** Talk to me about "Circuit Breakers". Why do we strictly need them?
**A:** "Without them, one slow service kills the whole platform. Imagine the 'Recommendation Service' gets slow. The 'Product Page' calls it. The Recommendation service hangs for 30 seconds. Now all the Product Page threads are blocked waiting. Soon, the web server runs out of threads. The whole site goes down just because Recommendations are slow. A Circuit Breaker detects the timeouts, 'trips' the circuit, and immediately returns a dummy response (or empty list) so the Product Page can still load quickly. It degrades functionality to save availability."

###### Depth 8
**Q:** How do you handle "Secret Management" (DB passwords) in this architecture?
**A:** "Never commit secrets to Git. Not even encrypted if we can avoid it. I use a dedicated Vault (HashiCorp Vault or AWS Secrets Manager). The app boots up, authenticates with its own Identity (IAM Role / ServiceAccount), and asks the Vault for the password. ideally, we use **Dynamic Secrets**—the Vault generates a temporary SQL user/password just for that container and revolves it every hour. If an attacker dumps the config, the credentials are already useless."

###### Depth 9
**Q:** What is the "Sidecar Pattern" and is it worth the resource cost?
**A:** "It involves running a helper container (like Envoy or a log shipper) in the same Pod as the app. It intercepts all network traffic. It handles retry logic, TLS termination, and metrics collection. Is it worth it? For a team of 5? No. For an organization of 100? Yes. It decouples the operational concerns from the code. Developers don't need to import 'Retry libraries'. I can upgrade the Sidecar to fix a TLS bug without asking 50 teams to recompile their Java apps."

###### Depth 10
**Q:** How do you decide between REST, gRPC, and GraphQL?
**A:** "REST is for public APIs—it's cacheable, human-readable, and tooling is everywhere. gRPC is for internal service-to-service—it uses Protobuf (binary), so it's much smaller and faster to parse, plus it generates code for us (type safety). GraphQL is for the Frontend—it solves the 'Over-fetching' problem where a Mobile app has to call 5 APIs to build one screen. I usually put a GraphQL Aggregation layer in front of my backend REST/gRPC services."

###### Depth 11
**Q:** How do you implement "feature flags" at scale without technical debt?
**A:** "Feature flags are basically 'if-statements' in the code checking a remote config. The debt comes when we forget to remove them. I mandate an 'Expiration Date' on flags. If a flag 'EnableNewCheckout' is 3 months old, the build should warn us. Also, flags allow **Canary Deployments**—I can turn on the new feature for just internal employees first, then 1% of users, then 50%. It detaches 'Deployment' (moving code) from 'Release' (showing features)."

###### Depth 12
**Q:** Explain "Idempotency Keys" in API design. Why are they critical for payments?
**A:** "The network is unreliable. If a client sends a 'Pay $50' request and times out, they don't know if the server got it. They retry. If we aren't careful, we charge them twice. An Idempotency Key is a unique UUID generated by the client (`request-123`). The server stores this key. If it sees `request-123` again, it checks the database: 'Oh, I already processed this. Here is the cached success response.' It safeguards against retries."

###### Depth 13
**Q:** How does "Backpressure" work in a system under heavy load?
**A:** "It's the polite way of saying 'I am full'. If a Service A is sending data to Service B faster than B can process, B acts like a buffer. Eventually, B's memory fills up. Backpressure is the mechanism where B signals A to slow down (e.g., TCP Window closing, or Reactive Streams `request(n)`). If we don't handle this, B will crash (OOM). It's better to drop traffic (Load Shedding) or slow down than to crash."

###### Depth 14
**Q:** Describe the "Outbox Pattern" for reliable messaging.
**A:** "We often need to 'Save to DB' and 'Publish to Kafka'. If we save DB but fail to publish, we have inconsistency. The Outbox Pattern says: Save the Kafka message *into the same database transaction* as the data (in a table called `Outbox`). Then, a separate process (Connector) reads the `Outbox` table and pushes to Kafka. Since DB writes are atomic, we guarantee we never lose the message."

###### Depth 15
**Q:** How do you perform "Zero Downtime Database Migrations" (e.g., renaming a column)?
**A:** "We can never just rename a column; that breaks the running code. It's a 4-step process:
1.  **Add** the new column (nullable).
2.  **Double-Write**: Update code to write to *both* old and new columns, but read from old. Backfill old data to new column.
3.  **Switch Read**: Update code to read from new column.
4.  **Stop Write/Delete**: Remove old column.
It takes weeks, but it's the only safe way."

###### Depth 16
**Q:** What is "Domain-Driven Design" (DDD) and how does it map to Microservices?
**A:** "DDD is about language. We sit with business experts and agree on what a 'Customer' actually is. In the 'Sales Context', a Customer is a lead. In 'Support Context', a Customer is a ticket owner. These are two different Bounded Contexts. Microservices should align with these Bounded Contexts. If we try to make one single 'Universal Customer Service' that does everything, we just rebuilt the monolith. We want loose coupling."

###### Depth 17
**Q:** How do you handle "Zombie Processes" in containers?
**A:** "In Linux, process ID 1 (PID 1) has special responsibilities—it must 'reap' orphaned child processes (zombies). If our app starts a shell script which starts the app, the shell script might be PID 1. If it doesn't handle signals correctly, zombies pile up and eat the process table. We use `tini` or `--init` in Docker to ensure a proper init process handles signal forwarding (SIGTERM) and reaping."

---

## 2. Operations, Monitoring & Incident Management

### Q2: The CEO calls you: " The site feels slow." Dashboards look green. How do you debug this? Walk me through your mental model from browser to kernel.
**Answer:**
"Dashboards looking green just means we aren't measuring the right thing. 'Feels slow' usually means latency tail (p99) or frontend rendering issues.

I start at the **User Experience (RUM)**. I check the browser network tab or our RUM tools (Datadog/New Relic) to see 'Time to First Byte' vs 'DOM Interactive'. If TTFB is fast, the slowness is massive JavaScript bundles or bad rendering.

If TTFB is slow, it's the backend. I look at the **Load Balancer**. Is it queueing? Are there 5xx errors? Then I jump to **APM Traces**. I look for 'Spans' that are unusually long. Is it the DB Query? An external API call?

If the code looks fine but runs slow, I go to the **System Resources**. Is CPU saturated? High Load Average? Is the Database locking? I work down the stack until I find the constrained resource."

#### Depth 1
**Q:** The CPU is low (10%), but "Load Average" is high (50). What does that indicate?
**A:** "Load Average counts tasks waiting for CPU *or* I/O. If CPU use is low, it means processes are stuck in `D state` (Uninterruptible Sleep), waiting on Disk I/O or Network File System. I would run `iotop` or `iostat` immediately. It's likely a bad disk or a saturated EBS volume queue."

##### Depth 2
**Q:** You find the Database CPU is at 100%. How do you identify the culprit query without crashing the DB?
**A:** "I look at `pg_stat_activity` (Postgres) or `SHOW FULL PROCESSLIST` (MySQL). I don't just look for long-running queries; I look for a *high frequency* of fast queries (Death by 1000 cuts). I check for 'Full Table Scans'—queries missing an index. If strictly necessary, I kill the query, but usually, I check if an index deployment failed."

###### Depth 3
**Q:** Explain "The Golden Signals" of monitoring. Why these four?
**A:** "Google SRE book standard:
1.  **Latency**: How long it takes. (Differentiate success vs error latency).
2.  **Traffic**: Demand on the system (RPS).
3.  **Errors**: Rate of failed requests (5xx).
4.  **Saturation**: How 'full' the system is (Memory, Queue Depth).
These 4 give a complete health picture. Saturation is the leading indicator of imminent failure; Errors are the trailing indicator."

###### Depth 4
**Q:** How do you define "Severity Levels" (SEV1 vs SEV3) for incidents?
**A:** "It must be objective, based on Business Impact, not technical severity.
*   **SEV-1 (Critical)**: Company is losing money *right now*. Checkout broken. Data corruption. All hands on deck.
*   **SEV-2 (Major)**: Core feature broken (Can't upload profile pic), workaround exists, or performance severe degradation. Fix today.
*   **SEV-3 (Minor)**: Annoyance, internal tool down, or bug affecting <1% users. Fix in normal sprint.
Clear definitions prevent 'Alert Fatigue' where everything is urgent."

###### Depth 5
**Q:** Walk me through a "Blameless Post-Mortem". Why is 'Blameless' important?
**A:** "If engineers fear punishment, they will hide details. We want the truth so we can fix the *system*.
We ask 'What', 'How', and 'Why'.
*   *What happened?* DB crashed.
*   *Why?* It ran out of memory.
*   *Why?* A bad query loaded 10GB data.
*   *Why?* The app didn't paginate.
*   *Root Cause:* Missing pagination safeguards in framework.
*   *Action Items:* Add lint rule for pagination. Add DB memory alert.
We focus on process: 'How did the system allow a human to make this mistake?'"

###### Depth 6
**Q:** What is an "Error Budget" and how do usage it to negotiate with Product Managers?
**A:** "We agree on an SLO (Service Level Objective), say 99.9% availability. That means we can be down 43 minutes a month. That is our Budget. If we have an outage and burn 30 minutes, we have 13 minutes left. If we burn it all, the policy triggers: **We freeze feature releases**. The team *must* work on Reliability tasks until the budget resets. It turns 'Reliability' from an abstract concept into a tangible currency that Product Managers understand."

###### Depth 7
**Q:** How do you debug a "Memory Leak" in a Java Application running in Production?
**A:** "I check the Garbage Collection (GC) logs first. Is the Heap usage 'saw-toothing' correctly, or is the baseline rising over time until OOM? If it's rising, I take a **Heap Dump**. I can't do this during peak traffic (it pauses the JVM). I analyze the Dump in Eclipse MAT, looking for 'Dominator Tree'—usually, it's a static Map/List cache that grows forever without eviction."

###### Depth 8
**Q:** Explain "observability cardinality" and why it blows up our Datadog bill.
**A:** "Cardinality is the number of unique values for a metric tag. If we tag a metric `request_duration` with `user_id`, and we have 1 million users, we create 1 million unique time-series data points. Monitoring tools charge by custom metric count. It explodes instantly. I enforce strict rules: No high-cardinality data (UUIDs, Emails) in Metric Tags. Put them in Logs or Traces instead."

###### Depth 9
**Q:** How do you tune the Linux Kernel for a high-throughput TCP web server?
**A:** "Defaults are for general purpose. For high-concurrency:
1.  Increase `fs.file-max` (File Descriptors) – 'Too many open files' error is common.
2.  Enable `tcp_tw_reuse` – Allow reusing sockets in TIME_WAIT state to prevent port exhaustion.
3.  Increase `net.core.somaxconn` – The backlog queue for pending connections. If full, users get 'Connection Refused' before we even accept the request."

###### Depth 10
**Q:** What is "Context Switching" and how does it affect performance?
**A:** "The CPU can only run one thread per core at a time. To multitask, it pauses Thread A, saves its state (registers), loads Thread B, and runs it. This switch takes microseconds. If we spawn 20,000 threads for 20,000 requests (Thread-per-request model), the CPU spends more time *switching* than working. This is why Non-Blocking I/O (Node.js/Netty) is better for high-concurrency—it uses few threads and avoids switching."

###### Depth 11
**Q:** How do you handle "Log Rotting" (Logs filling the disk) and "Log Loss"?
**A:** "Application should never write to a file. It should write to `STDOUT`. The container engine (Docker) captures it. Then a log shipper (Fluentd/Filebeat) reads the Docker json-file, buffers it, and sends to Elastic/Splunk.
*   **Disk Full:** Configure Docker log rotation (`max-size: 10m`, `max-file: 3`).
*   **Log Loss:** If the shipper is slow, where do logs go? They fill the buffer. If buffer fills, we either block the app (bad) or drop logs (acceptable). I prefer dropping logs over crashing the app."

###### Depth 12
**Q:** Describe a "Game Day" or Disaster Recovery simulation.
**A:** "We don't trust backups until we restore them. A Game Day is a scheduled drill. We pick a scenario: 'Primary East Region is down'. We gather in a room. We actually cut the connection (or simulate it). We verify:
1.  Did alerts fire?
2.  Did traffic failover?
3.  Did the RTO (Recovery Time Objective) meet the SLA?
We often find broken runbooks or expired credentials during these drills."

###### Depth 13
**Q:** How do you monitor "Asynchronous Queues" (Kafka/SQS) effectively?
**A:** "Monitoring the 'Consumer' CPU isn't enough. We must monitor **Consumer Lag**. How far behind is the consumer? If Lag is growing, we are falling behind. But Lag alone is tricky—if traffic stops, Lag stops growing but doesn't drop. I also monitor 'Oldest Message Age'. If a message has sat in the queue for 30 minutes in a real-time system, we are effectively down."

###### Depth 14
**Q:** What is "False Positive" vs "False Negative" in alerting, and which is worse?
**A:** "False Positive: Pager goes off, but nothing is wrong. Causes 'Alert Fatigue'—engineer ignores the next real alert.
False Negative: Site is down, but pager is silent.
**False Negative is worse** for the business immediately. But False Positives destroy the *team's ability* to respond long-term. I aggressively delete flaky alerts. If it pages me and I don't need to do anything, it shouldn't be a page."

###### Depth 15
**Q:** How do you maintain "SRE Culture" in a team of feature developers?
**A:** "I don't hire a separate 'SRE Team' to fix devs' bugs. That creates a 'throw over the wall' mentality. I embed SRE principles: 'You build it, you run it'. Developers go on-call for their own services. Nothing incentivizes writing stable code faster than being woken up at 3 AM by your own bug. My role as SRE is to build the *platforms* and *tooling* to make it easy for them to run it safely."

###### Depth 16
**Q:** Explain the "CAP Theorem" operational trade-offs during a network partition.
**A:** "The Partition (P) happened. The cable is cut. We must choose:
*   **AP (Availability):** Let users keep writing to both sides. Data drifts apart. We deal with conflicts later (Merge). Good for Shopping Carts.
*   **CP (Consistency):** Lock the system. Return errors until the cable is fixed. Critical for Bank Balances.
Operationally, deciding this *during* an incident is impossible. It must be a pre-decided architectural constraint. Most web apps choose Availability."

###### Depth 17
**Q:** How do you benchmark a system's "Capacity" before a marketing launch?
**A:** "Load Testing is usually wrong because it tests the 'Happy Path'. I do **Stress Testing**. I ramp up traffic until the system breaks. I want to know the *Point of Failure*. Is it 10k RPS? 50k? And *how* does it fail? Does it slow down gracefully, or crash hard? I verify that Auto-Scaling triggers *before* the crash point (accounting for spin-up latency)."

---

*Use this guide to drive deep operational conversations. The difference between a Senior Engineer and a Principal is often just the number of production outages they have survived and learned from.*
