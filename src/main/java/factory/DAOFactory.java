package factory;

import dao.IFileDAO;
import dao.IToDoDAO;
import dao.IUserDAO;
import service.FileService;
import service.ToDoService;
import service.UserService;

import java.util.HashMap;
import java.util.Map;

public class DAOFactory {
    private static Map<Class<?>, Object> map = new HashMap<>();

    static{
		map.put(IUserDAO.class, new UserService());
		map.put(IToDoDAO.class, new ToDoService());
		map.put(IFileDAO.class, new FileService());
    }

    public static <T> T getDAO(Class<T> type) {
        return  type.cast(map.get(type));
    }

}
