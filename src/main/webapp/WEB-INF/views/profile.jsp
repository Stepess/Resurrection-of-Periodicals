<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<title>Periodicals</title>
<link rel="stylesheet" type="text/css"
href="<c:url value="/resources/style.css" />" >
</head>
<body>
<h1>Your Profile</h1>
Hello <security:authentication property="principal.username" />!
 <c:out value="${user.username}" /><br/>
 <c:out value="${user.firstName}" />
 <c:out value="${user.lastName}" />

<s:url href="/periodicals/{username}" var="periodicalsUrl">
 <s:param name="username" value="jbauer" />
</s:url>
</body>
</html>