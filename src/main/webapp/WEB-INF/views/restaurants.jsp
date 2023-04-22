<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<body>
<jsp:include page="fragments/header.jsp"/>

<div align="center">
    <h2><spring:message code="restaurant.title"/></h2>
    <hr>
    <section>
        <form method="get" action="/restaurants/search">
            <input type="text" name="keyword"/> &nbsp;
            <button type="submit"><spring:message code="common.search"/></button>
        </form>

        <h3>
            <a href="/restaurants/create"><spring:message code="restaurant.add"/></a>
        </h3>

        <table border="1" cellpadding="6">
            <thead>
            <tr>
                <th><spring:message code="restaurant.name"/></th>
                <th><spring:message code="user.registered"/></th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${restaurants}" var="restaurant">
                <jsp:useBean id="restaurant" type="com.topjava.votesystem.model.Restaurant"/>
                <tr>
                    <td>${restaurant.name}</td>
                    <td>${restaurant.registered.toLocalDate()} ${restaurant.registered.toLocalTime()}</td>
                    <td><a href="restaurants/${restaurant.id}/menu"><spring:message code="menu.name"/></a></td>
                    <td><a href="restaurants/update?id=${restaurant.id}"><spring:message code="common.update"/></a></td>
                    <td><a href="restaurants/delete?id=${restaurant.id}"><spring:message code="common.delete"/></a></td>
                </tr>
            </c:forEach>
        </table>
    </section>
</div>
</body>
</html>
