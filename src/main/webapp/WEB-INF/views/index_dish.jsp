<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Меню</title>
</head>
<body>
<div align="center">
    <h2>Меню</h2>
    <form method="get" action="/restaurants/${restaurantId}/search">
        <input type="text" name="keyword"/> &nbsp;
        <input type="submit" value="Поиск">
    </form>

    <h3>
        <a href="${restaurantId}/new">Добавить блюдо</a>
    </h3>
    <table border="1" cellpadding="5">
        <tr>
            <th>ID</th>
            <th>Имя блюда</th>
            <th>Описание</th>
            <th>Цена</th>
            <th>Дата регистрации</th>
            <th>Action</th>
        </tr>

        <c:if test="${not empty dishes}">
            <c:forEach items="${dishes}" var="dish">
                <tr>
                    <td>${dish.id}</td>
                    <td>${dish.name}</td>
                    <td>${dish.description}</td>
                    <td>${dish.price.toString()}</td>
                    <td>${dish.registered.toLocalDate()} ${dish.registered.toLocalTime()}</td>
                    <td>
                        <a href="/restaurants/${restaurantId}/edit?id=${dish.id}">Редактировать</a>
                        &nbsp;&nbsp;&nbsp;
                        <a href="/restaurants/${restaurantId}/delete?id=${dish.id}">Удалить</a>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
    <a href="/restaurants">Назад</a>
</div>

</body>
</html>
