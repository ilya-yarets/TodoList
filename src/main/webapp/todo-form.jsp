<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.ZoneId" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy"); %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <title>ToDo Manager</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <meta name="description" content=""/>
    <!--<script type="text/javascript" src="js/jquery.js"></script>-->
    <link rel="stylesheet" href="css/main.css" type="text/css"/>
    <link rel="favicon.ico" href="favicon.ico" type="img/x-icon"/>
</head>
<body>
<!--Родительский контейнер-->
<div id="container">
    <header>
        <!--надпись-->
        <h1>ToDoList - task manager</h1>
        <h2 id="username">Hello, ${login}!</h2>
        <!--меню навигации. горизонтальный список ul, ненумерованный li-->
        <nav>
            <ul id="topmenu">
                <li>
                    <a href="${pageContext.request.contextPath}/logout.jsp">Log Out</a>
                </li>
            </ul>
        </nav>
    </header>
    <div id="slider">
        <div id="course">
            <img src="img/slider.png" alt="ToDo"/>
            <h2>ToDo - your planners</h2>
            <section>
                <p><b>ORGANIZE</b></p>
                <p>Run your life like a pro. Organize all your to-do’s in lists and projects.</p>
                <p>Color tag them to set priorities and categories.</p>
                <p>Boost your productivity with notes, subtasks and attachments.</p>
                <p>Get more done with shared lists and assigned tasks.</p>
            </section>
        </div>
    </div>
    <div id="content">
        <div id="left">
            <div class="block">
                <div class="block-header">Navigation</div>
                <div class="block-content">
                    <nav>
                        <div>
                            <a href="${pageContext.request.contextPath}/TodoController?LIST">All active tasks</a>
                        </div>
                        <div>
                            <a href="${pageContext.request.contextPath}/TodoController?action=DATE&statusToDo=1&dateToDo=<%= LocalDate.now(ZoneId.of("Europe/Minsk"))%>">Today</a>
                        </div>
                        <div>
                            <a href="${pageContext.request.contextPath}/TodoController?action=DATE&statusToDo=1&dateToDo=<%=(LocalDate.now(ZoneId.of("Europe/Minsk"))).plusDays(1)%>">Tomorrow</a>
                        </div>
                        <div>
                            <a href="${pageContext.request.contextPath}/TodoController?action=EXPIRED&statusToDo=4">Expired</a>
                        </div>
                        <div>
                            <a href="${pageContext.request.contextPath}/TodoController?action=LIST&statusToDo=3">Recycle
                                Bin</a>
                        </div>
                        <div>
                            <a href="${pageContext.request.contextPath}/TodoController?action=LIST&statusToDo=2">Completed</a>
                        </div>
                        <div>
                            <form action="${pageContext.request.contextPath}/TodoController?action=SELECT_DATE"
                                  method="POST">
                                <p>Someday:</p>
                                <input type="date" class="form-control" name="dateToDo" value="${todo.dateToDo}"/>
                                <button type="submit">OK</button>
                            </form>
                        </div>
                    </nav>
                </div>
            </div>
        </div>
        <div id="right">
            <div class="block">
                <div class="block-header">Smart quotes</div>
                <div class="block-content">
                    <div class="quote">
                        <p>Education is a progressive discovery of our own ignorance.</p>
                        <p class="right"><i>&copy;William James</i></p>
                    </div>
                </div>
            </div>
        </div>
        <div class = "container">
            <h1>${LIST_NAME}</h1>
            <hr/>
            <div class = "row">
                <div class = "col-md-4">
                    <form action = "${pageContext.request.contextPath}/AddFormController" enctype="multipart/form-data" method="POST" >

                        <div class = "form-group">
                            <input type = "text" class = "form-control" name = "nameToDo" placeholder = "Note" value = "${currentTask.nameToDo}"/>
                        </div>

                        <div class = "form-group">
                            <input type = "date" class = "form-control" name = "dateToDo" value = "${currentTask.dateToDo}"/>
                        </div>

                        <input type = "hidden" name = "id" value = "${currentTask.id}"/>

                        <button type = "submit" class = "btn btn-primary">Save</button>
                    </form>
                    <a href = "${pageContext.request.contextPath}/TodoController?action=LIST">Back to List</a>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
