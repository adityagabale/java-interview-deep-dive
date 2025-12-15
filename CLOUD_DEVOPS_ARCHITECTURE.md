# Cloud and DevOps Architecture - Deep Dive Interview Guide
## IC 16+ Level Technical Interview

This guide covers Kubernetes, Docker, Chef, Azure, AWS with real-world scenarios, troubleshooting, and architectural depth.

---

## 1. Kubernetes Architecture & Implementation

### Q1.1: Setting Up Production-Grade Kubernetes Cluster
**Question:** Walk me through how you would set up a production-grade Kubernetes cluster from scratch. What considerations would you have for a multi-tenant environment supporting 100+ microservices?

**Answer:**
**Infrastructure Setup:**
- **Control Plane HA**: Minimum 3 master nodes across availability zones
- **etcd**: Separate etcd cluster (3-5 nodes) with regular snapshots to S3/Azure Blob
- **Worker Nodes**: Auto-scaling groups based on resource metrics
- **Network Plugin**: Calico or Cilium for network policies and security

**Real Project Example:**
```yaml
# Production cluster setup using kubeadm
apiVersion: kubeadm.k8s.io/v1beta3
kind: ClusterConfiguration
metadata:
  name: prod-cluster
networking:
  podSubnet: 10.244.0.0/16
  serviceSubnet: 10.96.0.0/12
controlPlaneEndpoint: "k8s-api-lb.example.com:6443"
etcd:
  external:
    endpoints:
    - https://10.0.1.10:2379
    - https://10.0.1.11:2379
    - https://10.0.1.12:2379
apiServer:
  extraArgs:
    enable-admission-plugins: NodeRestriction,PodSecurityPolicy,ResourceQuota
    audit-log-path: /var/log/kube-apiserver-audit.log
    audit-log-maxage: "30"
```

**Multi-Tenancy Considerations:**
1. **Namespace Isolation**: Separate namespaces per team/service
2. **Resource Quotas**: CPU/Memory limits per namespace
3. **Network Policies**: Strict ingress/egress rules
4. **RBAC**: Role-based access with service accounts
5. **Pod Security Policies**: Enforce security standards

**Follow-up Depth 1:** How do you handle cluster upgrades without downtime?
- **Answer**: Blue-green cluster strategy or rolling node upgrades
  - Drain nodes gracefully (`kubectl drain --ignore-daemonsets`)
  - Upgrade kubeadm/kubelet version one node at a time
  - Use PodDisruptionBudgets to ensure service availability
  - Test in staging environment first with same workload patterns

**Follow-up Depth 2:** What's your disaster recovery strategy for etcd?
- **Answer**: Automated hourly snapshots with 7-day retention
  ```bash
  ETCDCTL_API=3 etcdctl snapshot save /backup/etcd-$(date +%Y%m%d-%H%M%S).db \
    --endpoints=https://127.0.0.1:2379 \
    --cacert=/etc/kubernetes/pki/etcd/ca.crt \
    --cert=/etc/kubernetes/pki/etcd/server.crt \
    --key=/etc/kubernetes/pki/etcd/server.key
  ```
  - Store in multiple regions (S3 cross-region replication)
  - Regular restore drills to validate recovery procedures
  - RPO: 1 hour, RTO: 30 minutes

**Follow-up Depth 3:** How do you monitor cluster health and performance at scale?
- **Answer**: Multi-layered monitoring approach
  - **Prometheus + Grafana**: Metrics collection and visualization
  - **AlertManager**: Critical alerts (API server latency, node failures)
  - **Kube-state-metrics**: Cluster state monitoring
  - **Node-exporter**: Node-level metrics
  - Custom ServiceMonitors for application metrics
  - ELK/EFK stack for log aggregation
  - Distributed tracing with Jaeger for request flows

---

### Q1.2: Troubleshooting Pod Failures
**Question:** A critical production pod keeps crashing with OOMKilled status. Walk me through your investigation and resolution process.

**Answer:**
**Investigation Steps:**
1. **Check Pod Status**:
   ```bash
   kubectl describe pod <pod-name> -n <namespace>
   kubectl get pod <pod-name> -n <namespace> -o yaml
   ```
   Look for `lastState.terminated.reason: OOMKilled`

2. **Analyze Resource Limits**:
   ```yaml
   resources:
     requests:
       memory: "256Mi"
       cpu: "100m"
     limits:
       memory: "512Mi"  # Pod killed at this threshold
       cpu: "500m"
   ```

3. **Check Memory Usage Patterns**:
   ```bash
   kubectl top pod <pod-name> -n <namespace>
   # Historical metrics from Prometheus
   container_memory_usage_bytes{pod="<pod-name>"}
   ```

**Real Project Example:**
We had a Java microservice experiencing OOMKilled errors during peak traffic (Black Friday). Investigation revealed:
- **Root Cause**: Memory leak in caching layer + inadequate JVM heap settings
- **Immediate Fix**: Increased memory limits from 512Mi to 2Gi
- **Long-term Solution**:
  ```yaml
  env:
  - name: JAVA_OPTS
    value: "-Xms1g -Xmx1536m -XX:+UseG1GC -XX:MaxGCPauseMillis=200"
  resources:
    requests:
      memory: "1536Mi"
    limits:
      memory: "2048Mi"
  ```
  - Implemented cache eviction policies (LRU with max size)
  - Added heap dump on OOM: `-XX:+HeapDumpOnOutOfMemoryError`
  - Set up alerts for memory usage >80%

**Follow-up Depth 1:** What if increasing memory limits isn't an option due to cluster capacity?
- **Answer**: Optimize application memory footprint
  - Profile memory usage with tools (VisualVM, JProfiler)
  - Implement proper connection pooling (database, HTTP clients)
  - Review data structures and algorithms for efficiency
  - Consider horizontal scaling with more replica pods
  - Use HPA with memory metrics

**Follow-up Depth 2:** How do you prevent similar issues in the future?
- **Answer**: Proactive monitoring and load testing
  - Set up Prometheus alerts for memory trends
  - Regular chaos engineering exercises (kill random pods)
  - Load testing in staging with production-like data
  - Implement PodDisruptionBudgets and resource quotas
  - Code review checklist for memory management

---

### Q1.3: Kubernetes Networking Deep Dive
**Question:** Explain how packet flows from an external client to a pod in your cluster. What happens when you have multiple replicas behind a Service?

**Answer:**
**Traffic Flow Anatomy:**
```
External Client → LoadBalancer → Ingress Controller → Service → Pod
```

**Detailed Flow:**
1. **Ingress Layer**:
   ```yaml
   apiVersion: networking.k8s.io/v1
   kind: Ingress
   metadata:
     name: api-ingress
     annotations:
       nginx.ingress.kubernetes.io/ssl-redirect: "true"
   spec:
     rules:
     - host: api.example.com
       http:
         paths:
         - path: /
           pathType: Prefix
           backend:
             service:
               name: api-service
               port:
                 number: 80
   ```

2. **Service Layer (ClusterIP)**:
   ```yaml
   apiVersion: v1
   kind: Service
   metadata:
     name: api-service
   spec:
     selector:
       app: api
     ports:
     - protocol: TCP
       port: 80
       targetPort: 8080
     type: ClusterIP
   ```

3. **Kube-proxy & iptables**:
   - Kube-proxy maintains iptables/IPVS rules
   - Service IP is virtual (not assigned to any interface)
   - Traffic is load-balanced via DNAT rules to pod IPs
   - Default algorithm: Round-robin (IPVS) or random (iptables)

**Real Project Example:**
We implemented session affinity for a stateful application:
```yaml
spec:
  sessionAffinity: ClientIP
  sessionAffinityConfig:
    clientIP:
      timeoutSeconds: 10800  # 3 hours
```

**Network Policies for Security:**
```yaml
apiVersion: networking.k8s.io/v1
kind: NetworkPolicy
metadata:
  name: api-network-policy
spec:
  podSelector:
    matchLabels:
      app: api
  policyTypes:
  - Ingress
  - Egress
  ingress:
  - from:
    - podSelector:
        matchLabels:
          app: frontend
    ports:
    - protocol: TCP
      port: 8080
  egress:
  - to:
    - podSelector:
        matchLabels:
          app: database
    ports:
    - protocol: TCP
      port: 5432
```

**Follow-up Depth 1:** How do you troubleshoot connectivity issues between pods?
- **Answer**: Systematic debugging approach
  ```bash
  # Test DNS resolution
  kubectl exec -it <pod> -- nslookup api-service.default.svc.cluster.local
  
  # Test network connectivity
  kubectl exec -it <pod> -- curl http://api-service:80
  
  # Check network policies
  kubectl get networkpolicies -A
  kubectl describe networkpolicy <policy-name>
  
  # Inspect iptables rules
  iptables -t nat -L -n | grep api-service
  
  # Check CNI plugin logs
  kubectl logs -n kube-system <calico-node-pod>
  ```

**Follow-up Depth 2:** What's your approach for east-west traffic encryption?
- **Answer**: Service mesh implementation (Istio/Linkerd)
  - mTLS between all services automatically
  - No application code changes required
  - Certificate rotation handled by mesh
  - Example Istio configuration:
  ```yaml
  apiVersion: security.istio.io/v1beta1
  kind: PeerAuthentication
  metadata:
    name: default
    namespace: production
  spec:
    mtls:
      mode: STRICT
  ```

---

## 2. Docker Best Practices & Optimization

### Q2.1: Docker Image Optimization
**Question:** How do you optimize Docker images for production? What's your approach to minimize image size and build time?

**Answer:**
**Multi-stage Builds (Best Practice):**
```dockerfile
# Stage 1: Build
FROM maven:3.8-openjdk-17 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/application.jar app.jar
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

**Optimization Techniques:**
1. **Layer Caching**: Order COPY commands strategically
2. **Minimal Base Images**: Use Alpine or distroless
3. **Remove Build Dependencies**: Multi-stage builds
4. **Single RUN Command**: Reduce layers
   ```dockerfile
   RUN apt-get update && \
       apt-get install -y package1 package2 && \
       apt-get clean && \
       rm -rf /var/lib/apt/lists/*
   ```

**Real Project Example:**
Optimized a Node.js application image from 1.2GB to 180MB:
```dockerfile
# Before: 1.2GB
FROM node:16
COPY . /app
RUN npm install
CMD ["node", "server.js"]

# After: 180MB
FROM node:16-alpine AS builder
WORKDIR /app
COPY package*.json ./
RUN npm ci --only=production

FROM node:16-alpine
WORKDIR /app
COPY --from=builder /app/node_modules ./node_modules
COPY . .
USER node
CMD ["node", "server.js"]
```

**Follow-up Depth 1:** How do you handle secrets in Docker images?
- **Answer**: Never bake secrets into images
  - Use Docker secrets (Swarm) or Kubernetes secrets
  - Mount secrets as volumes at runtime
  - Use external secret managers (AWS Secrets Manager, HashiCorp Vault)
  ```bash
  docker run -e DB_PASSWORD_FILE=/run/secrets/db_password \
    --secret db_password myapp
  ```

**Follow-up Depth 2:** What's your Docker registry strategy for large teams?
- **Answer**: Private registry with access controls
  - AWS ECR / Azure ACR with IAM policies
  - Image scanning for vulnerabilities (Trivy, Clair)
  - Tag immutability for production images
  - Lifecycle policies to delete old images
  - Example ECR policy:
  ```json
  {
    "rules": [{
      "rulePriority": 1,
      "description": "Keep last 10 images",
      "selection": {
        "tagStatus": "any",
        "countType": "imageCountMoreThan",
        "countNumber": 10
      },
      "action": {"type": "expire"}
    }]
  }
  ```

---

### Q2.2: Docker Container Troubleshooting
**Question:** A container is running but not responding to requests. How do you debug this?

**Answer:**
**Debugging Workflow:**
1. **Check Container Status**:
   ```bash
   docker ps -a
   docker logs <container-id> --tail 100 -f
   docker inspect <container-id>
   ```

2. **Exec into Container**:
   ```bash
   docker exec -it <container-id> /bin/sh
   # Check if process is running
   ps aux | grep java
   # Check network bindings
   netstat -tlnp
   # Test internally
   curl http://localhost:8080/health
   ```

3. **Network Inspection**:
   ```bash
   docker network inspect <network-name>
   # Check port mappings
   docker port <container-id>
   ```

4. **Resource Constraints**:
   ```bash
   docker stats <container-id>
   # Check for CPU/memory throttling
   docker inspect <container-id> | grep -A 10 "Memory"
   ```

**Real Project Example:**
Production issue where containers became unresponsive during high load:
- **Symptoms**: 503 errors, container running but health checks failing
- **Investigation**: `docker stats` showed CPU at 100%, memory normal
- **Root Cause**: Single-threaded event loop blocking on CPU-intensive operation
- **Solution**: 
  - Implemented worker threads for CPU-intensive tasks
  - Added resource limits:
  ```yaml
  deploy:
    resources:
      limits:
        cpus: '2.0'
        memory: 2G
      reservations:
        cpus: '1.0'
        memory: 1G
  ```
  - Implemented proper health checks:
  ```dockerfile
  HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
    CMD curl -f http://localhost:8080/health || exit 1
  ```

**Follow-up Depth 1:** How do you debug networking issues between containers?
- **Answer**: Use Docker network tools
  ```bash
  # Create debug container in same network
  docker run -it --network=container:<target-id> nicolaka/netshoot
  # Inside netshoot
  curl http://localhost:8080
  tcpdump -i any port 8080
  ```

---

## 3. AWS Architecture & Services

### Q3.1: Designing Highly Available Architecture
**Question:** Design a highly available, auto-scaling web application architecture on AWS. Consider multi-region deployment and disaster recovery.

**Answer:**
**Architecture Components:**
```
Route53 (Global DNS) 
  ↓
CloudFront (CDN)
  ↓
ALB (Multi-AZ)
  ↓
ECS/EKS Cluster (Auto-scaling)
  ↓
RDS (Multi-AZ with Read Replicas)
Aurora Global Database (Multi-Region)
ElastiCache (Redis Cluster Mode)
```

**Infrastructure as Code (Terraform)**:
```hcl
# Multi-AZ Auto Scaling Group
resource "aws_autoscaling_group" "app" {
  name                = "app-asg"
  vpc_zone_identifier = aws_subnet.private[*].id
  min_size            = 3
  max_size            = 20
  desired_capacity    = 6
  health_check_type   = "ELB"
  health_check_grace_period = 300

  launch_template {
    id      = aws_launch_template.app.id
    version = "$Latest"
  }

  tag {
    key                 = "Environment"
    value               = "production"
    propagate_at_launch = true
  }
}

# Application Load Balancer
resource "aws_lb" "main" {
  name               = "app-alb"
  internal           = false
  load_balancer_type = "application"
  security_groups    = [aws_security_group.alb.id]
  subnets            = aws_subnet.public[*].id

  enable_deletion_protection = true
  enable_http2              = true
  enable_cross_zone_load_balancing = true
}

# RDS Multi-AZ
resource "aws_db_instance" "primary" {
  identifier             = "app-db-primary"
  engine                = "postgres"
  engine_version        = "14.7"
  instance_class        = "db.r6g.2xlarge"
  allocated_storage     = 1000
  storage_type          = "io1"
  iops                  = 10000
  multi_az              = true
  db_subnet_group_name  = aws_db_subnet_group.main.name
  backup_retention_period = 30
  enabled_cloudwatch_logs_exports = ["postgresql"]
  
  performance_insights_enabled = true
  performance_insights_retention_period = 7
}
```

**Real Project Example:**
Designed multi-region architecture for fintech application requiring 99.99% uptime:

**Primary Region (us-east-1):**
- 3 AZs with EKS clusters
- Aurora PostgreSQL Multi-AZ (primary)
- ElastiCache Redis cluster mode
- S3 with cross-region replication

**Secondary Region (us-west-2):**
- Warm standby EKS cluster
- Aurora Global Database (replica)
- Read-only ElastiCache
- S3 replica bucket

**Disaster Recovery Strategy:**
- **RPO**: 1 minute (Aurora Global Database lag)
- **RTO**: 5 minutes (automated failover)
- Route53 health checks with automatic DNS failover
- Database promotion automated via Lambda

**Follow-up Depth 1:** How do you handle database failover and data consistency?
- **Answer**: Aurora Global Database with automated promotion
  ```bash
  # Automated failover process
  1. Route53 health check detects primary region failure
  2. Lambda triggers Aurora cluster promotion in secondary region
  3. Update application config to point to new primary
  4. DNS TTL set to 60s for quick propagation
  ```
  - Connection string uses DNS CNAME pointing to writer endpoint
  - Application implements retry logic with exponential backoff
  - Read replicas promoted only after write operations stopped

**Follow-up Depth 2:** What's your cost optimization strategy for this architecture?
- **Answer**: Multi-pronged approach
  - **Compute**: 
    - Reserved Instances for baseline capacity (40% savings)
    - Spot Instances for non-critical workloads (70% savings)
    - Right-sizing based on CloudWatch metrics
  - **Storage**:
    - S3 Intelligent-Tiering for older data
    - EBS gp3 instead of io1 where possible
    - Lifecycle policies to delete old snapshots
  - **Database**:
    - Aurora Serverless v2 for dev/staging
    - Scheduled scaling down of non-prod environments
  - **Monitoring**: Cost anomaly detection with AWS Budgets

---

### Q3.2: AWS Networking & Security
**Question:** Explain VPC design for a microservices architecture. How do you implement network segmentation and security?

**Answer:**
**VPC Architecture:**
```
VPC (10.0.0.0/16)
├── Public Subnets (10.0.1.0/24, 10.0.2.0/24, 10.0.3.0/24)
│   ├── NAT Gateways (one per AZ)
│   ├── Application Load Balancers
│   └── Bastion Hosts (in ASG)
├── Private Subnets - App (10.0.10.0/24, 10.0.11.0/24, 10.0.12.0/24)
│   ├── EKS Worker Nodes
│   └── Application Containers
└── Private Subnets - Data (10.0.20.0/24, 10.0.21.0/24, 10.0.22.0/24)
    ├── RDS Instances
    ├── ElastiCache Clusters
    └── OpenSearch Domains
```

**Security Groups (Layered Defense):**
```hcl
# ALB Security Group
resource "aws_security_group" "alb" {
  name        = "alb-sg"
  description = "Allow HTTPS from internet"
  
  ingress {
    from_port   = 443
    to_port     = 443
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }
  
  egress {
    from_port       = 8080
    to_port         = 8080
    protocol        = "tcp"
    security_groups = [aws_security_group.app.id]
  }
}

# Application Security Group
resource "aws_security_group" "app" {
  name        = "app-sg"
  description = "Allow traffic from ALB"
  
  ingress {
    from_port       = 8080
    to_port         = 8080
    protocol        = "tcp"
    security_groups = [aws_security_group.alb.id]
  }
  
  egress {
    from_port       = 5432
    to_port         = 5432
    protocol        = "tcp"
    security_groups = [aws_security_group.rds.id]
  }
}

# Database Security Group
resource "aws_security_group" "rds" {
  name        = "rds-sg"
  description = "Allow PostgreSQL from app"
  
  ingress {
    from_port       = 5432
    to_port         = 5432
    protocol        = "tcp"
    security_groups = [aws_security_group.app.id]
  }
}
```

**Network ACLs (Additional Layer)**:
```hcl
resource "aws_network_acl" "private_app" {
  vpc_id     = aws_vpc.main.id
  subnet_ids = aws_subnet.private_app[*].id

  ingress {
    protocol   = "tcp"
    rule_no    = 100
    action     = "allow"
    cidr_block = "10.0.0.0/16"  # VPC CIDR only
    from_port  = 1024
    to_port    = 65535
  }

  egress {
    protocol   = "-1"
    rule_no    = 100
    action     = "allow"
    cidr_block = "0.0.0.0/0"
  }
}
```

**Real Project Example:**
Implemented zero-trust network architecture for healthcare application:
- **VPC Endpoints**: Private connectivity to S3, DynamoDB, ECR (no internet)
- **Transit Gateway**: Connected multiple VPCs (dev, staging, prod) with route tables
- **AWS PrivateLink**: Exposed internal APIs to partner VPCs
- **Flow Logs**: All VPC traffic logged to S3 for compliance
- **GuardDuty**: Threat detection for unusual network patterns

**Follow-up Depth 1:** How do you implement least privilege network access?
- **Answer**: Micro-segmentation with security groups
  - Each microservice has its own security group
  - Only allow specific port/protocol combinations
  - Use prefix lists for frequently used CIDRs
  - Regular audits with AWS Config rules
  - Automated remediation with Systems Manager

**Follow-up Depth 2:** How do you monitor and respond to network security events?
- **Answer**: Layered monitoring approach
  - **CloudWatch Logs Insights**: Query VPC Flow Logs for anomalies
  - **Security Hub**: Centralized security findings
  - **EventBridge**: Trigger Lambda for automated response
  - **Example rule**: Block IPs with >100 failed connection attempts
  ```python
  def lambda_handler(event, context):
      suspicious_ip = event['detail']['sourceIPAddress']
      # Add to NACL deny rule
      ec2.create_network_acl_entry(
          NetworkAclId='acl-xxx',
          RuleNumber=50,
          Protocol='-1',
          RuleAction='deny',
          CidrBlock=f'{suspicious_ip}/32'
      )
  ```

---

## 4. Azure DevOps & Infrastructure

### Q4.1: Azure Kubernetes Service (AKS) Best Practices
**Question:** What are your best practices for running production workloads on AKS? How do you handle node upgrades and maintenance?

**Answer:**
**AKS Production Setup:**
```bash
# Create AKS cluster with best practices
az aks create \
  --resource-group prod-rg \
  --name prod-aks-cluster \
  --node-count 3 \
  --node-vm-size Standard_D8s_v3 \
  --network-plugin azure \
  --network-policy calico \
  --enable-managed-identity \
  --enable-cluster-autoscaler \
  --min-count 3 \
  --max-count 20 \
  --max-pods 50 \
  --enable-addons monitoring,azure-policy \
  --zones 1 2 3 \
  --kubernetes-version 1.27.7 \
  --node-osdisk-size 128 \
  --enable-encryption-at-host
```

**Node Pools Strategy:**
```bash
# System node pool (critical add-ons)
az aks nodepool add \
  --cluster-name prod-aks-cluster \
  --name systempool \
  --node-count 3 \
  --node-vm-size Standard_D4s_v3 \
  --mode System \
  --node-taints CriticalAddonsOnly=true:NoSchedule

# User node pool (applications)
az aks nodepool add \
  --cluster-name prod-aks-cluster \
  --name userpool \
  --node-count 5 \
  --node-vm-size Standard_D8s_v3 \
  --mode User \
  --enable-cluster-autoscaler \
  --min-count 3 \
  --max-count 30
```

**Upgrade Strategy (Zero Downtime):**
1. **Test in Lower Environment**: Upgrade dev/staging first
2. **Enable Maintenance Window**:
   ```json
   {
     "timeInWeek": [{
       "day": "Sunday",
       "hourSlots": [2, 3, 4]
     }]
   }
   ```
3. **Surge Upgrade Settings**:
   ```bash
   az aks nodepool update \
     --cluster-name prod-aks-cluster \
     --name userpool \
     --max-surge 33%  # Create 1 new node per 3 existing
   ```
4. **Control Plane Upgrade**: Automatically handled by Azure
5. **Node Pool Upgrade**: Cordons, drains, deletes old nodes

**Real Project Example:**
Managed AKS cluster upgrade from 1.24 to 1.27 for e-commerce platform:
- **Preparation**: 
  - Reviewed deprecated APIs using kubectl-convert
  - Updated Helm charts for compatibility
  - Set PodDisruptionBudgets:
  ```yaml
  apiVersion: policy/v1
  kind: PodDisruptionBudget
  metadata:
    name: api-pdb
  spec:
    minAvailable: 2
    selector:
      matchLabels:
        app: api
  ```
- **Execution**: Weekend upgrade window, 4 hours
- **Rollback Plan**: Snapshot of etcd, node image rollback capability
- **Result**: Zero customer-facing downtime

**Follow-up Depth 1:** How do you manage Azure costs for AKS?
- **Answer**: Cost optimization techniques
  - **Node Reservations**: 3-year reserved instances (60% savings)
  - **Spot Node Pools**: For batch workloads
  ```bash
  az aks nodepool add \
    --cluster-name prod-aks-cluster \
    --name spotpool \
    --priority Spot \
    --eviction-policy Delete \
    --spot-max-price -1 \
    --node-count 0 \
    --min-count 0 \
    --max-count 10
  ```
  - **Cluster Autoscaler**: Scale down during off-hours
  - **Azure Advisor**: Regular cost recommendations
  - **Kube-cost**: Per-namespace cost visibility

---

### Q4.2: Azure DevOps CI/CD Pipelines
**Question:** Design a comprehensive CI/CD pipeline for microservices deployment to AKS using Azure DevOps.

**Answer:**
**Pipeline Architecture:**
```yaml
# azure-pipelines.yml
trigger:
  branches:
    include:
    - main
    - develop
  paths:
    include:
    - services/user-api/*

pool:
  vmImage: 'ubuntu-latest'

variables:
  - group: production-secrets
  - name: dockerRegistryServiceConnection
    value: 'acr-connection'
  - name: imageRepository
    value: 'user-api'
  - name: containerRegistry
    value: 'prodregistry.azurecr.io'
  - name: tag
    value: '$(Build.BuildId)'

stages:
- stage: Build
  displayName: 'Build and Test'
  jobs:
  - job: BuildJob
    steps:
    # Code quality checks
    - task: SonarCloudPrepare@1
      inputs:
        SonarCloud: 'SonarCloud'
        organization: 'myorg'
        scannerMode: 'CLI'
    
    # Unit tests
    - script: |
        mvn clean test
        mvn jacoco:report
      displayName: 'Run Unit Tests'
    
    # Publish test results
    - task: PublishTestResults@2
      inputs:
        testResultsFormat: 'JUnit'
        testResultsFiles: '**/TEST-*.xml'
        mergeTestResults: true
    
    # Build Docker image
    - task: Docker@2
      displayName: 'Build and Push Image'
      inputs:
        command: buildAndPush
        repository: $(imageRepository)
        dockerfile: 'Dockerfile'
        containerRegistry: $(dockerRegistryServiceConnection)
        tags: |
          $(tag)
          latest
    
    # Security scan
    - task: aquasecScanner@4
      inputs:
        image: '$(containerRegistry)/$(imageRepository):$(tag)'
        scanner: 'Trivy'
        severity: 'HIGH,CRITICAL'

- stage: DeployDev
  displayName: 'Deploy to Development'
  dependsOn: Build
  condition: and(succeeded(), eq(variables['Build.SourceBranch'], 'refs/heads/develop'))
  jobs:
  - deployment: DeployDev
    environment: 'development'
    strategy:
      runOnce:
        deploy:
          steps:
          - task: KubernetesManifest@0
            inputs:
              action: 'deploy'
              kubernetesServiceConnection: 'aks-dev'
              namespace: 'development'
              manifests: |
                k8s/deployment.yaml
                k8s/service.yaml
              containers: |
                $(containerRegistry)/$(imageRepository):$(tag)

- stage: DeployProd
  displayName: 'Deploy to Production'
  dependsOn: Build
  condition: and(succeeded(), eq(variables['Build.SourceBranch'], 'refs/heads/main'))
  jobs:
  - deployment: DeployProd
    environment: 'production'
    strategy:
      canary:
        increments: [10, 25, 50, 100]
        preDeploy:
          steps:
          - script: echo "Pre-deployment checks"
        deploy:
          steps:
          - task: KubernetesManifest@0
            displayName: 'Deploy Canary'
            inputs:
              action: 'deploy'
              kubernetesServiceConnection: 'aks-prod'
              namespace: 'production'
              manifests: |
                k8s/deployment.yaml
              containers: |
                $(containerRegistry)/$(imageRepository):$(tag)
          
          # Wait and monitor
          - script: sleep 300
            displayName: 'Canary Monitoring Window'
        
        routeTraffic:
          steps:
          - script: echo "Routing $(strategy.increment)% traffic"
        
        postRouteTraffic:
          steps:
          - task: Bash@3
            displayName: 'Check Error Rate'
            inputs:
              targetType: 'inline'
              script: |
                ERROR_RATE=$(curl -s "https://prometheus.example.com/api/v1/query?query=rate(http_requests_total{status=~\"5..\",service=\"user-api\"}[5m])" | jq '.data.result[0].value[1]')
                if (( $(echo "$ERROR_RATE > 0.01" | bc -l) )); then
                  echo "Error rate too high: $ERROR_RATE"
                  exit 1
                fi
        
        on:
          failure:
            steps:
            - task: KubernetesManifest@0
              displayName: 'Rollback'
              inputs:
                action: 'reject'
                kubernetesServiceConnection: 'aks-prod'
                namespace: 'production'
          
          success:
            steps:
            - script: echo "Deployment successful"
```

**Real Project Example:**
Implemented GitOps workflow with ArgoCD + Azure Pipelines:
- **CI (Azure Pipelines)**: Build, test, security scan, push image
- **CD (ArgoCD)**: Automated sync from Git repository
- **Benefits**: 
  - Declarative deployments
  - Easy rollback (git revert)
  - Audit trail of all changes
  - Multi-cluster support

**Follow-up Depth 1:** How do you handle database migrations in your pipeline?
- **Answer**: Automated but controlled migrations
  ```yaml
  - stage: MigrateDev
    jobs:
    - job: RunMigrations
      steps:
      - task: Kubernetes@1
        inputs:
          command: 'run'
          arguments: 'migration-job-$(Build.BuildId) --image=$(containerRegistry)/migrator:$(tag) --restart=Never'
      
      - script: |
          kubectl wait --for=condition=complete job/migration-job-$(Build.BuildId) --timeout=300s
        displayName: 'Wait for Migration'
  ```
  - Use Flyway/Liquibase for version control
  - Separate migration jobs before deployment
  - Always write backward-compatible migrations
  - Testing on production copy before applying

**Follow-up Depth 2:** What's your strategy for secrets management in CI/CD?
- **Answer**: Azure Key Vault integration
  ```yaml
  - task: AzureKeyVault@2
    inputs:
      azureSubscription: 'Azure-Connection'
      KeyVaultName: 'prod-keyvault'
      SecretsFilter: '*'
      RunAsPreJob: true
  ```
  - Secrets injected as environment variables
  - Never commit secrets to Git
  - Rotate secrets regularly (automated)
  - Use managed identities for Azure resources

---

## 5. Chef Configuration Management

### Q5.1: Chef Infrastructure Automation
**Question:** How do you use Chef to manage infrastructure at scale? Walk me through your cookbook development and testing process.

**Answer:**
**Cookbook Structure (Best Practices):**
```
user-api-cookbook/
├── attributes/
│   └── default.rb          # Default attributes
├── recipes/
│   ├── default.rb          # Main recipe
│   ├── install.rb          # Installation logic
│   └── configure.rb        # Configuration logic
├── templates/
│   └── application.yml.erb # Config templates
├── files/
│   └── systemd.service     # Static files
├── test/
│   └── integration/
│       └── default/
│           └── default_test.rb
├── metadata.rb             # Cookbook metadata
└── Berksfile              # Dependencies
```

**Example Recipe:**
```ruby
# recipes/default.rb
# Install Java
package 'openjdk-17-jdk' do
  action :install
end

# Create application user
user 'appuser' do
  comment 'Application Service Account'
  home '/opt/app'
  shell '/bin/bash'
  system true
  action :create
end

# Create application directory
directory '/opt/app' do
  owner 'appuser'
  group 'appuser'
  mode '0755'
  recursive true
  action :create
end

# Download application JAR from artifact repository
remote_file '/opt/app/application.jar' do
  source "https://artifactory.example.com/repository/releases/user-api-#{node['app']['version']}.jar"
  owner 'appuser'
  group 'appuser'
  mode '0644'
  action :create
  notifies :restart, 'service[user-api]', :delayed
end

# Template configuration file
template '/opt/app/application.yml' do
  source 'application.yml.erb'
  owner 'appuser'
  group 'appuser'
  mode '0600'
  variables(
    database_url: node['app']['database']['url'],
    redis_host: node['app']['redis']['host']
  )
  sensitive true
  notifies :restart, 'service[user-api]', :delayed
end

# Systemd service
template '/etc/systemd/system/user-api.service' do
  source 'systemd.service.erb'
  owner 'root'
  group 'root'
  mode '0644'
  notifies :run, 'execute[systemctl-daemon-reload]', :immediately
end

execute 'systemctl-daemon-reload' do
  command 'systemctl daemon-reload'
  action :nothing
end

# Start and enable service
service 'user-api' do
  action [:enable, :start]
end
```

**Testing Strategy (Test Kitchen):**
```yaml
# .kitchen.yml
---
driver:
  name: docker

provisioner:
  name: chef_zero
  product_name: chef
  product_version: 18

platforms:
  - name: ubuntu-22.04
  - name: centos-8

suites:
  - name: default
    run_list:
      - recipe[user-api::default]
    attributes:
      app:
        version: '1.0.0'
        database:
          url: 'postgresql://localhost/testdb'
        redis:
          host: 'localhost'
```

**InSpec Tests:**
```ruby
# test/integration/default/default_test.rb
describe package('openjdk-17-jdk') do
  it { should be_installed }
end

describe user('appuser') do
  it { should exist }
  its('home') { should eq '/opt/app' }
end

describe file('/opt/app/application.jar') do
  it { should exist }
  it { should be_file }
  its('owner') { should eq 'appuser' }
end

describe file('/opt/app/application.yml') do
  it { should exist }
  its('mode') { should cmp '0600' }
end

describe systemd_service('user-api') do
  it { should be_enabled }
  it { should be_running }
end

describe port(8080) do
  it { should be_listening }
  its('processes') { should include 'java' }
end

describe http('http://localhost:8080/health') do
  its('status') { should cmp 200 }
  its('body') { should match /UP/ }
end
```

**Real Project Example:**
Managed 500+ servers across multiple data centers with Chef:
- **Chef Server**: HA setup with PostgreSQL backend
- **Environment Separation**: Dev, Staging, Production environments
- **Role-based Nodes**:
  ```ruby
  # roles/api-server.rb
  name 'api-server'
  description 'API Server Role'
  run_list(
    'recipe[base::default]',
    'recipe[monitoring::node_exporter]',
    'recipe[user-api::default]'
  )
  default_attributes(
    'app' => {
      'version' => '2.3.1',
      'jvm_options' => '-Xms2g -Xmx4g'
    }
  )
  ```
- **Automated Bootstrap**: New servers register and configure automatically
- **Compliance Scanning**: InSpec profiles for CIS benchmarks

**Follow-up Depth 1:** How do you handle secrets in Chef cookbooks?
- **Answer**: Chef Vault or encrypted data bags
  ```bash
  # Create encrypted data bag
  knife vault create passwords mysql -M client \
    -A admin1,admin2 \
    -S "role:db-server AND chef_environment:production"
  
  # Use in recipe
  mysql_password = chef_vault_item('passwords', 'mysql')['password']
  ```
  - Secrets encrypted with node public keys
  - Only authorized nodes can decrypt
  - Regular secret rotation via automated jobs

**Follow-up Depth 2:** How do you ensure idempotency in your cookbooks?
- **Answer**: Use Chef resources properly
  - Always use `action :nothing` with notifies
  - Check current state before making changes
  - Use `only_if` and `not_if` guards:
  ```ruby
  execute 'setup-database' do
    command '/usr/local/bin/db-setup.sh'
    not_if 'psql -U postgres -lqt | grep myapp'
  end
  ```
  - Test idempotency: run `kitchen converge` twice

---

## 6. Advanced Troubleshooting Scenarios

### Q6.1: Performance Degradation Investigation
**Question:** Your production Kubernetes cluster is experiencing intermittent latency spikes. Walk me through your investigation process.

**Answer:**
**Systematic Debugging Approach:**

**1. Gather Symptoms:**
```bash
# Check cluster-wide metrics
kubectl top nodes
kubectl top pods -A --sort-by=cpu
kubectl top pods -A --sort-by=memory

# Review recent events
kubectl get events -A --sort-by='.lastTimestamp'
```

**2. Application Layer:**
```bash
# Check pod status and restarts
kubectl get pods -A -o wide | grep -E 'CrashLoop|Error|OOMKilled'

# Review application logs
kubectl logs <pod-name> --previous --tail=1000 | grep -i "error\|exception\|timeout"

# Check metrics from Prometheus
http_request_duration_seconds{quantile="0.99",service="api"}
```

**3. Network Layer:**
```bash
# DNS resolution times
kubectl run -it --rm debug --image=busybox --restart=Never -- nslookup kubernetes.default

# Check for packet loss
kubectl exec -it <pod> -- ping -c 100 <service-name>

# Inspect CNI plugin
kubectl logs -n kube-system <calico-node-pod>
```

**4. Infrastructure Layer:**
```bash
# Node pressure conditions
kubectl describe node <node-name> | grep -A 5 "Conditions"

# Check disk I/O
kubectl debug node/<node-name> -it --image=ubuntu
# Inside debug container
iostat -x 1 10

# Memory pressure
free -h
cat /proc/sys/vm/swappiness
```

**Real Project Example:**
API response times spiked from 50ms to 2000ms during peak hours:

**Investigation:**
1. **Observed**: CPU throttling on specific pods
2. **Prometheus Query**: `rate(container_cpu_cfs_throttled_seconds_total[5m]) > 0`
3. **Root Cause**: CPU limits set too low relative to requests
   ```yaml
   resources:
     requests:
       cpu: "500m"
     limits:
       cpu: "500m"  # Same as request = aggressive throttling
   ```

**Solution:**
```yaml
resources:
  requests:
    cpu: "500m"
  limits:
    cpu: "2000m"  # Allow bursting
```

**Additional Findings:**
- Database connection pool exhaustion during spikes
- Increased to 50 connections with proper timeout settings
- Implemented HPA based on custom metrics (queue depth)

**Follow-up Depth 1:** How do you prevent this from happening again?
- **Answer**: Proactive measures
  - Load testing with production traffic patterns
  - Chaos engineering (Chaos Mesh):
  ```yaml
  apiVersion: chaos-mesh.org/v1alpha1
  kind: StressChaos
  metadata:
    name: cpu-stress
  spec:
    mode: one
    selector:
      labelSelectors:
        app: api
    stressors:
      cpu:
        workers: 4
    duration: '5m'
  ```
  - SLO/SLI monitoring with alerts
  - Regular capacity planning reviews

---

## 7. Security & Compliance

### Q7.1: Implementing Security Best Practices
**Question:** How do you implement a comprehensive security strategy for cloud-native applications?

**Answer:**
**Defense in Depth Strategy:**

**1. Container Security:**
```dockerfile
# Minimal attack surface
FROM gcr.io/distroless/java17-debian11

# Non-root user
# (distroless already uses non-root UID 65532)

# Read-only root filesystem
# Enforced via Kubernetes securityContext
```

**Kubernetes Security Context:**
```yaml
apiVersion: v1
kind: Pod
metadata:
  name: secure-pod
spec:
  securityContext:
    runAsNonRoot: true
    runAsUser: 1000
    fsGroup: 1000
    seccompProfile:
      type: RuntimeDefault
  containers:
  - name: app
    image: myapp:latest
    securityContext:
      allowPrivilegeEscalation: false
      readOnlyRootFilesystem: true
      capabilities:
        drop:
        - ALL
    volumeMounts:
    - name: tmp
      mountPath: /tmp
  volumes:
  - name: tmp
    emptyDir: {}
```

**2. Network Security:**
```yaml
# Default deny all traffic
apiVersion: networking.k8s.io/v1
kind: NetworkPolicy
metadata:
  name: default-deny-all
spec:
  podSelector: {}
  policyTypes:
  - Ingress
  - Egress
---
# Allow specific ingress
apiVersion: networking.k8s.io/v1
kind: NetworkPolicy
metadata:
  name: api-allow-ingress
spec:
  podSelector:
    matchLabels:
      app: api
  policyTypes:
  - Ingress
  ingress:
  - from:
    - namespaceSelector:
        matchLabels:
          name: frontend
    - podSelector:
        matchLabels:
          app: gateway
    ports:
    - protocol: TCP
      port: 8080
```

**3. Secrets Management:**
```yaml
# External Secrets Operator (AWS Secrets Manager)
apiVersion: external-secrets.io/v1beta1
kind: ExternalSecret
metadata:
  name: app-secrets
spec:
  refreshInterval: 1h
  secretStoreRef:
    kind: SecretStore
    name: aws-secrets-manager
  target:
    name: app-secrets
    creationPolicy: Owner
  data:
  - secretKey: database-password
    remoteRef:
      key: prod/database/password
  - secretKey: api-key
    remoteRef:
      key: prod/api/key
```

**4. Image Scanning (CI/CD Pipeline):**
```yaml
# Trivy scanning in pipeline
- name: Run Trivy vulnerability scanner
  uses: aquasecurity/trivy-action@master
  with:
    image-ref: 'myregistry.io/myapp:${{ github.sha }}'
    format: 'sarif'
    severity: 'CRITICAL,HIGH'
    exit-code: '1'  # Fail build on findings
```

**5. Compliance (OPA Gatekeeper):**
```yaml
apiVersion: templates.gatekeeper.sh/v1beta1
kind: ConstraintTemplate
metadata:
  name: requirelabels
spec:
  crd:
    spec:
      names:
        kind: RequireLabels
      validation:
        openAPIV3Schema:
          properties:
            labels:
              type: array
              items: string
  targets:
    - target: admission.k8s.gatekeeper.sh
      rego: |
        package requirelabels
        
        violation[{"msg": msg}] {
          provided := {label | input.review.object.metadata.labels[label]}
          required := {label | label := input.parameters.labels[_]}
          missing := required - provided
          count(missing) > 0
          msg := sprintf("Missing required labels: %v", [missing])
        }
---
apiVersion: constraints.gatekeeper.sh/v1beta1
kind: RequireLabels
metadata:
  name: require-owner-label
spec:
  match:
    kinds:
      - apiGroups: [""]
        kinds: ["Pod"]
  parameters:
    labels: ["owner", "team", "cost-center"]
```

**Real Project Example:**
Achieved SOC 2 compliance for SaaS platform:
- **Audit Logging**: All API calls logged to immutable storage
- **Encryption**: Data at rest (AES-256) and in transit (TLS 1.3)
- **Access Control**: MFA required, role-based access
- **Vulnerability Management**: Weekly scans, 48-hour SLA for critical patches
- **Incident Response**: Automated playbooks with PagerDuty integration

**Follow-up:** How do you handle zero-day vulnerabilities?
- **Answer**: Rapid response process
  1. **Detection**: Automated CVE scanning (Snyk, Grype)
  2. **Assessment**: Severity and exploitability analysis
  3. **Mitigation**: WAF rules, network isolation if needed
  4. **Patching**: Emergency change process, <24h for critical
  5. **Verification**: Re-scan and penetration testing

---

## Architecture Decision Records (ADRs)

### Example ADR: Choosing Kubernetes over ECS

**Context:**
Need container orchestration platform for microservices architecture supporting 50+ services, 200+ containers.

**Decision:**
Adopt Kubernetes (EKS on AWS) over AWS ECS.

**Rationale:**
- **Multi-cloud Strategy**: Kubernetes allows portability (AWS/Azure/GCP)
- **Ecosystem**: Rich tooling (Helm, Istio, ArgoCD)
- **Flexibility**: Custom schedulers, operators, CRDs
- **Community**: Larger community, more third-party integrations
- **Workload Types**: Better support for batch jobs, stateful sets
- **Future-proof**: Industry standard, not vendor lock-in

**Consequences:**
- **Positive**:
  - Avoided vendor lock-in
  - Access to CNCF ecosystem
  - Easier to hire Kubernetes expertise
- **Negative**:
  - Steeper learning curve
  - More complexity to manage
  - Higher initial setup cost

**Alternatives Considered:**
- **AWS ECS**: Simpler, tighter AWS integration, but vendor lock-in
- **Docker Swarm**: Easier but limited ecosystem, declining adoption
- **Nomad**: Good alternative but smaller ecosystem

---

## Summary: Key Competencies for IC 16+ Level

1. **Architecture**: Design highly available, scalable systems
2. **Troubleshooting**: Systematic debugging under pressure
3. **Automation**: Infrastructure as Code, CI/CD pipelines
4. **Security**: Defense in depth, compliance frameworks
5. **Cost Optimization**: Balance performance and budget
6. **Mentorship**: Guide team on best practices
7. **Communication**: Explain technical decisions to stakeholders

**Interview Success Factors:**
- Provide real project examples with metrics
- Show understanding of trade-offs
- Demonstrate depth in troubleshooting
- Explain architectural decisions
- Discuss failure scenarios and remediation
