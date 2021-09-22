package controllers;

import constants.ConstantsJSP;
import dao.IUserDAO;
import entity.User;
import factory.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterController", urlPatterns = {"/RegisterController"})
public class RegisterController extends BaseController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forward(ConstantsJSP.JUMP_REG_FORM, request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        IUserDAO userDAO = DAOFactory.getDAO(IUserDAO.class);

        user.setFirstName(request.getParameter(ConstantsJSP.KEY_FIRST_NAME));
        user.setLastName(request.getParameter(ConstantsJSP.KEY_LAST_NAME));
        user.setLogin(request.getParameter(ConstantsJSP.KEY_LOGIN));
        user.setPassword(request.getParameter(ConstantsJSP.KEY_PASSWORD));
        userDAO.RegisterUser(user);

        forward(ConstantsJSP.JUMP_LOGIN_PAGE, request, response);
    }
}   