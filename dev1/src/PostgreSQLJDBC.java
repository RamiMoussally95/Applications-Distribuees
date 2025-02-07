import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreSQLJDBC {

    // Database connection parameters
    private static final String URL = "jdbc:postgresql://localhost:5432/jdbc_test";
    private static final String USER = "postgres";
    private static final String PASSWORD = "your_password";

    public static void main(String[] args) {
        try (Connection connection = connectToDatabase()) {
            System.out.println("✅ Connected to PostgreSQL successfully!");

            // Execute and display query results
            displayUsers(connection);

        } catch (SQLException e) {
            System.err.println("❌ Connection error: " + e.getMessage());
        }
    }

    /**
     * Establishes a connection to the PostgreSQL database.
     *
     * @return Connection object
     * @throws SQLException if connection fails
     */
    private static Connection connectToDatabase() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Executes a query to retrieve users from the database and prints the results.
     *
     * @param connection The active database connection
     */
    private static void displayUsers(Connection connection) {
        String query = "SELECT * FROM users";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            System.out.println("📋 Users:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                System.out.printf("➡ ID: %d | Name: %s | Email: %s%n", id, name, email);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error while executing query: " + e.getMessage());
        }
    }
}
