# Applications-Distribuees
# JDBC PostgreSQL Project

# JDBC PostgreSQL Project

This project demonstrates how to connect a Java application to a PostgreSQL database using **JDBC (Java Database Connectivity)**. The application retrieves and displays data dynamically from a `users` table, providing clean and modular code with proper resource handling.

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
- Dynamically retrieves and displays column names and data from the `users` table.
- Demonstrates proper **resource management** using `try-with-resources`.
- Modular and reusable code structure.

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
Below is the improved and modular Java code for the project:

java
Copy
Edit
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    // Database connection parameters
    private static final String URL = "jdbc:postgresql://localhost:5432/jdbc_test";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Rami2004";

    public static void main(String[] args) {
        // Connect to the database and execute the query
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("✅ Connected to the PostgreSQL database successfully!");

            // Execute query and display results
            displayUsers(connection);

        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Executes a query to retrieve and display all users from the database.
     *
     * @param connection The active database connection
     */
    private static void displayUsers(Connection connection) {
        String query = "SELECT * FROM users";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Print column names
            int columnCount = resultSet.getMetaData().getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(resultSet.getMetaData().getColumnName(i) + "\t");
            }
            System.out.println("\n---------------------------------");

            // Print rows
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(resultSet.getString(i) + "\t");
                }
                System.out.println();
            }

        } catch (Exception e) {
            System.err.println("❌ Query execution error: " + e.getMessage());
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
✅ Connected to the PostgreSQL database successfully!
id	name	email	
---------------------------------
1	Alice Dupont	alice@example.com
2	Bob Martin	bob@example.com
3	Charlie Lemarchand	charlie@example.com

