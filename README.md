Bank Management System (ATM Application)

Project Title: Bank Management System (ATM Simulation)
Technologies: Java, JDBC, MS SQL Server, OOP, Exception Handling

Description:
Developed a fully functional console-based ATM Management System that allows users to create accounts, authenticate using ATM number and PIN, and perform secure banking operations. The backend is built using MS SQL Server, and the application interacts with the database through JDBC using parameterized queries.

Key Components (Highlighted):

User Authentication System: Login using ATM Number + PIN with validation.

Account Creation Module: Auto-generates a unique 10-digit ATM number and stores credentials securely.

ATM Operations:

View Balance

Deposit Amount (updates balance + records transaction)

Withdraw Amount (balance check + 100-multiple validation)

Mini Statement (fetching latest transactions in descending order)

Database Integration:

Designed Users and Transactions tables with foreign key relationship.

Implemented transaction logs with timestamps.

Connected Java to MSSQL using JDBC driver.

Core Java Concepts:

Used OOP principles with classes such as DatabaseConnection, AtmOperations, and MainATM.

Used PreparedStatement for secure SQL queries (prevents SQL injection).

Added exception handling, input validation, and menu-driven UI.

Outcome:
Successfully built an end-to-end banking simulation demonstrating strong skills in Java programming, database handling, JDBC connectivity, SQL operations, and modular application design.
