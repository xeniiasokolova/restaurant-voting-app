<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<body>
<br>
<section>
    <form method="post" action="users">
        <spring:message code="app.login"/>:
            <select name="userId" style="width: 100px">
                <option value="100001" selected>User</option>
                <option value="100000">Admin</option>
            </select>
    </select>
        <button type="submit"><spring:message code="common.select"/></button>
    </form>
</section>
</body>
</html>