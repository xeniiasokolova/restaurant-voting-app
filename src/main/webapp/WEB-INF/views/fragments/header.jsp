<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<header>
  <a href="${pageContext.request.contextPath}/restaurants"><spring:message code="restaurant.title"/></a> | <a href="${pageContext.request.contextPath}/users"><spring:message code="user.title"/></a> | <a href="${pageContext.request.contextPath}"><spring:message code="app.home"/></a>
</header>