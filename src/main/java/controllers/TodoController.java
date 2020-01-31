package controllers;

import constants.ConstantsJSP;
import constants.Status;
import dao.IToDoDAO;
import dao.IUserDAO;
import entity.ToDo;
import factory.DAOFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@WebServlet(name = "TodoController", urlPatterns = {"/TodoController"})
public class TodoController extends HttpServlet {

    private RequestDispatcher dispatcher = null;

    private IToDoDAO toDoDAO = DAOFactory.getDAO(IToDoDAO.class);

    private IUserDAO userDAO = DAOFactory.getDAO(IUserDAO.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        toDoDAO.updateExpiredTasks();

        String action = request.getParameter(ConstantsJSP.KEY_ACTION);

        if (action == null) {
            action = "LIST";
        }

        switch (action) {

            case "LIST":
                listTasks(request, response);
                break;

            case "EDIT":
                getSingleEvent(request, response);
                break;

            case "DELETE":
                deleteTask(request, response);
                break;

            case "DONE":
                doneTask(request, response);
                break;

            case "DATE":
                dateTaskList(request, response);
                break;

            case "EXPIRED":
                expiredTask(request, response);
                break;

            case "RECOVERY":
                recoveryTask(request, response);
                break;

            default:
                listTasks(request, response);
                break;

        }
    }

    public void listTasks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String statusToDo = request.getParameter(ConstantsJSP.KEY_STATUS);
        HttpSession session = request.getSession();
        String loginSession = (String) session.getAttribute(ConstantsJSP.KEY_LOGIN);
        int userId = userDAO.getUserId(loginSession);

        if (statusToDo == null) {
            List<ToDo> listAllTasks = toDoDAO.getAllActiveTasksForUser(userId);
            request.setAttribute(ConstantsJSP.KEY_LIST, listAllTasks);
            request.setAttribute(ConstantsJSP.KEY_LIST_NAME, ConstantsJSP.MESSAGE_ALL_ACTIVE_TASKS);
            dispatcher = request.getRequestDispatcher(ConstantsJSP.JUMP_TODO_LIST);
            dispatcher.forward(request, response);
        } else {
            List<ToDo> activeStatusList = toDoDAO.getAllTasksByStatus(userId, Byte.parseByte(statusToDo));
            if (Byte.parseByte(statusToDo) == Status.RECYCLE) {
                request.setAttribute(ConstantsJSP.KEY_LIST_NAME, ConstantsJSP.MESSAGE_RECYCLE_LIST);
            }
            if (Byte.parseByte(statusToDo) == Status.DONE) {
                request.setAttribute(ConstantsJSP.KEY_LIST_NAME, ConstantsJSP.MESSAGE_COMPLETED_LIST);
            }
            request.setAttribute(ConstantsJSP.KEY_LIST, activeStatusList);
            dispatcher = request.getRequestDispatcher(ConstantsJSP.JUMP_TODO_LIST);
            dispatcher.forward(request, response);
        }
//        listTasks(request, response);
    }

    private void getSingleEvent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer id = Integer.valueOf(request.getParameter(ConstantsJSP.KEY_ID));

        ToDo currentTask = toDoDAO.getById(id);

        request.setAttribute(ConstantsJSP.KEY_CURRENT_TASK, currentTask);

        request.setAttribute(ConstantsJSP.KEY_LIST_NAME, ConstantsJSP.MESSAGE_EDIT_TASK);
        dispatcher = request.getRequestDispatcher(ConstantsJSP.JUMP_TODO);
        dispatcher.forward(request, response);
    }

    private void deleteTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter(ConstantsJSP.KEY_ID));
        byte statusToDo = Byte.parseByte(request.getParameter(ConstantsJSP.KEY_STATUS));
        String fileName = request.getParameter(ConstantsJSP.KEY_FILE_NAME);
        String filePath = "C:\\Files\\resources" + File.separator + fileName;
        File file = new File(filePath);


        if (statusToDo == Status.RECYCLE) {
            if (toDoDAO.delete(id)) {
                if (file.exists()) {
                    file.delete();
                }
                request.setAttribute(ConstantsJSP.KEY_NOTIFICATION, ConstantsJSP.MESSAGE_DELETE);
            }
        } else {
            statusToDo = Status.RECYCLE;
            if (toDoDAO.updateStatus(id, statusToDo)) {
                request.setAttribute(ConstantsJSP.KEY_NOTIFICATION, ConstantsJSP.MESSAGE_MOVE_TO_RECYCLE);
            }
        }
        request.setAttribute(ConstantsJSP.KEY_LIST_NAME, ConstantsJSP.MESSAGE_ALL_ACTIVE_TASKS);
        listTasks(request, response);
    }

    private void expiredTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String loginSession = (String) session.getAttribute(ConstantsJSP.KEY_LOGIN);
        byte statusToDo = Byte.parseByte(request.getParameter(ConstantsJSP.KEY_STATUS));
        int userId = userDAO.getUserId(loginSession);

        List<ToDo> expiredList = toDoDAO.getAllTasksByStatus(userId, statusToDo);

        request.setAttribute(ConstantsJSP.KEY_LIST_NAME, ConstantsJSP.MESSAGE_EXPIRED_TASK);
        request.setAttribute(ConstantsJSP.KEY_LIST, expiredList);
        dispatcher = request.getRequestDispatcher(ConstantsJSP.JUMP_TODO_LIST);
        dispatcher.forward(request, response);
    }


    private void doneTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter(ConstantsJSP.KEY_ID));
        byte statusToDo = Byte.parseByte(request.getParameter(ConstantsJSP.KEY_STATUS));

        if (statusToDo == Status.DONE) {
            statusToDo = Status.ACTUAL;
            if (toDoDAO.updateStatus(id, statusToDo))
                request.setAttribute(ConstantsJSP.KEY_LIST_NAME, ConstantsJSP.MESSAGE_ALL_ACTIVE_TASKS);
            request.setAttribute(ConstantsJSP.KEY_NOTIFICATION, ConstantsJSP.MESSAGE_TASK_RELEVANT);
        } else {
            statusToDo = Status.DONE;
            if (toDoDAO.updateStatus(id, statusToDo)) {
                request.setAttribute(ConstantsJSP.KEY_LIST_NAME, ConstantsJSP.MESSAGE_ALL_ACTIVE_TASKS);
                request.setAttribute(ConstantsJSP.KEY_NOTIFICATION, ConstantsJSP.MESSAGE_TASK_DONE);
            }
        }

        listTasks(request, response);
    }

    private void recoveryTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter(ConstantsJSP.KEY_ID));

        if (toDoDAO.recoveryThisTask(id)) {
            request.setAttribute(ConstantsJSP.KEY_NOTIFICATION, ConstantsJSP.MESSAGE_TASK_RELEVANT);
        }
//        listTasks(request, response);
        dispatcher = request.getRequestDispatcher(ConstantsJSP.JUMP_RECOVERY);
        dispatcher.forward(request, response);

    }

    private void dateTaskList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String loginSession = (String) session.getAttribute(ConstantsJSP.KEY_LOGIN);
        byte statusToDo = Byte.parseByte(request.getParameter(ConstantsJSP.KEY_STATUS));
        int userId = userDAO.getUserId(loginSession);

        Date dateToDo = Date.valueOf(request.getParameter(ConstantsJSP.KEY_DATE_TODO));

        LocalDate today = LocalDate.now(ZoneId.of("Europe/Minsk"));
        LocalDate tomorrow = today.plusDays(1);

        if (LocalDate.parse(request.getParameter(ConstantsJSP.KEY_DATE_TODO)).equals(today) && statusToDo == Status.ACTUAL) {
            List<ToDo> todayList = toDoDAO.getAllTasksByDate(userId, statusToDo, dateToDo);
            request.setAttribute(ConstantsJSP.KEY_LIST, todayList);
            request.setAttribute(ConstantsJSP.KEY_LIST_NAME, ConstantsJSP.KEY_TODAY);
            dispatcher = request.getRequestDispatcher(ConstantsJSP.JUMP_TODO_LIST);
            dispatcher.forward(request, response);
        } else if (LocalDate.parse(request.getParameter(ConstantsJSP.KEY_DATE_TODO)).equals(tomorrow) && statusToDo == Status.ACTUAL) {
            List<ToDo> todayList = toDoDAO.getAllTasksByDate(userId, statusToDo, dateToDo);
            request.setAttribute(ConstantsJSP.KEY_LIST, todayList);
            dispatcher = request.getRequestDispatcher(ConstantsJSP.JUMP_TODO_LIST);
            request.setAttribute(ConstantsJSP.KEY_LIST_NAME, ConstantsJSP.KEY_TOMORROW);
            dispatcher.forward(request, response);
        }
//        listTasks(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter(ConstantsJSP.KEY_ACTION);

        if (action == null) {
            action = "LIST";
        }

        switch (action) {

            case "SELECT_DATE":
                selectDate(request, response);
                break;

            case "LIST":
                listTasks(request, response);
                break;

        }
    }

    private void selectDate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String dateToDo = request.getParameter(ConstantsJSP.KEY_DATE_TODO);
        if (dateToDo == null || dateToDo.equals("")) {
            request.setAttribute(ConstantsJSP.KEY_NOTIFICATION, ConstantsJSP.MESSAGE_DATE_NOT_FOUND);
            dispatcher = request.getRequestDispatcher(ConstantsJSP.JUMP_TODO_LIST);
            dispatcher.forward(request, response);
        }
            Byte actualStatus = Status.ACTUAL;

            HttpSession session = request.getSession();
            String loginSession = (String) session.getAttribute(ConstantsJSP.KEY_LOGIN);
            int userId = userDAO.getUserId(loginSession);

            List<ToDo> todayList = toDoDAO.getAllTasksByDate(userId, actualStatus, Date.valueOf(dateToDo));

            request.setAttribute(ConstantsJSP.KEY_LIST_NAME, ConstantsJSP.MESSAGE_TASKS_FOR + Date.valueOf(dateToDo));
            request.setAttribute(ConstantsJSP.KEY_LIST, todayList);
            dispatcher = request.getRequestDispatcher(ConstantsJSP.JUMP_TODO_LIST);
            dispatcher.forward(request, response);
    }

}