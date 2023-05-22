<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.topjava.votesystem.util.SecurityUtil" %>
<%@ page import="com.topjava.votesystem.model.Role" %>

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

        <c:if test="${SecurityUtil.authUserRole().contains(Role.ADMIN)}">
            <h3>
                <a href="/restaurants/create"><spring:message code="restaurant.add"/></a>
            </h3>
        </c:if>

        <table border="1" cellpadding="6">
            <thead>
            <tr>
                <th><spring:message code="restaurant.name"/></th>
                <th><spring:message code="user.registered"/></th>
                <th></th>
                <th></th>
                <c:if test="${SecurityUtil.authUserRole().contains(Role.ADMIN)}">
                    <th></th>
                    <th></th>
                </c:if>
            </tr>
            </thead>
            <c:forEach items="${restaurants}" var="restaurant">
                <jsp:useBean id="restaurant" type="com.topjava.votesystem.model.Restaurant"/>
                <tr>
                    <td>${restaurant.name}</td>
                    <td>${restaurant.registered.toLocalDate()} ${restaurant.registered.toLocalTime()}</td>
                    <td><a href="restaurants/${restaurant.id}/menu"><spring:message code="menu.name"/></a></td>
           <%--         <c:if test="${SecurityUtil.authUserRole().contains(Role.USER)}">  --%>
                    <td>
                        <c:if test="${isButtonActive}">
                            <form action="/restaurants/vote?id=${restaurant.id}" method="post">
                                <button type="submit"><spring:message code="restaurant.vote"/></button>
                            </form>
                        </c:if>
                        <c:if test="${!isButtonActive}">
                            <button type="submit" disabled=${!isButtonActive}>
                                <spring:message code="restaurant.vote"/>
                            </button>
                        </c:if>

                    </td>
             <%--       </c:if>  --%>



                    <c:if test="${SecurityUtil.authUserRole().contains(Role.ADMIN)}">
                        <td><a href="restaurants/update?id=${restaurant.id}"><spring:message code="common.update"/></a>
                        </td>
                        <td><a href="restaurants/delete?id=${restaurant.id}"><spring:message code="common.delete"/></a>
                        </td>

                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </section>
</div>
</body>
</html>
