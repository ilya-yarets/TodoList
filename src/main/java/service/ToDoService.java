package service;

import connector.MyConnector;
import constants.ConstantsJSP;
import constants.ConstantsDB;
import constants.Status;
import dao.IToDoDAO;
import entity.ToDo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ToDoService implements IToDoDAO {
    private Connection connection = MyConnector.getConnection();

    public ToDoService() {
    }

    @Override
    public void addNewTask(ToDo toDo) {
        Timestamp added_date = new Timestamp(System.currentTimeMillis());
        try (PreparedStatement preparedStatement = connection.prepareStatement(ConstantsDB.ADD_NEW_TASK)) {
            preparedStatement.setDate(1, toDo.getDateToDo());
            preparedStatement.setString(2, toDo.getNameToDo());
            preparedStatement.setByte(3, Status.ACTUAL);
            preparedStatement.setString(4, ConstantsJSP.EMPTY);
            preparedStatement.setInt(5, toDo.getUserId());
            preparedStatement.setString(6, ConstantsJSP.EMPTY);
            preparedStatement.setTimestamp(7, added_date);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateExpiredTasks() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(ConstantsDB.EXPIRED)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ToDo> getAllActiveTasksForUser(int userId) {
        List<ToDo> toDoList = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(ConstantsDB.GET_TASKS_FOR_USER)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ToDo toDo = new ToDo();
                toDo.setId(resultSet.getInt("ID"));
                toDo.setDateToDo(resultSet.getDate("DATETODO"));
                toDo.setNameToDo(resultSet.getString("NAMETODO"));
                toDo.setStatusToDo(resultSet.getByte("STATUSTODO"));
                toDo.setFileNameToDo(resultSet.getString("FILENAMETODO"));

                toDoList.add(toDo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toDoList;
    }

    @Override
    public List<ToDo> getAllTasksByStatus(int userId, byte status) {
        List<ToDo> toDoList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ConstantsDB.GET_TASKS_BY_STATUS)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setByte(2, status);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ToDo toDo = new ToDo();
                toDo.setId(resultSet.getInt("ID"));
                toDo.setDateToDo(resultSet.getDate("DATETODO"));
                toDo.setNameToDo(resultSet.getString("NAMETODO"));
                toDo.setStatusToDo(resultSet.getByte("STATUSTODO"));
                toDo.setFileNameToDo(resultSet.getString("FILENAMETODO"));

                toDoList.add(toDo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toDoList;
    }

    @Override
    public List<ToDo> getAllTasksByDate(int userId, byte status, Date taskDate) {
        List<ToDo> toDoList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ConstantsDB.GET_TASKS_FOR_DATE)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setByte(2, status);
            preparedStatement.setDate(3, taskDate);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ToDo toDo = new ToDo();
                toDo.setId(resultSet.getInt("ID"));
                toDo.setDateToDo(resultSet.getDate("DATETODO"));
                toDo.setNameToDo(resultSet.getString("NAMETODO"));
                toDo.setStatusToDo(resultSet.getByte("STATUSTODO"));
                toDo.setFileNameToDo(resultSet.getString("FILENAMETODO"));

                toDoList.add(toDo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toDoList;
    }

    @Override
    public boolean delete(int id) {
        boolean flag = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(ConstantsDB.DELETE_TASK)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean update(ToDo toDo) {
        boolean flag = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(ConstantsDB.EDIT_TASK)) {
            preparedStatement.setDate(1, toDo.getDateToDo());
            preparedStatement.setString(2, toDo.getNameToDo());
            preparedStatement.setInt(3, toDo.getId());

            preparedStatement.executeLargeUpdate();//FIXME - will throw exception! Is is required?
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateStatus(int id, byte newStatus) {
        boolean flag = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(ConstantsDB.UPDATE_STATUS)) {
            preparedStatement.setByte(1, newStatus);
            preparedStatement.setInt(2, id);

            preparedStatement.executeLargeUpdate();//FIXME - will throw exception! Is is required?
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean recoveryThisTask(int id) {
        boolean flag = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(ConstantsDB.UPDATE_RECOVERY)) {
            preparedStatement.setByte(1, Status.ACTUAL);
            preparedStatement.setInt(2, id);

            preparedStatement.executeLargeUpdate();//FIXME - will throw exception! Is is required?
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public ToDo getById(Integer id) {
        ToDo toDo = new ToDo();

        try (PreparedStatement preparedStatement = connection.prepareStatement(ConstantsDB.GET_TASK_BY_ID)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                toDo.setId(resultSet.getInt("ID"));
                toDo.setDateToDo(resultSet.getDate("DATETODO"));
                toDo.setNameToDo(resultSet.getString("NAMETODO"));
                toDo.setStatusToDo(resultSet.getByte("STATUSTODO"));
                toDo.setFileNameToDo(resultSet.getString("FILENAMETODO"));
            }
            preparedStatement.executeLargeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toDo;
    }
}