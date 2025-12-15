# Database Deep Dive – Oracle & PostgreSQL

Expert-level interview questions for 16+ years of experience covering setup, infrastructure, performance tuning, and real-world problem-solving. Each question includes **five levels of follow-up depth**.

---

## 1. Database Setup & Architecture

### Q1: How would you design a highly available PostgreSQL cluster for a mission-critical application?
**Answer:**
Use **streaming replication** with at least one synchronous standby for zero data loss (RPO=0). Deploy **Patroni** with **etcd/Consul** for automatic failover. Use **pgBouncer** for connection pooling and **HAProxy** for load balancing read traffic across replicas.

#### Follow-up Depth 1
**Q:** What is the difference between synchronous and asynchronous replication in PostgreSQL?
**A:** Synchronous replication waits for at least one standby to acknowledge the write before committing, guaranteeing zero data loss but adding latency. Asynchronous replication commits immediately, offering better performance but risking data loss during failover.

##### Follow-up Depth 2
**Q:** How does `synchronous_commit` interact with `synchronous_standby_names`?
**A:** `synchronous_commit` controls transaction durability levels (on, remote_apply, remote_write, local, off). `synchronous_standby_names` specifies which standbys must acknowledge. Setting `remote_apply` ensures writes are replayed on standby before commit.

###### Follow-up Depth 3
**Q:** In a 3-node cluster, how would you configure quorum-based synchronous replication?
**A:** Set `synchronous_standby_names = 'ANY 1 (standby1, standby2)'` to require acknowledgment from at least one of the two standbys, balancing availability and durability.

###### Follow-up Depth 4
**Q:** What happens if both synchronous standbys fail simultaneously?
**A:** The primary will block writes until a standby reconnects (unless `synchronous_commit` is lowered to `local`). Monitor `pg_stat_replication` and implement alerts for replication lag.

###### Follow-up Depth 5
**Q:** How would you automate failover while preventing split-brain scenarios?
**A:** Use Patroni with a distributed consensus store (etcd) for leader election. Patroni uses leases to ensure only one primary exists; fencing mechanisms prevent writes to old primary.

---

### Q2: Explain Oracle's Real Application Clusters (RAC) architecture and when you would choose it over Data Guard.
**Answer:**
**RAC** provides active-active clustering where multiple instances access the same database via shared storage, enabling load distribution and high availability. **Data Guard** is active-passive disaster recovery with physical/logical standbys.

Choose RAC for: horizontal scalability, zero downtime upgrades, single database view.  
Choose Data Guard for: disaster recovery, read offloading, cost-effective HA.

#### Follow-up Depth 1
**Q:** How does Oracle RAC handle cache coherency across nodes?
**A:** Via **Cache Fusion** – the Global Cache Service (GCS) and Global Enqueue Service (GES) coordinate block transfers over the interconnect using a distributed lock manager.

##### Follow-up Depth 2
**Q:** What is a "consistent read" block transfer in RAC?
**A:** When one instance needs a past image of a block held by another, the holding instance constructs a read-consistent version using undo and ships it via the interconnect.

###### Follow-up Depth 3
**Q:** How do you diagnose high interconnect latency in RAC?
**A:** Check AWR for `gc cr block receive time` and `gc current block receive time`. Use `oratop` or `GV$INTERCONNECT_PING` to measure round-trip latency; ideally <1ms.

###### Follow-up Depth 4
**Q:** What network configuration best practices ensure low RAC interconnect latency?
**A:** Use dedicated 10GbE+ links, enable jumbo frames (MTU 9000), disable flow control, use LACP bonding for redundancy, and isolate interconnect traffic from application traffic.

###### Follow-up Depth 5
**Q:** How would you handle a scenario where one RAC node repeatedly evicts due to network issues?
**A:** Check cluster logs (`$GRID_HOME/log`), analyze `ocssd.log` for missed heartbeats, verify network hardware, and consider increasing `CSS misscount` temporarily while root cause is fixed.

---

## 2. Performance Tuning & Query Optimization

### Q3: A critical query in PostgreSQL is running 10x slower after a data migration. Diagnose and fix.
**Answer:**
1. Run `EXPLAIN ANALYZE` to check execution plan changes.
2. Check if `ANALYZE` was run post-migration to update statistics.
3. Verify indexes exist and are valid.
4. Compare `pg_stat_user_tables` and `pg_stat_user_indexes` metrics.
5. Check for bloat and consider `VACUUM FULL` or `REINDEX`.

#### Follow-up Depth 1
**Q:** What does `EXPLAIN ANALYZE` show that `EXPLAIN` alone does not?
**A:** `EXPLAIN ANALYZE` executes the query and shows actual row counts, execution time, and buffer hits vs. misses, revealing planner estimation errors.

##### Follow-up Depth 2
**Q:** How do you interpret a seq scan on a table with an index on the WHERE clause column?
**A:** The planner estimates seq scan is cheaper (small table, low selectivity, or outdated stats). Check `pg_stats` for column statistics and run `ANALYZE`. If stats are correct, index may be genuinely suboptimal.

###### Follow-up Depth 3
**Q:** The query plan shows "Rows Removed by Filter: 9999990". What does this indicate?
**A:** The planner chose an index that doesn't filter effectively, or there's no suitable index. This indicates the index is scanned but most rows fail the filter predicate, suggesting a missing composite index.

###### Follow-up Depth 4
**Q:** How would you decide between creating a new composite index vs. restructuring the query?
**A:** Analyze write overhead (index maintenance on INSERT/UPDATE), storage cost, and query frequency. If the query is infrequent, restructuring or partitioning may be better than adding indexes.

###### Follow-up Depth 5
**Q:** After adding the index, performance didn't improve. What are possible causes?
**A:** Index bloat (run `REINDEX`), low `effective_cache_size` causing wrong planner choices, or query needs index-only scan but table has high dead tuples (run `VACUUM`).

---

### Q4: How do you identify and resolve blocking in Oracle?
**Answer:**
1. Query `V$SESSION` and `V$LOCK` to find blocking sessions (`BLOCKING_SESSION` column).
2. Use `V$SQL` to identify the blocking SQL.
3. Decide whether to kill the blocker (`ALTER SYSTEM KILL SESSION`) or wait.
4. Implement `DDL_LOCK_TIMEOUT` and `DML_LOCKS` tuning.

#### Follow-up Depth 1
**Q:** What is the difference between a lock and a latch in Oracle?
**A:** Locks protect data (rows, tables) and are session-level. Latches protect internal memory structures and are lightweight, short-duration spinlocks.

##### Follow-up Depth 2
**Q:** How do you diagnose latch contention?
**A:** Check AWR report for "Latch: XYZ" wait events. Query `V$LATCH` and `V$LATCH_CHILDREN` for misses and sleeps. Common culprits: cache buffers chains, shared pool.

###### Follow-up Depth 3
**Q:** A query shows high wait on "latch: cache buffers chains". What's the root cause?
**A:** Hot blocks being accessed by many sessions (e.g., frequently updated index root block, sequence cache). Solutions: increase `CACHE` size for sequences, partition indexes, or use reverse-key indexes.

###### Follow-up Depth 4
**Q:** When would you use a reverse-key index, and what are the trade-offs?
**A:** Use for monotonically increasing keys (timestamps, sequences) to distribute inserts across index leaf blocks, reducing contention. Trade-off: range scans become inefficient.

###### Follow-up Depth 5
**Q:** How do you migrate a normal index to a reverse-key index with minimal downtime?
**A:** Create new reverse-key index online (`CREATE INDEX ... REVERSE ONLINE`), swap via `ALTER TABLE ... RENAME CONSTRAINT`, drop old index. Monitor `DBA_INDEX_USAGE` to ensure queries use new index.

---

## 3. Backup, Recovery & High Availability

### Q5: Describe a point-in-time recovery (PITR) strategy for PostgreSQL.
**Answer:**
Enable **continuous archiving** with `archive_mode = on` and `archive_command` to copy WAL files to safe storage. Take base backups using `pg_basebackup` or `pg_backup_start/stop`. For PITR, restore base backup and replay WAL until target time using `recovery_target_time`.

#### Follow-up Depth 1
**Q:** What is the difference between `pg_basebackup` and filesystem-level backups?
**A:** `pg_basebackup` is PostgreSQL-aware, ensures consistency, and includes necessary WAL. Filesystem backups require `pg_backup_start/stop` or the database to be shut down.

##### Follow-up Depth 2
**Q:** How do you verify a backup is restorable without impacting production?
**A:** Regularly restore backups to a test environment, perform consistency checks (`pg_checksums`), and run validation queries.

###### Follow-up Depth 3
**Q:** What happens if WAL archiving falls behind during high write load?
**A:** `pg_wal/` directory fills up, potentially causing disk full and database halt. Monitor with `pg_stat_archiver`, set retention policies, and tune `archive_command` for parallelism.

###### Follow-up Depth 4
**Q:** How would you implement incremental backups to reduce backup windows?
**A:** Use `pg_basebackup --incremental` (PG 17+) or third-party tools like pgBackRest/Barman that support incremental and differential backups via WAL-based increments.

###### Follow-up Depth 5
**Q:** In a disaster, WAL archives are corrupted. How do you minimize data loss?
**A:** Promote a streaming replica immediately (loses uncommitted transactions). Implement multiple archive destinations (`archive_command` with rsync to multiple sites) and WAL-G for cloud backups with redundancy.

---

### Q6: How do you perform a zero-downtime Oracle database upgrade from 19c to 23c?
**Answer:**
Use **Transient Logical Standby** or **GoldenGate** for near-zero downtime:
1. Set up Data Guard logical standby.
2. Upgrade standby to 23c using DBUA.
3. Sync data and switch over.
4. Validate and redirect application traffic.

#### Follow-up Depth 1
**Q:** What is the difference between physical and logical standby in Data Guard?
**A:** Physical standby applies redo blocks (binary replication), identical structure. Logical standby applies SQL from redo logs, allowing open for read/write, useful for upgrades and reporting.

##### Follow-up Depth 2
**Q:** What limitations exist with logical standby?
**A:** Not all data types are supported (e.g., BFILE, collections), certain DDL operations may fail, and there's replication lag. Check `DBA_LOGSTDBY_UNSUPPORTED`.

###### Follow-up Depth 3
**Q:** How do you monitor and minimize logical standby lag?
**A:** Query `V$LOGSTDBY_STATS` for apply lag. Increase parallelism (`PREPARE_SERVERS`, `APPLY_SERVERS`), tune `PGA_AGGREGATE_TARGET`, and ensure network bandwidth is sufficient.

###### Follow-up Depth 4
**Q:** During switchover, how do you ensure zero data loss?
**A:** Use `SYNC` mode in Data Guard, verify `V$ARCHIVE_DEST` shows no gaps, execute `ALTER DATABASE COMMIT TO SWITCHOVER TO LOGICAL STANDBY` with verification steps.

###### Follow-up Depth 5
**Q:** Post-upgrade, application reports schema differences. What's the root cause and fix?
**A:** Logical standby may have skipped unsupported DDL. Compare schemas with `DBMS_METADATA.GET_DDL`, manually apply missing changes, and test thoroughly in staging before production switchover.

---

## 4. Troubleshooting & Real-World Scenarios

### Q7: Scenario – A PostgreSQL database suddenly shows "too many clients" errors. Investigate and resolve.
**Answer:**
1. Check `max_connections` setting and current count via `SELECT count(*) FROM pg_stat_activity`.
2. Identify idle or long-running connections: `SELECT * FROM pg_stat_activity WHERE state != 'idle' OR state_change < now() - interval '5 minutes'`.
3. Kill problematic connections or increase `max_connections`.
4. Implement connection pooling (pgBouncer) to reduce connection overhead.

#### Follow-up Depth 1
**Q:** What is the overhead of increasing `max_connections` from 100 to 1000?
**A:** Each connection consumes shared memory (`work_mem`, `temp_buffers`) and backend process overhead (~10MB per connection). Increasing may require raising `shared_buffers` and kernel parameters (`SHMMAX`).

##### Follow-up Depth 2
**Q:** How does pgBouncer reduce connection overhead compared to direct connections?
**A:** pgBouncer maintains a pool of PostgreSQL connections and multiplexes client connections, supporting transaction or session pooling. This reduces backend process churn and memory usage.

###### Follow-up Depth 3
**Q:** What is the difference between session and transaction pooling in pgBouncer?
**A:** Session pooling assigns a backend to a client for the entire session (supports `SET` variables). Transaction pooling releases backend after each transaction (higher throughput but cannot use session state).

###### Follow-up Depth 4
**Q:** Application uses temp tables extensively. Which pooling mode should you choose?
**A:** Session pooling – transaction pooling clears temp tables after each transaction, breaking application logic.

###### Follow-up Depth 5
**Q:** After deploying pgBouncer, `pg_stat_statements` shows increased query time. Why?
**A:** Queries now include pooler overhead (connection acquisition latency). Tune `pool_mode`, increase `default_pool_size`, and monitor pgBouncer stats (`SHOW STATS`, `SHOW POOLS`).

---

### Q8: Oracle database experiences intermittent hangs during peak load. Diagnose.
**Answer:**
1. Check AWR report for top wait events (e.g., enqueue waits, latch contention, I/O waits).
2. Query `V$SESSION_WAIT` during hang to identify blockers.
3. Review `V$SYSTEM_EVENT` for cumulative wait patterns.
4. Use ASH (Active Session History) to analyze waits during hang windows.

#### Follow-up Depth 1
**Q:** What does a high "log file sync" wait event indicate?
**A:** LGWR is slow to write redo to disk. Check storage I/O performance, consider faster disks (SSD), or reduce commit frequency in application.

##### Follow-up Depth 2
**Q:** How do you differentiate between "log file sync" caused by slow storage vs. application design?
**A:** Check `V$SYSTEM_EVENT` for average wait time. If >10ms, suspect storage. Also query `V$SESS_IO` for commits per session – high commit rates indicate application issues (batch commits may help).

###### Follow-up Depth 3
**Q:** What Oracle feature can reduce commit latency without sacrificing durability?
**A:** Use **commit write nowait** or group commits. Alternatively, configure `SYNC` vs. `ASYNC` for standby logs in Data Guard to balance durability and performance.

###### Follow-up Depth 4
**Q:** In a RAC environment, should redo logs be on shared or local storage?
**A:** Local storage (dedicated redo logs per instance) for lower latency. Shared storage is possible but increases interconnect load and complexity.

###### Follow-up Depth 5
**Q:** How do you size redo logs to minimize "log file switch" wait events?
**A:** Monitor `V$LOG_HISTORY` for switch frequency. Aim for switches every 15-20 minutes during peak load. Size redo logs using: `(redo_rate_MB/s × 1200s) / number_of_groups`.

---

## 5. Advanced Topics & Best Practices

### Q9: Explain table partitioning strategies in PostgreSQL and when to use each.
**Answer:**
- **Range partitioning**: Time-series data (e.g., partition by month/year).
- **List partitioning**: Discrete categories (e.g., region, status).
- **Hash partitioning**: Distribute data evenly for load balancing.

Partitioning improves query performance (partition pruning), simplifies maintenance (drop old partitions), and enables parallel query execution.

#### Follow-up Depth 1
**Q:** How does partition pruning improve query performance?
**A:** The planner eliminates partitions that don't match WHERE clause constraints, reducing scanned data. Check `EXPLAIN` for "Partitions removed" count.

##### Follow-up Depth 2
**Q:** What happens if the partition key is not in the WHERE clause?
**A:** All partitions are scanned (no pruning). Design partition keys based on common query patterns. Use composite keys if needed.

###### Follow-up Depth 3
**Q:** How do you handle queries that need to join across partitions?
**A:** Ensure partition keys are aligned between tables, or use partition-wise joins (`enable_partitionwise_join = on`) to parallelize joins.

###### Follow-up Depth 4
**Q:** What are the limitations of partition-wise joins in PostgreSQL?
**A:** Both tables must be partitioned identically (same key, same boundaries). Not all join types support it, and it may increase planning time.

###### Follow-up Depth 5
**Q:** How would you migrate a 5TB unpartitioned table to partitioned with minimal downtime?
**A:** Use `pg_partman` or manual steps: create partitioned table, copy data in batches using `INSERT ... SELECT` with `WHERE` filters, swap table names, and rebuild indexes concurrently.

---

### Q10: How do you implement row-level security (RLS) in PostgreSQL for multi-tenant SaaS applications?
**Answer:**
Enable RLS on tables with `ALTER TABLE ... ENABLE ROW LEVEL SECURITY`, then create policies using `CREATE POLICY` to filter rows based on `current_user` or custom session variables set via `SET LOCAL`.

Example:
```sql
CREATE POLICY tenant_policy ON orders
  USING (tenant_id = current_setting('app.tenant_id')::int);
```

#### Follow-up Depth 1
**Q:** What is the performance impact of RLS policies?
**A:** Policies are applied as additional WHERE clauses. Ensure partition keys or indexes align with policy filters to avoid full table scans.

##### Follow-up Depth 2
**Q:** How do you test that RLS policies prevent cross-tenant data leaks?
**A:** Create test users, set session variables, run queries, and verify results. Use `SET ROLE` to impersonate users in tests.

###### Follow-up Depth 3
**Q:** Can RLS policies be bypassed by superusers?
**A:** Yes, by default. Use `ALTER TABLE ... FORCE ROW LEVEL SECURITY` to apply policies even to table owners and superusers.

###### Follow-up Depth 4
**Q:** How do you handle administrative queries that need to access all tenants?
**A:** Create separate admin roles with BYPASSRLS privilege or write policies with exceptions for specific roles using `current_user`.

###### Follow-up Depth 5
**Q:** In a scenario with 10,000 tenants, how would you optimize RLS for reporting queries?
**A:** Partition tables by `tenant_id` for partition pruning, use materialized views for aggregates, or create tenant-specific read replicas with filtered data for large tenants.

---

*Use this guide to demonstrate deep database expertise, combining theoretical knowledge with practical problem-solving for senior-level interviews.*
