# Applications-Distribuees
# JDBC PostgreSQL Project

This project demonstrates how to connect a Java application to a PostgreSQL database using **JDBC (Java Database Connectivity)**. The application performs basic database operations, such as retrieving and displaying data from a `users` table.

---

## Table of Contents
- [Features](#features)
- [Requirements](#requirements)
- [Setup Instructions](#setup-instructions)
  - [1. Install Required Tools](#1-install-required-tools)
  - [2. Set Up PostgreSQL Database](#2-set-up-postgresql-database)
  - [3. Set Up Java Project](#3-set-up-java-project)
  - [4. Run the Application](#4-run-the-application)
- [Code](#code)
- [Project Structure](#project-structure)
- [Output Example](#output-example)
- [Author](#author)

---

## Features
- Connects to a **PostgreSQL 16** database.
- Retrieves all records from the `users` table.
- Dynamically handles and prints the column names and values.
- Demonstrates Java database interaction using **JDBC**.

---

## Requirements
- **Java Development Kit (JDK)**: Version 17 or later.
- **PostgreSQL**: Version 16.
- **PostgreSQL JDBC Driver**: Version 42.6.0.
- **VS Code**: Or any IDE of your choice.

---

## Setup Instructions

### 1. Install Required Tools
- Install **PostgreSQL** from [here](https://www.postgresql.org/download/).
- Install **JDK 17** from [here](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html).
- Download the **PostgreSQL JDBC Driver** from [here](https://jdbc.postgresql.org/).

---

### 2. Set Up PostgreSQL Database
1. Open **pgAdmin 4** or use the `psql` command-line tool.
2. Create a new database:
   ```sql
   CREATE DATABASE jdbc_test;
Connect to the jdbc_test database and create the users table:
sql
Copy
Edit
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL
);
Insert some test data:
sql
Copy
Edit
INSERT INTO users (name, email) VALUES
('Alice Dupont', 'alice@example.com'),
('Bob Martin', 'bob@example.com'),
('Charlie Lemarchand', 'charlie@example.com');
3. Set Up Java Project
Clone the Repository:

sh
Copy
Edit
git clone <your-repository-url>
cd <repository-folder>
Add the JDBC Driver:

Download the .jar file for the PostgreSQL JDBC Driver.
Place it inside a folder named lib in your project.
Add the Java Code:

Create a folder named src and add the following file:
src/Main.java
Code
Below is the Java code used in this project:

java
Copy
Edit
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/jdbc_test";
        String user = "postgres";
        String password = "Rami2004";
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL database successfully!");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users;");
            int columnCount = rs.getMetaData().getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(rs.getMetaData().getColumnName(i) + "\t");
            }
            System.out.println("\n---------------------------------");
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
4. Run the Application
Using the Terminal
Compile the program:
sh
Copy
Edit
javac -cp "lib/postgresql-42.6.0.jar" src/Main.java
Run the program:
sh
Copy
Edit
java -cp "lib/postgresql-42.6.0.jar;src" Main
Using VS Code
Open the Run and Debug panel.
Select Run Main and press F5.
Project Structure
css
Copy
Edit
devoir/
├── lib/
│   └── postgresql-42.6.0.jar
├── src/
│   └── Main.java
Output Example
When you run the application, the output should look like this:

sql
Copy
Edit
Connected to the PostgreSQL database successfully!
id	name	email	
---------------------------------
1	Alice Dupont	alice@example.com
2	Bob Martin	bob@example.com
3	Charlie Lemarchand	charlie@example.com

