package org.example;
import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/HW27?"
                    + "useSSL=false&user=root&password=rootroot&serverTimezone=UTC");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

   public static Connection getConnection(){
        return connection;
    }

    public static void close(Connection connection) throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
