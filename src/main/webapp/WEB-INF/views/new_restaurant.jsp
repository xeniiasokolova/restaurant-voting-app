<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Добавить ресторан</title>
</head>
<body>
<div align="center">
    <h2>Добавить ресторан</h2>
    <form:form action="save" method="post" modelAttribute="restaurant">
        <table border="0" cellpadding="5">
            <tr>
                <td>Имя:</td>
                <td><form:input path="name"/></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Сохранить"></td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>
