package connector;

import constants.ConstantsDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MyConnector {
    private static Connection connection = null;

    public static Connection getConnection() {
        if(connection == null){
            setConnection();
        }
        return connection;
    }

    private static void setConnection(){
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle(ConstantsDB.DB_PROPERTY_FILE_NAME);
            Class.forName(resourceBundle.getString(ConstantsDB.HOST));
            String url = resourceBundle.getString(ConstantsDB.URL);
            String user = resourceBundle.getString(ConstantsDB.USER);
            String password = resourceBundle.getString(ConstantsDB.PASSWORD);
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection with mySQL completed successfully");
        } catch (ClassNotFoundException e) {
            System.err.println("Class driver manager is not connect to the project" + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL Exception!");
            e.printStackTrace();
        }
    }

    public static void closeConnection() {//FIXME - использовать методы закрытия ресурсов
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Unsuccessful attempt of connection object close" + e.getMessage());
            }
        }
    }
}