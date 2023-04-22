<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<body>
<jsp:include page="fragments/header.jsp"/>

<div align="center">
    <h2><spring:message code="user.title"/></h2>
    <hr>
    <section>

        <form method="get" action="/users/search">
            <input type="text" name="keyword"/> &nbsp;
            <button type="submit"><spring:message code="common.search"/></button>
        </form>

        <h3><a href="users/create"><spring:message code="user.add"/></a></h3>
        <table border="1" cellpadding="6">
            <thead>
            <tr>
                <th><spring:message code="user.name"/></th>
                <th><spring:message code="user.email"/></th>
                <th><spring:message code="user.roles"/></th>
                <th><spring:message code="user.active"/></th>
                <th><spring:message code="user.registered"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${users}" var="user">

                <jsp:useBean id="user" type="com.topjava.votesystem.model.User"/>
                <%--    <tr data-meal-excess="${u.excess}">   --%>
                <tr>
                    <td>${user.name}</td>
                    <td>${user.email}</td>
                    <td>${user.roles}</td>
                    <td></td>
                    <td>${user.registered.toLocalDate()} ${user.registered.toLocalTime()}</td>
                    <td><a href="users/update?id=${user.id}"><spring:message code="common.update"/></a></td>
                    <td><a href="users/delete?id=${user.id}"><spring:message code="common.delete"/></a></td>
                </tr>
            </c:forEach>
        </table>
    </section>
</div>
</body>
</html>