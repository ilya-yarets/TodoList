package service;

import connector.MyConnector;
import constants.ConstantsSQL;
import dao.IUserDAO;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService implements IUserDAO {
    static Connection connection = MyConnector.getConnection();

    @Override
    public void RegisterUser(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(ConstantsSQL.ADD_NEW_USER)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean userVerification(String login, String password) {
        boolean check = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(ConstantsSQL.GET_USER_BY_LOGIN_PASSWORD)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            ResultSet rs1 = preparedStatement.executeQuery();
            check = rs1.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public int getUserId(String login) {
        int id = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(ConstantsSQL.GET_ID_USER)) {
            preparedStatement.setString(1, login);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}