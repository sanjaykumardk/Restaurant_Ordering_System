package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() throws SQLException {
        try {
            String url = "jdbc:mysql://localhost:3306/restaurant_db";
            String user = "sanjaydk";
            String password = "123abc";  // Replace with your password
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new SQLException("Error connecting to the database", e);
        }
    }
}
