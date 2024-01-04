<%--
  Created by IntelliJ IDEA.
  User: Asher
  Date: 31.12.2023
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up</title>
  <link href="/css/logAndReg.css" rel="stylesheet" type="text/css">
</head>
<body>
  <div class="container">
    <form action="" method="post">
      <h1>Register Form</h1>
      <div class="form-group">
        <b>Display name</b>
        <input type="text" class="form-control" name="name" minlength="5" maxlength="20" required>
      </div>
      <div class="form-group">
        <b>Email</b>
        <input type="email" class="form-control" name="email" required>
      </div>
      <div class="form-group">
        <b>Password</b>
        <input type="password" class="form-control" name="password" required>
      </div>
      <input type="submit" class="btn" value="Sign up">
    </form>
  </div>
</body>
</html>
