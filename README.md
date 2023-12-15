# **LabSeq Exercise**

This simplifies the startup for the LabSeq exercise, offering a full-stack solution with a Spring Boot backend, Redis caching, and an Angular frontend, all orchestrated with Docker.

## Features

- **Spring Boot Backend:** Implement the LabSeq algorithm with a robust and scalable Spring Boot backend.
- **Redis Caching:** Optimize performance with Redis caching to efficiently store and retrieve sequence values.
- **Angular Frontend:** Interact seamlessly with the LabSeq sequence through an intuitive Angular frontend.
- **Docker Orchestration:** Easily deploy and manage the entire solution using Docker.

## Getting Started

### **Requirements**

- Maven
- Java 17
- Docker

### **Start-up**

Navigate to the project directory and run the following commands in the command line:

```shell
cd Labseq
docker-compose up --build
```

## **Instructions**

#### Using Browser
To interact with the LabSeq function, navigate to [localhost](http://localhost) in your browser.

#### Using Swagger

For a GUI of all the requests available, use [Swagger](http://localhost:8080/swagger-ui/index.html).

#### Using Curl

Retrieve the calculated value of the labseq sequence at the specified index (replace  `${n}` with the desired index value).

```shell
curl -X 'GET' 'http://localhost:8080/labseq/${n}' \
  -H 'accept: application/json'
```

### Tests in Backend

For tests, it was used JUnit 5 and Mockito. To run the tests, run the following commands:

```shell
cd backend
mvn clean test
 ```
### **Service**
These tests cover various scenarios, including retrieving a cached value, calculating values for specific indices, handling edge cases (like index 0 and negative indices), and ensuring that exceptions are thrown appropriately. You can add more test cases as needed to cover additional scenarios or edge cases in the LabSeqService.

1. **`testCalculateLabSeq_CachedValueExists`**
    - **Scenario:** This test checks if the `calculateLabSeq` method returns a cached value when it exists in Redis.
    - **Mock Behavior:**
        - The RedisTemplate is mocked to indicate that a cached value is present.
        - The `valueOperations.get(any())` method is mocked to return a BigInteger value (in this case, 5) when retrieving from the cache.
    - **Assertion:** Verifies that the calculated result is equal to the cached value (5).

2. **`testCalculateLabSeq_IndexZero`**
    - **Scenario:** Tests the calculation for the LabSeq value at index 0.
    - **Assertion:** Verifies that the calculated result is 0.

3. **`testCalculateLabSeq_IndexOne`**
    - **Scenario:** Tests the calculation for the LabSeq value at index 1.
    - **Assertion:** Verifies that the calculated result is 1.

4. **`testCalculateLabSeq_IndexTwo`**
    - **Scenario:** Tests the calculation for the LabSeq value at index 2.
    - **Assertion:** Verifies that the calculated result is 0.

5. **`testCalculateLabSeq_IndexThree`**
    - **Scenario:** Tests the calculation for the LabSeq value at index 3.
    - **Assertion:** Verifies that the calculated result is 1.

6. **`testCalculateLabSeq_NegativeIndex`**
    - **Scenario:** Tests the calculation for a negative index, which should throw a LabSeqException.
    - **Assertion:** Verifies that a LabSeqException is thrown when attempting to calculate the LabSeq value with a negative index.


### **Controller**
These tests cover scenarios like retrieving LabSeq values for different indices, handling caching, and testing edge cases (like index 0). You can add more test cases as needed to cover additional scenarios or edge cases in the LabSeqController.

1. **`testGetLabSeqValue`**
    - **Scenario:** Tests the GET request for the LabSeq value at a specific index.
    - **Mock Behavior:**
        - Mocks the behavior of the LabSeqService to return a BigInteger value (in this case, 10) when calculating the LabSeq value for any index.
    - **Assertions:**
        - Verifies that the response status is OK (200).
        - Verifies that the response content is the expected value ("10").

2. **`testGetLabSeqValue_CachedValue`**
    - **Scenario:** Tests the GET request for the LabSeq value at a specific index, with caching involved.
    - **Mock Behavior:**
        - Mocks the behavior of the LabSeqService to return a cached BigInteger value (10) when calculating the LabSeq value for any index.
    - **Assertions:**
        - Performs the GET request twice and verifies that the response status is OK (200) and the content is the expected value ("10") on both requests, demonstrating caching.

3. **`testGetLabSeqValue_IndexZero`**
    - **Scenario:** Tests the GET request for the LabSeq value at index 0.
    - **Mock Behavior:**
        - Mocks the behavior of the LabSeqService to return a BigInteger value of 0 when calculating the LabSeq value for index 0.
    - **Assertions:**
        - Verifies that the response status is OK (200).
        - Verifies that the response content is the expected value ("0").

