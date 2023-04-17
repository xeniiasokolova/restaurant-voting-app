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
            <th>Имя блюда</th>
            <th>Описание</th>
            <th>Цена</th>
            <th>Дата регистрации</th>
            <th>Action</th>
        </tr>

        <c:if test="${not empty result}">
            <c:forEach items="${result}" var="dish">
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
