<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Результат поиска</title>
</head>
<body>
<div align="center">
    <h2>Результат поиска</h2>
    <table border="1" cellpadding="5">
        <tr>
            <th>ID</th>
            <th>Имя</th>
            <th>E-mail</th>
            <th>Дата регистрации</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${result}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.email}</td>
                <td>${user.registered.toLocalDate()} ${user.registered.toLocalTime()}</td>
                <td>
                    <a href="/edit?id=${user.id}">Редактировать</a>
                    &nbsp;&nbsp;&nbsp;
                    <a href="/delete?id=${user.id}">Удалить</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="/">Назад</a>
</div>
</body>
</html>
