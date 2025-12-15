# Scalable, High-Performing Applications – Real-Time & Batch Processing

This document provides deep-level questions and scenario-based exercises with detailed answers. Each answer is followed by **six levels of follow-up questions** to reinforce understanding.

---

## 1. Scalability Fundamentals

### Q1: What is the difference between vertical and horizontal scaling?
**Answer:**
- **Vertical scaling** (scale-up): adding more resources (CPU, RAM) to a single machine.
- **Horizontal scaling** (scale-out): adding more machines to distribute load.

Vertical scaling is simpler but limited by hardware; horizontal scaling provides near-linear growth but requires stateless design and distributed coordination.

#### Follow-up Depth 1
**Q:** What challenges arise when transitioning from vertical to horizontal scaling?
**A:** Session management, data consistency across nodes, service discovery, and load balancing become non-trivial.

##### Follow-up Depth 2
**Q:** How would you handle session data in a horizontally scaled system?
**A:** Use external session stores (Redis, Memcached), sticky sessions (less resilient), or stateless JWTs.

###### Follow-up Depth 3
**Q:** What are the trade-offs of using JWTs for session state?
**A:** Pros – stateless, no server-side lookup; Cons – token revocation is hard, larger payload, clock-skew issues.

###### Follow-up Depth 4
**Q:** How can you implement token revocation with JWTs?
**A:** Maintain a blacklist of revoked tokens in Redis, or use short-lived access tokens with refresh tokens stored server-side.

###### Follow-up Depth 5
**Q:** What happens if the Redis blacklist becomes a single point of failure?
**A:** Replicate Redis (Sentinel/Cluster), use local caching with TTL, or fall back to short token lifetimes so revocation is less critical.

###### Follow-up Depth 6
**Q:** How would you design a geo-distributed token revocation system?
**A:** Use a multi-region Redis Cluster with cross-region replication (CRDT-based), or broadcast revocation events via Kafka to regional caches.

---

### Q2: Explain the CAP theorem and its implications for distributed systems.
**Answer:**
CAP states a distributed system can provide at most two of: **Consistency**, **Availability**, **Partition tolerance**. Since network partitions are unavoidable, systems choose CP (consistent but may be unavailable) or AP (always available but inconsistent).

#### Follow-up Depth 1
**Q:** When would you choose a CP system over an AP system?
**A:** When correctness is critical – e.g., financial transactions, inventory deductions.

##### Follow-up Depth 2
**Q:** How do modern databases like CockroachDB achieve strong consistency?
**A:** They use consensus protocols (Raft/Paxos) to coordinate writes across replicas.

###### Follow-up Depth 3
**Q:** What latency trade-off does Raft introduce for write operations?
**A:** Writes require a majority quorum; cross-region deployments add network round-trip latency per commit.

###### Follow-up Depth 4
**Q:** How can you minimize Raft commit latency in multi-region setups?
**A:** Use regional leader placement, witness replicas, or tiered consistency (strong within region, eventual across).

###### Follow-up Depth 5
**Q:** What is a witness replica and how does it help quorum?
**A:** A lightweight replica that participates in voting but doesn't store full data, reducing cost while maintaining quorum.

###### Follow-up Depth 6
**Q:** What failure scenarios can witness replicas not handle?
**A:** If the witness and leader are co-located and both fail, quorum is lost; spread witnesses across fault domains.

---

## 2. Real-Time Processing

### Q3: Describe the architecture of a low-latency event streaming platform.
**Answer:**
Typical components: **message brokers** (Kafka, Pulsar), **stream processors** (Kafka Streams, Flink), **in-memory stores** (Redis), and **stateless compute nodes**. Data flows through topics, is processed in near real-time, and results are pushed to consumers or written to sinks.

#### Follow-up Depth 1
**Q:** How do you minimize latency in a Kafka-based streaming pipeline?
**A:** Tune producer batching (`linger.ms`, `batch.size`), use SSD-backed brokers, optimize consumer parallelism, and co-locate brokers/consumers.

##### Follow-up Depth 2
**Q:** What is the impact of increasing `linger.ms` on throughput vs latency?
**A:** Higher `linger.ms` batches more records, improving throughput but increasing per-message latency.

###### Follow-up Depth 3
**Q:** How would you benchmark the optimal `linger.ms` for your use case?
**A:** Run load tests with varying values, measure end-to-end latency percentiles (p99), and choose the value that meets SLA while maximizing throughput.

###### Follow-up Depth 4
**Q:** What tools would you use to measure end-to-end latency in Kafka?
**A:** Kafka's built-in `kafka-producer-perf-test`, custom timestamps in message headers, or APM tools (Datadog, Jaeger).

###### Follow-up Depth 5
**Q:** How do you correlate producer and consumer timestamps across time zones?
**A:** Use UTC epoch milliseconds (e.g., `System.currentTimeMillis()`) and synchronize clocks via NTP.

###### Follow-up Depth 6
**Q:** What accuracy can NTP provide and when is it insufficient?
**A:** NTP provides ~1-10 ms accuracy; for sub-millisecond requirements, use PTP (Precision Time Protocol) or GPS-disciplined clocks.

---

### Q4: Scenario – A trading platform receives 100k events/sec but consumers lag behind. Diagnose and fix.
**Answer:**
1. Check consumer count vs partitions – add consumers up to partition count.
2. Profile processing logic – reduce I/O, use async processing.
3. Increase `max.poll.records` and `fetch.min.bytes` if network is the bottleneck.
4. Consider back-pressure mechanisms or dedicated priority queues for critical events.

#### Follow-up Depth 1
**Q:** What happens if you add more consumers than partitions?
**A:** Extra consumers sit idle; partitions can only be assigned to one consumer per group.

##### Follow-up Depth 2
**Q:** How would you dynamically scale consumers based on lag?
**A:** Use Kubernetes HPA with custom metrics (Kafka consumer lag from Prometheus exporter).

###### Follow-up Depth 3
**Q:** What are the pitfalls of auto-scaling consumers too aggressively?
**A:** Frequent rebalances increase latency, cause duplicate processing (at-least-once), and may overwhelm downstream systems.

###### Follow-up Depth 4
**Q:** How can you reduce rebalance frequency during scaling?
**A:** Use static group membership (`group.instance.id`), increase `session.timeout.ms`, or use cooperative rebalancing.

###### Follow-up Depth 5
**Q:** What is cooperative rebalancing and how does it differ from eager rebalancing?
**A:** Cooperative incrementally migrates partitions without stop-the-world pauses, reducing rebalance duration.

###### Follow-up Depth 6
**Q:** What consumer configuration enables cooperative rebalancing?
**A:** Set `partition.assignment.strategy` to `CooperativeStickyAssignor` (Kafka ≥ 2.4).

---

## 3. Batch Processing

### Q5: Compare MapReduce with Apache Spark for large-scale batch processing.
**Answer:**
MapReduce writes intermediate results to disk after each phase, causing high I/O. Spark keeps data in-memory via RDDs/DataFrames, drastically reducing latency. Spark also provides a richer API (SQL, ML, GraphX).

#### Follow-up Depth 1
**Q:** When might MapReduce still be preferred over Spark?
**A:** Extremely large datasets that exceed memory, or legacy Hadoop ecosystems with limited resources.

##### Follow-up Depth 2
**Q:** How does Spark handle memory overflow?
**A:** Spills partitions to disk when memory is exhausted, degrading performance but preventing failure.

###### Follow-up Depth 3
**Q:** How can you tune Spark to minimize spills?
**A:** Increase executor memory, reduce partition size, use efficient serialization (Kryo), and cache only necessary DataFrames.

###### Follow-up Depth 4
**Q:** What is Kryo serialization and why is it faster than Java serialization?
**A:** Kryo is a binary serialization library that produces smaller payloads and avoids Java reflection overhead.

###### Follow-up Depth 5
**Q:** How do you register custom classes with Kryo in Spark?
**A:** Set `spark.kryo.classesToRegister` or implement a `KryoRegistrator` and configure `spark.kryo.registrator`.

###### Follow-up Depth 6
**Q:** What happens if a class is not registered with Kryo?
**A:** Kryo falls back to writing the full class name per object, increasing payload size and partially negating performance gains.

---

### Q6: Scenario – A nightly batch job processing 500 GB takes 8 hours; it must complete in under 2 hours.
**Answer:**
1. Profile job stages to find bottlenecks (shuffle, I/O, skew).
2. Increase parallelism (more executors, partitions).
3. Address data skew with salting or repartitioning.
4. Cache intermediate datasets if reused.
5. Consider incremental processing to reduce nightly volume.

#### Follow-up Depth 1
**Q:** How do you detect data skew in Spark?
**A:** Spark UI shows stage task durations; look for a few tasks taking much longer than others.

##### Follow-up Depth 2
**Q:** What is key salting and how does it help?
**A:** Append a random suffix to skewed keys, redistributing data evenly; aggregate later by original key.

###### Follow-up Depth 3
**Q:** What are the downsides of key salting?
**A:** Increases shuffle size and adds a second aggregation step, slightly raising overall complexity.

###### Follow-up Depth 4
**Q:** How do you determine the optimal salt factor?
**A:** Analyze key distribution; choose a factor that splits the largest key into roughly equal-sized partitions matching executor count.

###### Follow-up Depth 5
**Q:** Can Spark's Adaptive Query Execution (AQE) automatically handle skew?
**A:** Yes, AQE can split skewed partitions at runtime (Spark 3.0+) via `spark.sql.adaptive.skewJoin.enabled`.

###### Follow-up Depth 6
**Q:** What limitations does AQE skew handling have?
**A:** It only applies to shuffle joins; pre-existing skew in source data or aggregations may still require manual salting.

---

## 4. Hybrid Architectures (Lambda/Kappa)

### Q7: Explain the Lambda Architecture and its trade-offs.
**Answer:**
Lambda has a **batch layer** (accurate but delayed), a **speed layer** (fast but approximate), and a **serving layer** that merges both views. Trade-offs: code duplication across layers, operational overhead maintaining two pipelines.

#### Follow-up Depth 1
**Q:** How does Kappa Architecture simplify Lambda?
**A:** Kappa uses a single stream-processing layer for both real-time and replay (re-process from Kafka offsets), eliminating the batch layer.

##### Follow-up Depth 2
**Q:** When is Kappa not suitable?
**A:** When historical corrections require complex batch computations that are impractical to rerun through the stream.

###### Follow-up Depth 3
**Q:** How would you implement state recovery in a Kappa system?
**A:** Use changelog topics or RocksDB state stores with checkpointing (Kafka Streams, Flink).

###### Follow-up Depth 4
**Q:** What is a changelog topic in Kafka Streams?
**A:** An internal compacted topic that records state store updates, enabling recovery by replaying the changelog.

###### Follow-up Depth 5
**Q:** How does compaction work and why is it important for changelogs?
**A:** Compaction retains only the latest value per key, bounding topic size while preserving full state.

###### Follow-up Depth 6
**Q:** What happens if compaction lag is too high?
**A:** Recovery time increases as more records must be replayed; tune `min.cleanable.dirty.ratio` and `segment.ms`.

---

## 5. Observability & Troubleshooting

### Q8: How do you instrument a high-throughput system for latency monitoring?
**Answer:**
Emit metrics at ingestion, processing, and egress stages. Use histograms for latency percentiles (p50, p95, p99). Integrate with Prometheus/Grafana or cloud-native tools (Datadog, New Relic).

#### Follow-up Depth 1
**Q:** Why are percentiles more useful than averages for latency?
**A:** Averages hide outliers; p99 reveals worst-case user experiences.

##### Follow-up Depth 2
**Q:** How do you set SLOs based on percentiles?
**A:** Define acceptable thresholds (e.g., p99 < 200 ms) and alert when breached over a rolling window.

###### Follow-up Depth 3
**Q:** What is error budget and how does it relate to SLOs?
**A:** Error budget is the allowed downtime/latency breach percentage; when exhausted, freeze new feature releases and focus on reliability.

###### Follow-up Depth 4
**Q:** How do you calculate remaining error budget?
**A:** `remaining = (1 - SLO) * time_window - actual_downtime`. E.g., 99.9% SLO over 30 days allows ~43 min downtime.

###### Follow-up Depth 5
**Q:** What organizational practices help enforce error budget policies?
**A:** Blameless postmortems, automated SLO dashboards, and requiring reliability review before feature launches.

###### Follow-up Depth 6
**Q:** How do you handle conflicting priorities when error budget is exhausted?
**A:** Escalate to leadership, defer non-critical features, and allocate engineering time to reliability improvements.

---

### Q9: Scenario – Production alerts show sudden CPU spikes during batch processing. Diagnose.
**Answer:**
1. Correlate time with job schedules.
2. Check Spark/Flink UI for skewed tasks or GC pressure.
3. Review recent code changes for inefficient algorithms.
4. Profile with async-profiler or perf.

#### Follow-up Depth 1
**Q:** How would GC pressure manifest in CPU metrics?
**A:** High GC time leads to sustained CPU usage and increased latency; monitor `jvm_gc_pause_seconds`.

##### Follow-up Depth 2
**Q:** What JVM flags can mitigate long GC pauses in Spark?
**A:** Use G1GC, tune heap size, set `-XX:MaxGCPauseMillis`, and enable `-XX:+UseStringDeduplication`.

###### Follow-up Depth 3
**Q:** When would you consider off-heap memory instead of tuning GC?
**A:** For large caches or serialization buffers where heap pressure is constant; frameworks like Flink use off-heap extensively.

###### Follow-up Depth 4
**Q:** How does Flink manage off-heap memory?
**A:** Flink allocates MemorySegments backed by direct ByteBuffers, bypassing JVM heap and GC.

###### Follow-up Depth 5
**Q:** What are the risks of using off-heap memory?
**A:** Memory leaks are harder to detect, native OOM can crash the process, and debugging tools are less mature.

###### Follow-up Depth 6
**Q:** How do you monitor off-heap memory usage?
**A:** Use JMX MBeans (`java.nio:type=BufferPool`), native memory tracking (`-XX:NativeMemoryTracking`), or framework-specific metrics.

---

## 6. Design & Best Practices

### Q10: What patterns ensure exactly-once semantics in distributed pipelines?
**Answer:**
- **Idempotent producers** (Kafka `enable.idempotence=true`)
- **Transactional consumers** (read-process-write in a transaction)
- **Deduplication** via unique message IDs stored in a state store

#### Follow-up Depth 1
**Q:** How does Kafka achieve idempotent writes?
**A:** Each producer is assigned a PID and monotonically increasing sequence numbers; brokers reject duplicates.

##### Follow-up Depth 2
**Q:** What is the performance overhead of enabling idempotence?
**A:** Minimal – about 1-3% latency increase due to sequence tracking.

###### Follow-up Depth 3
**Q:** Does enabling transactions in Kafka affect consumer isolation levels?
**A:** Yes – set `isolation.level=read_committed` to read only committed transactional messages.

###### Follow-up Depth 4
**Q:** What happens if a transaction times out before commit?
**A:** The transaction is aborted; `read_committed` consumers skip aborted records.

###### Follow-up Depth 5
**Q:** How do you tune `transaction.timeout.ms` for long-running transactions?
**A:** Increase the value to accommodate processing time, but keep it below the broker's `transaction.max.timeout.ms`.

###### Follow-up Depth 6
**Q:** What monitoring should you add for transactional producers?
**A:** Track `txn-abort-rate`, `txn-begin-time-avg`, and `txn-send-offsets-time-avg` via JMX or Prometheus.

---

*Use this guide to prepare for in-depth discussions on building and troubleshooting scalable, high-performing systems.*
