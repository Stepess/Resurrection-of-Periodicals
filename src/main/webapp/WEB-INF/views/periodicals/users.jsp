<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Periodicals</title>
</head>
<body>
<c:forEach items="${userList}" var="user">
    <li id="user_<c:out value="user.id"/>">

            <c:out value="${user.username}"/> <br>
            <c:out value="${user.firstName}"/> <br>
            <c:out value="${user.lastName}"/> <br>
            <c:out value="${user.email}"/> <br>

    </li>
</c:forEach>
</body>
</html>
