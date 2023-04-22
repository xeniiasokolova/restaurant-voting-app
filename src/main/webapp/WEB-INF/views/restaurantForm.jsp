<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><spring:message code="restaurant.title"/></title>
</head>
<body>
<div align="center">
    <section>
        <jsp:useBean id="restaurant" type="com.topjava.votesystem.model.Restaurant" scope="request"/>

        <h3><spring:message code="${restaurant.isNew() ? 'restaurant.add' : 'restaurant.edit'}"/></h3>
        <hr>
        <form action="save" method="post">
            <input type="hidden" name="id" value="${restaurant.id}">
            <table border="0" cellpadding="6">
                <tr>
                    <td><spring:message code="restaurant.name"/>:</td>
                    <td><input type="text" value="${restaurant.name}" size=30 name="name" required></td>
                </tr>
            </table>

            <button type="submit"><spring:message code="common.save"/></button>
            <button onclick="window.history.back()" type="button"><spring:message
                    code="common.cancel"/></button>
        </form>
    </section>
</div>
</body>
</html>
