<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Пользователи</title>
</head>
<body>
<div align="center">
    <h2>Пользователи</h2>
    <form method="get" action="search">
        <input type="text" name="keyword"/> &nbsp;
        <input type="submit" value="Поиск">
    </form>

    <h3>
        <a href="/restaurants">Рестораны</a><br>
        <a href="/new"> Новый пользователь</a>

    </h3>
    <table border="1" cellpadding="5">
        <tr>
            <th>ID</th>
            <th>Имя</th>
            <th>E-mail</th>
            <th>Дата регистрации</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${users}" var="user">
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
