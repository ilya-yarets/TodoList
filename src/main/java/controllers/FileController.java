package controllers;

import constants.ConstantsJSP;
import dao.IFileDAO;
import factory.DAOFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@WebServlet(name = "FileController", urlPatterns = {"/FileController"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10 MB
        maxFileSize = 1024 * 1024 * 1000, // 1 GB
        maxRequestSize = 1024 * 1024 * 1000)    // 1 GB

public class FileController extends BaseController {
    RequestDispatcher dispatcher = null;
    private IFileDAO iFileDAO = DAOFactory.getDAO(IFileDAO.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter(ConstantsJSP.KEY_ACTION);

        switch (action) {
            case "DELETE":
                deleteFile(request, response);
                break;
        }
    }

    private void deleteFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter(ConstantsJSP.KEY_ID));
            String fileName = request.getParameter(ConstantsJSP.KEY_FILE_NAME);
            String filePath = "C:\\Files\\resources" + File.separator + fileName;

            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
                iFileDAO.updateFilePath(id, ConstantsJSP.EMPTY, ConstantsJSP.EMPTY);
                request.setAttribute(ConstantsJSP.KEY_NOTIFICATION, ConstantsJSP.MESSAGE_NOTIFICATION_DELETED);
            }
            dispatcher = request.getRequestDispatcher(ConstantsJSP.JUMP_TODO_LIST);
            dispatcher.forward(request, response);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter(ConstantsJSP.KEY_ACTION);

        switch (action) {
            case "UPLOAD":
                uploadFile(request, response);
                break;
        }
    }

    private void uploadFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String folderName = "resources";
            String uploadPath = "C:\\Files\\" + folderName;
            File dir = new File(uploadPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            Part file = request.getPart(ConstantsJSP.KEY_FILE_NAME_TODO);

                String fileName = file.getSubmittedFileName();
            if (fileName == null || fileName =="") { //FIXME - is == required or will be better equals operation?
                request.setAttribute(ConstantsJSP.KEY_NOTIFICATION, ConstantsJSP.MESSAGE_FILE_NOT_FOUND);
                dispatcher = request.getRequestDispatcher(ConstantsJSP.JUMP_TODO_LIST);
                dispatcher.forward(request, response);
            } else {
                String path = uploadPath + File.separator + fileName;
                InputStream is = file.getInputStream();
                Files.copy(is, Paths.get(uploadPath + File.separator + fileName), StandardCopyOption.REPLACE_EXISTING);

                int id = Integer.parseInt(request.getParameter(ConstantsJSP.KEY_ID));

                if (iFileDAO.updateFilePath(id, fileName, path))
                    request.setAttribute(ConstantsJSP.KEY_NOTIFICATION, ConstantsJSP.MESSAGE_NOTIFICATION_ADDED);

                dispatcher = request.getRequestDispatcher(ConstantsJSP.JUMP_TODO_LIST);
                dispatcher.forward(request, response);
            }
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }
}