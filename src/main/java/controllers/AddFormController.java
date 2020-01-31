package controllers;

import constants.ConstantsJSP;
import dao.IToDoDAO;
import dao.IUserDAO;
import entity.ToDo;
import factory.DAOFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "AddFormController", urlPatterns = {"/AddFormController"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10 MB
        maxFileSize = 1024 * 1024 * 1000, // 1 GB
        maxRequestSize = 1024 * 1024 * 1000)    // 1 GB
public class AddFormController extends BaseController {

    private RequestDispatcher dispatcher = null;

    private IToDoDAO toDoDAO = DAOFactory.getDAO(IToDoDAO.class);

    private IUserDAO userDAO = DAOFactory.getDAO(IUserDAO.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter(ConstantsJSP.KEY_ID);
        String nameToDo = request.getParameter(ConstantsJSP.KEY_NAME_TODO);
        String dateToDo = request.getParameter(ConstantsJSP.KEY_DATE_TODO);

        if (dateToDo == null || dateToDo.equals("")) {
            request.setAttribute(ConstantsJSP.KEY_NOTIFICATION, ConstantsJSP.MESSAGE_DATE_NOT_FOUND);
            dispatcher = request.getRequestDispatcher(ConstantsJSP.JUMP_TODO_LIST);
            dispatcher.forward(request, response);
        }

            ToDo event = new ToDo();

            event.setNameToDo(nameToDo);
            event.setDateToDo(Date.valueOf(dateToDo));

        HttpSession session = request.getSession();
        String loginSession = (String) session.getAttribute(ConstantsJSP.KEY_LOGIN);
        event.setUserId(userDAO.getUserId(loginSession));


        if (id.isEmpty() || id == null) {
            toDoDAO.addNewTask(event);
            request.setAttribute(ConstantsJSP.KEY_NOTIFICATION, ConstantsJSP.MESSAGE_ADD_NEW_TASK);

        } else {
            event.setId(Integer.parseInt(id));

            if (toDoDAO.update(event))
                request.setAttribute(ConstantsJSP.KEY_NOTIFICATION, ConstantsJSP.MESSAGE_NOTIFICATION_SUCCESS);
        }

        dispatcher = request.getRequestDispatcher(ConstantsJSP.JUMP_LIST);
        dispatcher.forward(request, response);
    }

}
