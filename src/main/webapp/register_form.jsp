<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="css/login.css" type="text/css"/>
  <title>Registration Form</title>
</head>
<body>
<jsp:useBean id="user" scope="request" class="entity.User"></jsp:useBean>

<div class="login-page">
  <div class="form">
    <form class="login-form" action="RegisterController" method="post">
      <input type="text" name="firstname" required="" placeholder="First name" value="<jsp:getProperty name="user" property="firstName"/>">
      <input type="text" name="lastname" required="" placeholder="Last name" value="<jsp:getProperty name="user" property="lastName"/>">
      <input type="text" name="login" required="" placeholder="Username" value="<jsp:getProperty name="user" property="login"/>">
      <input type="password" name="password" required="" placeholder="Password" value="<jsp:getProperty name="user" property="password"/>">
      <button type="submit">Register</button>
      <p class="message">Already have an account? <a href="${pageContext.request.contextPath}/login_form.jsp">Return to logging form</a></p>
    </form>
  </div>
</div>
</form>
</body>
</html>
