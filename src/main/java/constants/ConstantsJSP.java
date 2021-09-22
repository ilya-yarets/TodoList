package constants;

public final class ConstantsJSP {
    //error message
    public static final String KEY_ERROR_MESSAGE = "errorMessage";

    public static final String KEY_ERROR = "error";
    public static final String ERROR_WRONG_LOGIN_OR_PASSWORD = "Wrong password or login.";

    //message
    public static final String KEY_NOTIFICATION = "NOTIFICATION";

    public static final String MESSAGE_DELETE = "Task Deleted Successfully!";
    public static final String MESSAGE_MOVE_TO_RECYCLE = "Task successfully moved to recycle bin!";
    public static final String MESSAGE_TASK_RELEVANT = "The task is again relevant!";
    public static final String MESSAGE_TASK_DONE = "Task have done!";
    public static final String MESSAGE_NOTIFICATION_SUCCESS = "Task have edited Successfully!";
    public static final String MESSAGE_NOTIFICATION_DELETED = "File Successfully deleted!";
    public static final String MESSAGE_NOTIFICATION_ADDED = "File Successfully added! The file is placed in C:\\Files\\resources";
    public static final String MESSAGE_FILE_NOT_FOUND = "File not found. Please return to the task list and add the file!";
    public static final String MESSAGE_DATE_NOT_FOUND = "DATE not found. Please return to the task list and select the date!";

    //lists tasks
    public static final String KEY_LIST_NAME = "LIST_NAME";

    public static final String MESSAGE_FIRST_MESSAGE_AFTER_LOGIN = "Hello! We're glad to see you. You can create a new task";
    public static final String MESSAGE_ALL_ACTIVE_TASKS = "All active tasks";
    public static final String MESSAGE_RECYCLE_LIST = "Recycle";
    public static final String MESSAGE_COMPLETED_LIST = "Completed tasks";
    public static final String MESSAGE_EDIT_TASK = "Edit task";
    public static final String MESSAGE_EXPIRED_TASK = "Expired";
    public static final String MESSAGE_TASKS_FOR = "Tasks for ";
    public static final String MESSAGE_ADD_NEW_TASK = "Add new task";

    //attributes
    public static final String KEY_LOGIN = "login";
    public static final String KEY_USER_ID = "userId";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_FIRST_NAME = "firstname";
    public static final String KEY_LAST_NAME = "lastname";
    public static final String KEY_NAME_TODO = "nameToDo";
    public static final String KEY_DATE_TODO = "dateToDo";
    public static final String KEY_FILE_NAME_TODO = "fileNameToDo";
    public static final String KEY_STATUS = "statusToDo";
    public static final String KEY_LIST = "list";
    public static final String KEY_ID = "id";
    public static final String KEY_CURRENT_TASK = "currentTask";
    public static final String KEY_TODAY = "Today";
    public static final String KEY_TOMORROW = "Tomorrow";
    public static final String KEY_FILE_NAME = "fileName";

    //todo_controller
    public static final String KEY_ACTION = "action";

    //pages
    public static final String JUMP_TODO = "/todo-form.jsp";
    public static final String JUMP_ERROR = "/error.jsp";
    public static final String JUMP_LOGIN_PAGE = "/login_form.jsp";
    public static final String JUMP_TODO_LIST = "/todo-list.jsp";
    public static final String JUMP_REG_FORM = "/register_form.jsp";
    public static final String JUMP_RECOVERY = "/TodoController?action=LIST&statusToDo=3";
    public static final String JUMP_LIST = "/TodoController?LIST";

    public static final String EMPTY = "";
}