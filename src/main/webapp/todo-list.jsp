<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.ZoneId" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd"); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <title>ToDo Manager</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <meta name="description" content=""/>
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
        <!--Центральный блок-->
        <div id="center">
            <div class="container">

                <h1>${LIST_NAME}</h1>
                <hr/>

                <p>${NOTIFICATION}</p>

                <p>
                    <button class="btn btn-primary" onclick="window.location.href = '${pageContext.request.contextPath}/todo-form.jsp'">Add Employee
                    </button>
                </p>

                <table class="table table-striped table-bordered">

                    <tr class="thead-dark">
                        <th>Date</th>
                        <th>Note</th>
                        <th>File</th>
                        <th>Actions</th>
                    </tr>

                    <c:forEach items="${list}" var="list">

                        <tr>
                            <td>${list.dateToDo}</td>
                            <td>${list.nameToDo}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${list.fileNameToDo==''}">
                                        <form action="${pageContext.request.contextPath}/FileController?action=UPLOAD&id=${list.id}"
                                              enctype="multipart/form-data" method="POST">
                                            <input type="file" name="fileNameToDo" class="inputfile"/>
                                            <button type="submit" class="btn btn-primary">Upload file</button>
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        ${list.fileNameToDo}
                                        <a href="${pageContext.request.contextPath}/FileController?action=DELETE&fileName=${list.fileNameToDo}&id=${list.id}"><img
                                                src="img/cross.png" width="15"
                                                height="15" alt="Delete"></a>
                                    </c:otherwise>
                                </c:choose></td>
                            <td>
                                <a href="${pageContext.request.contextPath}/TodoController?action=DONE&id=${list.id}&statusToDo=${list.statusToDo}">Done</a>
                                |
                                <a href="${pageContext.request.contextPath}/TodoController?action=EDIT&id=${list.id}">Edit</a>
                                |
                                <a href="${pageContext.request.contextPath}/TodoController?action=DELETE&id=${list.id}&statusToDo=${list.statusToDo}&fileName=${list.fileNameToDo}">Delete</a>
                                |
                                <% String statusToDo = request.getParameter("statusToDo");%>
                                <a href="${pageContext.request.contextPath}/TodoController?action=RECOVERY&id=${list.id}">
                                    <% if (statusToDo != null && statusToDo.equals("3")) { %>
                                    Recovery
                                    <% } %>
                                </a>

                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
