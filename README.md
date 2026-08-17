# bank-balance


## Prerequisites
* Java 21
* Maven 3.9+
* Podman & Podman Compose (or Docker/Docker Compose)

## Architecture Overview
The application is structured using a strict Layered Architecture (Controller, Service, Repository, DTO, Model) to ensure clean separation of concerns and maintainability.

## Local Execution Instructions

1. **How to run to docker-compose.yml file**
   The provided compose file spins up LocalStack (mocking AWS SQS) and a synthetic transaction generator.
   ```bash
   podman-compose up -d

2. Run the Application
   ```bash
   ./mvnw spring-boot:run
   
3. Testing the Endpoint
   ```bash
   curl --location --request GET 'http://localhost:8086/balances/5b19c8b6-0cc4-4c72-a989-0c2ee15fa975' \
   --header 'Content-Type: application/json'

**Verify the SQS Queue via AWS CLI**
   `aws --endpoint-url=http://localhost:4566 --region sa-east-1 sqs get-queue-attributes \
   --queue-url http://localhost:4566/000000000000/transacoes-financeiras-processadas \
   --attribute-names ApproximateNumberOfMessages`



***Peek at a single transaction message payload***
`aws --endpoint-url=http://localhost:4566 --region sa-east-1 sqs receive-message \
--queue-url http://localhost:4566/000000000000/transacoes-financeiras-processadas \
--max-number-of-messages 1`

4. Testing the H2 database:
**Step 1: Enable the Console in your YAML**
````
   `spring:
      application:
         name: bankbalance
   h2:
      console:
      enabled: true
      path: /h2-console
   datasource:
      url: jdbc:h2:mem:bankdb
   # ... rest of your config
   ````
**Step 2: Access the Web Interface**
Start your Spring Boot application (./mvnw spring-boot:run).
Open your web browser and navigate to: http://localhost:8086/h2-console

**Step 3: Log In and Query**
Fill it out using the exact credentials from your application.yaml

Driver Class: org.h2.Driver
JDBC URL: jdbc:h2:mem:bankdb
User Name: sa
Password: (leave this completely blank)

***Click connect an run the queries***
`SELECT * FROM account_balance ORDER BY updated_at DESC;`



