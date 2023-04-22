<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<title><spring:message code="menu.name"/></title>
<body>
<div align="center">
    <section>
        <jsp:useBean id="dish" type="com.topjava.votesystem.model.Dish" scope="request"/>
        <h3><spring:message code="${dish.isNew() ? 'dish.add' : 'dish.edit'}"/></h3>
        <hr>
        <form:form action="save" method="post">
            <input type="hidden" name="id" value="${dish.id}">
            <input type="hidden" name="restaurantid" value="${dish.restaurant.id}">
            <table border="0" cellpadding="6">
                <tr>
                    <td><spring:message code="dish.name"/>:</td>
                    <td><input type="text" value="${dish.name}" size=30 name="name" required></td>
                </tr>
                <tr>
                    <td><spring:message code="dish.description"/>:</td>
                    <td><input type="text" value="${dish.description}" size=30 name="description" required></td>
                </tr>
                <tr>
                    <td><spring:message code="dish.price"/>:</td>
                    <td><input type="text" value="${dish.price}" size=30 name="price" required></td>
                </tr>
            </table>

            <button type="submit"><spring:message code="common.save"/></button>
            <button onclick="window.history.back()" type="button"><spring:message
                    code="common.cancel"/></button>

        </form:form>
    </section>
</div>
</body>
</html>