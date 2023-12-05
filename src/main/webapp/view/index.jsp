<%@ page import="java.util.Map" %>
<%@ page import="ru.kors.model.Account" %><%--
  Created by IntelliJ IDEA.
  User: Asher
  Date: 05.12.2023
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Hello</title>
</head>
<body>
<%
    Map<String, Account> accounts = (Map<String, Account>) request.getAttribute("accounts");
%>
<h2>Все пользователи:</h2>
<table>
    <tr>
        <th>Account name</th>
        <th>Balance</th>
    </tr>
    <%
        for (var account : accounts.entrySet()) {
    %>
    <tr>
        <td><%=account.getValue().getName()%></td>
        <td><%=account.getValue().getBalance()%></td>
    </tr>
    <%
        }
    %>
</table>
<hr/>
<h2>Создание нового пользователя:</h2>
<form method="post">
    <input type="text" name="name" placeholder="Account Name" minlength="5" maxlength="20"><br/>
    <input type="password" name="password" placeholder="Password" minlength="5"><br/>
    <input type="number" name="balance" placeholder="Balance" min="0" step="0.01"><br/>
    <input type="submit" value="Create"><br/>
</form>
</body>
</html>
