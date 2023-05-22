<%@ page import="com.topjava.votesystem.model.Role" %>
<%@ page import="com.topjava.votesystem.util.SecurityUtil" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<header>
  <a href="${pageContext.request.contextPath}/restaurants"><spring:message code="restaurant.title"/></a> |
  <%-- Показываем ссылку на страницу Users только для администраторов --%>
  <c:if test="${SecurityUtil.authUserRole().contains(Role.ADMIN)}">
    <a href="${pageContext.request.contextPath}/users"><spring:message code="user.title"/></a> |
  </c:if>
  <a href="${pageContext.request.contextPath}/login"><spring:message code="app.home"/></a>
</header>