# ATM Simulation System (Java)

## 📌 Overview

This repository contains a **Java-based ATM Simulation System** that demonstrates core Object-Oriented Programming (OOP) concepts such as **inheritance, abstraction, encapsulation, enums, and polymorphism**.  
The program simulates basic ATM operations including client authentication, account selection, balance inquiry, deposits, and withdrawals.

This project is suitable for:
- Learning Java OOP design
- Academic coursework
- Simple console-based banking simulations

---

## 🧩 Features

- Client authentication using **Client ID** and **PIN**
- Support for multiple bank accounts per client
- Account types:
  - Savings Account
  - Current Account
- ATM operations:
  - View balance
  - Withdraw money
  - Deposit money
- Transaction tracking with:
  - Transaction ID
  - Date
  - Status (SUCCESS / FAILURE)
- Currency support using enums
- Clean separation of responsibilities across classes

---

## 🏗️ System Design

### Key Classes & Enums

- **Client**  
  Stores personal details, PIN, currency, and linked bank accounts

- **BankAccount (Abstract)**  
  Common properties for all accounts  
  Abstract methods: `deposit()` and `withdraw()`

- **SavingsAccount**  
  Includes interest rate  
  Implements deposit and withdrawal logic

- **CurrentAccount**  
  Standard account without interest

- **ATM**  
  Handles user interaction  
  Manages authentication and menu-driven operations

- **Transaction**  
  Records transaction history with status and timestamp

- **Branch**  
  Represents a bank branch

- **Enums**
  - `PaymentMethod`
  - `Currency`
  - `TransactionStatus`

---

## ▶️ How to Run

### Prerequisites
- Java JDK 8 or higher
- Command line or any Java IDE (IntelliJ, Eclipse, VS Code)

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/atm-simulation-java.git
   ```

2. Navigate to the project directory:
  ```bash
  cd atm-simulation-java
  ```

3. Compile the program:
  ```bash
  javac Main.java
  ```

4. Run the program:
  ```bash
  java Main
  ```

<img align="right" src="https://visitor-badge.laobi.icu/badge?page_id=patricnilackshan.ATM-Simulation" />
