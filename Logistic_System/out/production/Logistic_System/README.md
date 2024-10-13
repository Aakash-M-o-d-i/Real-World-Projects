# 📦 Logistic System CLI

## 🚀 Project Overview

The **Logistic System CLI** is a command-line application designed to manage package logistics. It allows users to add, track, update, and view packages' details, sender/receiver information, and more, all connected to a MySQL database.

---

## 📋 Features

### 1. ➕ Add New Package

Allows users to add a new package by entering the following details:

#### 👤 Sender Information:
- **Sender Name** 📝
- **Sender Address** 🏠
- **Sender Contact Info** ☎️:
    - Phone Number 📞
    - Email ID 📧

#### 👥 Receiver Information:
- **Receiver Name** 📝
- **Receiver Address** 🏠
- **Receiver Contact Info** ☎️:
    - Phone Number 📞
    - Email ID 📧

#### 📦 Package Information:
- **Package ID** 🆔: Unique identifier for the package.
- **Sender ID** 🆔: Identifier for the sender.
- **Receiver ID** 🆔: Identifier for the receiver.
- **Package Weight** ⚖️: Weight of the package (in kg).
- **Package Dimensions** 📏: Length, Width, Height (in cm).
- **Package Description** 📝: Short description of the package.
- **Delivery Type** 🚚: (Standard, Express, Same-Day, etc.)
- **Origin Location** 🌍: Starting point of the package.
- **Destination Location** 🌍: Final delivery destination.
- **Expected Delivery Date** 📅: Estimated delivery date.

---

### 2. 🔍 Track Package

Allows users to track a package by entering the **Package ID**. The system will display:

- **Package ID** 🆔
- **Current Status** 🔄: In Transit, Delivered, etc.
- **Current Location** 📍
- **Expected Delivery Date** 📅

---

### 3. ✏️ Update Package Status

Enables users to update the current status and location of a package in real-time. Keep your customers informed!

---

### 4. 📜 View Package History

Users can view the full history of a package, including every status update from the moment it was added to the system.

---

### 5. 🚪 Exit

Exits the Logistic System CLI.

---

## 🗄️ Database Integration

This project connects to a **MySQL** database, where all package information, sender/receiver details, and status updates are stored securely. Ensure MySQL is installed and properly configured for the system to work.

---

## 🛠️ Setup and Installation

1. **Install MySQL** and create a database for the logistic system.
2. Clone this repository to your local machine:
   ```bash
   git clone https://github.com/your-repository/logistic-system-cli.git
   Navigate to the project folder:
    ```
3. **Navigate to the project folder**:

    ```bash
    cd logistic-system-cli
    ```

4. **Compile the Java files**:

    ```bash
    javac -cp .:mysql-connector-java-8.0.26.jar *.java
    ```

5. **Run the CLI application**:

    ```bash
    java -cp .:mysql-connector-java-8.0.26.jar LogisticSystem
    ```

6. **Configure the database connection** in the `DatabaseConnection.java` file:

    ```java
    String DATABASE_URL = "jdbc:mysql://localhost:3306/logistic_system";
    String DATABASE_USER = "root";
    String DATABASE_PASSWORD = "password";
    ```

---
## 💻 Usage

1. **Run the Logistic System CLI**:

    ```bash
    java LogisticSystem
    ```

2. **Follow the on-screen instructions to**:
    - Add new packages 📦
    - Track packages 🔍
    - Update package statuses ✏️
    - View package history 📜
    - Exit 🚪

---

## 👥 Contribution

Contributions are welcome! Fork the repository and submit a pull request with improvements or new features.

---

[//]: # (## 📄 License)
[//]: # (---)

## 📧 Contact / DM

- Twitter🌐: https://twitter.com/AakashModi1750_
- LinkedIn🌐:: https://www.linkedin.com/in/aakash-modi-9800052a9/
- GitHub🐱‍💻: https://github.com/Aakash-M-o-d-i
- Instagram🌐: https://www.instagram.com/a_akash.modi/profilecard/?igsh=ZndsbzB3amUwcmh1

---


