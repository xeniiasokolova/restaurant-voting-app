<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Рестораны</title>
</head>
<body>
<div align="center">
    <h2>Рестораны</h2>
    <form method="get" action="/restaurants/search">
        <input type="text" name="keyword"/> &nbsp;
        <input type="submit" value="Поиск">
    </form>

    <h3>
        <a href="restaurants/new"> Новый ресторан</a>
    </h3>
    <table border="1" cellpadding="5">
        <tr>
            <th>ID</th>
            <th>Имя ресторана</th>
            <th>Дата регистрации</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${restaurants}" var="restaurant">
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
</div>

</body>
</html>
