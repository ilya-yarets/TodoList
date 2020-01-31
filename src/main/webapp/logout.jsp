<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="css/logout.css" type="text/css"/>
  <title>Logout Page</title>
</head>
<body>
<% session.invalidate(); %>
<div class="login-page">
  <div class="form">
    <form class="login-form">
      <p>You have been successfully logout!</p>
      <p class="message"><a href="${pageContext.request.contextPath}/login_form.jsp">Log in</a></p>
    </form>
  </div>
</div>
</body>
</html>
