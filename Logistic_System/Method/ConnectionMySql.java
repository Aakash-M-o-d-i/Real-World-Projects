package Method;
import java.sql.*;

public class ConnectionMySql {
    // Database URL, username, and password
    private static final String URL = "jdbc:mysql://localhost:3306/logisticSys";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1111";

    // Method to get a database connection
    public static Connection getConnection() throws SQLException {
        // Returns a connection object to be used elsewhere
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
