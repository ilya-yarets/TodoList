package controllers;

import constants.ConstantsJSP;
import dao.IUserDAO;
import entity.User;
import factory.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends BaseController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forward(ConstantsJSP.JUMP_LOGIN_PAGE, request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(ConstantsJSP.KEY_LOGIN);
        String password = request.getParameter(ConstantsJSP.KEY_PASSWORD);
        login = login.trim();
        password = password.trim();
        User user = new User();
        IUserDAO userDAO = DAOFactory.getDAO(IUserDAO.class);
        boolean checkItOut = userDAO.userVerification(login, password);

        if (checkItOut) {
            user.setLogin(login);
            HttpSession session = request.getSession();
            session.setAttribute(ConstantsJSP.KEY_LOGIN, user.getLogin());
            request.setAttribute(ConstantsJSP.KEY_LIST_NAME, ConstantsJSP.MESSAGE_FIRST_MESSAGE_AFTER_LOGIN);
            forward(ConstantsJSP.JUMP_TODO, request, response);
        } else {
            forwardError(ConstantsJSP.ERROR_WRONG_LOGIN_OR_PASSWORD, request, response);
        }
    }
}