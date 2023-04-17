<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Меню</title>
</head>
<body>
<div align="center">
    <h2>${param.action == 'create' ? 'Новое блюдо' : 'Редактировать блюдо'}</h2>
    <form:form action="save" method="post" modelAttribute="dish">
        <table border="0" cellpadding="5">
            <tr>
                <td>ID: </td>
                <td>${dish.id}
                    <form:hidden path="id"/>
                </td>
            </tr>
            <tr>
                <td>Имя: </td>
                <td><form:input path="name" /></td>
            </tr>
            <tr>
                <td>Описание: </td>
                <td><form:input path="description" /></td>
            </tr>
            <tr>
                <td>Цена: </td>
                <td><form:input path="price" /></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Сохранить"></td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>