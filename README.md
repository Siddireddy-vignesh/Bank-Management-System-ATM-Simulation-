A console-based ATM Simulation System built using Java, JDBC, and MS SQL Server.
This project allows users to create accounts, log in using ATM credentials, and perform essential banking operations securely.

🚀 Features

🔐 User Authentication (ATM Number + PIN)

🆕 Create New Account with auto-generated ATM number

💰 View Balance

➕ Deposit Money

➖ Withdraw Money (with validations)

📜 Mini Statement (transaction logs)

🗄️ MSSQL Database Integration using JDBC

🧱 Modular code using OOP concepts

⚠️ Strong error handling and input validation

🛠️ Technologies Used

Java

JDBC

MS SQL Server

SQL Queries

Object-Oriented Programming

🗂️ Database Structure
Users Table
Column	Type	Description
atm_number	BIGINT	Unique ATM number (PK)
atm_pin	INT	4-digit PIN
balance	FLOAT	Current balance
Transactions Table
Column	Type	Description
id	INT (PK)	Auto-increment transaction ID
atm_number	BIGINT	Linked to Users table (FK)
type	NVARCHAR	Deposit / Withdraw
amount	FLOAT	Transaction amount
date_time	DATETIME	Timestamp
🔗 Java Files Overview
✔ DatabaseConnection.java

Handles connection to MS SQL Server using JDBC.

✔ AtmOperations.java

Contains all ATM functions like deposit, withdraw, balance check, and mini statements.

✔ MainATM.java

Menu-driven UI for login, account creation, and ATM interface.

▶️ How to Run

Import the project into VS Code or IntelliJ

Install MS SQL Server & create the database:

CREATE DATABASE ATM_DB;


Create required tables

Add the JDBC driver to your project lib folder

Run MainATM.java

📷 Output (Highlights)

✔ Login Screen
✔ Account Creation
✔ Deposit / Withdraw
✔ Mini Statement
