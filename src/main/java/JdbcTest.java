import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcTest {
    //
    // UPDATE THESE VALUES TO MATCH YOUR configuration
    //
    private static final String JDBC_URL = "jdbc:postgresql://172.19.0.3:5432/jobapp"; // <-- Change to port 5433
    private static final String USER = "stillswarm";
    private static final String PASSWORD = "password"; // Use the simple password we set

    public static void main(String[] args) {
        System.out.println("Attempting to connect to the database...");
        Connection connection = null;
        try {
            // 1. Get a connection to the database
            connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

            if (connection != null) {
                System.out.println("✅ SUCCESS! Connection established.");
                System.out.println("PostgreSQL Version: " + connection.getMetaData().getDatabaseProductVersion());
            } else {
                System.out.println("❌ FAILED! DriverManager.getConnection() returned null.");
            }

        } catch (SQLException e) {
            System.err.println("❌ FAILED! An SQL Exception occurred.");
            // Print the full details of the error
            e.printStackTrace();
        } finally {
            // 3. Close the connection
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Connection closed.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}