<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Periodicals</title>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="/resources/style.css" />">
</head>
<body>
<c:forEach items="${commentList}" var="comment">
    <li id="comment_<c:out value="comment.id"/>">
        <div class="commentUserId"><c:out value="${comment.userId}"/> </div>
        <div class="commentMessage">
            <c:out value="${comment.message}"/>
        </div>
        <div>
            <span class="commentTime"><c:out value="${comment.date}"/></span>
            <span class="commentLocation">
(<c:out value="${comment.latitude}"/>,
<c:out value="${comment.longitude}"/>)</span>
        </div>
    </li>
</c:forEach>
</body>
</html>