<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page session="false" %>
<html>
<head>
    <title>Periodicals</title>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="/resources/style.css" />">
</head>
<body>
<h1><s:message code="periodicals.welcome"/> </h1>
<a href="<c:url value="/comments" />">Comments</a> |
<a href="<s:url value="/periodicals/register" />">Register</a>
</body>
</html>