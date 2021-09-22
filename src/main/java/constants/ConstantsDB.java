package constants;

public class ConstantsDB {
    //FILE
    public static final String UPDATE_FILE_PATH = "UPDATE TODO SET FILENAMETODO=?, PATH=? WHERE ID=?";

    //TODO
    public static final String ADD_NEW_TASK = "INSERT INTO todo(DATETODO, NAMETODO, STATUSTODO, FILENAMETODO, USERID, PATH, added_date) values (?,?,?,?,?,?,?)";
    public static final String EXPIRED = "UPDATE TODO SET STATUSTODO=4 WHERE STATUSTODO=1 AND DATETODO<CURRENT_DATE()";
    public static final String GET_TASKS_FOR_USER = "SELECT ID, DATETODO, NAMETODO, STATUSTODO, FILENAMETODO FROM TODO WHERE USERID=? AND STATUSTODO=1";
    public static final String GET_TASKS_BY_STATUS = "SELECT ID, DATETODO, NAMETODO, STATUSTODO, FILENAMETODO FROM TODO WHERE USERID=? AND STATUSTODO=?";
    public static final String GET_TASKS_FOR_DATE = "SELECT ID, DATETODO, NAMETODO, STATUSTODO, FILENAMETODO FROM TODO WHERE USERID=? AND STATUSTODO=? AND DATETODO=?";
    public static final String DELETE_TASK = "DELETE FROM TODO WHERE ID=?";
    public static final String EDIT_TASK = "UPDATE TODO SET DATETODO=?, NAMETODO=? WHERE ID=?";
    public static final String UPDATE_STATUS = "UPDATE TODO SET STATUSTODO=? WHERE ID=?";
    public static final String UPDATE_RECOVERY = "UPDATE TODO SET STATUSTODO=?, DATETODO=CURRENT_DATE() WHERE ID=?";
    public static final String GET_TASK_BY_ID = "SELECT ID, DATETODO, NAMETODO, STATUSTODO, FILENAMETODO FROM TODO WHERE ID=?";

    //User
    public static final String ADD_NEW_USER = "INSERT INTO USER (FIRSTNAME, LASTNAME, LOGIN, PASSWORD) VALUES(?, ?, ?, ?)";
    public static final String GET_USER_BY_LOGIN_PASSWORD = "SELECT * FROM user WHERE login=? and password=?";
    public static final String GET_ID_USER = "SELECT ID FROM user WHERE LOGIN=?";

    //Connection
    public static final String DB_PROPERTY_FILE_NAME = "database";
    public static final String HOST = "host";
    public static final String URL = "url";
    public static final String USER = "user";
    public static final String PASSWORD = "password";
}