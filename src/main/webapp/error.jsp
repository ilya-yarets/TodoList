<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="css/error.css" type="text/css"/>
  <title>Error Page</title>
</head>
<body>
<div class="login-page">
  <div class="form">
    <form class="login-form">
      <p>Error: ${errorMessage}</p>
      <p class="message"><a href="${pageContext.request.contextPath}/login_form.jsp">Please, try again...</a></p>
    </form>
  </div>
</div>
</body>
</html>
