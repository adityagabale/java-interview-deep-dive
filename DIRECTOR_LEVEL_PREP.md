# The Director's Playbook: 80 Levels of Mastery (Conversational Q&A)

**Target Audience:** Director / VP of Engineering.
**Mode:** Interview Simulation.
**Tone:** "War Stories". The goal is not to answer correctly, but to answer *competently*.

---

## Zone 1: Financial & Strategic Engineering (Levels 1-20)
*The spreadsheet is mightier than the IDE.*

### Q1: "We need to cut costs. Why can't we just capitalize all our developer salaries to improve EBITDA?"
**A:** "Because that's accounting fraud, specifically **SOP 98-1**. We can only capitalize labor for *new functionality* or *major upgrades*, not maintenance or bug fixes. If I tell my team to mark 'Bug Fix' tickets as 'New Features', I go to jail. I track this strictly by Jira Epic types to protect the company during an audit."

### Q2: "Our cloud bill is $100k/month. Is that good or bad?"
**A:** "A raw number is meaningless. What is our **Unit Cost per Transaction**? If we spent $100k to process $1B, we are geniuses. If we spent $100k to process $100, we are bankrupt. I track *Cost Efficiency*, not just Cost. If the bill goes up 10% but revenue goes up 20%, I'm buying champagne, not cutting servers."

### Q3: "I see a lot of 'Unattached EBS Volumes' on the bill. Why?"
**A:** "That is the **'Lazy Tax'**. When a developer terminates an EC2 instance, they often forget to delete the attached storage. It's pure waste. I run a script every Friday: 'If Volume is detached > 7 days, snapshot and delete'. It saves us 15% immediately."

### Q4: "Datadog is raising our renewal price by 40%. What do we do?"
**A:** "We triggered the **'Renewal Squeeze'**. They secured us cheap, got us addicted, and now they are squeezing. I don't just pay it. I look at **Cardinality**. Are we logging 'User ID' in metric tags? That explodes the bill. First I sanitize our usage, then I negotiate a 3-year contract with a 5% cap on future increases."

### Q5: "Should we hire 10 more full-time engineers for this migration project?"
**A:** "No. Migrations are spiky. Once it's done, I don't want 10 people looking for work. I use **Contractors (OpEx)** for the migration. They are expensive hourly but cheaper long-term because I can release them when the project ends without morale capability damage."

### Q6: "Why do you pad your budget by 20%? Are you bad at math?"
**A:** "I am realistic about **'Unknown Unknowns'**. Hiring takes 4 months, not 1. Laptops get stuck in customs. A critical vendor will change their API and force a 2-week refactor. The 20% isn't slop; it's insurance against reality."

### Q7: "We are buying a startup for their tech. What is your due diligence?"
**A:** "I ignore the code; code is easy to rewrite. I look at **Vendor Contracts**. Did they sign a 5-year exclusive deal with Oracle? I look at **Key Person Retention**. If the CTO leaves, is the IP gone? The asset is the team, not the repo."

### Q8: "Why build our own Payment Switch instead of using Stripe?"
**A:** "Because Stripe takes 2.9%. At our scale ($50B volume), that fee is $1.5B/year. Building our own switch costs $10M/year but saves hundreds of millions. **Build vs. Buy** flips when the marginal cost of the vendor exceeds the fixed cost of the engineering team."

### Q9: "Marketing wants to buy a SaaS tool with a credit card. Why do you care?"
**A:** "Shadow IT becomes Security's nightmare. If they upload customer emails to a non-compliant tool, we violate GDPR. I don't block them, but I force it through **SSO (Single Sign-On)**. If we can't control access via Okta, they can't buy it."

### Q10: "This feature is delayed by 1 month. So what?"
**A:** "That is **Cost of Delay**. If this feature drives $1M/month, delaying it cost us $1M. I prioritize features by 'Cost of Delay', not 'Effort'. I'd rather ship an ugly MVP today and earn $1M than a perfect feature next month."

### Q11: "Why not buy 3-year Reserved Instances for everything?"
**A:** "Because RIs are a bet on **Architecture Stagnation**. If I reserve 100 m5.large instances, I am betting we won't move to Serverless Lambda next year. I only reserve the 'Base Load' (the minimum traffic we see at 3 AM). The rest stays On-Demand or Spot."

### Q12: "We are moving data from AWS to Azure. Why is the bill huge?"
**A:** "**Egress Fees**. Cloud providers let data *in* for free but charge a fortune to let it *out*. It's 'Data Gravity'. We must architect to process data where it lives. Do the analytics in AWS; only move the *results* out."

### Q13: "Oracle is auditing us. Should we worry?"
**A:** "Yes. Oracle audits **VMware Clusters**. If you run one Oracle VM on a cluster with 100 CPUs, they might charge you for *all 100 CPUs* because vMotion *could* move the VM anywhere. I isolate Oracle on physical hardware pinned to specific sockets to limit liability."

### Q14: "Refactor this legacy code. It's ugly."
**A:** "No. I don't refactor for aesthetics. I refactor for **ROI**. Will this refactor reduce latency? Will it speed up onboarding? If it's ugly but stable and rarely touched, I leave it alone. 'If it ain't broke, don't fix it' makes money."

### Q15: "We spent $5M on Project X. We can't just kill it."
**A:** "That is the **Sunk Cost Fallacy**. The $5M is gone. The only question is: 'Will the *next* $1M generate a return?' If no, we kill it today. Continuing to fund a failure just to 'save face' is how companies die."

### Q16: "Finance asks for a 10% budget cut. What do you cut?"
**A:** "I never cut 'Efficiency' projects. I offer cuts that have visible **Business Impact**. 'I can cut the Real-Time Dashboard. You will get data T+1 day.' This usually makes them find the money elsewhere. Pain must be shared."

### Q17: "Why is our Gross Margin only 50%? Software should be 80%."
**A:** "Our infrastructure is bloated. We are running single-tenant databases for every customer. We need to move to **Multi-Tenancy** (Shared Pool) to improve bin-packing density. It's an architecture change to fix a finance metric."

### Q18: "Calculate the TCO (Total Cost of Ownership) of this Microservice."
**A:** "It's not just EC2 cost. It's: Dev Salaries + On-Call Pay + Datadog Metrics + Data Egress + Security Audits. A 'Free' microservice often costs $50k/year just to keep alive."

### Q19: "Why are we paying for 'Premium Support'?"
**A:** "For the **SLA**. When the site goes down on Black Friday, I need a phone number that rings in 15 minutes, not a web form that replies in 24 hours. It's insurance."

### Q20: "How do you track CapEx for developers?"
**A:** "I don't make them fill timesheets (they lie). I map **Jira Epics** to CapEx/OpEx. If the Epic is 'New Feature', all tickets under it are CapEx. Automated and audit-proof."

---

## Zone 2: Organizational "Warfare" & Design (Levels 21-40)
*Culture is what happens when you leave the room.*

### Q21: "How do you manage managers? Do you code review them?"
**A:** "No. I review their **Decisions**. 'Why did you choose NoSQL?' 'Why did you promote Bob?' If they can't explain the *Why*, they aren't managing. My job is to be the 'Chief Question Officer'."

### Q22: "We have a 10x engineer who is a jerk. Keep or fire?"
**A:** "Fire. Immediately. A 'Brilliant Jerk' is a **Multiplier of Zero**. They output 10 units, but they cause 5 other people to output 0 units (or quit). The net system output is negative."

### Q23: "The teams are moving too slow. Should we combine them?"
**A:** "Look at **Conway's Law**. If we have a Monolithic Team, we get Monolithic Code. If we want Microservices, we need small, decoupled teams (Two-Pizza Rule). I don't combine; I split."

### Q24: "How big should a team be?"
**A:** "**7 +/- 2**. Below 5, they fracture if one person leaves. Above 9, communication overhead ($N(N-1)/2$) kills velocity. At 10, I split into two squads."

### Q25: "Bob is the only one who knows the legacy core. What is the risk?"
**A:** "**Bus Factor = 1**. If Bob leaves, we die. I force Bob to take a 2-week vacation. The things that break while he is gone are my roadmap. We document/automate them immediately."

### Q26: "Do you hire for experience or potential?"
**A:** "I hire for **Slope**, not Y-Intercept. I'd rather have a junior who learns 50% faster than a senior who hasn't learned a new tool in 5 years. Technology changes too fast for static knowledge."

### Q27: "How do you handle a PIP (Performance Improvement Plan)?"
**A:** "Honestly. I tell them: 'There is a gap between expectations and reality. Here is the plan to close it. If we can't close it in 30 days, we part ways.' A PIP is not a punishment; it's a final clarity check."

### Q28: "What is a Skip-Level meeting for?"
**A:** "To find the **'Ground Truth'**. Managers filter information up. I ask individual contributors: 'What is the stupidest thing we do here?' They always know the broken process that my Managers are hiding."

### Q29: "How do you get other teams to do what you want?"
**A:** "Influence without Authority. I find their **KPI**. How does *my* request help *them* get *their* bonus? 'If you integrate this API, your team's incident rate will drop 50%'. Sell the benefit, not the work."

### Q30: "The team disagrees with your decision. What now?"
**A:** "**Disagree and Commit**. We debate fiercely. But once I decide, I expect them to execute as if it were their own idea. Sabotage via passive-aggressiveness is not tolerated."

### Q31: "Why do we have 'Hero Culture'?"
**A:** "Heroes are a **symptom of failure**. If someone has to work all weekend to save a release, our process is broken. I don't praise the hero; I fix the pipeline so the hero isn't needed."

### Q32: "How do you explain Stock Options to 22-year-olds?"
**A:** "I teach **'Golden Handcuffs'**. 'This equity vest is worth $50k/year. If you leave, you walk away from $50k.' Retention is about making it economically painful to leave."

### Q33: "Why focus on Diversity?"
**A:** "To remove **Blind Spots**. A homogeneous team builds for themselves. A diverse team builds for the world. If we only hire men, we miss usability issues for 50% of the population. It's ROI."

### Q34: "Rumors are spreading about layoffs. What do you do?"
**A:** "Fill the **Vacuum**. In the absence of truth, people invent fear. I hold an All-Hands. I tell them exactly what I know and what I don't know. 'I cannot promise certainty, but I promise transparency'."

### Q35: "When do you promote someone?"
**A:** "When they are **already doing the job**. A promotion is a lagging indicator. I don't promote on potential; I promote on proven execution."

### Q36: "What is Psychological Safety?"
**A:** "The belief that **mistakes won't be punished**. If I scream at someone for breaking Prod, they will hide the next bug. If I say 'Great catch, how do we automate prevention?', they will report everything."

### Q37: "How do you fire someone?"
**A:** "Fast and humane. The firing should **never be a surprise**. They should have seen it coming for months (via 1:1s). The actual meeting is 5 minutes: 'Decision is made. Here is the package. Thank you'."

### Q38: "We need to RIF (Reduction in Force) 20% of the org."
**A:** "Map the **Critical Path**. Who maintains the revenue engines? Keep them. Cut the speculative R&D. Do it once. Do not do 'rolling layoffs'. Cut deep so the survivors feel safe."

### Q39: "People want to switch teams. Should we let them?"
**A:** "Yes. **Internal Mobility**. If I block them from moving to Team B, they won't stay on Team A. They will go to Google. Better to keep the talent in the company."

### Q40: "What is your legacy?"
**A:** "A team that **doesn't need me**. If I can leave for 3 months and the department grows, I succeeded. If it collapses, I failed."

---

## Zone 3: The Architecture of Money (Levels 41-60)
*Accuracy > Availability > Latency. The "Deep Dive".*

### Q41: "How do you architect a Ledger for 100% accuracy?"
**A:** "**Double-Entry Accounting**. Never update a balance (`balance = balance + 10`). Always insert two rows: Credit User A, Debit User B. The sum of the transaction table must effectively be zero. This provides an audit trail for every cent."

### Q42: "Network timed out sending a payment. Did it go through?"
**A:** "We don't know. That's why **Idempotency** is God. The client sends a unique key (`req_123`). If we receive `req_123` again, we check our database. 'Oh, we already processed this. Return Success.' We never process the same money movement twice."

### Q43: "Explain ISO 8583 vs ISO 20022. Why the pain?"
**A:** "ISO 8583 is a **Bitmap** protocol from the 80s. It packs data into bits to save bandwidth. Field 2 is PAN, Field 4 is Amount. It's unreadable but fast.
ISO 20022 is **XML**. It's verbose, hierarchical, and rich.
The pain is **Mapping**. 8583 has 'Private Use' fields where banks hacked in custom data. 20022 is strict. You can't just 'convert' it; you have to interpret the business intent of the hacked fields."

### Q44: "How do you secure Credit Card numbers (PCI-DSS)?"
**A:** "I **Tokenize** everything. The PAN (Primary Account Number) enters the DMZ, gets swapped for a Token (`tkn_55a`) by the Vault, and only the Token travels internally. The CDE (Cardholder Data Environment) is tiny. If hackers steal the database, they get tokens, not cards."

### Q45: "What is an HSM (Hardware Security Module)?"
**A:** "A dedicated physical crypto-processor. We don't store master keys in RAM or on Disk. The keys live inside the HSM. We send the data *to* the HSM to be encrypted. Even if you have root access to the server, you can't extract the key."

### Q46: "HSMs are slow (20ms latency). How do we handle 50k TPS?"
**A:** "**Keyset Hierarchy**. Use the HSM to encrypt a 'Data Key' (DEK). Cache the DEK in memory (for a short time) to encrypt data. Rotate the DEK frequently. Minimizing round-trips to the HSM is the only way to scale."

### Q47: "Settlement vs. Authorization. What's the diff?"
**A:** "Authorization is **Synchronous/Real-time**: 'Can Alice buy this coffee?' (Yes/No).
Settlement is **Asynchronous/Batch**: 'Move the $5 from Alice's Bank to the Merchant's Bank'. This happens overnight via ACH/FedNow. You rarely settle in real-time."

### Q48: "The Bank is down. Do we decline the user?"
**A:** "No. We do **STIP (Stand-In Processing)**. If the upstream bank times out, *we* act as the bank. We approve transactions < $50 if the user has a good history. We take the credit risk to preserve the customer experience."

### Q49: "How do you handle 'Eventual Consistency' in payments?"
**A:** "You don't. Payments must be **Strongly Consistent**. You can't show a user 'Balance: $1000' and let them spend it if the backend thinks 'Balance: $0'. We use ACID databases (Postgres/Oracle) for the ledger. We use Eventual Consistency only for the 'Transaction History' view."

### Q50: "Optimistic vs Pessimistic Locking for Balance updates?"
**A:** "**Optimistic Locking** (`UPDATE accounts SET bal = bal-10 WHERE id=1 AND version=5`). It scales better. Pessimistic Locking (`SELECT FOR UPDATE`) holds database connections open too long and causes deadlocks at high concurrency."

### Q51: "How do you prevent Race Conditions on 'Double Spend'?"
**A:** "The **Database Constraint** is the final guard. An `UNIQUE` constraint on `(transaction_id)` or the Optimistic concurrency `version` check. Application logic is never enough because two app servers can run in parallel."

### Q52: "How do you sync clocks across servers?"
**A:** "You can't trust NTP perfectly. Clocks skew. I design for **Clock Independence**. I use 'Vector Clocks' or causal identifiers for ordering. For the Ledger, I rely on the single source of truth (the DB transaction time), not the app server time."

### Q53: "Visa is timing out. Threads are piling up. What happens?"
**A:** "**Circuit Breaker Pattern**. If 50% of calls to Visa fail, the Circuit 'Opens'. We instantly fail all future calls for 30 seconds without even trying. This frees up threads and gives Visa time to recover. 'Fail Fast' saves the system."

### Q54: "Compliance says German data must stay in Germany."
**A:** "We need **Geo-Sharding**. Users in EU partition live in the Frankfurt region DB. Users in US live in Virginia. The app router directs traffic based on User ID. We treat regions as separate 'Silos' to respect Data Residency."

### Q55: "The Mainframe shuts down every night at 1 AM. How are we 24/7?"
**A:** "**Store and Forward**. When Mainframe is down, we capture the transaction, store it in a durable queue (Kafka), and acknowledge the user (or STIP verify). When Mainframe wakes up, we replay the queue. It's 'Asynchronous processing' forced by legacy."

### Q56: "Active-Active Architecture. Two Data Centers writing data. Conflicts?"
**A:** "It's the hardest problem. Start with **Active-Passive**. If you must go Active-Active, shard users. 'East Users' -> 'East DC'. 'West Users' -> 'West DC'. Avoid 'Bi-directional replication' of the same user's data unless you enjoy resolving merge conflicts manually."

### Q57: "Fraud check takes 200ms. We only have 500ms total. Help."
**A:** "Parallelize. Call the Fraud Model, The Ledger Read, and the Token Vault **in parallel**. Await all. If Fraud takes too long, we might have a 'Timeout Policy': 'If < $20, approve anyway'. Speed vs. Risk trade-off."

### Q58: "Security says 'Zero Trust'. What does that look like?"
**A:** "**mTLS (Mutual TLS)** everywhere. Service A doesn't just talk to Service B. Service A presents a certificate. B verifies it. We encrypt inside the VPC. We assume an attacker is *already* on the network."

### Q59: "How do we report on data without killing the Prod DB?"
**A:** "**Change Data Capture (CDC)**. We read the Postgres WAL (Write Ahead Log) and stream changes to Snowflake via Kafka. Reporting runs on Snowflake. We never run `SELECT *` analytics on the transactional ledger."

### Q60: "Mobile app makes 10 calls to render the screen. It's slow."
**A:** "**BFF (Backend for Frontend)**. The Mobile App calls `/home` once. The BFF (GraphQL or specific API) makes the 10 fast internal calls, aggregates the JSON, and sends one packet back. Minimizes radio latency."

---

## Zone 4: Operational Resilience & Executive Presence (Levels 61-80)
*When the sky falls, you hold the roof.*

### Q61: "SLA vs SLO. What's the difference?"
**A:** "SLA is the **Contract** (If we fail, we pay lawyers). SLO is the **Internal Goal** (If we fail, pagers go off). I set the SLO tighter than the SLA (e.g., 99.9% SLO for a 99.0% SLA) so we have a safety buffer to react before we owe money."

### Q62: "We burned our Error Budget. Now what?"
**A:** "**Code Freeze**. We stop shipping features. The team works *only* on stability bugs and reliability tooling until the budget resets. Product hates this, but it's the only way to enforce 'Reliability is Feature #1'."

### Q63: "Outage! Who is in charge?"
**A:** "The **Incident Commander (IC)**. It is *not* the CEO. It is not me. It is a trained engineer. I shut up and let the IC run the checklist. My job is to handle the Executive Communication so the IC isn't distracted by the CEO asking 'Are we up yet?'"

### Q64: "How much data can we lose? (RPO)"
**A:** "For payments? **Zero (RPO=0)**. This implies synchronous replication to a standby DB. It costs performance (latency of light to the replica), but we cannot explain 'lost money' to a regulator."

### Q65: "How do we test 'Resilience'?"
**A:** "**Chaos Engineering**. We don't just hope. We kill a random node in Prod during the day. If the system flickers, we fix it. Resilience is a muscle; it must be exercised."

### Q66: "Deployment fears. How do we mitigate?"
**A:** "**Shadow Mode**. We deploy the new engine. We send it a copy of live traffic. We compare its output to the old engine's output. We do this for a week. We prove mathematical equivalence before we ever switch the real user traffic."

### Q67: "The Federal Reserve Examiner is here. They want logs."
**A:** "**Evidence over Assurance**. I don't say 'We are secure'. I say 'Here is the Jira ticket #123 where we rotated the key, timestamped by the system'. If it's not documented, we fail the exam."

### Q68: "What is our Risk Appetite?"
**A:** "That is a Board decision. Do we accept 'Low' risk for 'High' speed? In Payments, the appetite for Core Ledger risk is **Zero**. For the 'Rewards System', the appetite might be 'Moderate'. We architect differently for each."

### Q69: "Board Meeting. Site was down. What do you say?"
**A:** "I speak **Business Impact**. Not 'Kubernetes DNS failed'. I say 'We lost 5% of transaction volume for 10 minutes. Revenue impact $50k. Root cause identified. Fix deployed to prevent recurrence.' They care about the $50k, not the DNS."

### Q70: "This microservice is a mess. What do we do?"
**A:** "**Technical Bankruptcy**. We declare it. We don't refactor; we rewrite or delete. Sometimes the best code is *no code*. Deprecating a feature to remove complexity is a strategic win."

### Q71: "Canary Releases. Why?"
**A:** "Because 'It worked on my machine' is a lie. We deploy to **1% of users**. If metrics (Error Rate) deviate by 0.1%, the system auto-rollbacks. Humans are too slow to catch a 1% error rate."

### Q72: "Disaster Recovery. Does it work?"
**A:** "Only if we verify it. **Game Days**. We physically cut the connection to US-East-1. Does traffic flow to US-West-2? Usually, we find hardcoded config files that fail. We find this in the drill, not the disaster."

### Q73: "Server crashed. Root cause?"
**A:** "Keep asking **5 Whys**.
1. Server crashed. Why? OOM.
2. Why? Memory leak.
3. Why? New code change.
4. Why? Bad code review.
5. Why? **Urgency/Culture**. The root cause is almost always *Process* or *people*, not the server."

### Q74: "Datadog bill exploded. Why?"
**A:** "**Cardinality**. Someone added `TransactionID` (a UUID) as a Tag. Now we have 50 million unique time-series. I enforce a 'Tag Governance' policy in CI/CD to block high-cardinality tags."

### Q75: "DDOS Attack. We are drowning."
**A:** "**Load Shedding**. We can't serve everyone. Drop the Free Tier. Drop the 'Get History' API. Save the 'Checkout' API for VIP users. Survival mode means amputating a limb to save the body."

### Q76: "What causes the most outages?"
**A:** "**Expired Certificates**. It's embarrassing. I use `cert-manager` to auto-rotate everything. A human should never touch a cert."

### Q77: "SolarWinds. Supply Chain Attack. Are we safe?"
**A:** "We lock our dependencies. We use a private Artifactory. We scan every NPM package for CVEs before it enters our build. We assume the public internet is poisoned."

### Q78: "Rogue Admin deletes the DB. How?"
**A:** "They shouldn't be able to. **Two-Person Rule**. Deleting a Prod DB requires two separate admin approvals in the console. No single human has the power to destroy the company."

### Q79: "Runbooks. Are they useful?"
**A:** "Only if they are **Executable**. A wiki page saying 'Check the logs' is useless at 3 AM. A script saying `./diagnose_payments.sh` is useful. Automate the runbook."

### Q80: "When do you leave?"
**A:** "When the team **no longer needs me**. My goal is to build a machine that runs itself. When I am the bottleneck to nothing, I have reached the next level."

---

*Study these. Speak them out loud. In the interview, pivot every question back to a specific "War Story" from this list.*
