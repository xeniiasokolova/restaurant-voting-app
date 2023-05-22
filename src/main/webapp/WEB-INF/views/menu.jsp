<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.topjava.votesystem.util.SecurityUtil" %>
<%@ page import="com.topjava.votesystem.model.Role" %>

<html>
<body>
<jsp:include page="fragments/header.jsp"/>
<div align="center">
    <h2><spring:message code="menu.name"/></h2>
    <hr>
    <section>
        <form method="get" action="/restaurants/${id}/menu/search">
            <input type="text" name="keyword"/> &nbsp;
            <button type="submit"><spring:message code="common.search"/></button>
        </form>

        <c:if test="${SecurityUtil.authUserRole().contains(Role.ADMIN)}">
            <h3>
                <a href="/restaurants/${id}/menu/create"><spring:message code="dish.add"/></a>
            </h3>
        </c:if>
        <table border="1" cellpadding="6">

            <tr>
                <th><spring:message code="dish.name"/></th>
                <th><spring:message code="dish.description"/></th>
                <th><spring:message code="dish.price"/></th>
                <th><spring:message code="dish.registered"/></th>
                <c:if test="${SecurityUtil.authUserRole().contains(Role.ADMIN)}">
                    <th></th>
                    <th></th>
                </c:if>
            </tr>

            <c:if test="${not empty dishes}">
                <c:forEach items="${dishes}" var="dish">
                    <jsp:useBean id="dish" type="com.topjava.votesystem.model.Dish"/>
                    <tr>
                        <td>${dish.name}</td>
                        <td>${dish.description}</td>
                        <td>${dish.price.toString()}</td>
                        <td>${dish.registered.toLocalDate()} ${dish.registered.toLocalTime()}</td>
                        <c:if test="${SecurityUtil.authUserRole().contains(Role.ADMIN)}">
                            <td><a href="/restaurants/${dish.restaurant.id}/menu/update?id=${dish.id}"><spring:message
                                    code="common.update"/></a></td>
                            <td><a href="/restaurants/${dish.restaurant.id}/menu/delete?id=${dish.id}"><spring:message
                                    code="common.delete"/></a></td>
                        </c:if>
                    </tr>
                </c:forEach>
            </c:if>
        </table>

    </section>
</div>

</body>
</html>
