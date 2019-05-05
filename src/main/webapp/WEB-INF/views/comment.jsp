<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Periodicals</title>
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/resources/style.css" />"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"/>
</head>
<body>
<div class="commentView">
    <div class="commentUserId"><c:out value="${comment.userId}"/> </div>
    <div class="commentMessage"><c:out value="${comment.message}" /></div>
    <div>
        <span class="commentTime"><c:out value="${comment.date}" /></span>
    </div>
</div>
</body>
</html>
