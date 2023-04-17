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
            <th>Имя ресторана</th>
            <th>Дата регистрации</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${result}" var="restaurant">
            <tr>
                <td>${restaurant.id}</td>
                <td>${restaurant.name}</td>
                <td>${restaurant.registered.toLocalDate()} ${restaurant.registered.toLocalTime()}</td>
                <td>
                    <a href="/restaurants/${restaurant.id}">Меню</a>
                    &nbsp;&nbsp;&nbsp;
                    <a href="/restaurants/edit?id=${restaurant.id}">Редактировать</a>
                    &nbsp;&nbsp;&nbsp;
                    <a href="/restaurants/delete?id=${restaurant.id}">Удалить</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="/restaurants">Назад</a>
</div>
</body>
</html>
