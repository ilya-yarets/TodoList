package controllers;

import constants.ConstantsJSP;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// to forward to a view layer
	protected void forward(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

	// to forward back with an error message 
	protected void forwardError(String message, HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException {
		// to put a message into the request scope
		request.setAttribute(ConstantsJSP.KEY_ERROR_MESSAGE, message);
		forward(ConstantsJSP.JUMP_ERROR, request, response);
	}
}