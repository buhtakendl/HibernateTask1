package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
    // Class.forName("com.mysql.jdbc.Driver");
    // Connection connection = DriverManager.getConnection(connectionURL, userName, password)
    private static final String userName = "root";
    private static final String password = "root";
    private static final String connectionURL = "jdbc:mysql://localhost:3306/firsttable";


    private static Connection connection;



    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection(connectionURL, userName, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

}
