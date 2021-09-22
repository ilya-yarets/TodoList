package dao;

import entity.ToDo;

import java.sql.Date;
import java.util.List;

public interface IToDoDAO {
    List<ToDo> getAllActiveTasksForUser(int userId);

    List<ToDo> getAllTasksByStatus(int userId, byte status);

    ToDo getById(Integer id);

    boolean update(ToDo toDo);

    boolean delete(int id);

    boolean updateStatus(int id, byte newStatus);

    List<ToDo> getAllTasksByDate(int userId, byte status, Date taskDate);

    void updateExpiredTasks();

    boolean recoveryThisTask(int id);

    void addNewTask(ToDo toDo);
}