<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Spittr</title>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="/resources/style.css" />">
</head>
<body>
<c:forEach items="${commentList}" var="comment">
    <li id="spittle_<c:out value="comment.id"/>">
        <div class="spittleMessage">
            <c:out value="${comment.message}"/>
        </div>
        <div>
            <span class="spittleTime"><c:out value="${comment.date}"/></span>
            <span class="spittleLocation">
(<c:out value="${comment.latitude}"/>,
<c:out value="${comment.longitude}"/>)</span>
        </div>
    </li>
</c:forEach>
</body>
</html>