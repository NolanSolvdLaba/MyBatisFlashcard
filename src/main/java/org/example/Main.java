package org.example;

import util.ConnectionPool;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Assuming you have a ConnectionPool instance called "connectionPool"
        ConnectionPool connectionPool = new ConnectionPool(); // Instantiate your ConnectionPool class
        Connection connection = connectionPool.getConnection();
        System.out.println("Connection acquired: " + connection);

        // Other code and operations

        // Remember to release the connection when you're done
        connectionPool.releaseConnection(connection);
    }
}
