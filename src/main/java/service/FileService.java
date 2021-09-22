package service;

import connector.MyConnector;
import constants.ConstantsDB;
import dao.IFileDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FileService implements IFileDAO {
    Connection connection = MyConnector.getConnection();

    public FileService() {
    }

    public boolean updateFilePath(int id, String filename, String pathFile) {
        boolean flag = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(ConstantsDB.UPDATE_FILE_PATH)) {
            preparedStatement.setString(1, filename);
            preparedStatement.setString(2, pathFile);
            preparedStatement.setInt(3, id);

            preparedStatement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
}