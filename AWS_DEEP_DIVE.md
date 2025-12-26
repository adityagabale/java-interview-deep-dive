# AWS Cloud Architecture – Principal/IC Level Deep Dive

**Target Audience:** 17+ Years Experience, Principal Architect / Fellow.  
**Focus:** Simplicity, Organizational Impact, Deep Internals (Kernels/Hardware), and Cost Efficiency.  
**Philosophy:** "Any intelligent fool can make things bigger and more complex. It takes a touch of genius – and a lot of courage – to move in the opposite direction."

---

**How to use this document**
- Each topic starts with a bookish baseline question, then progressively deeper follow-ups (Depth 1..N).
- Each answer expands jargon the first time it appears (short refresher in-line).
- Assume a payments platform (switch/RTP) and a Principal Architect interviewer.
- Pause anywhere: treat each Q/A as a standalone interview card.

---

## 1. Global Resilience & Networking

### Q1: Design a global, active-active Payment Switch on AWS for a bank. How do you achieve 99.999% availability while adhering to strict GDPR/Data Residency laws?
**Answer:**  
**Executive intent (Principal-level):** I design this as **multi-region, cell-based** architecture where each region can run independently, and I enforce **data residency by partitioning data by legal boundary** (EU vs US) rather than trying to replicate everything everywhere.

**Baseline architecture (what I would draw on the whiteboard):**
- **Global ingress:** **AWS Global Accelerator (GA)** = Anycast static IPs announced over **Border Gateway Protocol (BGP)** from AWS edge locations, steering traffic to the closest healthy region.
- **Regional entry:** **Network Load Balancer (NLB)** / **Application Load Balancer (ALB)** in each region, fronting the switch APIs.
- **Compute:** **Elastic Kubernetes Service (EKS)** or **Elastic Container Service (ECS)** for always-on low-latency services (payments switch core). Use Lambda only for non-core async utilities.
- **Data residency:** **Two (or more) data planes**:
    - **EU plane:** all EU **Personally Identifiable Information (PII)** + EU-ledger partitions stored and processed only in EU regions.
    - **US plane:** all US PII + US-ledger partitions stored and processed only in US regions.
- **Cross-plane connectivity:** Only **non-PII** and **regulatory-allowed aggregates** cross boundaries (e.g., anonymized metrics, settlement summaries), via event streams.

**Why this meets 99.999%:**
- 99.999% requires eliminating single-region dependencies. Each region is a **Cell** (independent capacity + independent failure domain). A region outage becomes a routing event, not a business outage.

**Data-store options (not just DynamoDB):**
- **DynamoDB (NoSQL key-value/document DB):** predictable single-digit ms, partition-key based scaling. Optional **Global Tables** for multi-region replication (active-active), but only within allowed jurisdictions.
- **Aurora PostgreSQL (managed relational, MySQL/Postgres-compatible):** strong transactional semantics; optional **Aurora Global Database** (primary + cross-region read replicas with fast replication) for DR and read locality.
- **RDS PostgreSQL (managed Postgres):** cheaper/simpler, but multi-region replication and failover are more manual than Aurora Global.
- **Kafka (MSK) + regional databases:** event-sourced replication where **the event stream is replicated** but **PII events are filtered** per boundary.

**GDPR / residency enforcement (how I make it hard to violate):**
- **Routing gate:** determine residency at ingress (token claims / client profile) and route to the correct regional endpoint.
- **Data plane separation:** separate AWS accounts/VPCs per jurisdiction; **Service Control Policies (SCPs)** deny data services in non-approved regions.
- **Cryptographic controls:** **Key Management Service (KMS)** keys are region-scoped; sensitive tables encrypted with jurisdiction-specific keys.

#### Depth 1
**Q:** How do you handle "split brain" when inter-region connectivity is partially down?  
**A:** I avoid global shared-write state. Each region is a **Cell** with a local write authority for its own residency partition. If the backbone is cut, EU continues EU payments; US continues US payments. Cross-region reconciliation becomes **async** and bounded.

##### Depth 2
**Q:** Why Global Accelerator over Route 53 latency routing?  
**A:** Route 53 is DNS-based and depends on client/ISP caching (time-to-live, **TTL**). GA uses **Anycast IPs** and steers traffic at the AWS edge via BGP—failover happens in seconds without waiting for DNS caches.

###### Depth 3
**Q:** Refresher: What exactly is DynamoDB Global Tables?  
**A:** **Global Tables** replicate a DynamoDB table across multiple regions in a multi-writer model. Writes in one region propagate to others automatically. Conflict resolution is typically **last-writer-wins** (timestamp-based), which is fine for some metadata but dangerous for balances.

###### Depth 4
**Q:** If Global Tables are last-writer-wins, how do you avoid corrupting balances?  
**A:** I do not use LWW replication for ledger state. I use **write affinity** (a home region per legal partition) and replicate **events** or **read-only copies** across regions. For true financial state, I keep a single write authority per partition and treat everything else as derived.

###### Depth 5
**Q:** Can Aurora do something like Global Tables?  
**A:** Aurora uses **Aurora Global Database**, not Global Tables. It’s typically **one primary writer region** with fast cross-region replication to read replicas. It’s excellent for DR and read-locality, but it’s not a multi-writer active-active ledger by default.

###### Depth 6
**Q:** How do you replicate only where permitted (EU stays EU, US stays US)?  
**A:** I design **separate clusters** per jurisdiction and replicate only within that jurisdiction (EU-West ⇄ EU-Central, US-East ⇄ US-West). Cross-jurisdiction replication is restricted to **non-PII** event topics or aggregated datasets, enforced by topic-level policies and data classification.

###### Depth 7
**Q:** Deep dive: How does Global Accelerator work at packet level?  
**A:** GA advertises Anycast IPs from many edge PoPs. The client hits the closest PoP; then traffic rides the AWS private backbone to the chosen region endpoint, reducing public-internet jitter. Operationally: you get fast health-based steering without DNS.

###### Depth 8
**Q:** What is the BGP hijack risk and practical mitigations?  
**A:** In BGP hijack, an attacker/AS announces routes it doesn’t own. Mitigations include **route origin validation (RPKI/ROA)** on the internet side and defense-in-depth on the application side (mTLS, certificate pinning, WAF, and anomaly detection).

###### Depth 9
**Q:** Design a "break glass" mechanism if IAM federation is down or compromised.  
**A:** Use a separate security account with tightly controlled emergency roles and hardware MFA. Pre-provision runbooks for isolating compromised principals, rotating keys, and disabling federation, with SCPs to prevent privilege escalation.

###### Depth 10
**Q:** How do you enforce zero-trust service-to-service inside a VPC?  
**A:** I use **mTLS** via a service mesh (Istio/Envoy) or AWS-native controls (VPC Lattice where appropriate). IPs are not identity; certificates are. Authorization is policy-based (SPIFFE/SPIRE style identity is a common approach).

###### Depth 11
**Q:** Transit Gateway vs VPC Peering for 500 VPCs?  
**A:** Peering is non-transitive and becomes N^2. **Transit Gateway (TGW)** is hub-and-spoke (N) and is operationally simpler. I accept TGW data processing costs in exchange for drastically reduced routing complexity and clearer blast radius.

###### Depth 12
**Q:** How do you detect DNS tunneling / exfiltration?  
**A:** Enable Route 53 Resolver query logging, GuardDuty DNS findings, and DNS Firewall policies. I look for high-entropy subdomains, abnormal query volume, and unusual NXDOMAIN patterns.

###### Depth 13
**Q:** Latency impact of TLS 1.3 for low-latency services?  
**A:** Symmetric crypto is cheap with modern CPUs; the expensive part is the handshake. I use session resumption, keep-alives, and minimize new connections. For internal traffic, I keep connections warm via Envoy pools.

###### Depth 14
**Q:** Refresher: What is the AWS Nitro System and why does it matter?  
**A:** Nitro offloads virtualization, networking, and storage to dedicated hardware, reducing noisy-neighbor effects and improving isolation. Practically, it gives better performance consistency—important for p99 latency.

###### Depth 15
**Q:** Chaos engineering at the network layer—what do you actually run?  
**A:** AWS Fault Injection Simulator (FIS) experiments that disrupt connectivity (subnet/route/instance impairment) while watching GA reroute, regional cells degrade gracefully, and SLOs remain within error budget.

---

## 2. Serverless & Event-Driven Scalability

### Q2: Scale a Serverless "Black Friday" Order Processing system to 100,000 TPS. Costs must remain linear.
**Answer:**  
**Executive intent (Principal-level):** I make throughput predictable by **decoupling ingest from processing** and by enforcing **bounded concurrency**, so cost scales with *work done* rather than with *traffic spikes*.

**Baseline architecture (what I would draw on the whiteboard):**
- **Ingress:** Amazon API Gateway (**APIGW**) → Amazon Simple Queue Service (**SQS**) (buffer) → AWS Lambda (batch consumer)
- **Workflow orchestration:** AWS Step Functions **Express** (high-volume, short-lived workflows)
- **Event fan-out:** Amazon EventBridge (content-based routing) or Amazon Simple Notification Service (SNS) for simple pub/sub
- **Persistence:**
    - DynamoDB (NoSQL key-value/document DB) for idempotency + fast state
    - Amazon Aurora / Amazon RDS (relational DBs) for strict transactional boundaries
- **Observability:** Amazon CloudWatch + AWS X-Ray (distributed tracing)

**How linear cost is achieved:**
- **Queue as shock absorber:** SQS smooths bursts; Lambda concurrency becomes the control knob.
- **Batching:** Multiple messages per invocation amortize overhead.
- **Idempotency:** Retries are expected; correctness must not multiply cost.
- **Back-pressure:** When downstream slows, processing rate is bounded.

**Explicit trade-offs:**
- APIGW adds cost per request but simplifies throttling/auth; Lambda Function URLs are cheaper for trusted internal traffic.
- Step Functions Express are cost-effective for short-lived flows; Standard is better for long waits/humans.

#### Depth 1
**Q:** Why Amazon Simple Queue Service (SQS) between Amazon API Gateway (APIGW) and Lambda? Why not direct invocation?  
**A:** **Throttling protection**. 100k TPS directly to Lambda hits concurrency limits. SQS acts as a shock absorber. Lambda polls SQS at a manageable max concurrency.

##### Depth 2
**Q:** How do you handle "Lambda Cold Starts" for Java applications?  
**A:** Java runtimes are slower to initialize because the **Java Virtual Machine (JVM)** must start and warm up.
1. **SnapStart**: snapshot restore (boots in ms for supported runtimes).
2. **Provisioned Concurrency**: keep instances warm (expensive).
3. **GraalVM**: native image (fast startup, more build complexity).  
   **Simplicity:** use Node.js/Go for latency-sensitive edge handlers if Java warmup is the bottleneck.

###### Depth 3
**Q:** What is "Event Processing Failure" strategy in an async flow?  
**A:** Use a **Dead-Letter Queue (DLQ)**. If Lambda fails after retries, send the message to DLQ. Then use a controlled **redrive** after fixes.

###### Depth 4
**Q:** Deep dive: How does Lambda manage "Firecracker MicroVM" isolation?  
**A:** Each Lambda instance runs in a KVM-based microVM (Firecracker). It uses a minimal device model, enabling fast startup and strong isolation.

###### Depth 5
**Q:** How do you optimize "Step Functions" cost for high-volume flows?  
**A:** Standard workflows charge per state transition; Express charges by GB-second. Use Express for high-volume short-lived flows; use Standard for long waits and human steps.

###### Depth 6
**Q:** Describe the "EventBridge" pattern vs SNS for Fan-Out.  
**A:** SNS is a topic-to-subscribers pipe. EventBridge is a rule-based router with filtering and better decoupling. Use EventBridge when producers shouldn’t know consumers.

###### Depth 7
**Q:** What is the limiting factor of Kinesis Data Streams vs Kafka (MSK)?  
**A:** Kinesis throughput is shard-based; scaling shards has operational considerations. Kafka partitions can provide higher stable throughput and replay flexibility; MSK often fits stable high-throughput platforms.

###### Depth 8
**Q:** How do "Lambda Function URLs" change the architecture?  
**A:** They can remove APIGW for simple internal use cases, reducing cost; trade-off is fewer built-in features (WAF/usage plans).

###### Depth 9
**Q:** Explain "Idempotency" implementation in Lambda accessing DynamoDB.  
**A:** Use **AWS Lambda Powertools (utilities library for logging/metrics/tracing/idempotency)**. Track event IDs in a DynamoDB table; if seen, return cached/previous result.

###### Depth 10
**Q:** How do you trace a request from APIGW → SQS → Lambda → DynamoDB?  
**A:** Use **AWS X-Ray (distributed tracing)** with trace propagation and linking. The queue breaks synchronous traces, so you rely on trigger-based trace linking.

###### Depth 11
**Q:** Analyze the "TCP Connection Reuse" problem in Lambda.  
**A:** Execution contexts can be reused; initialize clients outside the handler to reuse connections. For relational DBs, use RDS Proxy to avoid connection storms.

###### Depth 12
**Q:** What is the cost impact of "Wait States" in Step Functions?  
**A:** Standard workflows don’t charge for wait time; Express charges for duration, so avoid long waits in Express.

###### Depth 13
**Q:** How do you handle "Partial Failures" in a Lambda processing a batch of 10 SQS messages?  
**A:** Enable “Report Batch Item Failures” so only failed items are retried; successful ones are deleted.

###### Depth 14
**Q:** Deep dive: Extension API in Lambda Observability.  
**A:** Extensions run alongside the function runtime and can ship telemetry asynchronously without blocking the main handler.

###### Depth 15
**Q:** Formal Proof: Is Serverless always cheaper than EC2/Containers?  
**A:** No. Always-on steady workloads often become cheaper on EC2/ECS with Savings Plans; serverless is a premium for burst and scale-to-zero.

---

## 3. Storage & Database Architecture

### Q3: Architect a storage layer for a "Netflix-like" streaming data lake. Petabytes of data, low latency metadata queries.
**Answer:**  
**Executive intent (Principal-level):** I separate **durable object storage** from **elastic query compute** and treat **metadata as a first-class system**, because discoverability determines usability.

**Baseline architecture (what I would draw on the whiteboard):**
- **Data lake:** Amazon Simple Storage Service (**S3**) with Intelligent-Tiering
- **Hot metadata path:** DynamoDB (NoSQL key-value/document DB) for low-latency catalog and lookup queries
- **Schema & catalog:** AWS Glue Data Catalog
- **Query engines:**
    - Amazon Athena (serverless SQL on S3) for ad-hoc analysis
    - Amazon EMR (Elastic MapReduce) / Apache Spark for batch processing
    - Amazon Redshift for predictable, high-concurrency BI workloads

**Why this works:**
- S3 is the system of record (11 nines durability); compute is disposable.
- Columnar formats (Parquet / ORC) and partitioning reduce scanned bytes and cost.
- DynamoDB avoids S3 scans for “where is my data?” queries.

**Explicit trade-offs:**
- Athena is cheapest for intermittent use; Redshift wins when concurrency and SLAs matter.
- Single-table DynamoDB designs are powerful but dangerous without disciplined access patterns.

#### Depth 1
**Q:** Why use Amazon Simple Storage Service (S3) Standard vs Intelligent Tiering for Lake?  
**A:** Intelligent-Tiering reduces operational mistakes from lifecycle rules and adapts automatically; fees are typically worth it at PB scale.

##### Depth 2
**Q:** How do you optimize S3 performance for high request rates?  
**A:** S3 scales automatically; avoid putting everything under one prefix, and design key naming to spread access patterns.

###### Depth 3
**Q:** Explain "S3 Consistency Model" and its history.  
**A:** S3 is strongly consistent for new puts and overwrites now; historically some operations had eventual behavior, which required defensive patterns.

###### Depth 4
**Q:** Deep dive: How does DynamoDB partition data?  
**A:** It uses partition keys and internal partitioning; sustained hot keys throttle at partition level, not table level.

###### Depth 5
**Q:** What is the "Single Table Design" pros/cons?  
**A:** Pro: fewer round trips; Con: complexity and evolution pain. Use it only for proven hot paths.

###### Depth 6
**Q:** How does Aurora Serverless v2 differ from v1?  
**A:** v2 scales more continuously and avoids the harsh pause/resume model of v1.

###### Depth 7
**Q:** Explain "DAX" (DynamoDB Accelerator) implementation details.  
**A:** Read-through/write-through cache that lowers latency, but introduces consistency trade-offs.

###### Depth 8
**Q:** Design a strategy for "S3 Object Locking" (WORM) for compliance.  
**A:** Use Object Lock in compliance mode with retention policies; it’s strong ransomware protection but must avoid storing deletable PII inside immutable retention.

###### Depth 9
**Q:** How do you minimize Athena costs for searching logs?  
**A:** Partition + Parquet. Athena charges per bytes scanned.

###### Depth 10
**Q:** What is "Redshift Spectrum" vs "Athena"?  
**A:** Spectrum extends Redshift to query S3 with joins; Athena is pure serverless query on S3.

###### Depth 11
**Q:** How does EBS io2 Block Express achieve high performance?  
**A:** Nitro + NVMe interface; high predictable IOPS.

###### Depth 12
**Q:** When to use Fargate vs EC2 for container storage?  
**A:** EC2 gives access to local NVMe instance store; Fargate is simpler but more constrained.

###### Depth 13
**Q:** How do you encrypt an existing unencrypted RDS database with minimal downtime?  
**A:** Snapshot-copy-encrypt-restore causes downtime; for near-zero downtime, replicate to an encrypted target using AWS Database Migration Service (DMS) then cut over.

###### Depth 14
**Q:** What is "Macie" and is it worth the cost?  
**A:** PII discovery in S3; restrict scope or sampling to avoid scanning explosions.

###### Depth 15
**Q:** Explain the mechanics of EFS distributed locking.  
**A:** EFS is NFSv4; shared filesystem semantics trade latency for multi-node shareability.

---

## 4. Security & Governance at Scale

### Q4: Design a multi-account security strategy for a 1000+ account Organization. How do you enforce "Preventative Guardrails" without blocking execution?
**Answer:**  
**Executive intent (Principal-level):** I enforce security using **preventative guardrails** (things that cannot happen) and **detective controls** (things we detect and remediate), without blocking delivery velocity.

**Baseline architecture (what I would draw on the whiteboard):**
- **Landing zone:** AWS Control Tower
- **Account structure:** Organizational Units (**OU**) for Security, Log Archive, Shared Services, and Workloads
- **Identity:** AWS IAM Identity Center (formerly AWS Single Sign-On, SSO) federated with corporate IdP; avoid long-lived IAM users
- **Guardrails:**
    - AWS Organizations **Service Control Policies (SCPs)** for hard denies (regions, public S3, encryption)
    - AWS Config rules for compliance posture
- **Central visibility:** AWS CloudTrail, CloudWatch, and AWS Security Hub aggregated centrally

**How teams are not blocked:**
- SCPs only for irreversible risks.
- Everything else uses detect-and-remediate via EventBridge → Lambda.

**Explicit trade-offs:**
- SCP mistakes can lock accounts; all changes are staged in sandbox OUs.
- Attribute-Based Access Control (ABAC) reduces role sprawl but requires disciplined tagging.

#### Depth 1
**Q:** Explain the hierarchy of policy evaluation (SCP vs IAM Policy vs Resource Policy).  
**A:** SCP deny overrides everything. Effective permissions are the intersection of organization constraints + identity permissions + resource policies.

##### Depth 2
**Q:** How do you test SCPs before deploying to 1000 accounts?  
**A:** Stage in sandbox OU; run automated integration tests that validate both allowed and denied operations.

###### Depth 3
**Q:** Deep dive: ABAC vs RBAC at scale.  
**A:** ABAC uses tags and conditions to avoid role explosion; RBAC is simpler but doesn’t scale without sprawl.

###### Depth 4
**Q:** How does AWS Network Firewall differ from Security Groups + NACLs?  
**A:** SG/NACL are L3/L4; Network Firewall provides deeper inspection and centralized egress control.

###### Depth 5
**Q:** Design a secure "Shared Services VPC" pattern.  
**A:** Hub-and-spoke with Transit Gateway (TGW) to centralize egress controls and monitoring.

###### Depth 6
**Q:** How do you detect "Privilege Escalation" in CloudTrail logs?  
**A:** Detect self-admin grants; auto-remediate and alert using EventBridge rules.

###### Depth 7
**Q:** Explain Nitro Enclaves for sensitive processing.  
**A:** Isolated compute with no network; communicates via local socket and uses attestation.

###### Depth 8
**Q:** IAM Roles Anywhere vs IoT Core Certificates?  
**A:** Roles Anywhere fits servers; IoT Core fits massive device fleets.

###### Depth 9
**Q:** How do you secure IMDSv2 against SSRF?  
**A:** Require session tokens and restrict metadata access; SSRF becomes much harder.

###### Depth 10
**Q:** Confused deputy problem and ExternalId?  
**A:** ExternalId in trust policy prevents third-party role abuse.

###### Depth 11
**Q:** How do KMS Multi-Region Keys help DR?  
**A:** They replicate key material so encryption/decryption works across regions without manual re-keying.

###### Depth 12
**Q:** Can IAM policies be mathematically verified?  
**A:** IAM Access Analyzer applies automated reasoning to detect unintended public access paths.

###### Depth 13
**Q:** GDPR “right to be forgotten” vs immutable storage like Glacier?  
**A:** Don’t store deletable PII in immutable retention; or use crypto-shredding (delete keys).

###### Depth 14
**Q:** CloudTrail Data Events—impact?  
**A:** Minimal latency impact; potential massive cost impact—scope carefully.

###### Depth 15
**Q:** VPC Reachability Analyzer internals?  
**A:** It analyzes config graphs (routes/SG/NACL) rather than sending packets.

---

## 5. Cost Optimization & FinOps

### Q5: You inherited an AWS bill of $10M/year. Reduce it by 30% in 3 months without performance degradation.
**Answer:**  
**Executive intent (Principal-level):** I achieve 30% reduction by removing waste first, then right-sizing, then committing to predictable spend — without destabilizing production.

**Baseline approach (what I would draw on the whiteboard):**
1. **Waste removal:** Unattached Amazon Elastic Block Store (**EBS**) volumes, idle Elastic IPs (**EIP**), old snapshots, abandoned dev stacks
2. **Right-sizing:** AWS Compute Optimizer + CloudWatch Agent memory metrics
3. **Efficiency:** Migrate eligible workloads to AWS Graviton (**ARM64**)
4. **Commitments:** AWS Savings Plans (**SP**) and Reserved Instances (**RI**)

**How performance is protected:**
- Every change tied to latency/error SLOs.
- NAT Gateway (**NAT GW**) and cross-AZ transfer are treated as architecture problems, not billing issues.

**Explicit trade-offs:**
- 100% commitment coverage is risky; 80–90% preserves flexibility.
- Spot Instances are only for stateless, interruption-tolerant tiers.

#### Depth 1
**Q:** Why Compute Savings Plans (CSP) over EC2 Instance Savings Plans?  
**A:** CSP is more flexible across compute services and families; it reduces non-usage risk.

##### Depth 2
**Q:** How do you orchestrate Spot Instances for a stateful production workload?  
**A:** Prefer not to; if needed, isolate Spot to stateless tiers and ensure graceful drain and eviction handling.

###### Depth 3
**Q:** Explain the S3 Standard-IA minimum object size trap.  
**A:** Small objects are billed as if they are at least 128KB and have minimum durations—can increase cost.

###### Depth 4
**Q:** Deep dive: Data Transfer Costs (Inter-AZ vs Inter-Region).  
**A:** Treat them as architecture fixes: use VPC endpoints/PrivateLink and locality to cut transfer spend.

###### Depth 5
**Q:** Graviton (ARM64) requires recompilation. Is it worth it for Java?  
**A:** Often yes—Java frequently moves with minimal changes; price-performance gains can be significant.

###### Depth 6
**Q:** gp3 vs gp2 differences?  
**A:** gp3 decouples size from IOPS; gp2 couples them and causes waste.

###### Depth 7
**Q:** NAT Gateway costs in high-throughput scenarios?  
**A:** NAT GW charges per hour and per GB processed; avoid routing S3/DynamoDB traffic through NAT by using endpoints.

###### Depth 8
**Q:** What is CUR (Cost and Usage Report) and why Athena?  
**A:** CUR is detailed cost data; Athena lets you query spikes down to resource and hour.

###### Depth 9
**Q:** Tagging compliance for chargeback?  
**A:** Enforce via IaC and guardrails; deny/tag-on-create patterns may be used carefully.

###### Depth 10
**Q:** Lambda Power Tuning mechanism?  
**A:** Higher memory increases CPU; sometimes more memory is cheaper due to shorter runtime.

###### Depth 11
**Q:** Savings Plan coverage target?  
**A:** Not 100%; keep headroom for demand shifts.

###### Depth 12
**Q:** Shut down dev environments at night?  
**A:** EventBridge schedules + automation to stop/start compute; large cost win.

###### Depth 13
**Q:** DynamoDB On-Demand vs Provisioned at steady state?  
**A:** On-Demand is convenient but can be expensive; move to Provisioned + autoscaling once stable.

###### Depth 14
**Q:** Bottlerocket OS benefits?  
**A:** Smaller OS footprint, security hardening, and operational efficiency for container hosts.

###### Depth 15
**Q:** FinOps culture: how do you motivate engineers?  
**A:** Visibility + ownership: cost next to latency metrics, plus simple targets and recognition.

---

*Use this guide to drive simplicity (Occam's Razor) in architecture while demonstrating mastery of the underlying AWS mechanics.*

---

## Glossary (quick refresh)
- **Global Accelerator:** AWS service that provides static Anycast IPs at the edge, routing user traffic to the nearest healthy AWS region over the private backbone.
- **Route 53:** AWS's scalable DNS and health-based routing service for domain resolution and endpoint steering.
- **DynamoDB:** Fully managed NoSQL key-value and document database with single-digit millisecond latency at any scale.
- **DynamoDB Global Tables:** Multi-region, multi-master replication feature for DynamoDB, enabling active-active tables across AWS regions.
- **Aurora:** AWS's managed relational database engine compatible with MySQL and PostgreSQL, offering high performance and availability.
- **RDS:** Relational Database Service, a managed service for operating popular SQL databases (e.g., MySQL, PostgreSQL, Oracle) on AWS.
- **KMS:** Key Management Service, AWS's managed encryption key store and cryptographic API for securing data at rest and in transit.
- **SCP:** Service Control Policy, an organization-level policy in AWS Organizations that restricts which AWS services and actions are allowed in member accounts.
- **VPC:** Virtual Private Cloud, a logically isolated network environment in AWS where you can launch resources and control network settings.
- **Transit Gateway:** AWS networking hub that connects multiple VPCs and on-premises networks, simplifying large-scale network architectures.

## Abbreviations (expanded)
- **GA — Global Accelerator**
- **TTL — Time-to-live**
- **BGP — Border Gateway Protocol**
- **NLB — Network Load Balancer**
- **ALB — Application Load Balancer**
- **APIGW — Amazon API Gateway**
- **SQS — Simple Queue Service**
- **DLQ — Dead-Letter Queue**
- **EKS — Elastic Kubernetes Service**
- **ECS — Elastic Container Service**
- **MSK — Managed Streaming for Apache Kafka**
- **KMS — Key Management Service**
- **SCP — Service Control Policy**
- **VPC — Virtual Private Cloud**
- **TGW — Transit Gateway**
- **IAM — Identity and Access Management**
- **MFA — Multi-Factor Authentication**
- **mTLS — Mutual TLS**
- **WAF — Web Application Firewall**
- **RPKI — Resource Public Key Infrastructure**
- **ROA — Route Origin Authorization**
- **PII — Personally Identifiable Information**
- **TPS — Transactions per second**
- **IOPS — Input/Output operations per second**
- **EBS — Elastic Block Store**
- **EIP — Elastic IP**
- **AZ — Availability Zone**
- **NAT GW — NAT Gateway**
- **CUR — Cost and Usage Report**
- **SP — Savings Plans**
- **RI — Reserved Instances**
- **ARM64 — 64-bit ARM architecture**
- **JVM — Java Virtual Machine**
- **KVM — Kernel-based Virtual Machine**
- **NFS — Network File System**
- **WORM — Write Once Read Many**
- **DMS — Database Migration Service**
- **ACU — Aurora Capacity Unit**
- **DAX — DynamoDB Accelerator**
- **SSRF — Server-Side Request Forgery**
- **STS — Security Token Service**
- **SMT — Satisfiability Modulo Theories**