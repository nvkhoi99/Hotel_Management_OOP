package Hotel.DAL.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {

    private static List<Connection> availableConnections;
    private static List<Connection> usedConnections;

    private static String ENDPOINT;
    private static String USER;
    private static String PASSWORD;

    private static int CAPACITY = 10;

    private ConnectionPool() {

    }

    public static void configure(String endpoint, String user, String password) throws SQLException {
        ENDPOINT = endpoint;
        USER = user;
        PASSWORD = password;

        availableConnections = new ArrayList<>();
        usedConnections = new ArrayList<>();
        for (int i = 0; i < CAPACITY; i++) {
            availableConnections.add(DriverManager.getConnection(ENDPOINT, USER, PASSWORD));
        }
    }

    public static Connection getconnection() throws SQLException {
        if (availableConnections.isEmpty()) {
            throw new SQLException("Hệ thống hiện không khả dụng, vui lòng thử lại sau");
        }
        Connection connection = availableConnections.remove(0);
        usedConnections.add(connection);
        return connection;
    }

    public static void releaseConnection(Connection connection) {
        try {
            usedConnections.remove(connection);
            if (connection.isClosed()) {
                connection = DriverManager.getConnection(ENDPOINT, USER, PASSWORD);
            }
            availableConnections.add(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
