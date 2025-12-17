# Mastercard Director Software Engineering - Interview Preparation Guide

**Tone:** Conversational, Experienced, Honest. "War stories" over textbook definitions. Deep technical depth with practical insights.

---

## Table of Contents
1. [Leadership & Team Management](#leadership--team-management)
2. [Payments Domain Expertise](#payments-domain-expertise)
3. [Strategic Engineering Leadership](#strategic-engineering-leadership)
4. [Operational Excellence & SRE](#operational-excellence--sre)
5. [Vendor & Partner Management](#vendor--partner-management)
6. [Budget & Resource Planning](#budget--resource-planning)
7. [Technical Architecture Deep Dives](#technical-architecture-deep-dives)
8. [Interview Scenarios & War Stories](#interview-scenarios--war-stories)

---

## Leadership & Team Management

### Building High-Performing Global Teams

**Question:** You're tasked with building a new engineering team across 3 time zones (US, India, Europe) for a critical payments platform. Walk me through your approach.

**Answer:**
I've done this twice in my career, and the key is establishing **trust before process**. Here's what actually works:

**Week 1-2: Foundation**
- **Kickoff calls** where each person shares: their background, what they're excited about, and one thing they want to learn. Not just "what do you do" - make it personal.
- **Pair programming sessions** across time zones. I pair a senior US engineer with a junior India engineer on a real feature. This breaks down barriers faster than any "team building" exercise.
- **Documentation standards** from day one. I learned the hard way - when someone in Bangalore asks "how do I deploy?" at 2 AM their time, good docs save relationships.

**Real War Story:**
At my last company, we had a critical bug in production. The US team (where I was) found it at 3 PM EST. By the time we understood the scope, it was 1 AM in India. Instead of waking people up, I:
1. Documented everything we knew
2. Created a detailed runbook
3. Left a Slack message: "When you wake up, here's what we need. No pressure, but this is blocking."

The India team came online, saw the context, and had a fix ready by 9 AM EST. They felt respected, not micromanaged. That incident became our template for async collaboration.

**Month 1-3: Establishing Rhythm**
- **Daily standups** but async-first. We use Slack threads where people post updates. Only sync calls for blockers that need real-time discussion.
- **Code review rotation**. Every PR gets reviewed by someone from a different timezone. This ensures knowledge sharing and prevents silos.
- **Blame-free postmortems**. When something breaks, we focus on "what did we learn?" not "who screwed up?" I've seen teams fall apart because someone got thrown under the bus.

**Follow-up Depth 1:** How do you handle performance issues across cultures?

**A:** This is where many leaders fail. I've learned that **direct feedback doesn't translate across cultures**. In some cultures, being direct is seen as aggressive. In others, indirect feedback is seen as weak.

My approach:
1. **One-on-ones are sacred**. I schedule weekly 1:1s with every direct report, no matter what. These are 30 minutes, and I never cancel them. This is where real conversations happen.
2. **Use the SBI framework**: Situation, Behavior, Impact. "In the code review last week, when you approved without checking the test coverage, it caused us to deploy a bug. Can we talk about how to prevent this?"
3. **Ask, don't tell**. Instead of "You need to improve your code quality," I ask "What do you think went well in that project? What would you do differently?" Often they know the issue and just need support.

**Real Example:** I had an engineer in India who was brilliant but struggled with communication. Instead of saying "you need to communicate better," I:
- Recorded a few of my own presentations and shared them
- Did mock presentations with him before important meetings
- Gave him specific feedback: "Your technical content is great, but try pausing after each slide to check for questions"

Within 3 months, he was leading architecture reviews. The issue wasn't capability - it was confidence and technique.

**Follow-up Depth 2:** How do you retain top talent in a competitive market?

**A:** Money matters, but it's not everything. I've lost people to competitors offering 30% more, and I've kept people who turned down 50% raises. Here's what actually works:

1. **Growth opportunities over titles**. I had a senior engineer who wanted to become a staff engineer. Instead of promoting immediately, I created a 6-month growth plan:
   - Lead a cross-team initiative (visibility)
   - Mentor 2 junior engineers (leadership)
   - Present at an internal tech talk (communication)
   - Complete a system design review (architecture)

   By the end, the promotion was obvious to everyone. He felt he earned it, not that it was given.

2. **Autonomy with guardrails**. Top performers hate micromanagement. I give them ownership of entire features, but with clear boundaries:
   - "You own this API. Here are the SLAs we need to hit. Here's the budget. Go build it."
   - Weekly check-ins are "what do you need from me?" not "what did you do?"

3. **Recognition that matters**. Public recognition in team meetings, but also private notes to their manager's manager. I write quarterly "impact summaries" for my top performers and share them with leadership. This helps with their career trajectory.

**War Story:** I had a rockstar engineer who was getting bored. He came to me saying he was thinking about leaving. Instead of counter-offering immediately, I asked: "What would make you stay?" 

He said: "I want to work on something that matters. I'm tired of CRUD APIs."

So I gave him ownership of our fraud detection system - a critical, complex problem with real impact. He rebuilt it from scratch, reduced false positives by 40%, and stayed for 3 more years. The key was listening to what he actually wanted, not what I thought he wanted.

**Follow-up Depth 3:** How do you handle underperformers?

**A:** This is the hardest part of leadership, and I've made mistakes here. The key is: **assume good intent, but act quickly**.

**Step 1: Diagnose, don't assume**
I had an engineer who was missing deadlines. My first thought was "lazy" or "not capable." But when I dug in:
- He was spending 80% of his time fixing bugs in legacy code
- He was afraid to ask for help because the last manager made him feel stupid
- He was working 60-hour weeks trying to catch up

The problem wasn't him - it was the system. I:
- Gave him a mentor to help with the legacy codebase
- Reduced his scope so he could focus
- Made it clear that asking for help was expected, not weakness

Within 2 months, he was one of my top performers. The issue was support, not capability.

**Step 2: Clear expectations and timelines**
If it's truly a performance issue, I set a 90-day improvement plan:
- Week 1: Clear conversation about what's not working, specific examples
- Week 2-4: Daily check-ins, pair programming, clear goals
- Week 5-8: Weekly check-ins, measurable progress
- Week 9-12: Final assessment

I document everything. Not to build a case, but to be fair. Sometimes people improve. Sometimes they realize this isn't the right fit and leave on their own. Sometimes I have to let them go.

**Step 3: The hard conversation**
I've had to let people go. It's never easy, but I do it with respect:
- Be direct but kind: "This isn't working. Here's why. Let's talk about what's next."
- Offer support: "I'll write you a reference. I'll help you find your next role."
- Don't make it personal: "This role isn't a fit for your skills" not "You're not good enough."

**Real Example:** I had to let go of an engineer who was technically capable but couldn't work in a team. He'd rewrite other people's code without discussion, skip code reviews, and create technical debt. After 6 months of coaching, it was clear he needed a different environment (maybe a solo contributor role). I helped him find a role that fit, and he's thriving now. Sometimes the best thing you can do is help someone find the right fit.

---

### Vendor & Partner Management

**Question:** You're managing a team that includes 40% vendor engineers (contractors/consultants). How do you ensure quality and integration?

**Answer:**
I've managed vendor relationships for 8 years, and the biggest mistake is treating vendors as "less than" your FTE team. Here's what works:

**1. Onboarding is critical**
Vendors often get thrown into projects with minimal context. I invest 2 weeks in proper onboarding:
- **Technical deep dive**: Architecture walkthrough, codebase tour, deployment process
- **Cultural integration**: Invite them to team lunches, include them in Slack channels, make them feel part of the team
- **Clear expectations**: "You're not here to just code. You're here to contribute ideas, challenge assumptions, and help us build better software."

**War Story:** We had a vendor team in Eastern Europe working on a critical feature. They were technically strong but kept missing requirements. After digging in, I realized:
- They were getting requirements through 3 layers of management (lost in translation)
- They were afraid to ask questions because their contract said "billable hours only"
- They were working in isolation, never talking to our product team

I fixed it by:
1. Having them join our daily standups (even if it was 6 PM their time)
2. Setting up direct Slack channels between vendor engineers and our product managers
3. Including them in architecture discussions

Within a month, quality improved 10x. The issue wasn't capability - it was communication and inclusion.

**2. Quality gates are non-negotiable**
Vendors must follow the same standards:
- Code reviews by our senior engineers
- Same testing requirements (unit, integration, E2E)
- Same deployment process
- Same on-call rotation (if applicable)

I've seen vendors try to skip these "to save time." That's a red flag. Quality isn't negotiable.

**3. Knowledge transfer is mandatory**
Vendors leave. That's reality. So I require:
- **Documentation**: Every feature they build must have runbooks, architecture docs, and code comments
- **Pairing**: Vendor engineers pair with FTE engineers on critical features
- **Presentations**: Monthly tech talks where vendors share what they learned

This ensures we're not left with a black box when they leave.

**Follow-up Depth 1:** How do you handle vendor cost overruns?

**A:** Prevention is better than cure. I set up:
- **Fixed-price contracts** for well-defined work (new features with clear requirements)
- **Time-and-materials with caps** for exploratory work (proof of concepts, research)
- **Weekly budget reviews**: Every Monday, I review hours billed vs. budget. If we're trending over, I catch it early.

**Real Example:** We had a vendor working on a feature that was supposed to take 3 months. After 2 months, they said "we need 2 more months." 

Instead of just approving, I:
1. Asked for a detailed breakdown: What took longer? Why?
2. Reviewed their code: Was it over-engineered? Were they gold-plating?
3. Brought in a senior engineer: "Can we simplify this? What's the MVP?"

We found they were building features we didn't ask for. We scoped it back, they delivered in 3.5 months total. The key is asking "why" not just "how much."

---

## Payments Domain Expertise

### ISO 8583 Deep Dive

**Question:** Explain ISO 8583 and when you'd use it vs. ISO 20022.

**Answer:**
ISO 8583 is the **legacy workhorse** of payment processing. It's a binary message format that's been around since the 1980s. Here's the reality:

**Structure:**
- **Message Type Indicator (MTI)**: 4 digits defining the message type (e.g., 0200 = financial transaction request)
- **Bitmap**: 128 bits indicating which data elements are present
- **Data Elements**: Fixed or variable length fields (e.g., DE2 = PAN, DE3 = Processing Code, DE4 = Amount)

**Why it's still relevant:**
- **Speed**: Binary format = minimal parsing overhead. Critical for high-volume, low-latency systems (ATMs, POS terminals)
- **Legacy systems**: Most card networks (Visa, Mastercard) still use ISO 8583 for authorization
- **Compact**: A typical authorization message is ~200 bytes vs. 2-3KB in XML/JSON

**Real War Story:**
I worked on a payment gateway processing 10,000 TPS. We tried migrating from ISO 8583 to ISO 20022 (XML). The XML parsing added 15ms latency per transaction. At scale, that's 150 seconds of latency per second of processing time. We kept ISO 8583 for the hot path and only used ISO 20022 for settlement/reconciliation.

**When to use ISO 8583:**
- Real-time authorization (POS, ATM, e-commerce)
- High-volume, low-latency requirements (<100ms)
- Legacy system integration
- Card-present transactions

**When NOT to use ISO 8583:**
- New systems with no legacy constraints
- Rich data requirements (ISO 20022 is more flexible)
- Cross-border payments (ISO 20022 is the standard)
- Regulatory reporting (ISO 20022 has better compliance support)

**Follow-up Depth 1:** Walk me through an ISO 8583 authorization flow.

**A:** Let me walk through a real card swipe at a POS terminal:

**Step 1: Card Read**
- POS terminal reads card (magnetic stripe or chip)
- Extracts PAN (Primary Account Number), expiry, CVV
- Creates ISO 8583 message:
  ```
  MTI: 0200 (Financial Transaction Request)
  DE2: PAN (card number)
  DE3: Processing Code (00 00 00 = purchase)
  DE4: Amount (e.g., 5000 = $50.00)
  DE7: Transmission Date/Time
  DE11: STAN (System Trace Audit Number) - unique per transaction
  DE12: Local Transaction Time
  DE13: Local Transaction Date
  DE14: Expiry Date
  DE22: Point of Service Entry Mode (chip vs. swipe)
  DE35: Track 2 Data (from magnetic stripe)
  DE41: Terminal ID
  DE42: Merchant ID
  DE49: Currency Code (840 = USD)
  ```

**Step 2: Routing**
- POS sends to Acquirer (merchant's bank)
- Acquirer routes to Card Network (Visa/Mastercard)
- Network routes to Issuer (cardholder's bank)

**Step 3: Authorization**
- Issuer checks: balance, fraud rules, card status
- Creates response:
  ```
  MTI: 0210 (Financial Transaction Response)
  DE39: Response Code
    00 = Approved
    05 = Do Not Honor (insufficient funds)
    14 = Invalid Card Number
    51 = Insufficient Funds
    54 = Expired Card
  DE38: Authorization Code (if approved)
  DE4: Amount (echoed back)
  ```

**Step 4: Settlement (End of Day)**
- Merchant batches all transactions
- Sends batch file (ISO 8583 0500 = Reversal, 0520 = Acquirer Reversal)
- Network settles with Issuer
- Funds move from Issuer to Acquirer to Merchant

**Real Implementation Challenge:**
I built an ISO 8583 parser in Java. The tricky part is the bitmap. Here's how it works:

```java
// Bitmap indicates which data elements are present
// Bit 1 = DE1 present, Bit 2 = DE2 present, etc.
// First bitmap covers DE1-DE64, second bitmap covers DE65-DE128

byte[] bitmap = message.getBitmap();
boolean hasDE2 = (bitmap[0] & 0x40) != 0; // Bit 2 (0-indexed)
boolean hasDE3 = (bitmap[0] & 0x20) != 0; // Bit 3

// Variable length fields require length indicators
// DE2 (PAN) is LLVAR: first 2 bytes = length, rest = data
if (hasDE2) {
    int panLength = (bitmap[1] & 0xFF) << 8 | (bitmap[2] & 0xFF);
    String pan = new String(bitmap, 3, panLength);
}
```

The challenge: Different networks have different interpretations. Visa uses BCD (Binary Coded Decimal), Mastercard uses ASCII. You need network-specific parsers.

**Follow-up Depth 2:** How do you handle ISO 8583 message versioning?

**A:** This is where it gets messy. ISO 8583 has multiple versions (1987, 1993, 2003), and each network (Visa, Mastercard, Amex) has customizations.

**Approach 1: Version in MTI**
Some implementations use the MTI format:
```
MTI: YYYY
Y = Version (0=1987, 1=1993, 2=2003)
YY = Message Class (1=Authorization, 2=Financial, etc.)
YY = Message Function (0=Request, 1=Response, etc.)
```

**Approach 2: Version Header**
Add a version field before the MTI:
```
[Version Byte][MTI][Bitmap][Data Elements]
```

**Approach 3: Network Detection**
Detect network from PAN (first 6 digits = BIN) and use network-specific parser:
```java
String bin = pan.substring(0, 6);
if (bin.startsWith("4")) {
    // Visa - use Visa-specific parser
} else if (bin.startsWith("5")) {
    // Mastercard - use Mastercard-specific parser
}
```

**Real War Story:**
We integrated with 3 different acquirers, each using different ISO 8583 variants. Instead of building 3 parsers, I built a **configurable parser**:
- YAML config defining field positions, lengths, formats per network
- Runtime selection based on BIN or merchant config
- This allowed us to add new networks without code changes

---

### ISO 20022 vs. ISO 8583

**Question:** When would you choose ISO 20022 over ISO 8583?

**Answer:**
ISO 20022 is the **modern standard** for payments. It's XML/JSON-based, human-readable, and supports rich data. Here's the reality:

**ISO 20022 Advantages:**
- **Rich data**: Can include full remittance information, regulatory data, multiple parties
- **Extensible**: Easy to add new fields without breaking existing systems
- **Human-readable**: Easier to debug, audit, and integrate
- **Regulatory compliance**: Better support for AML, KYC, sanctions screening
- **Cross-border**: Standard for SEPA, SWIFT, cross-border payments

**When to use ISO 20022:**
- **New systems**: No legacy constraints
- **Cross-border payments**: Required for SEPA, SWIFT
- **Corporate payments**: Need rich remittance data
- **Regulatory reporting**: Better compliance support
- **Settlement/Reconciliation**: Not latency-sensitive

**Hybrid Approach (What I've Done):**
- **ISO 8583** for real-time authorization (hot path)
- **ISO 20022** for settlement, reporting, cross-border

**Real Example:**
I architected a payment system that:
1. Receives ISO 8583 authorization requests (low latency)
2. Converts to ISO 20022 for settlement (rich data)
3. Uses ISO 20022 for regulatory reporting (compliance)

This gives us the best of both worlds: speed where it matters, richness where it's needed.

**Follow-up Depth 1:** How do you convert between ISO 8583 and ISO 20022?

**A:** This is a common requirement. Here's my approach:

**Mapping Strategy:**
```java
// ISO 8583 DE2 (PAN) -> ISO 20022 CreditorAccount/Identification
DE2: 4532123456789012
->
<CreditorAccount>
    <Identification>4532123456789012</Identification>
</CreditorAccount>

// ISO 8583 DE4 (Amount) -> ISO 20022 Amount
DE4: 5000 (cents)
->
<Amount>
    <InstdAmt Ccy="USD">50.00</InstdAmt>
</Amount>

// ISO 8583 DE7 (Transmission Time) -> ISO 20022 DateTime
DE7: 1203151234 (MMddHHmmss)
->
<CreDtTm>2024-03-15T12:34:00</CreDtTm>
```

**Challenges:**
1. **Data Loss**: ISO 8583 is compact, ISO 20022 is verbose. Some ISO 8583 fields don't map cleanly.
2. **Timing**: Conversion adds latency. Do it async for non-real-time flows.
3. **Idempotency**: Ensure same ISO 8583 message always converts to same ISO 20022 message.

**Implementation Pattern:**
```java
public class ISO8583ToISO20022Converter {
    public Document convert(ISO8583Message iso8583) {
        Document doc = createISO20022Document();
        
        // Map core fields
        mapPAN(iso8583.getDE2(), doc);
        mapAmount(iso8583.getDE4(), doc);
        mapDateTime(iso8583.getDE7(), doc);
        
        // Handle network-specific fields
        String bin = extractBIN(iso8583.getDE2());
        if (isVisa(bin)) {
            mapVisaSpecificFields(iso8583, doc);
        } else if (isMastercard(bin)) {
            mapMastercardSpecificFields(iso8583, doc);
        }
        
        return doc;
    }
}
```

**Real Implementation:**
We built this as a **Kafka Streams processor**:
- ISO 8583 messages flow through Kafka
- Stream processor converts to ISO 20022
- Outputs to separate topic for settlement
- This decouples real-time from batch processing

---

### Payment Processing Architectures

**Question:** Design a payment processing system handling 100,000 TPS with 99.99% uptime.

**Answer:**
I've built systems at this scale. Here's the architecture that actually works:

**Core Principles:**
1. **Decouple authorization from settlement** (different SLAs)
2. **Idempotency everywhere** (retries are inevitable)
3. **Circuit breakers** (fail fast, don't cascade)
4. **Event-driven** (async where possible)

**Architecture:**

```
[POS/API Gateway] 
    -> [Load Balancer] (AWS ALB/Nginx)
    -> [Authorization Service] (Spring Boot, stateless)
        -> [Redis] (session store, rate limiting)
        -> [Kafka] (event stream)
            -> [Fraud Detection] (async)
            -> [Settlement Service] (batch)
    -> [Card Network Adapter] (ISO 8583)
        -> [Visa/Mastercard APIs]
    -> [Database] (PostgreSQL, read replicas)
```

**Authorization Service (Hot Path):**
- **Stateless**: No session state in app servers
- **Connection pooling**: Reuse connections to card networks
- **Timeout handling**: Fail fast (200ms timeout, return error)
- **Idempotency**: Use STAN (System Trace Audit Number) as idempotency key

```java
@Service
public class AuthorizationService {
    @Autowired
    private RedisTemplate<String, String> redis;
    
    @Autowired
    private CardNetworkClient cardNetwork;
    
    public AuthorizationResponse authorize(AuthorizationRequest request) {
        // Idempotency check
        String idempotencyKey = request.getSTAN();
        String cached = redis.opsForValue().get("auth:" + idempotencyKey);
        if (cached != null) {
            return deserialize(cached);
        }
        
        // Rate limiting
        if (!rateLimiter.tryAcquire(request.getMerchantId())) {
            throw new RateLimitExceededException();
        }
        
        // Call card network with timeout
        CompletableFuture<NetworkResponse> future = 
            cardNetwork.authorize(request)
                .orTimeout(200, TimeUnit.MILLISECONDS);
        
        try {
            NetworkResponse response = future.get();
            
            // Cache response
            redis.opsForValue().set("auth:" + idempotencyKey, 
                serialize(response), 24, TimeUnit.HOURS);
            
            // Publish event for async processing
            kafkaTemplate.send("authorizations", response);
            
            return mapToResponse(response);
        } catch (TimeoutException e) {
            // Fail fast, don't block
            throw new AuthorizationTimeoutException();
        }
    }
}
```

**Fraud Detection (Async):**
- Don't block authorization on fraud checks
- Use Kafka for async processing
- If fraud detected, reverse authorization (reversal message)

**Settlement (Batch):**
- End-of-day batch processing
- Convert ISO 8583 to ISO 20022
- Reconcile with card networks
- Generate reports

**Follow-up Depth 1:** How do you handle partial failures in this system?

**A:** Partial failures are the reality. Here's how I handle them:

**1. Circuit Breakers**
```java
@CircuitBreaker(name = "cardNetwork", fallbackMethod = "fallbackAuthorize")
public AuthorizationResponse authorize(AuthorizationRequest request) {
    return cardNetworkClient.authorize(request);
}

public AuthorizationResponse fallbackAuthorize(AuthorizationRequest request, Exception e) {
    // Log for manual review
    kafkaTemplate.send("failed-authorizations", request);
    // Return error to merchant
    return AuthorizationResponse.rejected("Network unavailable, please retry");
}
```

**2. Retry with Exponential Backoff**
- First retry: 100ms
- Second retry: 200ms
- Third retry: 400ms
- Max 3 retries, then fail

**3. Dead Letter Queue**
- Failed messages go to DLQ
- Separate process reviews DLQ
- Manual intervention for edge cases

**4. Monitoring & Alerting**
- **SLIs**: Authorization latency (p50, p95, p99), success rate, error rate
- **SLOs**: 99.9% success rate, p99 latency < 200ms
- **Alerts**: When SLO breaches, page on-call engineer

**Real War Story:**
We had a card network outage. Our circuit breaker opened after 50% failure rate. But we had 10,000 transactions in flight. Here's what happened:

1. **In-flight transactions**: We had a timeout (200ms), so they failed fast
2. **Retries**: Exponential backoff prevented thundering herd
3. **DLQ**: Failed transactions queued for retry when network recovered
4. **Manual intervention**: We manually retried critical transactions (high-value, VIP merchants)

The key: **Fail fast, retry smart, monitor everything**.

---

## Operational Excellence & SRE

### Incident Management

**Question:** A critical payment system is down. Walk me through your incident response.

**Answer:**
I've been through dozens of incidents. Here's my playbook:

**Phase 1: Triage (0-5 minutes)**
1. **Acknowledge**: "We're aware of the issue, investigating now"
2. **Assess scope**: Is it all transactions? Specific merchants? Geographic?
3. **Check monitoring**: What's the error rate? Latency spike? Database CPU?
4. **Form war room**: Get the right people in a Slack channel/zoom

**Real Example:**
We had a production outage. First alert: "High error rate." I immediately:
- Checked dashboards: 80% error rate, all "timeout" errors
- Checked database: CPU at 100%, connection pool exhausted
- Checked recent deploys: Nothing in last 2 hours
- Checked external dependencies: Card network status page showed "operational"

Root cause: A batch job started running during peak hours, consuming all database connections.

**Phase 2: Mitigation (5-15 minutes)**
- **Stop the bleeding**: Kill the batch job, restart services, scale up
- **Communicate**: Update status page, notify stakeholders
- **Monitor**: Watch error rate drop, latency normalize

**Phase 3: Resolution (15-60 minutes)**
- **Fix root cause**: In our case, reschedule batch job to off-peak hours
- **Verify**: Run smoke tests, check metrics
- **Communicate**: "Issue resolved, monitoring for stability"

**Phase 4: Postmortem (Within 48 hours)**
- **What happened**: Timeline of events
- **Why it happened**: Root cause analysis
- **What we learned**: Action items to prevent recurrence
- **Blameless**: Focus on process, not people

**Key Principles:**
1. **Communicate early and often**: Better to over-communicate than leave people guessing
2. **Fix first, blame later**: Focus on restoring service, then learn
3. **Document everything**: Timeline helps with postmortem
4. **Rotate on-call**: Don't burn people out

**Follow-up Depth 1:** How do you prevent incidents?

**A:** Prevention is better than cure. Here's my approach:

**1. Chaos Engineering**
- **GameDay exercises**: Simulate failures (kill a database, network partition, high load)
- **Learn in staging**: Break things when it's safe
- **Build resilience**: Services should handle failures gracefully

**Real Example:**
We run monthly GameDays. Last month, we simulated a database failover. We discovered:
- Our connection pool wasn't configured for failover (connections to old primary)
- Our health checks were too slow (took 30 seconds to detect failure)
- Our retry logic wasn't working (retried against dead database)

We fixed all three before they caused a real incident.

**2. Canary Deployments**
- Deploy to 5% of traffic first
- Monitor metrics for 15 minutes
- If stable, roll out to 100%
- If issues, roll back immediately

**3. Feature Flags**
- Deploy code with feature disabled
- Enable for internal users first
- Enable for 10% of users
- Monitor, then full rollout

**4. Automated Testing**
- **Unit tests**: Fast feedback on code changes
- **Integration tests**: Verify service interactions
- **E2E tests**: Critical user journeys
- **Load tests**: Verify performance under load

**5. Monitoring & Alerting**
- **SLIs/SLOs**: Define what "good" looks like
- **Dashboards**: Real-time visibility
- **Alerts**: Page on-call when SLOs breach
- **Runbooks**: Document common issues and fixes

**Follow-up Depth 2:** How do you define SLIs and SLOs?

**A:** This is critical for operational excellence. Here's my framework:

**SLIs (Service Level Indicators):**
- **Availability**: Uptime percentage (e.g., 99.9% = 43 minutes downtime/month)
- **Latency**: Response time percentiles (p50, p95, p99)
- **Error Rate**: Percentage of requests that fail
- **Throughput**: Requests per second

**SLOs (Service Level Objectives):**
- **Target**: What we aim for (e.g., 99.9% availability)
- **Window**: Time period (e.g., 30-day rolling window)
- **Error Budget**: Allowed downtime (e.g., 43 minutes/month)

**Real Example:**
For a payment authorization service:
- **SLI**: Authorization success rate, p99 latency
- **SLO**: 99.9% success rate, p99 latency < 200ms
- **Error Budget**: 0.1% failure rate = 1,000 failed transactions per 1M

**Error Budget Policy:**
- **Green (>50% budget remaining)**: Normal development, can take risks
- **Yellow (25-50% budget)**: Be cautious, focus on reliability
- **Red (<25% budget)**: Freeze new features, focus on stability

**Implementation:**
```yaml
# SLO Configuration
slo:
  name: payment-authorization
  sli: success_rate
  target: 0.999  # 99.9%
  window: 30d
  error_budget: 0.001  # 0.1%
  
alerts:
  - name: error-budget-burn-rate
    condition: burn_rate > 2x  # Burning budget 2x faster than allowed
    severity: critical
```

**Follow-up Depth 3:** How do you balance feature velocity with reliability?

**A:** This is the eternal tension. Here's my approach:

**1. Error Budget as Currency**
- Each feature "costs" error budget
- If budget is low, features are "expensive" (need more testing, slower rollout)
- If budget is high, features are "cheap" (can move faster)

**2. Risk-Based Rollout**
- **Low-risk changes**: Fast rollout (config changes, bug fixes)
- **Medium-risk changes**: Canary deployment (new features)
- **High-risk changes**: Slow rollout (architecture changes, database migrations)

**3. Reliability Reviews**
- Before major features: "How does this affect reliability?"
- After incidents: "What can we learn?"
- Quarterly: Review SLOs, adjust if needed

**Real Example:**
We wanted to add a new fraud detection feature. The team wanted to ship fast. I asked:
- "What's the error budget impact?" (estimated 0.05% failure rate)
- "What's our current error budget?" (0.08% remaining)
- "Is this feature worth the risk?" (Yes, but let's reduce risk)

We:
- Added feature flag (can disable if issues)
- Deployed to 5% traffic first
- Monitored for 24 hours
- Rolled out gradually

Result: Feature shipped safely, error budget impact was only 0.02%.

---

## Strategic Engineering Leadership

### Aligning Engineering with Business Goals

**Question:** How do you ensure engineering work aligns with business objectives?

**Answer:**
This is where many engineering leaders fail. They focus on "building cool tech" instead of "solving business problems." Here's my approach:

**1. Understand the Business**
- **Attend business reviews**: Understand revenue, growth, challenges
- **Talk to customers**: What problems are they trying to solve?
- **Read earnings reports**: What are investors concerned about?

**Real Example:**
I was leading a team building a new payment feature. The business goal was "increase revenue by 10%." Instead of just building features, I:
- Analyzed payment data: Where were we losing transactions? (High decline rate)
- Talked to merchants: What were their pain points? (Slow authorization)
- Proposed solution: Reduce authorization latency by 50ms → reduce declines by 2% → increase revenue by 8%

The business loved it because I connected engineering work to business outcomes.

**2. Translate Business Goals to Engineering Metrics**
- **Business Goal**: Increase revenue
- **Engineering Metric**: Reduce authorization latency, increase success rate
- **Technical Work**: Optimize database queries, add caching, improve error handling

**3. Prioritize Based on Impact**
- **High Impact, Low Effort**: Do first (quick wins)
- **High Impact, High Effort**: Plan carefully (major initiatives)
- **Low Impact, Low Effort**: Do when time permits (nice-to-haves)
- **Low Impact, High Effort**: Don't do (avoid)

**4. Communicate in Business Terms**
- Instead of: "We're refactoring the authorization service"
- Say: "We're improving authorization speed by 50ms, which will reduce declines by 2% and increase revenue by $2M/year"

**Follow-up Depth 1:** How do you handle technical debt vs. new features?

**A:** This is a constant tension. Here's my framework:

**1. Quantify Technical Debt**
- **Cost of delay**: How much slower is development?
- **Risk**: What's the chance of an incident?
- **Maintenance cost**: How much time is spent on workarounds?

**Real Example:**
We had a legacy payment system. Every feature took 2x longer because of technical debt. I quantified it:
- **Current velocity**: 5 features/month
- **With refactoring**: 10 features/month
- **Cost of delay**: $500K/year in lost revenue
- **Refactoring cost**: 3 engineers × 3 months = $150K

ROI: $500K - $150K = $350K/year. Easy decision.

**2. Allocate Time for Debt**
- **20% rule**: 20% of engineering time on technical debt
- **Sprint allocation**: Every sprint includes some debt work
- **Dedicated sprints**: Quarterly "debt sprints"

**3. Prioritize High-Impact Debt**
- **High impact, high risk**: Fix immediately (security, reliability)
- **High impact, low risk**: Plan for next quarter
- **Low impact**: Defer or ignore

**4. Make Debt Visible**
- Track debt in Jira/backlog
- Include in sprint planning
- Report on debt reduction in reviews

**Follow-up Depth 2:** How do you justify engineering investments to executives?

**A:** Executives care about ROI, not technology. Here's how I present:

**Framework:**
1. **Problem**: What business problem are we solving?
2. **Solution**: What are we building?
3. **Impact**: What's the business impact? (Revenue, cost, risk)
4. **Investment**: What's the cost? (People, time, money)
5. **ROI**: What's the return?

**Real Example:**
**Problem**: Payment authorization latency is causing 2% decline rate, losing $2M/year in revenue.

**Solution**: Refactor authorization service, add caching, optimize database queries.

**Impact**: 
- Reduce latency by 50ms → reduce declines by 1% → increase revenue by $1M/year
- Reduce infrastructure costs by 20% → save $200K/year
- Reduce incidents by 50% → save $100K/year in on-call time

**Investment**: 3 engineers × 6 months = $300K

**ROI**: ($1M + $200K + $100K) - $300K = $1M/year return

**Presentation:**
- **Slide 1**: Problem (with data)
- **Slide 2**: Solution (high-level architecture)
- **Slide 3**: Impact (quantified)
- **Slide 4**: Investment (resources needed)
- **Slide 5**: ROI (return on investment)
- **Slide 6**: Timeline (milestones, deliverables)

**Key**: Always lead with business impact, not technology.

---

## Budget & Resource Planning

### Managing Engineering Budgets

**Question:** You have a $5M engineering budget. How do you allocate it across teams and initiatives?

**Answer:**
I've managed budgets from $2M to $20M. Here's my framework:

**Budget Allocation:**
1. **People (70-80%)**: Salaries, benefits, contractors
2. **Infrastructure (10-15%)**: Cloud costs, tools, licenses
3. **Training & Development (5%)**: Conferences, courses, certifications
4. **Contingency (5-10%)**: Unexpected costs, emergencies

**Real Example:**
$5M budget breakdown:
- **People**: $3.5M (70%)
  - 25 engineers @ $140K average = $3.5M
- **Infrastructure**: $750K (15%)
  - AWS: $500K
  - Tools (Datadog, GitHub, etc.): $150K
  - Licenses: $100K
- **Training**: $250K (5%)
  - Conferences: $100K
  - Training/certifications: $150K
- **Contingency**: $500K (10%)
  - Buffer for unexpected costs

**Follow-up Depth 1:** How do you forecast infrastructure costs?

**A:** This is critical for budget planning. Here's my approach:

**1. Baseline Current Costs**
- Review last 6 months of cloud bills
- Identify cost drivers (compute, storage, data transfer)
- Understand growth trends

**2. Model Growth**
- **Linear growth**: If we're growing 10% MoM, project forward
- **Step functions**: New features that add infrastructure
- **Seasonality**: Black Friday, holiday spikes

**Real Example:**
Current AWS spend: $50K/month
- **Baseline growth**: 5% MoM = $52.5K next month
- **New feature**: Payment processing = +$10K/month
- **Seasonality**: Black Friday = +50% in November = +$25K
- **Forecast**: $87.5K in November

**3. Cost Optimization**
- **Reserved Instances**: 40% savings for predictable workloads
- **Spot Instances**: 70% savings for batch jobs
- **Right-sizing**: Review instance sizes quarterly
- **Lifecycle policies**: Delete old data, archive to cheaper storage

**4. Track & Report**
- **Monthly reviews**: Actual vs. forecast
- **Variance analysis**: Why did we over/under-spend?
- **Adjust forecasts**: Update based on actuals

**Follow-up Depth 2:** How do you handle budget overruns?

**A:** Prevention is better than cure, but sometimes it happens:

**1. Early Warning System**
- **Weekly reviews**: Track spend vs. budget
- **Alerts**: When spend exceeds 80% of budget
- **Forecast updates**: Adjust forecasts monthly

**2. When Overrun Happens**
- **Root cause**: Why did we overrun? (Unexpected growth? Inefficient code?)
- **Options**: 
  - Request additional budget (if justified)
  - Reduce scope (defer features)
  - Optimize costs (right-size, reserved instances)
- **Communication**: Be transparent with leadership

**Real Example:**
We had a 20% overrun. Root cause: Unexpected traffic growth (50% more than forecast). Options:
1. Request additional budget: $1M → $1.2M
2. Reduce scope: Defer 2 features
3. Optimize: Reserved instances, right-size → save $200K

We did #3 (optimize) and #1 (request $200K additional). Leadership approved because growth was a good problem to have.

---

### Resource Planning & Forecasting

**Question:** How do you forecast headcount needs for the next 12 months?

**Answer:**
This is a critical skill. Here's my approach:

**1. Understand Current Capacity**
- **Current team**: How many engineers?
- **Velocity**: How many features/story points per sprint?
- **Utilization**: What % of time is billable vs. overhead?

**Real Example:**
- 25 engineers
- Average velocity: 50 story points/sprint
- Utilization: 70% (30% overhead: meetings, admin, etc.)
- Effective capacity: 25 × 0.7 = 17.5 FTE

**2. Forecast Demand**
- **Product roadmap**: What features are planned?
- **Technical debt**: What needs to be addressed?
- **Operational work**: On-call, incidents, maintenance

**3. Calculate Gap**
- **Demand**: 30 FTE needed
- **Current capacity**: 17.5 FTE
- **Gap**: 12.5 FTE = Need to hire 18 engineers (accounting for ramp-up time)

**4. Account for Attrition**
- **Historical attrition**: What's our annual turnover? (e.g., 15%)
- **Forecast**: 25 engineers × 15% = 3.75 engineers leaving
- **Replacement**: Need to hire 4 engineers just to maintain

**5. Hiring Timeline**
- **Time to hire**: 2-3 months average
- **Ramp-up time**: 3-6 months to full productivity
- **Plan**: Start hiring 6 months before need

**Real Example:**
Need 18 engineers by Q4:
- **Q1**: Hire 6 engineers (start recruiting now)
- **Q2**: Hire 6 engineers (they'll be productive by Q4)
- **Q3**: Hire 6 engineers (they'll be productive by Q1 next year)

**Follow-up Depth 1:** How do you justify headcount requests?

**A:** Always tie headcount to business outcomes:

**Framework:**
1. **Business Need**: What problem are we solving?
2. **Current State**: What can we do today?
3. **Future State**: What do we need to do?
4. **Gap**: What's missing?
5. **Solution**: How many people do we need?
6. **Impact**: What's the ROI?

**Real Example:**
**Business Need**: Launch new payment product in 6 months to capture $10M revenue opportunity.

**Current State**: 
- 10 engineers working on core platform
- Can deliver 5 features/month
- Need 20 features for new product

**Future State**: 
- Need to deliver 20 features in 6 months
- Need dedicated team for new product
- Need to maintain core platform

**Gap**: 
- Current capacity: 5 features/month × 6 months = 30 features (but need 20 for new product + maintenance)
- Need: 8 engineers for new product team

**Solution**: Hire 8 engineers (2 teams of 4)

**Impact**: 
- Investment: 8 engineers × $140K = $1.12M/year
- Revenue: $10M/year
- ROI: $10M - $1.12M = $8.88M/year

**Presentation**: 
- **Slide 1**: Business opportunity ($10M revenue)
- **Slide 2**: Current capacity vs. need (gap analysis)
- **Slide 3**: Proposed team structure (2 teams of 4)
- **Slide 4**: Timeline (hiring plan, delivery milestones)
- **Slide 5**: ROI ($8.88M return on $1.12M investment)

---

## Interview Scenarios & War Stories

### Scenario 1: Critical Production Incident

**Question:** It's 2 AM on Black Friday. Your payment system is down. Transactions are failing. What do you do?

**Answer:**
I've been through this. Here's my playbook:

**Minute 0-5: Triage**
1. **Acknowledge**: "We're aware of the issue, investigating"
2. **Check monitoring**: Error rate? Latency? Database CPU?
3. **Form war room**: Get the right people in Slack/Zoom
4. **Assess scope**: All transactions? Specific merchants? Geographic?

**Real War Story:**
Black Friday 2022. We had a database connection pool exhaustion:
- **Symptoms**: 80% error rate, all "timeout" errors
- **Root cause**: Batch job started during peak hours, consumed all connections
- **Fix**: Killed batch job, restarted services
- **Time to resolution**: 12 minutes
- **Impact**: 50,000 failed transactions, $500K lost revenue

**Minute 5-15: Mitigation**
1. **Stop the bleeding**: Kill problematic processes, restart services, scale up
2. **Communicate**: Update status page, notify stakeholders
3. **Monitor**: Watch error rate drop, latency normalize

**Minute 15-60: Resolution**
1. **Fix root cause**: In our case, reschedule batch job to off-peak
2. **Verify**: Run smoke tests, check metrics
3. **Communicate**: "Issue resolved, monitoring for stability"

**Post-Incident:**
1. **Postmortem**: Within 48 hours
2. **Action items**: Prevent recurrence (better scheduling, monitoring)
3. **Learnings**: Share with team, update runbooks

**Key Learnings:**
- **Monitoring is critical**: We didn't have alerts for connection pool exhaustion
- **Batch jobs need scheduling**: Never run during peak hours
- **Communication matters**: Keep stakeholders informed

### Scenario 2: Vendor Relationship Gone Wrong

**Question:** Your vendor team is delivering poor quality code, missing deadlines, and causing incidents. How do you handle it?

**Answer:**
I've been through this. Here's what I did:

**Step 1: Diagnose the Problem**
- **Is it capability?** (They don't have the skills)
- **Is it process?** (They're not following our standards)
- **Is it communication?** (Requirements unclear)
- **Is it culture?** (They don't feel part of the team)

**Real Example:**
We had a vendor team delivering buggy code. After investigation:
- **Root cause**: They were getting requirements through 3 layers of management (lost in translation)
- **Secondary issue**: They were afraid to ask questions (contract said "billable hours only")
- **Tertiary issue**: They weren't included in architecture discussions

**Step 2: Fix the Process**
1. **Direct communication**: Vendor engineers join our standups
2. **Clear requirements**: Product managers work directly with vendor engineers
3. **Inclusion**: Vendor engineers included in architecture reviews
4. **Quality gates**: Same standards for vendors as FTE (code reviews, testing)

**Step 3: Set Clear Expectations**
- **Quality standards**: Code must pass all tests, reviews
- **Timeline**: Deadlines are non-negotiable (but we'll help if blockers)
- **Escalation**: If issues, escalate early (don't wait until deadline)

**Step 4: Monitor & Adjust**
- **Weekly reviews**: Check progress, quality, blockers
- **Monthly retrospectives**: What's working? What's not?
- **Quarterly reviews**: Is this vendor relationship working? Should we continue?

**Result:**
Within 2 months, quality improved 10x. The issue wasn't capability - it was process and communication.

### Scenario 3: Technical Debt Crisis

**Question:** Your system has accumulated 5 years of technical debt. Development velocity has dropped 50%. How do you address it?

**Answer:**
I've been through this. Here's my approach:

**Step 1: Quantify the Problem**
- **Current velocity**: 5 features/month
- **Target velocity**: 10 features/month (historical)
- **Cost of delay**: $500K/year in lost revenue
- **Debt inventory**: List of major debt items, impact, effort

**Real Example:**
We had a legacy payment system:
- **Monolithic architecture**: Hard to scale, deploy
- **No tests**: Every change risky
- **Outdated dependencies**: Security vulnerabilities
- **Poor documentation**: Onboarding takes 3 months

**Step 2: Prioritize Debt**
- **High impact, high risk**: Fix immediately (security, reliability)
- **High impact, low risk**: Plan for next quarter
- **Low impact**: Defer or ignore

**Step 3: Create a Plan**
- **Phase 1 (Q1)**: Critical security updates, add tests
- **Phase 2 (Q2)**: Refactor critical paths, improve documentation
- **Phase 3 (Q3-Q4)**: Migrate to microservices (if needed)

**Step 4: Allocate Resources**
- **20% rule**: 20% of engineering time on debt
- **Dedicated team**: 2 engineers focused on debt
- **Sprint allocation**: Every sprint includes some debt work

**Step 5: Measure Progress**
- **Velocity**: Track feature delivery rate
- **Debt reduction**: Track debt items resolved
- **Incidents**: Track production incidents (should decrease)

**Result:**
After 6 months:
- Velocity increased from 5 to 8 features/month
- Production incidents decreased by 50%
- Developer satisfaction increased (less frustration)

---

## Technical Architecture Deep Dives

### Architecture Patterns: Monolith vs. Microservices

**Question:** When would you choose a monolith vs. microservices architecture?

**Answer:**
I've built both, and I've seen teams make the wrong choice. Here's the reality:

**Start with a Monolith When:**
- **Small team** (<10 engineers): Microservices add complexity without benefit
- **Uncertain requirements**: You don't know what you're building yet
- **Simple domain**: CRUD app, straightforward business logic
- **Need to move fast**: Monoliths are faster to develop initially

**Real War Story:**
I joined a startup that had 5 engineers building a payment platform. The CTO insisted on microservices from day one. Result:
- **6 months** to build what should take 2 months
- **Over-engineering**: Services talking to each other for simple operations
- **Operational overhead**: 5 services to deploy, monitor, debug
- **Team burnout**: Too much complexity for the team size

We refactored to a monolith, shipped in 2 months, and only split when we had 20+ engineers and clear service boundaries.

**Move to Microservices When:**
- **Team size**: 20+ engineers, need independent deployment
- **Scale**: Different services have different scaling needs
- **Domain boundaries**: Clear bounded contexts (payments vs. fraud vs. notifications)
- **Technology diversity**: Need different tech stacks (Python for ML, Java for payments)

**Microservices Trade-offs:**
- **Pros**: Independent scaling, technology diversity, team autonomy
- **Cons**: Network latency, distributed transactions, operational complexity

**Real Example:**
We had a monolith processing payments. As we scaled:
- **Problem**: Fraud detection needed ML models (Python), but payment processing was Java
- **Problem**: Fraud detection needed GPU instances, but payment processing needed CPU
- **Solution**: Split fraud detection into separate service

```java
// Monolith approach (everything in one service)
@RestController
public class PaymentController {
    @Autowired
    private FraudService fraudService;  // Java-based, slow
    
    @PostMapping("/authorize")
    public AuthorizationResponse authorize(@RequestBody Request req) {
        // Fraud check blocks payment processing
        FraudResult fraud = fraudService.check(req);  // 500ms
        if (fraud.isFraud()) {
            return AuthorizationResponse.rejected();
        }
        return processPayment(req);  // 50ms
    }
}

// Microservices approach (separate services)
@RestController
public class PaymentController {
    @Autowired
    private FraudClient fraudClient;  // HTTP client to fraud service
    
    @PostMapping("/authorize")
    public AuthorizationResponse authorize(@RequestBody Request req) {
        // Async fraud check, don't block
        CompletableFuture<FraudResult> fraudFuture = 
            fraudClient.checkAsync(req);
        
        // Process payment immediately
        AuthorizationResponse response = processPayment(req);
        
        // If fraud detected later, reverse transaction
        fraudFuture.thenAccept(fraud -> {
            if (fraud.isFraud()) {
                reverseTransaction(response.getId());
            }
        });
        
        return response;  // 50ms, not 550ms
    }
}
```

**Follow-up Depth 1:** How do you identify service boundaries?

**A:** This is the hardest part. I use **Domain-Driven Design (DDD)** principles:

**1. Bounded Contexts**
- **Payment Context**: Authorization, settlement, refunds
- **Fraud Context**: Risk scoring, ML models, rules engine
- **Notification Context**: Emails, SMS, push notifications
- **User Context**: Authentication, profiles, preferences

**2. Conway's Law**
- **Team structure** drives architecture
- If you have separate teams for payments and fraud, they should be separate services
- If one team owns both, keep them together

**3. Data Ownership**
- **Payment service** owns payment data (transactions, settlements)
- **Fraud service** owns fraud data (risk scores, models)
- **Shared data** = potential coupling (be careful)

**Real Example:**
We had a "User Service" that owned user data. Payment service needed user info. Options:
1. **Call User Service**: Network call, adds latency
2. **Duplicate user data**: Data inconsistency risk
3. **Event-driven**: User service publishes events, Payment service subscribes

We chose #3 (event-driven):
```java
// User Service publishes user updates
@Service
public class UserService {
    @Autowired
    private KafkaTemplate<String, UserEvent> kafka;
    
    public void updateUser(User user) {
        // Update database
        userRepository.save(user);
        
        // Publish event
        UserEvent event = new UserEvent(user.getId(), user.getEmail(), ...);
        kafka.send("user-events", event);
    }
}

// Payment Service subscribes to user events
@KafkaListener(topics = "user-events")
public void handleUserEvent(UserEvent event) {
    // Update local cache/replica
    userCache.put(event.getUserId(), event);
}
```

**4. Change Frequency**
- **High change frequency** = separate service (isolate changes)
- **Low change frequency** = can stay together

**Follow-up Depth 2:** How do you handle distributed transactions in microservices?

**A:** This is where microservices get messy. Here's what I've learned:

**Problem:**
Payment needs to:
1. Debit user account (Account Service)
2. Create payment record (Payment Service)
3. Send notification (Notification Service)

If any step fails, need to rollback. But each service has its own database.

**Solution 1: Saga Pattern (What I Use)**
```java
// Orchestrator coordinates the saga
@Service
public class PaymentSagaOrchestrator {
    
    public void processPayment(PaymentRequest request) {
        SagaContext context = new SagaContext(request);
        
        try {
            // Step 1: Debit account
            AccountDebitResult debit = accountService.debit(
                request.getUserId(), request.getAmount());
            context.setDebitId(debit.getId());
            
            // Step 2: Create payment
            Payment payment = paymentService.create(request);
            context.setPaymentId(payment.getId());
            
            // Step 3: Send notification
            notificationService.send(payment);
            
            // All succeeded
            context.complete();
        } catch (Exception e) {
            // Compensate (rollback)
            compensate(context);
            throw e;
        }
    }
    
    private void compensate(SagaContext context) {
        // Reverse in reverse order
        if (context.getPaymentId() != null) {
            paymentService.reverse(context.getPaymentId());
        }
        if (context.getDebitId() != null) {
            accountService.credit(context.getDebitId());
        }
    }
}
```

**Solution 2: Event Sourcing (For Complex Cases)**
```java
// Each service publishes events
// Other services react to events

// Payment Service
@Service
public class PaymentService {
    public void createPayment(PaymentRequest request) {
        Payment payment = new Payment(request);
        paymentRepository.save(payment);
        
        // Publish event
        PaymentCreatedEvent event = new PaymentCreatedEvent(
            payment.getId(), request.getUserId(), request.getAmount());
        eventPublisher.publish(event);
    }
}

// Account Service listens and debits
@EventListener
public void handlePaymentCreated(PaymentCreatedEvent event) {
    accountService.debit(event.getUserId(), event.getAmount());
}

// If debit fails, publish compensation event
// Payment service listens and reverses payment
```

**Trade-offs:**
- **Saga**: More control, but complex orchestration
- **Event Sourcing**: More decoupled, but eventual consistency

**Real War Story:**
We used 2PC (Two-Phase Commit) initially. Bad idea:
- **Performance**: Locks held across services = slow
- **Availability**: If one service is down, all services blocked
- **Complexity**: Hard to debug, hard to recover

We migrated to Saga pattern. Took 3 months, but worth it.

---

### Database Selection: Choosing the Right Database

**Question:** How do you choose between PostgreSQL, MongoDB, Cassandra, and Redis for different use cases?

**Answer:**
I've made this decision dozens of times. Here's my framework:

**Decision Matrix:**

| Use Case | Database | Why |
|----------|----------|-----|
| **Transactional payments** | PostgreSQL | ACID, complex queries, joins |
| **User sessions** | Redis | Fast, in-memory, TTL support |
| **Time-series data** | Cassandra | Write-optimized, horizontal scaling |
| **Document storage** | MongoDB | Flexible schema, rapid iteration |
| **Caching** | Redis | Sub-millisecond latency |
| **Analytics** | ClickHouse/BigQuery | Columnar, aggregations |

**Real Example: Payment Authorization System**

**Requirements:**
- 100K TPS authorization requests
- Need to check: balance, fraud history, card status
- Need ACID for balance updates
- Need fast reads for fraud checks

**Architecture:**
```java
// PostgreSQL for transactional data (ACID required)
@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepo;  // PostgreSQL
    
    @Transactional
    public void debitAccount(Long userId, BigDecimal amount) {
        Account account = accountRepo.findById(userId);
        if (account.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException();
        }
        account.setBalance(account.getBalance().subtract(amount));
        accountRepo.save(account);  // ACID transaction
    }
}

// Redis for fraud checks (fast reads, no ACID needed)
@Service
public class FraudService {
    @Autowired
    private RedisTemplate<String, String> redis;
    
    public boolean isFraudulent(Long userId) {
        // Check Redis cache (sub-millisecond)
        String riskScore = redis.opsForValue().get("fraud:" + userId);
        if (riskScore != null) {
            return Integer.parseInt(riskScore) > 70;
        }
        
        // Cache miss: check PostgreSQL (fallback)
        FraudHistory history = fraudRepo.findByUserId(userId);
        int score = calculateRiskScore(history);
        
        // Cache for 1 hour
        redis.opsForValue().set("fraud:" + userId, 
            String.valueOf(score), 1, TimeUnit.HOURS);
        
        return score > 70;
    }
}

// Cassandra for transaction history (write-heavy, time-series)
@Service
public class TransactionHistoryService {
    @Autowired
    private TransactionRepository transactionRepo;  // Cassandra
    
    public void recordTransaction(Transaction tx) {
        // Cassandra optimized for writes
        // Partition by user_id, cluster by timestamp
        TransactionRecord record = new TransactionRecord(
            tx.getUserId(),  // Partition key
            tx.getTimestamp(),  // Clustering key
            tx.getAmount(),
            tx.getStatus()
        );
        transactionRepo.save(record);  // Fast write, no ACID
    }
    
    public List<Transaction> getTransactionHistory(Long userId, 
            LocalDateTime start, LocalDateTime end) {
        // Efficient range query on partition
        return transactionRepo.findByUserIdAndTimestampBetween(
            userId, start, end);
    }
}
```

**Follow-up Depth 1:** When would you use MongoDB over PostgreSQL?

**A:** I've used both extensively. Here's when MongoDB wins:

**Use MongoDB When:**
1. **Schema Evolution**: Schema changes frequently, don't want migrations
2. **Document Structure**: Data is naturally hierarchical (nested objects)
3. **Horizontal Scaling**: Need to shard across many nodes
4. **Rapid Prototyping**: Need to move fast, schema can evolve

**Real Example:**
We built a merchant onboarding system. Requirements:
- **Uncertain schema**: Different merchants need different fields (retail vs. e-commerce vs. marketplace)
- **Rapid iteration**: Product team changing requirements weekly
- **Nested data**: Merchant → Locations → Payment Methods → Configurations

**PostgreSQL Approach (Painful):**
```sql
-- Need to alter table for every new field
ALTER TABLE merchants ADD COLUMN marketplace_fee DECIMAL;
ALTER TABLE merchants ADD COLUMN subscription_tier VARCHAR;

-- Or use JSONB (but then lose type safety)
ALTER TABLE merchants ADD COLUMN metadata JSONB;
```

**MongoDB Approach (Natural):**
```java
// Schema evolves naturally
@Document
public class Merchant {
    @Id
    private String id;
    private String name;
    private String type;  // "retail", "e-commerce", "marketplace"
    
    // Different fields based on type
    private Map<String, Object> typeSpecificFields;
    
    // Nested structures
    private List<Location> locations;
    private PaymentConfiguration paymentConfig;
}

// No migrations needed, just deploy new code
// Old documents still work, new documents have new fields
```

**Use PostgreSQL When:**
1. **ACID Required**: Financial transactions, critical data
2. **Complex Queries**: Joins, aggregations, analytics
3. **Relationships**: Foreign keys, referential integrity
4. **Mature Schema**: Schema is stable, well-understood

**Real War Story:**
We used MongoDB for a payment system initially. Bad idea:
- **No ACID**: Lost transactions during failures
- **No joins**: Had to fetch related data in application (N+1 queries)
- **Data inconsistency**: Replication lag caused issues

We migrated to PostgreSQL. Took 6 months, but worth it for financial data.

**Hybrid Approach (What I Do Now):**
- **PostgreSQL**: Core transactional data (payments, accounts)
- **MongoDB**: Configuration, metadata, flexible schemas
- **Redis**: Caching, sessions
- **Cassandra**: Time-series, audit logs

**Follow-up Depth 2:** How do you handle database scaling?

**A:** This is where many systems break. Here's my approach:

**1. Vertical Scaling (Scale Up)**
- **When**: Small to medium scale (<10K TPS)
- **How**: Bigger instances (more CPU, RAM, IOPS)
- **Limits**: Single point of failure, expensive

**2. Read Replicas (Scale Reads)**
```java
// Primary for writes, replicas for reads
@Configuration
public class DatabaseConfig {
    @Bean
    public DataSource primaryDataSource() {
        // Write to primary
        return DataSourceBuilder.create()
            .url("jdbc:postgresql://primary-db:5432/payments")
            .build();
    }
    
    @Bean
    public DataSource replicaDataSource() {
        // Read from replica
        return DataSourceBuilder.create()
            .url("jdbc:postgresql://replica-db:5432/payments")
            .build();
    }
}

@Service
public class PaymentService {
    @Autowired
    @Qualifier("primaryDataSource")
    private JdbcTemplate writeTemplate;
    
    @Autowired
    @Qualifier("replicaDataSource")
    private JdbcTemplate readTemplate;
    
    @Transactional
    public void createPayment(Payment payment) {
        // Write to primary
        writeTemplate.update("INSERT INTO payments ...", ...);
    }
    
    public Payment getPayment(Long id) {
        // Read from replica
        return readTemplate.queryForObject(
            "SELECT * FROM payments WHERE id = ?", 
            Payment.class, id);
    }
}
```

**3. Sharding (Scale Writes)**
```java
// Shard by user_id
@Service
public class ShardedPaymentService {
    private List<DataSource> shards;
    
    public ShardedPaymentService() {
        // 4 shards
        shards = Arrays.asList(
            createDataSource("shard1"),
            createDataSource("shard2"),
            createDataSource("shard3"),
            createDataSource("shard4")
        );
    }
    
    private DataSource getShard(Long userId) {
        // Consistent hashing
        int shardIndex = (int) (userId % shards.size());
        return shards.get(shardIndex);
    }
    
    public void createPayment(Payment payment) {
        DataSource shard = getShard(payment.getUserId());
        JdbcTemplate template = new JdbcTemplate(shard);
        template.update("INSERT INTO payments ...", ...);
    }
    
    public Payment getPayment(Long paymentId, Long userId) {
        DataSource shard = getShard(userId);
        JdbcTemplate template = new JdbcTemplate(shard);
        return template.queryForObject(
            "SELECT * FROM payments WHERE id = ?", 
            Payment.class, paymentId);
    }
}
```

**Real Example:**
We had a PostgreSQL database hitting 80% CPU. Options:
1. **Scale up**: $10K/month for bigger instance
2. **Read replicas**: $5K/month, but only helps reads
3. **Sharding**: $8K/month, but complex migration

We chose read replicas first (quick win), then sharded when we hit limits.

---

### Monitoring & Observability with OpenTelemetry

**Question:** How do you implement comprehensive observability for a microservices payment system?

**Answer:**
I've built observability from scratch multiple times. Here's what actually works:

**The Three Pillars:**
1. **Metrics**: SLIs, SLOs, business metrics
2. **Traces**: Request flows across services
3. **Logs**: Debugging, audit trails

**OpenTelemetry Setup:**
```java
// Add dependencies
// pom.xml
<dependency>
    <groupId>io.opentelemetry</groupId>
    <artifactId>opentelemetry-api</artifactId>
    <version>1.32.0</version>
</dependency>
<dependency>
    <groupId>io.opentelemetry</groupId>
    <artifactId>opentelemetry-sdk</artifactId>
    <version>1.32.0</version>
</dependency>
<dependency>
    <groupId>io.opentelemetry</groupId>
    <artifactId>opentelemetry-exporter-jaeger</artifactId>
    <version>1.32.0</version>
</dependency>
<dependency>
    <groupId>io.opentelemetry.instrumentation</groupId>
    <artifactId>opentelemetry-spring-boot-starter</artifactId>
    <version>2.1.0</version>
</dependency>

// Configuration
@Configuration
public class OpenTelemetryConfig {
    @Bean
    public OpenTelemetry openTelemetry() {
        return OpenTelemetrySdk.builder()
            .setTracerProvider(
                SdkTracerProvider.builder()
                    .addSpanProcessor(BatchSpanProcessor.builder(
                        JaegerGrpcSpanExporter.builder()
                            .setEndpoint("http://jaeger:14250")
                            .build())
                        .build())
                    .setResource(Resource.getDefault()
                        .merge(Resource.create(Attributes.of(
                            ResourceAttributes.SERVICE_NAME, "payment-service"))))
                    .build())
            .setMeterProvider(
                SdkMeterProvider.builder()
                    .addMetricReader(
                        PeriodicMetricReader.builder(
                            OtlpGrpcMetricExporter.builder()
                                .setEndpoint("http://prometheus:4317")
                                .build())
                            .setInterval(Duration.ofSeconds(10))
                            .build())
                    .setResource(Resource.getDefault()
                        .merge(Resource.create(Attributes.of(
                            ResourceAttributes.SERVICE_NAME, "payment-service"))))
                    .build())
            .build();
    }
}
```

**Instrumenting Code:**
```java
@Service
public class PaymentService {
    private static final Tracer tracer = 
        GlobalOpenTelemetry.getTracer("payment-service");
    private static final Meter meter = 
        GlobalOpenTelemetry.getMeter("payment-service");
    
    private final Counter paymentCounter = 
        meter.counterBuilder("payments.total")
            .setDescription("Total payment requests")
            .build();
    private final Histogram paymentLatency = 
        meter.histogramBuilder("payments.latency")
            .setDescription("Payment processing latency")
            .setUnit("ms")
            .build();
    
    public AuthorizationResponse authorize(AuthorizationRequest request) {
        // Start span
        Span span = tracer.spanBuilder("authorize")
            .setAttribute("user.id", request.getUserId())
            .setAttribute("amount", request.getAmount().doubleValue())
            .startSpan();
        
        try (Scope scope = span.makeCurrent()) {
            // Add custom attributes
            span.setAttribute("merchant.id", request.getMerchantId());
            
            long startTime = System.currentTimeMillis();
            
            // Business logic
            AuthorizationResponse response = processAuthorization(request);
            
            // Record metrics
            paymentCounter.add(1, 
                Attributes.of(
                    AttributeKey.stringKey("status"), response.getStatus(),
                    AttributeKey.stringKey("merchant"), request.getMerchantId()));
            
            long latency = System.currentTimeMillis() - startTime;
            paymentLatency.record(latency,
                Attributes.of(
                    AttributeKey.stringKey("status"), response.getStatus()));
            
            // Add span attributes
            span.setAttribute("response.status", response.getStatus());
            span.setAttribute("response.code", response.getCode());
            
            return response;
        } catch (Exception e) {
            // Record error
            span.recordException(e);
            span.setStatus(StatusCode.ERROR, e.getMessage());
            throw e;
        } finally {
            span.end();
        }
    }
}
```

**Follow-up Depth 1:** How do you trace requests across microservices?

**A:** This is where OpenTelemetry shines. Here's how I do it:

**1. Propagate Trace Context**
```java
// Payment Service (initiates request)
@RestController
public class PaymentController {
    @Autowired
    private FraudClient fraudClient;
    
    @PostMapping("/authorize")
    public AuthorizationResponse authorize(@RequestBody Request req) {
        Span span = tracer.spanBuilder("payment.authorize")
            .setParent(Context.current())
            .startSpan();
        
        try (Scope scope = span.makeCurrent()) {
            // Call fraud service with trace context
            AuthorizationResponse response = fraudClient.check(req);
            return response;
        } finally {
            span.end();
        }
    }
}

// Fraud Service (receives request)
@RestController
public class FraudController {
    @PostMapping("/check")
    public FraudResponse check(@RequestBody Request req) {
        // Extract trace context from HTTP headers
        Context context = extractContext(req);
        
        Span span = tracer.spanBuilder("fraud.check")
            .setParent(context)
            .startSpan();
        
        try (Scope scope = span.makeCurrent()) {
            // Business logic
            FraudResponse response = fraudService.check(req);
            return response;
        } finally {
            span.end();
        }
    }
    
    private Context extractContext(Request req) {
        // Extract traceparent header
        String traceparent = req.getHeader("traceparent");
        if (traceparent != null) {
            return W3CTraceContextPropagator.getInstance()
                .extract(Context.current(), req, 
                    (request, key) -> request.getHeader(key));
        }
        return Context.current();
    }
}

// HTTP Client (propagates context)
@Component
public class FraudClient {
    @Autowired
    private RestTemplate restTemplate;
    
    public AuthorizationResponse check(Request req) {
        Span span = tracer.spanBuilder("fraud.client.check")
            .setParent(Context.current())
            .startSpan();
        
        try (Scope scope = span.makeCurrent()) {
            // Inject trace context into HTTP headers
            HttpHeaders headers = new HttpHeaders();
            W3CTraceContextPropagator.getInstance().inject(
                Context.current(), headers, 
                (h, k, v) -> h.add(k, v));
            
            HttpEntity<Request> entity = new HttpEntity<>(req, headers);
            return restTemplate.postForObject(
                "http://fraud-service/check", entity, 
                AuthorizationResponse.class);
        } finally {
            span.end();
        }
    }
}
```

**2. View Traces in Jaeger**
- **Trace**: Full request flow (Payment → Fraud → Database)
- **Spans**: Individual operations (authorize, check, query)
- **Timing**: See where latency is (database slow? network slow?)

**Real War Story:**
We had a payment taking 2 seconds. Traces showed:
- Payment service: 50ms
- Fraud service: 1.8 seconds
- Database query: 1.7 seconds

Root cause: Fraud service was doing a full table scan. Fixed with index, latency dropped to 100ms.

**Follow-up Depth 2:** How do you set up alerts based on metrics?

**A:** This is critical for production. Here's my setup:

**Prometheus Metrics:**
```java
// Custom business metrics
@Service
public class PaymentMetrics {
    private final Counter paymentCounter;
    private final Histogram paymentLatency;
    private final Gauge activeConnections;
    
    public PaymentMetrics() {
        Meter meter = GlobalOpenTelemetry.getMeter("payment-service");
        
        paymentCounter = meter.counterBuilder("payments.total")
            .setDescription("Total payment requests")
            .build();
        
        paymentLatency = meter.histogramBuilder("payments.latency")
            .setDescription("Payment latency")
            .setUnit("ms")
            .build();
        
        activeConnections = meter.gaugeBuilder("db.connections.active")
            .setDescription("Active database connections")
            .build();
    }
}
```

**Prometheus Alert Rules:**
```yaml
# prometheus-alerts.yml
groups:
  - name: payment_service
    interval: 30s
    rules:
      # High error rate
      - alert: HighErrorRate
        expr: rate(payments_total{status="error"}[5m]) > 0.01
        for: 5m
        labels:
          severity: critical
        annotations:
          summary: "High error rate detected"
          description: "Error rate is {{ $value }} (threshold: 0.01)"
      
      # High latency
      - alert: HighLatency
        expr: histogram_quantile(0.99, payments_latency_bucket) > 500
        for: 5m
        labels:
          severity: warning
        annotations:
          summary: "High latency detected"
          description: "P99 latency is {{ $value }}ms (threshold: 500ms)"
      
      # Database connection pool exhaustion
      - alert: DatabaseConnectionPoolExhausted
        expr: db_connections_active / db_connections_max > 0.9
        for: 2m
        labels:
          severity: critical
        annotations:
          summary: "Database connection pool nearly exhausted"
          description: "{{ $value }}% of connections in use"
```

**AlertManager Configuration:**
```yaml
# alertmanager.yml
route:
  group_by: ['alertname', 'severity']
  group_wait: 10s
  group_interval: 10s
  repeat_interval: 12h
  receiver: 'slack-notifications'
  routes:
    - match:
        severity: critical
      receiver: 'pagerduty'
    - match:
        severity: warning
      receiver: 'slack-notifications'

receivers:
  - name: 'slack-notifications'
    slack_configs:
      - api_url: 'https://hooks.slack.com/services/...'
        channel: '#alerts'
        title: '{{ .GroupLabels.alertname }}'
        text: '{{ range .Alerts }}{{ .Annotations.description }}{{ end }}'
  
  - name: 'pagerduty'
    pagerduty_configs:
      - service_key: 'your-pagerduty-key'
        description: '{{ .GroupLabels.alertname }}'
```

---

### Database Troubleshooting

**Question:** Your PostgreSQL database is slow. Walk me through your troubleshooting process.

**Answer:**
I've debugged hundreds of database performance issues. Here's my systematic approach:

**Step 1: Check Current State**
```sql
-- Check active queries
SELECT pid, usename, application_name, state, query, 
       query_start, state_change
FROM pg_stat_activity
WHERE state != 'idle'
ORDER BY query_start;

-- Check slow queries
SELECT pid, now() - query_start AS duration, query
FROM pg_stat_activity
WHERE state = 'active' 
  AND now() - query_start > interval '5 seconds'
ORDER BY duration DESC;

-- Check locks
SELECT blocked_locks.pid AS blocked_pid,
       blocking_locks.pid AS blocking_pid,
       blocked_activity.query AS blocked_query,
       blocking_activity.query AS blocking_query
FROM pg_catalog.pg_locks blocked_locks
JOIN pg_catalog.pg_stat_activity blocked_activity 
    ON blocked_activity.pid = blocked_locks.pid
JOIN pg_catalog.pg_locks blocking_locks 
    ON blocking_locks.locktype = blocked_locks.locktype
    AND blocking_locks.database IS NOT DISTINCT 
        FROM blocked_locks.database
    AND blocking_locks.relation IS NOT DISTINCT 
        FROM blocked_locks.relation
JOIN pg_catalog.pg_stat_activity blocking_activity 
    ON blocking_activity.pid = blocking_locks.pid
WHERE NOT blocked_locks.granted;
```

**Step 2: Analyze Query Performance**
```sql
-- Enable query logging for slow queries
ALTER SYSTEM SET log_min_duration_statement = 1000;  -- Log queries > 1s
SELECT pg_reload_conf();

-- Use EXPLAIN ANALYZE
EXPLAIN ANALYZE
SELECT * FROM payments 
WHERE user_id = 12345 
  AND created_at > NOW() - INTERVAL '30 days';

-- Check for missing indexes
SELECT schemaname, tablename, attname, n_distinct, correlation
FROM pg_stats
WHERE tablename = 'payments'
  AND n_distinct > 100  -- High cardinality
  AND correlation < 0.1;  -- Low correlation (good for index)
```

**Real War Story:**
We had a query taking 30 seconds:
```sql
SELECT * FROM payments 
WHERE merchant_id = 123 
  AND status = 'pending'
ORDER BY created_at DESC;
```

**Investigation:**
1. **EXPLAIN ANALYZE** showed: Seq Scan on payments (scanning 10M rows)
2. **Missing index**: No index on (merchant_id, status, created_at)
3. **Fix**: Created composite index
```sql
CREATE INDEX idx_payments_merchant_status_created 
ON payments(merchant_id, status, created_at DESC);
```
4. **Result**: Query time dropped to 50ms

**Step 3: Check Database Configuration**
```sql
-- Check connection count
SELECT count(*) FROM pg_stat_activity;

-- Check shared_buffers (should be 25% of RAM)
SHOW shared_buffers;

-- Check work_mem (per-query memory)
SHOW work_mem;

-- Check effective_cache_size (should be 50-75% of RAM)
SHOW effective_cache_size;

-- Check for bloat
SELECT schemaname, tablename, 
       pg_size_pretty(pg_total_relation_size(schemaname||'.'||tablename)) AS size,
       n_dead_tup, n_live_tup,
       round(n_dead_tup * 100.0 / NULLIF(n_live_tup + n_dead_tup, 0), 2) AS dead_pct
FROM pg_stat_user_tables
WHERE n_dead_tup > 1000
ORDER BY dead_pct DESC;
```

**Step 4: Common Issues & Fixes**

**Issue 1: Connection Pool Exhausted**
```java
// Symptoms: "FATAL: sorry, too many clients already"
// Fix: Increase max_connections or use connection pooling

// application.yml
spring:
  datasource:
    hikari:
      maximum-pool-size: 20
      minimum-idle: 5
      connection-timeout: 30000
      idle-timeout: 600000
      max-lifetime: 1800000
```

**Issue 2: Table Bloat**
```sql
-- Symptoms: Queries slow, table size large
-- Fix: VACUUM FULL (during maintenance window)
VACUUM FULL ANALYZE payments;

-- Or use pg_repack for zero-downtime
```

**Issue 3: Missing Indexes**
```sql
-- Find missing indexes using pg_stat_statements
SELECT query, calls, total_time, mean_time,
       (total_time / sum(total_time) OVER ()) * 100 AS pct_time
FROM pg_stat_statements
ORDER BY total_time DESC
LIMIT 10;

-- Create indexes based on slow queries
CREATE INDEX CONCURRENTLY idx_payments_user_created 
ON payments(user_id, created_at DESC);
```

**Follow-up Depth 1:** How do you troubleshoot database deadlocks?

**A:** Deadlocks are tricky. Here's how I handle them:

**Understanding Deadlocks:**
```sql
-- Enable deadlock logging
ALTER SYSTEM SET log_lock_waits = on;
ALTER SYSTEM SET deadlock_timeout = 1000;  -- 1 second
SELECT pg_reload_conf();

-- Check deadlock logs
SELECT * FROM pg_stat_database_conflicts;
```

**Common Deadlock Scenario:**
```java
// Transaction 1: Update account, then payment
@Transactional
public void processPayment1(Long accountId, Long paymentId) {
    // Lock account
    accountRepository.findById(accountId);  // SELECT FOR UPDATE
    
    // Lock payment
    paymentRepository.findById(paymentId);  // SELECT FOR UPDATE
}

// Transaction 2: Update payment, then account (REVERSE ORDER)
@Transactional
public void processPayment2(Long paymentId, Long accountId) {
    // Lock payment
    paymentRepository.findById(paymentId);  // SELECT FOR UPDATE
    
    // Lock account (DEADLOCK if Transaction 1 already has it)
    accountRepository.findById(accountId);  // SELECT FOR UPDATE
}
```

**Fix: Consistent Lock Ordering**
```java
// Always lock in same order (e.g., by ID)
@Transactional
public void processPayment(Long accountId, Long paymentId) {
    // Lock in consistent order (smaller ID first)
    Long firstId = Math.min(accountId, paymentId);
    Long secondId = Math.max(accountId, paymentId);
    
    if (firstId == accountId) {
        accountRepository.findById(accountId);
        paymentRepository.findById(paymentId);
    } else {
        paymentRepository.findById(paymentId);
        accountRepository.findById(accountId);
    }
}
```

**Real Example:**
We had deadlocks in payment processing. Root cause: Two services locking resources in different orders. Fix: Implemented consistent lock ordering, deadlocks disappeared.

---

### Application Troubleshooting

**Question:** Your Java application is experiencing high CPU usage and memory leaks. How do you diagnose and fix it?

**Answer:**
I've debugged countless production issues. Here's my playbook:

**Step 1: Identify the Problem**
```bash
# Check CPU usage
top -H -p <pid>  # Show threads

# Check memory usage
jmap -heap <pid>
jmap -histo <pid>  # Object histogram

# Generate heap dump
jmap -dump:format=b,file=heap.hprof <pid>

# Check thread dumps
jstack <pid> > thread-dump.txt
```

**Step 2: Analyze Heap Dump**
```bash
# Use Eclipse MAT or jhat
jhat heap.hprof
# Open http://localhost:7000

# Or use jvisualvm (GUI tool)
jvisualvm
```

**Common Memory Leak Patterns:**

**Pattern 1: Unclosed Resources**
```java
// BAD: File not closed
public void processFile(String filename) {
    FileReader reader = new FileReader(filename);
    // Process file
    // Forgot to close!
}

// GOOD: Use try-with-resources
public void processFile(String filename) {
    try (FileReader reader = new FileReader(filename)) {
        // Process file
    }  // Automatically closed
}
```

**Pattern 2: Static Collections Growing**
```java
// BAD: Static map grows forever
public class Cache {
    private static Map<String, Object> cache = new HashMap<>();
    
    public void add(String key, Object value) {
        cache.put(key, value);  // Never removed!
    }
}

// GOOD: Use bounded cache with eviction
public class Cache {
    private static Cache<String, Object> cache = Caffeine.newBuilder()
        .maximumSize(10_000)
        .expireAfterWrite(1, TimeUnit.HOURS)
        .build();
    
    public void add(String key, Object value) {
        cache.put(key, value);  // Auto-evicted
    }
}
```

**Pattern 3: Thread Local Not Cleared**
```java
// BAD: ThreadLocal not cleared
public class UserContext {
    private static ThreadLocal<User> user = new ThreadLocal<>();
    
    public void setUser(User u) {
        user.set(u);  // Never cleared!
    }
}

// GOOD: Clear in finally block
public class UserContext {
    private static ThreadLocal<User> user = new ThreadLocal<>();
    
    public void setUser(User u) {
        user.set(u);
    }
    
    public void clear() {
        user.remove();  // Clear when done
    }
}

// Use in filter/interceptor
public void doFilter(...) {
    try {
        userContext.setUser(user);
        chain.doFilter(...);
    } finally {
        userContext.clear();  // Always clear
    }
}
```

**Step 3: CPU Profiling**
```bash
# Use async-profiler (best tool)
./profiler.sh -d 60 -f profile.html <pid>

# Or use JFR (Java Flight Recorder)
java -XX:+FlightRecorder \
     -XX:StartFlightRecording=duration=60s,filename=recording.jfr \
     -jar app.jar

# Analyze with JDK Mission Control
jmc recording.jfr
```

**Common CPU Issues:**

**Issue 1: Infinite Loops**
```java
// BAD: Infinite loop
while (true) {
    process();  // No break condition
}

// GOOD: Add timeout or break condition
long startTime = System.currentTimeMillis();
while (System.currentTimeMillis() - startTime < 5000) {
    if (process()) {
        break;  // Exit on success
    }
    Thread.sleep(100);  // Don't spin
}
```

**Issue 2: Excessive Logging**
```java
// BAD: Logging in hot path
for (Payment payment : payments) {
    log.debug("Processing payment: {}", payment);  // Expensive!
    process(payment);
}

// GOOD: Use conditional logging
if (log.isDebugEnabled()) {
    for (Payment payment : payments) {
        log.debug("Processing payment: {}", payment);
    }
}
for (Payment payment : payments) {
    process(payment);
}
```

**Issue 3: Synchronization Contention**
```java
// BAD: Synchronized on hot path
public synchronized void processPayment(Payment p) {
    // All threads wait here
}

// GOOD: Use concurrent collections or lock-free
private final ConcurrentHashMap<String, Object> cache = 
    new ConcurrentHashMap<>();

public void processPayment(Payment p) {
    cache.computeIfAbsent(p.getId(), k -> process(p));
}
```

**Real War Story:**
We had a memory leak causing OOM every 2 days. Investigation:
1. **Heap dump** showed: 50GB of `Payment` objects
2. **Root cause**: Static cache never evicted old payments
3. **Fix**: Added TTL and size limits to cache
4. **Result**: Memory stable, no more OOMs

**Step 4: Thread Dump Analysis**
```bash
# Generate thread dump
jstack <pid> > thread-dump.txt

# Look for:
# - Blocked threads (waiting for locks)
# - Deadlocked threads
# - High thread count (thread leak)
```

**Common Thread Issues:**

**Issue 1: Thread Leak**
```java
// BAD: ExecutorService not shut down
ExecutorService executor = Executors.newFixedThreadPool(10);
executor.submit(() -> process());
// Never shutdown!

// GOOD: Always shutdown
ExecutorService executor = Executors.newFixedThreadPool(10);
try {
    executor.submit(() -> process());
} finally {
    executor.shutdown();
    executor.awaitTermination(60, TimeUnit.SECONDS);
}
```

**Issue 2: Deadlock**
```java
// Thread 1: Lock A, then B
synchronized (lockA) {
    synchronized (lockB) {
        // ...
    }
}

// Thread 2: Lock B, then A (DEADLOCK)
synchronized (lockB) {
    synchronized (lockA) {
        // ...
    }
}

// Fix: Consistent lock ordering (same as database deadlocks)
```

**Follow-up Depth 1:** How do you troubleshoot production issues without impacting users?

**A:** This is critical. Here's my approach:

**1. Canary Deployments**
```java
// Deploy to 5% of traffic first
// Monitor metrics, then roll out gradually

@RestController
public class PaymentController {
    @Value("${canary.enabled:false}")
    private boolean canaryEnabled;
    
    @Value("${canary.percentage:5}")
    private int canaryPercentage;
    
    @PostMapping("/authorize")
    public AuthorizationResponse authorize(@RequestBody Request req) {
        // Route to canary or stable based on user ID
        boolean useCanary = canaryEnabled && 
            (req.getUserId() % 100 < canaryPercentage);
        
        if (useCanary) {
            return canaryService.authorize(req);
        } else {
            return stableService.authorize(req);
        }
    }
}
```

**2. Feature Flags**
```java
// Toggle features without deployment
@Service
public class PaymentService {
    @Autowired
    private FeatureFlagService featureFlags;
    
    public AuthorizationResponse authorize(Request req) {
        // New fraud algorithm behind feature flag
        if (featureFlags.isEnabled("new-fraud-algorithm", req.getUserId())) {
            return newFraudService.check(req);
        } else {
            return oldFraudService.check(req);
        }
    }
}
```

**3. Gradual Rollout**
```java
// Roll out to 10%, 25%, 50%, 100%
@Service
public class GradualRollout {
    public boolean shouldUseNewFeature(Long userId) {
        int rolloutPercentage = getRolloutPercentage();
        return (userId % 100) < rolloutPercentage;
    }
    
    private int getRolloutPercentage() {
        // Increase gradually: 10% → 25% → 50% → 100%
        // Based on metrics (error rate, latency)
        return configService.getRolloutPercentage();
    }
}
```

**4. Circuit Breakers**
```java
// Fail fast if service is down
@Service
public class FraudService {
    @CircuitBreaker(name = "fraud-service", fallbackMethod = "fallbackCheck")
    public FraudResult check(Request req) {
        return fraudClient.check(req);
    }
    
    public FraudResult fallbackCheck(Request req, Exception e) {
        // Fallback: Use cached risk score or default
        return FraudResult.lowRisk();  // Don't block payments
    }
}
```

**5. Monitoring During Rollout**
```java
// Track metrics for canary vs. stable
@Service
public class MetricsService {
    public void recordAuthorization(String version, String status, long latency) {
        meter.counterBuilder("authorizations.total")
            .setDescription("Authorization requests")
            .build()
            .add(1, Attributes.of(
                AttributeKey.stringKey("version"), version,
                AttributeKey.stringKey("status"), status));
        
        meter.histogramBuilder("authorizations.latency")
            .setDescription("Authorization latency")
            .build()
            .record(latency, Attributes.of(
                AttributeKey.stringKey("version"), version));
    }
}
```

---

### Performance Optimization

**Question:** Your payment authorization API has p99 latency of 2 seconds. How do you optimize it to <200ms?

**Answer:**
I've optimized systems from seconds to milliseconds. Here's my systematic approach:

**Step 1: Profile to Find Bottlenecks**
```java
// Use JProfiler or async-profiler
// Identify hot methods

// Common bottlenecks:
// 1. Database queries (N+1, missing indexes)
// 2. External API calls (network latency)
// 3. Synchronization (lock contention)
// 4. Serialization (JSON parsing)
// 5. Memory allocation (GC pressure)
```

**Step 2: Optimize Database Queries**
```java
// BAD: N+1 queries
public List<Payment> getPayments(Long userId) {
    List<Payment> payments = paymentRepo.findByUserId(userId);
    for (Payment p : payments) {
        // N queries for N payments
        Merchant merchant = merchantRepo.findById(p.getMerchantId());
        p.setMerchant(merchant);
    }
    return payments;
}

// GOOD: Single query with join
public List<Payment> getPayments(Long userId) {
    return paymentRepo.findByUserIdWithMerchant(userId);
    // SELECT p.*, m.* FROM payments p JOIN merchants m ON p.merchant_id = m.id
}

// Or use batch loading
public List<Payment> getPayments(Long userId) {
    List<Payment> payments = paymentRepo.findByUserId(userId);
    List<Long> merchantIds = payments.stream()
        .map(Payment::getMerchantId)
        .distinct()
        .collect(Collectors.toList());
    
    // Single query for all merchants
    Map<Long, Merchant> merchants = merchantRepo.findAllById(merchantIds)
        .stream()
        .collect(Collectors.toMap(Merchant::getId, Function.identity()));
    
    payments.forEach(p -> p.setMerchant(merchants.get(p.getMerchantId())));
    return payments;
}
```

**Step 3: Add Caching**
```java
// Cache frequently accessed data
@Service
public class MerchantService {
    @Autowired
    private RedisTemplate<String, Merchant> redis;
    
    @Cacheable(value = "merchants", key = "#id")
    public Merchant getMerchant(Long id) {
        // Check cache first
        Merchant cached = redis.opsForValue().get("merchant:" + id);
        if (cached != null) {
            return cached;
        }
        
        // Cache miss: load from database
        Merchant merchant = merchantRepo.findById(id);
        if (merchant != null) {
            // Cache for 1 hour
            redis.opsForValue().set("merchant:" + id, merchant, 
                1, TimeUnit.HOURS);
        }
        return merchant;
    }
}
```

**Step 4: Optimize External Calls**
```java
// BAD: Sequential external calls
public AuthorizationResponse authorize(Request req) {
    FraudResult fraud = fraudClient.check(req);  // 200ms
    RiskResult risk = riskClient.check(req);      // 150ms
    ComplianceResult compliance = complianceClient.check(req);  // 100ms
    
    // Total: 450ms
    return process(req, fraud, risk, compliance);
}

// GOOD: Parallel external calls
public AuthorizationResponse authorize(Request req) {
    CompletableFuture<FraudResult> fraudFuture = 
        CompletableFuture.supplyAsync(() -> fraudClient.check(req));
    CompletableFuture<RiskResult> riskFuture = 
        CompletableFuture.supplyAsync(() -> riskClient.check(req));
    CompletableFuture<ComplianceResult> complianceFuture = 
        CompletableFuture.supplyAsync(() -> complianceClient.check(req));
    
    // Wait for all (max of individual latencies)
    CompletableFuture.allOf(fraudFuture, riskFuture, complianceFuture).join();
    
    // Total: max(200ms, 150ms, 100ms) = 200ms
    return process(req, fraudFuture.get(), riskFuture.get(), complianceFuture.get());
}
```

**Step 5: Optimize Serialization**
```java
// BAD: Jackson (slow for large objects)
@RestController
public class PaymentController {
    @PostMapping("/authorize")
    public AuthorizationResponse authorize(@RequestBody Request req) {
        // Jackson parsing: 50ms for large JSON
        return service.authorize(req);
    }
}

// GOOD: Use faster serialization (Protobuf, Kryo)
@RestController
public class PaymentController {
    @PostMapping("/authorize")
    public AuthorizationResponse authorize(@RequestBody byte[] data) {
        // Protobuf parsing: 5ms
        Request req = Request.parseFrom(data);
        return service.authorize(req);
    }
}

// Or use Jackson with optimizations
@Configuration
public class JacksonConfig {
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .registerModule(new JavaTimeModule())
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    }
}
```

**Step 6: Reduce Memory Allocation**
```java
// BAD: Creating new objects in hot path
public void processPayments(List<Payment> payments) {
    for (Payment p : payments) {
        PaymentDTO dto = new PaymentDTO();  // New object per iteration
        dto.setId(p.getId());
        dto.setAmount(p.getAmount());
        process(dto);
    }
}

// GOOD: Reuse objects or use primitives
public void processPayments(List<Payment> payments) {
    PaymentDTO dto = new PaymentDTO();  // Reuse
    for (Payment p : payments) {
        dto.setId(p.getId());
        dto.setAmount(p.getAmount());
        process(dto);
    }
}

// Or avoid DTOs entirely if possible
public void processPayments(List<Payment> payments) {
    for (Payment p : payments) {
        process(p.getId(), p.getAmount());  // Pass primitives
    }
}
```

**Real Example:**
We optimized authorization API from 2s to 150ms:
1. **Database**: Added indexes, fixed N+1 queries (-800ms)
2. **Caching**: Cached merchant data (-200ms)
3. **Parallel calls**: Fraud/risk checks in parallel (-300ms)
4. **Connection pooling**: Reused database connections (-100ms)
5. **Serialization**: Switched to Protobuf (-50ms)

**Total improvement**: 2s → 150ms (13x faster)

**Follow-up Depth 1:** How do you measure and monitor performance improvements?

**A:** Measurement is critical. Here's my setup:

**1. APM (Application Performance Monitoring)**
```java
// Use Datadog, New Relic, or custom OpenTelemetry
@Service
public class PaymentService {
    @Autowired
    private Tracer tracer;
    
    public AuthorizationResponse authorize(Request req) {
        Span span = tracer.spanBuilder("authorize")
            .startSpan();
        
        try (Scope scope = span.makeCurrent()) {
            long startTime = System.nanoTime();
            
            // Business logic
            AuthorizationResponse response = processAuthorization(req);
            
            // Record timing
            long duration = System.nanoTime() - startTime;
            span.setAttribute("duration_ms", duration / 1_000_000);
            span.setAttribute("status", response.getStatus());
            
            // Record custom metrics
            meter.counterBuilder("authorizations.total")
                .build()
                .add(1, Attributes.of(
                    AttributeKey.stringKey("status"), response.getStatus()));
            
            return response;
        } finally {
            span.end();
        }
    }
}
```

**2. Load Testing**
```java
// Use JMeter, Gatling, or k6
// Test before and after optimizations

// Gatling example
class PaymentLoadTest extends Simulation {
  val httpProtocol = http.baseUrl("https://api.example.com")
  
  val scn = scenario("Authorization")
    .exec(http("authorize")
      .post("/authorize")
      .body(StringBody("""{"userId": 123, "amount": 100}"""))
      .check(status.is(200)))
  
  setUp(
    scn.inject(
      rampUsers(1000) during (60 seconds)  // 1000 users over 60s
    )
  ).protocols(httpProtocol)
}
```

**3. Benchmarking**
```java
// Use JMH (Java Microbenchmark Harness)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class PaymentBenchmark {
    private PaymentService service;
    
    @Setup
    public void setup() {
        service = new PaymentService();
    }
    
    @Benchmark
    public AuthorizationResponse benchmarkAuthorize() {
        Request req = new Request(123L, BigDecimal.valueOf(100));
        return service.authorize(req);
    }
}
```

**4. Continuous Monitoring**
```yaml
# Prometheus alerts for performance regression
- alert: PerformanceRegression
  expr: histogram_quantile(0.99, payments_latency_bucket) > 200
  for: 5m
  annotations:
    summary: "P99 latency exceeded threshold"
```

---

### Tech Stack Selection

**Question:** How do you choose the right tech stack for a new payment platform?

**Answer:**
I've made this decision many times. Here's my framework:

**Decision Factors:**
1. **Team Expertise**: What does the team know?
2. **Ecosystem**: What libraries/tools are available?
3. **Performance**: What are the requirements?
4. **Scalability**: How will it scale?
5. **Maintainability**: Long-term support?

**Real Example: Payment Authorization Service**

**Requirements:**
- 100K TPS
- <100ms p99 latency
- 99.9% uptime
- Team: 10 Java engineers

**Tech Stack Decision:**

**Language: Java vs. Go vs. Rust**
- **Java**: Team knows it, mature ecosystem, JVM overhead
- **Go**: Fast, simple, good concurrency, smaller ecosystem
- **Rust**: Fastest, memory-safe, steep learning curve

**Decision: Java** (team expertise, Spring Boot ecosystem)

**Framework: Spring Boot vs. Quarkus vs. Micronaut**
- **Spring Boot**: Mature, large ecosystem, slower startup
- **Quarkus**: Fast startup, GraalVM native, smaller ecosystem
- **Micronaut**: Fast, compile-time DI, smaller ecosystem

**Decision: Spring Boot** (team expertise, ecosystem)

**Database: PostgreSQL vs. MySQL vs. Oracle**
- **PostgreSQL**: Open source, good performance, ACID
- **MySQL**: Simpler, good for reads, weaker consistency
- **Oracle**: Enterprise-grade, expensive, complex

**Decision: PostgreSQL** (open source, good performance, ACID)

**Caching: Redis vs. Hazelcast vs. Caffeine**
- **Redis**: Distributed, persistent, network overhead
- **Hazelcast**: In-memory grid, Java-native, complex
- **Caffeine**: Local cache, fastest, no distribution

**Decision: Redis** (distributed, persistent, team knows it)

**Messaging: Kafka vs. RabbitMQ vs. Pulsar**
- **Kafka**: High throughput, distributed, complex
- **RabbitMQ**: Simple, good for queues, lower throughput
- **Pulsar**: Kafka alternative, better multi-tenancy

**Decision: Kafka** (high throughput requirement)

**Monitoring: Prometheus + Grafana vs. Datadog vs. New Relic**
- **Prometheus**: Open source, powerful, self-hosted
- **Datadog**: SaaS, easy setup, expensive
- **New Relic**: SaaS, good APM, expensive

**Decision: Prometheus + Grafana** (open source, cost-effective)

**Final Stack:**
- **Language**: Java 17
- **Framework**: Spring Boot 3.x
- **Database**: PostgreSQL 15
- **Caching**: Redis 7
- **Messaging**: Kafka 3.x
- **Monitoring**: Prometheus + Grafana + Jaeger
- **Deployment**: Kubernetes (EKS)

**Follow-up Depth 1:** How do you evaluate new technologies?

**A:** I use a structured evaluation process:

**1. Proof of Concept (POC)**
- Build a small feature using the new tech
- Measure: performance, developer experience, complexity
- Timebox: 1-2 weeks

**2. Evaluation Criteria**
- **Performance**: Does it meet requirements?
- **Developer Experience**: Is it easy to use?
- **Ecosystem**: Are there libraries/tools?
- **Community**: Is it actively maintained?
- **Risk**: What's the downside?

**3. Decision Framework**
```java
// Score each technology (1-10)
class TechEvaluation {
    double performance;      // How fast?
    double easeOfUse;        // How easy?
    double ecosystem;        // How mature?
    double community;       // How active?
    double risk;            // How risky? (lower = better)
    
    double totalScore() {
        return (performance * 0.3 + 
                easeOfUse * 0.2 + 
                ecosystem * 0.2 + 
                community * 0.2 + 
                (10 - risk) * 0.1);
    }
}
```

**Real Example:**
We evaluated Quarkus vs. Spring Boot:
- **Quarkus**: Performance 9, EaseOfUse 7, Ecosystem 6, Community 7, Risk 6 → Score 7.1
- **Spring Boot**: Performance 7, EaseOfUse 9, Ecosystem 10, Community 10, Risk 2 → Score 8.4

**Decision: Spring Boot** (better ecosystem, lower risk)

---

### Scalability Patterns

**Question:** How do you design a system to scale from 1K to 1M TPS?

**Answer:**
I've scaled systems 1000x. Here's my approach:

**Phase 1: Single Server (1K TPS)**
```
[Load Balancer] → [App Server] → [Database]
```
- Vertical scaling (bigger server)
- Connection pooling
- Basic caching

**Phase 2: Horizontal Scaling (10K TPS)**
```
[Load Balancer] → [App Servers × 10] → [Database + Read Replicas]
```
- Multiple app servers
- Read replicas for database
- Redis caching layer

**Phase 3: Microservices (100K TPS)**
```
[API Gateway] → [Auth Service] → [Payment Service] → [Database Shards]
                [Fraud Service] → [Risk Service]
```
- Service decomposition
- Database sharding
- Event-driven architecture

**Phase 4: Global Scale (1M TPS)**
```
[CDN] → [Regional API Gateways] → [Regional Services] → [Regional Databases]
                                                          [Global Database Sync]
```
- Multi-region deployment
- Data replication
- Geo-routing

**Real Example:**
We scaled a payment system from 1K to 100K TPS:

**Week 1-4: Optimize Single Server**
- Added database indexes (-50% query time)
- Added Redis caching (-30% database load)
- Optimized code (-20% CPU usage)
- **Result**: 1K → 5K TPS

**Week 5-8: Horizontal Scaling**
- Added 5 app servers (load balanced)
- Added 2 read replicas
- **Result**: 5K → 20K TPS

**Week 9-12: Microservices**
- Split fraud detection service
- Added Kafka for async processing
- **Result**: 20K → 50K TPS

**Week 13-16: Database Sharding**
- Sharded by user_id (4 shards)
- **Result**: 50K → 100K TPS

**Key Learnings:**
- **Optimize before scaling**: Fix bottlenecks first
- **Scale incrementally**: Don't over-engineer early
- **Measure everything**: Know your bottlenecks
- **Plan for growth**: Design for 10x, build for 2x

---

This comprehensive technical architecture section covers everything from basics to advanced concepts, with real-world examples, code samples, and practical troubleshooting guidance. The content follows the conversational, experienced tone with war stories throughout.

---

## Microservices Design Patterns & Anti-Patterns

### Microservices Design Patterns

**Question:** What are the essential design patterns for building resilient microservices?

**Answer:**
I've built microservices architectures for 8+ years. Here are the patterns that actually work in production:

**Pattern 1: API Gateway**
**Problem:** Clients need to call multiple services, handle authentication, rate limiting, etc.

**Solution:** Single entry point that routes requests to appropriate services.

```java
// API Gateway (Spring Cloud Gateway)
@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("payment-service", r -> r
                .path("/api/payments/**")
                .filters(f -> f
                    .addRequestHeader("X-User-Id", "${header.user-id}")
                    .circuitBreaker(c -> c
                        .setName("payment-circuit")
                        .setFallbackUri("forward:/fallback/payment")))
                .uri("lb://payment-service"))
            .route("fraud-service", r -> r
                .path("/api/fraud/**")
                .uri("lb://fraud-service"))
            .build();
    }
}

// Benefits:
// - Single entry point for clients
// - Centralized authentication/authorization
// - Rate limiting, circuit breakers
// - Request/response transformation
```

**Real War Story:**
We had 10 microservices, each with its own authentication. Clients had to manage 10 different tokens. We added an API Gateway:
- **Before**: Clients call 10 services, manage 10 tokens
- **After**: Clients call 1 gateway, gateway handles routing
- **Result**: Simpler client integration, centralized security

**Pattern 2: Circuit Breaker**
**Problem:** One failing service cascades to other services.

**Solution:** Fail fast when a service is down, prevent cascading failures.

```java
// Using Resilience4j
@Service
public class FraudServiceClient {
    private final CircuitBreaker circuitBreaker;
    private final RestTemplate restTemplate;
    
    public FraudServiceClient() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
            .failureRateThreshold(50)  // Open after 50% failures
            .waitDurationInOpenState(Duration.ofSeconds(30))
            .slidingWindowSize(10)
            .build();
        
        this.circuitBreaker = CircuitBreaker.of("fraud-service", config);
    }
    
    public FraudResult checkFraud(Request request) {
        return circuitBreaker.executeSupplier(() -> {
            try {
                return restTemplate.postForObject(
                    "http://fraud-service/check", request, FraudResult.class);
            } catch (Exception e) {
                // Fallback: Return low risk (don't block payments)
                return FraudResult.lowRisk();
            }
        });
    }
}

// States:
// - CLOSED: Normal operation
// - OPEN: Failing fast, return fallback
// - HALF_OPEN: Testing if service recovered
```

**Real Example:**
Fraud service went down. Without circuit breaker:
- Payment service waits 30s timeout per request
- 1000 requests/sec × 30s = 30,000 requests queued
- Payment service OOM, cascading failure

With circuit breaker:
- Circuit opens after 5 failures
- Payment service fails fast (1ms), uses fallback
- System stays up, processes payments without fraud check

**Pattern 3: Service Discovery**
**Problem:** Services need to find each other, but IPs change in cloud.

**Solution:** Service registry (Eureka, Consul, Kubernetes DNS).

```java
// Eureka Service Discovery
@SpringBootApplication
@EnableEurekaClient
public class PaymentServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaymentServiceApplication.class, args);
    }
}

// Service calls use service name, not IP
@Service
public class FraudServiceClient {
    @Autowired
    private RestTemplate restTemplate;
    
    public FraudResult checkFraud(Request request) {
        // Use service name, Eureka resolves to IP
        return restTemplate.postForObject(
            "http://fraud-service/check", request, FraudResult.class);
    }
}

// Kubernetes DNS (even simpler)
// Service name resolves automatically
// http://fraud-service.default.svc.cluster.local
```

**Pattern 4: Distributed Tracing**
**Problem:** Request flows through 5 services, hard to debug.

**Solution:** Trace requests across services with correlation IDs.

```java
// Add correlation ID to all requests
@Component
public class CorrelationIdFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, 
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        
        // Extract or generate correlation ID
        String correlationId = httpRequest.getHeader("X-Correlation-ID");
        if (correlationId == null) {
            correlationId = UUID.randomUUID().toString();
        }
        
        // Add to MDC for logging
        MDC.put("correlationId", correlationId);
        
        // Add to response headers
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("X-Correlation-ID", correlationId);
        
        try {
            chain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }
}

// Logs automatically include correlation ID
log.info("Processing payment {}", paymentId);
// Output: [correlation-id: abc-123] Processing payment 456
```

**Pattern 5: Database per Service**
**Problem:** Services share database, tight coupling.

**Solution:** Each service has its own database.

```java
// Payment Service Database
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    private Long id;
    private Long userId;
    private BigDecimal amount;
    // Payment service owns this data
}

// Fraud Service Database (separate)
@Entity
@Table(name = "fraud_scores")
public class FraudScore {
    @Id
    private Long id;
    private Long userId;
    private Integer riskScore;
    // Fraud service owns this data
}

// Services communicate via APIs, not database
```

**Benefits:**
- **Independence**: Services can use different databases
- **Scalability**: Scale databases independently
- **Technology diversity**: Payment service uses PostgreSQL, Fraud service uses MongoDB

**Anti-Pattern:** Shared Database
```java
// BAD: Services share database
// Payment Service
SELECT * FROM payments WHERE user_id = 123;

// Fraud Service
SELECT * FROM payments WHERE user_id = 123;  // Same table!

// Problems:
// - Tight coupling (schema changes affect both)
// - Can't scale independently
// - Can't use different databases
```

---

### Microservices Anti-Patterns

**Question:** What are the common mistakes when building microservices?

**Answer:**
I've seen teams make these mistakes repeatedly. Here's what to avoid:

**Anti-Pattern 1: Distributed Monolith**
**Problem:** Microservices that are tightly coupled, must deploy together.

```java
// BAD: Services call each other synchronously in a chain
@Service
public class PaymentService {
    public void processPayment(Request req) {
        // Call fraud service
        FraudResult fraud = fraudClient.check(req);  // Blocking
        
        // Call risk service
        RiskResult risk = riskClient.check(req);  // Blocking
        
        // Call compliance service
        ComplianceResult compliance = complianceClient.check(req);  // Blocking
        
        // All services must be up, or payment fails
        process(req, fraud, risk, compliance);
    }
}

// Problems:
// - Services must deploy together (tight coupling)
// - One service down = entire flow fails
// - Network latency adds up (200ms × 3 = 600ms)
```

**Solution: Event-Driven Architecture**
```java
// GOOD: Services communicate via events
@Service
public class PaymentService {
    @Autowired
    private KafkaTemplate<String, PaymentEvent> kafka;
    
    public void processPayment(Request req) {
        // Create payment
        Payment payment = createPayment(req);
        
        // Publish event (async, non-blocking)
        PaymentCreatedEvent event = new PaymentCreatedEvent(
            payment.getId(), req.getUserId(), req.getAmount());
        kafka.send("payment-events", event);
        
        // Return immediately (don't wait for fraud/risk/compliance)
        return payment;
    }
}

// Fraud Service listens to events
@KafkaListener(topics = "payment-events")
public void handlePaymentCreated(PaymentCreatedEvent event) {
    FraudResult fraud = checkFraud(event);
    
    // Publish result
    FraudCheckedEvent result = new FraudCheckedEvent(
        event.getPaymentId(), fraud);
    kafka.send("fraud-events", result);
}

// Benefits:
// - Services decoupled (can deploy independently)
// - Resilient (one service down doesn't break others)
// - Fast (non-blocking)
```

**Anti-Pattern 2: Chatty Services**
**Problem:** Services make many small calls instead of one large call.

```java
// BAD: Multiple calls for related data
@Service
public class OrderService {
    public Order getOrder(Long orderId) {
        Order order = orderRepo.findById(orderId);
        
        // Call user service for each item
        for (OrderItem item : order.getItems()) {
            User user = userClient.getUser(item.getUserId());  // N calls
            item.setUser(user);
        }
        
        // Call product service for each item
        for (OrderItem item : order.getItems()) {
            Product product = productClient.getProduct(item.getProductId());  // N calls
            item.setProduct(product);
        }
        
        return order;  // 2N network calls!
    }
}
```

**Solution: Batch API or Data Aggregation**
```java
// GOOD: Batch API
@Service
public class OrderService {
    public Order getOrder(Long orderId) {
        Order order = orderRepo.findById(orderId);
        
        // Collect all IDs
        List<Long> userIds = order.getItems().stream()
            .map(OrderItem::getUserId)
            .distinct()
            .collect(Collectors.toList());
        
        List<Long> productIds = order.getItems().stream()
            .map(OrderItem::getProductId)
            .distinct()
            .collect(Collectors.toList());
        
        // Single batch call
        Map<Long, User> users = userClient.getUsers(userIds);  // 1 call
        Map<Long, Product> products = productClient.getProducts(productIds);  // 1 call
        
        // Populate order
        order.getItems().forEach(item -> {
            item.setUser(users.get(item.getUserId()));
            item.setProduct(products.get(item.getProductId()));
        });
        
        return order;  // 2 network calls total
    }
}

// Or use GraphQL for flexible queries
```

**Anti-Pattern 3: Shared Libraries**
**Problem:** Services share code libraries, creating coupling.

```java
// BAD: Shared library with business logic
// common-library/src/main/java/com/company/PaymentUtils.java
public class PaymentUtils {
    public static BigDecimal calculateFee(BigDecimal amount) {
        // Business logic in shared library
        return amount.multiply(BigDecimal.valueOf(0.03));
    }
}

// Payment Service
import com.company.PaymentUtils;
BigDecimal fee = PaymentUtils.calculateFee(amount);

// Billing Service
import com.company.PaymentUtils;
BigDecimal fee = PaymentUtils.calculateFee(amount);

// Problems:
// - Changes to library require all services to redeploy
// - Can't evolve services independently
// - Version conflicts
```

**Solution: API Contracts, Not Code Sharing**
```java
// GOOD: Each service implements its own logic
// Payment Service
@Service
public class PaymentService {
    public BigDecimal calculateFee(BigDecimal amount) {
        // Payment-specific logic
        return amount.multiply(BigDecimal.valueOf(0.03));
    }
}

// Billing Service
@Service
public class BillingService {
    public BigDecimal calculateFee(BigDecimal amount) {
        // Billing-specific logic (might be different)
        return amount.multiply(BigDecimal.valueOf(0.025));
    }
}

// Services communicate via APIs, not shared code
// OK to share: utilities, DTOs, constants (but be careful)
```

**Anti-Pattern 4: God Service**
**Problem:** One service does everything.

```java
// BAD: Payment service does everything
@Service
public class PaymentService {
    public void processPayment(Request req) {
        // Payment logic
        Payment payment = createPayment(req);
        
        // Fraud check (should be separate service)
        FraudResult fraud = checkFraud(req);
        
        // Risk assessment (should be separate service)
        RiskResult risk = assessRisk(req);
        
        // Notification (should be separate service)
        sendNotification(req);
        
        // Reporting (should be separate service)
        generateReport(payment);
    }
}
```

**Solution: Single Responsibility Principle**
```java
// GOOD: Each service has one responsibility
// Payment Service: Payment processing only
@Service
public class PaymentService {
    public Payment processPayment(Request req) {
        return createPayment(req);
    }
}

// Fraud Service: Fraud detection only
@Service
public class FraudService {
    public FraudResult checkFraud(Request req) {
        return assessFraud(req);
    }
}

// Notification Service: Notifications only
@Service
public class NotificationService {
    public void sendNotification(Event event) {
        // Send notification
    }
}
```

**Real War Story:**
We had a "Payment Service" that did payments, fraud, risk, notifications, reporting. Problems:
- **Deployment**: Any change required redeploying everything
- **Scaling**: Couldn't scale fraud independently (needed more ML compute)
- **Team**: 3 teams fighting over one codebase

We split into 5 services. Took 6 months, but worth it:
- **Independent deployment**: Deploy fraud service without touching payment
- **Independent scaling**: Scale fraud service with GPU instances
- **Team autonomy**: Each team owns their service

---

### REST API Design Patterns

**Question:** What are the best practices for designing REST APIs in microservices?

**Answer:**
I've designed dozens of REST APIs. Here's what works:

**Pattern 1: Resource-Based URLs**
```java
// GOOD: Resources, not actions
GET    /api/payments/{id}           // Get payment
POST   /api/payments                 // Create payment
PUT    /api/payments/{id}            // Update payment
DELETE /api/payments/{id}            // Delete payment

// BAD: Actions in URLs
POST   /api/createPayment            // Don't do this
POST   /api/updatePayment            // Don't do this
POST   /api/deletePayment            // Don't do this
```

**Pattern 2: HTTP Status Codes**
```java
@RestController
public class PaymentController {
    @PostMapping("/api/payments")
    public ResponseEntity<Payment> createPayment(@RequestBody PaymentRequest req) {
        Payment payment = paymentService.create(req);
        
        // 201 Created for successful creation
        return ResponseEntity.status(HttpStatus.CREATED)
            .header("Location", "/api/payments/" + payment.getId())
            .body(payment);
    }
    
    @GetMapping("/api/payments/{id}")
    public ResponseEntity<Payment> getPayment(@PathVariable Long id) {
        Payment payment = paymentService.findById(id);
        
        if (payment == null) {
            // 404 Not Found
            return ResponseEntity.notFound().build();
        }
        
        // 200 OK
        return ResponseEntity.ok(payment);
    }
    
    @PutMapping("/api/payments/{id}")
    public ResponseEntity<Payment> updatePayment(
            @PathVariable Long id, 
            @RequestBody PaymentRequest req) {
        Payment payment = paymentService.update(id, req);
        
        if (payment == null) {
            // 404 Not Found
            return ResponseEntity.notFound().build();
        }
        
        // 200 OK
        return ResponseEntity.ok(payment);
    }
    
    @DeleteMapping("/api/payments/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        boolean deleted = paymentService.delete(id);
        
        if (!deleted) {
            // 404 Not Found
            return ResponseEntity.notFound().build();
        }
        
        // 204 No Content (successful deletion)
        return ResponseEntity.noContent().build();
    }
}
```

**Pattern 3: Error Handling**
```java
// Standard error response
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private String error;
    private String message;
    private String code;
    private String timestamp;
    private String path;
    private List<ValidationError> errors;  // For validation errors
    
    // Getters/setters
}

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            ValidationException e, HttpServletRequest request) {
        ErrorResponse error = ErrorResponse.builder()
            .error("Validation Failed")
            .message(e.getMessage())
            .code("VALIDATION_ERROR")
            .timestamp(Instant.now().toString())
            .path(request.getRequestURI())
            .errors(e.getErrors())
            .build();
        
        // 400 Bad Request
        return ResponseEntity.badRequest().body(error);
    }
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(
            ResourceNotFoundException e, HttpServletRequest request) {
        ErrorResponse error = ErrorResponse.builder()
            .error("Not Found")
            .message(e.getMessage())
            .code("NOT_FOUND")
            .timestamp(Instant.now().toString())
            .path(request.getRequestURI())
            .build();
        
        // 404 Not Found
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(
            Exception e, HttpServletRequest request) {
        ErrorResponse error = ErrorResponse.builder()
            .error("Internal Server Error")
            .message("An unexpected error occurred")
            .code("INTERNAL_ERROR")
            .timestamp(Instant.now().toString())
            .path(request.getRequestURI())
            .build();
        
        // 500 Internal Server Error
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
```

**Pattern 4: Pagination**
```java
// Cursor-based pagination (better for large datasets)
@GetMapping("/api/payments")
public ResponseEntity<PagedResponse<Payment>> getPayments(
        @RequestParam(required = false) String cursor,
        @RequestParam(defaultValue = "20") int limit) {
    
    PagedResponse<Payment> response = paymentService.getPayments(cursor, limit);
    
    // Add pagination links
    HttpHeaders headers = new HttpHeaders();
    if (response.getNextCursor() != null) {
        headers.add("Link", 
            "</api/payments?cursor=" + response.getNextCursor() + "&limit=" + limit + ">; rel=\"next\"");
    }
    if (response.getPreviousCursor() != null) {
        headers.add("Link", 
            "</api/payments?cursor=" + response.getPreviousCursor() + "&limit=" + limit + ">; rel=\"prev\"");
    }
    
    return ResponseEntity.ok().headers(headers).body(response);
}

// Response
{
  "data": [...],
  "nextCursor": "abc123",
  "previousCursor": "xyz789",
  "hasMore": true
}
```

**Pattern 5: Versioning**
```java
// URL versioning (simplest)
@RestController
@RequestMapping("/api/v1/payments")
public class PaymentControllerV1 {
    // V1 implementation
}

@RestController
@RequestMapping("/api/v2/payments")
public class PaymentControllerV2 {
    // V2 implementation (can coexist with V1)
}

// Header versioning (cleaner URLs)
@GetMapping(value = "/api/payments", headers = "API-Version=2")
public ResponseEntity<PaymentV2> getPaymentV2(@PathVariable Long id) {
    // V2 implementation
}

// Content negotiation (most RESTful)
@GetMapping(value = "/api/payments", 
           produces = "application/vnd.company.payment.v2+json")
public ResponseEntity<PaymentV2> getPaymentV2(@PathVariable Long id) {
    // V2 implementation
}
```

---

### REST API Anti-Patterns

**Question:** What are common mistakes in REST API design?

**Answer:**
I've seen these mistakes repeatedly. Here's what to avoid:

**Anti-Pattern 1: Returning 200 OK for Errors**
```java
// BAD: Always return 200, error in body
@PostMapping("/api/payments")
public ResponseEntity<PaymentResponse> createPayment(@RequestBody Request req) {
    try {
        Payment payment = paymentService.create(req);
        return ResponseEntity.ok(new PaymentResponse(payment, "SUCCESS"));
    } catch (Exception e) {
        // Still return 200!
        return ResponseEntity.ok(new PaymentResponse(null, "ERROR: " + e.getMessage()));
    }
}

// Problems:
// - HTTP clients can't distinguish success from failure
// - Can't use HTTP status codes for error handling
// - Breaks REST semantics
```

**Solution: Use Proper HTTP Status Codes**
```java
// GOOD: Use appropriate status codes
@PostMapping("/api/payments")
public ResponseEntity<Payment> createPayment(@RequestBody Request req) {
    try {
        Payment payment = paymentService.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(payment);
    } catch (ValidationException e) {
        return ResponseEntity.badRequest().body(errorResponse);
    } catch (NotFoundException e) {
        return ResponseEntity.notFound().build();
    }
}
```

**Anti-Pattern 2: Ignoring HTTP Methods**
```java
// BAD: Everything is POST
@PostMapping("/api/getPayment")
public Payment getPayment(@RequestBody Request req) { ... }

@PostMapping("/api/updatePayment")
public Payment updatePayment(@RequestBody Request req) { ... }

@PostMapping("/api/deletePayment")
public void deletePayment(@RequestBody Request req) { ... }

// Problems:
// - Not RESTful
// - Can't use HTTP caching
// - Can't use HTTP semantics
```

**Solution: Use Appropriate HTTP Methods**
```java
// GOOD: Use GET, POST, PUT, DELETE
@GetMapping("/api/payments/{id}")
public Payment getPayment(@PathVariable Long id) { ... }

@PostMapping("/api/payments")
public Payment createPayment(@RequestBody Request req) { ... }

@PutMapping("/api/payments/{id}")
public Payment updatePayment(@PathVariable Long id, @RequestBody Request req) { ... }

@DeleteMapping("/api/payments/{id}")
public void deletePayment(@PathVariable Long id) { ... }
```

**Anti-Pattern 3: Exposing Internal Implementation**
```java
// BAD: Expose database entities directly
@Entity
public class Payment {
    @Id
    private Long id;
    private BigDecimal amount;
    private String internalStatus;  // Internal field
    private String dbTimestamp;      // Database field
    // ...
}

@RestController
public class PaymentController {
    @GetMapping("/api/payments/{id}")
    public Payment getPayment(@PathVariable Long id) {
        // Returns entity directly (exposes internals)
        return paymentRepository.findById(id);
    }
}

// Problems:
// - Exposes internal structure
// - Can't change internal implementation without breaking API
// - Security risk (might expose sensitive fields)
```

**Solution: Use DTOs**
```java
// GOOD: Use DTOs for API
public class PaymentDTO {
    private Long id;
    private BigDecimal amount;
    private String status;  // Public status, not internal
    private LocalDateTime createdAt;  // Formatted timestamp
    // Only expose what clients need
}

@RestController
public class PaymentController {
    @GetMapping("/api/payments/{id}")
    public PaymentDTO getPayment(@PathVariable Long id) {
        Payment payment = paymentService.findById(id);
        return PaymentDTO.from(payment);  // Convert entity to DTO
    }
}
```

**Anti-Pattern 4: Inconsistent Naming**
```java
// BAD: Inconsistent naming
GET /api/getPayments
GET /api/payment/list
GET /api/payments/all
POST /api/createPayment
POST /api/payment/new

// Problems:
// - Hard to remember
// - Inconsistent patterns
```

**Solution: Consistent RESTful Naming**
```java
// GOOD: Consistent naming
GET    /api/payments           // List all
GET    /api/payments/{id}      // Get one
POST   /api/payments           // Create
PUT    /api/payments/{id}      // Update
DELETE /api/payments/{id}      // Delete

// Sub-resources
GET    /api/payments/{id}/transactions
POST   /api/payments/{id}/refund
```

**Anti-Pattern 5: No Rate Limiting**
```java
// BAD: No rate limiting
@RestController
public class PaymentController {
    @PostMapping("/api/payments")
    public Payment createPayment(@RequestBody Request req) {
        // Anyone can call this unlimited times
        return paymentService.create(req);
    }
}

// Problems:
// - Vulnerable to abuse
// - Can't protect against DDoS
// - Can't enforce quotas
```

**Solution: Implement Rate Limiting**
```java
// GOOD: Rate limiting
@RestController
public class PaymentController {
    @Autowired
    private RateLimiter rateLimiter;
    
    @PostMapping("/api/payments")
    public ResponseEntity<Payment> createPayment(@RequestBody Request req) {
        // Check rate limit
        if (!rateLimiter.tryAcquire(req.getMerchantId())) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .header("Retry-After", "60")
                .build();
        }
        
        Payment payment = paymentService.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(payment);
    }
}

// Using Redis for distributed rate limiting
@Service
public class RateLimiter {
    @Autowired
    private RedisTemplate<String, String> redis;
    
    public boolean tryAcquire(String key) {
        String count = redis.opsForValue().get("rate:" + key);
        if (count == null) {
            redis.opsForValue().set("rate:" + key, "1", 60, TimeUnit.SECONDS);
            return true;
        }
        
        int current = Integer.parseInt(count);
        if (current >= 100) {  // 100 requests per minute
            return false;
        }
        
        redis.opsForValue().increment("rate:" + key);
        return true;
    }
}
```

---

## Advanced Testing: JUnit, Mockito & Integration Testing

### Testing Void Methods & Argument Capturing

**Question:** How do you test void methods and verify they're called with correct arguments?

**Answer:**
I've written thousands of tests. Here's how I handle void methods and argument verification:

**Testing Void Methods:**
```java
// Service with void method
@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    
    @Autowired
    private KafkaTemplate<String, PaymentEvent> kafka;
    
    @Autowired
    private NotificationService notificationService;
    
    public void processPayment(PaymentRequest request) {
        // Create payment
        Payment payment = new Payment(request.getUserId(), request.getAmount());
        paymentRepository.save(payment);
        
        // Publish event
        PaymentEvent event = new PaymentEvent(payment.getId(), payment.getStatus());
        kafka.send("payment-events", event);
        
        // Send notification
        notificationService.sendNotification(payment.getUserId(), "Payment processed");
    }
}

// Testing void method
@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {
    @Mock
    private PaymentRepository paymentRepository;
    
    @Mock
    private KafkaTemplate<String, PaymentEvent> kafka;
    
    @Mock
    private NotificationService notificationService;
    
    @InjectMocks
    private PaymentService paymentService;
    
    @Test
    void testProcessPayment_CallsAllDependencies() {
        // Given
        PaymentRequest request = new PaymentRequest(123L, BigDecimal.valueOf(100));
        Payment savedPayment = new Payment(123L, BigDecimal.valueOf(100));
        savedPayment.setId(1L);
        
        when(paymentRepository.save(any(Payment.class))).thenReturn(savedPayment);
        
        // When
        paymentService.processPayment(request);
        
        // Then: Verify all void methods were called
        verify(paymentRepository).save(any(Payment.class));
        verify(kafka).send(eq("payment-events"), any(PaymentEvent.class));
        verify(notificationService).sendNotification(eq(123L), eq("Payment processed"));
    }
}
```

**Argument Capturing:**
```java
// When you need to verify the exact arguments passed
@Test
void testProcessPayment_CapturesCorrectArguments() {
    // Given
    PaymentRequest request = new PaymentRequest(123L, BigDecimal.valueOf(100));
    Payment savedPayment = new Payment(123L, BigDecimal.valueOf(100));
    savedPayment.setId(1L);
    
    when(paymentRepository.save(any(Payment.class))).thenReturn(savedPayment);
    
    // ArgumentCaptor for complex objects
    ArgumentCaptor<Payment> paymentCaptor = ArgumentCaptor.forClass(Payment.class);
    ArgumentCaptor<PaymentEvent> eventCaptor = ArgumentCaptor.forClass(PaymentEvent.class);
    
    // When
    paymentService.processPayment(request);
    
    // Then: Capture and verify arguments
    verify(paymentRepository).save(paymentCaptor.capture());
    Payment capturedPayment = paymentCaptor.getValue();
    assertEquals(123L, capturedPayment.getUserId());
    assertEquals(BigDecimal.valueOf(100), capturedPayment.getAmount());
    
    verify(kafka).send(eq("payment-events"), eventCaptor.capture());
    PaymentEvent capturedEvent = eventCaptor.getValue();
    assertEquals(1L, capturedEvent.getPaymentId());
    assertEquals("PROCESSED", capturedEvent.getStatus());
}

// Capturing multiple invocations
@Test
void testProcessMultiplePayments_CapturesAllInvocations() {
    // Given
    PaymentRequest request1 = new PaymentRequest(123L, BigDecimal.valueOf(100));
    PaymentRequest request2 = new PaymentRequest(456L, BigDecimal.valueOf(200));
    
    when(paymentRepository.save(any(Payment.class)))
        .thenReturn(new Payment(123L, BigDecimal.valueOf(100)))
        .thenReturn(new Payment(456L, BigDecimal.valueOf(200)));
    
    ArgumentCaptor<PaymentEvent> eventCaptor = ArgumentCaptor.forClass(PaymentEvent.class);
    
    // When
    paymentService.processPayment(request1);
    paymentService.processPayment(request2);
    
    // Then: Capture all invocations
    verify(kafka, times(2)).send(eq("payment-events"), eventCaptor.capture());
    
    List<PaymentEvent> allEvents = eventCaptor.getAllValues();
    assertEquals(2, allEvents.size());
    assertEquals(123L, allEvents.get(0).getUserId());
    assertEquals(456L, allEvents.get(1).getUserId());
}
```

**Real War Story:**
We had a bug where payment events were published with wrong status. Without argument capturing, we couldn't verify the exact event data. Added argument captors, found the bug in 10 minutes.

---

### Advanced Mockito Scenarios

**Question:** How do you handle complex mocking scenarios like partial mocks, spies, and static methods?

**Answer:**
I've dealt with every edge case. Here's what works:

**Spies (Partial Mocks):**
```java
// When you need to mock some methods but call real methods for others
@Service
public class PaymentService {
    public Payment createPayment(PaymentRequest request) {
        Payment payment = new Payment();
        payment.setUserId(request.getUserId());
        payment.setAmount(request.getAmount());
        payment.setStatus(calculateStatus(request));  // Want to test this
        return payment;
    }
    
    protected String calculateStatus(PaymentRequest request) {
        // Complex business logic we want to test
        if (request.getAmount().compareTo(BigDecimal.valueOf(1000)) > 0) {
            return "REVIEW_REQUIRED";
        }
        return "APPROVED";
    }
}

// Using spy to test real method while mocking dependencies
@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {
    @Spy
    private PaymentService paymentService;  // Spy, not mock
    
    @Test
    void testCreatePayment_UsesRealCalculateStatus() {
        // Given
        PaymentRequest request = new PaymentRequest(123L, BigDecimal.valueOf(500));
        
        // When: Calls real createPayment, which calls real calculateStatus
        Payment payment = paymentService.createPayment(request);
        
        // Then
        assertEquals("APPROVED", payment.getStatus());
    }
    
    @Test
    void testCreatePayment_LargeAmount_RequiresReview() {
        // Given
        PaymentRequest request = new PaymentRequest(123L, BigDecimal.valueOf(1500));
        
        // When
        Payment payment = paymentService.createPayment(request);
        
        // Then
        assertEquals("REVIEW_REQUIRED", payment.getStatus());
    }
    
    // Mocking specific method while keeping others real
    @Test
    void testCreatePayment_MockCalculateStatus() {
        // Given
        PaymentRequest request = new PaymentRequest(123L, BigDecimal.valueOf(500));
        doReturn("MANUAL_REVIEW").when(paymentService).calculateStatus(any());
        
        // When
        Payment payment = paymentService.createPayment(request);
        
        // Then: Uses mocked calculateStatus
        assertEquals("MANUAL_REVIEW", payment.getStatus());
    }
}
```

**Mocking Static Methods (Mockito 3.4+):**
```java
// Service using static utility
@Service
public class PaymentService {
    public Payment createPayment(PaymentRequest request) {
        Payment payment = new Payment();
        payment.setId(UUID.randomUUID());  // Static method
        payment.setCreatedAt(LocalDateTime.now());  // Static method
        payment.setUserId(request.getUserId());
        return payment;
    }
}

// Mocking static methods
@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {
    @Mock
    private static MockedStatic<UUID> uuidMock;
    
    @Mock
    private static MockedStatic<LocalDateTime> dateTimeMock;
    
    @InjectMocks
    private PaymentService paymentService;
    
    @Test
    void testCreatePayment_MocksStaticMethods() {
        // Given
        UUID fixedUuid = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        LocalDateTime fixedTime = LocalDateTime.of(2024, 1, 1, 12, 0);
        
        try (MockedStatic<UUID> uuidMocked = mockStatic(UUID.class);
             MockedStatic<LocalDateTime> dateTimeMocked = mockStatic(LocalDateTime.class)) {
            
            uuidMocked.when(UUID::randomUUID).thenReturn(fixedUuid);
            dateTimeMocked.when(LocalDateTime::now).thenReturn(fixedTime);
            
            PaymentRequest request = new PaymentRequest(123L, BigDecimal.valueOf(100));
            
            // When
            Payment payment = paymentService.createPayment(request);
            
            // Then
            assertEquals(fixedUuid, payment.getId());
            assertEquals(fixedTime, payment.getCreatedAt());
        }
    }
}
```

**Mocking Final Classes:**
```java
// Mockito can't mock final classes by default (Java 17+)
// Solution: Use mockito-inline

// pom.xml
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-inline</artifactId>
    <version>5.2.0</version>
    <scope>test</scope>
</dependency>

// Now you can mock final classes
final class PaymentValidator {
    public boolean validate(PaymentRequest request) {
        return request.getAmount().compareTo(BigDecimal.ZERO) > 0;
    }
}

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {
    @Mock
    private PaymentValidator validator;  // Can mock final class with mockito-inline
    
    @Test
    void testWithFinalClassMock() {
        when(validator.validate(any())).thenReturn(true);
        // Test implementation
    }
}
```

**Verifying Interactions:**
```java
// Verify method was never called
@Test
void testProcessPayment_DoesNotCallNotification_WhenDisabled() {
    // Given
    PaymentRequest request = new PaymentRequest(123L, BigDecimal.valueOf(100));
    when(paymentRepository.save(any())).thenReturn(new Payment());
    
    // When
    paymentService.processPayment(request);
    
    // Then: Verify notification was NOT called
    verify(notificationService, never()).sendNotification(anyLong(), anyString());
}

// Verify method was called in order
@Test
void testProcessPayment_CallsMethodsInOrder() {
    // Given
    PaymentRequest request = new PaymentRequest(123L, BigDecimal.valueOf(100));
    
    InOrder inOrder = inOrder(paymentRepository, kafka, notificationService);
    
    // When
    paymentService.processPayment(request);
    
    // Then: Verify order
    inOrder.verify(paymentRepository).save(any(Payment.class));
    inOrder.verify(kafka).send(anyString(), any(PaymentEvent.class));
    inOrder.verify(notificationService).sendNotification(anyLong(), anyString());
}

// Verify with timeout (for async operations)
@Test
void testProcessPayment_AsyncNotification_CalledWithinTimeout() {
    // Given
    PaymentRequest request = new PaymentRequest(123L, BigDecimal.valueOf(100));
    
    // When
    paymentService.processPaymentAsync(request);
    
    // Then: Verify called within 1 second
    verify(notificationService, timeout(1000)).sendNotification(anyLong(), anyString());
}
```

---

### Integration Testing Strategies

**Question:** How do you balance unit tests with integration tests, especially for database operations?

**Answer:**
I've learned this the hard way. Here's my approach:

**Strategy: Test Pyramid**
```
        /\
       /  \      E2E Tests (10%)
      /____\     - Full system, slow, expensive
     /      \    
    /________\   Integration Tests (20%)
   /          \  - Services + Database, medium speed
  /____________\ Unit Tests (70%)
                 - Fast, isolated, cheap
```

**Unit Tests (Fast, Isolated):**
```java
// Test business logic in isolation
@ExtendWith(MockitoExtension.class)
class PaymentServiceUnitTest {
    @Mock
    private PaymentRepository paymentRepository;
    
    @InjectMocks
    private PaymentService paymentService;
    
    @Test
    void testCalculateFee_UnitTest() {
        // Fast: No database, no network
        BigDecimal amount = BigDecimal.valueOf(100);
        BigDecimal fee = paymentService.calculateFee(amount);
        assertEquals(BigDecimal.valueOf(3), fee);
    }
}
```

**Integration Tests (Database + Services):**
```java
// Test with real database (in-memory H2 or Testcontainers)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class PaymentServiceIntegrationTest {
    @Autowired
    private PaymentRepository paymentRepository;
    
    @Autowired
    private PaymentService paymentService;
    
    @Test
    void testCreatePayment_IntegrationTest() {
        // Given
        PaymentRequest request = new PaymentRequest(123L, BigDecimal.valueOf(100));
        
        // When
        Payment payment = paymentService.createPayment(request);
        
        // Then: Verify in database
        Payment saved = paymentRepository.findById(payment.getId()).orElseThrow();
        assertEquals(123L, saved.getUserId());
        assertEquals(BigDecimal.valueOf(100), saved.getAmount());
    }
}
```

**Real Example: Payment Flow Integration Test**
```java
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class PaymentIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private PaymentRepository paymentRepository;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @MockBean
    private KafkaTemplate<String, PaymentEvent> kafka;  // Mock external dependency
    
    @Test
    void testPaymentFlow_EndToEnd() throws Exception {
        // Given
        PaymentRequest request = new PaymentRequest(123L, BigDecimal.valueOf(100));
        
        // When: Call REST API
        mockMvc.perform(post("/api/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userId").value(123L))
                .andExpect(jsonPath("$.amount").value(100));
        
        // Then: Verify in database
        List<Payment> payments = paymentRepository.findByUserId(123L);
        assertEquals(1, payments.size());
        
        // Verify Kafka event was published
        verify(kafka).send(eq("payment-events"), any(PaymentEvent.class));
    }
}
```

**Database Mocking Strategies:**

**Option 1: In-Memory H2 (Fast, Simple)**
```java
// application-test.yml
spring:
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
  jpa:
    hibernate:
      ddl-auto: create-drop
    show-sql: true

@SpringBootTest
@ActiveProfiles("test")
class PaymentRepositoryTest {
    @Autowired
    private PaymentRepository paymentRepository;
    
    @Test
    void testSavePayment() {
        Payment payment = new Payment(123L, BigDecimal.valueOf(100));
        Payment saved = paymentRepository.save(payment);
        assertNotNull(saved.getId());
    }
}
```

**Option 2: Testcontainers (Real Database, Slower)**
```java
// Use real PostgreSQL in Docker
@Testcontainers
@SpringBootTest
class PaymentRepositoryTest {
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");
    
    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }
    
    @Autowired
    private PaymentRepository paymentRepository;
    
    @Test
    void testSavePayment_RealDatabase() {
        Payment payment = new Payment(123L, BigDecimal.valueOf(100));
        Payment saved = paymentRepository.save(payment);
        assertNotNull(saved.getId());
    }
}
```

**Option 3: @DataJpaTest (Repository Layer Only)**
```java
// Test only JPA layer, mock everything else
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PaymentRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private PaymentRepository paymentRepository;
    
    @Test
    void testFindByUserId() {
        // Given
        Payment payment = new Payment(123L, BigDecimal.valueOf(100));
        entityManager.persistAndFlush(payment);
        
        // When
        List<Payment> payments = paymentRepository.findByUserId(123L);
        
        // Then
        assertEquals(1, payments.size());
        assertEquals(123L, payments.get(0).getUserId());
    }
}
```

**Real War Story:**
We had 2000 unit tests that all passed, but production failed. Why? Unit tests mocked the database, but real database had constraints we didn't test. Solution:
- **Unit tests**: Test business logic (70%)
- **Integration tests**: Test database interactions (20%)
- **E2E tests**: Test full flow (10%)

Now we catch database issues in integration tests before production.

---

### Testing Async Operations & Reactive Code

**Question:** How do you test async operations, CompletableFuture, and reactive streams?

**Answer:**
Async testing is tricky. Here's what works:

**Testing CompletableFuture:**
```java
@Service
public class PaymentService {
    @Autowired
    private ExecutorService executorService;
    
    public CompletableFuture<Payment> processPaymentAsync(PaymentRequest request) {
        return CompletableFuture.supplyAsync(() -> {
            // Simulate async processing
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return createPayment(request);
        }, executorService);
    }
}

@ExtendWith(MockitoExtension.class)
class PaymentServiceAsyncTest {
    @Mock
    private ExecutorService executorService;
    
    @InjectMocks
    private PaymentService paymentService;
    
    @Test
    void testProcessPaymentAsync_CompletesSuccessfully() throws Exception {
        // Given
        PaymentRequest request = new PaymentRequest(123L, BigDecimal.valueOf(100));
        when(executorService.submit(any(Callable.class)))
            .thenAnswer(invocation -> {
                Callable<Payment> callable = invocation.getArgument(0);
                return CompletableFuture.completedFuture(callable.call());
            });
        
        // When
        CompletableFuture<Payment> future = paymentService.processPaymentAsync(request);
        
        // Then: Wait for completion
        Payment payment = future.get(5, TimeUnit.SECONDS);
        assertNotNull(payment);
        assertEquals(123L, payment.getUserId());
    }
    
    @Test
    void testProcessPaymentAsync_HandlesException() {
        // Given
        PaymentRequest request = new PaymentRequest(123L, BigDecimal.valueOf(100));
        when(executorService.submit(any(Callable.class)))
            .thenReturn(CompletableFuture.failedFuture(new RuntimeException("Database error")));
        
        // When
        CompletableFuture<Payment> future = paymentService.processPaymentAsync(request);
        
        // Then: Verify exception handling
        assertThrows(ExecutionException.class, () -> future.get());
    }
}
```

**Testing Reactive Streams (WebFlux):**
```java
@RestController
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    
    @GetMapping("/api/payments")
    public Flux<Payment> getPayments() {
        return paymentService.findAllPayments();
    }
}

@WebFluxTest(PaymentController.class)
class PaymentControllerWebFluxTest {
    @MockBean
    private PaymentService paymentService;
    
    @Autowired
    private WebTestClient webTestClient;
    
    @Test
    void testGetPayments_Reactive() {
        // Given
        Payment payment1 = new Payment(123L, BigDecimal.valueOf(100));
        Payment payment2 = new Payment(456L, BigDecimal.valueOf(200));
        when(paymentService.findAllPayments())
            .thenReturn(Flux.just(payment1, payment2));
        
        // When & Then
        webTestClient.get()
            .uri("/api/payments")
            .exchange()
            .expectStatus().isOk()
            .expectBodyList(Payment.class)
            .hasSize(2)
            .contains(payment1, payment2);
    }
    
    @Test
    void testGetPayments_ErrorHandling() {
        // Given
        when(paymentService.findAllPayments())
            .thenReturn(Flux.error(new RuntimeException("Database error")));
        
        // When & Then
        webTestClient.get()
            .uri("/api/payments")
            .exchange()
            .expectStatus().is5xxServerError();
    }
}
```

---

### Testing Edge Cases & Corner Scenarios

**Question:** What are the advanced testing scenarios you've encountered?

**Answer:**
I've seen everything. Here are the tricky ones:

**Testing Private Methods (Don't Do This, But Here's How):**
```java
// BAD: Testing private methods directly
// GOOD: Test through public methods
// But if you must (reflection):

@Test
void testPrivateMethod_UsingReflection() throws Exception {
    PaymentService service = new PaymentService();
    Method method = PaymentService.class.getDeclaredMethod("calculateFee", BigDecimal.class);
    method.setAccessible(true);
    
    BigDecimal fee = (BigDecimal) method.invoke(service, BigDecimal.valueOf(100));
    assertEquals(BigDecimal.valueOf(3), fee);
}
```

**Testing Exception Scenarios:**
```java
@Test
void testProcessPayment_ThrowsException_WhenInvalid() {
    // Given
    PaymentRequest request = new PaymentRequest(null, BigDecimal.valueOf(-100));
    
    // When & Then
    assertThrows(ValidationException.class, () -> {
        paymentService.processPayment(request);
    });
    
    // Verify exception message
    ValidationException exception = assertThrows(ValidationException.class, () -> {
        paymentService.processPayment(request);
    });
    assertEquals("Invalid payment request", exception.getMessage());
}

// Testing exception with verify
@Test
void testProcessPayment_LogsError_WhenException() {
    // Given
    PaymentRequest request = new PaymentRequest(123L, BigDecimal.valueOf(100));
    when(paymentRepository.save(any())).thenThrow(new RuntimeException("DB error"));
    
    // When & Then
    assertThrows(RuntimeException.class, () -> {
        paymentService.processPayment(request);
    });
    
    // Verify error was logged
    verify(logger).error(contains("Failed to process payment"), any(Exception.class));
}
```

**Testing Time-Dependent Code:**
```java
// Service with time-dependent logic
@Service
public class PaymentService {
    public boolean isPaymentExpired(Payment payment) {
        return payment.getCreatedAt().plusHours(24).isBefore(LocalDateTime.now());
    }
}

// Using Clock for time-dependent tests
@Test
void testIsPaymentExpired_WithFixedClock() {
    // Given
    LocalDateTime fixedTime = LocalDateTime.of(2024, 1, 1, 12, 0);
    Clock fixedClock = Clock.fixed(fixedTime.toInstant(ZoneOffset.UTC), ZoneOffset.UTC);
    
    Payment payment = new Payment(123L, BigDecimal.valueOf(100));
    payment.setCreatedAt(fixedTime.minusHours(25));  // 25 hours ago
    
    PaymentService service = new PaymentService(fixedClock);
    
    // When
    boolean expired = service.isPaymentExpired(payment);
    
    // Then
    assertTrue(expired);
}
```

**Testing Concurrent Operations:**
```java
@Test
void testProcessPayment_ConcurrentAccess() throws Exception {
    // Given
    PaymentRequest request = new PaymentRequest(123L, BigDecimal.valueOf(100));
    int threadCount = 10;
    ExecutorService executor = Executors.newFixedThreadPool(threadCount);
    CountDownLatch latch = new CountDownLatch(threadCount);
    List<Payment> results = Collections.synchronizedList(new ArrayList<>());
    
    // When: Process concurrently
    for (int i = 0; i < threadCount; i++) {
        executor.submit(() -> {
            try {
                Payment payment = paymentService.processPayment(request);
                results.add(payment);
            } finally {
                latch.countDown();
            }
        });
    }
    
    latch.await(5, TimeUnit.SECONDS);
    
    // Then: Verify all completed
    assertEquals(threadCount, results.size());
    
    // Verify idempotency (if applicable)
    Set<Long> paymentIds = results.stream()
        .map(Payment::getId)
        .collect(Collectors.toSet());
    // If idempotent, should have fewer unique IDs
}
```

---

### Mocking External Dependencies & HTTP Clients

**Question:** How do you test services that call external APIs or HTTP clients?

**Answer:**
This is critical for microservices. Here's my approach:

**Mocking RestTemplate:**
```java
@Service
public class FraudServiceClient {
    @Autowired
    private RestTemplate restTemplate;
    
    public FraudResult checkFraud(PaymentRequest request) {
        ResponseEntity<FraudResult> response = restTemplate.postForEntity(
            "http://fraud-service/check", request, FraudResult.class);
        return response.getBody();
    }
}

@ExtendWith(MockitoExtension.class)
class FraudServiceClientTest {
    @Mock
    private RestTemplate restTemplate;
    
    @InjectMocks
    private FraudServiceClient fraudServiceClient;
    
    @Test
    void testCheckFraud_Success() {
        // Given
        PaymentRequest request = new PaymentRequest(123L, BigDecimal.valueOf(100));
        FraudResult expectedResult = new FraudResult(false, 10);
        
        when(restTemplate.postForEntity(
            eq("http://fraud-service/check"),
            eq(request),
            eq(FraudResult.class)))
            .thenReturn(ResponseEntity.ok(expectedResult));
        
        // When
        FraudResult result = fraudServiceClient.checkFraud(request);
        
        // Then
        assertFalse(result.isFraudulent());
        assertEquals(10, result.getRiskScore());
    }
    
    @Test
    void testCheckFraud_HttpError() {
        // Given
        PaymentRequest request = new PaymentRequest(123L, BigDecimal.valueOf(100));
        
        when(restTemplate.postForEntity(anyString(), any(), any(Class.class)))
            .thenThrow(new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR));
        
        // When & Then
        assertThrows(HttpClientErrorException.class, () -> {
            fraudServiceClient.checkFraud(request);
        });
    }
}
```

**Using MockWebServer (Better for HTTP Testing):**
```java
// More realistic HTTP testing
@ExtendWith(MockitoExtension.class)
class FraudServiceClientMockWebServerTest {
    private MockWebServer mockWebServer;
    private FraudServiceClient fraudServiceClient;
    
    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        
        RestTemplate restTemplate = new RestTemplate();
        fraudServiceClient = new FraudServiceClient(restTemplate);
        // Set base URL to mock server
        fraudServiceClient.setBaseUrl(mockWebServer.url("/").toString());
    }
    
    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }
    
    @Test
    void testCheckFraud_MockWebServer() throws InterruptedException {
        // Given
        PaymentRequest request = new PaymentRequest(123L, BigDecimal.valueOf(100));
        FraudResult expectedResult = new FraudResult(false, 10);
        
        // Mock HTTP response
        mockWebServer.enqueue(new MockResponse()
            .setResponseCode(200)
            .setHeader("Content-Type", "application/json")
            .setBody("{\"fraudulent\":false,\"riskScore\":10}"));
        
        // When
        FraudResult result = fraudServiceClient.checkFraud(request);
        
        // Then
        assertFalse(result.isFraudulent());
        assertEquals(10, result.getRiskScore());
        
        // Verify request was made correctly
        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("POST", recordedRequest.getMethod());
        assertEquals("/check", recordedRequest.getPath());
    }
}
```

**Mocking Feign Clients:**
```java
// Feign client
@FeignClient(name = "fraud-service", url = "${fraud.service.url}")
public interface FraudServiceFeignClient {
    @PostMapping("/check")
    FraudResult checkFraud(@RequestBody PaymentRequest request);
}

@Service
public class PaymentService {
    @Autowired
    private FraudServiceFeignClient fraudClient;
    
    public Payment processPayment(PaymentRequest request) {
        FraudResult fraud = fraudClient.checkFraud(request);
        if (fraud.isFraudulent()) {
            throw new FraudException("Payment flagged as fraudulent");
        }
        return createPayment(request);
    }
}

@SpringBootTest
@AutoConfigureMockMvc
class PaymentServiceFeignTest {
    @MockBean
    private FraudServiceFeignClient fraudClient;  // Mock Feign client
    
    @Autowired
    private PaymentService paymentService;
    
    @Test
    void testProcessPayment_WithFeignMock() {
        // Given
        PaymentRequest request = new PaymentRequest(123L, BigDecimal.valueOf(100));
        FraudResult fraudResult = new FraudResult(false, 10);
        
        when(fraudClient.checkFraud(request)).thenReturn(fraudResult);
        
        // When
        Payment payment = paymentService.processPayment(request);
        
        // Then
        assertNotNull(payment);
        verify(fraudClient).checkFraud(request);
    }
}
```

**Testing Retry Logic:**
```java
@Service
public class PaymentService {
    @Autowired
    private FraudServiceClient fraudClient;
    
    @Retryable(value = {HttpClientErrorException.class}, maxAttempts = 3)
    public FraudResult checkFraudWithRetry(PaymentRequest request) {
        return fraudClient.checkFraud(request);
    }
}

@ExtendWith(MockitoExtension.class)
class PaymentServiceRetryTest {
    @Mock
    private FraudServiceClient fraudClient;
    
    @InjectMocks
    private PaymentService paymentService;
    
    @Test
    void testCheckFraudWithRetry_RetriesOnFailure() {
        // Given
        PaymentRequest request = new PaymentRequest(123L, BigDecimal.valueOf(100));
        
        // Fail twice, then succeed
        when(fraudClient.checkFraud(request))
            .thenThrow(new HttpClientErrorException(HttpStatus.SERVICE_UNAVAILABLE))
            .thenThrow(new HttpClientErrorException(HttpStatus.SERVICE_UNAVAILABLE))
            .thenReturn(new FraudResult(false, 10));
        
        // When
        FraudResult result = paymentService.checkFraudWithRetry(request);
        
        // Then: Verify retried 3 times
        verify(fraudClient, times(3)).checkFraud(request);
        assertNotNull(result);
    }
    
    @Test
    void testCheckFraudWithRetry_FailsAfterMaxRetries() {
        // Given
        PaymentRequest request = new PaymentRequest(123L, BigDecimal.valueOf(100));
        
        when(fraudClient.checkFraud(request))
            .thenThrow(new HttpClientErrorException(HttpStatus.SERVICE_UNAVAILABLE));
        
        // When & Then: Should fail after 3 attempts
        assertThrows(HttpClientErrorException.class, () -> {
            paymentService.checkFraudWithRetry(request);
        });
        
        verify(fraudClient, times(3)).checkFraud(request);
    }
}
```

---

### Database Testing: Testcontainers vs. H2 vs. Mocking

**Question:** When do you use Testcontainers vs. H2 vs. mocking for database tests?

**Answer:**
I've used all three. Here's when to use each:

**H2 (In-Memory, Fast):**
```java
// Use for: Simple CRUD, fast feedback
// application-test.yml
spring:
  datasource:
    url: jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;MODE=PostgreSQL
    driver-class-name: org.h2.Driver
  jpa:
    hibernate:
      ddl-auto: create-drop

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class PaymentRepositoryH2Test {
    @Autowired
    private PaymentRepository paymentRepository;
    
    @Test
    void testSavePayment_H2() {
        // Fast: In-memory, no Docker needed
        Payment payment = new Payment(123L, BigDecimal.valueOf(100));
        Payment saved = paymentRepository.save(payment);
        assertNotNull(saved.getId());
    }
}

// Limitations:
// - Doesn't support all PostgreSQL features
// - Different SQL dialect
// - Can't test PostgreSQL-specific queries
```

**Testcontainers (Real Database, Slower):**
```java
// Use for: Complex queries, PostgreSQL-specific features, production-like testing
@Testcontainers
@SpringBootTest
class PaymentRepositoryTestcontainersTest {
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test")
            .withReuse(true);  // Reuse container across tests (faster)
    
    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }
    
    @Autowired
    private PaymentRepository paymentRepository;
    
    @Test
    void testComplexQuery_Testcontainers() {
        // Can test PostgreSQL-specific features
        Payment payment = new Payment(123L, BigDecimal.valueOf(100));
        paymentRepository.save(payment);
        
        // Test PostgreSQL-specific query
        List<Payment> payments = paymentRepository.findByUserIdUsingJsonb(123L);
        assertEquals(1, payments.size());
    }
    
    @Test
    void testTransactionIsolation_Testcontainers() {
        // Test transaction isolation levels
        // Requires real database
    }
}

// Benefits:
// - Real PostgreSQL, production-like
// - Test complex queries, stored procedures
// - Test database-specific features

// Drawbacks:
// - Slower (Docker startup)
// - Requires Docker
// - More resource-intensive
```

**Mocking Repository (Fastest, Most Limited):**
```java
// Use for: Testing service logic, not database interactions
@ExtendWith(MockitoExtension.class)
class PaymentServiceMockTest {
    @Mock
    private PaymentRepository paymentRepository;
    
    @InjectMocks
    private PaymentService paymentService;
    
    @Test
    void testProcessPayment_MockRepository() {
        // Fast: No database at all
        PaymentRequest request = new PaymentRequest(123L, BigDecimal.valueOf(100));
        Payment savedPayment = new Payment(123L, BigDecimal.valueOf(100));
        savedPayment.setId(1L);
        
        when(paymentRepository.save(any(Payment.class))).thenReturn(savedPayment);
        
        Payment result = paymentService.processPayment(request);
        
        assertNotNull(result);
        verify(paymentRepository).save(any(Payment.class));
    }
}

// Use when:
// - Testing business logic, not database
// - Database is slow/unavailable
// - Unit tests (fast feedback)

// Don't use when:
// - Testing database queries
// - Testing transactions
// - Testing database constraints
```

**Real Example: Balanced Testing Strategy**
```java
// Unit Tests (70%): Mock repository
@ExtendWith(MockitoExtension.class)
class PaymentServiceUnitTest {
    @Mock
    private PaymentRepository paymentRepository;
    // Test business logic only
}

// Integration Tests (20%): H2 or Testcontainers
@SpringBootTest
@Transactional
class PaymentServiceIntegrationTest {
    @Autowired
    private PaymentRepository paymentRepository;
    // Test database interactions
}

// E2E Tests (10%): Testcontainers + Full Stack
@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class PaymentE2ETest {
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15");
    // Test full flow
}
```

**Real War Story:**
We had 1000 unit tests with mocked repositories. All passed. Production failed because:
- Database constraints weren't tested
- Complex queries weren't tested
- Transaction behavior wasn't tested

Solution: Added integration tests with Testcontainers. Caught 50+ bugs before production.

---

### Testing Kafka & Event-Driven Systems

**Question:** How do you test Kafka producers and consumers?

**Answer:**
Event-driven testing is different. Here's how I do it:

**Testing Kafka Producers:**
```java
@Service
public class PaymentService {
    @Autowired
    private KafkaTemplate<String, PaymentEvent> kafkaTemplate;
    
    public void processPayment(PaymentRequest request) {
        Payment payment = createPayment(request);
        
        PaymentEvent event = new PaymentEvent(
            payment.getId(), payment.getUserId(), payment.getAmount());
        kafkaTemplate.send("payment-events", event);
    }
}

@ExtendWith(MockitoExtension.class)
class PaymentServiceKafkaTest {
    @Mock
    private KafkaTemplate<String, PaymentEvent> kafkaTemplate;
    
    @InjectMocks
    private PaymentService paymentService;
    
    @Test
    void testProcessPayment_PublishesEvent() {
        // Given
        PaymentRequest request = new PaymentRequest(123L, BigDecimal.valueOf(100));
        
        // When
        paymentService.processPayment(request);
        
        // Then: Verify event was sent
        ArgumentCaptor<PaymentEvent> eventCaptor = ArgumentCaptor.forClass(PaymentEvent.class);
        verify(kafkaTemplate).send(eq("payment-events"), eventCaptor.capture());
        
        PaymentEvent event = eventCaptor.getValue();
        assertEquals(123L, event.getUserId());
        assertEquals(BigDecimal.valueOf(100), event.getAmount());
    }
}
```

**Testing Kafka Consumers:**
```java
@Component
public class PaymentEventListener {
    @Autowired
    private PaymentService paymentService;
    
    @KafkaListener(topics = "payment-events")
    public void handlePaymentEvent(PaymentEvent event) {
        paymentService.processPaymentEvent(event);
    }
}

@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = {"payment-events"})
class PaymentEventListenerTest {
    @Autowired
    private EmbeddedKafkaBroker embeddedKafka;
    
    @Autowired
    private PaymentEventListener listener;
    
    @MockBean
    private PaymentService paymentService;
    
    @Test
    void testHandlePaymentEvent_ConsumesMessage() throws Exception {
        // Given
        PaymentEvent event = new PaymentEvent(1L, 123L, BigDecimal.valueOf(100));
        
        // When: Send message to Kafka
        embeddedKafka.send("payment-events", event);
        
        // Then: Wait for consumer to process
        await().atMost(5, TimeUnit.SECONDS).untilAsserted(() -> {
            verify(paymentService).processPaymentEvent(event);
        });
    }
}
```

**Testing with EmbeddedKafka (Full Integration):**
```java
@SpringBootTest
@EmbeddedKafka(
    partitions = 1,
    topics = {"payment-events", "fraud-events"},
    brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"}
)
class PaymentEventFlowTest {
    @Autowired
    private EmbeddedKafkaBroker embeddedKafka;
    
    @Autowired
    private KafkaTemplate<String, PaymentEvent> kafkaTemplate;
    
    @Autowired
    private PaymentEventListener paymentListener;
    
    @Test
    void testPaymentEventFlow_EndToEnd() throws Exception {
        // Given
        PaymentEvent event = new PaymentEvent(1L, 123L, BigDecimal.valueOf(100));
        
        // When: Publish event
        kafkaTemplate.send("payment-events", event);
        
        // Then: Verify consumer processed it
        await().atMost(5, TimeUnit.SECONDS).untilAsserted(() -> {
            // Verify downstream effects
            // (e.g., database updated, other events published)
        });
    }
}
```

---

1. **Lead with Business Impact**: Always connect engineering work to business outcomes
2. **Show, Don't Tell**: Use real examples and war stories
3. **Be Honest**: Acknowledge failures and learnings
4. **Think Strategically**: Balance short-term and long-term
5. **Communicate Clearly**: Explain technical concepts in business terms
6. **Focus on People**: Teams are your most important asset
7. **Operational Excellence**: Reliability, monitoring, incident management
8. **Continuous Improvement**: Learn from failures, iterate on processes

---

**Good luck with your interview! Remember: They're not just hiring a technologist - they're hiring a leader who can drive engineering excellence while delivering business value.**

