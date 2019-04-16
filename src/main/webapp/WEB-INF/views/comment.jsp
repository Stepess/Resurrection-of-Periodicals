<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Periodicals</title>
</head>
<body>
<div class="spittleView">
    <div class="spittleMessage"><c:out value="${comment.message}" /></div>
    <div>
        <span class="spittleTime"><c:out value="${comment.date}" /></span>
    </div>
</div>
</body>
</html>
