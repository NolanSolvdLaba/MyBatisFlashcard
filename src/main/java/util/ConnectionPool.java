package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mybatis_flashcard_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";

    private static final int POOL_SIZE = 10;

    private static ConnectionPool instance;
    private List<Connection> connections;

    public ConnectionPool() {
        connections = new ArrayList<>();
        initializeConnections();
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    private void initializeConnections() {
        try {
            for (int i = 0; i < POOL_SIZE; i++) {
                Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                connections.add(connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized Connection getConnection() throws InterruptedException {
        while (connections.isEmpty()) {
            wait();
        }
        Connection connection = connections.remove(connections.size() - 1);
        return connection;
    }

    public synchronized void releaseConnection(Connection connection) {
        connections.add(connection);
        notifyAll();
    }

    public synchronized void closeAllConnections() throws SQLException {
        for (Connection connection : connections) {
            connection.close();
        }
        connections.clear();
    }
}
