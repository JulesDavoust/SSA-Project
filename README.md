# Real Estate Fractional Ownership System

Welcome to the **Real Estate Fractional Ownership System** project! This system allows users to invest in fractional property ownership, manage wallets, and receive notifications for important events like property funding and rental income.

---

## **1. How to Run the Project**

### **1.1 Prerequisites**

- **Java 17+**
- **Maven**
- **Postman or cURL** (for testing endpoints)

### **1.2 Steps to Run**

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/JulesDavoust/SSA-Project.git
   cd SSA-PROJECT/ssa-application
   ```

2. **Build and Run the Project**:

   ```bash
   mvn clean install
   cd services/property-service
   mvn spring-boot:run
   cd services/investment-service
   mvn spring-boot:run
   cd services/wallet-service
   mvn spring-boot:run
   ```

3. **Access H2 Console**:

   - Open: `http://localhost:8080/h2-console`
   - Use the following credentials:

     - **Driver Class**: `org.h2.Driver`
     - **JDBC URL**: `jdbc:h2:mem:propertydb`
     - **User Name**: `sa`
     - **Password**: (leave empty).

   - Open: `http://localhost:8081/h2-console`
   - Use the following credentials:
     - **Driver Class**: `org.h2.Driver`
     - **JDBC URL**: `jdbc:h2:mem:walletdb`
     - **User Name**: `sa`
     - **Password**: (leave empty).

   - Open: `http://localhost:8082/h2-console`
   - Use the following credentials:
     - **Driver Class**: `org.h2.Driver`
     - **JDBC URL**: `jdbc:h2:mem:investmentdb`
     - **User Name**: `sa`
     - **Password**: (leave empty).

4. **Test Endpoints**:
   - Use the provided Postman collection to test all endpoints.
   - Ensure proper IDs (wallets) are retrieved from the H2 database before testing specific endpoints. And ensure you to replace all the {wallet id}, {property id} and {investment id} by a real id.

---

## **2. Project Structure**

```
ssa-application/
|-- services/
|   |-- investment-service/
|   |   |-- pom.xml
|   |   |-- src/main/
|   |   |   |-- resources/
|   |   |   |-- java/.../ssaapplication/
|   |   |   |   |-- controller/
|   |   |   |   |-- models/
|   |   |   |   |-- repositories/
|   |   |   |   |-- services/
|   |   |   |   |-- validations/
|   |-- property-service/
|   |   |-- pom.xml
|   |   |-- src/main/
|   |   |   |-- resources/
|   |   |   |-- java/.../ssaapplication/
|   |   |   |   |-- models/
|   |   |   |   |-- repositories/
|   |   |   |   |-- services/
|   |   |   |   |-- controllers/
|   |   |   |   |-- strategies/
|   |   |   |   |-- transitions/
|   |-- wallet-service/
|   |   |-- pom.xml
|   |   |-- src/main/
|   |   |   |-- resources/
|   |   |   |-- java/.../ssaapplication/
|   |   |   |   |-- models/
|   |   |   |   |-- repositories/
|   |   |   |   |-- services/
|   |   |   |   |-- controllers/
|   |-- notifications-service/
|   |   |-- pom.xml
|   |   |-- src/.../ssaapplication/
|   |   |   |-- channels/
|   |   |   |-- services/
|-- pom.xml
```

---

## **3. Services Overview**

### **3.1 Wallet Service**

The Wallet service is responsible for managing user wallets, enabling credits, debits, and balance inquiries. Each user has a unique wallet that tracks their available balance.

#### **Purpose and Functionality**

- **Core Purpose**: To handle all financial transactions for users within the platform.
- **Key Features**:
  - **Credits**: Add funds to a user's wallet.
  - **Debits**: Deduct funds for investments while validating balance sufficiency.
  - **Balance Inquiry**: Retrieve the current wallet balance at any time.

#### **Connections to Other Services**

- **Notification Service**: Users receive notifications when:
  - Funds are credited (e.g., rental income).
  - Funds are debited (optional, if significant transactions occur).

#### **Specific Details**

- Wallet IDs are used to uniquely identify each wallet. Moreover you have to retrieve it in the H2 database.
- Uses the H2 database for data persistence. Wallets can be queried directly via H2 for inspection or debugging.

---

### **3.2 Property Service**

The Property service handles the management of investment properties, including their funding status and deadlines.

#### **Purpose and Functionality**

- **Core Purpose**: To provide a catalog of properties for fractional investment and manage their lifecycle.
- **Key Features**:
  - **Catalog Management**:
    - List properties (all or only those open for funding).
    - Add, update, or delete properties as required.
  - **Funding Management**:
    - Track funding progress and deadlines.
    - Transition properties to `FUNDED` when fully financed or `EXPIRED` if the funding deadline passes.
  - **Returns Calculation**:
    - Calculate expected rental income and long-term appreciation.

#### **Connections to Other Services**

- **Notification Service**:
  - Notifies users when a property is fully funded.
  - Notifies users if funding expires and refunds are issued.

#### **Specific Details**

- Properties have funding deadlines, rental income percentages, and appreciation percentages.
- The system enforces a maximum of six properties open for funding at any given time.
- Uses the H2 database to store and query property details.

---

### **3.3 Notification Service**

The Notification service ensures users are informed about key events within the platform, enhancing transparency and user experience.

#### **Purpose and Functionality**

- **Core Purpose**: To send notifications about important events related to properties and wallets.
- **Key Features**:
  - **Funding Notifications**:
    - Notify investors when a property is fully funded.
    - Notify investors when funding expires.
  - **Income Notifications**:
    - Notify users when rental income is credited to their wallet.

#### **Connections to Other Services**

- **Property Service**:
  - When a property's funding status changes (e.g., `FUNDED` or `EXPIRED`), notifications are triggered for all investors.
- **Wallet Service**:
  - When funds are credited or debited to a wallet (e.g., rental income), notifications are sent to the wallet owner.

#### **Specific Details**

- The notifications are logged in the console (simulated as emails for simplicity).
- Can be extended to integrate real email services (e.g., SendGrid or SMTP).

---

### **3.4 Investment Service**

The Investment service allow to manage all the investments and in validity of those.

#### **Purpose and Functionality**

- **Core Purpose**: Allow to retreive a list of investments and allocate some.
- **Key Features**:
  - **Investment Retreival**
    - Retreive all the investments
    - Retreive the investment by user
  - **Investment Allocation**
    - Invest on some properties
    - Verify the investment on a property is possible
    - Verify that the ser can invest this year

#### **Connections to Other Services**
- **Implemented Services**:
  - **Property Service**:
    - Retreive a properties by Id
    - Retreive all the properties
  - **Wallet Service**:
    - Retreive a wallet by Id
    - Retreive all the wallets
    - Update wallet amount

#### **Specific Details**

- Some of the validation operations would have been done by some other services but that are not implemented yet.
- The investments don't save the properties data that they refers to, only the property UUID.

---

### **4. Purpose and Verification**

This system is designed to:

- Simplify fractional property investment and respect the microservice principle.
- Ensure seamless financial transactions through wallets.
- Keep users informed about critical updates via notifications.

To verify that all services work as intended:

1. Use the provided Postman collection to test endpoints.
2. Observe console logs for notifications.
3. Query the H2 database to ensure data consistency.

---

Enjoy using the Real Estate Fractional Ownership System! 🚀