package connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MyConnector {
    private static Connection connection = null;
    public static Connection getConnection() {
        try {
            final String FILE_NAME = "database";
            ResourceBundle resourceBundle = ResourceBundle.getBundle(FILE_NAME);
            Class.forName(resourceBundle.getString("host"));
            String url = resourceBundle.getString("url");
            String user = resourceBundle.getString("user");
            String password = resourceBundle.getString("password");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection with mySQL completed successfully");
        } catch (ClassNotFoundException e) {
            System.err.println("Class driver manager is not connect to the project" + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL Exception!");
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Unsuccessful attempt of connection object close" + e.getMessage());
            }
        }
    }
}