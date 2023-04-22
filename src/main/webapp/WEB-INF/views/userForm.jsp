<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<title><spring:message code="user.title"/></title>
<body>
<div align="center">
    <section>
        <jsp:useBean id="user" type="com.topjava.votesystem.model.User" scope="request"/>
        <%--    `meal.new` cause javax.el.ELException - bug tomcat --%>
        <h3><spring:message code="${user.isNew() ? 'user.add' : 'user.edit'}"/></h3>
        <hr>
        <form method="post" action="save">
            <input type="hidden" name="id" value="${user.id}">
            <table border="0" cellpadding="6">
                <tr>
                    <td><spring:message code="user.name"/>:</td>
                    <td><input type="text" value="${user.name}" size=30 name="name" required></td>
                </tr>
                <tr>
                    <td><spring:message code="user.email"/>:</td>
                    <td><input type="text" value="${user.email}" size=30 name="email" required></td>
                </tr>
                <tr>
                    <td><spring:message code="user.password"/>:</td>
                    <td><input type="text" value="${user.password}" size=30 name="password" required></td>
                </tr>
                <tr>
                    <td><spring:message code="user.roles"/>:</td>
                    <td>
                        <select name="id" style="width: 100%">
                        <option value="100000" selected>User</option>
                    <option value="100001">Admin</option>
                </select>
                    </td>
                   <%-- <td><input type="text" value="${user.roles}" size=30 name="password" required></td>  --%>
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
