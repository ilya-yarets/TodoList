<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="css/login.css" type="text/css"/>
  <title>Login Form</title>
</head>
<body>
<jsp:useBean id="user" scope="request" class="entity.User"></jsp:useBean>

<div class="login-page">
  <div class="form">
    <form class="login-form" action="LoginController" method="post">
      <input type="text" placeholder="Login" required="" name="login" value="<jsp:getProperty name="user" property="login"/>">
      <input type="password" placeholder="Password" required="" name="password" value="<jsp:getProperty name="user" property="password"/>">
      <button type="submit">Sing in</button>
      <p class="message">Not registered? <a href="${pageContext.request.contextPath}/register_form.jsp">Create an account</a></p>
    </form>
  </div>
</div>
</body>
</html>
